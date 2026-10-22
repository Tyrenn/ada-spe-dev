package com.ada.spedev.guillaume.katas.kata07_formes;

// "implements Forme" : Cercle s'engage à fournir TOUTES les méthodes de l'interface,
// sinon le code ne compile pas.
public class Cercle implements Forme {

	private final double rayon;

	public Cercle(double rayon) {
		this.rayon = rayon;
	}

	@Override
	public double aire() {
		return Math.PI * this.rayon * this.rayon;
	}

	@Override
	public double perimetre() {
		return 2 * Math.PI * this.rayon;
	}

	@Override
	public String nom() {
		return "Cercle de rayon " + this.rayon;
	}
}
