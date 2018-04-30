#!/bin/bash

set -e -x


pushd fulfillment-service-repo/fulfillment-service
  ./mvnw clean package
popd

cp fulfillment-service-repo/fulfillment-service/target/fulfillment-service-0.0.1-SNAPSHOT.jar   build-output/.
