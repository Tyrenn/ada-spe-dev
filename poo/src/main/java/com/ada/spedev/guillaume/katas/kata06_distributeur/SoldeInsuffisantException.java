package com.ada.spedev.guillaume.katas.kata06_distributeur;

// Une exception personnalisée est une classe comme une autre : elle peut avoir des attributs et des méthodes.
// "extends Exception" fait d'elle une exception VÉRIFIÉE (checked) : l'appelant est OBLIGÉ de la gérer.
// (le mot-clé extends sera expliqué dans la partie sur l'héritage)
public class SoldeInsuffisantException extends Exception {

	private final double solde;
	private final double montantDemande;

	public SoldeInsuffisantException(double solde, double montantDemande) {
		super("Solde insuffisant : " + solde + " € disponibles, " + montantDemande + " € demandés");
		this.solde = solde;
		this.montantDemande = montantDemande;
	}

	public double getManquant() {
		return this.montantDemande - this.solde;
	}
}
