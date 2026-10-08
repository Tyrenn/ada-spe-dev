package com.ada.spedev.guillaume.katas;

/*
 * KATA 03 - Jouer avec les chiffres d'un nombre
 * Notions : boucle while, division entière et modulo, fonctions qui retournent un int / boolean,
 *           une fonction qui en appelle une autre
 *
 * Astuce : pour un nombre n,
 *   - n % 10  donne le dernier chiffre   (1234 % 10 = 4)
 *   - n / 10  enlève le dernier chiffre  (1234 / 10 = 123)
 *
 * Consigne : écrire les fonctions suivantes (on suppose n >= 0)
 *   1) nombreDeChiffres(n)  : 1234 -> 4     (attention au cas 0 -> 1)
 *   2) sommeDesChiffres(n)  : 1234 -> 10
 *   3) inverser(n)          : 1234 -> 4321
 *   4) estPalindrome(n)     : 12321 -> true, 1234 -> false   (utiliser inverser !)
 *   5) racineNumerique(n)   : on fait la somme des chiffres jusqu'à n'avoir plus qu'un seul chiffre
 *                             9875 -> 29 -> 11 -> 2
 *
 * Pourquoi un while et pas un for ici ? On ne sait pas à l'avance combien de tours il faut faire.
 *
 * Squelette :
 *
 *   public static int nombreDeChiffres(int n) { ... }
 *   public static int sommeDesChiffres(int n) { ... }
 *   public static int inverser(int n) { ... }
 *   public static boolean estPalindrome(int n) { ... }
 *   public static int racineNumerique(int n) { ... }
 */
public class Kata03_Chiffres {

	public static int nombreDeChiffres(int n) {
		if (n == 0) {
			return 1;
		}
		int compteur = 0;
		while (n > 0) {
			n = n / 10;
			compteur++;
		}
		return compteur;
	}

	public static int sommeDesChiffres(int n) {
		int somme = 0;
		while (n > 0) {
			somme = somme + n % 10;
			n = n / 10;
		}
		return somme;
	}

	public static int inverser(int n) {
		int resultat = 0;
		while (n > 0) {
			resultat = resultat * 10 + n % 10;
			n = n / 10;
		}
		return resultat;
	}

	public static boolean estPalindrome(int n) {
		return n == inverser(n);
	}

	public static int racineNumerique(int n) {
		while (n >= 10) {
			n = sommeDesChiffres(n);
		}
		return n;
	}

	public static void main(String[] args) {
		System.out.println("nombreDeChiffres(1234) = " + nombreDeChiffres(1234));
		System.out.println("nombreDeChiffres(0)    = " + nombreDeChiffres(0));
		System.out.println("sommeDesChiffres(1234) = " + sommeDesChiffres(1234));
		System.out.println("inverser(1234)         = " + inverser(1234));
		System.out.println("estPalindrome(12321)   = " + estPalindrome(12321));
		System.out.println("estPalindrome(1234)    = " + estPalindrome(1234));
		System.out.println("racineNumerique(9875)  = " + racineNumerique(9875));

		// Pour aller plus loin : afficher tous les palindromes entre 100 et 200
		System.out.println("Palindromes entre 100 et 200 :");
		for (int i = 100; i <= 200; i++) {
			if (estPalindrome(i)) {
				System.out.println(i);
			}
		}
	}
}
