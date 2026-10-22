package com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution;

// Carre n'hérite plus de Rectangle : il n'y a plus de contrat à trahir.
public class Carre implements Forme {

	private final double cote;

	public Carre(double cote) {
		this.cote = cote;
	}

	public Carre avecCote(double nouveauCote) {
		return new Carre(nouveauCote);
	}

	// Si on a besoin d'un Rectangle, on le demande explicitement
	public Rectangle versRectangle() {
		return new Rectangle(this.cote, this.cote);
	}

	@Override
	public double aire() {
		return this.cote * this.cote;
	}

	@Override
	public double perimetre() {
		return 4 * this.cote;
	}

	@Override
	public String toString() {
		return "Carré " + this.cote;
	}
}
