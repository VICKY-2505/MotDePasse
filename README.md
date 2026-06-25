# Générateur de mots de passe sécurisés

Application en ligne de commande (CLI) développée en **Java 21**. Elle permet de générer des mots de passe personnalisés, puis d’évaluer leur robustesse via un service isolé exécuté dans **Docker**.

## Présentation

Le projet sépare deux responsabilités :

- **Java** gère l’interaction avec l’utilisateur et la génération des mots de passe ;
- **Docker** exécute l’analyse de sécurité dans un conteneur dédié.

Cette architecture isole la validation du reste de l’application et s’inscrit dans une approche DevOps basée sur des services indépendants.

## Fonctionnalités

- Choix de la **longueur** du mot de passe (minimum 8 caractères, maximum 32) ;
- Activation ou non des **majuscules**, **minuscules**, **chiffres** et **symboles** ;
- Génération d’**un ou plusieurs** mots de passe (mode rafale) ;
- **Évaluation de la force** de chaque mot de passe via Docker ;
- Interface **CLI interactive** avec gestion des erreurs de saisie.

### Niveaux de force affichés

| Score | Indicateur   |
|------:|--------------|
| 0     | TRES_FAIBLE  |
| 1     | FAIBLE       |
| 2     | MOYEN        |
| 3     | FORT         |
| 4     | TRES_FORT    |

Le score est calculé dans le conteneur à partir de la longueur du mot de passe et de la présence de majuscules, chiffres et caractères spéciaux.

## Structure du projet

```text
MotDePasse/
├── Docker/
│   ├── Dockerfile
│   └── PasswordAnalyser.java
├── src/
│   ├── Main.java
│   ├── GenerateurMotDePasse.java
│   └── VerificateurSecurite.java
└── README.md
```

## Analyse technique

### `Main.java`

Point d’entrée de l’application. Elle collecte les paramètres utilisateur (longueur, types de caractères, quantité), valide les saisies, lance la génération et affiche chaque mot de passe avec son niveau de force.

### `GenerateurMotDePasse.java`

Contient la logique de génération. Elle s’appuie sur `SecureRandom`, adapté à la création de données sensibles, et construit le mot de passe à partir des jeux de caractères sélectionnés par l’utilisateur.

### `VerificateurSecurite.java`

Lance le conteneur Docker `password` via `ProcessBuilder`, transmet le mot de passe généré et récupère le résultat de l’analyse. En cas de problème (Docker arrêté, image absente, etc.), un message d’erreur explicite est renvoyé.

### `PasswordAnalyser.java` (Docker)

Programme Java exécuté dans le conteneur. Il évalue le mot de passe reçu en argument et affiche l’indicateur de force correspondant.

## Prérequis

- [Java 21](https://adoptium.net/)
- [Docker](https://www.docker.com/) (Docker Desktop ou moteur Docker en cours d’exécution)

## Installation

### 1. Construire l’image Docker

Depuis la racine du projet :

```powershell
docker build -t password -f Docker/Dockerfile Docker
```

### 2. Tester le conteneur seul

```powershell
docker run --rm password "MonMotDePasse123!"
```

La commande doit afficher un indicateur de force (`TRES_FAIBLE`, `FAIBLE`, `MOYEN`, `FORT` ou `TRES_FORT`).

## Compilation et exécution

### Compiler les sources Java

```powershell
javac -d out src/*.java
```

### Lancer l’application

```powershell
java -cp out Main
```

L’application vous guidera pas à pas dans le terminal :

1. Saisir la longueur souhaitée (≥ 8) ;
2. Indiquer le nombre de mots de passe à générer ;
3. Choisir les types de caractères à inclure (`oui` / `non`) ;
4. Consulter les mots de passe générés et leur force.

## Technologies utilisées

- Java 21
- Docker
- `SecureRandom` (génération cryptographique)
- `ProcessBuilder` (communication avec Docker)

## Messages d’erreur possibles

| Message                    | Cause probable                                      |
|----------------------------|-----------------------------------------------------|
| `DOCKER_NON_DEMARRE`       | Le démon Docker n’est pas démarré                   |
| `IMAGE_DOCKER_INTROUVABLE` | L’image `password` n’a pas été construite           |
| `ERREUR_DOCKER`            | Échec de communication ou d’exécution du conteneur  |


