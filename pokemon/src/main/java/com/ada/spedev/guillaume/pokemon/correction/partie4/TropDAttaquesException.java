package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 12 - Exception VÉRIFIÉE : "votre Pokémon veut apprendre une 5e attaque, laquelle oublier ?"
public class TropDAttaquesException extends Exception {

	private final Attaque attaqueRefusee;

	public TropDAttaquesException(String nomPokemon, Attaque attaqueRefusee) {
		super(nomPokemon + " connaît déjà " + Pokemon.NB_MAX_ATTAQUES + " attaques et ne peut pas apprendre "
				+ attaqueRefusee.getNom());
		this.attaqueRefusee = attaqueRefusee;
	}

	public Attaque getAttaqueRefusee() {
		return this.attaqueRefusee;
	}
}
