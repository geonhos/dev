# Build stage
FROM eclipse-temurin:21-alpine AS build
WORKDIR /app

COPY build.gradle /app
COPY settings.gradle /app
COPY gradle /app/gradle
COPY gradlew /app
RUN chmod +x /app/gradlew

RUN /app/gradlew dependencies

COPY src src
RUN /app/gradlew bootJar

# Run stage
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]