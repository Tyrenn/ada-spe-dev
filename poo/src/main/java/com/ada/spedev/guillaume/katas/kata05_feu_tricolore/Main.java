package com.ada.spedev.guillaume.katas.kata05_feu_tricolore;

/*
 * KATA 05 - Le feu tricolore
 * Notions : enum (avec attributs et méthodes), switch sur une enum, état interne d'un objet,
 *           un objet qui change d'état au fil du temps
 *
 * Pourquoi une enum plutôt qu'un String ou un int ?
 *     String couleur = "vret";   // faute de frappe : compile très bien, bug à l'exécution
 *     Couleur couleur = Couleur.VRET;   // ne compile pas : le compilateur nous protège
 *
 * Consigne :
 *   1) Créer une enum Couleur { VERT, ORANGE, ROUGE }.
 *   2) Ajouter à l'enum une méthode  suivante()  : VERT -> ORANGE -> ROUGE -> VERT.
 *   3) Créer une classe FeuTricolore qui a une couleur (démarre au ROUGE) et une méthode
 *      passerALaSuivante().
 *   4) Dans le main : afficher les 6 premières couleurs du feu.
 *
 * Étape 2 : chaque couleur a une durée (VERT 30 s, ORANGE 5 s, ROUGE 25 s).
 *           Ajouter un attribut et un constructeur à l'enum :  VERT(30), ORANGE(5), ROUGE(25);
 *           Ajouter au feu une méthode  tic()  qui simule une seconde et change de couleur
 *           automatiquement quand la durée est écoulée.
 *
 * Étape 3 : un feu peut tomber en panne : il passe à l'ORANGE (clignotant) et tic() ne fait plus rien
 *           jusqu'à l'appel de reparer().
 *
 * Bonus : un Carrefour qui contient deux feux, toujours dans des états compatibles
 *         (jamais VERT tous les deux en même temps !).
 *
 * Remarque : une enum est une classe ! Couleur.VERT est un objet, on peut appeler ses méthodes.
 */
public class Main {

	public static void main(String[] args) {
		FeuTricolore feu = new FeuTricolore("rue de la Paix");

		for (int i = 0; i < 6; i++) {
			System.out.println(feu);
			feu.passerALaSuivante();
		}

		System.out.println("----- Étape 2 : tic() pendant 70 secondes -----");
		Couleur derniere = null;
		for (int seconde = 0; seconde < 70; seconde++) {
			if (feu.getCouleur() != derniere) {
				derniere = feu.getCouleur();
				System.out.println("t = " + seconde + " s -> " + derniere
						+ (derniere.peutPasser() ? " (on passe)" : " (on s'arrête)"));
			}
			feu.tic();
		}

		System.out.println("----- Étape 3 : panne -----");
		feu.tomberEnPanne();
		for (int i = 0; i < 100; i++) {
			feu.tic();
		}
		System.out.println(feu);
		feu.reparer();
		System.out.println(feu);

		System.out.println("----- Toutes les valeurs d'une enum -----");
		for (Couleur c : Couleur.values()) {
			System.out.println(c + " dure " + c.getDureeSecondes() + " s");
		}
	}
}
