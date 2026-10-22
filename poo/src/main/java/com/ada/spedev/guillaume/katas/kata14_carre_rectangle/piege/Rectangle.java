package com.ada.spedev.guillaume.katas.kata14_carre_rectangle.piege;

public class Rectangle {

	protected double largeur;
	protected double hauteur;

	public Rectangle(double largeur, double hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public double getLargeur() {
		return this.largeur;
	}

	public double getHauteur() {
		return this.hauteur;
	}

	// Contrat implicite de Rectangle : setLargeur ne change QUE la largeur
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public double aire() {
		return this.largeur * this.hauteur;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + this.largeur + " x " + this.hauteur;
	}
}
