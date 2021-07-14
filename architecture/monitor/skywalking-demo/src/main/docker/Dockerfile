FROM apache/skywalking-java-agent:8.5.0-jdk8
VOLUME /tmp
ADD lab-39-springmvc-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
