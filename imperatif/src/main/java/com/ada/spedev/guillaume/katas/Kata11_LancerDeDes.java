package com.ada.spedev.guillaume.katas;

/*
 * KATA 11 - Simulation de lancers de dés
 * Notions : Math.random(), cast double -> int, tableau de compteurs (l'index = la valeur),
 *           pourcentages en double, histogramme avec des boucles imbriquées
 *
 * Rappel : Math.random() retourne un double aléatoire dans [0.0 ; 1.0[  (1.0 exclu)
 *          Math.random() * 6        -> [0.0 ; 6.0[
 *          (int) (Math.random() * 6) -> 0, 1, 2, 3, 4 ou 5
 *
 * Consigne :
 *   1) lancerDe() -> int : retourne un nombre entre 1 et 6
 *   2) Lancer le dé 6000 fois et compter combien de fois chaque face sort.
 *      Astuce : un tableau int[] compteurs = new int[7]; et compteurs[face]++
 *      (on n'utilise pas la case 0, c'est plus lisible)
 *   3) Afficher pour chaque face : le nombre de sorties et le pourcentage.
 *   4) Afficher un histogramme : une étoile pour 50 lancers
 *        1 : ********************  (1003)
 *        2 : *******************   (987)
 *        ...
 *
 * Étape 2 : lancer DEUX dés et compter la somme (2 à 12). Quelle somme sort le plus souvent ?
 *           Pourquoi l'histogramme n'est-il plus "plat" ?
 *
 * Bonus : lancerDeAFaces(int nbFaces) pour simuler un dé à 4, 8, 20... faces.
 *
 * Squelette :
 *
 *   public static int lancerDe() { ... }
 *   public static int[] simulerLancers(int nbLancers) { ... }
 *   public static void afficherHistogramme(int[] compteurs, int debut, int lancersParEtoile) { ... }
 */
public class Kata11_LancerDeDes {

	public static int lancerDe() {
		return (int) (Math.random() * 6) + 1;
	}

	public static int lancerDeAFaces(int nbFaces) {
		return (int) (Math.random() * nbFaces) + 1;
	}

	public static int[] simulerLancers(int nbLancers) {
		int[] compteurs = new int[7];   // cases 1 à 6 utilisées
		for (int i = 0; i < nbLancers; i++) {
			int face = lancerDe();
			compteurs[face]++;
		}
		return compteurs;
	}

	public static int[] simulerDeuxDes(int nbLancers) {
		int[] compteurs = new int[13];  // cases 2 à 12 utilisées
		for (int i = 0; i < nbLancers; i++) {
			int somme = lancerDe() + lancerDe();
			compteurs[somme]++;
		}
		return compteurs;
	}

	public static void afficherPourcentages(int[] compteurs, int debut, int nbLancers) {
		for (int face = debut; face < compteurs.length; face++) {
			double pourcentage = compteurs[face] * 100.0 / nbLancers;
			System.out.println(face + " : " + compteurs[face] + " fois, soit "
					+ Math.round(pourcentage * 10) / 10.0 + " %");
		}
	}

	public static void afficherHistogramme(int[] compteurs, int debut, int lancersParEtoile) {
		for (int face = debut; face < compteurs.length; face++) {
			if (face < 10) {
				System.out.print(" ");   // pour aligner 2..9 avec 10, 11, 12
			}
			System.out.print(face + " : ");
			int nbEtoiles = compteurs[face] / lancersParEtoile;
			for (int i = 0; i < nbEtoiles; i++) {
				System.out.print('*');
			}
			System.out.println("  (" + compteurs[face] + ")");
		}
	}

	public static void main(String[] args) {
		int nbLancers = 6000;

		System.out.println("===== Un dé, " + nbLancers + " lancers =====");
		int[] unDe = simulerLancers(nbLancers);
		afficherPourcentages(unDe, 1, nbLancers);
		afficherHistogramme(unDe, 1, 50);

		System.out.println("===== Deux dés, " + nbLancers + " lancers =====");
		int[] deuxDes = simulerDeuxDes(nbLancers);
		afficherHistogramme(deuxDes, 2, 25);
		// Le 7 sort le plus : 6 combinaisons (1+6, 2+5, 3+4, 4+3, 5+2, 6+1) contre 1 seule pour 2 ou 12

		System.out.println("===== Bonus : dé à 20 faces =====");
		for (int i = 0; i < 10; i++) {
			System.out.print(lancerDeAFaces(20) + " ");
		}
		System.out.println();
	}
}
