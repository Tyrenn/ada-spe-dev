package com.ada.spedev.guillaume.katas.kata16_arene;

// Irrégulier : 1d10, et un coup critique (dégâts x3) sur un 10
public class Arc implements Arme {

	@Override
	public String nom() {
		return "arc";
	}

	@Override
	public int degats(De de) {
		int tirage = de.lancer(10);
		if (tirage == 10) {
			return tirage * 3;
		}
		return tirage;
	}
}
