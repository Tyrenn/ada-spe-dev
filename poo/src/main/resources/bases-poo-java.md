# La programmation orientée objet en Java

Ce mémo suit l'ordre du cours :

0. **Introduction** : d'où vient la POO, et ce qu'elle apporte.
1. **Les classes** : regrouper des données et le code qui les manipule.
2. **Les interfaces** : décrire ce qu'un objet *sait faire*, indépendamment de ce qu'il *est*.
3. **L'héritage** : un cas particulier (interface + réutilisation de code), puissant mais dangereux.

Chaque section renvoie aux katas correspondants du package `com.ada.spedev.guillaume.katas`.

---

## Introduction - Pourquoi la programmation orientée objet ?

### Le problème de départ

En programmation impérative, un programme est fait de **données** (variables, tableaux) d'un côté, et de
**fonctions** qui les manipulent de l'autre :

```java
double[] soldes = new double[100];
String[] titulaires = new String[100];

static void retirer(double[] soldes, int index, double montant) { ... }
```

Tant que le programme est petit, tout va bien. Mais quand il atteint des dizaines de milliers de lignes :

- **n'importe quelle fonction peut modifier n'importe quelle donnée** : `soldes[3] = -1000000;` est écrit
  quelque part, et personne ne sait où ;
- les données qui vont ensemble (le solde et le titulaire d'un même compte) sont **éparpillées** dans plusieurs tableaux ;
- changer la façon de stocker une donnée oblige à **modifier toutes les fonctions** qui l'utilisent ;
- il devient très difficile de travailler à plusieurs sur le même code sans se marcher dessus.

### Un peu d'histoire

| Date | Langage | Ce qu'il apporte |
|---|---|---|
| 1967 | **Simula 67** (Ole-Johan Dahl et Kristen Nygaard, Norvège) | Conçu pour la **simulation** (files d'attente, trafic...). Invente les **classes** et les **objets** : chaque élément simulé est un objet avec son propre état. |
| années 1970 | **Smalltalk** (Alan Kay, Xerox PARC) | Alan Kay invente l'expression « orienté objet ». Tout est objet, et les objets communiquent en **s'envoyant des messages**. |
| 1985 | **C++** (Bjarne Stroustrup) | Ajoute les objets au langage C : la POO devient massivement utilisée dans l'industrie. |
| 1995 | **Java** (James Gosling, Sun Microsystems) | POO plus simple et plus sûre que C++ (pas de pointeurs, mémoire gérée automatiquement), « write once, run anywhere ». |
| aujourd'hui | C#, Python, Kotlin, Swift, TypeScript, PHP... | Presque tous les langages populaires proposent des objets. |

L'idée d'Alan Kay : un programme devrait ressembler à un ensemble de **petits ordinateurs indépendants**
(les objets) qui coopèrent en s'envoyant des messages, chacun **gardant ses données pour lui**.

### La notion d'objet

Un **objet** regroupe trois choses :

- un **état** : ses données (le solde et le titulaire d'un compte) ;
- un **comportement** : ce qu'on peut lui demander de faire (déposer, retirer) ;
- une **identité** : deux comptes avec le même solde restent deux comptes différents.

On ne manipule plus des données avec des fonctions extérieures : on **demande à l'objet** de faire quelque chose.

```java
// Impératif : une fonction agit SUR des données
retirer(soldes, 3, 50);

// Objet : on demande à l'objet d'agir
compte.retirer(50);
```

C'est l'objet lui-même qui décide s'il accepte la demande (par exemple, refuser un retrait si le solde est insuffisant).

### La notion d'encapsulation

L'**encapsulation**, c'est le fait de **cacher l'état interne** d'un objet et de n'autoriser son accès qu'à travers
ses méthodes. L'objet est une **boîte noire** : on sait ce qu'il fait, pas comment il le fait.

Comparaison avec une voiture : le conducteur utilise un volant, des pédales, un levier de vitesse (l'**interface publique**).
Il n'a pas accès directement à l'injection ou à la boîte de vitesses (l'**état interne**). Le constructeur peut changer
complètement le moteur, le conducteur conduit toujours de la même façon.

Deux conséquences essentielles :

1. **L'objet garantit sa propre cohérence** : si le seul moyen de modifier un solde est `retirer()`, et que `retirer()`
   refuse les retraits trop grands, alors le solde n'est **jamais** négatif. Nulle part dans le programme.
2. **On peut changer l'intérieur sans casser l'extérieur** : stocker le solde en centimes plutôt qu'en euros
   ne change rien pour le code qui appelle `compte.retirer(50)`.

### Les avantages de la POO

- **Modéliser le monde réel** : le code parle le langage du métier (`Compte`, `Client`, `Commande`), il est plus
  facile à lire et à discuter avec des non-développeurs.
- **Fiabilité** : grâce à l'encapsulation, chaque objet protège ses règles (ses *invariants*). Un bug est localisé
  dans une classe, pas éparpillé dans tout le programme.
- **Modularité** : chaque classe a une responsabilité claire. On peut la comprendre, la tester et la modifier
  indépendamment des autres.
- **Travail en équipe** : chacun travaille sur ses classes en ne connaissant que les méthodes publiques des autres.
- **Réutilisation** : une classe bien écrite (`ArrayList`, `String`, `LocalDate`...) est réutilisée dans des milliers
  de programmes.
- **Extensibilité** : grâce aux interfaces et au polymorphisme, on ajoute de nouveaux comportements **en ajoutant
  des classes**, sans modifier le code existant (→ partie 2).
- **Testabilité** : un objet qui reçoit ses dépendances peut être testé avec de faux objets (→ kata 08).

### Les limites (pour être honnête)

- Plus de code « d'organisation » : pour un petit script, la POO est souvent superflue.
- Une mauvaise conception objet (trop de classes, héritages compliqués) peut rendre un programme **plus** difficile
  à comprendre qu'un programme impératif. La partie 3 montre les pièges les plus courants.
- La POO n'est pas la seule approche : la programmation **fonctionnelle** (fonctions pures, données immuables)
  gagne du terrain, et Java en a intégré des idées (lambdas, `record`, objets immuables → kata 03).

---

## Partie 1 - Les classes

### 1.1 Classe et objet

Une **classe** est un plan de construction. Un **objet** (ou **instance**) est construit à partir de ce plan avec `new`.

```java
public class Point {
    double x;          // attributs : l'état de l'objet
    double y;

    public Point(double x, double y) {   // constructeur
        this.x = x;
        this.y = y;
    }

    public double distanceOrigine() {    // méthode d'instance
        return Math.sqrt(x * x + y * y);
    }
}

Point p = new Point(3, 4);
p.distanceOrigine();   // 5.0
```

- Une classe publique par fichier, et le fichier porte le nom de la classe (`Point.java`).
- Le **constructeur** porte le nom de la classe et n'a pas de type de retour.
- `this` désigne l'objet courant. `this.x = x` : l'attribut reçoit la valeur du paramètre.
- Une méthode **sans `static`** est une méthode d'instance : elle s'appelle *sur un objet* (`p.distanceOrigine()`)
  et a accès à ses attributs.

→ **Kata 01**

### 1.2 Une variable objet contient une référence

Exactement comme pour les tableaux du cours impératif :

```java
Point a = new Point(1, 1);
Point b = a;          // b contient la MÊME adresse : un seul objet
b.deplacer(10, 0);
System.out.println(a);   // (11.0, 1.0)
```

- `new` crée un objet ; `=` copie seulement la référence.
- `==` compare les **adresses**, pas le contenu. Pour comparer le contenu : `equals` (→ 1.5).
- Une variable objet peut valoir `null` (aucun objet). Appeler une méthode dessus provoque une `NullPointerException`.

### 1.3 L'encapsulation en pratique

L'encapsulation présentée en introduction se met en œuvre avec les **modificateurs de visibilité** :
on **cache l'état** (`private`) et on n'expose que des **opérations** qui gardent l'objet valide.

```java
public class CompteBancaire {
    private double solde;            // invisible de l'extérieur

    public double getSolde() {       // lecture autorisée
        return solde;
    }

    public boolean retirer(double montant) {
        if (montant > solde) {
            return false;            // l'invariant "solde >= 0" est protégé
        }
        solde -= montant;
        return true;
    }
}
```

| Modificateur | Visible depuis                                   |
|--------------|--------------------------------------------------|
| `private`    | la classe uniquement                             |
| *(rien)*     | les classes du même package                      |
| `protected`  | le package + les sous-classes (→ héritage)       |
| `public`     | partout                                          |

Règles pratiques :

- Attributs **`private`** par défaut, toujours.
- Pas de setter automatique pour chaque attribut : se demander quelle **opération métier** a du sens
  (`deposer` plutôt que `setSolde`).
- `final` sur un attribut : il ne peut être affecté qu'une fois (dans le constructeur).
- Un **invariant** est une règle toujours vraie pour l'objet. Le constructeur doit l'établir, chaque méthode doit le préserver.
  En cas de valeur invalide : `throw new IllegalArgumentException("message");`

→ **Kata 02**

### 1.4 Objets immuables

Un objet **immuable** ne change jamais après sa construction : tous ses attributs sont `final`, et les « modifications »
retournent un **nouvel objet**.

```java
Fraction a = new Fraction(1, 2);
Fraction b = a.plus(new Fraction(1, 3));   // a n'a pas changé, b est un nouvel objet
```

Avantage : on peut partager un objet immuable entre plusieurs variables sans risque. `String` est immuable pour cette raison.

→ **Kata 03**

### 1.5 `toString`, `equals`, `hashCode`

En Java, **tout objet possède automatiquement** quelques méthodes de base, même si on ne les a pas écrites.
Leur comportement par défaut est rarement utile, alors on les **redéfinit** dans nos classes :

| Méthode | Comportement par défaut | Ce qu'on en fait |
|---|---|---|
| `toString()` | affiche quelque chose comme `Point@1b6d3586` | retourner un texte lisible ; appelée automatiquement par `println(objet)` et `"texte" + objet` |
| `equals(Object o)` | identique à `==` (même adresse) | comparer les **valeurs** |
| `hashCode()` | dérivé de l'adresse | **toujours** redéfinie avec `equals` (utilisée par `HashMap`, `HashSet`) |

Pour redéfinir une de ces méthodes, on écrit dans la classe une méthode avec **exactement la même signature**
(même nom, mêmes paramètres, même type de retour), précédée de l'annotation **`@Override`** :

```java
@Override
public String toString() {
    return "(" + x + ", " + y + ")";
}
```

`@Override` n'est pas obligatoire, mais il demande au compilateur de **vérifier** que la méthode existe bien :
une faute de frappe (`tostring`, `equals(Fraction o)`) devient une erreur de compilation au lieu d'un bug silencieux.

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Fraction)) return false;
    Fraction autre = (Fraction) o;
    return numerateur == autre.numerateur && denominateur == autre.denominateur;
}
```

### 1.6 `static` : ce qui appartient à la classe

| | Attribut / méthode d'instance | Attribut / méthode `static` |
|---|---|---|
| Existe | un exemplaire **par objet** | un exemplaire **pour toute la classe** |
| S'utilise | `objet.methode()` | `Classe.methode()` |
| Accès à `this` | oui | non |

```java
public class Adherent {
    private static int prochainNumero = 1;   // partagé par tous les adhérents
    private final int numero;                // propre à chaque adhérent

    public Adherent() {
        numero = prochainNumero;
        prochainNumero++;
    }
}
```

`Math.sqrt`, `Integer.parseInt`, `main`... sont des méthodes `static`. Tout le cours impératif était en `static` !

→ **Kata 04**

### 1.7 Composition et `ArrayList`

Un objet peut **contenir** d'autres objets : une `Bibliotheque` a des `Livre`. C'est la **composition**, la façon la plus
courante (et la plus saine) de construire des programmes objet.

```java
import java.util.ArrayList;
import java.util.List;

List<Livre> livres = new ArrayList<>();
livres.add(livre);
livres.get(0);
livres.size();
livres.remove(livre);
for (Livre l : livres) { ... }
```

Attention : retourner directement une liste interne permet à l'appelant de la modifier. On retourne une **copie**
(`new ArrayList<>(livres)`).

→ **Kata 04**

### 1.8 Les `enum`

Une `enum` est une classe dont **toutes les instances sont connues à l'avance**.

```java
public enum Couleur {
    VERT(30), ORANGE(5), ROUGE(25);

    private final int duree;

    Couleur(int duree) { this.duree = duree; }

    public int getDuree() { return duree; }
}

Couleur c = Couleur.VERT;
for (Couleur x : Couleur.values()) { ... }
```

Bien plus sûr qu'un `String` ou un `int` : une faute de frappe ne compile pas.

→ **Kata 05**

### 1.9 Les exceptions

#### Le principe

Une **exception** est un **objet** qui représente une erreur survenue pendant l'exécution. Quand une exception est
**lancée** (`throw`), la méthode s'arrête immédiatement : les lignes suivantes ne sont pas exécutées.
L'exception **remonte** alors la pile d'appels (la méthode appelante, puis celle qui l'a appelée, etc.)
jusqu'à trouver un bloc `try / catch` qui la **gère**. Si personne ne la gère, le programme s'arrête
et affiche une **stack trace**.

Pourquoi c'est utile pour les objets : un objet qui reçoit une demande impossible (retirer plus que le solde,
dénominateur nul...) doit pouvoir **refuser** et dire **pourquoi**, sans jamais se retrouver dans un état invalide.

#### Lancer une exception : `throw`

```java
public Fraction(int numerateur, int denominateur) {
    if (denominateur == 0) {
        throw new IllegalArgumentException("Le dénominateur ne peut pas être nul");
    }
    ...
}
```

`new` crée l'objet exception (avec un message), `throw` le lance.

#### Attraper une exception : `try / catch / finally`

```java
try {
    int montant = Integer.parseInt(saisie);   // peut lancer une NumberFormatException
    distributeur.retirer(compte, montant);    // peut lancer SoldeInsuffisantException...
    System.out.println("Retrait effectué");   // sautée si une exception est lancée au-dessus
} catch (NumberFormatException e) {
    System.out.println("Ce n'est pas un nombre : " + e.getMessage());
} catch (SoldeInsuffisantException e) {
    System.out.println("Il manque " + e.getManquant() + " €");
} finally {
    System.out.println("Carte rendue");       // exécuté DANS TOUS LES CAS
}
```

- On peut enchaîner **plusieurs `catch`** : le premier qui correspond au type de l'exception est exécuté.
- Le bloc **`finally`** s'exécute toujours, qu'il y ait eu une exception ou non : on y met le « rangement »
  (rendre la carte, fermer un fichier...).
- Méthodes utiles sur une exception : `getMessage()`, `getCause()`, `printStackTrace()`.

#### Laisser remonter : `throws`

Une méthode n'est pas obligée de gérer une exception : elle peut la laisser remonter vers son appelant,
en l'annonçant dans sa signature avec **`throws`** :

```java
public void retirer(Compte compte, int montant) throws SoldeInsuffisantException {
    compte.debiter(montant);   // si debiter lance l'exception, elle traverse retirer sans s'arrêter
}
```

On gère une exception **là où on sait quoi en faire** : le `Compte` sait détecter l'erreur, mais c'est
l'écran du distributeur qui sait comment l'afficher au client.

#### Exceptions vérifiées et non vérifiées

| | Vérifiée (*checked*) | Non vérifiée (*unchecked*) |
|---|---|---|
| Le compilateur oblige à la gérer | **oui** (`try / catch` ou `throws`) | non |
| Représente | un problème **prévisible**, dont l'appelant doit s'occuper | en général une **erreur de programmation** |
| Exemples | `IOException`, `SoldeInsuffisantException` (kata 06) | `IllegalArgumentException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`, `NumberFormatException` |
| Se crée avec | `extends Exception` | `extends RuntimeException` |

#### Créer sa propre exception

Une exception personnalisée est une classe comme les autres : elle peut avoir des **attributs** et des **méthodes**
qui décrivent précisément l'erreur.

```java
public class SoldeInsuffisantException extends Exception {
    private final double manquant;

    public SoldeInsuffisantException(double solde, double montantDemande) {
        super("Solde insuffisant");   // le message, transmis à Exception
        this.manquant = montantDemande - solde;
    }

    public double getManquant() {
        return manquant;
    }
}
```

`extends Exception` et `super(...)` relèvent de l'**héritage** (→ partie 3). Pour l'instant, on les utilise comme une
formule : c'est ce qui fait de notre classe une exception qu'on peut lancer avec `throw`.

#### Transformer une exception

Une exception technique peut être « traduite » en exception plus parlante, en gardant l'originale comme **cause** :

```java
try {
    montant = Integer.parseInt(saisie);
} catch (NumberFormatException e) {
    throw new IllegalArgumentException("\"" + saisie + "\" n'est pas un nombre", e);   // e devient la cause
}
```

#### Bonnes pratiques

- **Ne jamais écrire de `catch` vide** : l'erreur disparaît silencieusement, et le bug devient introuvable.
- Attraper l'exception **la plus précise possible** (`catch (NumberFormatException e)` plutôt que `catch (Exception e)`).
- Écrire des messages qui contiennent **la valeur fautive** : `"Montant invalide : -20"` plutôt que `"Erreur"`.
- Les exceptions sont faites pour les cas **exceptionnels**. Si l'échec est un résultat normal et fréquent
  (« ce livre est-il disponible ? »), un `boolean` ou une valeur de retour est plus simple.
- Une stack trace se lit **de haut en bas** : la première ligne donne le type et le message, les suivantes
  la méthode où l'exception est née, puis toutes les méthodes traversées jusqu'au `main`.

→ **Kata 06**

---

## Partie 2 - Les interfaces

### 2.1 Une interface est un contrat

Une interface déclare **ce qu'un objet sait faire**, sans dire **comment**.

```java
public interface Forme {
    double aire();
    double perimetre();
}

public class Cercle implements Forme {
    private final double rayon;

    public Cercle(double rayon) { this.rayon = rayon; }

    @Override
    public double aire() { return Math.PI * rayon * rayon; }

    @Override
    public double perimetre() { return 2 * Math.PI * rayon; }
}
```

- `implements` : la classe s'engage à fournir **toutes** les méthodes de l'interface, sinon le code ne compile pas.
- `@Override` : demande au compilateur de vérifier qu'on implémente bien une méthode existante.
- On ne peut **pas** faire `new Forme()`. Une interface n'a ni attributs, ni constructeur.

→ **Kata 07**

### 2.2 Le polymorphisme

Une variable de type interface peut contenir **n'importe quel objet** qui l'implémente.

```java
Forme[] formes = { new Cercle(1), new Rectangle(2, 3), new Triangle(3, 4, 5) };

double total = 0;
for (Forme f : formes) {
    total += f.aire();   // c'est la méthode aire() du VRAI objet qui est appelée
}
```

- **Type déclaré** (`Forme`) : ce que le compilateur autorise (`f.aire()` oui, `f.rayon` non).
- **Type réel** (`Cercle`) : le code qui s'exécute.

Conséquence majeure : ajouter une nouvelle forme = **ajouter une classe**. La boucle ci-dessus ne change jamais.

### 2.3 Dépendre d'une abstraction

Une classe qui utilise une interface plutôt qu'une classe concrète devient **indépendante** des détails.

```java
public class ServiceCommande {
    private final Notificateur notificateur;   // une interface

    public ServiceCommande(Notificateur notificateur) {   // on le reçoit de l'extérieur
        this.notificateur = notificateur;
    }
}

new ServiceCommande(new NotificateurEmail(...));
new ServiceCommande(new NotificateurSms());
new ServiceCommande(new NotificateurMemoire());   // un "faux" pour les tests
```

C'est l'**injection de dépendance** : le service ne fait pas `new NotificateurEmail()` lui-même, on lui fournit.
Bénéfices : changer de comportement sans modifier la classe, et **tester** avec de faux objets.

→ **Katas 08, 16**

### 2.4 Les interfaces du JDK

Le JDK est rempli d'interfaces. En les implémentant, nos classes se branchent sur du code existant.

```java
public class Joueur implements Comparable<Joueur> {
    @Override
    public int compareTo(Joueur autre) {
        return Integer.compare(autre.score, score);   // < 0 : this avant autre
    }
}

Collections.sort(joueurs);                     // ordre naturel (Comparable)
joueurs.sort(new ComparateurParAge());         // ordre alternatif (Comparator)
```

Autres interfaces courantes : `List`, `Set`, `Map`, `Iterable`, `Runnable`, `AutoCloseable`...

→ **Kata 09**

### 2.5 Interfaces fonctionnelles et lambdas

Une interface avec **une seule méthode abstraite** peut être implémentée par une **lambda**, sans écrire de classe :

```java
@FunctionalInterface
public interface Reduction {
    double appliquer(double montant);
}

Reduction soldes = montant -> montant / 2;
Comparator<Joueur> parAge = (j1, j2) -> Integer.compare(j1.getAge(), j2.getAge());
```

Le **pattern Stratégie** : un objet délègue une partie de son comportement à un autre objet, interchangeable
(`panier.setReduction(...)`). Il remplace avantageusement les longues chaînes de `if / else if`.

→ **Kata 10**

### 2.6 Plusieurs interfaces et méthodes `default`

Une classe peut implémenter **autant d'interfaces qu'elle veut** (alors qu'elle ne peut hériter que d'une seule classe).

```java
public class Canard implements Volant, Nageur, Marcheur { ... }
```

Une interface peut fournir du code avec une méthode `default`, construite à partir de ses méthodes abstraites :

```java
public interface Volant {
    int altitudeMax();

    default boolean peutSurvoler(int altitude) {
        return altitude < altitudeMax();
    }
}
```

→ **Kata 11**

---

## Partie 3 - L'héritage

### 3.1 L'héritage = une interface + du code

`extends` fait **deux choses à la fois** :

1. la sous-classe **implémente l'interface** de la classe mère (un `CompteCourant` peut être utilisé partout où un `Compte` est attendu) ;
2. la sous-classe **récupère le code** de la classe mère (attributs et méthodes).

Le point 1, les interfaces le font déjà. Le seul vrai apport de l'héritage, c'est le point 2 : **réutiliser du code**.

```java
public class CompteCourant extends Compte {
    private final double decouvert;

    public CompteCourant(String titulaire, double decouvert) {
        super(titulaire);              // constructeur de la classe mère, en première ligne
        this.decouvert = decouvert;
    }

    @Override
    public boolean retirer(double montant) {   // redéfinition
        ...
    }

    @Override
    public String toString() {
        return "[Courant] " + super.toString();   // réutiliser la version de la classe mère
    }
}
```

- Une classe n'a qu'**une seule** classe mère (héritage simple).
- `super(...)` : appel du constructeur parent. `super.methode()` : appel de la version parente.
- `final` sur une classe interdit d'en hériter (`String` est `final`).

→ **Kata 12**

> **Rappel : `toString`, `equals` et `hashCode` (→ 1.5)**
>
> On peut maintenant expliquer pourquoi tout objet possède ces méthodes « automatiquement » :
> en Java, **toutes les classes héritent de la classe `Object`**, même sans écrire `extends`.
>
> ```java
> public class Point { ... }
> // est en réalité
> public class Point extends Object { ... }
> ```
>
> `toString()`, `equals()` et `hashCode()` sont des méthodes de `Object`. Quand on les écrit dans `Point`
> avec `@Override`, on fait exactement la même chose que `CompteCourant` avec `retirer()` : une **redéfinition**
> de la méthode de la classe mère. Et `super.toString()` appellerait la version de `Object` (`Point@1b6d3586`).
>
> C'est aussi pour cela qu'une `List<Object>` peut contenir n'importe quel objet (→ kata 11) :
> tout objet **est un** `Object`.

> **Rappel : les exceptions personnalisées (→ 1.9)**
>
> La « formule » `extends Exception` s'explique maintenant : `SoldeInsuffisantException` **hérite** de la classe
> `Exception`, qui fournit déjà le message, la cause et la stack trace. `super("message")` appelle simplement
> le constructeur d'`Exception`.
>
> Les exceptions sont organisées en arbre d'héritage :
>
> ```
> Throwable
> ├── Error                       (problèmes graves de la JVM : OutOfMemoryError...)
> └── Exception                   (vérifiées)
>     ├── IOException
>     ├── SoldeInsuffisantException
>     └── RuntimeException        (non vérifiées)
>         ├── NullPointerException
>         └── IllegalArgumentException
>             └── NumberFormatException
> ```
>
> D'où deux règles :
>
> - `catch (IllegalArgumentException e)` attrape **aussi** les `NumberFormatException` : une `NumberFormatException`
>   **est une** `IllegalArgumentException` ;
> - quand on enchaîne plusieurs `catch`, il faut mettre **le plus précis d'abord** : un `catch (Exception e)` placé en
>   premier attraperait tout, et le compilateur refuse les `catch` suivants qui ne pourraient plus jamais servir.
>
> C'est l'un des rares endroits où l'héritage est vraiment le bon outil : chaque exception **est réellement un** cas
> particulier de sa classe mère, pour tous ses comportements.

### 3.2 Les classes abstraites

Une classe **abstraite** est à mi-chemin entre une interface et une classe : elle a des attributs et du code,
mais certaines méthodes sont laissées **sans corps** (`abstract`). On ne peut pas l'instancier.

```java
public abstract class Employe implements Payable {
    private final String nom;

    protected Employe(String nom) { this.nom = nom; }

    public abstract double salaireBrut();   // chaque sous-classe DOIT la fournir

    public double salaireNet() {            // écrite une fois, pour tous
        return salaireBrut() * 0.78;
    }
}
```

| | Interface | Classe abstraite | Classe |
|---|---|---|---|
| Méthodes abstraites | oui | oui | non |
| Code (méthodes concrètes) | `default` seulement | oui | oui |
| Attributs | non (constantes seulement) | oui | oui |
| Constructeur | non | oui | oui |
| `new` possible | non | non | oui |
| Combien par classe | autant qu'on veut | **une seule** | **une seule** |

→ **Kata 13**

### 3.3 Les dangers de l'héritage

**Le faux « est un » (principe de Liskov).** Partout où on attend un objet de la classe mère, une sous-classe doit
fonctionner **sans surprise**. « Un carré est un rectangle » est vrai en mathématiques, mais un `Carre extends Rectangle`
mutable casse le code qui fait `setLargeur` en s'attendant à ce que la hauteur ne bouge pas. Le compilateur ne détecte rien.

→ **Kata 14**

**La classe de base fragile.** En héritant, on dépend du **code interne** de la classe mère, pas seulement de ses méthodes
publiques. Exemple : `HashSet.addAll` appelle `add` en interne, donc un compteur qui redéfinit les deux compte en double.

**L'interface trop large.** En héritant, on expose **toutes** les méthodes publiques de la classe mère.
Une `Pile extends ArrayList` permet `add(0, x)` : la règle LIFO n'est plus garantie. (`java.util.Stack extends Vector`
est une erreur historique du JDK.)

**`protected` affaiblit l'encapsulation** : chaque sous-classe peut casser les invariants de la classe mère.

→ **Kata 15**

### 3.4 Préférer la composition

Pour réutiliser du code, on peut presque toujours **contenir** un objet plutôt qu'**en hériter** :

```java
public class Pile<E> {
    private final List<E> elements = new ArrayList<>();   // A UNE liste, n'EST PAS une liste

    public void empiler(E e) { elements.add(e); }
    public E depiler()       { return elements.remove(elements.size() - 1); }
}
```

Règles pratiques :

- Pour du **polymorphisme** → une **interface**.
- Pour **réutiliser du code** → la **composition** d'abord.
- L'**héritage** seulement quand la classe mère est **conçue pour être héritée** (souvent abstraite) et que la relation
  « est un » reste vraie **pour tous les comportements**.

→ **Kata 16** : un jeu complet sans aucun `extends`.
