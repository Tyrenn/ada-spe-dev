package com.ada.spedev.guillaume.katas.kata10_reductions;

// Un objet qui "ne fait rien" : évite de tester if (reduction != null) partout
public class AucuneReduction implements Reduction {

	@Override
	public double appliquer(double montant) {
		return montant;
	}
}
