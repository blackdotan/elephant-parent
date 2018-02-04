#!/bin/sh
PRG="$0"
JAVA_OPT="-Xms1g -Xmx2g"

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
PUKR_APPLICATION_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

#MBG生成指令

cd $PUKR_APPLICATION_HOME
#mvn clean javadoc:jar source:jar install deploy -DskipTests -U -Dclassifiers=pg

mvn clean package install deploy -DskipTests -U