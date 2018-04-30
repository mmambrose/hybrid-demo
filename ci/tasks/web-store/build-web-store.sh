#!/bin/bash

set -e -x


pushd web-store-repo/web-store
  ./mvnw clean package
popd

cp web-store-repo/web-store/target/web-store-0.0.1-SNAPSHOT.jar   build-output/.
