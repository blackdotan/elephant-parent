#!/bin/sh
PRG="$0"
JAVA_OPT="-Xms1g -Xmx2g"

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
CATTLE_UTILS_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

#MBG生成指令

cd $CATTLE_UTILS_HOME

mvn clean package install source:jar -DskipTests
