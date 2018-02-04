FROM openjdk:8

COPY target/vertx-http-app.jar /

CMD ["java", "-Dvertx.cacheDirBase=/tmp", "-jar", "vertx-http-app.jar"]