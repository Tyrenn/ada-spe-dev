package com.ada.spedev.guillaume.katas.kata15_classe_fragile.piege;

import java.util.ArrayList;

// Une pile (LIFO : dernier entré, premier sorti) construite en héritant d'ArrayList.
// Le JDK a fait exactement cette erreur : java.util.Stack hérite de Vector.
public class PileHeritee<E> extends ArrayList<E> {

	public void empiler(E e) {
		add(e);
	}

	public E depiler() {
		return remove(size() - 1);
	}

	public E sommet() {
		return get(size() - 1);
	}

	// Problème : on hérite AUSSI de add(int, E), remove(int), set(int, E), clear(), sort()...
	// N'importe qui peut insérer au milieu de la pile : la règle LIFO n'est plus garantie.
}
