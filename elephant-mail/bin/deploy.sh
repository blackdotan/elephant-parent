#!/bin/sh
PRG="$0"
JAVA_OPT="-Xms1g -Xmx2g"

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
CATTLE_MAIL_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

#MBG生成指令

cd $CATTLE_MAIL_HOME

mvn clean javadoc:jar source:jar install deploy -DskipTests -U -N
