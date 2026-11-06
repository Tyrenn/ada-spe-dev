package com.ada.spedev.guillaume.pokemon.correction.partie4;

// ÉTAPE 11 - La spécialité est maintenant une enum Type.
// Il redéfinit capturer() pour ajouter sa règle, puis laisse la mère faire le reste (super).
public class MaitreArene extends DresseurAbstrait {

	private final String nomArene;
	private final Type specialite;
	private int nbDefensesReussies = 0;

	public MaitreArene(String nom, String nomArene, Type specialite) {
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
		return "Maître " + getNom() + " de l'arène " + this.nomArene + ", spécialiste " + this.specialite.getLibelle()
				+ " (" + this.nbDefensesReussies + " défense(s) réussie(s))";
	}

	@Override
	public void capturer(Pokemon pokemon) throws EquipePleineException {
		if (pokemon.getType() != this.specialite) {
			throw new IllegalArgumentException(getNom() + " n'accepte que des Pokémon " + this.specialite.getLibelle()
					+ ", pas " + pokemon.getNom());
		}
		super.capturer(pokemon);   // doublon et taille maximale : vérifiés par DresseurAbstrait
	}

	@Override
	public String badgeOffert() {
		return "Badge " + this.nomArene;
	}

	@Override
	public Dresseur affronter(Dresseur adversaire, De de) {
		soignerEquipe();
		System.out.println(getNom() + " soigne son équipe avant le combat.");
		Dresseur vainqueur = new Combat(this, adversaire, de).lancer();
		if (vainqueur == this) {
			this.nbDefensesReussies++;
		}
		return vainqueur;
	}
}
