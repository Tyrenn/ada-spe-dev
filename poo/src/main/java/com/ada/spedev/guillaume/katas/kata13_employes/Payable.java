package com.ada.spedev.guillaume.katas.kata13_employes;

// Tout ce que l'entreprise doit payer chaque mois : des employés, mais aussi des factures !
public interface Payable {

	double montantMensuel();

	String libelle();
}
