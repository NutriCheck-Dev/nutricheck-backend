FROM eclipse-temurin:21-jre-noble
LABEL authors="karchem"

COPY target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Xms512m", "-Xmx512m", "/backend-0.0.1-SNAPSHOT.jar"]