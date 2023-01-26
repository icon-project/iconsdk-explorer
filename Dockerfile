FROM openjdk:11-jre-slim

ARG VERSION

#Admin Port
ENV SEVER_PORT=8080

#Database
ENV DB_DRIVERCLASSNAME="com.mysql.jdbc.Driver"
ENV DB_URL="jdbc:mysql://mysql:3306"
ENV DB_CONNECTIONPROPERTIE="useUnicode=yes;characterEncoding=utf8;"
ENV DB_USERNAME=root

#Logging


LABEL version=${VERSION}

ADD backend/build/distributions/iconsdk-explorer-boot-${VERSION}.tar /

RUN mkdir /erd/
COPY backend/erd /erd/

RUN mkdir /IRC/
COPY backend/IRC /IRC/

ENV IRC_PATH=/IRC/
ENV SQL_PATH=/erd/init(long).sql

RUN mv /iconsdk-explorer-boot-${VERSION} /iconsdk-explorer
WORKDIR /iconsdk-explorer
EXPOSE ${SEVER_PORT}
CMD /iconsdk-explorer/bin/iconsdk-explorer
