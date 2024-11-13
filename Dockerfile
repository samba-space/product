FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-alpine
ARG JAR_FILE=build/libs/product-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product.jar
ENV AWS_XRAY_DAEMON_ADDRESS=xray-daemon:2000
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product.jar"]