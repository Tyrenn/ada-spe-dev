package com.ada.spedev.guillaume.katas.kata03_fraction;

/*
 * KATA 03 - Les fractions
 * Notions : objet immuable, final, méthodes qui retournent un NOUVEL objet,
 *           validation dans le constructeur, méthode static private, equals / hashCode
 *
 * Un objet immuable ne change jamais après sa création. Avantage : on peut le partager
 * entre plusieurs variables sans risque (souvenez-vous du piège du kata 01 avec  Point b = a;).
 * String est immuable en Java pour cette raison.
 *
 * Consigne :
 *   1) Classe Fraction avec deux attributs private final : numerateur, denominateur (int).
 *   2) Le constructeur refuse un dénominateur nul et SIMPLIFIE la fraction (2/4 -> 1/2).
 *      Astuce : une méthode  private static int pgcd(int a, int b)  (algorithme d'Euclide).
 *   3) Méthodes  plus, moins, fois, diviseePar  qui prennent une Fraction et retournent une
 *      NOUVELLE Fraction. L'objet sur lequel on appelle la méthode n'est jamais modifié.
 *   4) toString() : "3/4", ou "2" si le dénominateur vaut 1.
 *
 * Étape 2 : que vaut  new Fraction(1, 2) == new Fraction(2, 4)  ?
 *           Redéfinir  equals(Object o)  pour que deux fractions de même valeur soient égales.
 *           (et hashCode() qui va avec : la règle est "toujours les deux ensemble")
 *
 * Étape 3 : gérer le signe : 1/-2 doit être stocké comme -1/2.
 *
 * Bonus : calculer 1/1 + 1/2 + 1/3 + ... + 1/10 avec une boucle. Le résultat est exact !
 *         Puis additionner 10 fois 1/10 : en Fraction on obtient 1, et en double ?
 *
 * Squelette :
 *
 *   public class Fraction {
 *       private final int numerateur;
 *       private final int denominateur;
 *
 *       public Fraction(int numerateur, int denominateur) { ... }
 *       public Fraction plus(Fraction autre) { ... }
 *       public Fraction fois(Fraction autre) { ... }
 *       ...
 *   }
 */
public class Main {

	public static void main(String[] args) {
		Fraction unDemi = new Fraction(1, 2);
		Fraction unTiers = new Fraction(1, 3);

		System.out.println(unDemi + " + " + unTiers + " = " + unDemi.plus(unTiers));         // 5/6
		System.out.println(unDemi + " - " + unTiers + " = " + unDemi.moins(unTiers));        // 1/6
		System.out.println(unDemi + " * " + unTiers + " = " + unDemi.fois(unTiers));         // 1/6
		System.out.println(unDemi + " / " + unTiers + " = " + unDemi.diviseePar(unTiers));   // 3/2
		System.out.println("unDemi n'a pas changé : " + unDemi);

		System.out.println("6/4 est simplifié en " + new Fraction(6, 4));     // 3/2
		System.out.println("1/-2 est stocké comme " + new Fraction(1, -2));   // -1/2
		System.out.println("4/2 s'affiche " + new Fraction(4, 2));            // 2

		System.out.println("----- Étape 2 : == vs equals -----");
		Fraction a = new Fraction(1, 2);
		Fraction b = new Fraction(2, 4);
		System.out.println("a == b      ? " + (a == b));        // false : deux objets
		System.out.println("a.equals(b) ? " + a.equals(b));     // true  : même valeur

		System.out.println("----- Bonus : somme harmonique -----");
		Fraction somme = new Fraction(0);
		for (int i = 1; i <= 10; i++) {
			somme = somme.plus(new Fraction(1, i));   // on remplace la référence, l'ancien objet n'est pas modifié
		}
		System.out.println("1 + 1/2 + ... + 1/10 = " + somme);   // 7381/2520

		Fraction dixDixiemes = new Fraction(0);
		double dixDixiemesDouble = 0;
		for (int i = 0; i < 10; i++) {
			dixDixiemes = dixDixiemes.plus(new Fraction(1, 10));
			dixDixiemesDouble += 0.1;
		}
		System.out.println("10 x 1/10 en Fraction : " + dixDixiemes);         // 1
		System.out.println("10 x 0.1 en double    : " + dixDixiemesDouble);   // 0.9999999999999999

		try {
			new Fraction(1, 0);
		} catch (IllegalArgumentException e) {
			System.out.println("Refusé : " + e.getMessage());
		}
	}
}
