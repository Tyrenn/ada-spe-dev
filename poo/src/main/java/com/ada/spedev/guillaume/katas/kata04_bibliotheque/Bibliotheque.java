package com.ada.spedev.guillaume.katas.kata04_bibliotheque;

// Composition : une Bibliotheque "a des" livres. Elle est responsable de la cohérence
// entre les livres et les adhérents (un livre emprunté apparaît dans le tableau de l'adhérent, et inversement).
public class Bibliotheque {

	public static final int CAPACITE_MAX = 100;

	// Tableau de taille fixe + compteur : seules les cases 0 à nbLivres - 1 sont remplies
	private final Livre[] livres = new Livre[CAPACITE_MAX];
	private int nbLivres = 0;

	public boolean ajouterLivre(Livre livre) {
		if (this.nbLivres >= CAPACITE_MAX) {
			return false;
		}
		this.livres[this.nbLivres] = livre;
		this.nbLivres++;
		return true;
	}

	public Livre chercherParIsbn(String isbn) {
		for (int i = 0; i < this.nbLivres; i++) {
			if (this.livres[i].getIsbn().equals(isbn)) {
				return this.livres[i];
			}
		}
		return null;
	}

	// Un tableau ne grandit pas : on compte d'abord les résultats, puis on crée un tableau à la bonne taille
	public Livre[] chercherParAuteur(String auteur) {
		int nb = 0;
		for (int i = 0; i < this.nbLivres; i++) {
			if (this.livres[i].getAuteur().equalsIgnoreCase(auteur)) {
				nb++;
			}
		}
		Livre[] resultat = new Livre[nb];
		int index = 0;
		for (int i = 0; i < this.nbLivres; i++) {
			if (this.livres[i].getAuteur().equalsIgnoreCase(auteur)) {
				resultat[index] = this.livres[i];
				index++;
			}
		}
		return resultat;
	}

	public Livre[] livresDisponibles() {
		int nb = 0;
		for (int i = 0; i < this.nbLivres; i++) {
			if (this.livres[i].estDisponible()) {
				nb++;
			}
		}
		Livre[] resultat = new Livre[nb];
		int index = 0;
		for (int i = 0; i < this.nbLivres; i++) {
			if (this.livres[i].estDisponible()) {
				resultat[index] = this.livres[i];
				index++;
			}
		}
		return resultat;
	}

	public boolean emprunter(String isbn, Adherent adherent) {
		Livre livre = chercherParIsbn(isbn);
		if (livre == null || !livre.estDisponible() || !adherent.peutEmprunter()) {
			return false;
		}
		livre.marquerEmprunte(adherent);
		adherent.ajouterEmprunt(livre);
		return true;
	}

	public boolean rendre(String isbn) {
		Livre livre = chercherParIsbn(isbn);
		if (livre == null || livre.estDisponible()) {
			return false;
		}
		livre.getEmprunteur().retirerEmprunt(livre);
		livre.marquerRendu();
		return true;
	}

	public void afficherCatalogue() {
		for (int i = 0; i < this.nbLivres; i++) {
			System.out.println("  " + this.livres[i]);
		}
	}
}
