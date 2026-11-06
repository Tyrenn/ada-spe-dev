package com.ada.spedev.guillaume.pokemon.correction.partie3;

import java.util.ArrayList;
import java.util.List;

// ÉTAPE 9 - Les trois dresseurs de la partie 2 recopiaient le même code (équipe, capture, Pokémon actif, soin).
// Il est maintenant écrit une seule fois ici.
//
// implements Dresseur : la classe abstraite s'engage à respecter l'interface, mais elle n'écrit PAS
// presentation() ni affronter() : chaque dresseur concret les fournira.
public abstract class DresseurAbstrait implements Dresseur {

	private final String nom;
	private final List<Pokemon> equipe = new ArrayList<>();

	protected DresseurAbstrait(String nom) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("Un dresseur doit avoir un nom");
		}
		this.nom = nom;
	}

	// Chaque sorte de dresseur dit combien de Pokémon il peut avoir
	public abstract int tailleMaxEquipe();

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public void capturer(Pokemon pokemon) throws EquipePleineException {
		if (this.equipe.contains(pokemon)) {
			throw new IllegalArgumentException(pokemon.getNom() + " est déjà dans l'équipe de " + this.nom);
		}
		if (this.equipe.size() >= tailleMaxEquipe()) {   // la version de la sous-classe
			throw new EquipePleineException(this.nom, pokemon);
		}
		this.equipe.add(pokemon);
	}

	@Override
	public List<Pokemon> getEquipe() {
		return new ArrayList<>(this.equipe);
	}

	@Override
	public Pokemon pokemonActif() {
		for (Pokemon p : this.equipe) {
			if (!p.estKo()) {
				return p;
			}
		}
		return null;
	}

	@Override
	public boolean peutEncoreCombattre() {
		return pokemonActif() != null;
	}

	@Override
	public void soignerEquipe() {
		for (Pokemon p : this.equipe) {
			p.soigner();
		}
	}

	// Par défaut, pas de badge. MaitreArene redéfinit cette méthode.
	@Override
	public String badgeOffert() {
		return null;
	}

	@Override
	public String toString() {
		return presentation();
	}
}
