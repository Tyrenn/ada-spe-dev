package com.ada.spedev.guillaume.katas.kata11_capacites;

public interface Marcheur {

	double vitesseKmH();

	default double tempsPourParcourirHeures(double distanceKm) {
		return distanceKm / vitesseKmH();
	}
}
