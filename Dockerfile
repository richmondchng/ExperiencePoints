FROM openjdk:11-jre-slim
ARG JAR_FILE=xpmanagement/target/xpmanagement-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} xpmanagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/xpmanagement-0.0.1-SNAPSHOT.jar"]