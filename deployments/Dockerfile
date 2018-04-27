FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD api-boot-0.0.1.jar api.jar
RUN sh -c 'touch /api.jar'
ENTRYPOINT [ "sh", "-c", "java -jar /api.jar" ]
