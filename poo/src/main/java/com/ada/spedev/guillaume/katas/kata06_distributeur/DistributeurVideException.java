package com.ada.spedev.guillaume.katas.kata06_distributeur;

public class DistributeurVideException extends Exception {

	private final int montantDisponible;

	public DistributeurVideException(int montantDisponible) {
		super("Le distributeur ne peut délivrer que " + montantDisponible + " € pour le moment");
		this.montantDisponible = montantDisponible;
	}

	public int getMontantDisponible() {
		return this.montantDisponible;
	}
}
