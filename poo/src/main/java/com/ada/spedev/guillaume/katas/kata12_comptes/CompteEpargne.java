package com.ada.spedev.guillaume.katas.kata12_comptes;

public class CompteEpargne extends Compte {

	private final double tauxAnnuel;
	private int retraitsCeMois = 0;

	public CompteEpargne(String titulaire, double tauxAnnuel) {
		super(titulaire);
		this.tauxAnnuel = tauxAnnuel;
	}

	// Un seul retrait par mois sur un livret
	@Override
	public boolean retirer(double montant) {
		if (this.retraitsCeMois >= 1) {
			return false;
		}
		boolean ok = super.retirer(montant);   // on délègue la vérification du solde à Compte
		if (ok) {
			this.retraitsCeMois++;
		}
		return ok;
	}

	@Override
	public void finDeMois() {
		this.solde += this.solde * this.tauxAnnuel / 100 / 12;
		this.retraitsCeMois = 0;
	}

	@Override
	public String toString() {
		return "[Épargne " + this.tauxAnnuel + " %] " + super.toString();
	}
}
