package com.ada.spedev.guillaume.katas.kata09_classement;

// Comparable<Joueur> est une interface du JDK : en l'implémentant, on donne aux Joueurs un "ordre naturel".
// Collections.sort(), TreeSet, Arrays.sort()... savent alors trier des Joueurs sans rien connaître d'eux.
public class Joueur implements Comparable<Joueur> {

	private final String pseudo;
	private final int score;
	private final int age;

	public Joueur(String pseudo, int score, int age) {
		this.pseudo = pseudo;
		this.score = score;
		this.age = age;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public int getScore() {
		return this.score;
	}

	public int getAge() {
		return this.age;
	}

	// Contrat de compareTo : négatif si this passe AVANT autre, positif si APRÈS, 0 si égalité.
	// Ici : le plus gros score en premier, et à score égal, ordre alphabétique du pseudo.
	@Override
	public int compareTo(Joueur autre) {
		if (this.score != autre.score) {
			return Integer.compare(autre.score, this.score);   // inversé : décroissant
		}
		return this.pseudo.compareTo(autre.pseudo);
	}

	@Override
	public String toString() {
		return this.pseudo + " (" + this.score + " pts, " + this.age + " ans)";
	}
}
