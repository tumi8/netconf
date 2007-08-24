#!/bin/bash
#
# vermont:       Starts VERMONT as daemon
# 

DAEMON="/usr/bin/vermont"
INIT="/usr/local/ensuite/yencap/Modules/VERMONT_Module/startup.xml"
NAME="vermont"
DESC="VERMONT Versatile Monitoring Toolkit"
HOMEDIR="/opt/vermont"
LOGDIR="/opt/vermont"
PIDFILE="/var/run/vermont"

# Check the existence of required files
test -f $DAEMON || exit 0
test -f $INIT || exit 0

# Identify the distribution
if [ -f /etc/debian_version ]
then
	DISTRIB="DEBIAN"
elif [ -f /etc/fedora-release ]
then
	DISTRIB="FEDORA"
elif [ -f /etc/redhat-release ]
then
	DISTRIB="REDHAT"
fi

start() {
	echo $"Starting $DESC"
	if [ $DISTRIB == "DEBIAN" ]
	then
		start-stop-daemon --start --quiet --name $NAME --make-pidfile --pidfile $PIDFILE --exec $DAEMON &
	else
		$DAEMON -f $INIT &
	fi
	RETVAL=$?
	sleep 2
	echo
	if [ $RETVAL -ne 0 ]; then
	    return $RETVAL
	fi
}

stop() {	
	echo $"Stopping $DESC"
	if [ $DISTRIB == "DEBIAN" ]
	then
		kill `cat $PIDFILE`
	else
		killproc $NAME
	fi
	RETVAL=$?
	echo
	if [ $RETVAL -ne 0 ]; then
	    return $RETVAL
	fi
	
}

# See how we were called.
RETVAL=0

case "$1" in
  'start' )
  	start;
	echo;;
	#break;;
  'stop' )
  	stop;
	echo;;
	#break;;
  'restart'  )
  	$0 stop
	$0 start
	echo;;
	#break;;
  'status' )
  	if [ $DISTRIB != "DEBIAN" ]
	then
		status $NAME
		RETVAL=$?
	else
		echo $"Usage: $0 {start|stop|restart}";
	fi
	;;
  *)
	echo $"Usage: $0 {start|stop|status|restart}";
	exit 1;;
esac 

exit $?
