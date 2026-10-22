package com.ada.spedev.guillaume.katas.kata06_distributeur;

public class Distributeur {

	private static final int VALEUR_BILLET = 10;

	private int nbBillets;

	public Distributeur(int nbBillets) {
		this.nbBillets = nbBillets;
	}

	public int getMontantDisponible() {
		return this.nbBillets * VALEUR_BILLET;
	}

	// Transforme la saisie du client en montant.
	// Integer.parseInt lance une NumberFormatException (technique) qu'on "traduit" en
	// IllegalArgumentException avec un message compréhensible, en gardant l'originale comme CAUSE.
	public int lireMontant(String saisie) {
		int montant;
		try {
			montant = Integer.parseInt(saisie.trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("\"" + saisie + "\" n'est pas un nombre", e);
		}
		if (montant <= 0) {
			throw new IllegalArgumentException("Le montant doit être positif : " + montant);
		}
		if (montant % VALEUR_BILLET != 0) {
			throw new IllegalArgumentException("Le montant doit être un multiple de " + VALEUR_BILLET + " : " + montant);
		}
		return montant;
	}

	// Cette méthode ne gère PAS la SoldeInsuffisantException lancée par compte.debiter :
	// elle la laisse "remonter" vers son propre appelant, et doit donc l'annoncer dans son throws.
	public void retirer(Compte compte, int montant) throws SoldeInsuffisantException, DistributeurVideException {
		// On vérifie le stock AVANT de débiter : sinon le client serait débité sans recevoir ses billets
		if (montant > getMontantDisponible()) {
			throw new DistributeurVideException(getMontantDisponible());
		}
		compte.debiter(montant);   // si ça lance une exception, la ligne suivante n'est jamais exécutée
		this.nbBillets -= montant / VALEUR_BILLET;
	}
}
