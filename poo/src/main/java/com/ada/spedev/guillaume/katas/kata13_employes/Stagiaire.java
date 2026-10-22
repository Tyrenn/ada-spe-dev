package com.ada.spedev.guillaume.katas.kata13_employes;

public class Stagiaire extends Employe {

	private static final double GRATIFICATION = 650;

	public Stagiaire(String nom) {
		super(nom);
	}

	@Override
	public double salaireBrut() {
		return GRATIFICATION;
	}

	// Pas de cotisations sur une gratification de stage : on redéfinit la méthode concrète
	@Override
	public double salaireNet() {
		return salaireBrut();
	}

	@Override
	public String poste() {
		return "Stagiaire";
	}
}
