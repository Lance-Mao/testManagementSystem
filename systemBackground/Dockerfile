FROM maven:3.5-jdk-8

EXPOSE 8081

COPY . /usr/share/maven

RUN mvn package

WORKDIR /root/.m2/repository

VOLUME /tmp
ADD target/systemBackground.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
