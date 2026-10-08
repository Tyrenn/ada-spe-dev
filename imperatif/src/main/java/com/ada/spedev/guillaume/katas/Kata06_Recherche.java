package com.ada.spedev.guillaume.katas;

/*
 * KATA 06 - Rechercher dans un tableau
 * Notions : parcours de tableau, return au milieu d'une boucle, while avec double condition,
 *           valeur "sentinelle" (-1 = pas trouvé)
 *
 * Consigne : avec le tableau
 *
 *   int[] valeurs = {4, 8, 15, 16, 23, 42, 8, 15, 8};
 *
 *   1) contient(int[] tab, int valeur)          -> boolean : true si la valeur est présente
 *   2) indexDe(int[] tab, int valeur)           -> int : position de la PREMIÈRE occurrence, -1 sinon
 *   3) dernierIndexDe(int[] tab, int valeur)    -> int : position de la DERNIÈRE occurrence, -1 sinon
 *                                                  (astuce : parcourir le tableau à l'envers)
 *   4) compterOccurrences(int[] tab, int valeur)-> int : combien de fois la valeur apparaît
 *   5) indexDuMax(int[] tab)                    -> int : position de la plus grande valeur
 *
 *   6) Réécrire indexDe avec une boucle while (sans return dans la boucle) :
 *      while (i < tab.length && tab[i] != valeur) { ... }
 *      Pourquoi l'ordre des deux conditions est-il important ?
 *
 * Bonus : rechercheDichotomique(int[] tabTrie, int valeur) -> int
 *   Sur un tableau TRIÉ, on regarde le milieu : si la valeur est plus petite on cherche à gauche,
 *   sinon à droite. Combien d'étapes au maximum pour un tableau de 1000 éléments ?
 *
 * Squelette :
 *
 *   public static boolean contient(int[] tab, int valeur) { ... }
 *   public static int indexDe(int[] tab, int valeur) { ... }
 *   public static int dernierIndexDe(int[] tab, int valeur) { ... }
 *   public static int compterOccurrences(int[] tab, int valeur) { ... }
 *   public static int indexDuMax(int[] tab) { ... }
 *   public static int indexDeAvecWhile(int[] tab, int valeur) { ... }
 */
public class Kata06_Recherche {

	public static boolean contient(int[] tab, int valeur) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == valeur) {
				return true;   // on sort dès qu'on a trouvé
			}
		}
		return false;   // on n'arrive ici que si on n'a rien trouvé
	}

	public static int indexDe(int[] tab, int valeur) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == valeur) {
				return i;
			}
		}
		return -1;
	}

	public static int dernierIndexDe(int[] tab, int valeur) {
		for (int i = tab.length - 1; i >= 0; i--) {
			if (tab[i] == valeur) {
				return i;
			}
		}
		return -1;
	}

	public static int compterOccurrences(int[] tab, int valeur) {
		int compteur = 0;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == valeur) {
				compteur++;
			}
		}
		return compteur;
	}

	public static int indexDuMax(int[] tab) {
		int indexMax = 0;
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] > tab[indexMax]) {
				indexMax = i;
			}
		}
		return indexMax;
	}

	public static int indexDeAvecWhile(int[] tab, int valeur) {
		int i = 0;
		// i < tab.length en PREMIER : sinon tab[i] plante (ArrayIndexOutOfBoundsException)
		// quand i == tab.length. Le && s'arrête dès que la première condition est fausse.
		while (i < tab.length && tab[i] != valeur) {
			i++;
		}
		if (i == tab.length) {
			return -1;
		}
		return i;
	}

	public static int rechercheDichotomique(int[] tabTrie, int valeur) {
		int debut = 0;
		int fin = tabTrie.length - 1;
		while (debut <= fin) {
			int milieu = (debut + fin) / 2;
			if (tabTrie[milieu] == valeur) {
				return milieu;
			} else if (valeur < tabTrie[milieu]) {
				fin = milieu - 1;
			} else {
				debut = milieu + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] valeurs = {4, 8, 15, 16, 23, 42, 8, 15, 8};

		System.out.println("contient 23 ?         " + contient(valeurs, 23));
		System.out.println("contient 7 ?          " + contient(valeurs, 7));
		System.out.println("indexDe 8             " + indexDe(valeurs, 8));
		System.out.println("indexDe 7             " + indexDe(valeurs, 7));
		System.out.println("dernierIndexDe 8      " + dernierIndexDe(valeurs, 8));
		System.out.println("compterOccurrences 8  " + compterOccurrences(valeurs, 8));
		System.out.println("indexDuMax            " + indexDuMax(valeurs));
		System.out.println("indexDeAvecWhile 16   " + indexDeAvecWhile(valeurs, 16));
		System.out.println("indexDeAvecWhile 7    " + indexDeAvecWhile(valeurs, 7));

		// Bonus
		int[] trie = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
		System.out.println("dichotomie 23         " + rechercheDichotomique(trie, 23));
		System.out.println("dichotomie 24         " + rechercheDichotomique(trie, 24));
		// Réponse : 1000 -> 500 -> 250 -> ... -> 1 : environ 10 étapes (2^10 = 1024)
	}
}
