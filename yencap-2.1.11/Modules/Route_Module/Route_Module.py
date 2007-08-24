###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Vincent CRIDLIG                                         #
#                                                                             #
# This program is free software; you can redistribute it and/or               #
# modify it under the terms of the GNU General Public License                 #
# as published by the Free Software Foundation; either version 2              #
# of the License, or (at your option) any later version.                      #
#                                                                             #
# This program is distributed in the hope that it will be useful,             #
# but WITHOUT ANY WARRANTY; without even the implied warranty of              #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               #
# GNU General Public License for more details.                                #
#                                                                             #
# You should have received a copy of the GNU General Public License           #
# along with this program; if not, write to the Free Software                 #
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. #
#                                                                             #
# Author Info:                                                                #
#   Name : Vincent CRIDLIG                                                    #
#   Email: Vincent.Cridlig@loria.fr                                           #
#                                                                             #
###############################################################################


import os, string

from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from Ft.Xml import XPath, EMPTY_NAMESPACE
from xml.dom import Node

from Modules.modulereply import ModuleReply 
from Modules.module import Module 

from constants import C

from routeManagerV6 import RouteManagerV6
from routeManagerV4 import RouteManagerV4


class Route_Module(Module):
	"""
		Main class of Route module. Allows routing table management.
	"""
	
	
	def __init__(self, name, path, namespace, cacheLifetime,parameters):
		"""
		Create an instance and initialize the structure needed by it.
		@type  parameters: dictionary
		@param parameters : should be a dictionary containning the follow keys
		"""
		Module.__init__(self, name, path, namespace, cacheLifetime)
		
		self.ipVersion = parameters["ip-version"]
		


	def getConfig(self):

		if self.ipVersion=="6":
			self.routeManager = RouteManagerV6(self.namespace)
		elif self.ipVersion=="4":
			self.routeManager = RouteManagerV4(self.namespace)
		#else:
		#	through exception...

		modulereply = ModuleReply(replynode=self.routeManager.serialize())
		return modulereply
		
		

	def editConfig(self, defaultoperation, testoption, erroroption, target, confignode, targetnode=None):
		
		#route = self.getRoute()
		
		#routeNodes = XPath.Evaluate("//*[@*[local-name()='operation']]",confignode)
		routeNodes = XPath.Evaluate("//*[local-name()='route-entry']",confignode)
		for routeNode in routeNodes:
			command = ""
			routeStruct = self.buildStruct(routeNode)
			att = routeNode.getAttributeNS(C.NETCONF_XMLNS, 'operation')
			if att=="create":
				command = command + "route add "
			elif att=="delete":
				command = command + "route del "
			
			if routeStruct.has_key("Destination"):
				command = command + routeStruct["Destination"] 
			if routeStruct.has_key("Passerelle"):
				if routeStruct["Passerelle"] != "*":
					command = command + " gw " + routeStruct["Passerelle"] 

			if routeStruct.has_key("Metric"):
				command = command + " metric " + routeStruct["Metric"] 

			if routeStruct.has_key("Genmask"):
				command = command + " netmask " + routeStruct["Genmask"] 

			if routeStruct.has_key("Iface"):
				command = command + " dev " + routeStruct["Iface"] 
			
			print command

		#execute command

		#if success
		modulereply = ModuleReply()
		#else
		#modulereply = ModuleReply(error....)

		return modulereply



	def buildStruct(self, routeNode):

		dictio = {}
		for elem in routeNode.childNodes:
			if elem.nodeType == Node.ELEMENT_NODE:
				print elem.tagName
				print elem.nodeValue
				dictio[elem.tagName] = elem.childNodes[0].nodeValue

		return dictio

