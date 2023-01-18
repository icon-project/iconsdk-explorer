FROM openjdk:11-jre-slim

ARG VERSION

#Admin Port
ENV SEVER_PORT=8080

#User Security
ENV SERVER_SERVLET_SESSION_TIMEOUT=1h

#Database
ENV DB_DRIVERCLASSNAME=org.hibernate.dialect.MariaDB102Dialect
ENV DB_URL=org.hibernate.dialect.MariaDB102Dialect
ENV DB_CONNECTIONPROPERTIE=jdbc:mysql://mysql:3306
ENV DB_USERNAME=root

#Logging
ENV LOGGING_LEVEL_ROOT=INFO

#keystore
ENV ADMIN_KEYSTORE-FILE=keystore.json

#governancePath
ENV ADMIN_GOVERNANCE_PATH=/genesis/governance
ENV ADMIN_GENESIS_PATH=/genesis/genesisfiles


LABEL version=${VERSION}

ADD backend/build/distributions/goloop-admin-boot-${VERSION}.tar /
RUN mkdir /genesis/
COPY backend/src/main/resources/genesis /genesis/


RUN mv /goloop-admin-boot-${VERSION} /goloop-admin
WORKDIR /goloop-admin
EXPOSE ${SEVER_PORT}
CMD /goloop-admin/bin/goloop-admin
