package com.ada.spedev.guillaume.katas.kata16_arene;

// Régulière : 6 + 1d4
public class Epee implements Arme {

	@Override
	public String nom() {
		return "épée";
	}

	@Override
	public int degats(De de) {
		return 6 + de.lancer(4);
	}
}
