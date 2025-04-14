FROM openjdk:17-jdk-slim
LABEL authors="ajou_way"
WORKDIR /app
COPY build/libs/*SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
