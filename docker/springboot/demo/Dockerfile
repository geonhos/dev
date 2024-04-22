# Build stage
FROM eclipse-temurin:21.0.2_13-jdk-alpine AS build
WORKDIR /app

# dependencies 는 .gradle 에 저장되고 캐싱되어 성능 향상에 도움이 된다.
COPY . /app
RUN chmod +x /app/gradlew
RUN /app/gradlew dependencies

COPY src src
RUN /app/gradlew bootJar

FROM eclipse-temurin:21.0.2_13-jdk-alpine
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 28080
ENTRYPOINT ["java","-jar","/app.jar"]