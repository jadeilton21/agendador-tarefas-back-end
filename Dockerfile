FROM gradle:8.14.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app


COPY --from=build /app/build/libs/*.jar /app/agendador-tarefas-back-and.jar

EXPOSE 8081

CMD ["java","-jar","/app/agendador-tarefas-back-and.jar"]