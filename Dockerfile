FROM openjdk:11
EXPOSE 9090
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} no-framework-web-app.jar
ENTRYPOINT ["java","-jar","no-framework-web-app.jar"]