package com.ada.spedev.guillaume.katas.kata15_classe_fragile.piege;

import java.util.Collection;
import java.util.HashSet;

// Objectif : un HashSet qui compte combien de fois on a essayé d'ajouter un élément.
// Exemple célèbre tiré du livre "Effective Java" (Joshua Bloch).
public class CompteurHashSet<E> extends HashSet<E> {

	private int nombreAjouts = 0;

	@Override
	public boolean add(E e) {
		this.nombreAjouts++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		this.nombreAjouts += c.size();
		return super.addAll(c);   // PIÈGE : en interne, HashSet.addAll appelle... this.add() pour chaque élément !
	}

	public int getNombreAjouts() {
		return this.nombreAjouts;
	}
}
