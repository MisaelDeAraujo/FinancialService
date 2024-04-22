FROM openjdk:17

COPY target/financialservice-0.0.1-SNAPSHOT.jar /app/financialservice-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8080

CMD ["java","-jar","financialservice-0.0.1-SNAPSHOT.jar"]