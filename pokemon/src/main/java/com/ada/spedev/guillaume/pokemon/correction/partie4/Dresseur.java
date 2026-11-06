package com.ada.spedev.guillaume.pokemon.correction.partie4;

import java.util.List;

// ÉTAPE 13 - affronter reçoit le dé à utiliser. Ce que TOUT dresseur sait faire, quel que soit son rôle.
// Le combat ne connaîtra que cette interface.
public interface Dresseur {

	String getNom();

	// Une phrase qui présente le dresseur (chaque sorte de dresseur a la sienne)
	String presentation();

	void capturer(Pokemon pokemon) throws EquipePleineException;

	List<Pokemon> getEquipe();

	// Le premier Pokémon de l'équipe qui n'est pas K.O., ou null s'il n'y en a plus
	Pokemon pokemonActif();

	boolean peutEncoreCombattre();

	void soignerEquipe();

	// Le badge gagné en battant ce dresseur, ou null s'il n'en donne pas
	String badgeOffert();

	// Combat contre un autre dresseur : retourne le vainqueur
	Dresseur affronter(Dresseur adversaire, De de);
}
