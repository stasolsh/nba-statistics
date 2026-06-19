FROM eclipse-temurin:21

ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9890"

ARG JAR_FILE=/build/libs/nba-statistics.jar

ADD ${JAR_FILE} application.jar

EXPOSE 9090 9892

CMD java -jar ${JAVA_OPTS} /application.jar