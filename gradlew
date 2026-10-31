#!/usr/bin/env sh
set -eu
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
JAVA_OPTS='-Xmx64m -Xms64m'
exec "$JAVA_HOME/bin/java" ${JAVA_OPTS} -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
