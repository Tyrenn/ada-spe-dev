package com.ada.spedev.guillaume.katas.kata11_capacites;

public interface Nageur {

	int profondeurMaxMetres();

	default boolean peutPlongerA(int profondeur) {
		return profondeur <= profondeurMaxMetres();
	}
}
