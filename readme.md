# Application Automaton

## Introduction

Cette application permet aux utilisateurs d'interagir avec divers automates définis dans des fichiers JSON. Les utilisateurs peuvent vérifier des mots, générer des mots aléatoires et créer des fichiers DOT représentant les automates.

## Prérequis

- Java Runtime Environment (JRE) installé sur votre ordinateur.
- Le fichier JAR exécutable de l'application.

## Installation

1. **Téléchargez le fichier JAR** : Obtenez le fichier `MonApplication.jar` à partir de la source ou du dépôt fourni.

2. **Préparez les ressources** : Assurez-vous d'avoir un répertoire contenant les fichiers JSON qui définissent les automates. Le chemin par défaut est `./src/main/resources/`.

## Utilisation

1. **Ouvrez un terminal ou une invite de commande** :
   - Sur Windows, vous pouvez ouvrir l'invite de commande en appuyant sur `Win + R`, en tapant `cmd` et en appuyant sur `Entrée`.
   - Sur macOS ou Linux, ouvrez l'application Terminal.

2. **Naviguez jusqu'au répertoire** : Utilisez la commande `cd` pour naviguer jusqu'au répertoire où se trouve le fichier `MonApplication.jar`. Par exemple :
   ```sh
   cd chemin/vers/votre/fichier/jar
   ```

3. **Exécutez l'application** : Exécutez le fichier JAR en utilisant la commande suivante :
   ```sh
   java -jar MonApplication.jar
   ```

4. **Suivez les instructions à l'écran** :
   - L'application affichera un menu avec les automates disponibles.
   - Sélectionnez un automate en entrant le numéro correspondant.
   - Choisissez une option dans le menu pour vérifier un mot, générer un mot ou créer un fichier DOT.
   - Suivez les invites pour entrer les informations requises.

## Exemple

Voici un exemple de la façon d'exécuter l'application :

1. **Démarrez l'application** :
   ```sh
   java -jar MonApplication.jar
   ```

2. **Sélectionnez un automate** :
```
1 	 abcAutomaton
2 	 ddmmyyyy
3 	 hhmm
4 	 smiley
Votre choix (1-4) ?
```

3. **Choisissez une option** :
   ```
   1. Vérifier un mot
   2. Générer un mot
   3. Créer un fichier .dot
   4. Quitter
   ```

4. **Vérifiez un mot** :
   ```
   Vous saisirez ensuite la chaîne à analyser, Merci
   ```

5. **Générez un mot** :
   ```
   mot généré : 01/01/2023
   ```

6. **Créez un fichier DOT** :
   ```
   Fichier .dot créé à l'emplacement: ./src/main/resources/ddmmyyyy.dot
   ```
