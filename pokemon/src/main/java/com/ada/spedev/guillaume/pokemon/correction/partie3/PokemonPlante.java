package com.ada.spedev.guillaume.pokemon.correction.partie3;

// ÉTAPES 6 et 7 - Pokémon de type Plante (pas de talent : il garde la version "Aucun" de la mère)
public class PokemonPlante extends Pokemon {

	public PokemonPlante(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);
	}

	@Override
	public String getType() {
		return "Plante";
	}

	// ÉTAPE 7 - Les Plantes craignent le Feu, et résistent à l'Eau, aux Plantes et à l'Électrique
	@Override
	public double sensibiliteA(String typeAdversaire) {
		if (typeAdversaire.equals("Feu")) {
			return 2.0;
		}
		if (typeAdversaire.equals("Eau") || typeAdversaire.equals("Plante") || typeAdversaire.equals("Électrique")) {
			return 0.5;
		}
		return 1.0;
	}
}
