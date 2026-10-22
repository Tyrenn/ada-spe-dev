package com.ada.spedev.guillaume.katas.kata16_arene;

/*
 * KATA 16 - Mini-projet : l'arène
 * Notions : TOUT ! classes, encapsulation, interfaces, composition, stratégies interchangeables,
 *           faux objets pour tester, surcharge de méthode, lambda
 *
 * Objectif : un petit jeu de combat au tour par tour, SANS héritage.
 * Réflexe naïf : Personnage -> Guerrier, Archer, Mage... et le jour où un Guerrier ramasse un arc ?
 * Ici, un personnage est COMPOSÉ d'une Arme et d'une Protection, interchangeables.
 *
 * Consigne :
 *   1) Interface De :  int lancer(int nbFaces);
 *        DeAleatoire (java.util.Random)  et  DeTruque (retourne toujours la même valeur).
 *   2) Interface Arme :  String nom();  int degats(De de);
 *        Epee       : 6 + 1d4
 *        Arc        : 1d10, coup critique x3 sur un 10
 *        BatonDeFeu : 15 dégâts tant qu'il a des charges, puis 2
 *   3) Interface Protection :  int absorber(int degats);
 *        SansProtection, Bouclier (-3 par coup, minimum 0), ArmureLourde (dégâts / 2)
 *   4) Classe Personnage : nom, pv, pvMax, une Arme, une Protection.
 *        recevoir(int degatsBruts) -> int,  attaquer(Personnage cible, De de) -> String,
 *        estVivant(),  equiper(Arme)  et  equiper(Protection)  (surcharge !)
 *   5) Classe Arene (reçoit un De) avec  combat(Personnage a, Personnage b) -> Personnage vainqueur.
 *
 * Étape 2 : avec un DeTruque, VÉRIFIER qu'une Epee fait 10 dégâts (6 + 4) et qu'un Bouclier
 *           en laisse passer 7. Sans le dé truqué, ce test serait impossible : pourquoi ?
 *
 * Étape 3 : en plein combat, le perdant ramasse une nouvelle arme. Quelles classes faut-il modifier ?
 *
 * Bonus :
 *   - une arme écrite en lambda ? (Arme a deux méthodes : est-ce possible ? et pour Protection ?)
 *   - une Protection "Esquive" qui annule les dégâts une fois sur deux (elle a besoin d'un De...)
 *   - un tournoi entre 4 personnages
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("===== Étape 2 : vérifications avec un dé truqué =====");
		De deMax = new DeTruque(4);
		Personnage cible = new Personnage("Mannequin", 100, new Epee(), new Bouclier());
		int degatsEpee = new Epee().degats(deMax);
		int subis = cible.recevoir(degatsEpee);
		System.out.println("Épée : " + degatsEpee + (degatsEpee == 10 ? " OK" : " KO"));
		System.out.println("Bouclier : " + subis + (subis == 7 ? " OK" : " KO"));
		System.out.println("Arc critique : " + new Arc().degats(new DeTruque(10)));   // 30

		System.out.println();
		System.out.println("===== Combat =====");
		Personnage conan = new Personnage("Conan", 60, new Epee(), new Bouclier());
		Personnage merlin = new Personnage("Merlin", 45, new BatonDeFeu(3), new SansProtection());
		Personnage robin = new Personnage("Robin", 50, new Arc(), new ArmureLourde());

		Arene arene = new Arene(new DeAleatoire(42));
		Personnage vainqueur = arene.combat(conan, merlin);

		System.out.println();
		System.out.println("===== Étape 3 : changement d'équipement =====");
		// Aucune classe à modifier : on remplace juste l'objet Arme ou Protection
		System.out.println("Robin troque son armure lourde contre un bouclier et défie le vainqueur.");
		robin.equiper(new Bouclier());
		if (vainqueur != null) {
			arene.combat(robin, vainqueur);
		}

		System.out.println();
		System.out.println("===== Bonus : Protection en lambda =====");
		// Protection n'a qu'une méthode : une lambda suffit
		Protection invincible = degats -> 0;
		Personnage golem = new Personnage("Golem", 1, new Epee(), invincible);
		golem.recevoir(1000);
		System.out.println(golem + (golem.estVivant() ? " a tout encaissé" : " est tombé"));
	}
}
