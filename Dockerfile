#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM maven:3.6.3-jdk-8 as build

COPY . .

ARG NEXUS_ADMIN_USER
ARG NEXUS_ADMIN_PASSWORD
ARG NEXUS_URL
ARG DIGITALFORMS_SWAGGER_ENABLED
ARG ENV_PROFILE

ENV NEXUS_ADMIN_USER ${NEXUS_ADMIN_USER}
ENV NEXUS_ADMIN_PASSWORD ${NEXUS_ADMIN_PASSWORD}
ENV NEXUS_URL ${NEXUS_URL}
ENV DIGITALFORMS_SWAGGER_ENABLED ${DIGITALFORMS_SWAGGER_ENABLED}
ENV ENV_PROFILE ${ENV_PROFILE}

# Build
RUN if [ "${ENV_PROFILE}" = "openshift" ]; \
    then mvn -Popenshift -s configuration/settings.xml clean install -Dmaven.test.skip=true; \
    else mvn -Pdefault-profile clean install -Dmaven.test.skip=true; \
    fi

# Deploy
#RUN if [ "${ENV_PROFILE}" = "openshift" ]; \
#    then mvn -Popenshift -s configuration/settings.xml deploy -Dmaven.test.skip=true; \
#    fi

CMD ["mvn", "spring-boot:run"]
#############################################################################################
