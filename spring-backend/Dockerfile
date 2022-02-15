FROM maven:3.8.4-openjdk-17-slim AS build
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

FROM eclipse-temurin:17-alpine
RUN apk --no-cache add curl
WORKDIR /app
COPY --from=build target/*.jar spring-backend.jar
ARG USERNAME=spring
ENV UID=12345
ENV GID=23456
RUN addgroup -S $USERNAME \
    && adduser \
    --disabled-password \
    --gecos "" \
    --home "$(pwd)" \
    --ingroup "$USERNAME" \
    --no-create-home \
    --uid "$UID" \
    "$USERNAME"
USER $USERNAME
CMD ["java","-jar","./spring-backend.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1
EXPOSE 8080
