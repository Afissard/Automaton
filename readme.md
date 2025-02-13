# Application Automaton

## Introduction

Cette application permet aux utilisateurs d'interagir avec divers automates définis dans des fichiers JSON. Les utilisateurs peuvent vérifier des mots, générer des mots aléatoires et créer des fichiers DOT représentant les automates.

## Utilisation

1. **Ouvrez un terminal ou une invite de commande** :
   - Sur Windows, vous pouvez ouvrir l'invite de commande en appuyant sur `Win + R`, en tapant `cmd` et en appuyant sur `Entrée`.
   - Sur macOS ou Linux, ouvrez l'application Terminal.

2. **Naviguez jusqu'au répertoire** : Utilisez la commande `cd` pour naviguer jusqu'au répertoire où se trouve le fichier `MonApplication.jar`. Par exemple :
   ```sh
   cd chemin/vers/votre/fichier.jar
   ```

3. **Exécutez l'application** : Exécutez le fichier JAR en utilisant la commande suivante :
   ```sh
   java -jar Automaton-1.0.jar
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

2. **Démarrage et sélection d'un automate** :
```
Resource path not provided. Using default path: ./src/main/resources/
---------- Nantes Université, BUT2 Informatique, 2025 ------------
------- TP - Modélisation et analyse à l’aide des automates -------
------- Menu de l’application (développée par Sacha Chauvel) -------
1 	 abcAutomaton
2 	 abcExented
3 	 ddmmyyyy
4 	 hhmm
5 	 smiley
Votre choix (1-5) ?
```

1. **Choix du programme** :
```
automaton : ddmmyyyy : Automaton for dates in the format dd/mm/yyyy
Choisissez une option:
2. Vérifier un mot
3. Générer un mot
4. Créer un fichier .dot

```

5. **Vérifiez un mot** :
   ```
   Vous saisirez ensuite la chaîne à analyser, Merci
   ```

6. **Générez un mot** :
   ```
   mot généré : 01/01/2023
   ```

7. **Créez un fichier DOT** :
   ```
   Fichier .dot créé à l'emplacement: ./src/main/resources/ddmmyyyy.dot
   ```
