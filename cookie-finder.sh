#!/bin/bash

if [ $# -ne 2 ]; then
    echo $0: usage: cookie-finder -f filename -d date
    exit 1
fi

while getopts f:d: flag
do
    case "${flag}" in
        f) filename=${OPTARG};;
        d) date=${OPTARG};;
    esac
done

java -jar target/cookies-1.0-SNAPSHOT.jar $filename $date