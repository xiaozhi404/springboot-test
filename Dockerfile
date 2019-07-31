FROM openjdk:8u181-jdk-alpine
ADD ./target/springboot-test-0.0.1-SNAPSHOT.jar /usr/src/app/springboot-test.jar
WORKDIR /usr/src/app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","springboot-test.jar", "--spring.profiles.active=docker"]
EXPOSE 8080

