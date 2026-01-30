# RAG Generator — Angular 20 & Spring Boot 3.5.5

<img src="./ui/ganatan-about-github.png" align="right" width="140" height="140" alt="ganatan logo">

---

## 🎯 Project Goals
- ✅ Build a complete **RAG (Retrieval-Augmented Generation)** architecture  
- ✅ **FullStack application**: **Angular 20.3.4** frontend and **Spring Boot 3.5.5 (Java 21)** backend  
- ✅ **GitLab CI/CD** integration and deployment on **OpenShift (Kubernetes)**  

---

**👉 Version française disponible ici** : [![Français](./ui/version-fr.png)](./README-fr.md)

---

## 📘 Table of Contents
- [Project Overview](#-project-overview)
- [Project Structure](#-project-structure)
- [Components](#-components)
- [Continuous Integration (CI)](#-continuous-integration-ci)
- [Docker Images](#-docker-images)
- [Frontend — Angular 20](#-frontend--angular-20)
- [Backend — Spring Boot 3.5.5](#-backend--spring-boot-355)
- [Docker](#-docker)
- [GitLab CI/CD](#-gitlab-cicd)
- [OpenShift Deployment](#-openshift-deployment)
- [Author & License](#-author--license)

---

## 🧱 Project Overview

**RAG Generator** is a complete demonstration of a **Retrieval-Augmented Generation (RAG)** architecture, ready for production.  
The application includes two independent modules:  
- `frontend-angular`: user interface  
- `backend-springboot`: API and business logic  

Each module can be built, tested, and deployed separately using **GitLab CI/CD**.  
Docker images are shared across local, CI, and **OpenShift** environments to ensure consistent execution everywhere.

---

## 🧬 Project Structure

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
│       └── (SQL scripts)
│
├── .gitlab-ci.yml
└── README.md
```

> Each pipeline handles linting, testing, packaging, Docker image creation,  
> and automatic deployment on OpenShift.

---

## 🧮 Components

| Component | Technology | Role |
|------------|-------------|------|
| Frontend | Angular 20 | User Interface |
| Backend | Spring Boot 3.5.5 / Java 21 | REST API, RAG orchestration |
| Database | PostgreSQL / Oracle | Context and embeddings storage |
| Registry | GitLab Container Registry | Docker image hosting |
| Cluster | OpenShift 4.x | Kubernetes-based deployment |

---

## 🔧 Continuous Integration (CI)

| Project | GitHub Actions | GitLab CI |
|----------|----------------|-----------|
| **Frontend Angular** | [![Frontend Angular CI](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-frontend-angular-ci.yml/badge.svg?branch=master)](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-frontend-angular-ci.yml) | [![GitLab Pipeline](https://gitlab.com/ganatan/sandbox/badges/master/pipeline.svg?job=frontend-angular)](https://gitlab.com/ganatan/sandbox/-/pipelines) |
| **Backend Spring Boot** | [![Backend Spring Boot CI](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-backend-springboot-ci.yml/badge.svg?branch=master)](https://github.com/ganatan/sandbox/actions/workflows/rag-generator-backend-springboot-ci.yml) | [![GitLab Pipeline](https://gitlab.com/ganatan/sandbox/badges/master/pipeline.svg?job=backend-springboot)](https://gitlab.com/ganatan/sandbox/-/pipelines) |

---

## 📦 Docker Images


| Component | Docker Hub | GitLab Container Registry |
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

Coverage report:  
`rag-generator/frontend-angular/coverage/angular-starter/index.html`

---

### Environment Configuration

The frontend behavior is controlled by the `useMock` flag defined in:

```typescript
// src/environments/environment.ts
export const environment = {
  useMock: true,
  backend: 'http://localhost:3000/api',
};
```

| Key | Type | Description |
|-----|------|--------------|
| `useMock` | `boolean` | Enables local mock mode (`true`) or real backend API mode (`false`) |
| `backend` | `string` | Backend API URL (default: `http://localhost:3000/api`) |

**Mock Mode (`useMock: true`)**  
→ The app loads local mock data instead of calling the backend.  

**API Mode (`useMock: false`)**  
→ The app sends requests to the Spring Boot backend API.

> Simply switch the `useMock` value in the environment file to toggle between mock and API modes.

---

### Main Commands

#### Development Mode (mock enabled by default)
```bash
npm run start
```
→ http://localhost:4200  
> Uses mock data locally.

#### Development Mode connected to backend
set `useMock: false` in `environment.ts`.  
→ http://localhost:4200  
> Sends requests to `http://localhost:3000/api`.

---

### Build & SSR

```bash
npm run build
npm run serve
```
→ http://localhost:4000

---

### Key Folder Structure

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

> `ai-service.ts` automatically checks `environment.useMock`  
> and switches between local mock data (`ai.mock.ts`) and real backend HTTP calls (`backend/api`).

---

## ☕ Backend — Spring Boot 3.5.5

### Code Quality Check
```bash
cd sandbox
cd rag-generator/backend-springboot
mvn checkstyle:check
```

### Unit Tests & Coverage
```bash
mvn clean test
mvn jacoco:report
```

Coverage report:  
`rag-generator/backend-springboot/target/site/jacoco/index.html`

---

### Configuration

Backend behavior is controlled by the `use.mock` flag in `application.properties`.

```properties
spring.application.name=backend-springboot
server.port=3000

# Toggle between mock and full API + database
use.mock=true

# LLM API keys (only used when use.mock=false)
openai.api.key=sk-your-openai-api-key
anthropic.api.key=claude-your-key
```

#### When `use.mock=true`
- The backend uses **local mocks** under `com.ganatan.starter.mock.llm.*`
- No external API or database connection

#### When `use.mock=false`
- The backend uses **real API keys** to call OpenAI / Claude

---

### Oracle Database Configuration

Example configuration for Oracle XE:

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

### REST Endpoints Overview

| Endpoint | Method | Description |
|-----------|---------|--------------|
| `/api/persons` | **GET** | Returns all persons |
| `/api/persons/{id}` | **GET** | Returns a specific person |
| `/api/persons` | **POST** | Creates a new person |
| `/api/persons/{id}` | **PUT** | Updates an existing person |
| `/api/persons/{id}` | **DELETE** | Deletes a person by ID |

> The `/api/persons` endpoints are available  
> and connect to the Oracle database via JPA/Hibernate.

---

### LLM Integration

| Mode | API Calls | Description |
|------|------------|--------------|
| **Mock Mode (`use.mock=true`)** | Disabled | Uses local responses from `mock/llm/` |
| **Real Mode (`use.mock=false`)** | Enabled | Calls OpenAI / Claude with real API keys |

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

> Qdrant stores and retrieves text embeddings used by the RAG pipeline to improve retrieval accuracy.

### Build & Run

#### Standard Execution
```bash
mvn clean install
mvn spring-boot:run
```

#### Run from JAR
```bash
java -jar target/backend-springboot-1.0.0.jar
```
→ http://localhost:3000

---

## 🐳 Docker

### Build Images
```bash
cd sandbox
cd rag-generator/frontend-angular
docker build -t frontend-angular:latest -f docker/Dockerfile.frontend-angular .

cd ../backend-springboot
docker build -t backend-springboot:latest -f docker/Dockerfile.backend-springboot .
```

### Run Containers
```bash
docker run -d --name frontend-angular -p 4000:4000 frontend-angular:latest
docker run -d --name backend-springboot -p 3000:3000 backend-springboot:latest
```

→ Frontend: http://localhost:4000  
→ Backend: http://localhost:3000

---

## 🚀 GitLab CI/CD

`.gitlab-ci.yml` includes both pipelines:

```yaml
include:
  - local: .gitlab/rag-generator-frontend-angular-ci.yml
  - local: .gitlab/rag-generator-backend-springboot-ci.yml
```

Each pipeline performs:
- Lint  
- Unit Tests  
- Build  
- Docker Image Creation  
- Push to Registry  
- OpenShift Deployment  

---

## 👤 Author & License

**Author:** Danny — [www.ganatan.com](https://www.ganatan.com)  
**License:** MIT  
