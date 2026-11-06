package com.ada.spedev.guillaume.pokemon.correction.partie4;

// (partie 3, type en enum depuis l'étape 11) Pokémon de type Électrique (pas de talent : il garde la version "Aucun" de la mère)
public class PokemonElectrique extends Pokemon {

	public PokemonElectrique(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);
	}

	@Override
	public Type getType() {
		return Type.ELECTRIQUE;
	}

	// ÉTAPE 7 - L'Électrique résiste à l'Électrique
	@Override
	public double sensibiliteA(Type typeAttaque) {
		if (typeAttaque == Type.ELECTRIQUE) {
			return 0.5;
		}
		return 1.0;
	}
}
