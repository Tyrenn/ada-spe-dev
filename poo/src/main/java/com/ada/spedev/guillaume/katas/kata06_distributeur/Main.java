package com.ada.spedev.guillaume.katas.kata06_distributeur;

/*
 * KATA 06 - Le distributeur de billets
 * Notions : exception, throw, try / catch / finally, plusieurs catch, throws,
 *           propagation dans la pile d'appels, exception vérifiée (checked) ou non (unchecked),
 *           exception personnalisée, cause d'une exception, lire une stack trace
 *
 * Une exception est un OBJET qui représente une erreur. Quand on la lance (throw), l'exécution
 * de la méthode s'arrête immédiatement et l'exception "remonte" les appels jusqu'à trouver
 * un try / catch qui la gère. Si personne ne la gère, le programme s'arrête.
 *
 * Consigne :
 *   1) Échauffement : que fait  Integer.parseInt("abc")  ? L'entourer d'un try / catch
 *      qui affiche  e.getMessage().
 *   2) Classe Compte (titulaire, solde) avec  debiter(double montant) throws SoldeInsuffisantException.
 *      Créer la classe SoldeInsuffisantException (avec la formule magique  extends Exception,
 *      expliquée dans la partie héritage) qui retient le solde et le montant demandé,
 *      et propose une méthode  getManquant().
 *   3) Classe Distributeur (un stock de billets de 10 €) avec :
 *        lireMontant(String saisie) -> int : refuse ce qui n'est pas un nombre, les montants <= 0
 *                                            et ceux qui ne sont pas multiples de 10
 *                                            (IllegalArgumentException)
 *        retirer(Compte, int montant) throws SoldeInsuffisantException, DistributeurVideException
 *   4) Dans le main, simuler une série de saisies du client. Pour chacune :
 *        try { lire le montant, retirer }
 *        catch (...) { un message différent selon l'erreur }
 *        finally { "Carte rendue" : la carte doit TOUJOURS être rendue, même en cas d'erreur }
 *
 * Étape 2 : enlever  throws SoldeInsuffisantException  de la méthode retirer du Distributeur.
 *           Que dit le compilateur ? Et si on enlève  throws  de IllegalArgumentException... il n'y en a pas : pourquoi ?
 *           (indice : IllegalArgumentException est une exception NON vérifiée)
 *
 * Étape 3 : dans lireMontant, quand Integer.parseInt échoue, relancer une IllegalArgumentException
 *           avec un message clair en gardant l'exception d'origine comme cause :
 *               throw new IllegalArgumentException("message", e);
 *           Afficher la cause dans le main avec  e.getCause().
 *
 * Étape 4 : décommenter la ligne "STACK TRACE" à la fin du main et lancer le programme.
 *           Lire la stack trace de haut en bas : où l'exception est-elle née ? quelles méthodes a-t-elle traversées ?
 *
 * Réflexion : au kata 02, retirer() retournait un boolean. Qu'apporte une exception ici ?
 *             Quand vaut-il mieux garder un boolean ?
 *
 * Bonus : un compteur d'opérations réussies et refusées, affiché à la fin.
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("===== Échauffement =====");
		try {
			int nombre = Integer.parseInt("abc");
			System.out.println("Jamais affiché : " + nombre);   // l'exception saute cette ligne
		} catch (NumberFormatException e) {
			System.out.println("Erreur attrapée : " + e.getMessage());
		}

		System.out.println();
		System.out.println("===== Au distributeur =====");
		Compte compte = new Compte("Ada", 200);
		Distributeur distributeur = new Distributeur(40);   // 400 € en billets de 10

		String[] saisies = { "50", "abc", "-20", "35", "", "200", "500", "100" };
		int reussis = 0;
		int refuses = 0;

		for (String saisie : saisies) {
			System.out.println("> Saisie : \"" + saisie + "\"");
			try {
				int montant = distributeur.lireMontant(saisie);
				distributeur.retirer(compte, montant);
				System.out.println("  Retrait de " + montant + " € effectué. Solde : " + compte.getSolde() + " €");
				reussis++;
			} catch (IllegalArgumentException e) {
				System.out.println("  Saisie refusée : " + e.getMessage());
				if (e.getCause() != null) {
					System.out.println("    (cause technique : " + e.getCause() + ")");
				}
				refuses++;
			} catch (SoldeInsuffisantException e) {
				System.out.println("  " + e.getMessage() + " -> il manque " + e.getManquant() + " €");
				refuses++;
			} catch (DistributeurVideException e) {
				System.out.println("  " + e.getMessage());
				refuses++;
			} finally {
				System.out.println("  Carte rendue.");
			}
		}

		System.out.println();
		System.out.println("Bilan : " + reussis + " retrait(s) réussi(s), " + refuses + " refusé(s)");
		System.out.println(compte + ", distributeur : " + distributeur.getMontantDisponible() + " €");

		// STACK TRACE : décommenter pour voir le programme s'arrêter sur une exception non gérée
		// distributeur.lireMontant("douze");
	}
}
