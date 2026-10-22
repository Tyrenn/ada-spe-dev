package com.ada.spedev.guillaume.katas.kata14_carre_rectangle.piege;

// "Un carré EST un rectangle" : vrai en géométrie... faux en programmation !
public class Carre extends Rectangle {

	public Carre(double cote) {
		super(cote, cote);
	}

	// Pour rester un carré, on est obligé de modifier les deux côtés.
	// Le code compile, mais on vient de rompre le contrat de Rectangle.
	@Override
	public void setLargeur(double largeur) {
		this.largeur = largeur;
		this.hauteur = largeur;
	}

	@Override
	public void setHauteur(double hauteur) {
		this.largeur = hauteur;
		this.hauteur = hauteur;
	}
}
