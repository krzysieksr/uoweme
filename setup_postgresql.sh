#!/bin/bash
set -e

version=$(psql --version)

if [[ $version != *"PostgreSQL"* ]]; then
	sudo apt-get update
	sudo apt-get install postgresql postgresql-contrib
fi

passwd="'dupa'"

psql -f src/main/resources/create_db.sql -v passwd=$passwd postgres

echo "Exelent, database created!"

echo "To connect type: psql -h localhost -d uoweme_db -U uoweme_user"
echo "Default password is $passwd"

exit 0
