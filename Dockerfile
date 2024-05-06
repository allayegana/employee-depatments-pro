FROM openjdk:21-ea-18-jdk-buster as base


LABEL author="ALLAYE"

WORKDIR /departments-employee

ENV JAVA_HOME=/usr/local/openjdk-21

ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY mvnw mvnw.cmd ./
COPY .mvn/ .mvn/
COPY pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src



FROM base as test
CMD ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080


