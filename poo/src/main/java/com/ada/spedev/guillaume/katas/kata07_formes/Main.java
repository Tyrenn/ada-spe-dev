package com.ada.spedev.guillaume.katas.kata07_formes;

/*
 * KATA 07 - Les formes géométriques
 * Notions : interface, implements, @Override, polymorphisme,
 *           type déclaré (Forme) vs type réel de l'objet (Cercle, Rectangle...)
 *
 * Une interface est un contrat. Celui qui UTILISE une Forme n'a pas besoin de savoir
 * si c'est un Cercle ou un Rectangle : il sait juste qu'il peut appeler aire() et perimetre().
 *
 * Consigne :
 *   1) Créer une interface Forme avec trois méthodes :  double aire();  double perimetre();  String nom();
 *   2) Créer trois classes qui implémentent Forme : Cercle(rayon), Rectangle(largeur, hauteur),
 *      Triangle(a, b, c)  (aire du triangle : formule de Héron, p = demi-périmètre
 *      aire = racine de p(p-a)(p-b)(p-c) ).
 *   3) Dans le main, créer un tableau  Forme[] formes = { new Cercle(1), new Rectangle(2, 3), ... };
 *      et afficher le nom, l'aire et le périmètre de chacune avec UNE SEULE boucle.
 *   4) Écrire  static double aireTotale(Forme[] formes)  et  static Forme plusGrande(Forme[] formes).
 *
 * Étape 2 : ajouter une nouvelle forme  Carre  (dans son propre fichier).
 *           Combien de lignes faut-il modifier dans aireTotale() et plusGrande() ? Pourquoi ?
 *
 * Étape 3 : peut-on écrire  new Forme()  ? Peut-on écrire  Forme f = new Cercle(2);  ?
 *           Avec f, peut-on accéder au rayon ? Pourquoi ?
 *
 * Bonus : un Triangle avec des côtés impossibles (1, 2, 10) doit être refusé dans le constructeur.
 *
 * Squelette :
 *
 *   public interface Forme {
 *       double aire();
 *       double perimetre();
 *       String nom();
 *   }
 *
 *   public class Cercle implements Forme {
 *       private final double rayon;
 *       ...
 *       @Override
 *       public double aire() { ... }
 *   }
 */
public class Main {

	// Cette méthode ne connaît QUE l'interface Forme : elle marche pour toutes les formes,
	// y compris celles qui seront inventées dans 10 ans.
	public static double aireTotale(Forme[] formes) {
		double total = 0;
		for (Forme forme : formes) {
			total += forme.aire();
		}
		return total;
	}

	public static Forme plusGrande(Forme[] formes) {
		Forme max = formes[0];
		for (Forme forme : formes) {
			if (forme.aire() > max.aire()) {
				max = forme;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Forme[] formes = {
				new Cercle(1),
				new Rectangle(2, 3),
				new Triangle(3, 4, 5),
				new Carre(2.5),
		};

		for (Forme forme : formes) {
			// Polymorphisme : c'est la méthode aire() du VRAI objet qui est appelée
			System.out.printf("%-25s aire = %6.2f   périmètre = %6.2f%n",
					forme.nom(), forme.aire(), forme.perimetre());
		}

		System.out.printf("Aire totale : %.2f%n", aireTotale(formes));
		System.out.println("Plus grande : " + plusGrande(formes).nom());

		System.out.println("----- Étape 3 -----");
		// Forme f0 = new Forme();     // ne compile pas : une interface n'est pas instanciable
		Forme f = new Cercle(2);       // OK : un Cercle EST une Forme
		System.out.println(f.nom());
		// f.rayon;                    // ne compile pas : le type déclaré Forme ne connaît pas "rayon"

		System.out.println("----- Bonus -----");
		try {
			new Triangle(1, 2, 10);
		} catch (IllegalArgumentException e) {
			System.out.println("Refusé : " + e.getMessage());
		}
	}
}
