#!/bin/bash

if [ $# != 1 ]
then
	echo "Usage: $0 configFile"
	exit 1
fi

HOSTNAME="localhost"
PORTNUMBER="830"
USERNAME="max"
PASSWORD="monitor"
ROLE="SuperManager"

java -jar NetconfConnect.jar $HOSTNAME $PORTNUMBER $USERNAME $PASSWORD $ROLE $1 
