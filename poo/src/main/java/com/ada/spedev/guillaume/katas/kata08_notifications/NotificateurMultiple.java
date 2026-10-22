package com.ada.spedev.guillaume.katas.kata08_notifications;

import java.util.List;

// Un Notificateur fait de plusieurs Notificateurs : vu de l'extérieur, c'est un Notificateur comme un autre.
public class NotificateurMultiple implements Notificateur {

	private final List<Notificateur> notificateurs;

	public NotificateurMultiple(List<Notificateur> notificateurs) {
		this.notificateurs = notificateurs;
	}

	@Override
	public void envoyer(String destinataire, String message) {
		for (Notificateur notificateur : this.notificateurs) {
			notificateur.envoyer(destinataire, message);
		}
	}
}
