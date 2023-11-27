package com.project.fifidianana.model;

public class Candidat {
    private int id;
    private String nom;
    private String parti;

    public Candidat() {
    }

    public Candidat(int id, String nom, String parti) {
        this.id = id;
        this.nom = nom;
        this.parti = parti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getParti() {
        return parti;
    }

    public void setParti(String parti) {
        this.parti = parti;
    }
}
