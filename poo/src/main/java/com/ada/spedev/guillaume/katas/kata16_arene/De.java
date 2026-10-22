package com.ada.spedev.guillaume.katas.kata16_arene;

// Le hasard est derrière une interface : en jeu on utilise un vrai dé, en test un dé truqué.
@FunctionalInterface
public interface De {

	// Retourne un nombre entre 1 et nbFaces
	int lancer(int nbFaces);
}
