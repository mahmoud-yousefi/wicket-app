FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install

FROM openjdk:11-jre-slim

ENV TOMCAT_VERSION=9.0.80

RUN mkdir -p /app

RUN apt-get update && apt-get install -y wget

RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar -xvzf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} /app/tomcat && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz

RUN apt-get remove -y wget && \
    apt-get autoremove -y && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN ls -l /app/tomcat

COPY --from=build /app/target/wicket-app.war /app/tomcat/webapps/

WORKDIR /app/tomcat

RUN chmod +x bin/catalina.sh

EXPOSE 8080

CMD ["bin/catalina.sh", "run"]