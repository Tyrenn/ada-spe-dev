package com.ada.spedev.guillaume.katas.kata07_formes;

public class Rectangle implements Forme {

	private final double largeur;
	private final double hauteur;

	public Rectangle(double largeur, double hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	@Override
	public double aire() {
		return this.largeur * this.hauteur;
	}

	@Override
	public double perimetre() {
		return 2 * (this.largeur + this.hauteur);
	}

	@Override
	public String nom() {
		return "Rectangle " + this.largeur + " x " + this.hauteur;
	}
}
