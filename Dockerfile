FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/SunnyGadgets_v1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_sunnygadgets.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_sunnygadgets.jar"]