<div align="center">

<h1>GameVault</h1>

<p>A REST API for tracking your video game library, playing status, and ratings.</p>

<p><strong>Java 21 · Spring Boot · PostgreSQL</strong></p>

<p>
  <a href="#features">Features</a> ·
  <a href="#getting-started">Getting started</a> ·
  <a href="#roadmap">Roadmap</a>
</p>

</div>

> This project is in an early stage of development.

## Features

- **Library tracking** — playing status and optional ratings from 0 to 10.
- **Game catalog** — create, browse, update, and delete games.
- **Accounts** — registration, session-based login/logout, and a current-user endpoint.
- **Data integrity** — request validation, unique accounts, and one library entry per user/game pair.

## Getting started

Requires **JDK 21** and **PostgreSQL** (developed with version 16). Commands use Bash; Windows users can run them in
WSL.

```bash
git clone https://github.com/adxria/gamevault.git
cd gamevault
cp src/main/resources/application.yaml.example src/main/resources/application.yaml
```

Create a PostgreSQL database and replace the datasource placeholders in `application.yaml` with your local connection
details. This file is ignored by Git. Hibernate currently manages development schema updates.

```bash
bash gradlew bootRun
```

The API runs at **http://localhost:8080**.

## Roadmap

- [x] **Catalog and library CRUD**
    - [x] Game catalog management
    - [x] Library entries with status and optional scores
    - [x] Request validation and database constraints

- [ ] **Authentication and access control**
    - [x] Registration and password hashing
    - [x] Session login/logout and CSRF protection
    - [x] Current-user endpoint
    - [ ] Entry ownership and catalog write permissions
    - [ ] Consistent API authentication responses

- [ ] **Quality and tooling**
    - [ ] Integration tests for accounts, sessions, and ownership
    - [ ] Versioned database migrations
    - [ ] Consistent error responses and improved logging
    - [ ] CI checks

- [ ] **Library features and demo**
    - [ ] Pagination, filtering, sorting, and title search
    - [ ] Progress tracking and richer game metadata
    - [ ] OpenAPI documentation
    - [ ] Docker Compose setup
