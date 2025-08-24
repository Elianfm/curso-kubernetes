FROM openjdk:17-jdk-alpine AS builder

WORKDIR /app/cursos
RUN mkdir ./logs

COPY ./pom.xml /app
COPY ./cursos/.mvn ./.mvn
COPY ./cursos/mvnw .
COPY ./cursos/pom.xml .

RUN ./mvnw dependency:go-offline

COPY ./cursos/src ./src

RUN ./mvnw clean package -DskipTests

###############################################################
FROM openjdk:17-jdk-alpine
WORKDIR /app 
COPY --from=builder /app/cursos/target/cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002
CMD ["java", "-jar", "./cursos-0.0.1-SNAPSHOT.jar"]
