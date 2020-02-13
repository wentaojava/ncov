FROM openjdk:8-jdk-alpine
ADD ncov-spring-boot.jar ncov-java.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
