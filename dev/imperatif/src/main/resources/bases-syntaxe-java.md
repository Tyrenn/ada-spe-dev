# Les bases de la syntaxe Java (programmation impérative)

Ce mémo couvre les fondamentaux du langage, sans programmation orientée objet :
types primitifs, casts, conditions, portée des variables, tableaux, boucles et fonctions.

---

## 1. Structure minimale d'un programme

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Bonjour !");
    }
}
```

- Toute instruction se termine par un **point-virgule** `;`.
- Les **accolades** `{ }` délimitent un bloc de code.
- Java est **sensible à la casse** : `age` et `Age` sont deux variables différentes.
- Commentaires :

```java
// commentaire sur une ligne

/* commentaire
   sur plusieurs lignes */
```

---

## 2. Les types primitifs

Java est un langage **fortement et statiquement typé** : chaque variable a un type fixé à la déclaration.

| Type      | Taille   | Plage / valeurs                                   | Valeur par défaut* | Exemple                 |
|-----------|----------|---------------------------------------------------|--------------------|-------------------------|
| `byte`    | 8 bits   | -128 à 127                                        | `0`                | `byte b = 100;`         |
| `short`   | 16 bits  | -32 768 à 32 767                                  | `0`                | `short s = 30000;`      |
| `int`     | 32 bits  | environ -2,1 milliards à 2,1 milliards            | `0`                | `int n = 42;`           |
| `long`    | 64 bits  | environ ±9,2 × 10¹⁸                               | `0L`               | `long l = 5000000000L;` |
| `float`   | 32 bits  | décimal, ~7 chiffres significatifs                | `0.0f`             | `float f = 3.14f;`      |
| `double`  | 64 bits  | décimal, ~15 chiffres significatifs               | `0.0`              | `double d = 3.14159;`   |
| `char`    | 16 bits  | un caractère Unicode                              | `'\u0000'`         | `char c = 'A';`         |
| `boolean` | –        | `true` ou `false`                                 | `false`            | `boolean ok = true;`    |

\* Les valeurs par défaut s'appliquent aux cases de tableaux. Une **variable locale** doit
obligatoirement être initialisée avant d'être lue, sinon le code ne compile pas.

### Points d'attention

- Un littéral entier est un `int` par défaut → suffixe `L` pour un `long`.
- Un littéral décimal est un `double` par défaut → suffixe `f` pour un `float`.
- `char` utilise des **apostrophes** `'a'`, alors que `String` utilise des **guillemets** `"a"`.
- `String` **n'est pas** un type primitif (c'est une classe), mais on l'utilise partout :

```java
String prenom = "Ada";
System.out.println("Bonjour " + prenom); // concaténation avec +
```

### Déclaration et affectation

```java
int age;            // déclaration
age = 30;           // affectation
int annee = 2026;   // déclaration + initialisation

final double TVA = 0.2; // constante : ne peut plus être modifiée
```

### Opérateurs courants

```java
int a = 7, b = 2;

a + b    // 9
a - b    // 5
a * b    // 14
a / b    // 3   ← division entière entre deux int !
a % b    // 1   (reste de la division euclidienne / modulo)

a++;     // a = a + 1
a += 3;  // a = a + 3  (existe aussi : -=, *=, /=, %=)

// Comparaisons → résultat boolean
a == b   a != b   a < b   a <= b   a > b   a >= b

// Logique
&&  // ET
||  // OU
!   // NON
```

---

## 3. Les casts (conversions de type)

### Conversion implicite (élargissement)

Automatique quand on passe d'un type « plus petit » vers un type « plus grand », sans perte :

```
byte → short → int → long → float → double
              char ↗
```

```java
int i = 100;
long l = i;      // OK, implicite
double d = i;    // OK, d vaut 100.0
```

### Conversion explicite (rétrécissement)

Obligatoire dans l'autre sens, car il peut y avoir **perte d'information**.
On écrit le type cible entre parenthèses :

```java
double d = 9.87;
int i = (int) d;        // i vaut 9 → la partie décimale est TRONQUÉE (pas arrondie)

int grand = 300;
byte b = (byte) grand;  // b vaut 44 → dépassement de capacité !

long l = 42L;
int n = (int) l;        // OK ici, mais risque si l dépasse la plage d'un int
```

### Cas pratiques

```java
// Division décimale entre deux int
int a = 7, b = 2;
double r1 = a / b;            // 3.0  ← la division entière est faite AVANT la conversion
double r2 = (double) a / b;   // 3.5  ← on caste un opérande avant la division

// char ↔ int (code Unicode)
char c = 'A';
int code = c;                 // 65
char suivant = (char) (c + 1); // 'B'

// Arrondi plutôt que troncature
long arrondi = Math.round(9.87); // 10

// String ↔ nombre (ce ne sont pas des casts, mais des conversions par méthode)
int x = Integer.parseInt("123");
double y = Double.parseDouble("3.14");
String s = String.valueOf(42);   // ou "" + 42
```

---

## 4. Conditions : `if` / `else`

### Syntaxe de base

```java
int note = 14;

if (note >= 10) {
    System.out.println("Admis");
} else {
    System.out.println("Ajourné");
}
```

### `else if` en chaîne

```java
if (note >= 16) {
    System.out.println("Très bien");
} else if (note >= 14) {
    System.out.println("Bien");
} else if (note >= 10) {
    System.out.println("Passable");
} else {
    System.out.println("Insuffisant");
}
```

Les conditions sont testées **dans l'ordre** : seule la première vraie est exécutée.

### Imbrication

```java
int age = 20;
boolean aBillet = true;

if (age >= 18) {
    if (aBillet) {
        System.out.println("Entrée autorisée");
    } else {
        System.out.println("Billet requis");
    }
} else {
    System.out.println("Réservé aux majeurs");
}
```

Souvent, une imbrication peut être simplifiée avec les opérateurs logiques :

```java
if (age >= 18 && aBillet) {
    System.out.println("Entrée autorisée");
}
```

### Opérateur ternaire

Raccourci pour choisir une valeur selon une condition :

```java
String statut = (note >= 10) ? "Admis" : "Ajourné";
```

### `switch` (bonus)

```java
int jour = 3;
switch (jour) {
    case 1 -> System.out.println("Lundi");
    case 2 -> System.out.println("Mardi");
    case 3 -> System.out.println("Mercredi");
    default -> System.out.println("Autre jour");
}
```

### ⚠️ Pièges

- Toujours mettre des accolades, même pour une seule instruction : sans elles, seule
  la ligne suivante dépend du `if`.
- `=` est une **affectation**, `==` est une **comparaison**.
- Pour comparer deux `String`, utiliser `.equals()` et non `==` :
  `if (prenom.equals("Ada")) { ... }`

---

## 5. Portée des variables (scope)

Une variable n'existe **que dans le bloc `{ }` où elle est déclarée**, et dans les blocs
imbriqués à l'intérieur de celui-ci.

```java
public static void main(String[] args) {
    int a = 1;                     // visible dans tout le main

    if (a > 0) {
        int b = 2;                 // visible uniquement dans ce if
        System.out.println(a + b); // OK : a est visible ici (bloc parent)
    }

    // System.out.println(b);     // ERREUR de compilation : b n'existe plus ici
}
```

### Règles à retenir

- Un bloc intérieur **voit** les variables des blocs extérieurs.
- Un bloc extérieur **ne voit pas** les variables des blocs intérieurs.
- On **ne peut pas redéclarer** une variable locale qui existe déjà dans un bloc englobant :

```java
int x = 5;
if (true) {
    // int x = 10;  // ERREUR : x est déjà défini dans la portée
    x = 10;         // OK : on modifie la variable existante
}
System.out.println(x); // 10
```

- Si on a besoin d'une valeur après un `if`, il faut **déclarer la variable avant** :

```java
String resultat;            // déclarée avant
if (note >= 10) {
    resultat = "Admis";
} else {
    resultat = "Ajourné";
}
System.out.println(resultat); // OK, et initialisée dans tous les cas
```

- La variable d'une boucle `for` n'existe que dans la boucle (voir section 7).

---

## 6. Les tableaux

Un tableau contient un **nombre fixe** d'éléments **du même type**. Sa taille ne peut pas changer
après création. Les indices commencent à **0**.

### Déclaration et création

```java
int[] notes = new int[5];              // 5 cases, toutes à 0
String[] noms = new String[3];         // 3 cases, toutes à null

int[] premiers = {2, 3, 5, 7, 11};     // création avec valeurs initiales
```

### Accès et modification

```java
int[] t = {10, 20, 30};

int premier = t[0];          // 10
int dernier = t[t.length - 1]; // 30 → length donne la taille (sans parenthèses)
t[1] = 99;                   // t vaut maintenant {10, 99, 30}

// t[3] = 5;                 // ERREUR à l'exécution : ArrayIndexOutOfBoundsException
```

### Afficher un tableau

```java
import java.util.Arrays;

System.out.println(t);                  // affiche une adresse du type [I@1b6d3586
System.out.println(Arrays.toString(t)); // affiche [10, 99, 30]
```

### Tableaux à deux dimensions (matrices)

```java
int[][] grille = new int[3][4];   // 3 lignes, 4 colonnes

int[][] m = {
    {1, 2, 3},
    {4, 5, 6}
};

int val = m[1][2];       // 6 → ligne 1, colonne 2
int nbLignes = m.length;       // 2
int nbColonnes = m[0].length;  // 3
```

### ⚠️ Valeur et Référence

**On parle de valeur** lorsque la variable contient directement la donnée elle-même.
C'est le cas de tous les **types primitifs** (`int`, `double`, `char`, `boolean`…).
Copier la variable copie la donnée : les deux variables sont ensuite **indépendantes**.

```java
int x = 5;
int y = x;       // y reçoit une COPIE de la valeur 5
y = 100;
System.out.println(x); // 5  → x n'a pas changé
System.out.println(y); // 100
```

```
x ──► [ 5 ]
y ──► [ 100 ]      deux cases mémoire distinctes
```

**On parle de référence** lorsque la variable ne contient pas la donnée, mais
**l'adresse** de l'endroit où elle est stockée en mémoire. C'est le cas des **tableaux**
(et plus généralement de tout ce qui n'est pas un type primitif, comme `String`).
Copier la variable copie seulement l'adresse : les deux variables désignent alors
**la même donnée**.

```java
int[] a = {1, 2, 3};
int[] b = a;     // b reçoit une copie de l'ADRESSE, pas du tableau
b[0] = 100;
System.out.println(a[0]); // 100 ! → modifier via b modifie aussi a
```

```
a ──┐
    ├──► [ 100 | 2 | 3 ]      un seul tableau en mémoire
b ──┘
```

Pour obtenir deux tableaux réellement indépendants, il faut faire une **copie explicite** :

```java
int[] copie = Arrays.copyOf(a, a.length); // nouveau tableau, contenu recopié
copie[0] = 0;
System.out.println(a[0]); // toujours 100
```

| | Type primitif (valeur) | Tableau (référence) |
|---|---|---|
| La variable contient | la donnée | l'adresse de la donnée |
| `b = a` copie | la donnée | l'adresse |
| Modifier `b` modifie `a` ? | non | oui |
| Comparaison `a == b` | compare les valeurs | compare les adresses (même tableau ?) |

Cette distinction explique aussi le comportement du passage de paramètres aux fonctions (voir section 8).

---

## 7. Les boucles

### Boucle `for`

Utilisée quand on connaît le **nombre d'itérations** à l'avance.

```java
//   initialisation ; condition ; incrément
for (int i = 0; i < 5; i++) {
    System.out.println("Tour " + i); // affiche Tour 0 à Tour 4
}
// i n'existe plus ici
```

Parcours d'un tableau :

```java
int[] t = {4, 8, 15, 16, 23, 42};
int somme = 0;
for (int i = 0; i < t.length; i++) {
    somme += t[i];
}
```

Compter à rebours :

```java
for (int i = 10; i > 0; i--) {
    System.out.println(i);
}
```

### Boucle `for-each`

Pour parcourir tous les éléments sans avoir besoin de l'indice (lecture seule) :

```java
for (int valeur : t) {
    System.out.println(valeur);
}
```

### Boucle `while`

Utilisée quand on ne connaît **pas** le nombre d'itérations : on répète **tant que** la condition est vraie.
La condition est testée **avant** chaque tour (la boucle peut ne jamais s'exécuter).

```java
int n = 1;
while (n < 100) {
    n = n * 2;
}
System.out.println(n); // 128
```

### Boucle `do ... while`

Comme `while`, mais la condition est testée **après** : le corps est exécuté **au moins une fois**.

```java
int compteur = 0;
do {
    compteur++;
} while (compteur < 3);
```

### Contrôle de boucle

```java
for (int i = 0; i < 10; i++) {
    if (i == 2) {
        continue; // passe directement au tour suivant
    }
    if (i == 5) {
        break;    // sort immédiatement de la boucle
    }
    System.out.println(i); // affiche 0, 1, 3, 4
}
```

### Boucles imbriquées (ex. parcours d'une matrice)

```java
int[][] m = {{1, 2, 3}, {4, 5, 6}};
for (int i = 0; i < m.length; i++) {
    for (int j = 0; j < m[i].length; j++) {
        System.out.print(m[i][j] + " ");
    }
    System.out.println();
}
```

### ⚠️ Pièges

- **Boucle infinie** : penser à faire évoluer la variable testée dans un `while`.
- **Décalage d'indice** : `i < t.length` et non `i <= t.length`.

---

## 8. Les méthodes

Une méthode regroupe des instructions réutilisables. Sans Programmation Orientée Objet, on les déclare `public static`
dans la même classe que `main`.

### Signature

```java
public static  int      additionner(int a, int b) {
//  ↑      ↑     ↑          ↑            ↑
// visib. static  type de    nom       paramètres
//               retour                (type + nom)
    return a + b;
}
```

- **Type de retour** : le type de la valeur renvoyée par `return`.
- **Paramètres** : liste `type nom` séparés par des virgules (peut être vide).
- La **signature** au sens strict = nom + types des paramètres.

### Type de retour `void`

Une méthode qui **ne renvoie rien** est appelée une ***Procédure*** a le type `void`. Si elle renvoie quelque chose elle est appelée ***Fonction***.

Par abus de langage il peut arriver qu'une ***Procédure*** soit appelée ***Fonction***.

```java
public static void direBonjour(String prenom) {
    System.out.println("Bonjour " + prenom);
    // pas de return obligatoire dans une procédure (on peut écrire `return;` pour sortir plus tôt)
}
```

### Fonctions

```java
public static boolean estPair(int n) {
    return n % 2 == 0;
}

public static double moyenne(int[] notes) {
    int somme = 0;
    for (int note : notes) {
        somme += note;
    }
    return (double) somme / notes.length;
}

public static int[] doubler(int[] t) {
    int[] resultat = new int[t.length];
    for (int i = 0; i < t.length; i++) {
        resultat[i] = t[i] * 2;
    }
    return resultat;
}
```

Règles sur `return` :

- Une fonction non `void` doit renvoyer une valeur **dans tous les chemins possibles**.
- `return` **termine immédiatement** la fonction.

```java
public static String signe(int n) {
    if (n > 0) {
        return "positif";
    } else if (n < 0) {
        return "négatif";
    }
    return "nul"; // obligatoire : sinon un chemin n'aurait pas de return
}
```

### Appel d'une méthode

```java
public static void main(String[] args) {
    int s = additionner(3, 4);          // s vaut 7
    direBonjour("Ada");
    if (estPair(s)) { ... }
    double m = moyenne(new int[]{12, 15, 9});
}
```

### Passage des paramètres

Java passe toujours les paramètres **par valeur** (copie) :

- Pour un **type primitif**, la fonction travaille sur une copie : l'original n'est pas modifié.
- Pour un **tableau**, c'est la référence qui est copiée : la fonction **peut modifier** le contenu du tableau d'origine.

```java
public static void incrementer(int x) { x++; }
public static void mettreAZero(int[] t) { t[0] = 0; }

int n = 5;
incrementer(n);       // n vaut toujours 5

int[] tab = {9, 9};
mettreAZero(tab);     // tab vaut {0, 9}
```

### Portée dans les méthodes

Les paramètres et variables déclarés dans une fonction sont **locaux** : ils n'existent que pendant
l'exécution de cette fonction et ne sont pas visibles depuis `main` (et inversement).

---

## Récapitulatif express

| Notion          | À retenir                                                                 |
|-----------------|---------------------------------------------------------------------------|
| Types primitifs | 8 types : `byte short int long float double char boolean`                 |
| Cast            | Implicite si élargissement, `(type)` explicite si rétrécissement          |
| Division        | `int / int` = division entière → caster en `double` si besoin             |
| Conditions      | `if / else if / else`, `==` pour comparer, `.equals()` pour les `String`  |
| Portée          | Une variable vit dans son bloc `{ }` et les blocs imbriqués               |
| Tableaux        | Taille fixe, indices de `0` à `length - 1`                                |
| Boucles         | `for` (nb connu), `while` (condition), `do-while` (au moins une fois)     |
| Fonctions       | `static typeRetour nom(params)`, `void` si rien à renvoyer                |
