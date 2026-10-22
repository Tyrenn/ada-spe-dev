package com.ada.spedev.guillaume.katas.kata16_arene;

// Puissant mais limité : 15 dégâts, puis 2 seulement quand il n'y a plus de charges
public class BatonDeFeu implements Arme {

	private int charges;

	public BatonDeFeu(int charges) {
		this.charges = charges;
	}

	@Override
	public String nom() {
		return "bâton de feu (" + this.charges + " charges)";
	}

	@Override
	public int degats(De de) {
		if (this.charges == 0) {
			return 2;
		}
		this.charges--;
		return 15;
	}
}
