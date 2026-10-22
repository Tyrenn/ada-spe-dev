package com.ada.spedev.guillaume.katas.kata10_reductions;

public class ReductionMontantFixe implements Reduction {

	private final double remise;
	private final double seuilMinimum;

	// Exemple : "10 € de remise dès 50 € d'achat" -> new ReductionMontantFixe(10, 50)
	public ReductionMontantFixe(double remise, double seuilMinimum) {
		this.remise = remise;
		this.seuilMinimum = seuilMinimum;
	}

	@Override
	public double appliquer(double montant) {
		if (montant < this.seuilMinimum) {
			return montant;
		}
		return Math.max(0, montant - this.remise);
	}
}
