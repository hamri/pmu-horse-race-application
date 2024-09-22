#!/bin/bash

# Start an Postgre container (named pmu-pg).
# The db will be initialized with the init-user-db.sh script.

CURRENT_DIR=$(cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P | sed -e 's/^\///' -e 's/\//\\/g' -e 's/^./\0:/');
PATH_TO_INIT="${CURRENT_DIR}\init-user-db.sh";

docker run --name pmu-pg -e POSTGRES_PASSWORD=password -d -p 5432:5432 -v "${PATH_TO_INIT}:/docker-entrypoint-initdb.d/init-user-db.sh" postgres:12-bullseye
