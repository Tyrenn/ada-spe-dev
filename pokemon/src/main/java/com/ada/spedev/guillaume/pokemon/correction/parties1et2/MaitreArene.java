package com.ada.spedev.guillaume.pokemon.correction.parties1et2;

import java.util.ArrayList;
import java.util.List;

// ÉTAPE 4 - Le maître d'une arène : il remet un badge à qui le bat.
public class MaitreArene implements Dresseur {

	private final String nom;
	private final String nomArene;
	private final List<Pokemon> equipe = new ArrayList<>();
	private int nbDefensesReussies = 0;

	public MaitreArene(String nom, String nomArene) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("Un dresseur doit avoir un nom");
		}
		this.nom = nom;
		this.nomArene = nomArene;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	public String getNomArene() {
		return this.nomArene;
	}

	public int getNbDefensesReussies() {
		return this.nbDefensesReussies;
	}

	@Override
	public String presentation() {
		return "Maître " + this.nom + " de l'arène " + this.nomArene + " (" + this.nbDefensesReussies + " défense(s) réussie(s))";
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
		return "Badge " + this.nomArene;
	}

	// Spécificité : il est chez lui, il soigne son équipe avant chaque combat et compte ses victoires
	@Override
	public Dresseur affronter(Dresseur adversaire) {
		soignerEquipe();
		System.out.println(this.nom + " soigne son équipe avant le combat.");
		Dresseur vainqueur = new Combat(this, adversaire).lancer();
		if (vainqueur == this) {
			this.nbDefensesReussies++;
		}
		return vainqueur;
	}

	@Override
	public String toString() {
		return presentation();
	}
}
