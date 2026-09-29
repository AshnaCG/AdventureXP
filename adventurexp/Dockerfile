FROM maven:3.9.12-eclipse-temurin-25
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN mv target/*.jar target/app.jar
CMD ["java", "-jar", "target/app.jar"]