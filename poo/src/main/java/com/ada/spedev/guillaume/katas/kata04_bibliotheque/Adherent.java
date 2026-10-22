package com.ada.spedev.guillaume.katas.kata04_bibliotheque;

public class Adherent {

	public static final int MAX_EMPRUNTS = 3;

	// static : UN SEUL compteur partagé par toute la classe, pas un par objet
	private static int prochainNumero = 1;

	private final int numero;
	private final String nom;
	// Un tableau a une taille fixe : on le crée à la taille max et on compte les cases réellement remplies
	private final Livre[] emprunts = new Livre[MAX_EMPRUNTS];
	private int nbEmprunts = 0;

	public Adherent(String nom) {
		this.nom = nom;
		this.numero = prochainNumero;
		prochainNumero++;
	}

	public int getNumero() {
		return this.numero;
	}

	public String getNom() {
		return this.nom;
	}

	// On retourne une copie : sinon l'appelant pourrait faire getEmprunts()[0] = null et casser l'état
	public Livre[] getEmprunts() {
		Livre[] copie = new Livre[this.nbEmprunts];
		for (int i = 0; i < this.nbEmprunts; i++) {
			copie[i] = this.emprunts[i];
		}
		return copie;
	}

	public boolean peutEmprunter() {
		return this.nbEmprunts < MAX_EMPRUNTS;
	}

	void ajouterEmprunt(Livre livre) {
		this.emprunts[this.nbEmprunts] = livre;
		this.nbEmprunts++;
	}

	void retirerEmprunt(Livre livre) {
		for (int i = 0; i < this.nbEmprunts; i++) {
			if (this.emprunts[i] == livre) {
				// On décale les livres suivants d'une case vers la gauche pour boucher le trou
				for (int j = i; j < this.nbEmprunts - 1; j++) {
					this.emprunts[j] = this.emprunts[j + 1];
				}
				this.nbEmprunts--;
				this.emprunts[this.nbEmprunts] = null;
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "Adhérent n°" + this.numero + " " + this.nom + " (" + this.nbEmprunts + " emprunt(s))";
	}
}
