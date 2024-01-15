FROM openjdk:17
COPY src/main/resources/application-env.yml /src/main/resources/
ARG JAR_FILE=build/libs/springStudy-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} springStudy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/springStudy-0.0.1-SNAPSHOT.jar"]