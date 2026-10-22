package com.ada.spedev.guillaume.katas.kata09_classement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * KATA 09 - Le classement des joueurs
 * Notions : implémenter une interface du JDK, Comparable<T> / compareTo, Comparator<T> / compare,
 *           générique <T>, Collections.sort
 *
 * Le JDK est rempli d'interfaces. En les implémentant, nos classes "se branchent" sur du code
 * écrit par d'autres il y a 25 ans. Collections.sort() ne connaît pas la classe Joueur,
 * mais elle sait trier n'importe quel objet Comparable.
 *
 * Consigne :
 *   1) Classe Joueur : pseudo, score, age.
 *   2) Joueur implémente  Comparable<Joueur>  : ordre naturel = score décroissant,
 *      puis pseudo alphabétique en cas d'égalité.
 *        compareTo retourne  < 0  si this doit être AVANT autre
 *                            > 0  si this doit être APRÈS autre
 *                              0  si égalité
 *      Astuce : Integer.compare(a, b) et String.compareTo(...)
 *   3) Créer une liste de joueurs et la trier avec  Collections.sort(joueurs);
 *      Afficher le podium.
 *
 * Étape 2 : on veut aussi trier par pseudo, ou par âge, SANS changer l'ordre naturel.
 *           Créer ComparateurParPseudo et ComparateurParAge qui implémentent  Comparator<Joueur>
 *           et utiliser  joueurs.sort(new ComparateurParAge());
 *
 * Étape 3 : que se passe-t-il si on essaie de trier une liste d'objets qui n'implémentent pas
 *           Comparable ?  (essayer avec la classe Point du kata 01)
 *
 * Bonus : Comparator est une interface avec UNE SEULE méthode abstraite -> on peut l'implémenter
 *         avec une lambda, sans créer de classe :
 *             joueurs.sort((j1, j2) -> Integer.compare(j1.getAge(), j2.getAge()));
 *             joueurs.sort(Comparator.comparing(Joueur::getPseudo).reversed());
 */
public class Main {

	public static void afficher(String titre, List<Joueur> joueurs) {
		System.out.println("----- " + titre + " -----");
		for (int i = 0; i < joueurs.size(); i++) {
			System.out.println((i + 1) + ". " + joueurs.get(i));
		}
	}

	public static void main(String[] args) {
		List<Joueur> joueurs = new ArrayList<>();
		joueurs.add(new Joueur("zelda", 1200, 17));
		joueurs.add(new Joueur("link", 800, 25));
		joueurs.add(new Joueur("mario", 1200, 40));
		joueurs.add(new Joueur("Peach", 950, 33));
		joueurs.add(new Joueur("bowser", 300, 52));

		Collections.sort(joueurs);
		afficher("Ordre naturel (score)", joueurs);
		System.out.println("Podium : " + joueurs.get(0).getPseudo() + ", "
				+ joueurs.get(1).getPseudo() + ", " + joueurs.get(2).getPseudo());

		joueurs.sort(new ComparateurParPseudo());
		afficher("Par pseudo", joueurs);

		joueurs.sort(new ComparateurParAge());
		afficher("Par âge", joueurs);

		// Étape 3 : List<Point> points = ...; Collections.sort(points);
		//   -> ne compile pas : Point n'implémente pas Comparable

		System.out.println("===== Bonus : lambdas =====");
		joueurs.sort((j1, j2) -> Integer.compare(j2.getAge(), j1.getAge()));
		afficher("Par âge décroissant (lambda)", joueurs);

		joueurs.sort(Comparator.comparing(Joueur::getPseudo, String.CASE_INSENSITIVE_ORDER).reversed());
		afficher("Par pseudo inversé (Comparator.comparing)", joueurs);
	}
}
