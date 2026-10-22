package com.ada.spedev.guillaume.katas.kata05_feu_tricolore;

// Une enum est une classe dont on connaît TOUTES les instances à l'avance.
// Il n'existera jamais d'autre Couleur que ces trois-là : impossible de faire new Couleur().
public enum Couleur {

	VERT(30),
	ORANGE(5),
	ROUGE(25);

	// Une enum peut avoir des attributs, un constructeur et des méthodes, comme une classe
	private final int dureeSecondes;

	Couleur(int dureeSecondes) {
		this.dureeSecondes = dureeSecondes;
	}

	public int getDureeSecondes() {
		return this.dureeSecondes;
	}

	public Couleur suivante() {
		switch (this) {
			case VERT:
				return ORANGE;
			case ORANGE:
				return ROUGE;
			default:
				return VERT;
		}
	}

	public boolean peutPasser() {
		return this == VERT;
	}
}
