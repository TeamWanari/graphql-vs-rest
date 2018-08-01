FROM openjdk:8-alpine as builder
COPY ./ /tmp
WORKDIR tmp
RUN ./mvnw package -Dmaven.test.skip=true

FROM openjdk:8-alpine
COPY --from=builder /tmp/target/*.jar /app.jar
CMD java -jar /app.jar
EXPOSE 8080