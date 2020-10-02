FROM maven:3.6.1-jdk-8 as builder

## Provides EST timezone for JUnit testing purposes
ENV TZ=America/New_York
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

### Provide Default Argument
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package

## Use only a JRE to run application
FROM gcr.io/distroless/java:8

## Provide Dynamic Environment Variables
ARG db_url
ENV db_url=${db_url}

ARG db_username
ENV db_username=${db_username}

ARG db_password
ENV db_password=${db_password}

## Copy Artifact from maven image
COPY --from=builder /usr/src/app/target/vew-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app
CMD ["app.jar"]