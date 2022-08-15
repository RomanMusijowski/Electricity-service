FROM openjdk:17-alpine

# copy the packaged jar file into our docker image
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/demo.jar"]

EXPOSE 8080