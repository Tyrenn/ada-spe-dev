package com.ada.spedev.guillaume.katas.kata15_classe_fragile.solution;

import java.util.ArrayList;
import java.util.List;

// La liste est un détail d'implémentation, cachée en private.
// On n'expose QUE les opérations d'une pile : impossible de tricher.
public class Pile<E> {

	private final List<E> elements = new ArrayList<>();

	public void empiler(E e) {
		this.elements.add(e);
	}

	public E depiler() {
		if (estVide()) {
			throw new IllegalStateException("La pile est vide");
		}
		return this.elements.remove(this.elements.size() - 1);
	}

	public E sommet() {
		if (estVide()) {
			throw new IllegalStateException("La pile est vide");
		}
		return this.elements.get(this.elements.size() - 1);
	}

	public boolean estVide() {
		return this.elements.isEmpty();
	}

	public int taille() {
		return this.elements.size();
	}

	@Override
	public String toString() {
		return "Pile" + this.elements + " <- sommet";
	}
}
