package com.ada.spedev.guillaume.pokemon.correction.partie3;

// ÉTAPES 6 et 7 - Pokémon de type Électrique (pas de talent : il garde la version "Aucun" de la mère)
public class PokemonElectrique extends Pokemon {

	public PokemonElectrique(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);
	}

	@Override
	public String getType() {
		return "Électrique";
	}

	// ÉTAPE 7 - L'Électrique résiste à l'Électrique
	@Override
	public double sensibiliteA(String typeAdversaire) {
		if (typeAdversaire.equals("Électrique")) {
			return 0.5;
		}
		return 1.0;
	}
}
