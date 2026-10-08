package com.ada.spedev.guillaume.katas;

/*
 * KATA 08 - Dessiner avec des boucles imbriquées
 * Notions : boucles for imbriquées, System.out.print vs System.out.println,
 *           procédures avec plusieurs paramètres (dont un char)
 *
 * Rappel : System.out.print(...)   affiche SANS retour à la ligne
 *          System.out.println()    fait juste un retour à la ligne
 *
 * Consigne : écrire les procédures suivantes (exemples avec taille = 4, symbole = '*')
 *
 *   1) rectangle(int largeur, int hauteur, char symbole)     rectangle(6, 3, '#')
 *                                                             ######
 *                                                             ######
 *                                                             ######
 *
 *   2) triangle(int taille, char symbole)                     *
 *                                                             **
 *                                                             ***
 *                                                             ****
 *
 *   3) triangleInverse(int taille, char symbole)              ****
 *                                                             ***
 *                                                             **
 *                                                             *
 *
 *   4) sapin(int taille)                                          *
 *      (indice : sur la ligne i, combien d'espaces ?             ***
 *       combien d'étoiles ?)                                    *****
 *                                                              *******
 *                                                                 |
 *
 *   5) damier(int taille)                                     #.#.
 *      (indice : (ligne + colonne) % 2)                       .#.#
 *                                                             #.#.
 *                                                             .#.#
 *
 *   6) cadre(int largeur, int hauteur)                        +----+
 *                                                             |    |
 *                                                             |    |
 *                                                             +----+
 *
 * Squelette :
 *
 *   public static void rectangle(int largeur, int hauteur, char symbole) {
 *       for (int ligne = 0; ligne < hauteur; ligne++) {
 *           for (int colonne = 0; colonne < largeur; colonne++) {
 *               // TODO
 *           }
 *           // TODO
 *       }
 *   }
 */
public class Kata08_Motifs {

	// Procédure utilitaire : affiche n fois le même caractère (sans retour à la ligne)
	public static void repeter(char symbole, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(symbole);
		}
	}

	public static void rectangle(int largeur, int hauteur, char symbole) {
		for (int ligne = 0; ligne < hauteur; ligne++) {
			for (int colonne = 0; colonne < largeur; colonne++) {
				System.out.print(symbole);
			}
			System.out.println();
		}
	}

	public static void triangle(int taille, char symbole) {
		for (int ligne = 1; ligne <= taille; ligne++) {
			for (int colonne = 0; colonne < ligne; colonne++) {
				System.out.print(symbole);
			}
			System.out.println();
		}
	}

	public static void triangleInverse(int taille, char symbole) {
		for (int ligne = taille; ligne >= 1; ligne--) {
			repeter(symbole, ligne);
			System.out.println();
		}
	}

	public static void sapin(int taille) {
		for (int ligne = 0; ligne < taille; ligne++) {
			repeter(' ', taille - 1 - ligne);
			repeter('*', 2 * ligne + 1);
			System.out.println();
		}
		repeter(' ', taille - 1);
		System.out.println('|');
	}

	public static void damier(int taille) {
		for (int ligne = 0; ligne < taille; ligne++) {
			for (int colonne = 0; colonne < taille; colonne++) {
				if ((ligne + colonne) % 2 == 0) {
					System.out.print('#');
				} else {
					System.out.print('.');
				}
			}
			System.out.println();
		}
	}

	public static void cadre(int largeur, int hauteur) {
		for (int ligne = 0; ligne < hauteur; ligne++) {
			boolean bordHautOuBas = ligne == 0 || ligne == hauteur - 1;
			for (int colonne = 0; colonne < largeur; colonne++) {
				boolean bordGaucheOuDroit = colonne == 0 || colonne == largeur - 1;
				if (bordHautOuBas && bordGaucheOuDroit) {
					System.out.print('+');
				} else if (bordHautOuBas) {
					System.out.print('-');
				} else if (bordGaucheOuDroit) {
					System.out.print('|');
				} else {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		rectangle(6, 3, '#');
		System.out.println();
		triangle(4, '*');
		System.out.println();
		triangleInverse(4, '*');
		System.out.println();
		sapin(5);
		System.out.println();
		damier(6);
		System.out.println();
		cadre(10, 5);
	}
}
