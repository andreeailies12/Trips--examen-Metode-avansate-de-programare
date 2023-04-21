package com.example.examen.service;

import com.example.examen.domain.Excursie;
import com.example.examen.domain.validators.Validator;
import com.example.examen.repository.Repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceExcursie  {
    private final Repository<Long, Excursie> repoExcursie;
    private final Validator<Long, Excursie> validator;
    public List<Excursie> lista_excursii = new ArrayList<>();

    public ServiceExcursie(Repository<Long, Excursie> repoExcursie, Validator<Long, Excursie> validator) {
        this.repoExcursie = repoExcursie;
        this.validator = validator;
    }

    public void addExcursie(Excursie student) {
        ;
        validator.validate(student);
        repoExcursie.save(student);

    }

    public int getExcursieid(String oras, String atractie, String categorie, Double pret) {
        return repoExcursie.findId(oras, atractie, categorie, pret);
    }

    public Iterable<Excursie> getAllExcursii() {
        return repoExcursie.findAll();
    }

    public List<Excursie> getExcursii() {
        Iterable<Excursie> toti = repoExcursie.findAll();
        for (Excursie s : toti) {
            if (!(lista_excursii.contains(s)))
                lista_excursii.add(s);
        }
        return StreamSupport.stream(lista_excursii.spliterator(), false).collect(Collectors.toList());//?

    }

    public List<Excursie> cautare(String oras, String atractie) {
        Iterable<Excursie> gasit = repoExcursie.findbyName(oras, atractie);
        List<Excursie> depistati = new ArrayList<>();
        for (Excursie s : gasit) {
            depistati.add(s);
        }

        if (depistati.size() == 0) {
            throw new RuntimeException("Entitatea cu acest oras si atractie nu exista!");
        }
        return depistati;
    }

    public List<Excursie> filtrarea(String categorie) {
        List<Excursie> toti = getExcursii();
        Predicate<Excursie> p1 = n -> Objects.equals(n.getCategorie(), categorie);
        List<Excursie> filtered = toti.stream().filter(p1).collect(Collectors.toList());
        if (filtered.size() == 0)
            throw new RuntimeException("Nu exista entitati de aceasta categorie!");
        return filtered;
    }

    public List<Excursie> filtrareb(String categorie, Double pret) {
        List<Excursie> toti = getExcursii();
        Predicate<Excursie> p1 = n -> Objects.equals(n.getCategorie(), categorie);// returneaza boolean;
        Predicate<Excursie> p2 = n -> n.getPret() > pret;//? n->; lambda
        List<Excursie> filtered = toti.stream().filter(p1.and(p2)).collect(Collectors.toList());
        if (filtered.size() == 0)
            throw new RuntimeException("Nu exista entitati de aceasta categorie care sa aiba pretul mai mare decat cel dat!");
        return filtered;
    }

    public List<Excursie> sortarea() {

        List<Excursie> toti = getExcursii();
        //List<Excursie> sortare_alfabetica = toti.stream().sorted((s1, s2) -> s1.getNume().compareTo(s2.getNume())).collect(Collectors.toList());
        Comparator<Excursie> excursieComparator = Comparator.comparing(Excursie::getOras).thenComparing(Excursie -> Excursie.getAtractie()).thenComparing(Excursie -> Excursie.getPret());
        toti.sort(excursieComparator);
        return toti;


    }

    public List<Excursie> sortareb() {
        List<Excursie> toti = getExcursii();
        Comparator<Excursie> excursieComparator = Comparator.comparing(Excursie::getCategorie);
        toti.sort(excursieComparator);
        return toti;


    }

    public List<String> sortarec () {
//        List<Excursie> toti = getgetExcursii()();
//        Comparator<Excursie> excursieComparator = Comparator.comparing(Excursie::getPret).reversed();
//        toti.sort(excursieComparator);
//        return toti;
        Iterable<Excursie> toti = getExcursii();
        List<Excursie> excursielist = StreamSupport.stream(toti.spliterator(),
                false).collect(Collectors.toList());
        excursielist.sort(Comparator.comparing(Excursie::getPret));
        Collections.reverse(excursielist);
        return excursielist.stream().map
                        (x -> x.getOras() + " " + x.getAtractie() + " " + x.getCategorie()+" "+x.getPret()).
                collect(Collectors.toList());

    }
}
