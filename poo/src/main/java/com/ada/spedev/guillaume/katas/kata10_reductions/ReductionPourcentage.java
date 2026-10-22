package com.ada.spedev.guillaume.katas.kata10_reductions;

public class ReductionPourcentage implements Reduction {

	private final double pourcentage;

	public ReductionPourcentage(double pourcentage) {
		if (pourcentage < 0 || pourcentage > 100) {
			throw new IllegalArgumentException("Pourcentage invalide : " + pourcentage);
		}
		this.pourcentage = pourcentage;
	}

	@Override
	public double appliquer(double montant) {
		return montant * (1 - this.pourcentage / 100);
	}
}
