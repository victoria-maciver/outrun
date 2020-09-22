FROM openjdk:11-jre-slim
ADD VERSION .
ARG VERSION_NUMBER
COPY ./target/outrun-0.1.0.jar /
EXPOSE 8443
EXPOSE 8081

RUN groupadd outrun && useradd -g outrun -s /bin/false outrun

USER outrun

ENV VERSION_NUMBER=$VERSION_NUMBER
ENTRYPOINT exec java $JAVA_OPTS -jar "outrun-0.1.0.jar"