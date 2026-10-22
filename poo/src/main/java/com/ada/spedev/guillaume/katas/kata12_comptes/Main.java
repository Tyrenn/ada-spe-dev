package com.ada.spedev.guillaume.katas.kata12_comptes;

import java.util.List;

/*
 * KATA 12 - Les comptes, version héritage
 * Notions : extends, super(...) dans le constructeur, super.methode(), @Override, protected,
 *           polymorphisme par héritage
 *
 * Idée clé : hériter d'une classe, c'est À LA FOIS
 *     1) implémenter son "interface" (toutes ses méthodes publiques) -> un CompteCourant EST un Compte
 *     2) récupérer son code -> on n'a pas à réécrire deposer(), getSolde()...
 *   Le point 1, les interfaces le font déjà. Le point 2 est le vrai apport de l'héritage
 *   ... et la source de ses dangers (voir katas 14 et 15).
 *
 * Consigne :
 *   1) Classe Compte : titulaire, solde (protected), deposer, retirer (refuse si solde insuffisant),
 *      finDeMois() qui ne fait rien, toString.
 *   2) CompteCourant extends Compte : un découvert autorisé. Redéfinir retirer() pour l'accepter.
 *      En fin de mois, 1 % d'agios si le solde est négatif.
 *   3) CompteEpargne extends Compte : un taux annuel. En fin de mois, ajouter les intérêts
 *      (taux / 12). Un seul retrait par mois : redéfinir retirer() en réutilisant super.retirer(...).
 *   4) Dans une List<Compte>, simuler 12 mois : appeler finDeMois() sur chaque compte sans savoir
 *      de quel type il est.
 *
 * Étape 2 : enlever l'appel  super(titulaire);  du constructeur de CompteCourant. Que dit le compilateur ?
 *
 * Étape 3 : retirer  @Override  et faire une faute de frappe dans le nom (retire au lieu de retirer).
 *           Que se passe-t-il ? Et avec @Override ?
 *
 * Réflexion : CompteCourant modifie directement  solde  grâce à protected.
 *             Que se passerait-il si un développeur écrivait  solde = -1000000;  dans une sous-classe ?
 *             Comparer avec le kata 02 où solde était private.
 */
public class Main {

	public static void main(String[] args) {
		Compte simple = new Compte("Ada");
		CompteCourant courant = new CompteCourant("Alan", 500);
		CompteEpargne livret = new CompteEpargne("Grace", 3);

		simple.deposer(100);
		courant.deposer(100);
		livret.deposer(1000);

		System.out.println("Ada retire 300 : " + simple.retirer(300));       // false
		System.out.println("Alan retire 300 : " + courant.retirer(300));     // true, découvert
		System.out.println("Grace retire 100 : " + livret.retirer(100));     // true
		System.out.println("Grace retire 100 : " + livret.retirer(100));     // false, déjà un retrait ce mois

		// Polymorphisme : la liste ne connaît que Compte, mais c'est le finDeMois() de chaque
		// type RÉEL qui est appelé.
		List<Compte> comptes = List.of(simple, courant, livret);
		for (int mois = 1; mois <= 12; mois++) {
			for (Compte compte : comptes) {
				compte.finDeMois();
			}
		}

		System.out.println("----- Après 12 mois -----");
		for (Compte compte : comptes) {
			System.out.println(compte);
		}
	}
}
