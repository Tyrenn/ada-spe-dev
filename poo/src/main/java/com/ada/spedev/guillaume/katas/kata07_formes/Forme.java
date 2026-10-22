package com.ada.spedev.guillaume.katas.kata07_formes;

// Une interface est un CONTRAT : elle dit CE QUE l'objet sait faire, pas COMMENT.
// Pas d'attributs, pas de constructeur, pas de code (pour l'instant) : juste des signatures.
public interface Forme {

	double aire();

	double perimetre();

	String nom();
}
