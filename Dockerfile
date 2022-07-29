FROM amazoncorretto:11.0.16-alpine3.15
RUN apk add bash
COPY docs /docs
COPY target/universal/stage/ /opt/docker/
RUN adduser -u 2004 -D docker && chmod +x /opt/docker/bin/codacy-checkstyle
USER docker
WORKDIR /src
ENTRYPOINT ["/opt/docker/bin/codacy-checkstyle"]
