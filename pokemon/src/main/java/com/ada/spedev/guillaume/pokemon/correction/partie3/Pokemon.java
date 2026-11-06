package com.ada.spedev.guillaume.pokemon.correction.partie3;

// ÉTAPE 6 - Pokemon devient une classe ABSTRAITE : on ne crée plus de "Pokémon tout court",
// seulement des PokemonFeu, PokemonEau, PokemonPlante, PokemonElectrique.
// Tout le code commun reste écrit ICI, une seule fois.
public abstract class Pokemon {

	private final String nom;
	private int niveau;
	private final int puissance;
	private boolean ko = false;

	// protected : seules les sous-classes appellent ce constructeur (avec super(...))
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
		this.nom = nom;
		this.niveau = niveau;
		this.puissance = puissance;
	}

	// ----- Ce que chaque sous-classe DOIT fournir -----

	public abstract String getType();

	// ----- ÉTAPE 7 : ce que chaque sous-classe PEUT redéfinir -----

	// Multiplicateur appliqué à la force d'un adversaire du type donné. Par défaut : aucune faiblesse.
	public double sensibiliteA(String typeAdversaire) {
		return 1.0;
	}

	// ----- ÉTAPE 8 : le talent (par défaut aucun) -----

	public String getTalent() {
		return "Aucun";
	}

	// ----- Code commun -----

	public String getNom() {
		return this.nom;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public int getPuissance() {
		return this.puissance;
	}

	// Redéfinie par PokemonFeu (talent Brasier)
	public int getForce() {
		return this.niveau * this.puissance;
	}

	// ÉTAPE 7 - Ma force contre CET adversaire : elle dépend de sa sensibilité à mon type
	public int forceContre(Pokemon adversaire) {
		return (int) Math.round(getForce() * adversaire.sensibiliteA(getType()));
	}

	public boolean estKo() {
		return this.ko;
	}

	// Redéfinie par PokemonEau (talent Croissance)
	public void gagnerNiveau() {
		if (this.niveau < 100) {
			this.niveau++;
		}
	}

	public void soigner() {
		this.ko = false;
	}

	public Pokemon affronter(Pokemon adversaire) {
		if (adversaire == this) {
			throw new IllegalArgumentException(this.nom + " ne peut pas s'affronter lui-même");
		}
		if (this.ko || adversaire.ko) {
			throw new IllegalStateException("Un Pokémon K.O. ne peut pas combattre");
		}

		// Polymorphisme : chaque objet utilise SA version de getType(), sensibiliteA(), getForce()
		int maForce = forceContre(adversaire);
		int saForce = adversaire.forceContre(this);
		Pokemon vainqueur = this;
		Pokemon perdant = adversaire;
		if (saForce > maForce) {
			vainqueur = adversaire;
			perdant = this;
		}
		System.out.println("  " + this.nom + " (force " + maForce + ") affronte " + adversaire.nom
				+ " (force " + saForce + ") : " + vainqueur.nom + " gagne !");

		perdant.ko = true;
		vainqueur.gagnerNiveau();   // la version de la sous-classe si elle la redéfinit
		return vainqueur;
	}

	@Override
	public String toString() {
		// getType() n'est écrite nulle part ici... c'est celle de l'objet réel qui sera appelée
		String texte = this.nom + " (" + getType() + ", niv. " + this.niveau + ", force " + getForce() + ")";
		if (this.ko) {
			texte = texte + " [K.O.]";
		}
		return texte;
	}
}
