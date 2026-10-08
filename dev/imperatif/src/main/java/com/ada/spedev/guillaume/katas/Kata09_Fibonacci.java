package com.ada.spedev.guillaume.katas;

/*
 * KATA 09 - La suite de Fibonacci
 * Notions : variables qui "avancent" dans une boucle, int vs long (dépassement),
 *           fonction qui remplit et retourne un tableau, récursivité (bonus)
 *
 * La suite : chaque terme est la somme des deux précédents.
 *   F(0) = 0, F(1) = 1, F(n) = F(n-1) + F(n-2)
 *   0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ...
 *
 * Consigne :
 *   1) fibonacci(int n) -> long : retourne le n-ième terme, avec une boucle for et
 *      seulement 2 ou 3 variables (pas de tableau).
 *   2) suiteFibonacci(int n) -> long[] : retourne un tableau avec les n premiers termes.
 *   3) Écrire fibonacciInt(int n) -> int (même chose avec des int).
 *      Comparer fibonacciInt(50) et fibonacci(50). Que se passe-t-il ? À partir de quel n ça casse ?
 *   4) premierTermeSuperieurA(long seuil) -> int : le premier n tel que F(n) > seuil  (while !)
 *
 * Bonus : fibonacciRecursif(int n) -> long, qui s'appelle elle-même.
 *   C'est très court à écrire... mais lancez-la avec n = 45. Pourquoi est-ce si lent ?
 *
 * Squelette :
 *
 *   public static long fibonacci(int n) {
 *       long precedent = 0;
 *       long courant = 1;
 *       // TODO
 *   }
 */
public class Kata09_Fibonacci {

	public static long fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		long precedent = 0;
		long courant = 1;
		for (int i = 2; i <= n; i++) {
			long suivant = precedent + courant;
			precedent = courant;
			courant = suivant;
		}
		return courant;
	}

	public static int fibonacciInt(int n) {
		if (n == 0) {
			return 0;
		}
		int precedent = 0;
		int courant = 1;
		for (int i = 2; i <= n; i++) {
			int suivant = precedent + courant;
			precedent = courant;
			courant = suivant;
		}
		return courant;
	}

	public static long[] suiteFibonacci(int n) {
		long[] suite = new long[n];
		for (int i = 0; i < n; i++) {
			if (i < 2) {
				suite[i] = i;
			} else {
				suite[i] = suite[i - 1] + suite[i - 2];
			}
		}
		return suite;
	}

	public static int premierTermeSuperieurA(long seuil) {
		int n = 0;
		while (fibonacci(n) <= seuil) {
			n++;
		}
		return n;
	}

	public static long fibonacciRecursif(int n) {
		if (n < 2) {
			return n;
		}
		return fibonacciRecursif(n - 1) + fibonacciRecursif(n - 2);
	}

	public static void main(String[] args) {
		System.out.println("fibonacci(10) = " + fibonacci(10));   // 55

		long[] suite = suiteFibonacci(15);
		System.out.print("15 premiers termes : ");
		for (int i = 0; i < suite.length; i++) {
			System.out.print(suite[i] + " ");
		}
		System.out.println();

		// Le dépassement des int
		System.out.println("fibonacciInt(50) = " + fibonacciInt(50));   // négatif !
		System.out.println("fibonacci(50)    = " + fibonacci(50));
		int n = 0;
		while (fibonacciInt(n) >= 0) {
			n++;
		}
		System.out.println("Avec des int, ça devient négatif à partir de n = " + n);   // 47

		System.out.println("Premier terme > 1000 : F(" + premierTermeSuperieurA(1000) + ")");

		// Bonus : la version récursive recalcule les mêmes termes des milliards de fois
		System.out.println("fibonacciRecursif(30) = " + fibonacciRecursif(30));
	}
}
