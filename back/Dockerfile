FROM hub.c.163.com/library/java:8-alpine

MAINTAINER yangkai.shen 237497819@qq.com

ADD target/*.jar back.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/back.jar"]
