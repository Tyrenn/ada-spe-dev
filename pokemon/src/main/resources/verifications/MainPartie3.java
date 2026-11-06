package com.ada.spedev.pokemon;

import java.util.ArrayList;
import java.util.List;

// TP POKÉMON - VÉRIFICATIONS DE LA PARTIE 3 : l'héritage et les types (étapes 6 à 9)
//
// Mode d'emploi :
//   1. Copiez ce fichier dans le package com.ada.spedev.pokemon, à côté de vos classes.
//   2. Quand vous avez terminé une étape, décommentez :
//        - son appel dans main() (par exemple : etape6Heritage();)
//        - la méthode correspondante plus bas (supprimez les deux lignes de commentaire qui l'entourent).
//   3. Lancez ce main : chaque ligne doit afficher OK.
//
// MainPartie1et2 ne compilera plus à l'étape 6 (new Pokemon(...) est interdit : Pokemon est abstraite) :
// c'est normal, et c'est même la preuve que votre classe est bien abstraite. Mettez-le de côté.
// Ajoutez aussi vos propres vérifications !
public class MainPartie3 {

	private static int nbErreurs = 0;

	public static void main(String[] args) {
		// etape6Heritage();
		// etape7Sensibilites();
		// etape8Talents();
		// etape9Dresseurs();

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

	/*
	private static void etape6Heritage() {
		titre("Étape 6 : Pokemon devient abstraite");
		// Pokemon p = new Pokemon(...);   -> ne compile plus : Pokemon est abstraite
		List<Pokemon> pokemons = new ArrayList<>();
		pokemons.add(new PokemonFeu("Salamèche", 5, 12));
		pokemons.add(new PokemonEau("Carapuce", 5, 10));
		pokemons.add(new PokemonPlante("Bulbizarre", 5, 11));
		pokemons.add(new PokemonElectrique("Pikachu", 5, 13));
		for (Pokemon p : pokemons) {
			System.out.println("  " + p);   // toString appelle getType() : la version du type réel
		}
		verifier("Salamèche est de type Feu", pokemons.get(0).getType().equals("Feu"));
		verifier("Pikachu est de type Électrique", pokemons.get(3).getType().equals("Électrique"));
	}
	*/

	/*
	private static void etape7Sensibilites() {
		titre("Étape 7 : les faiblesses et résistances");
		Pokemon bulbizarre = new PokemonPlante("Bulbizarre", 5, 11);   // force 55
		Pokemon carapuce = new PokemonEau("Carapuce", 5, 10);           // force 50
		Pokemon pikachu = new PokemonElectrique("Pikachu", 5, 13);      // force 65

		verifier("une Plante craint le Feu (x2)", bulbizarre.sensibiliteA("Feu") == 2.0);
		verifier("une Plante résiste à l'Eau (x0.5)", bulbizarre.sensibiliteA("Eau") == 0.5);
		verifier("l'Eau craint l'Électrique (x2)", carapuce.sensibiliteA("Électrique") == 2.0);
		verifier("type sans effet particulier : x1", pikachu.sensibiliteA("Plante") == 1.0);

		verifier("Bulbizarre contre Carapuce : 55 x 2 = 110", bulbizarre.forceContre(carapuce) == 110);
		verifier("Carapuce contre Bulbizarre : 50 x 0.5 = 25", carapuce.forceContre(bulbizarre) == 25);

		// Sans les types, Carapuce (50) perdait déjà contre Bulbizarre (55). Et contre Pikachu (65) ?
		Pokemon vainqueur = pikachu.affronter(carapuce);
		verifier("Pikachu bat Carapuce : 65 x 2 = 130 contre 50", vainqueur == pikachu);

		// Le type renverse un combat : Salamèche est plus faible, mais il est de type Feu
		Pokemon petitSalameche = new PokemonFeu("Salamèche", 3, 12);    // force 36
		Pokemon grosBulbizarre = new PokemonPlante("Bulbizarre", 6, 11); // force 66
		verifier("Salamèche (36 x 2 = 72, et 86 avec Brasier à l'étape 8) bat Bulbizarre (66 x 0.5 = 33)",
				petitSalameche.affronter(grosBulbizarre) == petitSalameche);
	}
	*/

	/*
	private static void etape8Talents() {
		titre("Étape 8 : les talents (super)");
		Pokemon salameche = new PokemonFeu("Salamèche", 5, 12);
		verifier("Brasier : force 60 x 1.2 = 72", salameche.getForce() == 72);

		Pokemon carapuce = new PokemonEau("Carapuce", 5, 10);
		carapuce.gagnerNiveau();
		verifier("Croissance : niveau 5 -> 7", carapuce.getNiveau() == 7);

		Pokemon vieuxCarapuce = new PokemonEau("Tortank", 99, 10);
		vieuxCarapuce.gagnerNiveau();
		verifier("Croissance respecte le niveau 100 maximum", vieuxCarapuce.getNiveau() == 100);

		Pokemon bulbizarre = new PokemonPlante("Bulbizarre", 5, 11);
		verifier("pas de talent : version de la mère", bulbizarre.getTalent().equals("Aucun"));
	}
	*/

	/*
	private static void etape9Dresseurs() {
		titre("Étape 9 : factoriser les dresseurs et le tour de l'arène");
		try {
			MaitreArene ondine = new MaitreArene("Ondine", "Azuria", "Eau");
			try {
				ondine.capturer(new PokemonFeu("Salamèche", 10, 12));
				verifier("le maître refuse un Pokémon hors de sa spécialité", false);
			} catch (IllegalArgumentException e) {
				verifier("le maître refuse un Pokémon hors de sa spécialité : " + e.getMessage(), true);
			}
			ondine.capturer(new PokemonEau("Stari", 11, 10));
			ondine.capturer(new PokemonEau("Staross", 11, 12));

			ApprentiArene louis = new ApprentiArene("Louis", "Azuria");
			louis.capturer(new PokemonEau("Carapuce", 6, 10));
			louis.capturer(new PokemonEau("Psykokwak", 6, 11));
			louis.capturer(new PokemonEau("Poissirène", 5, 12));
			try {
				louis.capturer(new PokemonEau("Magicarpe", 5, 2));
				verifier("l'apprenti est limité à 3 Pokémon", false);
			} catch (EquipePleineException e) {
				verifier("l'apprenti est limité à 3 Pokémon", louis.getEquipe().size() == 3);
			}

			// La même équipe que dans la partie 2... mais maintenant les types comptent
			Explorateur sacha = new Explorateur("Sacha", "Bourg Palette");
			sacha.capturer(new PokemonFeu("Salamèche", 8, 12));
			sacha.capturer(new PokemonPlante("Bulbizarre", 7, 11));
			sacha.capturer(new PokemonElectrique("Pikachu", 7, 13));

			List<Dresseur> arene = new ArrayList<>();
			arene.add(louis);
			arene.add(ondine);
			for (Dresseur adversaire : arene) {
				sacha.soignerEquipe();
				System.out.println();
				Dresseur vainqueur = sacha.affronter(adversaire);
				if (vainqueur != sacha) {
					break;
				}
			}
			System.out.println();
			System.out.println(sacha.presentation());
			verifier("Sacha a gagné le Badge Azuria", sacha.getBadges().contains("Badge Azuria"));
		} catch (EquipePleineException e) {
			System.out.println("Impossible de former les équipes : " + e.getMessage());
			nbErreurs++;
		}
	}
	*/
}
