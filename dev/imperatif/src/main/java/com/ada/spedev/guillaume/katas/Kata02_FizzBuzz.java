package com.ada.spedev.guillaume.katas;

/*
 * KATA 02 - FizzBuzz
 * Notions : boucle for, modulo, if / else if / else, fonction qui retourne un boolean, procédure
 *
 * Consigne :
 * Afficher les nombres de 1 à 100, mais :
 *   - pour les multiples de 3, afficher "Fizz" à la place du nombre
 *   - pour les multiples de 5, afficher "Buzz" à la place du nombre
 *   - pour les multiples de 3 ET de 5, afficher "FizzBuzz"
 *
 * Étape 2 : écrire une fonction  estMultiple(int nombre, int diviseur)  qui retourne un boolean,
 *           et l'utiliser dans le FizzBuzz.
 * Étape 3 : écrire une procédure  fizzBuzz(int limite)  qui fait le FizzBuzz de 1 à limite.
 *
 * Bonus : ajouter "Bang" pour les multiples de 7 (21 -> "FizzBang", 105 -> "FizzBuzzBang")
 *
 * Squelette :
 *
 *   public static boolean estMultiple(int nombre, int diviseur) {
 *       // TODO
 *   }
 *
 *   public static void fizzBuzz(int limite) {
 *       // TODO
 *   }
 *
 *   public static void main(String[] args) {
 *       fizzBuzz(100);
 *   }
 */
public class Kata02_FizzBuzz {

	public static boolean estMultiple(int nombre, int diviseur) {
		return nombre % diviseur == 0;
	}

	public static void fizzBuzz(int limite) {
		for (int i = 1; i <= limite; i++) {
			if (estMultiple(i, 3) && estMultiple(i, 5)) {
				System.out.println("FizzBuzz");
			} else if (estMultiple(i, 3)) {
				System.out.println("Fizz");
			} else if (estMultiple(i, 5)) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}
		}
	}

	// Bonus : avec 3 diviseurs, les if/else if deviennent ingérables (8 combinaisons !)
	// On affiche donc morceau par morceau avec System.out.print (sans retour à la ligne)
	public static void fizzBuzzBang(int limite) {
		for (int i = 1; i <= limite; i++) {
			boolean aAffiche = false;
			if (estMultiple(i, 3)) {
				System.out.print("Fizz");
				aAffiche = true;
			}
			if (estMultiple(i, 5)) {
				System.out.print("Buzz");
				aAffiche = true;
			}
			if (estMultiple(i, 7)) {
				System.out.print("Bang");
				aAffiche = true;
			}
			if (!aAffiche) {
				System.out.print(i);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		fizzBuzz(100);

		System.out.println("----- Bonus -----");
		fizzBuzzBang(105);
	}
}
