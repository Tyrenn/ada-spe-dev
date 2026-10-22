package com.ada.spedev.guillaume.katas.kata05_feu_tricolore;

public class FeuTricolore {

	private final String nom;
	private Couleur couleur;
	private int secondesEcoulees;
	private boolean enPanne;

	public FeuTricolore(String nom) {
		this.nom = nom;
		this.couleur = Couleur.ROUGE;   // par sécurité, un feu démarre au rouge
		this.secondesEcoulees = 0;
	}

	public Couleur getCouleur() {
		return this.couleur;
	}

	public boolean isEnPanne() {
		return this.enPanne;
	}

	public void passerALaSuivante() {
		this.couleur = this.couleur.suivante();
		this.secondesEcoulees = 0;
	}

	// Simule le passage d'une seconde : le feu change tout seul quand la durée de la couleur est écoulée
	public void tic() {
		if (this.enPanne) {
			return;
		}
		this.secondesEcoulees++;
		if (this.secondesEcoulees >= this.couleur.getDureeSecondes()) {
			passerALaSuivante();
		}
	}

	public void tomberEnPanne() {
		this.enPanne = true;
		this.couleur = Couleur.ORANGE;
	}

	public void reparer() {
		this.enPanne = false;
		this.couleur = Couleur.ROUGE;
		this.secondesEcoulees = 0;
	}

	@Override
	public String toString() {
		return "Feu " + this.nom + " : " + this.couleur + (this.enPanne ? " (clignotant)" : "");
	}
}
