#!/usr/bin/env bash
PRG="$0"
JAVA_OPT="-Xms1g -Xmx2g"

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
HOME_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

cd $HOME_HOME;
echo `pwd`;
git pull;

mvn clean package install deploy -DskipTests -U;
