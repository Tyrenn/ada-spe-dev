package com.ada.spedev.guillaume.katas.kata10_reductions;

public class Article {

	private final String nom;
	private final double prixUnitaire;
	private final int quantite;

	public Article(String nom, double prixUnitaire, int quantite) {
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
	}

	public double prixTotal() {
		return this.prixUnitaire * this.quantite;
	}

	@Override
	public String toString() {
		return this.quantite + " x " + this.nom + " à " + this.prixUnitaire + " €";
	}
}
