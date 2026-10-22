package com.ada.spedev.guillaume.katas.kata16_arene;

public class Arene {

	private static final int TOURS_MAX = 50;

	private final De de;

	public Arene(De de) {
		this.de = de;
	}

	// Les deux personnages attaquent à tour de rôle. Retourne le vainqueur (null en cas d'égalité).
	public Personnage combat(Personnage premier, Personnage second) {
		System.out.println("*** " + premier + " contre " + second + " ***");
		Personnage attaquant = premier;
		Personnage defenseur = second;

		for (int tour = 1; tour <= TOURS_MAX; tour++) {
			System.out.println("Tour " + tour + " : " + attaquant.attaquer(defenseur, this.de));
			if (!defenseur.estVivant()) {
				System.out.println(">>> " + attaquant.getNom() + " remporte le combat !");
				return attaquant;
			}
			Personnage temp = attaquant;
			attaquant = defenseur;
			defenseur = temp;
		}
		System.out.println(">>> Égalité après " + TOURS_MAX + " tours");
		return null;
	}
}
