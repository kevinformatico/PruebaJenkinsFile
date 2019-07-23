FROM markhobson/maven-chrome:latest

RUN rm -f /etc/localtime && ln -sf /usr/share/zoneinfo/America/Santiago /etc/localtime

COPY settings.xml /usr/share/maven/ref
RUN echo "200.14.169.120 el04cn15.bch.bancodechile.cl           portalcomercial.qa.labchile.cl" > /etc/hosts


