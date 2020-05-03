FROM openjdk:8
ADD target/polly-0.0.1-SNAPSHOT.jar polly.jar
COPY target/polly-0.0.1-SNAPSHOT.jar polly.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "polly.jar"]