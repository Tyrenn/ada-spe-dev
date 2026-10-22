package com.ada.spedev.guillaume.katas.kata13_employes;

public class Commercial extends Employe {

	private final double fixe;
	private final double tauxCommission;
	private double ventesDuMois;

	public Commercial(String nom, double fixe, double tauxCommission) {
		super(nom);
		this.fixe = fixe;
		this.tauxCommission = tauxCommission;
	}

	public void enregistrerVente(double montant) {
		this.ventesDuMois += montant;
	}

	@Override
	public double salaireBrut() {
		return this.fixe + this.ventesDuMois * this.tauxCommission / 100;
	}

	@Override
	public String poste() {
		return "Commercial";
	}
}
