package com.ada.spedev.guillaume.katas.kata12_comptes;

// extends : CompteCourant récupère TOUT ce que contient Compte (attributs et méthodes)
// et peut ajouter ou REDÉFINIR des comportements.
public class CompteCourant extends Compte {

	private final double decouvertAutorise;

	public CompteCourant(String titulaire, double decouvertAutorise) {
		super(titulaire);   // appelle le constructeur de Compte : OBLIGATOIREMENT en première ligne
		this.decouvertAutorise = decouvertAutorise;
	}

	// Redéfinition (override) : on remplace le comportement de Compte.retirer
	@Override
	public boolean retirer(double montant) {
		if (montant <= 0 || montant > this.solde + this.decouvertAutorise) {
			return false;
		}
		this.solde -= montant;   // accès direct grâce à protected
		return true;
	}

	// Des agios de 1 % si on est à découvert en fin de mois
	@Override
	public void finDeMois() {
		if (this.solde < 0) {
			this.solde += this.solde * 0.01;
		}
	}

	@Override
	public String toString() {
		// super.toString() : on réutilise la version de la classe mère et on la complète
		return "[Courant] " + super.toString() + " (découvert autorisé : " + this.decouvertAutorise + " €)";
	}
}
