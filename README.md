# NBA Statistics

![Build](https://github.com/stasolsh/nba-statistics/actions/workflows/custom-action.yml/badge.svg)
![Coverage](https://codecov.io/gh/stasolsh/nba-statistics/branch/master/graph/badge.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Gradle](https://img.shields.io/badge/Gradle-8.12.1-02303A?logo=gradle&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![JUnit](https://img.shields.io/badge/JUnit-5.11-red?logo=junit5)
![License](https://img.shields.io/badge/License-MIT-blue)

---

## Overview

NBA Statistics is a Spring Boot web application for managing and analyzing NBA teams, coaches, and players.

The application provides:
- Team management
- Coach management
- Player management
- Player filtering and statistics
- Search functionality
- Pagination support
- Integration and repository tests

---

## Database Model

![Database Diagram](src/main/resources/docs/db%20diagram.png)

### Relationships
- One team can have many players
- One team can have many coaches

---

## Tech Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Gradle
- MySQL
- H2 Database (tests)
- JUnit 5
- MockMvc
- JaCoCo
- GitHub Actions

---

## Features

### Teams
- Create team
- Update team
- Delete team
- Pagination support

### Coaches
- Create coach
- Update coach
- Delete coach
- Team assignment

### Players
- Create player
- Update player
- Delete player
- Filter players by name
- Search players by team and age range
- Pagination support

---

## Running the Application

### Run locally with Docker

Go to:

```bash
docker-local
```

Run:

```bash
run.sh
```

or on Windows:

```bash
run.bat
```

---

## Application URL

```text
http://localhost:9090/index-player
```

---

## Running Tests

Run all tests:

```bash
./gradlew clean test
```

Generate JaCoCo report:

```bash
./gradlew clean test jacocoTestReport
```

Open coverage report:

```text
build/reports/jacoco/test/html/index.html
```

---

## CI/CD

The project uses GitHub Actions for:
- Build verification
- Automated testing
- Code coverage checks

---

## UI Preview

### Coaches

![Coaches](src/main/resources/docs/coaches.png)

---

### Players

![Players](src/main/resources/docs/players.png)

---

### Teams

![Teams](src/main/resources/docs/teams.png)

---

## Future Improvements

- REST API support
- Swagger/OpenAPI documentation
- Authentication and authorization
- Player statistics charts
- Advanced search filters
- Docker Compose support
- Kubernetes deployment
- Redis caching

---

## Author

Stanislav Olshanskyi