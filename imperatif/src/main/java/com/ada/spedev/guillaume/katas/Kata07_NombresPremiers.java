package com.ada.spedev.guillaume.katas;

/*
 * KATA 07 - Les nombres premiers
 * Notions : fonction booléenne, boucle for avec sortie anticipée, remplir un tableau avec un while,
 *           tableau de boolean
 *
 * Rappel : un nombre premier est un entier >= 2 divisible uniquement par 1 et par lui-même.
 *          2, 3, 5, 7, 11, 13, ...
 *
 * Consigne :
 *   1) Écrire la fonction  estPremier(int n) -> boolean
 *   2) Écrire la procédure  afficherPremiersJusqua(int limite)  qui affiche tous les nombres
 *      premiers <= limite sur une seule ligne, séparés par des espaces.
 *   3) Écrire la fonction  premiersPremiers(int quantite) -> int[]  qui retourne un tableau
 *      contenant les "quantite" premiers nombres premiers.
 *      premiersPremiers(5) -> {2, 3, 5, 7, 11}
 *      Ici on connaît la taille du tableau mais pas jusqu'où chercher : while !
 *
 *   Optimisation : a-t-on besoin de tester tous les diviseurs jusqu'à n ?
 *   (indice : si n = a * b, alors a ou b est <= racine de n -> i * i <= n)
 *
 * Bonus : le crible d'Ératosthène
 *   crible(int limite) -> boolean[] : estPremier[i] vaut true si i est premier.
 *   - on part d'un tableau de boolean à true (sauf 0 et 1)
 *   - pour chaque nombre i encore à true, on met à false tous ses multiples (2i, 3i, 4i...)
 *
 * Squelette :
 *
 *   public static boolean estPremier(int n) { ... }
 *   public static void afficherPremiersJusqua(int limite) { ... }
 *   public static int[] premiersPremiers(int quantite) { ... }
 *   public static boolean[] crible(int limite) { ... }
 */
public class Kata07_NombresPremiers {

	public static boolean estPremier(int n) {
		if (n < 2) {
			return false;
		}
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void afficherPremiersJusqua(int limite) {
		for (int i = 2; i <= limite; i++) {
			if (estPremier(i)) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	public static int[] premiersPremiers(int quantite) {
		int[] resultat = new int[quantite];
		int trouves = 0;
		int candidat = 2;
		while (trouves < quantite) {
			if (estPremier(candidat)) {
				resultat[trouves] = candidat;
				trouves++;
			}
			candidat++;
		}
		return resultat;
	}

	public static boolean[] crible(int limite) {
		boolean[] premier = new boolean[limite + 1];   // un boolean[] est rempli de false par défaut
		for (int i = 2; i <= limite; i++) {
			premier[i] = true;
		}
		for (int i = 2; i * i <= limite; i++) {
			if (premier[i]) {
				for (int multiple = i * 2; multiple <= limite; multiple = multiple + i) {
					premier[multiple] = false;
				}
			}
		}
		return premier;
	}

	public static void main(String[] args) {
		System.out.println("estPremier(17) = " + estPremier(17));
		System.out.println("estPremier(21) = " + estPremier(21));
		System.out.println("estPremier(1)  = " + estPremier(1));

		System.out.print("Premiers <= 100 : ");
		afficherPremiersJusqua(100);

		int[] dixPremiers = premiersPremiers(10);
		System.out.print("Les 10 premiers : ");
		for (int i = 0; i < dixPremiers.length; i++) {
			System.out.print(dixPremiers[i] + " ");
		}
		System.out.println();

		// Bonus
		boolean[] resultatCrible = crible(100);
		System.out.print("Crible <= 100   : ");
		for (int i = 0; i < resultatCrible.length; i++) {
			if (resultatCrible[i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
}
