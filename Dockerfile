FROM public.ecr.aws/docker/library/maven:latest AS Build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN nvm clean package

FROM public.ecr.aws/docker/library/eclipse-temurin:21-alpine AS Run
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080