package com.ada.spedev.guillaume.katas.kata09_classement;

import java.util.Comparator;

public class ComparateurParAge implements Comparator<Joueur> {

	@Override
	public int compare(Joueur j1, Joueur j2) {
		return Integer.compare(j1.getAge(), j2.getAge());
	}
}
