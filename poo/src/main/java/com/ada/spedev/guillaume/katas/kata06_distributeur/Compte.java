package com.ada.spedev.guillaume.katas.kata06_distributeur;

public class Compte {

	private final String titulaire;
	private double solde;

	public Compte(String titulaire, double soldeInitial) {
		if (soldeInitial < 0) {
			throw new IllegalArgumentException("Le solde initial ne peut pas être négatif : " + soldeInitial);
		}
		this.titulaire = titulaire;
		this.solde = soldeInitial;
	}

	public String getTitulaire() {
		return this.titulaire;
	}

	public double getSolde() {
		return this.solde;
	}

	// "throws" annonce que cette méthode peut lancer une SoldeInsuffisantException.
	// Contrairement au kata 02 (qui retournait false), l'appelant ne peut pas ignorer l'échec
	// et il sait POURQUOI ça a échoué.
	public void debiter(double montant) throws SoldeInsuffisantException {
		if (montant <= 0) {
			throw new IllegalArgumentException("Le montant doit être positif : " + montant);
		}
		if (montant > this.solde) {
			throw new SoldeInsuffisantException(this.solde, montant);
		}
		this.solde -= montant;
	}

	@Override
	public String toString() {
		return this.titulaire + " : " + this.solde + " €";
	}
}
