#!/usr/bin/env bash
PRG="$0"
JAVA_OPT="-Xms1g -Xmx2g"

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
HOME=`cd "$PRGDIR/.." >/dev/null; pwd`
cd $HOME;
echo `pwd`;

# git pull;
mvn clean package install deploy -DskipTests -U -e -X;