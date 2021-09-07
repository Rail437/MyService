FROM openjdk:8-jdk-alpine3.9
ARG JAR_FILE=build/libs/MyService-0.0.1.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]