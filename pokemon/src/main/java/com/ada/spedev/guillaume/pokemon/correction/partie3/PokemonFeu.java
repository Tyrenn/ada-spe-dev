package com.ada.spedev.guillaume.pokemon.correction.partie3;

// ÉTAPES 6 à 8 - Pokémon de type Feu
public class PokemonFeu extends Pokemon {

	public PokemonFeu(String nom, int niveau, int puissance) {
		super(nom, niveau, puissance);   // construit d'abord la partie "Pokemon"
	}

	@Override
	public String getType() {
		return "Feu";
	}

	// ÉTAPE 7 - Le Feu craint l'Eau, et résiste au Feu et aux Plantes
	@Override
	public double sensibiliteA(String typeAdversaire) {
		if (typeAdversaire.equals("Eau")) {
			return 2.0;
		}
		if (typeAdversaire.equals("Feu") || typeAdversaire.equals("Plante")) {
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
