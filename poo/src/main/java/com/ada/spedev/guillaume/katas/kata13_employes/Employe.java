package com.ada.spedev.guillaume.katas.kata13_employes;

// abstract : on ne peut pas faire new Employe(...). C'est un "moule incomplet".
// Une classe abstraite = une interface (Payable) + du code commun (nom, matricule, fichePaie).
public abstract class Employe implements Payable {

	private static int prochainMatricule = 1;

	private final String nom;
	private final int matricule;

	protected Employe(String nom) {
		this.nom = nom;
		this.matricule = prochainMatricule;
		prochainMatricule++;
	}

	public String getNom() {
		return this.nom;
	}

	public int getMatricule() {
		return this.matricule;
	}

	// Méthode abstraite : pas de code. Chaque sous-classe DOIT la fournir.
	public abstract double salaireBrut();

	public abstract String poste();

	// Méthode concrète qui s'appuie sur une méthode abstraite ("patron de méthode").
	// Le calcul du net est écrit une seule fois, pour tous les employés.
	public double salaireNet() {
		return salaireBrut() * 0.78;
	}

	@Override
	public double montantMensuel() {
		return salaireBrut();
	}

	@Override
	public String libelle() {
		return poste() + " " + this.nom + " (matricule " + this.matricule + ")";
	}

	public String fichePaie() {
		return String.format("%-35s brut %8.2f €   net %8.2f €", libelle(), salaireBrut(), salaireNet());
	}
}
