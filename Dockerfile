FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/gcp-datacatalog-caching-demo-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} gcp-datacatalog-caching-demo.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/gcp-datacatalog-caching-demo.war"]