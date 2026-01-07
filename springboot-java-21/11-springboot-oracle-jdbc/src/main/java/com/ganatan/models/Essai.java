package com.ganatan.models;

public class Essai {
    private Long id;
    private String nom;

    public Essai(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
