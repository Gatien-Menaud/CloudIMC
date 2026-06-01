# Étape 1 : Compilation de l'application avec Maven et Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier les fichiers de configuration et le code source
COPY CloudIMC/pom.xml ./CloudIMC/
COPY CloudIMC/src ./CloudIMC/src/

# Compiler le projet en ignorant les tests pour accélérer le déploiement Cloud
RUN mvn -f CloudIMC/pom.xml clean package -DskipTests

# Étape 2 : Exécution de l'application avec un environnement Java 21 léger
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Récupérer le fichier .jar généré à l'étape précédente
COPY --from=build /app/CloudIMC/target/*.jar app.jar

# Exposer le port par défaut utilisé par Spring Boot
EXPOSE 8080

# Commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]