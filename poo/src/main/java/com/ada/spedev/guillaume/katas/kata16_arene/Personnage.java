package com.ada.spedev.guillaume.katas.kata16_arene;

// Un Personnage n'hérite de rien : il est COMPOSÉ d'une arme et d'une protection,
// qu'on peut changer en cours de partie. Pas besoin de classes Guerrier, Archer, Mage...
public class Personnage {

	private final String nom;
	private final int pvMax;
	private int pv;
	private Arme arme;
	private Protection protection;

	public Personnage(String nom, int pvMax, Arme arme, Protection protection) {
		this.nom = nom;
		this.pvMax = pvMax;
		this.pv = pvMax;
		this.arme = arme;
		this.protection = protection;
	}

	public String getNom() {
		return this.nom;
	}

	public int getPv() {
		return this.pv;
	}

	public boolean estVivant() {
		return this.pv > 0;
	}

	public void equiper(Arme nouvelleArme) {
		this.arme = nouvelleArme;
	}

	public void equiper(Protection nouvelleProtection) {
		this.protection = nouvelleProtection;
	}

	// Retourne les dégâts réellement subis
	public int recevoir(int degatsBruts) {
		int subis = this.protection.absorber(degatsBruts);
		this.pv = Math.max(0, this.pv - subis);
		return subis;
	}

	public String attaquer(Personnage cible, De de) {
		String nomArme = this.arme.nom();
		int bruts = this.arme.degats(de);
		int subis = cible.recevoir(bruts);
		return this.nom + " frappe " + cible.nom + " avec son " + nomArme + " : " + bruts + " dégâts, "
				+ subis + " subis -> " + cible;
	}

	@Override
	public String toString() {
		return this.nom + " [" + this.pv + "/" + this.pvMax + " PV]";
	}
}
