FROM markhobson/maven-chrome:latest

#RUN rm -f /etc/localtime && ln -sf /usr/share/zoneinfo/America/Santiago /etc/localtime

COPY settings.xml /usr/share/maven/ref

