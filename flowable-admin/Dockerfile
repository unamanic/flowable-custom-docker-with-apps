#FROM flowable/flowable-admin
#ADD lib dbLib
#ENTRYPOINT ["java", "-Dloader.path=WEB-INF/lib-provided,WEB-INF/lib,WEB-INF/classes/META-INF,WEB-INF/classes/flowable-default.properties,WEB-INF/classes/org,file:dbLib/mysql-connector-java-8.0.18.jar", "-cp", "/app.war", "-Dloader.main=org.flowable.ui.admin.application.FlowableAdminApplication", "org.springframework.boot.loader.PropertiesLauncher"]

FROM tomcat:8.5.50-jdk8-openjdk

RUN apt-get update -y && apt-get -qq -y install netcat

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
ADD flowable-admin.war /usr/local/tomcat/webapps/ROOT.war
ADD mysql-connector-java-8.0.18.jar /usr/local/tomcat/lib/
ADD postgresql-42.2.9.jre7.jar /usr/local/tomcat/lib/
ADD wait-for-something.sh .
RUN chmod +x ./wait-for-something.sh

CMD ["catalina.sh", "run"]