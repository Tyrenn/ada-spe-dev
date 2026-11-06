# TP : Pokémon, dresseurs et arènes

Ce TP reprend les notions du module POO : classes, encapsulation, composition, interfaces, polymorphisme...
et se termine par **l'héritage**.

Il est découpé en **4 parties**. À la fin de chaque partie, vous avez un programme qui fonctionne :

| Partie | Ce qu'on construit | Notions principales |
|---|---|---|
| **1. Les Pokémon** | des Pokémon qui s'affrontent : le plus fort gagne | classe, constructeur, encapsulation, `toString` |
| **2. Les dresseurs** | explorateurs, apprentis et maîtres d'arène qui se défient | **interface**, polymorphisme, composition (`ArrayList`), exception |
| **3. Les types** | Feu, Eau, Plante, Électrique : chaque type a ses forces et ses faiblesses | **classe abstraite**, `extends`, `super`, redéfinition |
| **4. Pour aller plus loin** | numéros, attaques, dés | `static`, `enum`, objet immuable, `equals` / `hashCode`, faux objets |

Un aperçu du résultat de la partie 3 :

```
=== Explorateur Sacha, de Bourg Palette (0 badge(s))  VS  Maître Ondine de l'arène Azuria, spécialiste Eau (0 défense(s) réussie(s)) ===
  Salamèche (force 58) affronte Stari (force 220) : Stari gagne !
  Bulbizarre (force 220) affronte Stari (force 65) : Bulbizarre gagne !
  Bulbizarre (force 242) affronte Staross (force 66) : Bulbizarre gagne !
>>> Sacha remporte le combat !
Sacha obtient le Badge Azuria !
```

## Organisation

- Projet Maven `pokemon`, package `com.ada.spedev.pokemon`.
- **Des fichiers de vérification** sont fournis à côté de ce sujet, dans le dossier `verifications/` :

| Fichier | Étapes |
|---|---|
| `MainPartie1et2.java` | 1 à 5 |
| `MainPartie3.java` | 6 à 9 |
| `MainPartie4.java` | 10 à 13 |

  Copiez le fichier de la partie en cours dans le package `com.ada.spedev.pokemon`. Tout y est commenté :
  quand vous avez terminé une étape, **décommentez son appel dans `main()` et la méthode correspondante**, puis lancez.
  Chaque ligne doit afficher `OK`. Ne recommentez pas les étapes précédentes : elles vous diront si une modification
  a cassé quelque chose.
- Si le fichier ne compile plus après avoir décommenté une étape, c'est en général qu'un nom de méthode, un paramètre
  ou un type de retour ne correspond pas exactement au sujet.
- Les vérifications ne testent pas tout : ajoutez les vôtres, avec la même méthode `verifier` :

```java
verifier("force = 5 x 12 = 60", salameche.getForce() == 60);
```

- Tous les attributs sont **`private`**. Pas de setter « par réflexe » : seulement des méthodes qui ont un sens dans le jeu.
- Chaque étape commence par un bloc **📌 Rappel** : les notions à avoir en tête avant de coder.

---

# Partie 1 - Les Pokémon

Pour commencer, un combat très simple : chaque Pokémon a un **niveau** et une **puissance**.
Sa **force** vaut `niveau × puissance`, et quand deux Pokémon s'affrontent, **le plus fort gagne**.

## Étape 1 - La classe `Pokemon`

> **📌 Rappel - classe, objet, encapsulation**
>
> - Une **classe** est un plan de construction ; un **objet** est construit à partir de ce plan avec `new`.
> - Le **constructeur** porte le nom de la classe, n'a pas de type de retour, et initialise les attributs.
>   `this.niveau = niveau;` : l'attribut reçoit la valeur du paramètre.
> - **Encapsulation** : les attributs sont `private`, et on ne les modifie qu'à travers des méthodes qui
>   **protègent les règles** de l'objet (ici : le niveau reste entre 1 et 100).
> - Le constructeur **refuse** les valeurs invalides :
>   ```java
>   if (niveau < 1 || niveau > 100) {
>   	throw new IllegalArgumentException("Niveau invalide : " + niveau);   // mettez la valeur fautive !
>   }
>   ```
> - `toString()` retourne un texte lisible ; elle est appelée automatiquement par `System.out.println(objet)`.
>   On l'écrit avec `@Override` devant :
>   ```java
>   @Override
>   public String toString() {
>   	return nom + " (niv. " + niveau + ")";
>   }
>   ```

Créez la classe **`Pokemon`** :

| Attribut | Rôle |
|---|---|
| `nom` | ne change jamais (`final`) |
| `niveau` | entre 1 et 100 |
| `puissance` | strictement positive, ne change jamais (`final`) |
| `ko` | `boolean`, `false` au départ |

- Constructeur `Pokemon(String nom, int niveau, int puissance)`, qui lève une `IllegalArgumentException` si le nom est
  vide, le niveau hors de [1, 100] ou la puissance ≤ 0 ;
- des getters : `getNom()`, `getNiveau()`, `getPuissance()`, et `boolean estKo()` ;
- `int getForce()` : retourne `niveau × puissance` (ce n'est pas un attribut : elle est **calculée**) ;
- `void gagnerNiveau()` : ajoute 1 au niveau, **sans jamais dépasser 100** ;
- `toString()` → `Salamèche (niv. 5, puissance 12, force 60)`, suivi de ` [K.O.]` si le Pokémon est K.O.

**À vérifier** :
- `new Pokemon("Salamèche", 5, 12)` a une force de 60 ; après `gagnerNiveau()`, niveau 6 et force 72 ;
- un Pokémon niveau 100 qui gagne un niveau reste niveau 100 ;
- `new Pokemon("Bug", 0, 10)` lève une exception : attrapez-la (`try / catch`) et affichez son message.

**Question** : pourquoi n'a-t-on pas d'attribut `force` ? Que faudrait-il penser à faire à chaque `gagnerNiveau()` si on en avait un ?

---

## Étape 2 - Le premier combat

> **📌 Rappel - des objets qui travaillent ensemble**
>
> - Une méthode peut recevoir **un autre objet** en paramètre : `salameche.affronter(bulbizarre)`.
>   Dans la méthode, `this` est Salamèche, et le paramètre `adversaire` est Bulbizarre.
> - `private` veut dire « privé à la **classe** », pas à l'objet : dans une méthode de `Pokemon`, on peut lire
>   `adversaire.ko`, car `adversaire` est aussi un `Pokemon`.
> - Une variable objet contient une **référence** : `vainqueur = adversaire;` ne copie pas le Pokémon, la variable
>   désigne le **même** objet. Et `==` teste si deux variables désignent le même objet.
> - Une méthode peut **retourner un objet** : ici, le vainqueur.
> - `IllegalStateException` : l'objet n'est pas dans un état qui permet l'action (un Pokémon K.O. ne combat pas).

Ajoutez à `Pokemon` :

- `Pokemon affronter(Pokemon adversaire)` :
  1. `IllegalArgumentException` si `adversaire` est le Pokémon lui-même, `IllegalStateException` si l'un des deux est K.O. ;
  2. le plus fort gagne ; **en cas d'égalité, celui qui attaque (`this`) gagne** ;
  3. le perdant devient K.O., le vainqueur gagne un niveau ;
  4. affiche `Salamèche (force 60) affronte Bulbizarre (force 55) : Salamèche gagne !` et retourne le vainqueur ;
- `void soigner()` : le Pokémon n'est plus K.O.

**À vérifier** :

| Test | Attendu |
|---|---|
| Salamèche (niv. 5, puissance 12) affronte Bulbizarre (niv. 5, puissance 11) | Salamèche gagne (60 contre 55) |
| après ce combat | Bulbizarre est K.O., Salamèche est niveau 6 |
| Salamèche affronte de nouveau Bulbizarre | `IllegalStateException` |
| Bulbizarre est soigné | il n'est plus K.O. |
| Pikachu (niv. 4, puissance 15) affronte Carapuce (niv. 6, puissance 10) | égalité 60 / 60 : Pikachu gagne |

**Petit défi** : créez 4 Pokémon et organisez un mini-tournoi dans le `main` : demi-finales, puis finale entre les
deux vainqueurs (pensez à les soigner ?).

🎉 **Fin de la partie 1** : vos Pokémon s'affrontent !

---

# Partie 2 - Les dresseurs

Il y a trois sortes de dresseurs :

| | Explorateur | Apprenti d'arène | Maître d'arène |
|---|---|---|---|
| Attributs propres | ville d'origine, badges gagnés | nom de son arène | nom de son arène, nombre de défenses réussies |
| Taille de l'équipe | 6 | **3** | 6 |
| Badge offert au vainqueur | aucun | aucun | `"Badge " + nomArene` |
| Façon d'affronter | s'il gagne, récupère le badge de l'adversaire | s'il perd, indique le chemin vers son maître | soigne son équipe **avant** le combat, compte ses défenses réussies |

Ils sont différents... mais un combat doit pouvoir opposer **n'importe lesquels** d'entre eux.
C'est le rôle d'une **interface**.

## Étape 3 - L'interface `Dresseur` et l'explorateur

> **📌 Rappel - interface**
>
> - Une interface décrit **ce qu'un objet sait faire**, pas **comment**. Elle ne contient que des signatures de méthodes :
>   pas d'attributs, pas de constructeur, et on ne peut pas faire `new Dresseur()`.
> - `implements` : la classe s'engage à fournir **toutes** les méthodes de l'interface, sinon elle ne compile pas.
>   Elle peut avoir **en plus** ses propres attributs et méthodes.
> - `@Override` devant chaque méthode : le compilateur vérifie qu'elle correspond bien à une méthode de l'interface.

> **📌 Rappel - composition et liste**
>
> - Un dresseur **a** des Pokémon : c'est un attribut `List<Pokemon>`. C'est la **composition**.
>   ```java
>   private final List<Pokemon> equipe = new ArrayList<>();
>   equipe.add(p);   equipe.size();   equipe.contains(p);   for (Pokemon p : equipe) { ... }
>   ```
> - Un getter qui retourne la liste doit retourner une **copie** (`new ArrayList<>(equipe)`) : sinon l'appelant peut
>   y ajouter des Pokémon en contournant vos règles.
> - Une méthode peut retourner **`null`** pour dire « aucun » : l'appelant doit le tester avant de s'en servir,
>   sinon `NullPointerException`.

> **📌 Rappel - exception vérifiée**
>
> Une exception qui hérite de **`Exception`** est **vérifiée** : le compilateur **oblige** celui qui appelle la méthode à
> s'en occuper, avec `try / catch`, ou en l'annonçant lui-même avec `throws`. On l'utilise pour un cas **prévisible**
> que l'appelant doit gérer (« votre équipe est pleine, ce Pokémon part au PC »).
>
> ```java
> public class EquipePleineException extends Exception {
> 	public EquipePleineException(String message) {
> 		super(message);   // transmet le message à Exception
> 	}
> }
>
> public void capturer(Pokemon p) throws EquipePleineException {
> 	if (...) {
> 		throw new EquipePleineException("...");
> 	}
> }
> ```

Créez l'**interface** **`Dresseur`** :

```java
String getNom();
String presentation();                 // une phrase qui présente le dresseur
void capturer(Pokemon pokemon) throws EquipePleineException;
List<Pokemon> getEquipe();             // une copie
Pokemon pokemonActif();                // le premier Pokémon de l'équipe qui n'est pas K.O., ou null
boolean peutEncoreCombattre();
void soignerEquipe();
String badgeOffert();                  // le badge gagné en battant ce dresseur, ou null
Dresseur affronter(Dresseur adversaire);   // retourne le vainqueur (écrite à l'étape 5)
```

Créez **`EquipePleineException`**, dont le message indique le dresseur et le Pokémon refusé.

Puis la classe **`Explorateur implements Dresseur`** :
- attributs : `nom`, `villeDOrigine`, l'équipe (`List<Pokemon>`, 6 au maximum), les badges (`List<String>`) ;
- `capturer` : `IllegalArgumentException` si ce Pokémon (le **même objet**) est déjà dans l'équipe,
  `EquipePleineException` si l'équipe est complète ;
- `presentation()` → `Explorateur Sacha, de Bourg Palette (0 badge(s))` ;
- `badgeOffert()` retourne `null` ; `getBadges()` retourne une copie ;
- pour `affronter`, retournez `null` pour l'instant : on l'écrira à l'étape 5.

**À vérifier** :
- un explorateur accepte 6 Pokémon (même plusieurs `new Pokemon("Rattata", 3, 8)` : ce sont des objets différents),
  et refuse le 7e ;
- capturer deux fois **le même** Pikachu est refusé ;
- `sacha.getEquipe().clear()` ne vide pas l'équipe de Sacha ;
- quand le premier Pokémon est K.O. (faites-lui perdre un combat contre un Mewtwo niveau 70...), `pokemonActif()` retourne
  le suivant ; après `soignerEquipe()`, à nouveau le premier.

**Question** : `equipe.contains(p)` utilise la méthode `equals` des Pokémon. Nous ne l'avons pas écrite : que compare-t-elle
alors ? Est-ce le comportement voulu pour deux Rattata niveau 3 ?

---

## Étape 4 - L'apprenti et le maître d'arène

> **📌 Rappel - polymorphisme**
>
> - Une variable du type de l'interface peut contenir **n'importe quel objet** qui l'implémente :
>   `Dresseur d = new MaitreArene(...);`
> - **Type déclaré** (`Dresseur`) : ce que le compilateur autorise (`d.presentation()` oui, `d.getNbDefensesReussies()` non).
>   **Type réel** (`MaitreArene`) : le code qui s'exécute.
> - On peut donc ranger des classes différentes dans une même `List<Dresseur>` et appeler `presentation()`
>   sur chacune : chaque dresseur répond **à sa façon**.

Créez **`ApprentiArene implements Dresseur`** et **`MaitreArene implements Dresseur`** en suivant le tableau du début de
la partie 2 :

- `ApprentiArene(String nom, String nomArene)` : 3 Pokémon maximum ;
  `presentation()` → `Apprenti Louis de l'arène Azuria` ;
- `MaitreArene(String nom, String nomArene)` : `badgeOffert()` retourne `"Badge Azuria"` ;
  `presentation()` → `Maître Ondine de l'arène Azuria (0 défense(s) réussie(s))`.

**À vérifier** :
- l'apprenti Louis refuse son 4e Pokémon ;
- seul le maître offre un badge ;
- une `List<Dresseur>` contenant un explorateur, un apprenti et un maître : affichez la présentation et la taille de
  l'équipe de chacun avec **une seule boucle**.

**Questions** :
1. Comparez vos trois classes. Quelles méthodes sont **identiques** ligne pour ligne ? (Gardez la réponse pour la partie 3.)
2. Pourquoi ne peut-on pas écrire ce code commun directement dans l'interface `Dresseur` ?

---

## Étape 5 - Les combats de dresseurs

> **📌 Rappel - dépendre d'une interface**
>
> Une classe qui ne manipule que l'**interface** `Dresseur` fonctionne avec **toutes** les sortes de dresseurs,
> y compris celles qui n'existent pas encore.
>
> Et pour savoir si l'adversaire offre un badge, inutile de tester sa classe : on lui **demande**
> (`adversaire.badgeOffert()`), et chaque dresseur répond à sa façon. C'est encore du polymorphisme.

### La classe `Combat`

- constructeur `Combat(Dresseur dresseur1, Dresseur dresseur2)` (`IllegalArgumentException` si c'est le même) ;
- `Dresseur lancer()` :
  1. `IllegalStateException` si l'un des deux n'a aucun Pokémon en état de combattre ;
  2. affiche `=== <présentation 1>  VS  <présentation 2> ===` ;
  3. tant que les deux peuvent combattre : le Pokémon actif du dresseur 1 **affronte** celui du dresseur 2 (étape 2 !).
     Le perdant est K.O. : au tour suivant, son dresseur enverra automatiquement le suivant ;
  4. retourne le dresseur qui a encore des Pokémon debout, et affiche `>>> Sacha remporte le combat !`.

### La méthode `affronter`

Dans chacune des trois classes, `affronter(Dresseur adversaire)` lance un `new Combat(this, adversaire)`,
retourne le vainqueur, et ajoute sa spécificité :

- **Explorateur** : s'il gagne et que `adversaire.badgeOffert()` n'est pas `null`, il ajoute ce badge à sa liste
  (s'il ne l'a pas déjà) et affiche `Sacha obtient le Badge Azuria !` ;
- **Apprenti** : s'il perd, il affiche `Louis : "Bien joué ! Le maître de l'arène Azuria t'attend."` ;
- **Maître** : il soigne son équipe **avant** le combat ; s'il gagne, `nbDefensesReussies` augmente.

### Le tour de l'arène

```java
Explorateur sacha = new Explorateur("Sacha", "Bourg Palette");
sacha.capturer(new Pokemon("Pikachu", 8, 13));
sacha.capturer(new Pokemon("Bulbizarre", 7, 11));
sacha.capturer(new Pokemon("Salamèche", 7, 12));

ApprentiArene louis = new ApprentiArene("Louis", "Azuria");
louis.capturer(new Pokemon("Carapuce", 6, 10));
louis.capturer(new Pokemon("Psykokwak", 6, 11));

MaitreArene ondine = new MaitreArene("Ondine", "Azuria");
ondine.capturer(new Pokemon("Stari", 11, 10));
ondine.capturer(new Pokemon("Staross", 11, 12));

List<Dresseur> arene = new ArrayList<>();
arene.add(louis);
arene.add(ondine);
for (Dresseur adversaire : arene) {
	sacha.soignerEquipe();
	sacha.affronter(adversaire);
}
System.out.println(sacha.presentation());
```

**À vérifier** :
- Sacha bat Louis, puis Ondine, et obtient le **Badge Azuria** ;
- un explorateur Régis avec un seul `new Pokemon("Rattata", 5, 8)` : `ondine.affronter(regis)` → Ondine se soigne, gagne,
  et a **1** défense réussie ; Régis n'a pas de badge.

**Question** : regardez le déroulé des combats. Pikachu a battu tous les Pokémon d'eau, l'un après l'autre. Dans le vrai
jeu, est-ce que tous les Pokémon se valent ? Qu'est-ce qui manque ? (Réponse dans la partie 3.)

🎉 **Fin de la partie 2** : vos dresseurs parcourent les arènes !

---

# Partie 3 - Les types (héritage)

Dans le vrai jeu, chaque Pokémon a un **type**, et les types ont des forces et des faiblesses :
une Plante craint le Feu, le Feu craint l'Eau, l'Eau craint les Plantes et l'Électrique...

| Le Pokémon... | craint (force adverse × 2) | résiste à (force adverse × 0.5) |
|---|---|---|
| **Feu** | Eau | Feu, Plante |
| **Eau** | Plante, Électrique | Feu, Eau |
| **Plante** | Feu | Eau, Plante, Électrique |
| **Électrique** | – | Électrique |

On pourrait ajouter un attribut `type` et des `if` partout :

```java
if (this.type.equals("Plante")) {
	if (typeAdversaire.equals("Feu")) { ... } else if (typeAdversaire.equals("Eau")) { ... }
} else if (this.type.equals("Eau")) {
	...
}
```

Et à chaque nouveau type, il faudrait retrouver et modifier **tous** ces `if`. Avec l'héritage, chaque type devient
une classe qui ne contient **que ce qui la rend différente** : ses faiblesses et ses résistances.

## Étape 6 - `Pokemon` devient une classe abstraite

> **📌 Rappel - classe abstraite et `extends`**
>
> - Une classe **abstraite** est entre l'interface et la classe : elle a des **attributs** et du **code**, mais certaines
>   méthodes n'ont **pas de corps** (`abstract`). On ne peut **pas** faire `new` dessus.
>   ```java
>   public abstract class Pokemon {
>   	public abstract String getType();    // chaque sous-classe DOIT l'écrire
>   	...                                  // tout le reste est écrit une seule fois
>   }
>   ```
> - `class PokemonFeu extends Pokemon` : `PokemonFeu` **récupère** tous les attributs et méthodes de `Pokemon`,
>   et **est un** `Pokemon` (on peut le ranger dans une variable ou une liste de type `Pokemon`).
> - `extends`, c'est comme `implements`... avec du code en plus : la fille doit fournir les méthodes abstraites,
>   exactement comme une classe qui implémente une interface.
> - Le constructeur de la fille **doit** appeler celui de la mère, en **première ligne**, avec `super(...)` :
>   ```java
>   public PokemonFeu(String nom, int niveau, int puissance) {
>   	super(nom, niveau, puissance);
>   }
>   ```
> - Les attributs `private` de la mère existent dans l'objet fille, mais la fille ne peut **pas** y accéder
>   directement : elle passe par les getters (`getNiveau()`, et non `this.niveau`). L'encapsulation tient toujours.
> - Un constructeur **`protected`** n'est appelable que par les sous-classes.
> - Une classe n'a **qu'une seule** classe mère, mais peut implémenter **plusieurs** interfaces.

1. Rendez `Pokemon` **abstraite** : son constructeur devient `protected`, et ajoutez la méthode abstraite
   `public abstract String getType();`.
2. Créez quatre sous-classes : **`PokemonFeu`**, **`PokemonEau`**, **`PokemonPlante`**, **`PokemonElectrique`**.
   Chacune a un constructeur qui appelle `super(...)`, et redéfinit `getType()` (avec `@Override`) pour retourner
   `"Feu"`, `"Eau"`, `"Plante"` ou `"Électrique"`.
3. Ajoutez le type dans `toString()` : `Salamèche (Feu, niv. 5, force 60)`.
4. Dans votre `Main`, remplacez les `new Pokemon("Salamèche", ...)` par `new PokemonFeu("Salamèche", ...)`, etc.

**À vérifier** :
- `new Pokemon(...)` ne compile plus ;
- une `List<Pokemon>` avec un Pokémon de chaque type : affichez-les tous ;
- `MainPartie1et2` ne compile plus, à cause des `new Pokemon(...)` : c'est la preuve que `Pokemon` est bien abstraite.
  Mettez-le de côté et passez à `MainPartie3`.

**Questions** :
1. Quelles classes avez-vous modifiées en dehors de `Pokemon` et de vos tests ? Pourquoi `Combat` et les dresseurs
   n'ont-ils pas changé ?
2. `toString()` appelle `getType()`, qui n'est écrite nulle part dans `Pokemon`. Pourquoi ça marche ?
3. Que se passe-t-il si `PokemonPlante` oublie d'écrire `getType()` ?

---

## Étape 7 - Forces et faiblesses (redéfinition)

> **📌 Rappel - redéfinir une méthode héritée**
>
> - Une méthode **concrète** de la mère (avec un corps) donne un **comportement par défaut**. Une fille peut la
>   **redéfinir** (*override*) avec la même signature : c'est alors **sa** version qui s'exécute, même si la variable est
>   déclarée de type `Pokemon`.
> - Différence avec une méthode `abstract` : une méthode abstraite **doit** être redéfinie, une méthode concrète **peut**
>   l'être.
> - Attention à la **surcharge** (*overload*) : même nom mais **paramètres différents**, ce n'est pas une redéfinition.
>   `@Override` protège de cette erreur.
> - Pour comparer deux `String`, on utilise `equals`, jamais `==`.

1. Dans `Pokemon`, ajoutez une méthode **concrète** qui donne le comportement par défaut (aucune faiblesse) :
   ```java
   // Multiplicateur appliqué à la force d'un adversaire de ce type
   public double sensibiliteA(String typeAdversaire) {
   	return 1.0;
   }
   ```
2. Dans **chaque** sous-classe, redéfinissez `sensibiliteA` selon le tableau du début de la partie 3.
   Par exemple, `PokemonPlante.sensibiliteA("Feu")` retourne `2.0`.
3. Dans `Pokemon`, ajoutez `int forceContre(Pokemon adversaire)` : ma force, multipliée par la sensibilité de
   l'adversaire **à mon type** (`adversaire.sensibiliteA(getType())`), arrondie avec `(int) Math.round(...)`.
4. Modifiez `affronter` : on compare maintenant `forceContre(adversaire)` et `adversaire.forceContre(this)`
   (et ce sont ces forces qu'on affiche).

**À vérifier** (Pokémon niveau 5 : Bulbizarre puissance 11, Carapuce puissance 10, Pikachu puissance 13) :

| Test | Attendu |
|---|---|
| `bulbizarre.sensibiliteA("Feu")` / `bulbizarre.sensibiliteA("Eau")` | 2.0 / 0.5 |
| `carapuce.sensibiliteA("Électrique")` / `pikachu.sensibiliteA("Plante")` | 2.0 / 1.0 |
| `bulbizarre.forceContre(carapuce)` / `carapuce.forceContre(bulbizarre)` | 110 / 25 |
| Pikachu affronte Carapuce | Pikachu gagne : 130 contre 50 |
| Salamèche (niv. 3, puissance 12) affronte Bulbizarre (niv. 6, puissance 11) | Salamèche gagne : 36 × 2 = 72 contre 66 × 0.5 = 33 |

**Question** : `affronter` est écrite **une seule fois**, dans `Pokemon`, et elle ne contient aucun `if` sur le type.
Comment fait-elle pour que Bulbizarre craigne le Feu ?

---

## Étape 8 - Les talents (`super`)

> **📌 Rappel - réutiliser la version de la mère avec `super`**
>
> - Dans une méthode redéfinie, `super.methode(...)` appelle la version **de la mère** : on **complète** son
>   comportement au lieu de tout recopier.
>   ```java
>   @Override
>   public int getForce() {
>   	return (int) Math.round(super.getForce() * 1.2);   // le calcul de la mère, avec 20 % en plus
>   }
>   ```
> - Sans `super.`, la méthode s'appellerait **elle-même** à l'infini (`StackOverflowError`).
> - Si la mère protège une règle (« niveau 100 maximum »), appeler `super` garantit que la fille la respecte aussi.

1. Dans `Pokemon`, ajoutez `public String getTalent()` qui retourne `"Aucun"` (comportement par défaut).
2. Donnez un talent au Feu et à l'Eau :

| Classe | Talent | Effet | Technique |
|---|---|---|---|
| `PokemonFeu` | **Brasier** | 20 % de force en plus | redéfinir `getForce()` avec `super.getForce()` |
| `PokemonEau` | **Croissance** | gagne **2** niveaux à chaque victoire | redéfinir `gagnerNiveau()` en appelant **deux fois** `super.gagnerNiveau()` |

3. **À vous** : inventez un talent pour `PokemonPlante` ou `PokemonElectrique`, en redéfinissant une méthode existante.

**À vérifier** :
- un Salamèche niveau 5 (puissance 12) a une force de 72 (60 × 1.2) ;
- un Carapuce niveau 5 qui gagne un niveau passe niveau 7 ; un Tortank niveau 99, niveau 100 (et pas 101) ;
- Bulbizarre (sans talent) : `getTalent()` retourne `"Aucun"` ;
- le dernier test de l'étape 7 donne maintenant 86 contre 33 (Brasier : 36 × 1.2 = 43, × 2 = 86).

**Questions** :
1. `affronter` appelle `vainqueur.gagnerNiveau()`. A-t-il fallu modifier `affronter` pour que Croissance fonctionne ?
2. Pourquoi `getTalent()` n'est-elle pas `abstract`, alors que `getType()` l'est ?

---

## Étape 9 - Factoriser les dresseurs

> **📌 Rappel - une classe abstraite qui implémente une interface**
>
> On peut combiner les deux : l'**interface** donne le type commun (le combat ne connaît qu'elle), la **classe abstraite**
> contient le code commun.
>
> ```java
> public abstract class DresseurAbstrait implements Dresseur {
> 	// attributs et méthodes communs à tous les dresseurs
> 	public abstract int tailleMaxEquipe();   // chaque dresseur donne sa propre valeur
> }
>
> public class ApprentiArene extends DresseurAbstrait { ... }
> ```
>
> - La classe abstraite a le droit de **ne pas écrire** toutes les méthodes de l'interface (ici `presentation` et
>   `affronter`) : ce sont les sous-classes concrètes qui les fourniront.
> - Une sous-classe peut redéfinir une méthode commune pour **ajouter une règle**, puis laisser la mère faire le reste :
>   ```java
>   @Override
>   public void capturer(Pokemon pokemon) throws EquipePleineException {
>   	if (...) {   // la règle propre au maître
>   		throw new IllegalArgumentException(...);
>   	}
>   	super.capturer(pokemon);
>   }
>   ```

Reprenez votre réponse à la question 1 de l'étape 4 : les trois dresseurs recopient le même code.

1. Créez **`DresseurAbstrait implements Dresseur`** avec : le nom, l'équipe, un constructeur `protected`, une méthode
   abstraite `tailleMaxEquipe()`, et les méthodes communes (`getNom`, `capturer`, `getEquipe`, `pokemonActif`,
   `peutEncoreCombattre`, `soignerEquipe`, `toString`). `badgeOffert()` y retourne `null` par défaut.
2. Les trois dresseurs font maintenant `extends DresseurAbstrait` et ne gardent **que ce qui leur est propre** :
   leurs attributs, `tailleMaxEquipe()`, `presentation()`, `affronter()`, et pour le maître `badgeOffert()`.
3. Maintenant que les Pokémon ont un type, le maître a une **spécialité** : `MaitreArene(String nom, String nomArene, String specialite)`.
   Il redéfinit `capturer` pour refuser (`IllegalArgumentException`) un Pokémon d'un autre type, puis appelle
   `super.capturer(pokemon)`. Sa présentation devient
   `Maître Ondine de l'arène Azuria, spécialiste Eau (0 défense(s) réussie(s))`.

**À vérifier** :
- Ondine (spécialité `"Eau"`) refuse un Salamèche ; Louis refuse toujours son 4e Pokémon ;
- le tour de l'arène, avec des types cette fois :

```java
MaitreArene ondine = new MaitreArene("Ondine", "Azuria", "Eau");
ondine.capturer(new PokemonEau("Stari", 11, 10));
ondine.capturer(new PokemonEau("Staross", 11, 12));

ApprentiArene louis = new ApprentiArene("Louis", "Azuria");
louis.capturer(new PokemonEau("Carapuce", 6, 10));
louis.capturer(new PokemonEau("Psykokwak", 6, 11));
louis.capturer(new PokemonEau("Poissirène", 5, 12));

Explorateur sacha = new Explorateur("Sacha", "Bourg Palette");
sacha.capturer(new PokemonFeu("Salamèche", 8, 12));
sacha.capturer(new PokemonPlante("Bulbizarre", 7, 11));
sacha.capturer(new PokemonElectrique("Pikachu", 7, 13));
```

Résultat attendu : Salamèche (le plus fort de l'équipe) perd contre le premier Pokémon d'eau, mais Bulbizarre
bat tous les autres. Sacha obtient le Badge Azuria.

**Questions** :
1. Combien de lignes avez-vous supprimées ?
2. Pourquoi garde-t-on l'interface `Dresseur`, au lieu de ne garder que la classe abstraite ?
   (Indice : un futur dresseur qui aurait déjà une autre classe mère.)

🎉 **Fin de la partie 3** : chaque type a ses forces et ses faiblesses, et le code commun n'est écrit qu'une fois.

---

# Partie 4 - Pour aller plus loin

Ces étapes sont indépendantes de la notion d'héritage : faites-les dans l'ordre, et allez aussi loin que vous pouvez.

## Étape 10 - Un numéro pour chaque Pokémon (`static`)

> **📌 Rappel - `static`**
>
> - Un attribut **`static`** existe en **un seul exemplaire pour toute la classe**, partagé par tous les objets.
>   Idéal pour un compteur qui donne un numéro différent à chaque Pokémon créé :
>   ```java
>   private static int prochainNumero = 1;   // un seul pour toute la classe
>   private final int numero;                // un par Pokémon
>   ```
> - Une méthode **`static`** s'appelle sur la classe, sans objet : `Pokedex.salameche(5)`. Elle n'a pas de `this`.

1. Chaque Pokémon reçoit un numéro unique (1, 2, 3...) dans le constructeur de `Pokemon` ; ajoutez `getNumero()`, et
   affichez-le dans `toString()` : `#1 Salamèche (Feu, niv. 5, force 72)`.
2. Créez une classe **`Pokedex`** avec des méthodes `static` qui créent des Pokémon prêts à l'emploi :
   `Pokedex.salameche(int niveau)` retourne un `new PokemonFeu("Salamèche", niveau, 12)`, et de même pour
   `carapuce` (Eau, 10), `bulbizarre` (Plante, 11) et `pikachu` (Électrique, 13).

**À vérifier** : deux Pokémon créés à la suite ont des numéros qui se suivent.

---

## Étape 11 - Les types deviennent une `enum`

> **📌 Rappel - les `enum`**
>
> - Une `enum` est une classe dont **toutes les instances sont connues à l'avance** : on ne fait jamais `new Type()`,
>   on utilise directement `Type.FEU`, `Type.EAU`...
> - Elle peut avoir des **attributs**, un **constructeur** et des **méthodes** :
>   ```java
>   public enum Couleur {
>   	VERT("vert"), ROUGE("rouge");          // chaque constante appelle le constructeur
>
>   	private final String libelle;
>
>   	Couleur(String libelle) {
>   		this.libelle = libelle;
>   	}
>
>   	public String getLibelle() {
>   		return libelle;
>   	}
>   }
>   ```
> - On compare deux constantes avec `==`. `Type.values()` retourne le tableau de toutes les constantes.
> - Avantage sur les `String` : une faute de frappe (`"feu"`, `"Electrique"`) ne compile pas, au lieu de donner un bug
>   silencieux.

1. Créez l'`enum` **`Type`** : `NORMAL`, `FEU`, `EAU`, `PLANTE`, `ELECTRIQUE`, chacune avec son libellé
   (`"Normal"`, `"Feu"`, `"Eau"`, `"Plante"`, `"Électrique"`).
2. Remplacez **tous** les `String` de type par `Type` : `getType()`, `sensibiliteA(Type typeAdversaire)`, la spécialité du
   maître... Laissez le compilateur vous indiquer les endroits à corriger.
3. Dans `toString()` et `presentation()`, affichez `getLibelle()`.

**À vérifier** :
- `MainPartie3` ne compile plus (il utilise des `String`) : mettez-le de côté, `MainPartie4` prend le relais ;
- écrivez `bulbizarre.sensibiliteA("feu")` : que dit le compilateur ? Et qu'aurait fait la version avec des `String` ?

---

## Étape 12 - Les attaques (objet immuable, `equals` et `hashCode`)

> **📌 Rappel - objet immuable**
>
> **Immuable** = l'objet ne change jamais après sa construction : tous les attributs sont `final`, et il n'y a aucun
> setter. Avantage : on peut **partager** le même objet entre plusieurs variables sans risque (c'est le cas de `String`).
> Ici, la même attaque `Charge` sera connue par **tous** les Pokémon.

> **📌 Rappel - `equals` et `hashCode`**
>
> | Méthode | Par défaut | Ce qu'on veut |
> |---|---|---|
> | `equals(Object o)` | même adresse, comme `==` | même **contenu** |
> | `hashCode()` | un nombre dérivé de l'adresse | un nombre calculé **à partir des mêmes attributs que `equals`** |
>
> **Le contrat à respecter** : si `a.equals(b)` est vrai, alors `a.hashCode() == b.hashCode()` **doit** être vrai.
> (L'inverse n'est pas obligatoire : deux objets différents peuvent avoir le même `hashCode`.)
>
> **Pourquoi ?** Un `HashSet` (ou une `HashMap`) range les objets dans des « tiroirs » numérotés par leur `hashCode`,
> puis n'appelle `equals` **que pour les objets du même tiroir**. Si deux objets égaux ont des `hashCode`
> différents, ils tombent dans deux tiroirs différents : `equals` n'est jamais appelé, et le `HashSet` garde un doublon.
>
> ```java
> @Override
> public boolean equals(Object o) {          // le paramètre DOIT être de type Object
> 	if (this == o) {
> 		return true;
> 	}
> 	if (!(o instanceof Attaque)) {
> 		return false;
> 	}
> 	Attaque autre = (Attaque) o;
> 	return this.nom.equals(autre.nom);
> }
>
> @Override
> public int hashCode() {
> 	return this.nom.hashCode();            // les mêmes attributs que dans equals
> }
> ```
>
> Règle simple : **on redéfinit toujours `equals` et `hashCode` ensemble**, avec les mêmes attributs.

### La classe `Attaque`

Créez la classe **`Attaque`**, **immuable** :
- `nom`, `type` (`Type`), `puissance` (`int`, > 0), avec des getters et un `toString()` →
  `Flammèche (Feu, puissance 40)` ;
- `equals()` : deux attaques sont égales si elles ont **le même nom**, sans tenir compte des majuscules
  (`equalsIgnoreCase`) ; et `hashCode()` cohérent (indice : `this.nom.toLowerCase().hashCode()`) ;
- une constante connue de tous : `public static final Attaque CHARGE = new Attaque("Charge", Type.NORMAL, 40);`

| Nom | Type | Puissance |
|---|---|---|
| Charge | Normal | 40 |
| Flammèche | Feu | 40 |
| Lance-Flammes | Feu | 90 |
| Pistolet à O | Eau | 40 |
| Hydrocanon | Eau | 110 |
| Fouet Lianes | Plante | 45 |
| Tranch'Herbe | Plante | 55 |
| Éclair | Électrique | 40 |
| Tonnerre | Électrique | 90 |

### Les Pokémon apprennent des attaques

Dans `Pokemon` :
- une liste d'attaques, qui contient `Attaque.CHARGE` dès la construction, **4 au maximum** ;
- `List<Attaque> getAttaques()` (une copie) ;
- `void apprendre(Attaque a) throws TropDAttaquesException` : `IllegalArgumentException` si l'attaque est déjà connue
  (`contains`, qui utilise votre `equals` !), **`TropDAttaquesException`** (exception vérifiée, à créer) s'il en connaît
  déjà 4 ;
- `int forceContre(Pokemon adversaire, Attaque attaque)` :
  ```
  force = getForce() × puissance de l'attaque / 40 × 1.5 si l'attaque est du type du Pokémon × sensibilité de l'adversaire au type de l'ATTAQUE
  ```
- `Attaque meilleureAttaqueContre(Pokemon adversaire)` : l'attaque connue qui donne la plus grande force ;
- `affronter` : chaque Pokémon utilise sa meilleure attaque contre l'autre.

Dans le `Pokedex` :
- les attaques du tableau (sauf Charge) deviennent des constantes : `FLAMMECHE`, `LANCE_FLAMMES`, `PISTOLET_A_O`,
  `HYDROCANON`, `FOUET_LIANES`, `TRANCH_HERBE`, `ECLAIR`, `TONNERRE`
  (par exemple `public static final Attaque FLAMMECHE = new Attaque("Flammèche", Type.FEU, 40);`) ;
- chaque Pokémon apprend une attaque de son type : Flammèche, Pistolet à O, Fouet Lianes, Éclair.
  `apprendre` peut lever une `TropDAttaquesException`, qui ne peut pas arriver ici : attrapez-la et transformez-la
  en `throw new IllegalStateException("Pokédex mal configuré", e);`.

**À vérifier** (Pokémon du Pokédex niveau 5 ; Salamèche a une force de 72 avec Brasier) :

| Test | Attendu |
|---|---|
| deux `Flammèche` / `FLAMMÈCHE` dans un `HashSet` | taille 1 (mettez `hashCode` en commentaire : que se passe-t-il ?) |
| Salamèche → Bulbizarre, Charge | 72 |
| Salamèche → Bulbizarre, Flammèche | 72 × 1.5 × 2 = 216 |
| Salamèche → Carapuce, Flammèche | 72 × 1.5 × 0.5 = 54 |
| meilleure attaque de Salamèche contre Carapuce | Charge (72 > 54) |
| Salamèche apprend Lance-Flammes et Éclair, puis Tonnerre | 4 attaques, puis `TropDAttaquesException` |
| Salamèche → Bulbizarre, Lance-Flammes | 72 × 90/40 × 1.5 × 2 = 486 |

---

## Étape 13 - Le hasard (les dés)

> **📌 Rappel - interface et faux objets**
>
> - Si le code ne dépend que d'une **interface** `De`, on peut lui donner **n'importe quel** dé : un vrai dé aléatoire
>   pour jouer, et un **dé truqué** pour vérifier les calculs. C'est le même principe que le `NotificateurMemoire` du
>   kata 08 ou le `DeTruque` du kata 16.
> - **Injection de dépendance** : le combat ne crée pas son dé lui-même, on le lui **donne** (en paramètre ou dans le
>   constructeur). Le même code sert alors à jouer et à vérifier.
> - `java.util.Random` : `random.nextInt(n)` donne un nombre entre 0 et n - 1 (donc `+ 1` pour un dé).
>   `new Random(graine)` donne **toujours la même suite** de nombres : pratique pour rejouer le même combat.

```java
public interface De {
	int lancer(int nbFaces);   // un nombre entre 1 et nbFaces
}
```

1. **`DeAleatoire implements De`** (avec `java.util.Random`, deux constructeurs : sans paramètre et avec une graine `long`)
   et **`DeTruque implements De`** (retourne toujours la même valeur, sans dépasser `nbFaces`).
2. `affronter(Pokemon adversaire, De de)` : chaque Pokémon lance un dé à 6 faces (d'abord `this`, puis l'adversaire),
   et sa force est **multipliée** par le résultat. Affichez le détail :
   `Salamèche lance Flammèche (216 x 4 = 864), Bulbizarre lance Charge (55 x 2 = 110) : Salamèche gagne !`
3. `Combat` reçoit un `De` dans son constructeur, et l'interface `Dresseur` devient `affronter(Dresseur adversaire, De de)`.
   Laissez le compilateur vous indiquer **toutes** les classes à corriger. (Grâce à l'étape 9, combien y en a-t-il ?)

**À vérifier** :
- `new DeTruque(4).lancer(6)` vaut 4 ; `new DeTruque(9).lancer(6)` vaut 6 ; 1000 lancers d'un `DeAleatoire` sont entre 1 et 6 ;
- avec un `new DeTruque(1)`, tous les combats donnent **exactement** les mêmes résultats qu'avant ;
- relancez le tour de l'arène avec un `new DeAleatoire()` plusieurs fois : Sacha gagne-t-il à chaque fois ?
  Donnez **Hydrocanon** à Staross pour corser les choses. S'il perd, il arrête son tour de l'arène (`break`).

**Question** : pourquoi les vérifications seraient-elles impossibles avec seulement un `DeAleatoire` ?

🎉 **Fin du TP !**

---

# Bonus (au choix)

1. **Des points de vie** : au lieu d'un K.O. immédiat, chaque Pokémon a des PV. À chaque tour, l'attaquant retire
   `forceContre(...) / 10` PV à son adversaire, et on alterne jusqu'au K.O. Quelles méthodes changent ?
   Lesquelles restent identiques ?
2. **Nouveau type** : `PokemonGlace`. Quelles classes existantes devez-vous modifier ? (Ni `Combat`, ni les dresseurs : pourquoi ?)
3. **Un Champion** : un nouveau dresseur qui n'accepte un combat que contre un explorateur ayant au moins 1 badge.
   Indice : ajoutez à l'interface `Dresseur` une méthode `int nbBadges()`.
4. **Les objets** : une interface `Objet` (`String getNom()`, `void utiliserSur(Pokemon p)`) avec `Potion` (soigne un
   Pokémon K.O.) et `SuperBonbon` (fait gagner un niveau). Un explorateur a un sac d'objets.
5. **Trier l'équipe** : `Pokemon implements Comparable<Pokemon>` (du plus fort au plus faible), et un dresseur
   place son meilleur Pokémon en tête avant un combat.
6. **Stratégies** : une interface `StrategieCombat` avec `Pokemon choisir(List<Pokemon> equipe, Pokemon adversaire)` :
   un dresseur malin envoie le Pokémon qui a le meilleur type contre l'adversaire, au lieu du premier de la liste.
7. **Évolution** : au niveau 16, Salamèche devient Reptincel. Faut-il une classe `Reptincel extends PokemonFeu`, ou un
   simple changement de nom ? Argumentez (pensez au kata 14).
8. **Double type** : Bulbizarre est en réalité Plante **et** Poison. L'héritage simple peut-il le représenter ?
   Proposez une conception (indice : un Pokémon pourrait **avoir** une liste de types au lieu d'**être** d'un type...).

---

# Récapitulatif : où sont les notions ?

| Notion | Où dans le TP |
|---|---|
| Classe, constructeur, `this`, `toString` | `Pokemon` (étape 1) |
| Encapsulation, invariants | niveau entre 1 et 100, taille des équipes, 4 attaques maximum |
| Références, `==` | `affronter` retourne le vainqueur, `vainqueur == this` |
| Interface, `implements` | `Dresseur` (étapes 3 et 4), `De` (étape 13) |
| Composition, `ArrayList`, copies | l'équipe et les badges des dresseurs, les attaques |
| Exceptions | `IllegalArgumentException`, `IllegalStateException`, `EquipePleineException`, `TropDAttaquesException` |
| Polymorphisme | `List<Dresseur>`, `badgeOffert()`, `presentation()`, `sensibiliteA()` |
| Classe abstraite, méthode abstraite | `Pokemon` (`getType`), `DresseurAbstrait` (`tailleMaxEquipe`) |
| `extends`, `super(...)`, `@Override` | les quatre types de Pokémon, les trois dresseurs |
| Méthode par défaut redéfinie | `sensibiliteA`, `getTalent`, `badgeOffert` |
| `super.methode()` | Brasier, Croissance, la capture du maître |
| `static` | numéro des Pokémon, `Pokedex`, `Attaque.CHARGE` |
| `enum` | `Type` |
| Objet immuable, `equals` / `hashCode` | `Attaque` |
| Faux objets, injection de dépendance | `DeTruque`, le `De` donné au combat |
