#! /usr/bin/python

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


import sys, syslog
from ConfigParser import ConfigParser
import LogManager

def main():
	
	
	try:
		LogManager.getInstance().logInfo("YencaP is starting...")
		
		if len(sys.argv)!=1:
			usage = "Usage: netconfd"
			print usage
			sys.exit(1)
		else:
			# Parse the configuration file the get the active application protocol and the options.
			cp = ConfigParser()
			monserver = cp.parseConfigurationFile()
			# Start the Netconf server, listening on port specified.
			monserver.listener()
		
	except Exception,exp:
		print exp
		sys.exit(1)

	

main()
