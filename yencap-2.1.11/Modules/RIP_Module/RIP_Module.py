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
from Ft.Xml.XPath import Evaluate, Compile
from Ft.Xml.XPath.Context import Context

from Modules.modulereply import ModuleReply 
from Modules.module import Module 

from constants import C
from RIP_Manager import RIP_Manager
from RIP_Commands.Redistribute_RC import Redistribute_RC
from RIP_Commands.Network_RC import Network_RC
from RIP_Commands.Neighbor_RC import Neighbor_RC
from RIP_Commands.PassiveInterface_RC import PassiveInterface_RC
from RIP_Commands.DistributeList_RC import DistributeList_RC
from RIP_Commands.Route_RC import Route_RC


class RIP_Module(Module):
	"""
		Main class of Route module. Allows routing table management.
	"""
	
	
	def __init__(self, name, path, namespace, cacheLifetime, parameters):
		"""
		Create an instance and initialize the structure needed by it.
		@type  parameters: dictionary
		@param parameters : should be a dictionary containning the follow keys
		"""
		Module.__init__(self, name, path, namespace, cacheLifetime)
		


	def getConfig(self, configName):

		if configName in [C.RUNNING, C.STARTUP]:
			self.ripManager = RIP_Manager(self.namespace)
			commands = self.ripManager.readCommands(configName)
			modulereply = ModuleReply(replynode=self.ripManager.serialize(commands))
		#elif configName == C.STARTUP:
		#	modulereply = ModuleReply(
		#	error_type = ModuleReply.APPLICATION,
		#	error_tag = ModuleReply.OPERATION_FAILED,
		#	error_severity = ModuleReply.ERROR,
		#	error_message = "RIP does not support startup yet.")
		elif configName == C.CANDIDATE:
			modulereply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "RIP does not support candidate yet.")
		else:
			modulereply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "config %s is not known." % configName)
		return modulereply
		
		

	def editConfig(self, defaultoperation, testoption, erroroption, target, confignode, targetnode=None):

		if target == C.CANDIDATE:
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity=ModuleReply.ERROR,
			error_message="The %s Interface doesn't support 'edit-config' in candidate configuration yet." % self.name)
			return xmlreply 

		self.ripManager = RIP_Manager(self.namespace)
		commands = []

		d = self.findEditOp(confignode)
		ec = self.ripManager.readCommands(target)

		for item in d:
			NS_RIP_Module = "urn:loria:madynes:ensuite:yencap:module:RIP:1.0"
			name = string.strip(str(item.tagName))
			operation = d[item]
			if name == "redistribute":
						
				if operation == C.CREATE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "redistribute":
							moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = "error: can not create redistribute: redistribute already exists.")
							return moduleReply
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							name = str(child.tagName)
							if name in ["bgp", "kernel", "connected", "ospf", "static"]:
								metric = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "metric")))
								route_map = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "route-map")))
								command = Redistribute_RC(name, metric, route_map)
								commands.append(command)
		
				elif operation == C.DELETE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "redistribute":
							command.revert()
							commands.append(command)
					
				elif operation == C.REPLACE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "redistribute":
							command.revert()
							commands.append(command)
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							name = str(child.tagName)
							if name in ["bgp", "kernel", "connected", "ospf", "static"]:
								metric = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "metric")))
								route_map = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "route-map")))
								command = Redistribute_RC(name, metric, route_map)
								commands.append(command)
					
				elif operation == C.MERGE:
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							name = str(child.tagName)
							if name in ["bgp", "kernel", "connected", "ospf", "static"]:
								metric = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "metric")))
								route_map = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "route-map")))
								command = Redistribute_RC(name, metric, route_map)
								commands.append(command)
					
			
			elif name in ["bgp", "kernel", "connected", "ospf", "static"]:
				metric = string.strip(str(item.getAttributeNS(EMPTY_NAMESPACE, "metric")))
				route_map = string.strip(str(item.getAttributeNS(EMPTY_NAMESPACE, "route-map")))
				command = Redistribute_RC(name, metric, route_map)
				rc = self.ripManager.find(command, target)
		
				if operation == C.CREATE:
					if rc != None:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not create " + name + " because it already exists.")
						return moduleReply
					else:
						commands.append(command)
		
				elif operation == C.DELETE:
					if rc != None:
						command.revert()
						commands.append(command)
					else:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not delete " + name + " because it does not exist.")
						return moduleReply
		
				elif operation == C.REPLACE:
					if rc != None:
						rc.revert()
						commands.append(rc)
		
					commands.append(command)
				
				elif operation == C.MERGE:
					commands.append(command)
		
			elif name == "networks":
				
				if operation == C.CREATE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "network":
							moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = "error: can not create " + name + " because it already exists.")
							return moduleReply
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "network":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Network_RC(network=value)
										commands.append(command)
		
				elif operation == C.DELETE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "network":
							command.revert()
							commands.append(command)
					
				elif operation == C.REPLACE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "network":
							command.revert()
							commands.append(command)
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "network":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Network_RC(network=value)
										commands.append(command)
					
				elif operation == C.MERGE:
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "network":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Network_RC(network=value)
										commands.append(command)
					
			elif name == "network":
				for node in item.childNodes:
					if node.nodeType==Node.TEXT_NODE:
						value = string.strip(str(node.nodeValue))
						command = Network_RC(network=value)
		
				rc = self.ripManager.find(command, target)
		
				if operation == C.CREATE:
					if rc != None:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not create " + name + " because it already exists.")
						return moduleReply
					else:
						commands.append(command)
		
				elif operation == C.DELETE:
					if rc != None:
						command.revert()
						commands.append(command)
					else:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not delete " + name + " because it does not exist.")
						return moduleReply
		
				elif operation == C.REPLACE:
					if rc != None:
						rc.revert()
						commands.append(rc)
		
					commands.append(command)
				
				elif operation == C.MERGE:
					commands.append(command)
				
		
			elif name == "neighbors":
				
				if operation == C.CREATE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "neighbor":
							moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = "error: can not create " + name + " because it already exists.")
							return moduleReply
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "neighbor":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Neighbor_RC(neighbor=value)
										commands.append(command)
		
				elif operation == C.DELETE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "neighbor":
							command.revert()
							commands.append(command)
					
				elif operation == C.REPLACE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "neighbor":
							command.revert()
							commands.append(command)
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "neighbor":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Neighbor_RC(neighbor=value)
										commands.append(command)
					
				elif operation == C.MERGE:
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "neighbor":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Neighbor_RC(neighbor=value)
										commands.append(command)
					
			elif name == "neighbor":
				for node in item.childNodes:
					if node.nodeType==Node.TEXT_NODE:
						value = string.strip(str(node.nodeValue))
						command = Neighbor_RC(neighbor=value)
		
				rc = self.ripManager.find(command, target)
		
				if operation == C.CREATE:
					if rc != None:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not create " + name + " because it already exists.")
						return moduleReply
					else:
						commands.append(command)
		
				elif operation == C.DELETE:
					if rc != None:
						command.revert()
						commands.append(command)
					else:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not delete " + name + " because it does not exist.")
						return moduleReply
		
				elif operation == C.REPLACE:
					if rc != None:
						rc.revert()
						commands.append(rc)
		
					commands.append(command)
				
				elif operation == C.MERGE:
					commands.append(command)
		
			elif name == "passive-interfaces":
				
				if operation == C.CREATE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "passive-interface":
							moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = "error: can not create " + name + " because it already exists.")
							return moduleReply
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "passive-interface":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = PassiveInterface_RC(ifname=value)
										commands.append(command)
		
				elif operation == C.DELETE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "passive-interface":
							command.revert()
							commands.append(command)
					
				elif operation == C.REPLACE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "passive-interface":
							command.revert()
							commands.append(command)
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "passive-interface":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = PassiveInterface_RC(ifname=value)
										commands.append(command)
					
				elif operation == C.MERGE:
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "passive-interface":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = PassiveInterface_RC(ifname=value)
										commands.append(command)
					
			elif name == "passive-interface":
				for node in item.childNodes:
					if node.nodeType==Node.TEXT_NODE:
						value = string.strip(str(node.nodeValue))
						command = PassiveInterface_RC(ifname=value)
		
				rc = self.ripManager.find(command, target)
		
				if operation == C.CREATE:
					if rc != None:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not create " + name + " because it already exists.")
						return moduleReply
					else:
						commands.append(command)
		
				elif operation == C.DELETE:
					if rc != None:
						command.revert()
						commands.append(command)
					else:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not delete " + name + " because it does not exist.")
						return moduleReply
		
				elif operation == C.REPLACE:
					if rc != None:
						rc.revert()
						commands.append(rc)
		
					commands.append(command)
				
				elif operation == C.MERGE:
					commands.append(command)
		
		
			elif name == "distribute-lists":
				
				if operation == C.CREATE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "distribute-list":
							moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = "error: can not create " + name + " because it already exists.")
							return moduleReply
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "distribute-list":
								name = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "name")))
								direct = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "direct")))
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = DistributeList_RC(listname, direct, value)
										commands.append(command)
		
				elif operation == C.DELETE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "distribute-list":
							command.revert()
							commands.append(command)
					
				elif operation == C.REPLACE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "distribute-list":
							command.revert()
							commands.append(command)
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "distribute-list":
								name = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "name")))
								direct = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "direct")))
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = DistributeList_RC(listname, direct, value)
										commands.append(command)
					
				elif operation == C.MERGE:
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "distribute-list":
								name = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "name")))
								direct = string.strip(str(child.getAttributeNS(EMPTY_NAMESPACE, "direct")))
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = DistributeList_RC(listname, direct, value)
										commands.append(command)
					
			elif name == "distribute-list":
				listname = string.strip(str(item.getAttributeNS(EMPTY_NAMESPACE, "name")))
				direct = string.strip(str(item.getAttributeNS(EMPTY_NAMESPACE, "direct")))
				for node in item.childNodes:
					if node.nodeType==Node.TEXT_NODE:
						value = string.strip(str(node.nodeValue))
						command = DistributeList_RC(listname, direct, value)
		
				rc = self.ripManager.find(command, target)
		
				if operation == C.CREATE:
					if rc != None:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not create " + name + " because it already exists.")
						return moduleReply
					else:
						commands.append(command)
		
				elif operation == C.DELETE:
					if rc != None:
						command.revert()
						commands.append(command)
					else:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not delete " + name + " because it does not exist.")
						return moduleReply
		
				elif operation == C.REPLACE:
					if rc != None:
						rc.revert()
						commands.append(rc)
		
					commands.append(command)
				
				elif operation == C.MERGE:
					commands.append(command)
		
		
			elif name == "routes":
				
				if operation == C.CREATE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "route":
							moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = "error: can not create " + name + " because it already exists.")
							return moduleReply
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "route":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Route_RC(network=value)
										commands.append(command)
		
				elif operation == C.DELETE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "route":
							command.revert()
							commands.append(command)
					
				elif operation == C.REPLACE:
					# Try to find a redistribute command
					for command in ec:
						if command.name == "route":
							command.revert()
							commands.append(command)
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "route":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Route_RC(network=value)
										commands.append(command)
					
				elif operation == C.MERGE:
					# Build the new commands
					for child in item.childNodes:
						if child.nodeType==Node.ELEMENT_NODE:
							if child.tagName == "route":
								for node in child.childNodes:
									if node.nodeType==Node.TEXT_NODE:
										value = string.strip(str(node.nodeValue))
										command = Route_RC(network=value)
										commands.append(command)
					
			elif name == "route":
				for node in item.childNodes:
					if node.nodeType==Node.TEXT_NODE:
						value = string.strip(str(node.nodeValue))
						command = Route_RC(network=value)
		
				rc = self.ripManager.find(command, target)
		
				if operation == C.CREATE:
					if rc != None:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not create " + name + " because it already exists.")
						return moduleReply
					else:
						commands.append(command)
		
				elif operation == C.DELETE:
					if rc != None:
						command.revert()
						commands.append(command)
					else:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.OPERATION_FAILED,
						error_severity = ModuleReply.ERROR,
						error_message = "error: can not delete " + name + " because it does not exist.")
						return moduleReply
		
				elif operation == C.REPLACE:
					if rc != None:
						rc.revert()
						commands.append(rc)
		
					commands.append(command)
				
				elif operation == C.MERGE:
					commands.append(command)
				
		#for command in commands:
		#	print command.toCLI()

		# Do commands
		try:
			self.ripManager.processCommands(target, commands)
			modulereply = ModuleReply()
		except Exception, exp:
			modulereply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = str(exp))

		return modulereply


	def copyConfig(self, sourceName, targetName, sourceNode = None):
		"""
			Copy the sourceName configuration of the current module to the targetName configuration.
			@type targetName: string
			@param targetName: "running", "startup", "candidate" configuration datastore
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
			** Relates to the netconf copy-config operation
		"""
		
		if targetName == C.CANDIDATE:
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity=ModuleReply.ERROR,
			error_message="The %s Interface doesn't support 'copy-config' in candidate configuration yet." % self.name)
			return xmlreply

		if (sourceName in [C.RUNNING, C.STARTUP] and targetName in [C.RUNNING, C.STARTUP]):
			print self.name
			print sourceName + " " + targetName
			try:
				
				self.ripManager = RIP_Manager(self.namespace)
				xmlreply = self.ripManager.copyConfiguration(sourceName, targetName)

				
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

		else:
			xmlreply = Module.copyConfig(self, sourceName, targetName, sourceNode)
		
		return xmlreply



	def findEditOp(self, confignode):
		result = {}
		
		NSS = {u"xc": C.NETCONF_XMLNS, u"yp": C.YENCAP_XMLNS, u"rip": self.namespace }
		ctx = Context(confignode, processorNss = NSS)
		nodes = Evaluate(u"//*[@xc:operation]",ctx)
		
		for node in nodes:
			result[node] = str(node.getAttributeNS(C.NETCONF_XMLNS, C.OPERATION))

		return result
