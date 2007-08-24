###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Vincent CRIDLIG                                         #
#                                                                             #
# This library is free software; you can redistribute it and/or               #
# modify it under the terms of the GNU Lesser General Public                  #
# License as published by the Free Software Foundation; either                #
# version 2.1 of the License, or (at your option) any later version.          #
#                                                                             #
# This library is distributed in the hope that it will be useful,             #
# but WITHOUT ANY WARRANTY; without even the implied warranty of              #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU           #
# Lesser General Public License for more details.                             #
#                                                                             #
# You should have received a copy of the GNU Lesser General Public            #
# License along with this library; if not, write to the Free Software         #
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA   #
#                                                                             #
# Author Info:                                                                #
#   Name : Vincent CRIDLIG                                                    #
#   Email: Vincent.Cridlig@loria.fr                                           #
#                                                                             #
###############################################################################

import os

def getYencapHome():
	if ( os.environ.get("YENCAP_HOME") == None ):
		return "/opt/yencap-2.1.11/"
	else:
		return os.environ.get("YENCAP_HOME")

def getYencapConfHome():
	if ( os.environ.get("YENCAP_CONF_HOME") == None):
		return "/opt/yencap-2.1.11/conf"
	else:
		return  os.environ.get("YENCAP_CONF_HOME")

def getVermontBinary():
	if ( os.environ.get("VERMONT_BINARY") == None ):
		return "vermont"
	else:
		return os.environ.get("VERMONT_BINARY")
	
class C:
	"""
		This class stores some constants related to NetConf configuration protocol.
	"""

	YENCAP = "YencaP"
	YENCAP_HOME = getYencapHome()
	YENCAP_CONF_HOME = getYencapConfHome() 
	VERMONT = getVermontBinary()
	SYSLOG_FILE = "/var/log/yencap"
	##PATH_TO_XSDVALID = "/tmp/xsdvalid-210/bin/xsdvalid"

	# Netconf port mapping
	NETCONF_SSH_PORT = 830
	NETCONF_BEEP_PORT = 831
	NETCONF_SOAPHTTP_PORT = 832
	NETCONF_SOAPBEEP_PORT = 833

	NETCONF_SSH_SUBSYSTEM = "netconf"

	# CONFIGURATION DATASTORES
	RUNNING = "running"
	CANDIDATE = "candidate"
	STARTUP = "startup"
	
	# CONFIGURATION DATASTORE LOCATION
	STARTUP_LOC = "startup.xml"
	CANDIDATE_LOC = "candidate.xml"
	STARTUP_URI = "file:%s/%s" % (YENCAP_CONF_HOME,STARTUP_LOC)
	CANDIDATE_URI = "file:%s/%s" % (YENCAP_CONF_HOME,CANDIDATE_LOC)
	
	HELLO_URI = 'file:%s/hello.xml' % (YENCAP_CONF_HOME)

	# OPERATIONS
	GET = "get"
	GET_CONFIG = "get-config"
	COPY_CONFIG = "copy-config"
	EDIT_CONFIG = "edit-config"
	DELETE_CONFIG = "delete-config"
	LOCK = "lock"
	UNLOCK = "unlock"
	CLOSE_SESSION = "close-session"
	KILL_SESSION = "kill-session"

	# NETCONF XML SCHEMA LOCATION
	NETCONF_SCHEMA_LOC = "conf/netconfSchema.xsd"
	NETCONF_SCHEMA_URI = "%s/%s" % (YENCAP_HOME, NETCONF_SCHEMA_LOC)
	NETCONF_XMLNS = "urn:ietf:params:xml:ns:netconf:base:1.0"
	YENCAP_XMLNS = "urn:loria:madynes:ensuite:yencap:1.0"
	
	SOURCE = "source"
	TARGET = "target"
	CONFIG = "config"
	
	OPERATION = "operation"
	DEFAULT_OPERATION = "default-operation"
	MERGE = "merge"
	REPLACE = "replace"
	CREATE = "create"
	DELETE = "delete"
	NONE = "none"

	TEST_OPTION = "test-option"
	TEST_THEN_SET = "test-then-set"
	SET = "set"

	ERROR_OPTION = "error-option"
	STOP_ON_ERROR = "stop-on-error"
	CONTINUE_ON_ERROR = "continue-on-error"
	ROLLBACK_ON_ERROR = "rollback-on-error"

	RPC = "rpc"
	RPC_REPLY = "rpc-reply"
	CAPABILITY = "capability"
	CAPABILITIES = "capabilities"
	HELLO = "hello"
	URL = "url"
	FILTER = "filter"
	SUBTREE = "subtree"
	XPATH = "xpath"
	OK = "ok"
	SESSION_ID = "session-id"
	MESSAGE_ID = "message-id"
	XMLNS = "xmlns"
	DELIMITER = "]]>]]>"

	# For RBAC capability
	ACTIVATE = "activate"
	DEACTIVATE = "deactivate"
	ROLES = "roles"
	ROLE = "role"
	JUNIOR_ROLES = "junior-roles"
	JUNIOR_ROLE = "junior-role"
	USERS = "users"
	USER = "user"
	PERMISSIONS = "permissions"
	PERMISSION = "permission"
	PERMISSION_ASSIGNEMENTS = "permission-assignements"
	PERMISSION_ASSIGNEMENT = "permission-assignement"
	USER_ASSIGNEMENTS = "user-assignements"
	USER_ASSIGNEMENT = "user-assignement"
	LOGIN = "login"
	PASSWORD = "password"
	PUBLIC_KEY = "public-key"
	NAME = "name"
	SCOPE = "scope"


