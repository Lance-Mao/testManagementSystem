FROM maven:3.5-jdk-8-slim

WORKDIR /app

COPY . /app

RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/systemBackground.jar"]
