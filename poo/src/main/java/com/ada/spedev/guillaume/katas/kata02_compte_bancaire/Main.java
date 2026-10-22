package com.ada.spedev.guillaume.katas.kata02_compte_bancaire;

/*
 * KATA 02 - Le compte bancaire
 * Notions : encapsulation, private / public, final, getters, invariants, exceptions
 *
 * Problème : avec des attributs publics, n'importe qui peut écrire  compte.solde = -1000000;
 * L'encapsulation consiste à CACHER l'état (private) et à n'exposer que des opérations
 * qui garantissent que l'objet reste toujours valide (un "invariant").
 *
 * Invariant de ce kata : le solde d'un compte n'est JAMAIS négatif.
 *
 * Consigne :
 *   1) Créer une classe CompteBancaire avec : titulaire (String), numero (String), solde (double).
 *      Tous les attributs sont private.
 *   2) Constructeur  CompteBancaire(String titulaire, String numero)  : le solde démarre à 0.
 *   3) Getters  getTitulaire(), getNumero(), getSolde().  PAS de setSolde() : pourquoi ?
 *   4) deposer(double montant) : refuse les montants <= 0
 *      (throw new IllegalArgumentException("message");)
 *   5) retirer(double montant) -> boolean : refuse si le solde est insuffisant (retourne false).
 *   6) virerVers(CompteBancaire destination, double montant) -> boolean
 *
 * Étape 2 : dans le main, essayer d'écrire  compte.solde = 1000;  -> que dit le compilateur ?
 *
 * Étape 3 : le titulaire et le numéro ne doivent jamais changer -> les rendre final.
 *           Que se passe-t-il si on essaie de les modifier dans une méthode ?
 *
 * Bonus : ajouter un historique des opérations (un tableau de String, ou une ArrayList si vous
 *         connaissez déjà) et une méthode afficherHistorique().
 *
 * Squelette :
 *
 *   public class CompteBancaire {
 *       private final String titulaire;
 *       private final String numero;
 *       private double solde;
 *
 *       public CompteBancaire(String titulaire, String numero) { ... }
 *       public double getSolde() { ... }
 *       public void deposer(double montant) { ... }
 *       public boolean retirer(double montant) { ... }
 *       public boolean virerVers(CompteBancaire destination, double montant) { ... }
 *   }
 */
public class Main {

	public static void main(String[] args) {
		CompteBancaire alice = new CompteBancaire("Alice", "FR-001");
		CompteBancaire bob = new CompteBancaire("Bob", "FR-002");

		alice.deposer(100);
		System.out.println(alice);

		System.out.println("Retrait de 30 : " + alice.retirer(30));     // true
		System.out.println("Retrait de 500 : " + alice.retirer(500));   // false, le solde ne bouge pas
		System.out.println(alice);

		System.out.println("Virement de 50 vers Bob : " + alice.virerVers(bob, 50));
		System.out.println(alice);
		System.out.println(bob);

		// alice.solde = 1000;   // ne compile pas : solde has private access in CompteBancaire

		try {
			alice.deposer(-20);
		} catch (IllegalArgumentException e) {
			System.out.println("Refusé : " + e.getMessage());
		}
	}
}
