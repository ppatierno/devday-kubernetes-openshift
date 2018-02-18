#!/usr/bin/env bash

URL=$1
COUNT=${2:-10}

echo $COUNT
echo $URL

for ((i=0; i<${COUNT}; i++))
do
    curl ${URL}
    printf "\n"
    sleep 1
done
