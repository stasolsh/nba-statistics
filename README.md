# NBA statistics
![Build](https://github.com/stasolsh/nba-statistics/actions/workflows/custom-action.yml/badge.svg)
![Coverage](https://codecov.io/gh/stasolsh/nba-statistics/branch/master/graph/badge.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Gradle](https://img.shields.io/badge/Gradle-8.12.1-02303A?logo=gradle&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5.11-red?logo=junit5)
![License](https://img.shields.io/badge/License-MIT-blue)

====
The idea of the project is to collect performance statistics among teams, coaches, and players.

The project has the following data model:
![db diagram.png](src/main/resources/docs/db%20diagram.png)

## Tech stack:
- Java 21
- Gradle
- Spring Boot
- Thymeleaf
- MySQL

## Instructions:

Steps how to start current service:

#### 1. Run nbs-statistics locally:
Go to \docker-local and run
```
run.bat|sh
```

#### 2. Link to start page is
```
 http://localhost:9090/index-player
```

#### 3. UI snippets are
![coaches.png](src/main/resources/docs/coaches.png)
![players.png](src/main/resources/docs/players.png)
![teams.png](src/main/resources/docs/teams.png)