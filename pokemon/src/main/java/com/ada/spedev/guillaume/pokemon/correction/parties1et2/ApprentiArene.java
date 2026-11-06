package com.ada.spedev.guillaume.pokemon.correction.parties1et2;

import java.util.ArrayList;
import java.util.List;

// ÉTAPE 4 - L'apprenti garde l'entrée d'une arène. Il n'a droit qu'à 3 Pokémon et ne donne pas de badge.
public class ApprentiArene implements Dresseur {

	private final String nom;
	private final String nomArene;
	private final List<Pokemon> equipe = new ArrayList<>();

	public ApprentiArene(String nom, String nomArene) {
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

	@Override
	public String presentation() {
		return "Apprenti " + this.nom + " de l'arène " + this.nomArene;
	}

	@Override
	public void capturer(Pokemon pokemon) throws EquipePleineException {
		if (this.equipe.contains(pokemon)) {
			throw new IllegalArgumentException(pokemon.getNom() + " est déjà dans l'équipe de " + this.nom);
		}
		if (this.equipe.size() >= 3) {
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
		return null;
	}

	// Spécificité : s'il perd, il indique le chemin vers son maître
	@Override
	public Dresseur affronter(Dresseur adversaire) {
		Dresseur vainqueur = new Combat(this, adversaire).lancer();
		if (vainqueur != this) {
			System.out.println(this.nom + " : \"Bien joué ! Le maître de l'arène " + this.nomArene + " t'attend.\"");
		}
		return vainqueur;
	}

	@Override
	public String toString() {
		return presentation();
	}
}
