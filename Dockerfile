# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN chmod +x ./mvnw
RUN sed -i 's/\r$//' ./mvnw
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package -Dmaven.test.skip=true

# Package stage
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/app.jar
ENTRYPOINT java -jar /app/app.jar
