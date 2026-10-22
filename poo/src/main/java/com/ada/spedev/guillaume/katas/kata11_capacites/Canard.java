package com.ada.spedev.guillaume.katas.kata11_capacites;

// Une classe peut implémenter AUTANT d'interfaces qu'elle veut (alors qu'on ne peut hériter que d'UNE classe)
public class Canard implements Volant, Nageur, Marcheur {

	@Override
	public int altitudeMaxMetres() {
		return 1500;
	}

	@Override
	public int profondeurMaxMetres() {
		return 2;
	}

	@Override
	public double vitesseKmH() {
		return 3;
	}

	@Override
	public String toString() {
		return "Canard";
	}
}
