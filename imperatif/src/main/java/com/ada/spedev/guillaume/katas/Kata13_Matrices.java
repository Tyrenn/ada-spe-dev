package com.ada.spedev.guillaume.katas;

/*
 * KATA 13 (BONUS) - Les tableaux à deux dimensions
 * Notions : int[][], tab.length (lignes) vs tab[0].length (colonnes), boucles imbriquées,
 *           fonction qui retourne un tableau 2D
 *
 * Un tableau 2D est un tableau de tableaux :
 *   int[][] grille = new int[3][4];      // 3 lignes, 4 colonnes, rempli de 0
 *   grille[1][2] = 7;                    // ligne 1, colonne 2
 *   int[][] m = {{1, 2, 3},
 *                {4, 5, 6}};
 *
 * Consigne :
 *   1) afficherMatrice(int[][] m) : affiche chaque ligne sur une ligne, valeurs séparées par
 *      une tabulation ("\t")
 *   2) tableDeMultiplication(int taille) -> int[][] : table[i][j] = (i + 1) * (j + 1)
 *      Afficher la table de 1 à 10.
 *   3) sommeMatrice(int[][] m) -> int
 *   4) sommeDiagonale(int[][] m) -> int : pour une matrice carrée, m[0][0] + m[1][1] + ...
 *   5) transposer(int[][] m) -> int[][] : les lignes deviennent les colonnes
 *        {{1, 2, 3},        {{1, 4},
 *         {4, 5, 6}}   ->    {2, 5},
 *                            {3, 6}}
 *
 * Bonus : estCarreMagique(int[][] m) -> boolean
 *   Un carré magique : la somme de chaque ligne, de chaque colonne et des deux diagonales
 *   est la même.   {{2, 7, 6},
 *                   {9, 5, 1},
 *                   {4, 3, 8}}   -> true (somme = 15)
 *
 * Squelette :
 *
 *   public static void afficherMatrice(int[][] m) { ... }
 *   public static int[][] tableDeMultiplication(int taille) { ... }
 *   public static int sommeMatrice(int[][] m) { ... }
 *   public static int sommeDiagonale(int[][] m) { ... }
 *   public static int[][] transposer(int[][] m) { ... }
 *   public static boolean estCarreMagique(int[][] m) { ... }
 */
public class Kata13_Matrices {

	public static void afficherMatrice(int[][] m) {
		for (int ligne = 0; ligne < m.length; ligne++) {
			for (int colonne = 0; colonne < m[ligne].length; colonne++) {
				System.out.print(m[ligne][colonne] + "\t");
			}
			System.out.println();
		}
	}

	public static int[][] tableDeMultiplication(int taille) {
		int[][] table = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				table[i][j] = (i + 1) * (j + 1);
			}
		}
		return table;
	}

	public static int sommeMatrice(int[][] m) {
		int somme = 0;
		for (int ligne = 0; ligne < m.length; ligne++) {
			for (int colonne = 0; colonne < m[ligne].length; colonne++) {
				somme = somme + m[ligne][colonne];
			}
		}
		return somme;
	}

	public static int sommeDiagonale(int[][] m) {
		int somme = 0;
		for (int i = 0; i < m.length; i++) {
			somme = somme + m[i][i];
		}
		return somme;
	}

	public static int[][] transposer(int[][] m) {
		int nbLignes = m.length;
		int nbColonnes = m[0].length;
		int[][] resultat = new int[nbColonnes][nbLignes];
		for (int ligne = 0; ligne < nbLignes; ligne++) {
			for (int colonne = 0; colonne < nbColonnes; colonne++) {
				resultat[colonne][ligne] = m[ligne][colonne];
			}
		}
		return resultat;
	}

	public static boolean estCarreMagique(int[][] m) {
		int n = m.length;
		int reference = sommeDiagonale(m);

		int antiDiagonale = 0;
		for (int i = 0; i < n; i++) {
			antiDiagonale = antiDiagonale + m[i][n - 1 - i];
		}
		if (antiDiagonale != reference) {
			return false;
		}

		for (int i = 0; i < n; i++) {
			int sommeLigne = 0;
			int sommeColonne = 0;
			for (int j = 0; j < n; j++) {
				sommeLigne = sommeLigne + m[i][j];
				sommeColonne = sommeColonne + m[j][i];
			}
			if (sommeLigne != reference || sommeColonne != reference) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Table de multiplication :");
		int[][] table = tableDeMultiplication(10);
		afficherMatrice(table);
		System.out.println("Somme de la table    : " + sommeMatrice(table));     // 3025
		System.out.println("Somme de la diagonale: " + sommeDiagonale(table));   // 385 (les carrés)

		int[][] m = {{1, 2, 3},
		             {4, 5, 6}};
		System.out.println("Matrice :");
		afficherMatrice(m);
		System.out.println("Transposée :");
		afficherMatrice(transposer(m));

		// Bonus
		int[][] magique = {{2, 7, 6},
		                   {9, 5, 1},
		                   {4, 3, 8}};
		int[][] pasMagique = {{1, 2, 3},
		                      {4, 5, 6},
		                      {7, 8, 9}};
		System.out.println("Carré magique ? " + estCarreMagique(magique));     // true
		System.out.println("Carré magique ? " + estCarreMagique(pasMagique));  // false
	}
}
