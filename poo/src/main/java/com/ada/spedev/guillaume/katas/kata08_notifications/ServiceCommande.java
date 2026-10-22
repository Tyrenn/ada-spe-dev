package com.ada.spedev.guillaume.katas.kata08_notifications;

// ServiceCommande dépend de l'INTERFACE Notificateur, jamais d'une implémentation précise.
// On lui donne son notificateur par le constructeur ("injection de dépendance").
public class ServiceCommande {

	private final Notificateur notificateur;
	private int prochainNumero = 1;

	public ServiceCommande(Notificateur notificateur) {
		this.notificateur = notificateur;
	}

	public int passerCommande(String client, double montant) {
		int numero = this.prochainNumero;
		this.prochainNumero++;
		this.notificateur.envoyer(client, "Votre commande n°" + numero + " de " + montant + " € est confirmée.");
		return numero;
	}

	public void expedier(String client, int numeroCommande) {
		this.notificateur.envoyer(client, "Votre commande n°" + numeroCommande + " a été expédiée.");
	}
}
