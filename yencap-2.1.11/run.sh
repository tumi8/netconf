#!/bin/bash

YENCAP_HOME=`pwd`
YENCAP_CONF_HOME=$YENCAP_HOME/conf
#VERMONT_BINARY=!INSERT_PATH_TO_YOUR_VERMONT_HERE!

export YENCAP_HOME YENCAP_CONF_HOME VERMONT_BINARY

rm -f /var/run/vermont.pid
/usr/bin/env python netconfd.py
