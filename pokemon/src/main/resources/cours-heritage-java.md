# L'héritage : une interface qui apporte du code

Ce cours prolonge la partie 2 du mémo POO (les interfaces). L'idée centrale :

> **Hériter, c'est implémenter une interface ET récupérer du code déjà écrit.**

On avance par petites marches, de l'interface « pure » jusqu'à l'héritage de classe :

1. l'interface : un contrat, zéro code ;
2. l'interface avec `default` : un contrat + un peu de code, mais sans état ;
3. la classe abstraite : un contrat + du code + **un état** (des attributs) ;
4. la classe concrète dont on hérite : tout est écrit, on ne fait que **spécialiser**.

Le fil rouge : des **instruments de musique**.

---

## 1. Point de départ : une interface

```java
public interface Instrument {
	String nom();
	String jouer();          // retourne le son produit
}

public class Guitare implements Instrument {
	@Override public String nom()   { return "Guitare"; }
	@Override public String jouer() { return "Gling gling"; }
}

public class Batterie implements Instrument {
	@Override public String nom()   { return "Batterie"; }
	@Override public String jouer() { return "Boum tchak"; }
}
```

Le polymorphisme fonctionne déjà :

```java
List<Instrument> orchestre = new ArrayList<>();
orchestre.add(new Guitare());
orchestre.add(new Batterie());
for (Instrument i : orchestre) {
	System.out.println(i.nom() + " : " + i.jouer());
}
```

**Ce que l'interface apporte** : un *type commun*, et donc le polymorphisme.
**Ce qu'elle n'apporte pas** : du code commun. Si tous les instruments doivent avoir un propriétaire,
un état d'accordage, un compteur d'utilisations... chaque classe va **réécrire la même chose**.

---

## 2. Première marche : les méthodes `default`

Une méthode `default` ajoute du code dans l'interface, **construit uniquement à partir des méthodes abstraites** :

```java
public interface Instrument {
	String nom();
	String jouer();

	default String presentation() {
		return nom() + " : " + jouer();
	}
}
```

Limite : une interface **n'a pas d'attributs** (pas d'état). Impossible d'écrire dans l'interface
« chaque instrument a un propriétaire et sait s'il est accordé ». Pour ça, il faut une classe.

---

## 3. Deuxième marche : la classe abstraite

Une classe **abstraite** = une interface + des attributs + du code.

```java
public abstract class InstrumentDeMusique implements Instrument {

	private final String proprietaire;   // un état partagé par TOUS les instruments
	private boolean accorde = false;

	protected InstrumentDeMusique(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getProprietaire() { return proprietaire; }

	public void accorder() { accorde = true; }

	// Méthode abstraite : pas de corps, chaque sous-classe DOIT l'écrire
	protected abstract String sonPur();

	// Méthode concrète écrite UNE fois pour tous, qui s'appuie sur la méthode abstraite
	@Override
	public String jouer() {
		return accorde ? sonPur() : sonPur() + " (faux !)";
	}
}
```

```java
public class Guitare extends InstrumentDeMusique {

	public Guitare(String proprietaire) {
		super(proprietaire);              // OBLIGATOIRE, en première ligne
	}

	@Override public String nom()        { return "Guitare"; }
	@Override protected String sonPur()  { return "Gling gling"; }
}
```

- `abstract class` : on ne peut **pas** faire `new InstrumentDeMusique(...)`.
- `abstract` sur une méthode : pas de corps, les sous-classes concrètes **doivent** la fournir (sinon : erreur de compilation).
- La méthode `jouer()` ci-dessus est un **patron de méthode** (*template method*) : l'algorithme général est
  écrit dans la classe mère, les détails sont délégués aux sous-classes.
- Une classe abstraite peut **implémenter une interface** sans en fournir toutes les méthodes (`nom()` est laissée aux filles).

### Ce que fait réellement `extends`

| `Guitare extends InstrumentDeMusique` | Équivalent avec ce qu'on connaît |
|---|---|
| Une `Guitare` peut être rangée dans une variable `InstrumentDeMusique` | comme `implements` |
| Une `Guitare` possède `proprietaire`, `accorde`, `accorder()`, `jouer()` sans les réécrire | **nouveau** : réutilisation de code |
| Une `Guitare` doit fournir `nom()` et `sonPur()` | comme `implements` : respecter le contrat |

---

## 4. Le constructeur et `super(...)`

Un objet `Guitare` contient **aussi** la partie `InstrumentDeMusique`. Cette partie doit être initialisée
**en premier**, par le constructeur de la classe mère :

```java
public Guitare(String proprietaire, int nbCordes) {
	super(proprietaire);         // 1. construit la partie "InstrumentDeMusique"
	this.nbCordes = nbCordes;    // 2. puis la partie "Guitare"
}
```

- `super(...)` doit être la **première instruction** du constructeur.
- Si on ne l'écrit pas, Java insère `super()` (sans argument) : erreur si la classe mère n'a pas de constructeur sans paramètre.
- Les attributs `private` de la mère sont **présents** dans l'objet, mais **inaccessibles** directement depuis la fille :
  on passe par les méthodes publiques (`getProprietaire()`) — l'encapsulation tient toujours.

---

## 5. Redéfinir une méthode (`@Override`) et appeler `super.methode()`

Une sous-classe peut **remplacer** une méthode héritée :

```java
public class Batterie extends InstrumentDeMusique {
	...
	@Override
	public String jouer() {
		return super.jouer() + " *cymbale*";   // réutilise la version de la mère, puis ajoute
	}
}
```

- **Redéfinition** (*override*) : même signature que dans la mère → c'est la version du **type réel** qui s'exécute.
- **Surcharge** (*overload*) : même nom, **paramètres différents** → deux méthodes distinctes, choisies à la compilation.
- `super.jouer()` : appelle la version de la classe mère (utile pour **compléter** plutôt que tout réécrire).
- `@Override` fait vérifier par le compilateur qu'on redéfinit bien quelque chose.

### Restreindre ce qu'une fille peut faire : `final`

```java
public final String description() { ... }   // aucune sous-classe ne peut la redéfinir
public final class Triangle extends ... { }  // personne ne peut hériter de Triangle
```

Mettre `final` sur une méthode « patron » protège l'algorithme : les filles changent les détails, pas la recette.

---

## 6. `protected` : un accès réservé à la famille

| Modificateur | Classe | Package | Sous-classes | Partout |
|---|---|---|---|---|
| `private` | ✔ | | | |
| *(rien)* | ✔ | ✔ | | |
| `protected` | ✔ | ✔ | ✔ | |
| `public` | ✔ | ✔ | ✔ | ✔ |

`protected` est pratique pour les **méthodes** destinées aux filles (`sonPur()`, les constructeurs de classe abstraite).
Pour les **attributs**, on garde `private` : un attribut `protected` peut être cassé par n'importe quelle sous-classe
(une fille qui met `pv = -50`...).

---

## 7. Polymorphisme avec l'héritage

Rien de nouveau, c'est **exactement** le polymorphisme des interfaces :

```java
InstrumentDeMusique i = new Batterie("Ringo");   // type déclaré : InstrumentDeMusique, type réel : Batterie
i.jouer();      // version de Batterie (type réel)
i.accorder();   // hérité, OK
// i.changerPeau();   ERREUR : le compilateur ne connaît que le type déclaré
```

### Retrouver le type réel : `instanceof`

À éviter le plus possible (c'est souvent le signe qu'une méthode polymorphe manque), mais parfois utile :

```java
if (i instanceof Batterie) {       // l'objet est-il réellement une Batterie ?
	Batterie b = (Batterie) i;     // cast : on le range dans une variable du type réel
	b.changerPeau();
}
```

> Si vous écrivez une suite de `if (x instanceof A) ... else if (x instanceof B) ...`,
> demandez-vous s'il ne faudrait pas plutôt une **méthode redéfinie** dans A et B.

---

## 8. Plusieurs niveaux, une seule mère

```
Object
└── InstrumentDeMusique (abstraite, implements Instrument)
    ├── InstrumentACordes (abstraite)
    │   ├── Guitare
    │   └── Violon
    └── Batterie
```

- Une classe n'a **qu'une seule** classe mère directe (héritage simple), mais **autant d'interfaces** qu'elle veut :
  `class Violon extends InstrumentACordes implements Comparable<Violon>, Transportable`.
- Tout remonte à `Object` : c'est de là que viennent `toString()`, `equals()`, `hashCode()`.

---

## 9. Résumé : de l'interface à l'héritage

| | Interface | Interface + `default` | Classe abstraite | Classe concrète |
|---|---|---|---|---|
| Définit un type (polymorphisme) | ✔ | ✔ | ✔ | ✔ |
| Méthodes sans corps | ✔ | ✔ | ✔ (`abstract`) | ✘ |
| Code partagé | ✘ | ✔ (sans état) | ✔ | ✔ |
| Attributs / constructeur | ✘ | ✘ | ✔ | ✔ |
| `new` possible | ✘ | ✘ | ✘ | ✔ |
| Combien pour une classe | plusieurs | plusieurs | **une seule** | **une seule** |

### Quand choisir quoi ?

1. Je veux juste que des objets différents soient **utilisables de la même façon** → **interface**.
2. Plusieurs classes partagent **le même état et le même algorithme**, et ne diffèrent que par quelques détails
   → **classe abstraite** (qui implémente souvent une interface).
3. Je veux réutiliser le code d'un objet qui n'est **pas vraiment** le même genre de chose
   → **composition** (un attribut), pas d'héritage (rappel : `Pile extends ArrayList` est une mauvaise idée, kata 15).

Le test à se poser : « une `Fille` **est un** `Mère`, **dans tous les cas, pour toutes les méthodes** ? »
Si la réponse est « oui, sauf quand... », préférez l'interface ou la composition (kata 14, le carré et le rectangle).

---

## Mini-exercices (10 minutes)

1. Ajoutez une classe `Violon extends InstrumentDeMusique`. Combien de lignes avez-vous écrites ?
   Combien en auriez-vous écrit avec seulement l'interface `Instrument` ?
2. Que se passe-t-il si `Violon` oublie `sonPur()` ? Et si elle écrit `sonpur()` avec `@Override` ? Sans `@Override` ?
3. Retirez `super(proprietaire)` du constructeur de `Guitare`. Lisez le message d'erreur.
4. Passez `accorde` en `protected` et écrivez une sous-classe qui triche. Remettez-le en `private`.
5. Dans un `main`, mettez une `Guitare` et une `Batterie` dans une `List<Instrument>`. Peut-on appeler `accorder()` ? Pourquoi ?
