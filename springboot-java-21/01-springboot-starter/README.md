# springboot-starter

Spring Boot application packaged as a **JAR** and **Maven build**.

---

## 📊 Dependency Updates

Check for outdated dependencies and plugins:

```bash
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```

---

## 🏗️ Build

Compile, run tests, and package the application:

```bash
mvn clean install
```

Generates the artifact:

```
target/springboot-starter-1.0.0.jar
```

---

## 🚀 Run

Run the application locally on port `3000`:

```bash
mvn spring-boot:run
```

Or directly with Java:

```bash
java -jar target/springboot-starter-1.0.0.jar
```

Access at:

```
http://localhost:3000
```

---

## 📦 Common Maven Commands

```bash
mvn clean                  # clean target directory
mvn compile                # compile sources
mvn test                   # run tests
mvn package                # build the artifact
mvn install                # install to local repository
mvn checkstyle:check       # run static analysis
mvn dependency:tree        # view dependency tree
mvn spring-boot:run        # run Spring Boot app
```
