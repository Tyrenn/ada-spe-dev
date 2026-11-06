package com.ada.spedev.guillaume.pokemon.correction.partie4;

// (partie 3, type en enum depuis l'étape 11) Pokémon de type Feu
public class PokemonFeu extends Pokemon {

	public PokemonFeu(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);   // construit d'abord la partie "Pokemon"
	}

	@Override
	public Type getType() {
		return Type.FEU;
	}

	// ÉTAPE 7 - Le Feu craint l'Eau, et résiste au Feu et aux Plantes
	@Override
	public double sensibiliteA(Type typeAttaque) {
		if (typeAttaque == Type.EAU) {
			return 2.0;
		}
		if (typeAttaque == Type.FEU || typeAttaque == Type.PLANTE) {
			return 0.5;
		}
		return 1.0;
	}

	// ÉTAPE 8 - Talent "Brasier" : 20 % de force en plus.
	// super.getForce() : on réutilise le calcul de la mère au lieu de le recopier.
	@Override
	public String getTalent() {
		return "Brasier";
	}

	@Override
	public int getForce() {
		return (int) Math.round(super.getForce() * 1.2);
	}
}
