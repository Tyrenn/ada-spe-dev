package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPES 10 et 12 - Des méthodes static qui créent ET préparent un Pokémon prêt à combattre.
// On les appelle sur la classe, sans objet : Pokedex.salameche(5)
public class Pokedex {

	// Attaques partagées : elles sont immuables, une seule instance suffit pour tout le programme
	public static final Attaque FLAMMECHE = new Attaque("Flammèche", Type.FEU, 40);
	public static final Attaque LANCE_FLAMMES = new Attaque("Lance-Flammes", Type.FEU, 90);
	public static final Attaque PISTOLET_A_O = new Attaque("Pistolet à O", Type.EAU, 40);
	public static final Attaque HYDROCANON = new Attaque("Hydrocanon", Type.EAU, 110);
	public static final Attaque FOUET_LIANES = new Attaque("Fouet Lianes", Type.PLANTE, 45);
	public static final Attaque TRANCH_HERBE = new Attaque("Tranch'Herbe", Type.PLANTE, 55);
	public static final Attaque ECLAIR = new Attaque("Éclair", Type.ELECTRIQUE, 40);
	public static final Attaque TONNERRE = new Attaque("Tonnerre", Type.ELECTRIQUE, 90);

	public static Pokemon salameche(int niveau) {
		Pokemon p = new PokemonFeu("Salamèche", niveau, 12);
		apprendre(p, FLAMMECHE);
		return p;
	}

	public static Pokemon carapuce(int niveau) {
		Pokemon p = new PokemonEau("Carapuce", niveau, 10);
		apprendre(p, PISTOLET_A_O);
		return p;
	}

	public static Pokemon bulbizarre(int niveau) {
		Pokemon p = new PokemonPlante("Bulbizarre", niveau, 11);
		apprendre(p, FOUET_LIANES);
		return p;
	}

	public static Pokemon pikachu(int niveau) {
		Pokemon p = new PokemonElectrique("Pikachu", niveau, 13);
		apprendre(p, ECLAIR);
		return p;
	}

	// Un Pokémon du Pokédex connaît 2 attaques : l'exception ne peut pas arriver.
	// Si elle arrive quand même, c'est un bug : on la transforme en exception non vérifiée.
	private static void apprendre(Pokemon pokemon, Attaque attaque) {
		try {
			pokemon.apprendre(attaque);
		} catch (TropDAttaquesException e) {
			throw new IllegalStateException("Pokédex mal configuré", e);
		}
	}
}
