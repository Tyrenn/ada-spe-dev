package com.ada.spedev.guillaume.katas.kata11_capacites;

public class Plongeur implements Nageur, Marcheur {

	private final String nom;

	public Plongeur(String nom) {
		this.nom = nom;
	}

	@Override
	public int profondeurMaxMetres() {
		return 40;
	}

	@Override
	public double vitesseKmH() {
		return 5;
	}

	@Override
	public String toString() {
		return "Plongeur " + this.nom;
	}
}
