package com.ada.spedev.guillaume.katas.kata07_formes;

// Étape 2 : ajouter une forme = ajouter une classe. Aucune ligne de aireTotale() ou plusGrande() ne change.
public class Carre implements Forme {

	private final double cote;

	public Carre(double cote) {
		this.cote = cote;
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
	public String nom() {
		return "Carré de côté " + this.cote;
	}
}
