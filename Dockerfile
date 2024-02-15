FROM openjdk:11
VOLUME /tmp
ARG JAR_PATH=./build/libs/
COPY ${JAR_PATH}justsayit-0.0.1-SNAPSHOT.jar ${JAR_PATH}justsayit-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "build/libs/justsayit-0.0.1-SNAPSHOT.jar"]