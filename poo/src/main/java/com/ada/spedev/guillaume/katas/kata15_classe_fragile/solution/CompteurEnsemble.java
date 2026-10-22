package com.ada.spedev.guillaume.katas.kata15_classe_fragile.solution;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Composition : CompteurEnsemble A UN HashSet au lieu d'ÊTRE un HashSet.
// On ne dépend plus de la façon dont HashSet est codé à l'intérieur.
public class CompteurEnsemble<E> {

	private final Set<E> ensemble = new HashSet<>();
	private int nombreAjouts = 0;

	public boolean add(E e) {
		this.nombreAjouts++;
		return this.ensemble.add(e);
	}

	public boolean addAll(Collection<? extends E> c) {
		this.nombreAjouts += c.size();
		return this.ensemble.addAll(c);   // appelle add() du HashSet interne, pas le nôtre : aucun double comptage
	}

	public boolean contains(E e) {
		return this.ensemble.contains(e);
	}

	public int size() {
		return this.ensemble.size();
	}

	public int getNombreAjouts() {
		return this.nombreAjouts;
	}
}
