package com.ada.spedev.guillaume.katas.kata15_classe_fragile;

import java.util.List;

import com.ada.spedev.guillaume.katas.kata15_classe_fragile.piege.CompteurHashSet;
import com.ada.spedev.guillaume.katas.kata15_classe_fragile.piege.PileHeritee;
import com.ada.spedev.guillaume.katas.kata15_classe_fragile.solution.CompteurEnsemble;
import com.ada.spedev.guillaume.katas.kata15_classe_fragile.solution.Pile;

/*
 * KATA 15 - La classe de base fragile
 * Notions : couplage fort de l'héritage, "préférer la composition à l'héritage",
 *           encapsulation cassée par l'héritage, génériques <E>
 *
 * Quand on hérite d'une classe, on dépend de SON CODE INTERNE (quelle méthode appelle quelle autre),
 * pas seulement de ses méthodes publiques. Si la classe mère change, ou si on se trompe sur son
 * fonctionnement, la classe fille casse... sans qu'on ait touché une ligne de la classe fille.
 *
 * Ce kata est en deux dossiers :
 *     piege/     : CompteurHashSet extends HashSet,   PileHeritee extends ArrayList
 *     solution/  : CompteurEnsemble et Pile qui CONTIENNENT une collection (composition)
 *
 * Consigne - partie 1 : le compteur
 *   1) CompteurHashSet<E> extends HashSet<E> compte le nombre d'éléments qu'on a tenté d'ajouter.
 *      Redéfinir add() (+1) et addAll() (+ taille de la collection).
 *   2) Ajouter 3 éléments avec addAll(List.of("a", "b", "c")). Que vaut getNombreAjouts() ?
 *      On attend 3. Expliquer le résultat.
 *   3) Réécrire en CompteurEnsemble<E> qui a un attribut  private final Set<E> ensemble = new HashSet<>();
 *
 * Consigne - partie 2 : la pile
 *   1) PileHeritee<E> extends ArrayList<E> avec empiler, depiler, sommet.
 *   2) Montrer qu'on peut "tricher" : pile.add(0, x) insère tout en bas, pile.clear() vide tout...
 *   3) Réécrire en Pile<E> qui contient une List<E> privée. Quelles méthodes sont maintenant visibles ?
 *
 * Réflexion : dans les deux cas, on voulait RÉUTILISER DU CODE. L'héritage l'a fait...
 *             mais en exposant tout le reste et en nous liant aux détails internes de la classe mère.
 *             La composition réutilise le code SANS ces inconvénients.
 *             Règle pratique : n'hériter que de classes CONÇUES pour être héritées (comme Employe au kata 13).
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("===== Partie 1 : le compteur =====");
		CompteurHashSet<String> piege = new CompteurHashSet<>();
		piege.addAll(List.of("a", "b", "c"));
		System.out.println("Héritage    : " + piege.getNombreAjouts() + " ajouts comptés (attendu 3)");   // 6 !

		CompteurEnsemble<String> solution = new CompteurEnsemble<>();
		solution.addAll(List.of("a", "b", "c"));
		System.out.println("Composition : " + solution.getNombreAjouts() + " ajouts comptés (attendu 3)");

		System.out.println("===== Partie 2 : la pile =====");
		PileHeritee<String> pileHeritee = new PileHeritee<>();
		pileHeritee.empiler("1er");
		pileHeritee.empiler("2e");
		pileHeritee.add(0, "tricheur");          // méthode héritée d'ArrayList : insertion tout en bas
		pileHeritee.set(1, "écrasé");            // on remplace un élément au milieu
		System.out.println("Pile héritée trafiquée : " + pileHeritee);

		Pile<String> pile = new Pile<>();
		pile.empiler("1er");
		pile.empiler("2e");
		pile.empiler("3e");
		// pile.add(0, "tricheur");              // ne compile pas : Pile n'a pas de méthode add
		System.out.println(pile);
		System.out.println("Dépile : " + pile.depiler());
		System.out.println("Sommet : " + pile.sommet());
		System.out.println(pile);
	}
}
