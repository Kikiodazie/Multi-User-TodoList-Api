FROM openjdk:11
EXPOSE 8080
#ADD target/todoList-api-docker.jar todoList-api-docker.jar
ENTRYPOINT ["java", "-jar","/todoList-api-docker.jar"]