FROM adoptopenjdk/openjdk8:alpine-jre
RUN apk add --no-cache ttf-dejavu && rm -rf /var/cache/apk/*

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ADD wait-for-something.sh .
RUN chmod +x /wait-for-something.sh
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]