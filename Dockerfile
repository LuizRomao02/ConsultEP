FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ConsultEP-00.00.01.jar /app/consultep-application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "consultep-application.jar"]
