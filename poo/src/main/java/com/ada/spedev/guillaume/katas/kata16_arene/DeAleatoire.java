package com.ada.spedev.guillaume.katas.kata16_arene;

import java.util.Random;

public class DeAleatoire implements De {

	private final Random random;

	public DeAleatoire() {
		this.random = new Random();
	}

	// Avec une graine fixe, la suite de tirages est toujours la même : pratique pour rejouer un combat
	public DeAleatoire(long graine) {
		this.random = new Random(graine);
	}

	@Override
	public int lancer(int nbFaces) {
		return this.random.nextInt(nbFaces) + 1;
	}
}
