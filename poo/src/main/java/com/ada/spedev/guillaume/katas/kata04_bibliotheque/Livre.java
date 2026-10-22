package com.ada.spedev.guillaume.katas.kata04_bibliotheque;

public class Livre {

	private final String isbn;
	private final String titre;
	private final String auteur;
	private Adherent emprunteur;   // null si le livre est disponible

	public Livre(String isbn, String titre, String auteur) {
		this.isbn = isbn;
		this.titre = titre;
		this.auteur = auteur;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getTitre() {
		return this.titre;
	}

	public String getAuteur() {
		return this.auteur;
	}

	public Adherent getEmprunteur() {
		return this.emprunteur;
	}

	public boolean estDisponible() {
		return this.emprunteur == null;
	}

	// Visibilité "package" (pas de mot-clé) : seules les classes du même package peuvent appeler ces méthodes.
	// C'est la Bibliotheque qui orchestre les emprunts, pas le code extérieur.
	void marquerEmprunte(Adherent adherent) {
		this.emprunteur = adherent;
	}

	void marquerRendu() {
		this.emprunteur = null;
	}

	@Override
	public String toString() {
		String etat = estDisponible() ? "disponible" : "emprunté par " + this.emprunteur.getNom();
		return "\"" + this.titre + "\" de " + this.auteur + " [" + etat + "]";
	}
}
