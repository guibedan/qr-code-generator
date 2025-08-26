FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/qr-code-generator.jar qr-code-generator.jar
EXPOSE 8080
CMD ["java","-jar","qr-code-generator.jar"]