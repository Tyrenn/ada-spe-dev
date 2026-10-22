package com.ada.spedev.guillaume.katas.kata08_notifications;

public class NotificateurConsole implements Notificateur {

	@Override
	public void envoyer(String destinataire, String message) {
		System.out.println("[CONSOLE] " + destinataire + " : " + message);
	}
}
