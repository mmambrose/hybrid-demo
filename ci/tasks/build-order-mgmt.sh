#!/bin/bash

set -e -x


pushd order-mgmt-repo/order-mgmt-service
  ./mvnw clean package
popd

cp order-mgmt-repo/order-mgmt-service/target/order-mgmt-service-0.0.1-SNAPSHOT.jar   build-output/.
