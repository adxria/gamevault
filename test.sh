#!/usr/bin/env bash
set -e

read -rsp "Database password: " GAMEVAULT_TEST_DB_PASSWORD
echo
export GAMEVAULT_TEST_DB_PASSWORD

bash gradlew test
