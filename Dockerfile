# ---- Build Stage ----
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app

# Copy Maven/Gradle build files first for caching (Maven example)
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN ./mvnw -q dependency:go-offline

# Copy source
COPY src src

# Build Spring Boot fat jar (layered)
RUN ./mvnw -q clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy only Spring Boot layers (recommended)
COPY --from=builder /app/target/*.jar app.jar

# Expose your port
EXPOSE 8080

# JVM tuning for containers + faster startup
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]