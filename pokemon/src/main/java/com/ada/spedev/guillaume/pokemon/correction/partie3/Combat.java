package com.ada.spedev.guillaume.pokemon.correction.partie3;

// (identique à la partie 2) Un combat entre deux dresseurs = une suite d'affrontements entre leurs Pokémon actifs.
// Le combat ne connaît que l'interface Dresseur : il marche pour TOUTES les sortes de dresseurs.
public class Combat {

	private final Dresseur dresseur1;
	private final Dresseur dresseur2;

	public Combat(Dresseur dresseur1, Dresseur dresseur2) {
		if (dresseur1 == dresseur2) {
			throw new IllegalArgumentException("Un dresseur ne peut pas s'affronter lui-même");
		}
		this.dresseur1 = dresseur1;
		this.dresseur2 = dresseur2;
	}

	// Retourne le vainqueur
	public Dresseur lancer() {
		if (!this.dresseur1.peutEncoreCombattre() || !this.dresseur2.peutEncoreCombattre()) {
			throw new IllegalStateException("Les deux dresseurs doivent avoir au moins un Pokémon en état de combattre");
		}
		System.out.println("=== " + this.dresseur1.presentation() + "  VS  " + this.dresseur2.presentation() + " ===");

		while (this.dresseur1.peutEncoreCombattre() && this.dresseur2.peutEncoreCombattre()) {
			Pokemon p1 = this.dresseur1.pokemonActif();
			Pokemon p2 = this.dresseur2.pokemonActif();
			p1.affronter(p2);   // le perdant est K.O. : son dresseur enverra le suivant au prochain tour
		}

		Dresseur vainqueur = this.dresseur2;
		if (this.dresseur1.peutEncoreCombattre()) {
			vainqueur = this.dresseur1;
		}
		System.out.println(">>> " + vainqueur.getNom() + " remporte le combat !");
		return vainqueur;
	}
}
