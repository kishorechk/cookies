# Cookies

## Pre-requisite
* Java 8

## Instructions

```
git clone https://github.com/kishorechk/cookies.git

cd cookies

./mvnw package

./cookies-finder.sh -fcookies.csv -d2018-12-09
```

## Assumptions
* the data file will be csv format
* If multiple cookies meet that criteria, please return all of them on separate lines.
* -d parameter takes date in UTC time zone.
* have enough memory to store the contents of the whole file.
* Cookies in the log file are sorted by timestamp (most recent occurrence is the first line of the file).