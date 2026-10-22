package com.ada.spedev.guillaume.katas.kata09_classement;

import java.util.Comparator;

// Comparator : un ordre "extérieur" à la classe. On peut en écrire autant qu'on veut.
public class ComparateurParPseudo implements Comparator<Joueur> {

	@Override
	public int compare(Joueur j1, Joueur j2) {
		return j1.getPseudo().compareToIgnoreCase(j2.getPseudo());
	}
}
