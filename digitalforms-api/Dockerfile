#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM maven:3.6.3-jdk-8 as build

COPY . .

ARG NEXUS_ADMIN_USER
ARG NEXUS_ADMIN_PASSWORD
ARG NEXUS_URL
ARG DIGITALFORMS_SWAGGER_ENABLED

ENV NEXUS_ADMIN_USER ${NEXUS_ADMIN_USER}
ENV NEXUS_ADMIN_PASSWORD ${NEXUS_ADMIN_PASSWORD}
ENV NEXUS_URL ${NEXUS_URL}
ENV DIGITALFORMS_SWAGGER_ENABLED ${DIGITALFORMS_SWAGGER_ENABLED}

RUN mvn -s configuration/settings.xml clean install

# RUN mvn -s configuration/settings.xml deploy -Dmaven.test.skip=true

CMD ["mvn", "spring-boot:run"]
#############################################################################################
