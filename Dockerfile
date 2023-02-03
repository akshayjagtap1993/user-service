FROM openjdk:18-jdk-alpine

COPY target/user-service-app.jar /home/app/app.jar

ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
