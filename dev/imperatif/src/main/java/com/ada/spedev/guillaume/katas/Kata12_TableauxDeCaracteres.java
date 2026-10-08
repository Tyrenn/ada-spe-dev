package com.ada.spedev.guillaume.katas;

/*
 * KATA 12 - Jouer avec des tableaux de caractères
 * Notions : char[], un char est un nombre (arithmétique sur les char), comparaisons de char,
 *           modulo pour "boucler", procédure qui modifie un tableau
 *
 * On ne manipule pas encore les String (ce sont des objets) : on travaille avec des char[].
 * Rappel : 'a' vaut 97, 'b' vaut 98... 'A' vaut 65... Les lettres se suivent !
 *          'c' - 'a' = 2      (char) ('a' + 2) = 'c'
 *
 * Mot de test :  char[] mot = {'k', 'a', 'y', 'a', 'k'};
 * Phrase de test : char[] phrase = {'b','o','n','j','o','u','r',' ','a','d','a'};
 *
 * Consigne :
 *   1) afficherMot(char[] mot) : affiche les caractères à la suite, puis retour à la ligne
 *   2) estVoyelle(char c) -> boolean  (a, e, i, o, u, y)
 *   3) compterVoyelles(char[] mot) -> int
 *   4) estPalindrome(char[] mot) -> boolean  ("kayak", "radar" -> true)
 *      (comparer la première et la dernière lettre, puis la 2e et l'avant-dernière, etc.)
 *   5) enMajuscules(char[] mot) : transforme le tableau en majuscules (ne touche pas aux espaces !)
 *      Indice : quelle est la différence entre 'a' et 'A' ?
 *
 *   6) Le chiffre de César : chiffrer(char[] message, int decalage)
 *      Chaque lettre minuscule est décalée de "decalage" positions dans l'alphabet.
 *      Avec un décalage de 3 : a -> d, b -> e, ... x -> a, y -> b, z -> c
 *      Les espaces ne bougent pas.
 *      Indice : position = c - 'a' (entre 0 et 25), puis on utilise % 26 pour revenir au début.
 *
 *   7) dechiffrer(char[] message, int decalage). Peut-on réutiliser chiffrer ?
 *
 * Bonus : compterLettres(char[] texte) -> int[26] : combien de fois chaque lettre apparaît
 *         (compteurs[c - 'a']++), puis afficher les lettres présentes.
 *
 * Squelette :
 *
 *   public static void afficherMot(char[] mot) { ... }
 *   public static boolean estVoyelle(char c) { ... }
 *   public static int compterVoyelles(char[] mot) { ... }
 *   public static boolean estPalindrome(char[] mot) { ... }
 *   public static void enMajuscules(char[] mot) { ... }
 *   public static void chiffrer(char[] message, int decalage) { ... }
 *   public static void dechiffrer(char[] message, int decalage) { ... }
 */
public class Kata12_TableauxDeCaracteres {

	public static void afficherMot(char[] mot) {
		for (int i = 0; i < mot.length; i++) {
			System.out.print(mot[i]);
		}
		System.out.println();
	}

	public static boolean estVoyelle(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
	}

	public static int compterVoyelles(char[] mot) {
		int compteur = 0;
		for (int i = 0; i < mot.length; i++) {
			if (estVoyelle(mot[i])) {
				compteur++;
			}
		}
		return compteur;
	}

	public static boolean estPalindrome(char[] mot) {
		for (int i = 0; i < mot.length / 2; i++) {
			if (mot[i] != mot[mot.length - 1 - i]) {
				return false;
			}
		}
		return true;
	}

	public static void enMajuscules(char[] mot) {
		for (int i = 0; i < mot.length; i++) {
			if (mot[i] >= 'a' && mot[i] <= 'z') {
				mot[i] = (char) (mot[i] - 'a' + 'A');   // ou mot[i] - 32
			}
		}
	}

	public static void chiffrer(char[] message, int decalage) {
		for (int i = 0; i < message.length; i++) {
			char c = message[i];
			if (c >= 'a' && c <= 'z') {
				int position = c - 'a';                         // 0 à 25
				int nouvellePosition = (position + decalage) % 26;
				message[i] = (char) ('a' + nouvellePosition);
			}
		}
	}

	public static void dechiffrer(char[] message, int decalage) {
		// Décaler de -3, c'est comme décaler de 26 - 3 = 23 (et ça évite un modulo négatif)
		chiffrer(message, 26 - decalage % 26);
	}

	public static int[] compterLettres(char[] texte) {
		int[] compteurs = new int[26];
		for (int i = 0; i < texte.length; i++) {
			if (texte[i] >= 'a' && texte[i] <= 'z') {
				compteurs[texte[i] - 'a']++;
			}
		}
		return compteurs;
	}

	public static void main(String[] args) {
		char[] mot = {'k', 'a', 'y', 'a', 'k'};
		char[] phrase = {'b', 'o', 'n', 'j', 'o', 'u', 'r', ' ', 'a', 'd', 'a'};

		afficherMot(mot);
		System.out.println("Voyelles dans kayak   : " + compterVoyelles(mot));
		System.out.println("kayak palindrome ?    : " + estPalindrome(mot));
		System.out.println("phrase palindrome ?   : " + estPalindrome(phrase));

		char[] copie = {'b', 'o', 'n', 'j', 'o', 'u', 'r', ' ', 'a', 'd', 'a'};
		enMajuscules(copie);
		afficherMot(copie);

		System.out.print("Chiffré (3)   : ");
		chiffrer(phrase, 3);
		afficherMot(phrase);   // erqmrxu dgd
		System.out.print("Déchiffré (3) : ");
		dechiffrer(phrase, 3);
		afficherMot(phrase);   // bonjour ada

		char[] alphabetFin = {'x', 'y', 'z'};
		chiffrer(alphabetFin, 3);
		System.out.print("xyz chiffré   : ");
		afficherMot(alphabetFin);   // abc

		// Bonus
		int[] compteurs = compterLettres(phrase);
		for (int i = 0; i < compteurs.length; i++) {
			if (compteurs[i] > 0) {
				System.out.println((char) ('a' + i) + " : " + compteurs[i]);
			}
		}
	}
}
