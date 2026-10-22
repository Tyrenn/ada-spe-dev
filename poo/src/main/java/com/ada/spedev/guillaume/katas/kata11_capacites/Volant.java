package com.ada.spedev.guillaume.katas.kata11_capacites;

public interface Volant {

	int altitudeMaxMetres();

	// Méthode default : une interface peut fournir du code, construit UNIQUEMENT à partir de ses
	// méthodes abstraites. Toutes les classes qui implémentent Volant en profitent gratuitement.
	default boolean peutSurvoler(int altitudeObstacle) {
		return altitudeObstacle < altitudeMaxMetres();
	}
}
