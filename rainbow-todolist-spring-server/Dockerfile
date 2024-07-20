FROM openjdk:17-jdk-alpine
RUN addgroup -S springbootg && adduser -S springbootu -G springbootg
USER springbootu:springbootg
ARG JAR_FILE=target/rainbow-todolist-*.jar
COPY ${JAR_FILE} rainbow-todolist-server.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/rainbow-todolist-server.jar"]
