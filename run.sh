#!/bin/bash

# This script allows you run this test on docker engine. Only you have to run with ./run.sh

DOCKER_IMAGE=pgtoopx/bch-maven-chrome
echo "Building Image"
#docker build . -t $DOCKER_IMAGE
echo "Ejecutando pruebas"
docker run --rm -i --add-host="portalcomercial.qa.labchile.cl:200.14.169.120" -v $PWD:/usr/src/ -w /usr/src $DOCKER_IMAGE mvn clean install -Dheadless=true -Ddocker=true
