package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 11 - Une enum remplace les String "Feu", "Eau"... : une faute de frappe ne compile plus.
public enum Type {
	NORMAL("Normal"),
	FEU("Feu"),
	EAU("Eau"),
	PLANTE("Plante"),
	ELECTRIQUE("Électrique");

	private final String libelle;

	Type(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return this.libelle;
	}
}
