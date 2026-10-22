package com.ada.spedev.guillaume.katas.kata13_employes;

// Une facture n'est PAS un employé, mais elle est Payable.
// Si l'entreprise ne raisonnait qu'avec la classe Employe, impossible de la payer avec le même code.
public class Facture implements Payable {

	private final String fournisseur;
	private final double montant;

	public Facture(String fournisseur, double montant) {
		this.fournisseur = fournisseur;
		this.montant = montant;
	}

	@Override
	public double montantMensuel() {
		return this.montant;
	}

	@Override
	public String libelle() {
		return "Facture " + this.fournisseur;
	}
}
