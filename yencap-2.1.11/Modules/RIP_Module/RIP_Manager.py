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

from RIP_VTY_Connection import VTY_Connection
import RIP_Parser
from RIP_Commands.RIP_Command import RIP_Command
from Ft.Xml.Domlette import PrettyPrint, implementation
from Modules.modulereply import ModuleReply


class RIP_Manager:

	def __init__(self, namespace):
		self.namespace = namespace
		host = "localhost"
		port = "2602"
		password = "zebra"
		enable_password = "zebra"
		self.vty_conex = VTY_Connection(host,port,password,enable_password)
		#commands = self.readCommands()
		commands = []
		elem = self.serialize(commands)
		#PrettyPrint(elem)

	def processCommands(self, target, toDoCommands):
		vty_conex_id = self.vty_conex.stablishConnection()
		self.vty_conex.setConfigureState(vty_conex_id, True)
		id,text = self.vty_conex.sendCommand(vty_conex_id, "router rip")
		for command in toDoCommands:
			command.do(self.vty_conex, vty_conex_id)
		self.vty_conex.setConfigureState(vty_conex_id, False)
		self.vty_conex.closeConnection(vty_conex_id)


	def readCommands(self, configName):
		vty_conex_id = self.vty_conex.stablishConnection()
		# faire plutot une commande show-config et parser pour construire les commandes.
		id,text = self.vty_conex.getConfiguration(vty_conex_id, configName, "rip")
		self.vty_conex.closeConnection(vty_conex_id)
		return RIP_Parser.parse("goal", text)


	def resetRip(self, targetName):
		vty_conex_id = self.vty_conex.stablishConnection()
		self.vty_conex.setConfigureState(vty_conex_id, True)
		existingCommands = self.readCommands(targetName)
		for command in existingCommands:
			command.undo(vty_conex, vty_conex_id)
		self.vty_conex.setConfigureState(vty_conex_id, False)
		vty_conex.closeConnection(vty_conex_id)
		

	"""
	def applyTextCLI(self, command):
		vty_conex_id = self.vty_conex.stablishConnection()
		self.vty_conex.setConfigureState(vty_conex_id)
		id,text = self.vty_conex.sendCommand(vty_conex_id, command)
		self.vty_conex.closeConnection(vty_conex_id)
		return text
	"""
	def find(self, command, targetName):
		commands = self.readCommands(targetName)
		for c in commands:
			if command.equals(c):
				return c
		return None


	def serialize(self, commands):
		doc = implementation.createDocument(self.namespace, None, None)
		element = doc.createElementNS(self.namespace,"rip")
		element.setAttributeNS(self.namespace, "version", "2")
		doc.appendChild(element)

		redistributeNode = None
		networkNode = None
		neighborNode = None
		passive_interfaceNode = None
		routeNode = None
		distribute_listNode = None

		for command in commands:
			node = command.toXML(doc, self.namespace)
			if command.name == "redistribute":
				if redistributeNode == None:
					redistributeNode = doc.createElementNS(self.namespace,"redistribute")
					element.appendChild(redistributeNode)
				redistributeNode.appendChild(node)
			elif command.name == "network":
				if networkNode == None:
					networkNode = doc.createElementNS(self.namespace,"networks")
					element.appendChild(networkNode)
				networkNode.appendChild(node)
			elif command.name == "neighbor":
				if neighborNode == None:
					neighborNode = doc.createElementNS(self.namespace,"neighbors")
					element.appendChild(neighborNode)
				neighborNode.appendChild(node)
			elif command.name == "passive-interface":
				if passive_interfaceNode == None:
					passive_interfaceNode = doc.createElementNS(self.namespace,"passive-interfaces")
					element.appendChild(passive_interfaceNode)
				passive_interfaceNode.appendChild(node)
			elif command.name == "route":
				if routeNode == None:
					routeNode = doc.createElementNS(self.namespace,"routes")
					element.appendChild(routeNode)
				routeNode.appendChild(node)
			elif command.name == "distribute-list":
				if distribute_listNode == None:
					distribute_listNode = doc.createElementNS(self.namespace,"distribute-lists")
					element.appendChild(distribute_listNode)
				distribute_listNode.appendChild(node)
		
		return doc.documentElement

	

	def copyConfiguration(self, sourceName, targetName):

		try:
			id = self.vty_conex.stablishConnection()
			id, text = self.vty_conex.copyConfiguration(id, sourceName, targetName)
			self.vty_conex.closeConnection(id)
			
			print text
			print text.startswith("copy")
			print text.startswith("copy running-config startup-config")
			print text.startswith("copy running-config startup-config \nConfiguration sa")
			
			if text.find("Configuration saved to") != -1:
				xmlreply = ModuleReply()
			else:
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.RESOURCE_DENIED, 
				error_severity=ModuleReply.ERROR,
				error_message= text+"\n")
				
		except ConnectError , exp :
			import traceback
			traceback.print_exc()
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.RESOURCE_DENIED,
			error_severity=ModuleReply.ERROR,
			error_tag_app=exp.getError(),
			error_message=str(exp))
		except Exception,exp:
			import traceback
			traceback.print_exc()
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message=str(exp))

		return xmlreply
