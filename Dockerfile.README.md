# Dockerfile Guide

This document explains every instruction currently present in `Dockerfile`.

## Purpose

The Dockerfile builds and runs the Spring Boot app using a multi-stage build:
- Stage 1 compiles the application with JDK.
- Stage 2 runs the packaged JAR with a smaller JRE image.

## Instruction-by-Instruction Explanation

| Instruction | Current value | What it does |
| --- | --- | --- |
| `FROM eclipse-temurin:25-jdk AS builder` | build stage base | Starts a build stage with Java 25 JDK to compile/package the app. |
| `WORKDIR /app` | `/app` | Sets working directory for subsequent commands in the builder stage. |
| `COPY mvnw pom.xml ./` | wrapper + Maven descriptor | Copies Maven wrapper script and project descriptor first to improve cache reuse. |
| `COPY .mvn .mvn` | Maven wrapper metadata | Adds Maven wrapper support files needed by `./mvnw`. |
| `RUN ./mvnw -q dependency:go-offline` | download dependencies | Pre-fetches dependencies so later builds can reuse a cached layer if dependencies did not change. |
| `COPY src src` | application source | Copies source files into the image after dependency caching steps. |
| `RUN ./mvnw -q clean package -DskipTests` | package JAR | Builds a Spring Boot executable JAR and skips tests during image build. |
| `FROM eclipse-temurin:25-jre` | runtime stage base | Starts a smaller runtime stage with JRE only. |
| `WORKDIR /app` | `/app` | Sets runtime working directory. |
| `COPY --from=builder /app/target/*.jar app.jar` | built artifact | Copies packaged JAR from builder stage into runtime image as `app.jar`. |
| `EXPOSE 8080` | `8080` | Documents that the container listens on port 8080. |
| `ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]` | JVM start command | Starts the app with container-aware memory settings and runs `app.jar`. |

## Practical Notes

- Multi-stage build reduces final image size by excluding build tools from runtime image.
- Copying `pom.xml`/`.mvn` before `src` improves cache hits when only code changes.
- `-DskipTests` speeds image build but should be paired with tests in CI before release.
- `MaxRAMPercentage=75` limits JVM heap fraction in containers to reduce OOM risk.

## Build and Run

```bash
docker build -t my-app:latest .
docker run --rm -p 8080:8080 my-app:latest
```

## Troubleshooting

- `./mvnw: not found` or permission errors: ensure Maven wrapper exists and is executable in repo.
- Build fails at dependency download: check network access from Docker build environment.
- App not reachable at `localhost:8080`: confirm container started successfully and port mapping is `-p 8080:8080`.
