package com.ada.spedev.guillaume.pokemon.correction.parties1et2;

import java.util.ArrayList;
import java.util.List;

// ÉTAPE 3 - Un explorateur parcourt les arènes et collectionne les badges.
public class Explorateur implements Dresseur {

	private final String nom;
	private final String villeDOrigine;
	private final List<Pokemon> equipe = new ArrayList<>();   // composition : un dresseur A des Pokémon
	private final List<String> badges = new ArrayList<>();

	public Explorateur(String nom, String villeDOrigine) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("Un dresseur doit avoir un nom");
		}
		this.nom = nom;
		this.villeDOrigine = villeDOrigine;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	public List<String> getBadges() {
		return new ArrayList<>(this.badges);
	}

	@Override
	public String presentation() {
		return "Explorateur " + this.nom + ", de " + this.villeDOrigine + " (" + this.badges.size() + " badge(s))";
	}

	@Override
	public void capturer(Pokemon pokemon) throws EquipePleineException {
		if (this.equipe.contains(pokemon)) {
			throw new IllegalArgumentException(pokemon.getNom() + " est déjà dans l'équipe de " + this.nom);
		}
		if (this.equipe.size() >= 6) {
			throw new EquipePleineException(this.nom, pokemon);
		}
		this.equipe.add(pokemon);
	}

	// Copie : l'appelant ne peut pas ajouter un 7e Pokémon en contournant capturer()
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

	@Override
	public String badgeOffert() {
		return null;   // un explorateur ne donne pas de badge
	}

	// Spécificité : s'il gagne, il récupère le badge de son adversaire (s'il y en a un)
	@Override
	public Dresseur affronter(Dresseur adversaire) {
		Dresseur vainqueur = new Combat(this, adversaire).lancer();
		String badge = adversaire.badgeOffert();   // polymorphisme : chaque dresseur répond à sa façon
		if (vainqueur == this && badge != null && !this.badges.contains(badge)) {
			this.badges.add(badge);
			System.out.println(this.nom + " obtient le " + badge + " !");
		}
		return vainqueur;
	}

	@Override
	public String toString() {
		return presentation();
	}
}
