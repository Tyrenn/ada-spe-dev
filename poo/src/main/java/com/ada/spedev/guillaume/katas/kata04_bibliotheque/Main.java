package com.ada.spedev.guillaume.katas.kata04_bibliotheque;

/*
 * KATA 04 - La bibliothèque
 * Notions : composition (des objets qui contiennent d'autres objets), tableaux, boucle for,
 *           attribut et méthode static, visibilité package, null, une classe = une responsabilité
 *
 * Rappel tableaux : taille FIXE, choisie à la création
 *     Livre[] livres = new Livre[100];   // 100 cases, toutes à null au départ
 *     int nbLivres = 0;                  // combien de cases sont réellement remplies
 *     livres[nbLivres] = livre; nbLivres++;          // "ajouter"
 *     for (int i = 0; i < nbLivres; i++) { ... livres[i] ... }
 *     Pour "retirer" un élément : décaler les suivants d'une case vers la gauche.
 *
 * Consigne : modéliser une petite bibliothèque avec TROIS classes, chacune dans son fichier.
 *   1) Livre : isbn, titre, auteur, et l'adhérent qui l'a emprunté (null si disponible).
 *      Méthode  estDisponible().
 *   2) Adherent : nom, numéro, et le tableau des livres qu'il a empruntés. Max 3 emprunts.
 *   3) Bibliotheque : le tableau des livres (capacité max 100), et les méthodes
 *        ajouterLivre(Livre) -> boolean (false si la bibliothèque est pleine)
 *        chercherParIsbn(String) -> Livre (ou null)
 *        chercherParAuteur(String) -> Livre[]
 *        livresDisponibles() -> Livre[]
 *        emprunter(String isbn, Adherent) -> boolean
 *        rendre(String isbn) -> boolean
 *
 * Étape 2 : le numéro d'adhérent doit être attribué automatiquement (1, 2, 3...).
 *           Astuce : un attribut  private static int prochainNumero  partagé par TOUS les adhérents.
 *           Quelle différence entre un attribut static et un attribut normal ?
 *
 * Étape 3 : que se passe-t-il si  getEmprunts()  retourne directement le tableau interne
 *           et que l'appelant fait  adherent.getEmprunts()[0] = null  ?
 *
 * Bonus : une méthode  afficherRetardataires()  (ajouter une date d'emprunt avec java.time.LocalDate)
 *
 * Question de conception : qui doit vérifier qu'un adhérent ne dépasse pas 3 emprunts ?
 *                          Le Livre ? L'Adherent ? La Bibliotheque ?
 */
public class Main {

	public static void main(String[] args) {
		Bibliotheque biblio = new Bibliotheque();
		biblio.ajouterLivre(new Livre("111", "Les Misérables", "Victor Hugo"));
		biblio.ajouterLivre(new Livre("222", "Notre-Dame de Paris", "Victor Hugo"));
		biblio.ajouterLivre(new Livre("333", "Le Petit Prince", "Antoine de Saint-Exupéry"));
		biblio.ajouterLivre(new Livre("444", "L'Étranger", "Albert Camus"));
		biblio.ajouterLivre(new Livre("555", "La Peste", "Albert Camus"));

		Adherent ada = new Adherent("Ada");
		Adherent alan = new Adherent("Alan");
		System.out.println(ada);    // n°1
		System.out.println(alan);   // n°2

		System.out.println("Ada emprunte 111 : " + biblio.emprunter("111", ada));     // true
		System.out.println("Alan emprunte 111 : " + biblio.emprunter("111", alan));   // false, déjà emprunté
		System.out.println("Ada emprunte 222 : " + biblio.emprunter("222", ada));
		System.out.println("Ada emprunte 333 : " + biblio.emprunter("333", ada));
		System.out.println("Ada emprunte 444 : " + biblio.emprunter("444", ada));     // false, max 3
		System.out.println("Alan emprunte 999 : " + biblio.emprunter("999", alan));   // false, n'existe pas

		System.out.println("Catalogue :");
		biblio.afficherCatalogue();

		System.out.println("Livres de Victor Hugo : " + biblio.chercherParAuteur("victor hugo").length);

		System.out.println("Ada rend 111 : " + biblio.rendre("111"));
		System.out.println("Alan emprunte 111 : " + biblio.emprunter("111", alan));

		System.out.println("Disponibles :");
		for (Livre livre : biblio.livresDisponibles()) {
			System.out.println("  " + livre);
		}

		System.out.println("----- Étape 3 : copie défensive -----");
		Livre[] copie = ada.getEmprunts();
		copie[0] = null;
		System.out.println(ada + " -> le tableau interne n'a pas été modifié : " + ada.getEmprunts()[0]);
	}
}
