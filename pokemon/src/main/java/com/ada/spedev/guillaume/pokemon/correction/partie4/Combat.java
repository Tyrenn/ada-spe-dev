package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 13 - Le combat reçoit son dé de l'extérieur (injection de dépendance) et le transmet à chaque affrontement.
public class Combat {

	private final Dresseur dresseur1;
	private final Dresseur dresseur2;
	private final De de;

	public Combat(Dresseur dresseur1, Dresseur dresseur2, De de) {
		if (dresseur1 == dresseur2) {
			throw new IllegalArgumentException("Un dresseur ne peut pas s'affronter lui-même");
		}
		this.dresseur1 = dresseur1;
		this.dresseur2 = dresseur2;
		this.de = de;
	}

	public Dresseur lancer() {
		if (!this.dresseur1.peutEncoreCombattre() || !this.dresseur2.peutEncoreCombattre()) {
			throw new IllegalStateException("Les deux dresseurs doivent avoir au moins un Pokémon en état de combattre");
		}
		System.out.println("=== " + this.dresseur1.presentation() + "  VS  " + this.dresseur2.presentation() + " ===");

		while (this.dresseur1.peutEncoreCombattre() && this.dresseur2.peutEncoreCombattre()) {
			Pokemon p1 = this.dresseur1.pokemonActif();
			Pokemon p2 = this.dresseur2.pokemonActif();
			p1.affronter(p2, this.de);
		}

		Dresseur vainqueur = this.dresseur2;
		if (this.dresseur1.peutEncoreCombattre()) {
			vainqueur = this.dresseur1;
		}
		System.out.println(">>> " + vainqueur.getNom() + " remporte le combat !");
		return vainqueur;
	}
}
