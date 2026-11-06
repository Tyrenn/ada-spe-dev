package com.ada.spedev.guillaume.pokemon.correction.partie4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * TP POKÉMON - CORRECTION DE LA PARTIE 4 : pour aller plus loin (étapes 10 à 13)
 */
public class Main {

	private static int nbErreurs = 0;

	// Un dé qui fait toujours 1 : les combats donnent le même résultat que sans dé
	private static final De SANS_HASARD = new DeTruque(1);

	public static void main(String[] args) {
		etape10Static();
		etape11Enum();
		etape12Attaques();
		etape13Des();

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

	private static void etape10Static() {
		titre("Étape 10 : static (numéro et Pokédex)");
		Pokemon salameche = Pokedex.salameche(5);
		Pokemon carapuce = Pokedex.carapuce(5);
		System.out.println("  " + salameche);
		System.out.println("  " + carapuce);
		verifier("numéros attribués automatiquement", carapuce.getNumero() == salameche.getNumero() + 1);
		verifier("le Pokédex crée un PokemonFeu", salameche instanceof PokemonFeu);
	}

	private static void etape11Enum() {
		titre("Étape 11 : le type devient une enum");
		for (Type t : Type.values()) {
			System.out.println("  " + t + " -> " + t.getLibelle());
		}
		Pokemon bulbizarre = Pokedex.bulbizarre(5);
		verifier("Bulbizarre est de type PLANTE", bulbizarre.getType() == Type.PLANTE);
		verifier("une Plante craint le Feu", bulbizarre.sensibiliteA(Type.FEU) == 2.0);
		// bulbizarre.sensibiliteA("feu");   -> ne compile plus : la faute de frappe est détectée !
		try {
			MaitreArene ondine = new MaitreArene("Ondine", "Azuria", Type.EAU);
			ondine.capturer(bulbizarre);
			verifier("le maître refuse un Pokémon hors de sa spécialité", false);
		} catch (IllegalArgumentException e) {
			verifier("le maître refuse un Pokémon hors de sa spécialité : " + e.getMessage(), true);
		} catch (EquipePleineException e) {
			verifier("le maître refuse un Pokémon hors de sa spécialité", false);
		}
	}

	private static void etape12Attaques() {
		titre("Étape 12 : les attaques");
		Attaque flammeche = new Attaque("Flammèche", Type.FEU, 40);
		Attaque flammecheMajuscules = new Attaque("FLAMMÈCHE", Type.FEU, 40);
		System.out.println("  " + flammeche);
		verifier("equals sur le nom (sans tenir compte des majuscules)", flammeche.equals(flammecheMajuscules));
		verifier("même hashCode", flammeche.hashCode() == flammecheMajuscules.hashCode());
		Set<Attaque> ensemble = new HashSet<>();
		ensemble.add(flammeche);
		ensemble.add(flammecheMajuscules);
		verifier("HashSet : pas de doublon", ensemble.size() == 1);

		Pokemon salameche = Pokedex.salameche(5);    // force 60 x 1.2 (Brasier) = 72
		Pokemon bulbizarre = Pokedex.bulbizarre(5);  // force 55
		Pokemon carapuce = Pokedex.carapuce(5);      // force 50
		verifier("Salamèche connaît Charge et Flammèche", salameche.getAttaques().size() == 2);

		verifier("Charge sur Bulbizarre : 72 x 40/40 = 72", salameche.forceContre(bulbizarre, Attaque.CHARGE) == 72);
		verifier("Flammèche sur Bulbizarre : 72 x 1 x 1.5 (même type) x 2 = 216",
				salameche.forceContre(bulbizarre, Pokedex.FLAMMECHE) == 216);
		verifier("Flammèche sur Carapuce : 72 x 1.5 x 0.5 = 54", salameche.forceContre(carapuce, Pokedex.FLAMMECHE) == 54);
		verifier("contre Carapuce, Charge (72) vaut mieux que Flammèche (54)",
				salameche.meilleureAttaqueContre(carapuce).equals(Attaque.CHARGE));

		try {
			salameche.apprendre(Pokedex.LANCE_FLAMMES);
			salameche.apprendre(Pokedex.ECLAIR);
			verifier("4 attaques acceptées", salameche.getAttaques().size() == 4);
			salameche.apprendre(Pokedex.TONNERRE);
			verifier("5e attaque refusée", false);
		} catch (TropDAttaquesException e) {
			verifier("5e attaque refusée : " + e.getMessage(), salameche.getAttaques().size() == 4);
		}
		salameche.getAttaques().clear();
		verifier("getAttaques() retourne une copie", salameche.getAttaques().size() == 4);
		verifier("Lance-Flammes sur Bulbizarre : 72 x 90/40 x 1.5 x 2 = 486",
				salameche.forceContre(bulbizarre, Pokedex.LANCE_FLAMMES) == 486);
	}

	private static void etape13Des() {
		titre("Étape 13 : les dés");
		verifier("dé truqué à 4 sur 6 faces : 4", new DeTruque(4).lancer(6) == 4);
		verifier("dé truqué à 9 sur 6 faces : 6", new DeTruque(9).lancer(6) == 6);
		De de = new DeAleatoire(42);
		boolean dansLesBornes = true;
		for (int i = 0; i < 1000; i++) {
			int valeur = de.lancer(6);
			if (valeur < 1 || valeur > 6) {
				dansLesBornes = false;
			}
		}
		verifier("dé aléatoire : 1000 lancers entre 1 et 6", dansLesBornes);

		Pokemon salameche = Pokedex.salameche(5);
		Pokemon bulbizarre = Pokedex.bulbizarre(5);
		verifier("dé truqué à 1 : Salamèche bat Bulbizarre (Flammèche 216 contre Charge 55)",
				salameche.affronter(bulbizarre, SANS_HASARD) == salameche);

		try {
			Explorateur sacha = new Explorateur("Sacha", "Bourg Palette");
			sacha.capturer(Pokedex.salameche(8));
			sacha.capturer(Pokedex.bulbizarre(7));
			sacha.capturer(Pokedex.pikachu(7));

			ApprentiArene louis = new ApprentiArene("Louis", "Azuria");
			louis.capturer(Pokedex.carapuce(6));
			louis.capturer(new PokemonEau("Psykokwak", 6, 11));

			MaitreArene ondine = new MaitreArene("Ondine", "Azuria", Type.EAU);
			Pokemon staross = new PokemonEau("Staross", 11, 12);
			staross.apprendre(Pokedex.HYDROCANON);
			ondine.capturer(new PokemonEau("Stari", 11, 10));
			ondine.capturer(staross);

			// Même graine = même combat à chaque exécution
			De vraiDe = new DeAleatoire(2026);
			List<Dresseur> arene = new ArrayList<>();
			arene.add(louis);
			arene.add(ondine);
			for (Dresseur adversaire : arene) {
				sacha.soignerEquipe();
				System.out.println();
				Dresseur vainqueur = sacha.affronter(adversaire, vraiDe);
				if (vainqueur != sacha) {
					System.out.println(sacha.getNom() + " rentre à Bourg Palette s'entraîner.");
					break;
				}
			}
			System.out.println();
			System.out.println(sacha.presentation());
		} catch (EquipePleineException e) {
			System.out.println("Impossible de former les équipes : " + e.getMessage());
			nbErreurs++;
		} catch (TropDAttaquesException e) {
			System.out.println("Impossible de préparer Staross : " + e.getMessage());
			nbErreurs++;
		}
	}
}
