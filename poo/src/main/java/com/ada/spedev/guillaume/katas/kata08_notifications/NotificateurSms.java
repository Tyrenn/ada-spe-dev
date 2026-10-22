package com.ada.spedev.guillaume.katas.kata08_notifications;

public class NotificateurSms implements Notificateur {

	private static final int TAILLE_MAX = 160;

	@Override
	public void envoyer(String destinataire, String message) {
		String texte = message;
		if (texte.length() > TAILLE_MAX) {
			texte = texte.substring(0, TAILLE_MAX - 3) + "...";
		}
		System.out.println("[SMS] -> " + destinataire + " : " + texte);
	}
}
