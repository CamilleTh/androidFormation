package com.societe.menuactionmodemul;

public class Stagiaire {
    private String nom;
    private boolean selectionne;

    public Stagiaire(String nom, boolean selectionne) {
        this.nom = nom;
        this.selectionne = selectionne;
    }

    public boolean isSelectionne() {
        return selectionne;
    }

    public void setSelectionne(boolean selectionne) {
        this.selectionne = selectionne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
