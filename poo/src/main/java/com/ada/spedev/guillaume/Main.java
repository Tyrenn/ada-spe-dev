package com.ada.spedev.guillaume;

/*
 * Programmation Orientée Objet en Java
 *
 * Chaque kata est un package (un dossier) qui contient plusieurs classes
 * et son propre Main.java avec la consigne en en-tête.
 *
 * Partie 1 - Les classes
 *   kata01_point             : classe, attributs, constructeur, this, méthodes, références
 *   kata02_compte_bancaire   : encapsulation, private, getters, invariants
 *   kata03_fraction          : objets immuables, méthodes qui retournent un nouvel objet, equals
 *   kata04_bibliotheque      : composition (des objets qui contiennent des objets), ArrayList, static
 *   kata05_feu_tricolore     : enum, état d'un objet
 *   kata06_distributeur      : exceptions (throw, try / catch / finally, throws, exception personnalisée)
 *
 * Partie 2 - Les interfaces
 *   kata07_formes            : interface, implements, polymorphisme
 *   kata08_notifications     : dépendre d'une abstraction, injection par le constructeur
 *   kata09_classement        : implémenter les interfaces du JDK (Comparable, Comparator)
 *   kata10_reductions        : pattern Stratégie, interface fonctionnelle, lambda
 *   kata11_capacites         : une classe, plusieurs interfaces, méthodes default
 *
 * Partie 3 - L'héritage
 *   kata12_comptes           : extends, super, @Override, protected
 *   kata13_employes          : classe abstraite = interface + code commun
 *   kata14_carre_rectangle   : le piège du "est un" (principe de Liskov)
 *   kata15_classe_fragile    : héritage vs composition
 *
 * Mini-projet
 *   kata16_arene             : tout ensemble
 */
public class Main {



	public static void main(String[] args) {
		InstrumentDeMusique idm = new InstrumentDeMusique("Michel");

		idm.sonPur();
	}
}



public abstract class InstrumentDeMusique {

	private final String proprietaire;   // un état partagé par TOUS les instruments
	private boolean accorde = false;

	protected InstrumentDeMusique(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getProprietaire() { return proprietaire; }

	public void accorder() { accorde = true; }

	// Méthode abstraite : pas de corps, chaque sous-classe DOIT l'écrire
	protected abstract String sonPur();

	// Méthode concrète écrite UNE fois pour tous, qui s'appuie sur la méthode abstraite
	@Override
	public String jouer() {
		return accorde ? sonPur() : sonPur() + " (faux !)";
	}
}