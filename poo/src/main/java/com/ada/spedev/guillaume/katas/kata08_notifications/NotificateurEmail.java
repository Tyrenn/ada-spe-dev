package com.ada.spedev.guillaume.katas.kata08_notifications;

// Simulation : dans la vraie vie, cette classe parlerait à un serveur SMTP
public class NotificateurEmail implements Notificateur {

	private final String expediteur;

	public NotificateurEmail(String expediteur) {
		this.expediteur = expediteur;
	}

	@Override
	public void envoyer(String destinataire, String message) {
		System.out.println("[EMAIL] De : " + this.expediteur);
		System.out.println("        À  : " + destinataire + "@exemple.fr");
		System.out.println("        " + message);
	}
}
