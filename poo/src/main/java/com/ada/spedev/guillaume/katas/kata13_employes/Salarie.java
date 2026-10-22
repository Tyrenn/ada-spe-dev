package com.ada.spedev.guillaume.katas.kata13_employes;

public class Salarie extends Employe {

	private final double salaireAnnuel;

	public Salarie(String nom, double salaireAnnuel) {
		super(nom);
		this.salaireAnnuel = salaireAnnuel;
	}

	@Override
	public double salaireBrut() {
		return this.salaireAnnuel / 12;
	}

	@Override
	public String poste() {
		return "Salarié";
	}
}
