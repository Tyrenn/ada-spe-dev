package com.ada.spedev.guillaume.pokemon.correction.partie3;

import java.util.ArrayList;
import java.util.List;

// ÉTAPE 9 - Il ne reste que ce qui est PROPRE à l'explorateur : sa ville, ses badges, sa façon d'affronter.
public class Explorateur extends DresseurAbstrait {

	private final String villeDOrigine;
	private final List<String> badges = new ArrayList<>();

	public Explorateur(String nom, String villeDOrigine) {
		super(nom);
		this.villeDOrigine = villeDOrigine;
	}

	public List<String> getBadges() {
		return new ArrayList<>(this.badges);
	}

	@Override
	public int tailleMaxEquipe() {
		return 6;
	}

	@Override
	public String presentation() {
		return "Explorateur " + getNom() + ", de " + this.villeDOrigine + " (" + this.badges.size() + " badge(s))";
	}

	@Override
	public Dresseur affronter(Dresseur adversaire) {
		Dresseur vainqueur = new Combat(this, adversaire).lancer();
		String badge = adversaire.badgeOffert();
		if (vainqueur == this && badge != null && !this.badges.contains(badge)) {
			this.badges.add(badge);
			System.out.println(getNom() + " obtient le " + badge + " !");
		}
		return vainqueur;
	}
}
