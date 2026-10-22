package com.ada.spedev.guillaume.katas.kata10_reductions;

// Composition de stratégies : une Reduction faite de deux Reductions, appliquées l'une après l'autre
public class ReductionCumulee implements Reduction {

	private final Reduction premiere;
	private final Reduction seconde;

	public ReductionCumulee(Reduction premiere, Reduction seconde) {
		this.premiere = premiere;
		this.seconde = seconde;
	}

	@Override
	public double appliquer(double montant) {
		return this.seconde.appliquer(this.premiere.appliquer(montant));
	}
}
