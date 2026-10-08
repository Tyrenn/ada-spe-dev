package com.ada.spedev.guillaume.katas;

/*
 * KATA 05 - Manipuler des tableaux
 * Notions : créer un tableau avec new, échanger deux cases, fonction qui RETOURNE un tableau,
 *           procédure qui MODIFIE un tableau (passage par référence !)
 *
 * Consigne :
 *   1) Écrire une fonction  inverser(int[] tab)  qui retourne un NOUVEAU tableau inversé.
 *      {1, 2, 3, 4, 5} -> {5, 4, 3, 2, 1}   (le tableau d'origine ne doit pas changer)
 *
 *   2) Écrire une procédure  echanger(int[] tab, int i, int j)  qui échange les cases i et j.
 *
 *   3) Écrire une procédure  inverserSurPlace(int[] tab)  qui inverse le tableau SANS en créer
 *      un nouveau (utiliser echanger). Combien de tours de boucle faut-il ?
 *
 *   4) Écrire une procédure  decalerAGauche(int[] tab)  : {1, 2, 3, 4, 5} -> {2, 3, 4, 5, 1}
 *
 *   5) Écrire une fonction  concatener(int[] a, int[] b)  : {1, 2} + {3, 4, 5} -> {1, 2, 3, 4, 5}
 *
 * Question à se poser : pourquoi une procédure (void) peut-elle modifier le tableau qu'on lui
 * passe, alors que si on lui passe un int, l'int de départ ne change pas ?
 * -> Testez avec la procédure  essayerDeModifier(int x)  ci-dessous.
 *
 * Squelette :
 *
 *   public static void afficherTableau(int[] tab) { ... }   // reprendre celle du kata 04
 *   public static int[] inverser(int[] tab) { ... }
 *   public static void echanger(int[] tab, int i, int j) { ... }
 *   public static void inverserSurPlace(int[] tab) { ... }
 *   public static void decalerAGauche(int[] tab) { ... }
 *   public static int[] concatener(int[] a, int[] b) { ... }
 *
 *   public static void essayerDeModifier(int x) {
 *       x = 999;
 *   }
 */
public class Kata05_ManipulationTableaux {

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

	public static int[] inverser(int[] tab) {
		int[] resultat = new int[tab.length];
		for (int i = 0; i < tab.length; i++) {
			resultat[i] = tab[tab.length - 1 - i];
		}
		return resultat;
	}

	public static void echanger(int[] tab, int i, int j) {
		int temporaire = tab[i];   // sans variable temporaire, on perd une des valeurs
		tab[i] = tab[j];
		tab[j] = temporaire;
	}

	public static void inverserSurPlace(int[] tab) {
		// on s'arrête à la moitié, sinon on ré-inverse tout !
		for (int i = 0; i < tab.length / 2; i++) {
			echanger(tab, i, tab.length - 1 - i);
		}
	}

	public static void decalerAGauche(int[] tab) {
		int premier = tab[0];
		for (int i = 0; i < tab.length - 1; i++) {
			tab[i] = tab[i + 1];
		}
		tab[tab.length - 1] = premier;
	}

	public static int[] concatener(int[] a, int[] b) {
		int[] resultat = new int[a.length + b.length];
		for (int i = 0; i < a.length; i++) {
			resultat[i] = a[i];
		}
		for (int i = 0; i < b.length; i++) {
			resultat[a.length + i] = b[i];
		}
		return resultat;
	}

	public static void essayerDeModifier(int x) {
		x = 999;
	}

	public static void main(String[] args) {
		int[] nombres = {1, 2, 3, 4, 5};

		System.out.print("Original         : ");
		afficherTableau(nombres);

		int[] inverse = inverser(nombres);
		System.out.print("inverser         : ");
		afficherTableau(inverse);
		System.out.print("Original intact  : ");
		afficherTableau(nombres);

		inverserSurPlace(nombres);
		System.out.print("inverserSurPlace : ");
		afficherTableau(nombres);   // le tableau d'origine a été modifié !

		int[] autre = {1, 2, 3, 4, 5};
		decalerAGauche(autre);
		System.out.print("decalerAGauche   : ");
		afficherTableau(autre);

		int[] debut = {1, 2};
		int[] fin = {3, 4, 5};
		System.out.print("concatener       : ");
		afficherTableau(concatener(debut, fin));

		// Le piège : un int est copié, un tableau non (on passe "l'adresse" du tableau)
		int valeur = 42;
		essayerDeModifier(valeur);
		System.out.println("valeur après essayerDeModifier : " + valeur);   // toujours 42

		// Autre piège : = sur un tableau ne copie pas, les deux variables pointent sur le même tableau
		int[] a = {1, 2, 3};
		int[] b = a;
		b[0] = 100;
		System.out.print("a après b[0] = 100 : ");
		afficherTableau(a);   // [100, 2, 3]
	}
}
