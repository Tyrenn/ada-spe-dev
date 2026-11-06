package com.ada.spedev.guillaume.pokemon.correction.partie3;

// (identique à la partie 2) Exception vérifiée levée quand l'équipe d'un dresseur est complète
public class EquipePleineException extends Exception {

	private final Pokemon pokemonRefuse;

	public EquipePleineException(String nomDresseur, Pokemon pokemonRefuse) {
		super("L'équipe de " + nomDresseur + " est pleine : " + pokemonRefuse.getNom() + " part au PC");
		this.pokemonRefuse = pokemonRefuse;
	}

	public Pokemon getPokemonRefuse() {
		return this.pokemonRefuse;
	}
}
