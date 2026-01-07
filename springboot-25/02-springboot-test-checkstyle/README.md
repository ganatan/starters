# springboot-starter

Application **Spring Boot** packagée en **JAR** et construite avec **Maven**.

---

## 🧩 Configuration du projet (pom.xml)

Avant toute compilation, vérifier et adapter les métadonnées du projet
dans le fichier `pom.xml` :

```xml
<artifactId>springboot-starter</artifactId>
<version>1.0.0</version>
<name>springboot-starter</name>
<description>Demo project for Spring Boot Starter</description>
<url/>
<licenses/>
```


## 📊 Dependency Updates

Vérifier les dépendances et plugins obsolètes :

```bash
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```

---

## 🏗️ Build

Compiler, exécuter les tests et packager l’application :

```bash
mvn clean install
```

Artefact généré :

```text
target/springboot-starter-1.0.0.jar
```

---

## 🚀 Run

Lancer l’application localement sur le port `3000` :

```bash
mvn spring-boot:run
```

Ou directement avec Java :

```bash
java -jar target/springboot-starter-1.0.0.jar
```

Accès :

```text
http://localhost:3000
```

---

## 📦 Commandes Maven usuelles

```bash
mvn clean                   # nettoyage du dossier target
mvn compile                 # compilation des sources
mvn test                    # exécution des tests
mvn package                 # génération de l’artefact
mvn install                 # installation en repo local
mvn dependency:tree         # affichage de l’arbre de dépendances
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
mvn spring-boot:run         # exécution Spring Boot
```
