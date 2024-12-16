# Higher Lower Movie Game

**A fun and challenging game to test your movie knowledge by comparing various categories of movies!**

## Overview

The `higher_lower_movie_game` is a Spring Boot application where users compare random movies based on a selected category such as **popularity**, **revenue**, **vote average**, or **runtime**. The game supports both **console** and **web interfaces** and offers two modes: **normal** and **hard**.

The game is simple yet addictive:
1. Select a category and a mode to start.
2. Compare the category values of two random movies.
3. Keep scoring points until you make a mistake!

In **hard mode**, the challenge increases as category values get closer, making it harder to guess.

## Features
- **Category Selection:** Compare movies based on `popularity`, `revenue`, `vote_average`, or `runtime`.
- **Game Modes:** Choose between **normal** and **hard** difficulty.
- **Dynamic Scoring:** Track your **current** and **highest scores**.
- **Persistence:** Movies are stored in an **H2 database**, initialized using `schema.sql`.
- **User Experience:** Restart from category selection upon an incorrect answer.
- **API Support:** Play via the web with Swagger documentation available.
- **Unit Testing:** Basic unit tests have been implemented.
- **SOLID Principles:** Code follows SOLID design principles for maintainability and extensibility.

## Technologies Used
- **Spring Boot**
- **H2 Database**
- **Swagger** for API documentation
- **JUnit** for unit testing
- Java 17 (JDK 17 or higher)

## Getting Started

### Installation
1. Build the project:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Playing the Game
- **Console Mode:** Follow the prompts in the terminal to select a category and mode.
- **Web Mode:** Access the web interface at `http://localhost:8080`. Use Swagger for API testing at:
  [Swagger UI](http://localhost:8080/swagger-ui/index.html).

## API Endpoints
- **Start New Game:** `POST http://localhost:8080/higler-lower-game/new-game`
- **Get Random Movie:** `GET http://localhost:8080/higler-lower-game/movie`

Refer to Swagger for detailed documentation and example requests.

## Database Initialization
The application uses an **H2 database**. Upon startup, `schema.sql` initializes the database with random movie data.

## Configuring Console and Web App

**You can control the logging and which part of the application to run (console or web app) using the following settings in application.properties:**
1. Run Web App Only: To hide console logs when running the web app, set:

**application.properties:**
# spring.main.web-application-type=none
# logging.level.com.example.higherlowermoviegame.console=off

2. Run Both Console and Web App: If you want to run both the console and web app, simply comment out or remove the line:

**application.properties:**
# spring.main.web-application-type=none
**This will enable both the console and web application to run simultaneously.**

