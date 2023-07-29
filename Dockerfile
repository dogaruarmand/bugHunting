FROM openjdk:17.0.1-jdk
WORKDIR /app
RUN pwd
ARG JAR_FILE
COPY target/*.jar bug_hunting.jar
EXPOSE 8090
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS\
                              -jar bug_hunting.jar" ]
