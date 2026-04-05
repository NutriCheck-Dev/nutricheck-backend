# NutriCheck Backend

[![maven-build](https://github.com/NutriCheck-Dev/nutricheck-backend/actions/workflows/maven.yml/badge.svg)](https://github.com/NutriCheck-Dev/nutricheck-backend/actions/workflows/maven.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/NutriCheck-Dev/nutricheck-backend/blob/main/LICENSE)

This repository contains the backend of the NutriCheck calorie tracker application.

## Features
- Search and retrieve food nutritional information provided by [OpenFoodFacts](https://world.openfoodfacts.org/) and the [Swiss Food Composition Database](https://naehrwertdaten.ch/)
- Food product search supported in languages English and German
- Upload custom recipes with description, ingredients and nutritional information
- Search and retrieve user-uploaded recipes and their nutritional information
- Rate other recipes and delete own uploaded recipes (TBD)
- Estimate the nutritional information of a meal based on a provided image using the [Gemini API](https://ai.google.dev/gemini-api/docs)
- User authentication using Google's OAuth server (TBD)

## Architecture
The entire application is written in [Java](https://www.java.com/) with [Spring Boot](https://spring.io/projects/spring-boot). As a database, we opted for [MySQL](https://www.mysql.com/). 

The `src` directory contains the four layers of our application:
- `controller` - the controllers which handle incoming/outgoing HTTP requests
- `model` - our database entities and the corresponding repositories
- `client` - the interaction with the external Gemini API and the food database providers
- `service` - the services which implement our business logic

## How to Build

To build the project, you need to have [Maven](https://maven.apache.org/) installed. Also, you need to 
have Java 21 installed and set up on your machine. You can then run the following command in the root directory of the project:

    mvn clean install

This will compile the code, run the unit tests and package the application into a JAR file.

## How to Run
The application can either be run manually using the JAR file or using Docker. In both cases, you need a
.env file with the following environment variables:

    MYSQL_DATABASE=<name of your MySQL database>
    MYSQL_ROOT_PASSWORD=<password for your MySQL root user>
    ADMIN_USERNAME=<username for the admin user>
    ADMIN_PASSWORD=<password for the admin user>
    GEMINI_API_KEY=<your Gemini API key>

If you want to run the application manually, you also need to export the environment variables in your terminal. For Bash/Zsh terminals,
this can be achieved by running:

    set -a
    source .env
    set +a

### Run manually
You need to have a MySQL server running locally and a database created with the name specified in the `MYSQL_DATABASE` environment variable. You can then run the application using the following command:

    java -jar target/nutricheck-backend-0.0.1-SNAPSHOT.jar

### Run with Docker
Make sure you have [Docker](https://www.docker.com/) installed and running on your machine. Additionally, you need to have [Docker Compose](https://docs.docker.com/compose/) installed.
You can then start the application by running the following command
in the root directory of the project:

    docker-compose -f docker-compose-dev.yml up --build -d

## Background
We developed this project as part of the "Praxis der Softwareentwicklung" course, part of the [KIT](https://www.kit.edu/english) bachelor's degree program in computer science.

