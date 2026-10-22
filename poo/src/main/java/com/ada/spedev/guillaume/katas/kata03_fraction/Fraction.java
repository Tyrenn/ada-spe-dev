package com.ada.spedev.guillaume.katas.kata03_fraction;

// Classe immuable : tous les attributs sont final, aucune méthode ne modifie l'objet.
// Les opérations retournent une NOUVELLE Fraction (comme String.toUpperCase() retourne une nouvelle String).
public class Fraction {

	private final int numerateur;
	private final int denominateur;

	public Fraction(int numerateur, int denominateur) {
		if (denominateur == 0) {
			throw new IllegalArgumentException("Le dénominateur ne peut pas être nul");
		}
		// On garde le signe au numérateur : 1/-2 devient -1/2
		if (denominateur < 0) {
			numerateur = -numerateur;
			denominateur = -denominateur;
		}
		// On simplifie dès la construction : 2/4 est stocké comme 1/2
		int diviseur = pgcd(Math.abs(numerateur), denominateur);
		this.numerateur = numerateur / diviseur;
		this.denominateur = denominateur / diviseur;
	}

	public Fraction(int entier) {
		this(entier, 1);
	}

	// static : cette méthode n'utilise aucun attribut, elle n'a pas besoin d'un objet Fraction.
	// private : c'est un détail interne de la classe.
	private static int pgcd(int a, int b) {
		while (b != 0) {
			int reste = a % b;
			a = b;
			b = reste;
		}
		return a == 0 ? 1 : a;
	}

	public int getNumerateur() {
		return this.numerateur;
	}

	public int getDenominateur() {
		return this.denominateur;
	}

	public Fraction plus(Fraction autre) {
		return new Fraction(this.numerateur * autre.denominateur + autre.numerateur * this.denominateur,
				this.denominateur * autre.denominateur);
	}

	public Fraction moins(Fraction autre) {
		return plus(autre.oppose());
	}

	public Fraction fois(Fraction autre) {
		return new Fraction(this.numerateur * autre.numerateur, this.denominateur * autre.denominateur);
	}

	public Fraction diviseePar(Fraction autre) {
		return fois(autre.inverse());
	}

	public Fraction oppose() {
		return new Fraction(-this.numerateur, this.denominateur);
	}

	public Fraction inverse() {
		return new Fraction(this.denominateur, this.numerateur);
	}

	public double versDouble() {
		return (double) this.numerateur / this.denominateur;
	}

	// equals : "même valeur" (contrairement à == qui veut dire "même objet")
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Fraction)) {
			return false;
		}
		Fraction autre = (Fraction) o;
		return this.numerateur == autre.numerateur && this.denominateur == autre.denominateur;
	}

	// Règle : si on redéfinit equals, on redéfinit hashCode (utilisé par HashMap, HashSet...)
	@Override
	public int hashCode() {
		return 31 * this.numerateur + this.denominateur;
	}

	@Override
	public String toString() {
		if (this.denominateur == 1) {
			return String.valueOf(this.numerateur);
		}
		return this.numerateur + "/" + this.denominateur;
	}
}
