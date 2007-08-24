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
#   Name : Humberto ABDELNUR                                                  #
#   Email: Humberto.Abdelnur@loria.fr                                         #
#                                                                             #
###############################################################################

import os
import BGP_quagga_Parser
from Modules.CLI_Module.CLI_Module import CLI_Module


class BGP_Module(CLI_Module):
	"""
		It is the main class of the BGP protocol Module implementation for the NetConf Agent.
	"""
	
	PROTOCOL_STRUCTURE_FILE = os.path.join(os.path.dirname(globals()["__file__"]),"XMLStructure/BGPStructure.xml")
	
	def __init__(self, name, path, namespace, cacheLifetime, parameters):

		CLI_Module.__init__(self, name, path, namespace, cacheLifetime, parameters)
		self.protocol = "bgp"
		

	def parse(self, mainRule,config):

		return BGP_quagga_Parser.parse(mainRule, config)


