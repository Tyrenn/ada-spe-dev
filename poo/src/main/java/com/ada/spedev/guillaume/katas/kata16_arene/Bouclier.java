package com.ada.spedev.guillaume.katas.kata16_arene;

// Retire 3 points à chaque coup
public class Bouclier implements Protection {

	@Override
	public int absorber(int degats) {
		return Math.max(0, degats - 3);
	}
}
