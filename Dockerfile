FROM openjdk

WORKDIR /app

COPY ./target/dock-application-0.0.1.jar /app/dock-application.jar

ENTRYPOINT ["java", "-jar", "spring-app.jar"]

EXPOSE 8085