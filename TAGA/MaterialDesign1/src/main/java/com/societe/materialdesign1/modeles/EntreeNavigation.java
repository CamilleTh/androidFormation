package com.societe.materialdesign1.modeles;

public class EntreeNavigation {
    private boolean afficherNotification;
    private String titre;

    public EntreeNavigation() {
    }

    public EntreeNavigation(boolean afficherNotification, String titre) {
        this.afficherNotification = afficherNotification;
        this.titre = titre;
    }

    public boolean isAfficherNotification() {
        return afficherNotification;
    }

    public void setAfficherNotification(boolean afficherNotification) {
        this.afficherNotification = afficherNotification;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
