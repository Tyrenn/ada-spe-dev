package com.ada.spedev.guillaume.katas.kata12_comptes;

// Classe mère (ou "superclasse") : le code commun à tous les comptes
public class Compte {

	private final String titulaire;

	// protected : visible par cette classe ET par ses sous-classes (et le package).
	// Attention : chaque sous-classe peut maintenant casser l'invariant... c'est un premier danger.
	protected double solde;

	public Compte(String titulaire) {
		this.titulaire = titulaire;
		this.solde = 0;
	}

	public String getTitulaire() {
		return this.titulaire;
	}

	public double getSolde() {
		return this.solde;
	}

	public void deposer(double montant) {
		if (montant <= 0) {
			throw new IllegalArgumentException("Montant invalide : " + montant);
		}
		this.solde += montant;
	}

	public boolean retirer(double montant) {
		if (montant <= 0 || montant > this.solde) {
			return false;
		}
		this.solde -= montant;
		return true;
	}

	// Appelée une fois par mois. Par défaut, il ne se passe rien.
	public void finDeMois() {
	}

	@Override
	public String toString() {
		return this.titulaire + " : " + Math.round(this.solde * 100) / 100.0 + " €";
	}
}
