package com.ada.spedev.guillaume.katas;

/*
 * KATA 01 - Les types primitifs
 * Notions : int, long, double, char, boolean, division entière, modulo (%), cast, dépassement
 *
 * Consigne :
 *
 * 1) Conversion de temps
 *    On a une durée de 98765 secondes stockée dans un int.
 *    Afficher cette durée sous la forme : "1 jour(s) 3 heure(s) 26 minute(s) 5 seconde(s)"
 *    Indice : utiliser la division entière (/) et le modulo (%).
 *
 * 2) Conversion de température
 *    Convertir 37 degrés Celsius en Fahrenheit : F = C * 9 / 5 + 32
 *    - Faites le calcul avec des int, puis avec des double. Que remarquez-vous ?
 *    - Que donne 9 / 5 ? Et 9.0 / 5 ? Pourquoi ?
 *
 * 3) Les casts
 *    - Stocker 7.99 dans un double, puis le convertir en int. Quel résultat ?
 *    - Afficher le caractère 'A', puis (int) 'A', puis (char) ('A' + 2)
 *
 * 4) Les limites
 *    - Afficher Integer.MAX_VALUE, puis Integer.MAX_VALUE + 1. Que se passe-t-il ?
 *    - Même chose avec un long.
 *
 * 5) Les booléens
 *    - Déclarer un int age. Créer un boolean estMajeur qui vaut true si age >= 18.
 *    - Créer un boolean peutVoter = estMajeur && aLaNationalite
 */
public class Kata01_Types {

	public static void main(String[] args) {
		// 1) Conversion de temps
		int totalSecondes = 98765;
		int jours = totalSecondes / 86400;          // 86400 secondes dans un jour
		int reste = totalSecondes % 86400;
		int heures = reste / 3600;
		reste = reste % 3600;
		int minutes = reste / 60;
		int secondes = reste % 60;
		System.out.println(totalSecondes + "s = " + jours + " jour(s) " + heures + " heure(s) "
				+ minutes + " minute(s) " + secondes + " seconde(s)");

		// 2) Conversion de température
		int celsiusInt = 37;
		int fahrenheitInt = celsiusInt * 9 / 5 + 32;
		System.out.println("37°C en int    : " + fahrenheitInt + "°F");    // 98 -> on perd la décimale

		double celsius = 37;
		double fahrenheit = celsius * 9 / 5 + 32;
		System.out.println("37°C en double : " + fahrenheit + "°F");       // 98.6

		System.out.println("9 / 5   = " + (9 / 5));     // 1   -> division entière
		System.out.println("9.0 / 5 = " + (9.0 / 5));   // 1.8 -> un des deux est un double

		// 3) Les casts
		double prix = 7.99;
		int prixTronque = (int) prix;
		System.out.println("(int) 7.99 = " + prixTronque);   // 7 -> tronqué, pas arrondi !
		System.out.println("Math.round(7.99) = " + Math.round(prix)); // 8

		char lettre = 'A';
		System.out.println("lettre = " + lettre);
		System.out.println("(int) 'A' = " + (int) lettre);          // 65 (code ASCII/Unicode)
		System.out.println("(char) ('A' + 2) = " + (char) (lettre + 2)); // C

		// 4) Les limites
		int max = Integer.MAX_VALUE;
		System.out.println("Integer.MAX_VALUE     = " + max);
		System.out.println("Integer.MAX_VALUE + 1 = " + (max + 1));  // devient négatif : dépassement !
		long maxLong = Integer.MAX_VALUE;
		System.out.println("en long, MAX + 1      = " + (maxLong + 1));
		System.out.println("Long.MAX_VALUE        = " + Long.MAX_VALUE);

		// 5) Les booléens
		int age = 20;
		boolean aLaNationalite = true;
		boolean estMajeur = age >= 18;
		boolean peutVoter = estMajeur && aLaNationalite;
		System.out.println("Majeur : " + estMajeur + ", peut voter : " + peutVoter);
	}
}
