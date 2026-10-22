package com.ada.spedev.guillaume.katas.kata14_carre_rectangle;

import java.util.List;

import com.ada.spedev.guillaume.katas.kata14_carre_rectangle.piege.Carre;
import com.ada.spedev.guillaume.katas.kata14_carre_rectangle.piege.Rectangle;
import com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution.Forme;

/*
 * KATA 14 - Le carré et le rectangle
 * Notions : le piège du "est un", principe de substitution de Liskov (le L de SOLID),
 *           préférer une interface commune à un héritage "naturel"
 *
 * Principe de Liskov : partout où le code attend un Rectangle, on doit pouvoir donner
 * une sous-classe de Rectangle SANS QUE LE CODE NE SE COMPORTE DIFFÉREMMENT.
 * Le compilateur ne vérifie PAS ce principe : c'est au développeur d'y penser.
 *
 * Ce kata est en deux dossiers :
 *     piege/     : Carre extends Rectangle  (la mauvaise idée)
 *     solution/  : Carre et Rectangle implémentent une interface Forme
 *
 * Consigne :
 *   1) Dans piege/ : une classe Rectangle (mutable, avec setLargeur et setHauteur) et
 *      Carre extends Rectangle. Le Carre redéfinit les setters pour rester carré.
 *   2) Écrire la méthode  static void doublerLaLargeur(Rectangle r)  qui double la largeur
 *      et VÉRIFIE que l'aire a doublé (afficher OK ou BUG).
 *   3) L'appeler avec un Rectangle 2 x 3, puis avec un Carre de côté 2. Que se passe-t-il ?
 *      La méthode doublerLaLargeur est-elle fausse ? Le Carre est-il faux ?
 *
 * Étape 2 : dans solution/, refaire les deux classes SANS héritage entre elles :
 *           une interface Forme (aire, perimetre), et deux classes immuables qui l'implémentent.
 *           doublerLaLargeur n'a plus de sens pour un Carre... et le compilateur l'empêche.
 *
 * Réflexion : "Un carré est un rectangle" est vrai pour des objets mathématiques, qui ne changent
 *             jamais. C'est la MUTABILITÉ (setLargeur) qui casse la relation.
 *             Un autre exemple célèbre : une Autruche est un Oiseau... mais un Oiseau sait voler().
 */
public class Main {

	public static void doublerLaLargeur(Rectangle r) {
		double aireAvant = r.aire();
		r.setLargeur(r.getLargeur() * 2);
		double aireApres = r.aire();
		String verdict = aireApres == aireAvant * 2 ? "OK" : "BUG (attendu " + aireAvant * 2 + ")";
		System.out.println("  " + r + " : aire " + aireAvant + " -> " + aireApres + "  " + verdict);
	}

	public static void main(String[] args) {
		System.out.println("===== Le piège =====");
		doublerLaLargeur(new Rectangle(2, 3));   // aire 6 -> 12  OK
		doublerLaLargeur(new Carre(2));          // aire 4 -> 16  BUG !

		System.out.println("===== La solution =====");
		com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution.Rectangle rectangle =
				new com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution.Rectangle(2, 3);
		com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution.Carre carre =
				new com.ada.spedev.guillaume.katas.kata14_carre_rectangle.solution.Carre(2);

		// On ne peut doubler la largeur que d'un Rectangle : c'est le TYPE qui l'impose
		System.out.println("  " + rectangle + " -> " + rectangle.avecLargeur(4));
		System.out.println("  " + carre + " -> " + carre.avecCote(4));
		// carre.avecLargeur(4);   // ne compile pas, et c'est tant mieux

		// Et tout ce qui est commun reste utilisable de façon polymorphe
		List<Forme> formes = List.of(rectangle, carre, carre.versRectangle());
		for (Forme f : formes) {
			System.out.println("  " + f + " : aire = " + f.aire() + ", périmètre = " + f.perimetre());
		}
	}
}
