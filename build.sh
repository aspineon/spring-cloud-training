#!/bin/bash
./mvnw clean package

docker rmi $(docker images | grep "<none>")

docker build -t "training/admin" admin
docker build -t "training/configuration" configuration
docker build -t "training/discovery" discovery
docker build -t "training/gateway" gateway
docker build -t "training/zipkin" zipkin

