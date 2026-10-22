package com.ada.spedev.guillaume.katas.kata08_notifications;

import java.util.ArrayList;
import java.util.List;

// Un "faux" notificateur qui n'envoie rien : il retient juste les messages.
// Très pratique pour TESTER ServiceCommande sans envoyer de vrais emails.
public class NotificateurMemoire implements Notificateur {

	private final List<String> messagesEnvoyes = new ArrayList<>();

	@Override
	public void envoyer(String destinataire, String message) {
		this.messagesEnvoyes.add(destinataire + " : " + message);
	}

	public List<String> getMessagesEnvoyes() {
		return new ArrayList<>(this.messagesEnvoyes);
	}
}
