package com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution;

// Immuable : au lieu de modifier, on crée un nouveau Rectangle (comme les Fractions du kata 03)
public class Rectangle implements Forme {

	private final double largeur;
	private final double hauteur;

	public Rectangle(double largeur, double hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public Rectangle avecLargeur(double nouvelleLargeur) {
		return new Rectangle(nouvelleLargeur, this.hauteur);
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
	public String toString() {
		return "Rectangle " + this.largeur + " x " + this.hauteur;
	}
}
