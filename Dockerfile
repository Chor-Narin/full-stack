# Stage 1: Build the JAR with Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copy pom and download deps first (caching)
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

RUN ./mvnw dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/full-stack-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9999

ENTRYPOINT ["java", "-jar", "app.jar"]