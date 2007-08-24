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

import sys
import amara
import LogManager
from rbac import rbacManager
from Servers.ssh.serverSSH import ServerSSH
from constants import C


class ConfigParser:

	def __init__(self):

		self.monserver = None


	def parseConfigurationFile(self):

		try:
			# Parse configuration file
			self.doc = amara.parse("file:" + C.YENCAP_CONF_HOME + "/netconfd.xml")

			# Read IP version (4 or 6)
			ipVersion = int(str(self.doc.yencap.ipversion))

			if ipVersion not in [4, 6]:
				raise Exception("Ip version must be 4 or 6 in %s/netconfd.xml file." % (C.YENCAP_CONF_HOME))

			# Read application protocol (only ssh is implemented.)
			appProtocol = str(self.doc.yencap.protocol.active)

			if appProtocol == "ssh":
				sshData = {}
				sshData["hostKeyType"] = str(self.doc.yencap.protocol.ssh.privatekeyfile.keytype)
				sshData["hostKeyFile"] = str(self.doc.yencap.protocol.ssh.privatekeyfile)
				self.monserver = ServerSSH(ipVersion, sshData)
				LogManager.getInstance().logInfo("Netconf over SSH started.")
			else:
				raise Exception("Only ssh is supported as a transport protocol.")
				
			# Read options
			for elem in self.doc.yencap.options.option:
				name = str(elem.name)
				value = str(elem.value)
				
				if (name == "accesscontrol"):
					if (value == "active"):
						rbacManager.getInstance().setActive(True)
					elif (value == "unactive"):
						rbacManager.getInstance().setActive(False)
					else:
						raise Exception("accesscontrol option value must be one of active or unactive.")
				else:
					raise Exception("Unknown option in %s/netconfd.xml." % (C.YENCAP_CONF_HOME))

		except Exception, exp:
			LogManager.getInstance().logError('Error while reading %s/netconfd.xml: %s' % (C.YENCAP_CONF_HOME, str(exp)))
			

		if (self.monserver == None):
			LogManager.getInstance().logError("YencaP service did not start. Check the configuration file.")
			sys.exit(1)
		else:
			return self.monserver

