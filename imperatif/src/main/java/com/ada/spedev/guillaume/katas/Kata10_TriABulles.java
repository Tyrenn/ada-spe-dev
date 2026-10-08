package com.ada.spedev.guillaume.katas;

/*
 * KATA 10 - Le tri à bulles
 * Notions : boucles imbriquées sur un tableau, échange de deux cases, procédure qui modifie
 *           un tableau, fonction booléenne, optimisation avec un boolean
 *
 * Principe du tri à bulles :
 *   On parcourt le tableau en comparant chaque case avec sa voisine de droite.
 *   Si elles sont dans le mauvais ordre, on les échange.
 *   Après un passage complet, le plus grand élément est "remonté" tout à droite (comme une bulle).
 *   On recommence jusqu'à ce que le tableau soit trié.
 *
 *   {5, 1, 4, 2, 8}
 *   passage 1 : {1, 4, 2, 5, 8}   <- le 8 est à sa place
 *   passage 2 : {1, 2, 4, 5, 8}
 *   passage 3 : aucun échange -> c'est trié !
 *
 * Consigne :
 *   1) estTrie(int[] tab) -> boolean : true si chaque case est <= à la suivante
 *   2) echanger(int[] tab, int i, int j)
 *   3) trierABulles(int[] tab) : trie le tableau dans l'ordre croissant
 *   4) Afficher le tableau après chaque passage pour visualiser les bulles.
 *   5) Optimisation : si lors d'un passage on n'a fait aucun échange, le tableau est trié
 *      -> on peut s'arrêter (utiliser un boolean et un while).
 *      Optimisation 2 : après k passages, les k derniers éléments sont déjà à leur place.
 *
 * Bonus : trierDecroissant(int[] tab). Quelle est la seule chose à changer ?
 *
 * Squelette :
 *
 *   public static void afficherTableau(int[] tab) { ... }
 *   public static boolean estTrie(int[] tab) { ... }
 *   public static void echanger(int[] tab, int i, int j) { ... }
 *   public static void trierABulles(int[] tab) { ... }
 *
 *   public static void main(String[] args) {
 *       int[] tab = {5, 1, 4, 2, 8, 9, 3, 7, 6};
 *       System.out.println("Trié ? " + estTrie(tab));
 *       trierABulles(tab);
 *       afficherTableau(tab);
 *       System.out.println("Trié ? " + estTrie(tab));
 *   }
 */
public class Kata10_TriABulles {

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

	public static boolean estTrie(int[] tab) {
		for (int i = 0; i < tab.length - 1; i++) {
			if (tab[i] > tab[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static void echanger(int[] tab, int i, int j) {
		int temporaire = tab[i];
		tab[i] = tab[j];
		tab[j] = temporaire;
	}

	// Version simple : on fait toujours tab.length - 1 passages
	public static void trierABulles(int[] tab) {
		for (int passage = 0; passage < tab.length - 1; passage++) {
			for (int i = 0; i < tab.length - 1; i++) {
				if (tab[i] > tab[i + 1]) {
					echanger(tab, i, i + 1);
				}
			}
			System.out.print("passage " + (passage + 1) + " : ");
			afficherTableau(tab);
		}
	}

	// Version optimisée : on s'arrête dès qu'un passage ne fait aucun échange,
	// et on ne re-parcourt pas la fin déjà triée
	public static void trierABullesOptimise(int[] tab) {
		boolean aEchange = true;
		int fin = tab.length - 1;
		while (aEchange) {
			aEchange = false;
			for (int i = 0; i < fin; i++) {
				if (tab[i] > tab[i + 1]) {
					echanger(tab, i, i + 1);
					aEchange = true;
				}
			}
			fin--;
			System.out.print("passage : ");
			afficherTableau(tab);
		}
	}

	// Bonus : seul le sens de la comparaison change
	public static void trierDecroissant(int[] tab) {
		boolean aEchange = true;
		while (aEchange) {
			aEchange = false;
			for (int i = 0; i < tab.length - 1; i++) {
				if (tab[i] < tab[i + 1]) {
					echanger(tab, i, i + 1);
					aEchange = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] tab = {5, 1, 4, 2, 8, 9, 3, 7, 6};
		System.out.println("Trié ? " + estTrie(tab));
		trierABulles(tab);
		System.out.println("Trié ? " + estTrie(tab));

		System.out.println("----- Version optimisée -----");
		int[] presqueTrie = {1, 2, 3, 5, 4, 6, 7, 8, 9};
		trierABullesOptimise(presqueTrie);   // seulement 2 passages au lieu de 8

		System.out.println("----- Bonus : décroissant -----");
		int[] autre = {5, 1, 4, 2, 8};
		trierDecroissant(autre);
		afficherTableau(autre);
	}
}
