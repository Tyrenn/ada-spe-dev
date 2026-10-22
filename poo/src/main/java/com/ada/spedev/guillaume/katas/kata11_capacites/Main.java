package com.ada.spedev.guillaume.katas.kata11_capacites;

import java.util.List;

/*
 * KATA 11 - Les capacités
 * Notions : une classe qui implémente PLUSIEURS interfaces, interface = capacité ("sait faire"),
 *           méthodes default, instanceof
 *
 * Question de départ : essayez de ranger ces êtres dans un arbre de classes (une seule classe mère
 * chacun) : Canard, Avion, Poisson, Plongeur.
 *     Canard -> Oiseau -> Animal ?   Mais un Avion vole aussi, et ce n'est pas un Animal...
 *     Plongeur nage comme un Poisson, mais marche comme un Canard...
 *   -> Un arbre ne suffit pas. Les interfaces décrivent ce qu'un objet SAIT FAIRE,
 *      indépendamment de ce qu'il EST. Et on peut en cumuler autant qu'on veut.
 *
 * Consigne :
 *   1) Trois interfaces :
 *        Volant   : int altitudeMaxMetres();
 *        Nageur   : int profondeurMaxMetres();
 *        Marcheur : double vitesseKmH();
 *   2) Quatre classes :
 *        Canard   implements Volant, Nageur, Marcheur
 *        Avion    implements Volant
 *        Poisson  implements Nageur
 *        Plongeur implements Nageur, Marcheur
 *   3) Écrire  static void meetingAerien(List<Volant> participants)  qui affiche l'altitude de chacun.
 *      Y mettre un Canard ET un Avion : ils n'ont rien en commun... sauf de savoir voler.
 *   4) Écrire  static Nageur meilleurPlongeur(List<Nageur> nageurs).
 *
 * Étape 2 : méthodes default. Ajouter dans Volant :
 *               default boolean peutSurvoler(int altitudeObstacle) { return altitudeObstacle < altitudeMaxMetres(); }
 *           Le Canard et l'Avion ont-ils dû être modifiés ? Faire pareil pour Nageur et Marcheur.
 *
 * Étape 3 : dans une List<Object>, parcourir tous les êtres et afficher leurs capacités
 *           avec  if (x instanceof Volant) { ... }
 *
 * Réflexion : pourquoi est-ce souvent un mauvais signe d'avoir beaucoup de instanceof dans un programme ?
 */
public class Main {

	public static void meetingAerien(List<Volant> participants) {
		System.out.println("Meeting aérien, obstacle : la tour Eiffel (330 m)");
		for (Volant v : participants) {
			System.out.println("  " + v + " monte jusqu'à " + v.altitudeMaxMetres() + " m"
					+ (v.peutSurvoler(330) ? ", passe au-dessus" : ", contourne"));
		}
	}

	public static Nageur meilleurPlongeur(List<Nageur> nageurs) {
		Nageur meilleur = nageurs.get(0);
		for (Nageur n : nageurs) {
			if (n.profondeurMaxMetres() > meilleur.profondeurMaxMetres()) {
				meilleur = n;
			}
		}
		return meilleur;
	}

	public static void main(String[] args) {
		Canard canard = new Canard();
		Avion avion = new Avion("A320");
		Poisson poisson = new Poisson();
		Plongeur plongeur = new Plongeur("Jacques");

		meetingAerien(List.of(canard, avion));

		Nageur champion = meilleurPlongeur(List.of(canard, poisson, plongeur));
		System.out.println("Meilleur plongeur : " + champion);
		System.out.println("Le plongeur peut-il descendre à 30 m ? " + plongeur.peutPlongerA(30));
		System.out.println("Le canard met " + canard.tempsPourParcourirHeures(6) + " h pour faire 6 km à pied");

		// Le MÊME objet canard peut être vu de trois façons différentes
		Volant v = canard;
		Nageur n = canard;
		Marcheur m = canard;
		System.out.println("Canard vu comme Volant : " + v.altitudeMaxMetres() + " m d'altitude");
		System.out.println("Canard vu comme Nageur : " + n.profondeurMaxMetres() + " m de profondeur");
		System.out.println("Canard vu comme Marcheur : " + m.vitesseKmH() + " km/h");

		System.out.println("----- Étape 3 : instanceof -----");
		List<Object> etres = List.of(canard, avion, poisson, plongeur);
		for (Object etre : etres) {
			String capacites = etre + " :";
			if (etre instanceof Volant) {
				capacites += " vole";
			}
			if (etre instanceof Nageur) {
				capacites += " nage";
			}
			if (etre instanceof Marcheur) {
				capacites += " marche";
			}
			System.out.println(capacites);
		}
	}
}
