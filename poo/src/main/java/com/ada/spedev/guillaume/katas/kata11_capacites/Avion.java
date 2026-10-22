package com.ada.spedev.guillaume.katas.kata11_capacites;

public class Avion implements Volant {

	private final String modele;

	public Avion(String modele) {
		this.modele = modele;
	}

	@Override
	public int altitudeMaxMetres() {
		return 12000;
	}

	@Override
	public String toString() {
		return "Avion " + this.modele;
	}
}
