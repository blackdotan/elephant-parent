#!/bin/sh
PRG="$0"
JAVA_OPT="-Xms1g -Xmx2g"

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
MYBATIS_PLUGIN_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

cd $MYBATIS_PLUGIN_HOME
mvn clean install deploy -DskipTests -U


