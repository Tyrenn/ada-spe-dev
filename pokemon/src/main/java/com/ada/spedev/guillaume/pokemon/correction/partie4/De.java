package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 13 - Le hasard derrière une interface : un vrai dé pour jouer, un dé truqué pour vérifier les calculs.
public interface De {

	// Retourne un nombre entre 1 et nbFaces
	int lancer(int nbFaces);
}
