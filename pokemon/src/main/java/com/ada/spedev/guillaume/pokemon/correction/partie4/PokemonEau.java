package com.ada.spedev.guillaume.pokemon.correction.partie4;

// (partie 3, type en enum depuis l'étape 11) Pokémon de type Eau
public class PokemonEau extends Pokemon {

	public PokemonEau(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);
	}

	@Override
	public Type getType() {
		return Type.EAU;
	}

	// ÉTAPE 7 - L'Eau craint les Plantes et l'Électrique, et résiste au Feu et à l'Eau
	@Override
	public double sensibiliteA(Type typeAttaque) {
		if (typeAttaque == Type.PLANTE || typeAttaque == Type.ELECTRIQUE) {
			return 2.0;
		}
		if (typeAttaque == Type.FEU || typeAttaque == Type.EAU) {
			return 0.5;
		}
		return 1.0;
	}

	// ÉTAPE 8 - Talent "Croissance" : gagne 2 niveaux à chaque victoire au lieu d'un.
	// On appelle deux fois la version de la mère : elle garde la règle "niveau 100 maximum".
	@Override
	public String getTalent() {
		return "Croissance";
	}

	@Override
	public void gagnerNiveau() {
		super.gagnerNiveau();
		super.gagnerNiveau();
	}
}
