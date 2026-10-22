package com.ada.spedev.guillaume.katas.kata16_arene;

// Divise les dégâts par deux
public class ArmureLourde implements Protection {

	@Override
	public int absorber(int degats) {
		return degats / 2;
	}
}
