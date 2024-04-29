FROM amazoncorretto:21-alpine-jdk

COPY build/libs/songservice-0.0.1-SNAPSHOT.jar /home/songservice.jar
CMD ["java", "-jar", "/home/songservice.jar"]

#docker run --name songservice --network app-network -p 8081:8081 -t songservice