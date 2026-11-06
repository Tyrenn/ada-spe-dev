package com.ada.spedev.guillaume.pokemon.correction.partie4;

import java.util.ArrayList;
import java.util.List;

// PARTIE 4 - La classe abstraite de la partie 3, enrichie :
//   étape 10 : un numéro unique (static)
//   étape 11 : le type est une enum
//   étape 12 : des attaques
//   étape 13 : un dé
public abstract class Pokemon {

	public static final int NB_MAX_ATTAQUES = 4;

	private static int prochainNumero = 1;   // ÉTAPE 10 - un seul compteur pour toute la classe

	private final int numero;                // un numéro par Pokémon
	private final String nom;
	private int niveau;
	private final int puissance;
	private boolean ko = false;
	private final List<Attaque> attaques = new ArrayList<>();

	protected Pokemon(String nom, int niveau, int puissance) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("Un Pokémon doit avoir un nom");
		}
		if (niveau < 1 || niveau > 100) {
			throw new IllegalArgumentException("Niveau invalide pour " + nom + " : " + niveau);
		}
		if (puissance <= 0) {
			throw new IllegalArgumentException("Puissance invalide pour " + nom + " : " + puissance);
		}
		this.numero = prochainNumero;
		prochainNumero++;
		this.nom = nom;
		this.niveau = niveau;
		this.puissance = puissance;
		this.attaques.add(Attaque.CHARGE);   // tout Pokémon connaît Charge
	}

	// ----- Ce que chaque sous-classe DOIT fournir -----

	public abstract Type getType();

	// ----- Ce que chaque sous-classe PEUT redéfinir -----

	public double sensibiliteA(Type typeAttaque) {
		return 1.0;
	}

	public String getTalent() {
		return "Aucun";
	}

	public int getForce() {
		return this.niveau * this.puissance;
	}

	public void gagnerNiveau() {
		if (this.niveau < 100) {
			this.niveau++;
		}
	}

	// ----- Accesseurs -----

	public int getNumero() {
		return this.numero;
	}

	public String getNom() {
		return this.nom;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public int getPuissance() {
		return this.puissance;
	}

	public boolean estKo() {
		return this.ko;
	}

	public void soigner() {
		this.ko = false;
	}

	// ----- ÉTAPE 12 : les attaques -----

	// Copie : l'appelant ne peut pas ajouter une 5e attaque en contournant apprendre()
	public List<Attaque> getAttaques() {
		return new ArrayList<>(this.attaques);
	}

	public void apprendre(Attaque nouvelle) throws TropDAttaquesException {
		if (this.attaques.contains(nouvelle)) {   // contains() utilise equals() de Attaque
			throw new IllegalArgumentException(this.nom + " connaît déjà " + nouvelle.getNom());
		}
		if (this.attaques.size() >= NB_MAX_ATTAQUES) {
			throw new TropDAttaquesException(this.nom, nouvelle);
		}
		this.attaques.add(nouvelle);
	}

	// Force = force du Pokémon x (puissance de l'attaque / 40) x bonus de même type x sensibilité de l'adversaire
	public int forceContre(Pokemon adversaire, Attaque attaque) {
		double bonusMemeType = 1.0;
		if (attaque.getType() == getType()) {
			bonusMemeType = 1.5;
		}
		double force = getForce() * attaque.getPuissance() / 40.0 * bonusMemeType * adversaire.sensibiliteA(attaque.getType());
		return (int) Math.round(force);
	}

	public Attaque meilleureAttaqueContre(Pokemon adversaire) {
		Attaque meilleure = this.attaques.get(0);
		for (Attaque a : this.attaques) {
			if (forceContre(adversaire, a) > forceContre(adversaire, meilleure)) {
				meilleure = a;
			}
		}
		return meilleure;
	}

	// ----- ÉTAPE 13 : le combat avec un dé -----

	public Pokemon affronter(Pokemon adversaire, De de) {
		if (adversaire == this) {
			throw new IllegalArgumentException(this.nom + " ne peut pas s'affronter lui-même");
		}
		if (this.ko || adversaire.ko) {
			throw new IllegalStateException("Un Pokémon K.O. ne peut pas combattre");
		}

		Attaque monAttaque = meilleureAttaqueContre(adversaire);
		Attaque sonAttaque = adversaire.meilleureAttaqueContre(this);
		int monDe = de.lancer(6);
		int sonDe = de.lancer(6);
		int maForce = forceContre(adversaire, monAttaque) * monDe;
		int saForce = adversaire.forceContre(this, sonAttaque) * sonDe;

		Pokemon vainqueur = this;
		Pokemon perdant = adversaire;
		if (saForce > maForce) {
			vainqueur = adversaire;
			perdant = this;
		}
		System.out.println("  " + this.nom + " lance " + monAttaque.getNom() + " (" + forceContre(adversaire, monAttaque)
				+ " x " + monDe + " = " + maForce + "), " + adversaire.nom + " lance " + sonAttaque.getNom()
				+ " (" + adversaire.forceContre(this, sonAttaque) + " x " + sonDe + " = " + saForce + ") : "
				+ vainqueur.nom + " gagne !");

		perdant.ko = true;
		vainqueur.gagnerNiveau();
		return vainqueur;
	}

	@Override
	public String toString() {
		String texte = "#" + this.numero + " " + this.nom + " (" + getType().getLibelle() + ", niv. " + this.niveau
				+ ", force " + getForce() + ")";
		if (this.ko) {
			texte = texte + " [K.O.]";
		}
		return texte;
	}
}
