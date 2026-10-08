package com.ada.spedev.guillaume.katas;

/*
 * KATA 04 - Statistiques sur des notes
 * Notions : tableaux, parcours avec for, .length, fonctions qui prennent un tableau en paramètre,
 *           int vs double pour la moyenne
 *
 * Consigne : à partir du tableau de notes suivant
 *
 *   int[] notes = {12, 8, 15, 19, 7, 10, 14, 11, 16, 9};
 *
 * écrire :
 *   1) une procédure afficherTableau(int[] tab)  qui affiche : [12, 8, 15, 19, 7, 10, 14, 11, 16, 9]
 *      (attention à la virgule après le dernier élément !)
 *   2) une fonction somme(int[] tab)      -> int
 *   3) une fonction moyenne(int[] tab)    -> double   (attention à la division entière !)
 *   4) une fonction min(int[] tab)        -> int
 *   5) une fonction max(int[] tab)        -> int
 *   6) une fonction compterAuDessus(int[] tab, int seuil) -> int : nombre de notes >= seuil
 *
 * Afficher ensuite : la somme, la moyenne, la note min, la note max,
 * et le nombre d'élèves qui ont la moyenne (>= 10).
 *
 * Bonus : écrire une fonction arrondir(double valeur) qui arrondit à 2 décimales
 *         (indice : Math.round(valeur * 100) / 100.0)
 *
 * Squelette :
 *
 *   public static void afficherTableau(int[] tab) { ... }
 *   public static int somme(int[] tab) { ... }
 *   public static double moyenne(int[] tab) { ... }
 *   public static int min(int[] tab) { ... }
 *   public static int max(int[] tab) { ... }
 *   public static int compterAuDessus(int[] tab, int seuil) { ... }
 *
 *   public static void main(String[] args) {
 *       int[] notes = {12, 8, 15, 19, 7, 10, 14, 11, 16, 9};
 *       afficherTableau(notes);
 *       // ...
 *   }
 */
public class Kata04_StatsNotes {

	public static void afficherTableau(int[] tab) {
		System.out.print("[");
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
			if (i < tab.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	public static int somme(int[] tab) {
		int total = 0;
		for (int i = 0; i < tab.length; i++) {
			total = total + tab[i];
		}
		return total;
	}

	public static double moyenne(int[] tab) {
		// (double) obligatoire, sinon division entière : 121 / 10 = 12 au lieu de 12.1
		return (double) somme(tab) / tab.length;
	}

	public static int min(int[] tab) {
		int plusPetit = tab[0];   // on part du premier élément, pas de 0 !
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] < plusPetit) {
				plusPetit = tab[i];
			}
		}
		return plusPetit;
	}

	public static int max(int[] tab) {
		int plusGrand = tab[0];
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] > plusGrand) {
				plusGrand = tab[i];
			}
		}
		return plusGrand;
	}

	public static int compterAuDessus(int[] tab, int seuil) {
		int compteur = 0;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] >= seuil) {
				compteur++;
			}
		}
		return compteur;
	}

	public static double arrondir(double valeur) {
		return Math.round(valeur * 100) / 100.0;
	}

	public static void main(String[] args) {
		int[] notes = {12, 8, 15, 19, 7, 10, 14, 11, 16, 9};

		afficherTableau(notes);
		System.out.println("Somme   : " + somme(notes));
		System.out.println("Moyenne : " + moyenne(notes));
		System.out.println("Min     : " + min(notes));
		System.out.println("Max     : " + max(notes));
		System.out.println("Élèves ayant la moyenne : " + compterAuDessus(notes, 10) + " / " + notes.length);

		// Bonus
		int[] autresNotes = {13, 14, 14};
		System.out.println("Moyenne brute   : " + moyenne(autresNotes));            // 13.666666666666666
		System.out.println("Moyenne arrondie: " + arrondir(moyenne(autresNotes)));  // 13.67
	}
}
