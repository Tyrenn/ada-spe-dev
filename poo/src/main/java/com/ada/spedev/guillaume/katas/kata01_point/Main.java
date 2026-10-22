package com.ada.spedev.guillaume.katas.kata01_point;

/*
 * KATA 01 - Le Point
 * Notions : classe, attributs, constructeur, this, méthodes d'instance, new, toString,
 *           une variable objet contient une RÉFÉRENCE (comme les tableaux !)
 *
 * Une classe est un "plan de construction". Un objet est une instance construite à partir de ce plan.
 *   Classe Point  -> le moule
 *   new Point(...) -> un gâteau sorti du moule
 *
 * Consigne :
 *   1) Créer une classe Point (dans Point.java) avec deux attributs x et y (double).
 *   2) Ajouter un constructeur Point(double x, double y).
 *   3) Ajouter une méthode  deplacer(double dx, double dy)  qui modifie le point.
 *   4) Ajouter  distanceOrigine()  et  distance(Point autre).
 *   5) Ajouter  toString()  pour afficher le point sous la forme "(x, y)".
 *   6) Dans le main : créer deux points, les afficher, déplacer l'un, calculer leur distance.
 *
 * Étape 2 : prédire ce qu'affiche le code ci-dessous AVANT de l'exécuter.
 *
 *     Point a = new Point(1, 1);
 *     Point b = a;
 *     b.deplacer(10, 0);
 *     System.out.println(a);
 *
 *   -> Faire le schéma mémoire (comme pour les tableaux) : combien d'objets Point existent ?
 *
 * Étape 3 : que vaut  new Point(1, 2) == new Point(1, 2)  ? Pourquoi ?
 *
 * Bonus :
 *   - un deuxième constructeur  Point()  qui crée l'origine (utiliser this(...))
 *   - milieu(Point autre) qui retourne un NOUVEAU Point
 *
 * Squelette :
 *
 *   public class Point {
 *       double x;
 *       double y;
 *
 *       public Point(double x, double y) { ... }
 *       public void deplacer(double dx, double dy) { ... }
 *       public double distanceOrigine() { ... }
 *       public double distance(Point autre) { ... }
 *       public String toString() { ... }
 *   }
 */
public class Main {

	public static void main(String[] args) {
		Point a = new Point(3, 4);
		Point b = new Point(-1, 1);

		System.out.println("a = " + a + ", b = " + b);
		System.out.println("Distance de a à l'origine : " + a.distanceOrigine());   // 5.0
		System.out.println("Distance a -> b : " + a.distance(b));                    // 5.0

		b.deplacer(1, -1);
		System.out.println("b après déplacement : " + b);                            // (0.0, 0.0)

		System.out.println("----- Étape 2 : références -----");
		Point p1 = new Point(1, 1);
		Point p2 = p1;            // p2 contient la MÊME adresse que p1 : un seul objet !
		p2.deplacer(10, 0);
		System.out.println("p1 = " + p1);   // (11.0, 1.0) : p1 a "bougé" aussi

		System.out.println("----- Étape 3 : == compare les adresses -----");
		Point q1 = new Point(1, 2);
		Point q2 = new Point(1, 2);
		System.out.println("q1 == q2 ? " + (q1 == q2));   // false : deux objets différents en mémoire
		System.out.println("q1 == q1 ? " + (q1 == q1));   // true

		System.out.println("----- Bonus -----");
		System.out.println("Origine : " + new Point());
		System.out.println("Milieu de a et (5, 0) : " + a.milieu(new Point(5, 0)));
	}
}
