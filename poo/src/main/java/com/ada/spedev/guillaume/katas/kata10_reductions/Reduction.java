package com.ada.spedev.guillaume.katas.kata10_reductions;

// @FunctionalInterface : l'interface n'a qu'UNE méthode abstraite -> on pourra l'implémenter avec une lambda.
// Le compilateur vérifie qu'on n'en ajoute pas une deuxième par erreur.
@FunctionalInterface
public interface Reduction {

	// Reçoit le montant avant réduction, retourne le montant après réduction
	double appliquer(double montant);
}
