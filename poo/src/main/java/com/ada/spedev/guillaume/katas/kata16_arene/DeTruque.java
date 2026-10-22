package com.ada.spedev.guillaume.katas.kata16_arene;

// Retourne toujours la même valeur (bornée par le nombre de faces) : idéal pour vérifier un calcul
public class DeTruque implements De {

	private final int valeur;

	public DeTruque(int valeur) {
		this.valeur = valeur;
	}

	@Override
	public int lancer(int nbFaces) {
		return Math.min(this.valeur, nbFaces);
	}
}
