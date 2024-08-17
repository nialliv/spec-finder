FROM maven:latest AS builder
COPY src /project/src
COPY pom.xml /project/
RUN mvn -f /project/pom.xml clean package

FROM openjdk:17-jdk-alpine
COPY --from=builder /project/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]