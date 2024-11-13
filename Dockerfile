FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE=build/libs/product-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product.jar"]