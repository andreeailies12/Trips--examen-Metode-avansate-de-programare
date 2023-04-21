package com.example.examen.domain;

import java.util.Objects;

public class Excursie extends Entity<Long> {
    private String oras;
    private String atractie;
    private String categorie;
    private double pret;

    public Excursie(){

    }
    public Excursie(String oras, String atractie, String categorie, double pret) {
        this.oras = oras;
        this.atractie = atractie;
        this.categorie = categorie;
        this.pret = pret;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getAtractie() {
        return atractie;
    }

    public void setAtractie(String atractie) {
        this.atractie = atractie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Excursie{" +
                "oras='" + oras + '\'' +
                ", atractie='" + atractie + '\'' +
                ", categorie='" + categorie + '\'' +
                ", pret=" + pret +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursie excursie = (Excursie) o;
        return Double.compare(excursie.pret, pret) == 0 && Objects.equals(oras, excursie.oras) && Objects.equals(atractie, excursie.atractie) && Objects.equals(categorie, excursie.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oras, atractie, categorie, pret);
    }
}
