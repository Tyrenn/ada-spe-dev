package com.ada.spedev.guillaume.pokemon.correction.partie3;

// ÉTAPE 9 - Maintenant que les Pokémon ont un type, le maître a une spécialité.
// Il redéfinit capturer() pour ajouter sa règle, puis laisse la mère faire le reste (super).
public class MaitreArene extends DresseurAbstrait {

	private final String nomArene;
	private final String specialite;
	private int nbDefensesReussies = 0;

	public MaitreArene(String nom, String nomArene, String specialite) {
		super(nom);
		this.nomArene = nomArene;
		this.specialite = specialite;
	}

	public String getNomArene() {
		return this.nomArene;
	}

	public int getNbDefensesReussies() {
		return this.nbDefensesReussies;
	}

	@Override
	public int tailleMaxEquipe() {
		return 6;
	}

	@Override
	public String presentation() {
		return "Maître " + getNom() + " de l'arène " + this.nomArene + ", spécialiste " + this.specialite
				+ " (" + this.nbDefensesReussies + " défense(s) réussie(s))";
	}

	@Override
	public void capturer(Pokemon pokemon) throws EquipePleineException {
		if (!pokemon.getType().equals(this.specialite)) {
			throw new IllegalArgumentException(getNom() + " n'accepte que des Pokémon " + this.specialite
					+ ", pas " + pokemon.getNom());
		}
		super.capturer(pokemon);   // doublon et taille maximale : vérifiés par DresseurAbstrait
	}

	@Override
	public String badgeOffert() {
		return "Badge " + this.nomArene;
	}

	@Override
	public Dresseur affronter(Dresseur adversaire) {
		soignerEquipe();
		System.out.println(getNom() + " soigne son équipe avant le combat.");
		Dresseur vainqueur = new Combat(this, adversaire).lancer();
		if (vainqueur == this) {
			this.nbDefensesReussies++;
		}
		return vainqueur;
	}
}
