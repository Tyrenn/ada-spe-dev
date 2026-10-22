package com.ada.spedev.guillaume.katas.kata10_reductions;

/*
 * KATA 10 - Les réductions du panier
 * Notions : pattern Stratégie, interface fonctionnelle, lambda, objet "qui ne fait rien" (Null Object),
 *           composer des objets qui implémentent la même interface
 *
 * Situation : le service marketing invente une nouvelle promo chaque semaine.
 * Version naïve :
 *     if (typePromo.equals("POURCENTAGE")) { ... }
 *     else if (typePromo.equals("FIXE")) { ... }
 *     else if ...      // une branche de plus chaque semaine, dans la classe Panier
 *
 * Avec une interface, chaque promo est une classe (ou une lambda), et Panier ne change plus.
 *
 * Consigne :
 *   1) Interface Reduction avec  double appliquer(double montant);
 *   2) Implémentations : AucuneReduction, ReductionPourcentage(pourcentage),
 *      ReductionMontantFixe(remise, seuilMinimum)  ("10 € dès 50 € d'achat").
 *   3) Classe Article (nom, prixUnitaire, quantite) et classe Panier :
 *        ajouter(Article), setReduction(Reduction), sousTotal(), total()
 *      Par défaut, un panier n'a AucuneReduction (pas de null !).
 *   4) Tester le même panier avec plusieurs réductions.
 *
 * Étape 2 : les soldes : -50 % sur tout. Au lieu de créer une classe, utiliser une lambda :
 *               Reduction soldes = montant -> montant / 2;
 *           Pourquoi est-ce possible ? (indice : combien de méthodes dans Reduction ?)
 *
 * Étape 3 : ReductionCumulee(Reduction premiere, Reduction seconde) qui applique les deux.
 *           C'est une Reduction... faite de Reductions.
 *
 * Bonus : "le moins cher des deux" : une Reduction qui prend deux Reductions et garde
 *         le résultat le plus avantageux pour le client (en lambda !).
 */
public class Main {

	public static void main(String[] args) {
		Panier panier = new Panier();
		panier.ajouter(new Article("Clavier", 45, 1));
		panier.ajouter(new Article("Souris", 15, 2));
		panier.ajouter(new Article("Tapis", 5, 1));

		System.out.println("Sous-total : " + panier.sousTotal() + " €");          // 80.0
		System.out.println("Sans réduction : " + panier.total() + " €");          // 80.0

		panier.setReduction(new ReductionPourcentage(10));
		System.out.println("-10 % : " + panier.total() + " €");                   // 72.0

		panier.setReduction(new ReductionMontantFixe(15, 50));
		System.out.println("-15 € dès 50 € : " + panier.total() + " €");          // 65.0

		panier.setReduction(new ReductionMontantFixe(15, 100));
		System.out.println("-15 € dès 100 € : " + panier.total() + " €");         // 80.0

		System.out.println("----- Étape 2 : lambda -----");
		Reduction soldes = montant -> montant / 2;
		panier.setReduction(soldes);
		System.out.println("Soldes -50 % : " + panier.total() + " €");            // 40.0

		System.out.println("----- Étape 3 : cumul -----");
		panier.setReduction(new ReductionCumulee(soldes, new ReductionMontantFixe(5, 0)));
		System.out.println("Soldes puis -5 € : " + panier.total() + " €");        // 35.0

		System.out.println("----- Bonus : la meilleure des deux -----");
		Reduction a = new ReductionPourcentage(20);
		Reduction b = new ReductionMontantFixe(20, 50);
		Reduction meilleure = montant -> Math.min(a.appliquer(montant), b.appliquer(montant));
		panier.setReduction(meilleure);
		System.out.println("Meilleure entre -20 % et -20 € : " + panier.total() + " €");   // 60.0
	}
}
