#!/bin/bash

# This script allows you run this test on docker engine. Only you have to run with ./run.sh

DOCKER_IMAGE=pgtoopx/bch-maven-chrome
echo "Building Image"
docker build . -t pgtoopx/bch-maven-chrome
echo "Ejecutando pruebas"
docker run --rm -i -v $PWD:/usr/src/ -w /usr/src $DOCKER_IMAGE mvn clean install -Dheadless=true
