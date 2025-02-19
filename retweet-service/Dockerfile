FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/retweet-service-0.0.1-SNAPSHOT.jar /app/retweet-service.jar

EXPOSE 8080

CMD ["java", "-jar", "retweet-service.jar"]