# springboot-starter

Application **Spring Boot** packagée en **JAR** et construite avec **Maven**.  
Projet minimal servant de base technique (architecture, qualité, tooling).

---

## 🧩 Pré-requis

- Java **25**
- Maven **3.9+**

Vérification :

```bash
java -version
mvn -v
```

---

## 📊 Dependency Updates

Vérifier les dépendances et plugins obsolètes :

```bash
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```

---

## 🏗️ Build (tests + qualité + packaging)

Compiler, exécuter les tests, appliquer les règles de qualité et packager l’application :

```bash
mvn clean install
```

Cette commande :
- exécute les tests
- génère le **coverage JaCoCo**
- applique **Checkstyle**
- échoue si une règle est violée

Artefact généré :

```text
target/springboot-starter-1.0.0.jar
```

---

## 📈 Coverage (JaCoCo)

Génération du coverage :

```bash
mvn test
```

Rapport HTML :

```text
target/site/jacoco/index.html
```

Notes :
- Java 25 → JaCoCo **0.8.14 minimum**
- le dossier `tools/` est exclu du coverage

---

## ✅ Qualité (Checkstyle)

Lancement de Checkstyle :

```bash
mvn verify
```

Fichier de configuration attendu :

```text
checkstyle.xml
```

---

## 🚀 Run

### Exécution via Maven

```bash
mvn spring-boot:run
```

### Exécution via Java

```bash
java -jar target/springboot-starter-1.0.0.jar
```

Accès par défaut :

```text
http://localhost:8080
```

---

## 📦 Commandes Maven usuelles

```bash
mvn clean                   # nettoyage du dossier target
mvn compile                 # compilation des sources
mvn test                    # exécution des tests + coverage (JaCoCo)
mvn package                 # génération de l’artefact
mvn install                 # installation en repository local
mvn dependency:tree         # affichage de l’arbre de dépendances
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
mvn spring-boot:run         # exécution Spring Boot
```

---

## 🗂️ Structure du projet

```text
.
├── .gitattributes
├── mvnw
├── mvnw.cmd
├── pom.xml
├── checkstyle.xml
└── src
    ├── main
    │   ├── java
    │   │   ├── com
    │   │   │   └── ganatan
    │   │   │       └── starter
    │   │   │           ├── StarterApplication.java
    │   │   │           └── api
    │   │   │               └── root
    │   │   │                   └── RootController.java
    │   │   └── tools
    │   │       └── GenerateProjectStructure.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── ganatan
                    └── starter
                        └── StarterApplicationTests.java
```

---

## 🎯 Objectif du projet

- base Spring Boot **simple et maîtrisée**
- configuration Maven claire
- qualité intégrée dès le départ (tests, coverage, checkstyle)
- point de départ pour une architecture plus avancée (DDD / Clean)
