package com.ada.spedev.guillaume.katas.kata02_compte_bancaire;

public class CompteBancaire {

	// private : personne en dehors de cette classe ne peut lire ou modifier ces attributs directement.
	// final : le titulaire et le numéro ne changent jamais après la construction.
	private final String titulaire;
	private final String numero;
	private double solde;

	public CompteBancaire(String titulaire, String numero) {
		this.titulaire = titulaire;
		this.numero = numero;
		this.solde = 0;
	}

	// Getters : on donne accès en LECTURE
	public String getTitulaire() {
		return this.titulaire;
	}

	public String getNumero() {
		return this.numero;
	}

	public double getSolde() {
		return this.solde;
	}

	// Pas de setSolde() ! On ne modifie le solde qu'à travers des opérations qui ont un sens métier.

	public void deposer(double montant) {
		if (montant <= 0) {
			throw new IllegalArgumentException("Le montant d'un dépôt doit être positif : " + montant);
		}
		this.solde += montant;
	}

	// Retourne false si le retrait est refusé : l'invariant "solde >= 0" est toujours garanti
	public boolean retirer(double montant) {
		if (montant <= 0) {
			throw new IllegalArgumentException("Le montant d'un retrait doit être positif : " + montant);
		}
		if (montant > this.solde) {
			return false;
		}
		this.solde -= montant;
		return true;
	}

	public boolean virerVers(CompteBancaire destination, double montant) {
		if (!retirer(montant)) {
			return false;
		}
		destination.deposer(montant);
		return true;
	}

	@Override
	public String toString() {
		return "Compte " + this.numero + " (" + this.titulaire + ") : " + this.solde + " €";
	}
}
