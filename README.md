# Les 5 vinasses gouleyantes de M. Opéra : apprendre à coder en jouant (Java)

*Note : Ce projet a été initialement développé sur le GitLab de l'IUT puis importé ici pour archivage, d'où l'absence d'historique de commits*

Jeu pédagogique en Java développé en équipe de quatre pour la Journée Portes Ouvertes (JPO 2025) de l'IUT. Le visiteur retrouve cinq bouteilles de vin cachées dans les sous-sols de l'IUT, et pour avancer il écrit lui-même quelques lignes de code dans NetBeans. Chaque niveau fait découvrir une notion de base de la programmation, des fonctions jusqu'aux boucles.

## Contenu du dépôt

| Élément | Description |
|---------|-------------|
| `JPO.pdf` | Manuel du jeu, avec l'histoire, l'introduction à NetBeans et l'énoncé de chaque niveau |
| `Les 5 vinasses gouleyantes de M.Opéra/` | Projet à compléter, dont les fonctions des niveaux sont vides |
| `Les 5 vinasses gouleyantes de M.Opéra - CORRIGE/` | Le même projet avec la solution de chaque niveau |
| `lib/` | Dépendances incluses, soit `EcranProg.jar` (fournie par un enseignant) et JavaFX SDK 17.0.10 (Linux x64) |

## Le jeu

Le joueur se déplace sur un plateau en grille. Les murs (marron) bloquent le passage, le chemin (vert) est sûr et l'arrivée est jaune. Les trous (noirs) et les lasers (rouges et violet) font recommencer le niveau, et les buissons (vert foncé) barrent la route.

| Niveau | Amulette | Notion abordée | Mission |
|--------|----------|----------------|---------|
| 1 | Le Réveil | Fonctions | Appeler `seDeplacer()` |
| 2 | Mobilité | Paramètres | Donner une puissance au `dash(int)` |
| 3 | Force | Variables | Créer la variable `"Buisson"` et couper les buissons avec `couper()` |
| 4 | Vigilance | Conditions `if` | Éteindre le laser seulement s'il est allumé |
| 5 | Infini | Boucles `for` | Éteindre tous les lasers, en combinant tout ce qui précède |

## Fonctionnalités techniques

- **Moteur de jeu orienté objet :** Classes `Plateau`, `Case`, `Position`, `Joueur` et `Ecran`, avec gestion des déplacements, du dash, de la coupe des buissons et des lasers.
- **Fonctions simplifiées pour les visiteurs :** `Main.java` expose seulement `seDeplacer()`, `dash()`, `couper()`, `eteindreLaser()` et `estAllume()`, qui masquent la complexité du moteur. Le visiteur complète les fonctions `niveau1()` à `niveau5()`.
- **Enchaînement des niveaux :** Le code écrit aux niveaux précédents est rejoué automatiquement quand il est nécessaire pour finir le niveau en cours.
- **Sauvegarde de la progression :** La classe `SaveFile` mémorise le niveau atteint dans un fichier.
- **Affichage :** Grille et interactions (clic, clavier) gérées avec la bibliothèque `EcranProg`, fournie par un enseignant.

## Compétences mises en œuvre

Programmation orientée objet en Java, conception d'un moteur de jeu et d'une interface de fonctions simplifiée, interface graphique avec JavaFX, gestion d'une sauvegarde, travail en équipe avec Git (GitLab), vulgarisation de notions de programmation pour des débutants.

## Prérequis

- **Java JDK 21**
- **NetBeans**, ou **Apache Ant** pour lancer en ligne de commande

Les dépendances sont déjà incluses dans le dossier `lib/`, à côté des deux projets. Le SDK JavaFX fourni est la version Linux x64. Sous Windows ou macOS, il faut télécharger le SDK correspondant et adapter les chemins dans les propriétés du projet.

## Installation et lancement

```bash
git clone https://github.com/Chocopter/JPO.git
cd JPO
```

**Projet corrigé** (depuis la racine du dépôt) :

```bash
cd "Les 5 vinasses gouleyantes de M.Opéra - CORRIGE"
ant run
```

**Projet à compléter** (depuis la racine du dépôt) :

```bash
cd "Les 5 vinasses gouleyantes de M.Opéra"
# Compléter les fonctions niveau1() à niveau5() dans Main.java
ant run
```

Dans le projet à compléter, le jeu n'est fonctionnel qu'une fois les fonctions des niveaux remplies, comme expliqué dans `JPO.pdf`.

## Auteurs

Adrian Styzinski, Dennis Bousdira, Sonesay Khanthavilayraksa et Simon Foussat-Ferré.