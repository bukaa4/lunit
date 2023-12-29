FROM openjdk:18
ADD target/lunit-0.0.1-SNAPSHOT.jar lunit-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/lunit-0.0.1-SNAPSHOT.jar"]