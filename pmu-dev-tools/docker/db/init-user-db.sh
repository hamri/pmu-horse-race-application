#!/bin/bash

# This file is automatically executed on startup by the "postgres" docker image (see https://hub.docker.com/_/postgres)
# ---------------------------------------------------------------------------------------------------------------------------
# Initialize a postgre instance for the application PMU-HORSE-RACE
# 1) Create a user "myuser" with password "password"
# 2) Create a database "mydb" (and give all privileges to myuser)
# 3) Create a schema "pmu_local" under "mydb" owned by "myuser"

set -e

echo "START - INIT DATABASE"

psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER myuser with password 'password';
    CREATE DATABASE mydb;
    GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;
    \c mydb;
    CREATE SCHEMA pmu_local AUTHORIZATION myuser;
EOSQL

echo "SUCCESS - INIT DATABASE"
