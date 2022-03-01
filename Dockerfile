FROM openjdk:8-jdk-alpine
LABEL maintainer="ashokmurthy88@gmail.com"
VOLUME /tmp
ADD build/libs/savings-service-b-0.0.1-SNAPSHOT.jar savings-service-b.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/.urandom","-jar","savings-service-b.jar"]
EXPOSE 8082