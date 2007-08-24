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

import syslog

class LogManager:

	instance = None
	
	def __init__(self):
		"""
			Instantiates a new Log manager.
		"""
		

	def logInfo(self, message):
		"""
			Log info message into /var/log/messages
		"""
		syslog.openlog("server.py")
		syslog.syslog(syslog.LOG_INFO, str(message))
		syslog.closelog()
	

	def logError(self, message):
		"""
			Log error message into /var/log/yencaP
		"""
		syslog.openlog("server.py")
		syslog.syslog(syslog.LOG_ERR, str(message))
		syslog.closelog()


	def logWarning(self, message):
		syslog.openlog("server.py")
		syslog.syslog(syslog.LOG_WARNING, str(message))
		syslog.closelog()
				


def getInstance():
	"""
		LogManager is a Singleton
		Use getInstance method to get the unique LogManager
	"""

	if LogManager.instance == None:
		LogManager.instance = LogManager()
	return LogManager.instance

