FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8080
ADD target/exchange-api-0.0.1-SNAPSHOT.jar exchange-api.jar
ENTRYPOINT ["java", "-jar", "/exchange-api.jar"]
