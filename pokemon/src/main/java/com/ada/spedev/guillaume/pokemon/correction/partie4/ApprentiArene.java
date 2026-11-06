package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 9 - L'apprenti : 3 Pokémon maximum. Il garde badgeOffert() de la mère (pas de badge).
public class ApprentiArene extends DresseurAbstrait {

	private final String nomArene;

	public ApprentiArene(String nom, String nomArene) {
		super(nom);
		this.nomArene = nomArene;
	}

	public String getNomArene() {
		return this.nomArene;
	}

	@Override
	public int tailleMaxEquipe() {
		return 3;
	}

	@Override
	public String presentation() {
		return "Apprenti " + getNom() + " de l'arène " + this.nomArene;
	}

	@Override
	public Dresseur affronter(Dresseur adversaire, De de) {
		Dresseur vainqueur = new Combat(this, adversaire, de).lancer();
		if (vainqueur != this) {
			System.out.println(getNom() + " : \"Bien joué ! Le maître de l'arène " + this.nomArene + " t'attend.\"");
		}
		return vainqueur;
	}
}
