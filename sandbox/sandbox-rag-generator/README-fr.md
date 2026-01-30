# 🧠 RAG Generator — Angular 20 & Spring Boot 3.5.5

<img src="./ui/ganatan-about-github.png" align="right" width="140" height="140" alt="logo ganatan">

---

## 🎯 Objectifs du projet
- ✅ Construire une architecture complète **RAG (Retrieval-Augmented Generation)**  
- ✅ Application **FullStack** : **Angular 20.3.4** en frontend et **Spring Boot 3.5.5 (Java 21)** en backend  
- ✅ Intégration **GitLab CI/CD** et déploiement sur **OpenShift (Kubernetes)**  

---

**👉 English version available here:** [![English](./ui/version-en.png)](./README.md)

---

## 📘 Sommaire
- [Présentation du projet](#-présentation-du-projet)
- [Structure du projet](#-structure-du-projet)
- [Composants](#-composants)
- [Intégration Continue (CI)](#-intégration-continue-ci)
- [Images Docker](#-images-docker)
- [Frontend — Angular 20](#-frontend--angular-20)
- [Backend — Spring Boot 3.5.5](#-backend--spring-boot-355)
- [Docker](#-docker)
- [GitLab CI/CD](#-gitlab-cicd)
- [Déploiement OpenShift](#-déploiement-openshift)
- [Auteur & Licence](#-auteur--licence)

---

## 🧬 Présentation du projet

**RAG Generator** est une démonstration complète d’une architecture **RAG (Retrieval-Augmented Generation)** prête pour la production.  
L’application se compose de deux modules indépendants :  
- `frontend-angular` : interface utilisateur  
- `backend-springboot` : API et logique métier  

Chaque module peut être construit, testé et déployé séparément via **GitLab CI/CD**.  
Les images Docker sont utilisées aussi bien en local qu’en CI et sur **OpenShift**, pour garantir une exécution identique partout.

---

## 🧩 Structure du projet

```
sandbox/
├── .github/
│   └── workflows/
│       ├── rag-generator-frontend-angular-ci.yml
│       └── rag-generator-backend-springboot-ci.yml
│
├── .gitlab/
│   ├── rag-generator-frontend-angular-ci.yml
│   └── rag-generator-backend-springboot-ci.yml
│
├── k8s/
│   ├── rag-generator-frontend-angular-deployment.yml
│   └── rag-generator-backend-springboot-deployment.yml
│
├── rag-generator/
│   ├── backend-springboot/
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── docker/Dockerfile.backend-springboot
│   │
│   ├── frontend-angular/
│   │   ├── src/
│   │   ├── package.json
│   │   └── docker/Dockerfile.frontend-angular
│   │
│   └── databases/
│       └── (scripts SQL)
│
├── .gitlab-ci.yml
└── README.md
```

> Chaque pipeline gère le linting, les tests, le packaging, la création d’images Docker  
> et le déploiement automatique sur OpenShift.

---

## 🧮 Composants

| Composant | Technologie | Rôle |
|------------|-------------|------|
| Frontend | Angular 20 | Interface utilisateur |
| Backend | Spring Boot 3.5.5 / Java 21 | API REST, orchestration RAG |
| Base de données | PostgreSQL / Oracle | Stockage du contexte et des embeddings |
| Registry | GitLab Container Registry | Hébergement des images Docker |
| Cluster | OpenShift 4.x | Déploiement Kubernetes |

---

## 🔧 Intégration Continue (CI)

| Projet  | GitHub Actions | GitLab CI |
|----------|----------------|-----------|
| **Frontend Angular** | [![Frontend Angular CI](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-frontend-angular-ci.yml/badge.svg?branch=master)](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-frontend-angular-ci.yml) | [![GitLab Pipeline](https://gitlab.com/ganatan/sandbox/badges/master/pipeline.svg?job=frontend-angular)](https://gitlab.com/ganatan/sandbox/-/pipelines) |
| **Backend Spring Boot** | [![Backend Spring Boot CI](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-backend-springboot-ci.yml/badge.svg?branch=master)](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-backend-springboot-ci.yml) | [![GitLab Pipeline](https://gitlab.com/ganatan/sandbox/badges/master/pipeline.svg?job=backend-springboot)](https://gitlab.com/ganatan/sandbox/-/pipelines) |

---

## 📦 Images Docker

| Composant | Docker Hub | GitLab Container Registry |
|------------|-------------|----------------------------|
| **Frontend Angular** | [![Docker Hub](https://img.shields.io/badge/Docker%20Hub-frontend--angular-blue?logo=docker&logoColor=white)](https://hub.docker.com/r/ganatan/rag-generator-frontend-angular) | [![GitLab Registry](https://img.shields.io/badge/GitLab%20Registry-frontend--angular-orange?logo=gitlab&logoColor=white)](https://gitlab.com/ganatan/sandbox/container_registry) |
| **Backend Spring Boot** | [![Docker Hub](https://img.shields.io/badge/Docker%20Hub-backend--springboot-blue?logo=docker&logoColor=white)](https://hub.docker.com/r/ganatan/rag-generator-backend-springboot) | [![GitLab Registry](https://img.shields.io/badge/GitLab%20Registry-backend--springboot-orange?logo=gitlab&logoColor=white)](https://gitlab.com/ganatan/sandbox/container_registry) |

---

## 🧩 Frontend — Angular 20

### Installation
```bash
git clone https://github.com/ganatan/sandbox.git
cd sandbox
cd rag-generator/frontend-angular
npm ci
```

### Lint & Tests
```bash
npm run lint
npm run test
npm run coverage
```

Rapport de couverture :  
`rag-generator/frontend-angular/coverage/angular-starter/index.html`

---

### Configuration d’environnement

Le comportement du frontend est contrôlé par la variable `useMock` définie dans :

```typescript
// src/environments/environment.ts
export const environment = {
  useMock: true,
  backend: 'http://localhost:3000/api',
};
```

| Clé | Type | Description |
|-----|------|-------------|
| `useMock` | `boolean` | Active le mode **mock local** (`true`) ou l’API backend réelle (`false`) |
| `backend` | `string` | URL du backend (par défaut : `http://localhost:3000/api`) |

**Mode mock (`useMock: true`)**  
→ L’application utilise des données locales simulées au lieu d’appeler le backend.  

**Mode API (`useMock: false`)**  
→ L’application envoie les requêtes au backend Spring Boot.

> Il suffit de modifier la valeur de `useMock` dans le fichier d’environnement pour basculer entre mock et API.

---

### Commandes principales

#### Mode développement (mock activé par défaut)
```bash
npm run start
```
→ http://localhost:4200  
> Utilise les données mock locales.

#### Mode développement connecté au backend
mettre `useMock: false` dans `environment.ts`.  
→ http://localhost:4200  
> Envoie les requêtes vers `http://localhost:3000/api`.

---

### Build & SSR

```bash
npm run build
npm run serve
```
→ http://localhost:4000

---

### Structure des dossiers

```
src/
├── app/
│   ├── core/
│   │   └── services/
│   │       ├── ai-service.ts
│   │       ├── ai-service.spec.ts
│   │       └── ai.mock.ts
│   ├── app.ts
│   ├── app.routes.ts
│   └── app.config.ts
├── environments/
│   ├── environment.ts
│   └── environment.development.ts
└── main.ts
```

> `ai-service.ts` vérifie automatiquement `environment.useMock`  
> et bascule entre les données locales (`ai.mock.ts`) et les appels HTTP réels (`backend/api`).

---

## ☕ Backend — Spring Boot 3.5.5

### Qualité du code
```bash
cd sandbox
cd rag-generator/backend-springboot
mvn checkstyle:check
```

### Tests unitaires et couverture
```bash
mvn clean test
mvn jacoco:report
```

Rapport de couverture :  
`rag-generator/backend-springboot/target/site/jacoco/index.html`

---

### ⚙️ Configuration

Le comportement du backend est contrôlé par la propriété `use.mock` dans le fichier `application.properties`.

```properties
spring.application.name=backend-springboot
server.port=3000

# Bascule entre mode mock et mode complet (API + base de données)
use.mock=true

# Clés API LLM (utilisées uniquement quand use.mock=false)
openai.api.key=sk-your-openai-api-key
anthropic.api.key=claude-your-key
```

#### Quand `use.mock=true`
- Le backend utilise les **mocks locaux** situés dans `com.ganatan.starter.mock.llm.*`
- Aucune connexion à une API externe ni à une base de données

#### Quand `use.mock=false`
- Le backend utilise les **vraies clés API** pour appeler OpenAI / Claude

---

### 🗄️ Configuration de la base Oracle

Exemple de configuration pour Oracle XE :

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=system
spring.datasource.password=Trustno1
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

### 🧩 Endpoints REST

| Endpoint | Méthode | Description |
|-----------|----------|--------------|
| `/api/persons` | **GET** | Retourne toutes les personnes |
| `/api/persons/{id}` | **GET** | Retourne une personne spécifique |
| `/api/persons` | **POST** | Crée une nouvelle personne |
| `/api/persons/{id}` | **PUT** | Met à jour une personne existante |
| `/api/persons/{id}` | **DELETE** | Supprime une personne par ID |

> Les endpoints `/api/persons` sont disponibles uniquement quand `use.mock=false`  
> et utilisent la base Oracle via JPA/Hibernate.

---

### 🧠 Intégration LLM

| Mode | Appels API | Description |
|------|-------------|--------------|
| **Mode mock (`use.mock=true`)** | Désactivés | Utilise les réponses locales de `mock/llm/` |
| **Mode réel (`use.mock=false`)** | Activés | Appelle OpenAI / Claude avec les clés API réelles |

---

## Qdrant Vector Database

**Docker Compose example:**

```yaml
services:
  qdrant:
    image: qdrant/qdrant
    container_name: qdrant
    ports:
      - "6333:6333" # REST API
      - "6334:6334" # gRPC API
    volumes:
      - ./qdrant_storage:/qdrant/storage
```

**Default connection:**
```properties
qdrant.host=localhost
qdrant.port=6333
```

### 🏗️ Build & Exécution

#### Exécution standard
```bash
mvn clean install
mvn spring-boot:run
```

#### Exécution depuis le JAR
```bash
java -jar target/backend-springboot-1.0.0.jar
```
→ http://localhost:3000

---

## 🐳 Docker

### Construction des images
```bash
cd sandbox
cd rag-generator/frontend-angular
docker build -t frontend-angular:latest -f docker/Dockerfile.frontend-angular .

cd ../backend-springboot
docker build -t backend-springboot:latest -f docker/Dockerfile.backend-springboot .
```

### Exécution des conteneurs
```bash
docker run -d --name frontend-angular -p 4000:4000 frontend-angular:latest
docker run -d --name backend-springboot -p 3000:3000 backend-springboot:latest
```

→ Frontend : http://localhost:4000  
→ Backend : http://localhost:3000

---

## 🚀 GitLab CI/CD

`.gitlab-ci.yml` inclut les deux pipelines :

```yaml
include:
  - local: .gitlab/rag-generator-frontend-angular-ci.yml
  - local: .gitlab/rag-generator-backend-springboot-ci.yml
```

Chaque pipeline effectue :
- Lint  
- Tests unitaires  
- Build  
- Création d’image Docker  
- Push vers le Registry  
- Déploiement sur OpenShift  

---

## 👤 Auteur & Licence

**Auteur :** Danny — [www.ganatan.com](https://www.ganatan.com)  
**Licence :** MIT
