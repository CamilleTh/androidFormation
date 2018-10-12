package com.societe.persistancebd.bean;

public class Stagiaire {
	private int id;
	private String nom;
	private String prenom;
	
	public Stagiaire(String nom, String prenom){
		this.nom = nom;
		this.prenom = prenom;
	}

	public Stagiaire(int id, String nom, String prenom) {
		this(nom,prenom);
		this.id = id;
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
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return (id + " : " + nom + " - " + prenom);
	}
}
