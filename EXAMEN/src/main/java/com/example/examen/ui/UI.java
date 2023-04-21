package com.example.examen.ui;

import com.example.examen.domain.Excursie;
import com.example.examen.service.ServiceExcursie;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final ServiceExcursie ss;
    private final Scanner scanner;

    public UI(ServiceExcursie ss) {
        this.ss = ss;
        this.scanner = new Scanner(System.in);
    }

    private void printMenu() {
        {
            System.out.println();
            System.out.println("BINE AI VENIT! Ce optiune doresti sa alegi?");
            System.out.println();
            System.out.println("1. Adaugare entitati in baza de date.");
            System.out.println("2. Afisarea entitati existenti.");
            System.out.println("3. Cautarea unei anumite entitati, dupa oras si atractie..");
            System.out.println("4. Filtrara entitatilor dintr-o anumita categorie si afisarea lor in consola.");
            System.out.println("5. Filtrarea entitatilor dintr-o anumita categorie," +
                    " care au pretl mai mare decat un pret dat.");
            System.out.println("6. Sortarea alfabetica a entitatilor,dupa oras si atractie, crescator.");
            System.out.println("7. Sortarea entitatilor crescator, dupa categorie.");
            System.out.println("8. Sortare dupa pret, descrescator.");
            System.out.println("9. Exit.");

        }
    }

    private String readLine(String helper) {
        System.out.print(helper);
        return scanner.nextLine();

    }

    public void start() {

        while (true) {
            loop();
        }
    }
    private void loop() {
        printMenu();
        String string = readLine(">>> ");
        try {

            {
                switch (string) {
                    case "1" -> add();
                    case "2" -> afisare();
                    case "3" -> cautare();
                    case "4" -> filtrarea();
                    case "5" -> filtrareb();
                    case "6" -> sortarea();
                    case "7" -> sortareb();
                    case "8" -> sortarec();
                    case "9" -> System.exit(0);
                    default -> {
                        System.out.println("Optiune gresita, va rugam reincercati!");
                    }
                }
            }

        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }
    private void add() {

        try {
            String oras = readLine("Oras: ");
            String atractie = readLine("Atractie: ");
            String categorie = readLine("Categorie: ");
            Double pret = Double.valueOf(readLine("Pret: "));

            Excursie student = new Excursie(oras, atractie, categorie, pret);
            ss.addExcursie(student);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void afisare() {

        int n = ss.getExcursii().size();
        for (int i = 0; i < n; i++) {
            System.out.println(ss.lista_excursii.get(i));
        }
    }
    private void cautare() {
        try {
            String oras = readLine("Oras: ");
            String atractie = readLine("Atractie: ");

            List<Excursie> s = ss.cautare(oras, atractie);
            s.forEach(x -> System.out.println(x));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filtrareb() {
        try {
            String categorie = readLine("Categorie: ");
            Double pret = Double.valueOf(readLine("Pret: "));
            List<Excursie> filtrareb = ss.filtrareb(categorie, pret);
            for (Excursie s : filtrareb)
                System.out.println(s);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filtrarea() {
        try {
            String categorie = readLine("Categorie: ");

            List<Excursie> filtrarea = ss.filtrarea(categorie);
            for (Excursie s : filtrarea)
                System.out.println(s);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void sortarec() {

        ss.sortarec().forEach(System.out::println);
    }
    private void sortareb() {
        try {

            List<Excursie> sortareb = ss.sortareb();
            for (Excursie s : sortareb)
                System.out.println(s);
            afisare_id_atractie_oras(sortareb);
            afisare_oras_atractie_categorie(sortareb);
            afisare_id_pret(sortareb);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortarea() {
        try {

            List<Excursie> sortarea = ss.sortarea();
            for (Excursie s : sortarea)
                System.out.println(s);
            afisare_id_atractie_oras(sortarea);
            afisare_oras_atractie_categorie(sortarea);
            afisare_id_pret(sortarea);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void afisare_id_atractie_oras(List<Excursie> lista) {
        int id;
        System.out.println();
        System.out.println("Afisarea de tipul id - atractie - oras");
        for (Excursie s : lista) {
            id = ss.getExcursieid(s.getOras(), s.getAtractie(), s.getCategorie(), s.getPret());
//            System.out.print(id + " ");
//            System.out.print(s.getNume()+" ");
//            System.out.print(s.getPrenume() + " ");

            System.out.println("Excursie {" +
                    "id='" + id + '\'' +
                    ", atractie='" + s.getAtractie() + '\'' +

                    ", oras='" + s.getOras() + '\'' +
                    '}');
        }
    }
    private void afisare_oras_atractie_categorie(List<Excursie> lista) {

        System.out.println();
        System.out.println("Afisarea de tipul- oras - atractie -categorie");
        for (Excursie s : lista) {
            System.out.println("Excursie {" +
                    ", oras='" + s.getOras() + '\'' +
                    ", atractie='" + s.getAtractie() + '\'' +
                    "categorie ='" + s.getPret() + '\'' +
                    '}');
        }
    }
    private void afisare_id_pret(List<Excursie> lista) {
        int id;
        System.out.println();
        System.out.println("Afisarea de tipul id - pret");
        for (Excursie s : lista) {
            id = ss.getExcursieid(s.getOras(), s.getAtractie(), s.getCategorie(), s.getPret());

            System.out.println("Excursie {" +
                    "id='" + id + '\'' +
                    ", pret='" + s.getPret() + '\'' +
                    '}');
        }
    }

}
