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

## Background
We developed this project as part of the "Praxis der Softwareentwicklung" course, part of the [KIT](https://www.kit.edu/english) bachelor's degree program in computer science.

