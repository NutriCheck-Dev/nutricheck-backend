FROM openjdk:21-jdk-slim
LABEL authors="karchem"

COPY target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Xms256m", "-Xmx512m", "/backend-0.0.1-SNAPSHOT.jar"]