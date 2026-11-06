package com.ada.spedev.pokemon;

import java.util.ArrayList;
import java.util.List;

// TP POKÉMON - VÉRIFICATIONS DES PARTIES 1 ET 2 (étapes 1 à 5)
//
// Mode d'emploi :
//   1. Copiez ce fichier dans le package com.ada.spedev.pokemon, à côté de vos classes.
//   2. Quand vous avez terminé une étape, décommentez :
//        - son appel dans main() (par exemple : etape1Pokemon();)
//        - la méthode correspondante plus bas (supprimez les deux lignes de commentaire qui l'entourent).
//   3. Lancez ce main : chaque ligne doit afficher OK.
//
// Si le fichier ne compile plus après avoir décommenté une étape, comparez le nom, les paramètres
// et le type de retour de vos méthodes avec ceux demandés dans le sujet.
// Ajoutez aussi vos propres vérifications !
public class MainPartie1et2 {

	private static int nbErreurs = 0;

	public static void main(String[] args) {
		// etape1Pokemon();
		// etape2Affronter();
		// etape3Explorateur();
		// etape4Arene();
		// etape5Combats();

		System.out.println();
		if (nbErreurs == 0) {
			System.out.println("Toutes les vérifications sont OK");
		} else {
			System.out.println(nbErreurs + " vérification(s) en échec");
		}
	}

	private static void verifier(String description, boolean condition) {
		if (condition) {
			System.out.println("  OK  " + description);
		} else {
			System.out.println("  KO  " + description);
			nbErreurs++;
		}
	}

	private static void titre(String texte) {
		System.out.println();
		System.out.println("===== " + texte + " =====");
	}

	// ============================== PARTIE 1 ==============================

	/*
	private static void etape1Pokemon() {
		titre("Étape 1 : la classe Pokemon");
		Pokemon salameche = new Pokemon("Salamèche", 5, 12);
		System.out.println("  " + salameche);
		verifier("force = 5 x 12 = 60", salameche.getForce() == 60);

		salameche.gagnerNiveau();
		verifier("après gagnerNiveau : niveau 6, force 72", salameche.getNiveau() == 6 && salameche.getForce() == 72);

		Pokemon mewtwo = new Pokemon("Mewtwo", 100, 20);
		mewtwo.gagnerNiveau();
		verifier("le niveau ne dépasse pas 100", mewtwo.getNiveau() == 100);

		try {
			new Pokemon("Bug", 0, 10);
			verifier("niveau 0 refusé", false);
		} catch (IllegalArgumentException e) {
			verifier("niveau 0 refusé : " + e.getMessage(), true);
		}
	}
	*/

	/*
	private static void etape2Affronter() {
		titre("Étape 2 : affronter");
		Pokemon salameche = new Pokemon("Salamèche", 5, 12);   // force 60
		Pokemon bulbizarre = new Pokemon("Bulbizarre", 5, 11);  // force 55

		Pokemon vainqueur = salameche.affronter(bulbizarre);
		verifier("Salamèche (60) bat Bulbizarre (55)", vainqueur == salameche);
		verifier("Bulbizarre est K.O.", bulbizarre.estKo());
		verifier("Salamèche passe niveau 6", salameche.getNiveau() == 6);

		try {
			salameche.affronter(bulbizarre);
			verifier("on ne peut pas affronter un Pokémon K.O.", false);
		} catch (IllegalStateException e) {
			verifier("on ne peut pas affronter un Pokémon K.O.", true);
		}

		bulbizarre.soigner();
		verifier("soigné, Bulbizarre peut de nouveau combattre", !bulbizarre.estKo());

		Pokemon pikachu = new Pokemon("Pikachu", 4, 15);   // force 60
		Pokemon carapuce = new Pokemon("Carapuce", 6, 10);  // force 60
		verifier("égalité : celui qui attaque gagne", pikachu.affronter(carapuce) == pikachu);
	}
	*/

	// ============================== PARTIE 2 ==============================

	/*
	private static void etape3Explorateur() {
		titre("Étape 3 : l'interface Dresseur et l'explorateur");
		Explorateur sacha = new Explorateur("Sacha", "Bourg Palette");
		Pokemon pikachu = new Pokemon("Pikachu", 5, 15);
		try {
			sacha.capturer(pikachu);
			for (int i = 0; i < 5; i++) {
				sacha.capturer(new Pokemon("Rattata", 3, 8));
			}
			verifier("6 Pokémon acceptés (même plusieurs Rattata identiques)", sacha.getEquipe().size() == 6);
			sacha.capturer(new Pokemon("Roucool", 3, 9));   // 7e
			verifier("7e Pokémon refusé", false);
		} catch (EquipePleineException e) {
			verifier("7e Pokémon refusé : " + e.getMessage(), sacha.getEquipe().size() == 6);
		}
		try {
			Explorateur regis = new Explorateur("Régis", "Bourg Palette");
			regis.capturer(pikachu);
			regis.capturer(pikachu);
			verifier("le même Pokémon deux fois est refusé", false);
		} catch (IllegalArgumentException e) {
			verifier("le même Pokémon deux fois est refusé", true);
		} catch (EquipePleineException e) {
			verifier("le même Pokémon deux fois est refusé", false);
		}

		sacha.getEquipe().clear();
		verifier("getEquipe() retourne une copie", sacha.getEquipe().size() == 6);

		verifier("le premier apte est actif", sacha.pokemonActif() == pikachu);
		pikachu.affronter(new Pokemon("Mewtwo", 70, 20));   // Pikachu perd et est K.O.
		verifier("Pikachu K.O. -> le suivant devient actif", sacha.pokemonActif() != pikachu);
		sacha.soignerEquipe();
		verifier("après soin, Pikachu redevient actif", sacha.pokemonActif() == pikachu);
		System.out.println("  " + sacha.presentation());
	}
	*/

	/*
	private static void etape4Arene() {
		titre("Étape 4 : maître et apprenti d'arène");
		MaitreArene ondine = new MaitreArene("Ondine", "Azuria");
		ApprentiArene louis = new ApprentiArene("Louis", "Azuria");
		try {
			louis.capturer(new Pokemon("Carapuce", 6, 10));
			louis.capturer(new Pokemon("Psykokwak", 6, 11));
			louis.capturer(new Pokemon("Poissirène", 5, 12));
			louis.capturer(new Pokemon("Magicarpe", 5, 2));   // 4e
			verifier("l'apprenti est limité à 3 Pokémon", false);
		} catch (EquipePleineException e) {
			verifier("l'apprenti est limité à 3 Pokémon : " + e.getMessage(), louis.getEquipe().size() == 3);
		}
		verifier("le maître offre un badge", "Badge Azuria".equals(ondine.badgeOffert()));
		verifier("l'apprenti n'offre pas de badge", louis.badgeOffert() == null);

		// Polymorphisme : trois classes différentes, rangées dans une liste du type de l'interface
		List<Dresseur> dresseurs = new ArrayList<>();
		dresseurs.add(new Explorateur("Sacha", "Bourg Palette"));
		dresseurs.add(louis);
		dresseurs.add(ondine);
		for (Dresseur d : dresseurs) {
			System.out.println("  " + d.presentation() + " - " + d.getEquipe().size() + " Pokémon");
		}
	}
	*/

	/*
	private static void etape5Combats() {
		titre("Étape 5 : les combats de dresseurs");
		try {
			Explorateur sacha = new Explorateur("Sacha", "Bourg Palette");
			sacha.capturer(new Pokemon("Pikachu", 8, 13));
			sacha.capturer(new Pokemon("Bulbizarre", 7, 11));
			sacha.capturer(new Pokemon("Salamèche", 7, 12));

			ApprentiArene louis = new ApprentiArene("Louis", "Azuria");
			louis.capturer(new Pokemon("Carapuce", 6, 10));
			louis.capturer(new Pokemon("Psykokwak", 6, 11));

			MaitreArene ondine = new MaitreArene("Ondine", "Azuria");
			ondine.capturer(new Pokemon("Stari", 11, 10));
			ondine.capturer(new Pokemon("Staross", 11, 12));

			// Sacha fait le tour de l'arène : d'abord l'apprenti, puis le maître
			List<Dresseur> arene = new ArrayList<>();
			arene.add(louis);
			arene.add(ondine);
			for (Dresseur adversaire : arene) {
				sacha.soignerEquipe();
				System.out.println();
				sacha.affronter(adversaire);
			}
			System.out.println();
			System.out.println(sacha.presentation());
			verifier("Sacha a gagné le Badge Azuria", sacha.getBadges().contains("Badge Azuria"));

			// Le maître affronte à son tour : il se soigne d'abord, et compte ses défenses
			Explorateur regis = new Explorateur("Régis", "Bourg Palette");
			regis.capturer(new Pokemon("Rattata", 5, 8));
			System.out.println();
			ondine.affronter(regis);
			verifier("Ondine compte une défense réussie", ondine.getNbDefensesReussies() == 1);
			verifier("Régis n'a pas de badge", regis.getBadges().isEmpty());
		} catch (EquipePleineException e) {
			System.out.println("Impossible de former les équipes : " + e.getMessage());
			nbErreurs++;
		}
	}
	*/
}
