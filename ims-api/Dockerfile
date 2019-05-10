FROM java:8
MAINTAINER woonsungBaek <ws.baek@gaonsoft.com>

# change timezone
RUN set -x \
    && apt-get update \
    && apt-get install -y --no-install-recommends tzdata \
    && rm -rf /var/lib/apt/lists/* \
    && ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
    
VOLUME /tmp
ADD target/ims-api-0.1.jar app.jar
EXPOSE 6920
ENV JAVA_OPTS ""
#CMD ["/bin/sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/Seoul -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=y -jar /app.jar"]
CMD ["/bin/sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/Seoul -jar /app.jar"]
