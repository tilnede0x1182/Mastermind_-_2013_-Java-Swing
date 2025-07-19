# Mastermind Java

## Description du projet (10 phrases)
Ce projet implémente une version Java du jeu Mastermind avec interface Swing, offrant une expérience graphique et interactive.
L’utilisateur peut choisir le rôle de **codeur** ou de **décodeur**, ou affronter une IA configurable selon trois niveaux de difficulté.
Le jeu supporte des configurations avancées : nombre de cases (4 à 8), jusqu’à **16 couleurs**, et nombre de coups limité ou illimité.
L’interface propose des menus de navigation, des fenêtres de saisie (nom du joueur, crédits, scores) et une gestion de sauvegarde/restauration de parties et de paramètres.
Les statistiques de chaque partie (coups, temps, date, score) sont enregistrées dans des fichiers et consultables via une fenêtre dédiée.
Trois modes de jeu adaptent la présentation des indices : du plus explicite (emplacements exacts et présences) au plus succinct (seulement présences).
Trois niveaux d’IA mettent en œuvre des algorithmes croissants en complexité : de l’aléatoire simple à une stratégie par minimax et entropie.
Un système de fichiers organise automatiquement les sauvegardes dans des répertoires nommés et numérotés.
Le code est structuré en plusieurs classes : UI (fenêtres, panels), IA (facile, moyen, difficile), utilitaires (fichiers, dates, chaînes).
L’ensemble est packagé dans un JAR exécutable, prêt à être intégré dans un portfolio ou déployé en application standalone.

## Technologies utilisées
- **Java SE 8** (JDK 1.8)
- **Swing** pour l’UI graphique
- **File I/O** (`java.io.File`, `FileWriter`, `BufferedReader`) pour la persistance
- **Collections standard** (`int[]`, `int[][]`) et algorithmes maison pour l’IA
- **Date/heure** : classes utilitaires internes pour horodatage

## Liste des fonctionnalités
- Choix du rôle : codeur ou décodeur (humain vs humain, humain vs IA).
- Sélection du **nombre de cases** (4 à 8) et **jusqu’à 16 couleurs**.
- Trois **niveaux de difficulté** IA : Facile, Moyen, Difficile.
- Trois **modes de jeu** avec variantes d’indices :
  - **Niveau 1** : indices détaillés (bien placés + bonnes couleurs, positions exactes).
  - **Niveau 2** : indices standards (nombre de pions bien placés + partiellement placés).
  - **Niveau 3** : indices réduits (seulement nombre de couleurs correctes, sans position).
- Gestion des **coups illimités** ou nombre de tentatives limité configurable.
- **Sauvegarde automatique** des parties et des paramètres dans `Sauvegardes/`.
- Chargement et classement automatique des fichiers de sauvegarde.
- Enregistrement et affichage des **statistiques** (score, temps, date).
- Fenêtres dédiées : Crédits, Saisie du nom, Score/Statistiques.
- Menu principal et navigation fluide entre écrans.

## Détails des modes de jeu
- **Choix des couleurs** : palette paramétrable de 4 à 16 couleurs distinctes pour composer le code.
- **3 niveaux d’IA** :
  1. **Facile** : génère des propositions aléatoires sans mémorisation des retours, fiable mais peu optimal.
  2. **Moyen** : filtre les possibilités en fonction des derniers retours (r1, r2) et choisit le coup qui maximise l’élimination.
  3. **Difficile** : utilise une approche de minimax/entropie pour minimiser le maximum des possibilités restantes à chaque tour.
- **3 niveaux de difficulté** pour le joueur (indice donné) :
  - **Niveau 1 (Débutant)** : reçoit à la fois le nombre de pions bien placés et le nombre de pions corrects mais mal placés.
  - **Niveau 2 (Intermédiaire)** : reçoit uniquement le total des pions corrects (bien ou mal placés) sans distinction.
  - **Niveau 3 (Expert)** : ne reçoit que le nombre de pions de la bonne couleur, sans aucune information de position.
- **Stratégies recommandées** :
  - Pour le **Débutant**, exploiter les indices détaillés pour tester chaque couleur une par une.
  - Pour l’**Intermédiaire**, alterner entre essais de séquences équilibrées et permutations pour affiner les présences.
  - Pour l’**Expert**, privilégier une démarche statistique d’élimination progressive des couleurs peu probables.

## Style graphique
- Interface **type application mobile 2013**, avec juxtapositions de panels fixes et boutons arrondis.
- Palette de **couleurs flashy** (fonds vifs, accents contrastés) pour distinguer les pions et la progression.
- Design **moderne et épuré**, misant sur des textes lisibles, des zones de saisie claires et des retours visuels nets.
