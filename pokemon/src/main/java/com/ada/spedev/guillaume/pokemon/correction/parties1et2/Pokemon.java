package com.ada.spedev.guillaume.pokemon.correction.parties1et2;

// ÉTAPES 1 et 2 - Un Pokémon tout simple : un nom, un niveau, une puissance.
// Sa force = niveau x puissance. Quand deux Pokémon s'affrontent, le plus fort gagne.
public class Pokemon {

	private final String nom;
	private int niveau;
	private final int puissance;
	private boolean ko = false;

	public Pokemon(String nom, int niveau, int puissance) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("Un Pokémon doit avoir un nom");
		}
		if (niveau < 1 || niveau > 100) {
			throw new IllegalArgumentException("Niveau invalide pour " + nom + " : " + niveau);
		}
		if (puissance <= 0) {
			throw new IllegalArgumentException("Puissance invalide pour " + nom + " : " + puissance);
		}
		this.nom = nom;
		this.niveau = niveau;
		this.puissance = puissance;
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

	public int getForce() {
		return this.niveau * this.puissance;
	}

	public boolean estKo() {
		return this.ko;
	}

	// Le niveau ne dépasse jamais 100 : c'est l'objet qui protège sa règle
	public void gagnerNiveau() {
		if (this.niveau < 100) {
			this.niveau++;
		}
	}

	public void soigner() {
		this.ko = false;
	}

	// ÉTAPE 2 - Le plus fort gagne (égalité : celui qui attaque, this). Le perdant est K.O., le vainqueur gagne un niveau.
	public Pokemon affronter(Pokemon adversaire) {
		if (adversaire == this) {
			throw new IllegalArgumentException(this.nom + " ne peut pas s'affronter lui-même");
		}
		if (this.ko || adversaire.ko) {
			throw new IllegalStateException("Un Pokémon K.O. ne peut pas combattre");
		}

		Pokemon vainqueur = this;
		Pokemon perdant = adversaire;
		if (adversaire.getForce() > this.getForce()) {
			vainqueur = adversaire;
			perdant = this;
		}
		System.out.println("  " + this.nom + " (force " + this.getForce() + ") affronte " + adversaire.nom
				+ " (force " + adversaire.getForce() + ") : " + vainqueur.nom + " gagne !");

		perdant.ko = true;   // on a le droit : perdant est aussi un Pokemon (private = privé à la CLASSE)
		vainqueur.gagnerNiveau();
		return vainqueur;
	}

	@Override
	public String toString() {
		String texte = this.nom + " (niv. " + this.niveau + ", puissance " + this.puissance + ", force " + getForce() + ")";
		if (this.ko) {
			texte = texte + " [K.O.]";
		}
		return texte;
	}
}
