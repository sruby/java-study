FROM apache/skywalking-java-agent:8.5.0-jdk8
VOLUME /tmp
ADD skywalking-demo-0.0.1-SNAPSHOT.jar app.jar
#因为基础镜像存在workdir目录，所以此处使用相对路径访问app.jar，而不能使用/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
