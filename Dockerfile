FROM openjdk:17
ARG JAR_FILE=build/libs/springStudy-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} springStudy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/springStudy-0.0.1-SNAPSHOT.jar"]