#!/bin/sh

echo "The application will start in ${SLEEP_TIME}s..." && sleep ${SLEEP_TIME}
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.egov.elearning.EgovcoursesApp"  "$@"