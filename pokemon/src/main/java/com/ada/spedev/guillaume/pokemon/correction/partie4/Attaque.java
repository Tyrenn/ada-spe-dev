package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 12 - Un objet IMMUABLE : tous les attributs sont final, aucun setter.
// La même attaque "Charge" peut être partagée par TOUS les Pokémon sans risque.
public class Attaque {

	// Attaque connue par tous les Pokémon dès leur création
	public static final Attaque CHARGE = new Attaque("Charge", Type.NORMAL, 40);

	private final String nom;
	private final Type type;
	private final int puissance;

	public Attaque(String nom, Type type, int puissance) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("Une attaque doit avoir un nom");
		}
		if (type == null) {
			throw new IllegalArgumentException("L'attaque " + nom + " doit avoir un type");
		}
		if (puissance <= 0) {
			throw new IllegalArgumentException("Puissance invalide pour " + nom + " : " + puissance);
		}
		this.nom = nom;
		this.type = type;
		this.puissance = puissance;
	}

	public String getNom() {
		return this.nom;
	}

	public Type getType() {
		return this.type;
	}

	public int getPuissance() {
		return this.puissance;
	}

	@Override
	public String toString() {
		return this.nom + " (" + this.type.getLibelle() + ", puissance " + this.puissance + ")";
	}

	// Deux attaques sont égales si elles ont le même nom, sans tenir compte des majuscules
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Attaque)) {
			return false;
		}
		Attaque autre = (Attaque) o;
		return this.nom.equalsIgnoreCase(autre.nom);
	}

	// Contrat : deux attaques égales (equals) doivent avoir le même hashCode.
	// equals ignore les majuscules -> hashCode doit les ignorer aussi.
	@Override
	public int hashCode() {
		return this.nom.toLowerCase().hashCode();
	}
}
