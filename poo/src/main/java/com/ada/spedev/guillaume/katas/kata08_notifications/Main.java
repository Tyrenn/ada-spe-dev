package com.ada.spedev.guillaume.katas.kata08_notifications;

import java.util.List;

/*
 * KATA 08 - Les notifications
 * Notions : dépendre d'une abstraction (interface) plutôt que d'une classe concrète,
 *           injection de dépendance par le constructeur, faux objet pour tester, composite
 *
 * Situation : une boutique en ligne doit prévenir ses clients quand une commande est passée.
 * Aujourd'hui par email, demain par SMS, et pendant les tests... surtout pas de vrais emails !
 *
 * Version naïve (à NE PAS faire) :
 *     public class ServiceCommande {
 *         private NotificateurEmail email = new NotificateurEmail(...);   // couplage fort
 *     }
 *   -> pour passer au SMS, il faut modifier ServiceCommande.
 *
 * Consigne :
 *   1) Créer une interface Notificateur avec une méthode  void envoyer(String destinataire, String message);
 *   2) Créer trois implémentations : NotificateurConsole, NotificateurEmail (simulé avec println),
 *      NotificateurSms (tronque les messages à 160 caractères).
 *   3) Créer ServiceCommande qui reçoit un Notificateur dans son CONSTRUCTEUR,
 *      avec les méthodes  passerCommande(String client, double montant) -> int (numéro de commande)
 *      et  expedier(String client, int numeroCommande).
 *   4) Dans le main, créer trois ServiceCommande avec trois notificateurs différents.
 *      Le code de ServiceCommande ne change JAMAIS.
 *
 * Étape 2 : créer un NotificateurMemoire qui n'affiche rien mais garde les messages dans une liste.
 *           S'en servir pour VÉRIFIER que passerCommande envoie bien le bon message.
 *           -> c'est le principe des tests unitaires avec des "fakes".
 *
 * Bonus : NotificateurMultiple, qui contient une liste de Notificateur et envoie à tous.
 *         Remarquez : c'est un Notificateur qui contient des Notificateurs.
 *
 * Question : quelle classe faut-il modifier pour ajouter les notifications Discord ?
 */
public class Main {

	public static void main(String[] args) {
		ServiceCommande parConsole = new ServiceCommande(new NotificateurConsole());
		ServiceCommande parEmail = new ServiceCommande(new NotificateurEmail("boutique@exemple.fr"));
		ServiceCommande parSms = new ServiceCommande(new NotificateurSms());

		parConsole.passerCommande("ada", 42.5);
		int numero = parEmail.passerCommande("alan", 19.99);
		parEmail.expedier("alan", numero);
		parSms.passerCommande("grace", 100);

		System.out.println("----- Étape 2 : vérifier avec un faux notificateur -----");
		NotificateurMemoire memoire = new NotificateurMemoire();
		ServiceCommande service = new ServiceCommande(memoire);
		service.passerCommande("linus", 10);
		service.passerCommande("linus", 20);

		List<String> messages = memoire.getMessagesEnvoyes();
		boolean ok = messages.size() == 2
				&& messages.get(1).equals("linus : Votre commande n°2 de 20.0 € est confirmée.");
		System.out.println(ok ? "TEST OK" : "TEST KO : " + messages);

		System.out.println("----- Bonus : plusieurs canaux à la fois -----");
		Notificateur tous = new NotificateurMultiple(List.of(new NotificateurConsole(), new NotificateurSms()));
		new ServiceCommande(tous).passerCommande("margaret", 75);
	}
}
