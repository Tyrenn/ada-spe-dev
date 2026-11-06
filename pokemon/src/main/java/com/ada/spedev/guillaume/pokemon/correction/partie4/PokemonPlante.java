package com.ada.spedev.guillaume.pokemon.correction.partie4;

// (partie 3, type en enum depuis l'étape 11) Pokémon de type Plante (pas de talent : il garde la version "Aucun" de la mère)
public class PokemonPlante extends Pokemon {

	public PokemonPlante(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);
	}

	@Override
	public Type getType() {
		return Type.PLANTE;
	}

	// ÉTAPE 7 - Les Plantes craignent le Feu, et résistent à l'Eau, aux Plantes et à l'Électrique
	@Override
	public double sensibiliteA(Type typeAttaque) {
		if (typeAttaque == Type.FEU) {
			return 2.0;
		}
		if (typeAttaque == Type.EAU || typeAttaque == Type.PLANTE || typeAttaque == Type.ELECTRIQUE) {
			return 0.5;
		}
		return 1.0;
	}
}
