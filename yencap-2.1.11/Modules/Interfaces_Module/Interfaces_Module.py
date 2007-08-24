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


from Modules.module import Module
from Modules.modulereply import ModuleReply 

from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from Ft.Xml.XPath.Context import Context
from Ft.Xml import XPath, EMPTY_NAMESPACE
from xml.dom import Node
from Ft.Xml.XPath import Evaluate

import string

from interface import Interface
from constants import C
from command.ifconfig import Ifconfig
from command.setMtu import SetMtu
from command.setBroadcast import SetBroadcast
from command.setNetmask import SetNetmask
from command.setAddr import SetAddr
from command.macroCommand import MacroCommand


class Interfaces_Module(Module):

	def __init__(self, name, path, namespace, cacheLifetime, parameters):
		"""
		Create an instance and initialize the structure needed by it.
		@type  parameters: dictionary
		@param parameters : should be a dictionary containning the follow keys
		"""
		Module.__init__(self, name, path, namespace, cacheLifetime)
		self.interfaces = {}
		

	def getConfig(self):
		"""
			Generate the interfaces XML configuration.
			@rtype: ModuleReply
			@return: the main node of the network interfaces.
			** Relates to the netconf get-config operation
		"""
		
		self.lookupInterfaces()
		self.buildRootNode()
		
		for ifname in self.interfaces.keys():
			ifacenode = self.doc.createElementNS(self.namespace,"interface")
			self.doc.documentElement.appendChild(ifacenode)
			self.interfaces[ifname].serializeConfig(self.doc, ifacenode)
		
		modulereply = ModuleReply(replynode=self.doc.documentElement)
		return modulereply


	def get(self):
		"""
			Generate the interfaces XML configuration.
			@rtype: ModuleReply
			@return: the main node of the network interfaces.
			** Relates to the netconf get-config operation
		"""
		
		self.lookupInterfaces()
		self.buildRootNode()
		
		for ifname in self.interfaces.keys():
			ifacenode = self.doc.createElementNS(self.namespace,"interface")
			self.doc.documentElement.appendChild(ifacenode)
			self.interfaces[ifname].serialize(self.doc, ifacenode)
		
		modulereply = ModuleReply(replynode=self.doc.documentElement)
		return modulereply


	def editConfig(self,defaultoperation,testoption,erroroption,target,confignode,targetnode=None):
		"""
			Apply a ifconfig request from the confignode to the targetnode. delete and create attributes are supported only for iface element. For example, it is not possible to delete the name or the IP address of a network interface. 
			
			@type defaultoperation: MERGE_OPERATION | REPLACE_OPERATION | NONE_OPERATION 
			@param defaultoperation : as specified in NETCONF protocol
			@type testoption : SET | TEST_AND_SET 
			@param testoption : as specified in NETCONF protocol
			@type erroroption : STOP_ON_ERROR | IGNORE_ERROR | ROLL_BACK_ON_ERROR 
			@param erroroption : as specified in NETCONF protocol
			@type target : RUNNING_TARGET | CANDIDATE_TARGET | STARTUP_TARGET
			@param target : as specified in NETCONF protocol
			@type targetnode : string
			@param targetnode : if the target is RUNNING_TARGET or STARTUP_TARGET it will be ignored otherwise should be the node of the CANDIDATE_TARGET that this module should procees
			@rtype: ModuleReply
			@return: It returns a success or error message.
			** Relates to the netconf edit-config operation
        """
		
		moduleReply = ModuleReply(
		error_type = ModuleReply.APPLICATION,
		error_tag = ModuleReply.OPERATION_FAILED,
		error_severity = ModuleReply.ERROR,
		error_message = "Sorry, edit-config is temporarily no more supported for Interfaces.")
		
		# First ,find what to change from edit-config
		# the interface name is the key and can not be changed
		NS= 'urn:ietf:params:xml:ns:netconf:base:1.0'
		NSS = {u'xc': NS }
		ctx = Context (confignode, processorNss = NSS)
		liste = Evaluate("//*[@xc:operation]", ctx)
		if len(liste)>=2:
			moduleReply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "Only one operation attribute is allowed.")
			return moduleReply
		elif len(liste)==1:
			opNode = liste[0]
			#for att in liste[0].attributes.values():
			for att in opNode.attributes:
				ns, name = att
				value = opNode.getAttributeNS(ns, str(name))
				if name == C.OPERATION and ns == C.NETCONF_XMLNS:
					opValue = value
					
		else:
			opValue = defaultoperation
			opNode = confignode

		if (not opValue in [C.MERGE, C.REPLACE, C.CREATE, C.DELETE]):
			moduleReply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "Operation value is not allowed. Must be one of (merge, replace, create, delete).")
			return moduleReply

		macrocommand = MacroCommand()
		
		# On peut optimiser en creant mergeInterface(interfaceNode)
		# On peut optimiser en creant replaceInterface(interfaceNode)
		# Chacune renvoie un tableau de commande qu'on ajouter au tableau global

		if opNode != None:
			nodeName = string.strip(str(opNode.tagName))
			if (nodeName == "interfaces"):
				if (opValue == C.MERGE):
					for node in opNode.childNodes:
						if (node.nodeType==Node.ELEMENT_NODE):
							if (node.tagName == "interface"):
								ifname = None
								mtu = None
								netmask = None
								addr = None
								broadcast = None
								for n in node.childNodes:
									if (n.nodeType==Node.ELEMENT_NODE):
										if (n.tagName == "name"):
											ifname = string.strip(str(n.childNodes[0].nodeValue))
										elif (node.tagName == "mtu"):
											mtu = int(string.strip(str(n.childNodes[0].nodeValue)))
		
								if ifname != None:
									if mtu !=None:
										macrocommand.add(SetMtu(ifname,mtu))
									if broadcast !=None:
										macrocommand.add(SetBroadcast(ifname,broadcast))
									if netmask !=None:
										macrocommand.add(SetNetmask(ifname,netmask))
									if addr != None:
										macrocommand.add(SetAddr(ifname,addr))

				#elif (opValue == C.CREATE):
				#elif (opValue == C.DELETE):
				#...

			elif (nodeName == "interface"):
				ifname = None
				mtu = None
				netmask = None
				addr = None
				broadcast = None
				address = None
				if (opValue == C.MERGE):
					for node in opNode.childNodes:
						if (node.nodeType==Node.ELEMENT_NODE):
							if (node.tagName == "name"):
								ifname = string.strip(str(node.childNodes[0].nodeValue))
							elif (node.tagName == "mtu"):
								mtu = int(string.strip(str(node.childNodes[0].nodeValue)))
							elif (node.tagName == "ipv4"):
								for n in node.childNodes:
									if (n.nodeType==Node.ELEMENT_NODE):
										if (n.tagName == "broadcast"):
											broadcast = string.strip(str(n.childNodes[0].nodeValue))
										elif (n.tagName == "netmask"):
											netmask = string.strip(str(n.childNodes[0].nodeValue))
										elif (n.tagName == "address-v4"):
											address = string.strip(str(n.childNodes[0].nodeValue))
							# ... addr v6...
				
				if ifname != None:
					if mtu != None:
						macrocommand.add(SetMtu(ifname,mtu))
					if broadcast !=None:
						macrocommand.add(SetBroadcast(ifname,broadcast))
					if netmask !=None:
						macrocommand.add(SetNetmask(ifname,netmask))
					if address != None:
						macrocommand.add(SetAddr(ifname,address))

			elif (nodeName == "mtu"):
				ifname = None
				mtu = None
				if (opValue == C.DELETE or opValue == C.CREATE):
					moduleReply = ModuleReply(
					error_type=ModuleReply.PROTOCOL,
					error_tag=ModuleReply.OPERATION_FAILED,
					error_severity=ModuleReply.ERROR,
					error_message="Sorry, mtu can not be deleted or created. Only interface element can.")
					return moduleReply
				elif (opValue == C.MERGE or opValue == C.REPLACE):
					# first find ifName
					for k in opNode.parent.childNodes:
						if (k.nodeType==Node.ELEMENT_NODE):
							if (k.tagName == "name"):
								ifname = string.strip(str(k.childNodes[0].nodeValue))
					# Then get the new mtu value
					mtu = int(string.strip(str(opNode.childNodes[0].nodeValue)))

				if ifname != None:
					# Build the command and add it to the macro command
					macrocommand.add(SetMtu(ifname,mtu))

			elif (nodeName == "broadcast"):
				ifname = None
				broadcast = None
				if (opValue == C.DELETE or opValue == C.CREATE):
					moduleReply = ModuleReply(
					error_type=ModuleReply.PROTOCOL,
					error_tag=ModuleReply.OPERATION_FAILED,
					error_severity=ModuleReply.ERROR,
					error_message="Sorry, broadcast can not be deleted or created. Only interface element can.")
					return moduleReply
				elif (opValue == C.MERGE or opValue == C.REPLACE):
					# first find ifName
					for k in opNode.parent.parent.childNodes:
						if (k.nodeType==Node.ELEMENT_NODE):
							if (k.tagName == "name"):
								ifname = string.strip(str(k.childNodes[0].nodeValue))
					# Then get the new mtu value
					broadcast = int(string.strip(str(opNode.childNodes[0].nodeValue)))

				if ifname != None:
					# Build the command and add it to the macro command
					macrocommand.add(SetBroadcast(ifname,broadcast))

			elif (nodeName == "netmask"):
				ifname = None
				netmask = None
				if (opValue == C.DELETE or opValue == C.CREATE):
					moduleReply = ModuleReply(
					error_type=ModuleReply.PROTOCOL,
					error_tag=ModuleReply.OPERATION_FAILED,
					error_severity=ModuleReply.ERROR,
					error_message="Sorry, netmask can not be deleted or created. Only interface element can.")
					return moduleReply
				elif (opValue == C.MERGE or opValue == C.REPLACE):
					# first find ifName
					for k in opNode.parent.parent.childNodes:
						if (k.nodeType==Node.ELEMENT_NODE):
							if (k.tagName == "name"):
								ifname = string.strip(str(k.childNodes[0].nodeValue))
					# Then get the new mtu value
					netmask = int(string.strip(str(opNode.childNodes[0].nodeValue)))

				if ifname != None:
					# Build the command and add it to the macro command
					macrocommand.add(SetNetmask(ifname,netmask))

			elif (nodeName == "address-v4"):
				ifname = None
				address = None
				if (opValue == C.DELETE or opValue == C.CREATE):
					moduleReply = ModuleReply(
					error_type=ModuleReply.PROTOCOL,
					error_tag=ModuleReply.OPERATION_FAILED,
					error_severity=ModuleReply.ERROR,
					error_message="Sorry, address-v4 can not be deleted or created. Only interface element can.")
					return moduleReply
				elif (opValue == C.MERGE or opValue == C.REPLACE):
					# first find ifName
					for k in opNode.parent.parent.childNodes:
						if (k.nodeType==Node.ELEMENT_NODE):
							if (k.tagName == "name"):
								ifname = string.strip(str(k.childNodes[0].nodeValue))
					# Then get the new mtu value
					address = int(string.strip(str(opNode.childNodes[0].nodeValue)))

				if ifname != None:
					# Build the command and add it to the macro command
					macrocommand.add(SetAddr(ifname,address))

			# elif ...

		# Prepare the commands that must be executed and add them to a macro command
		#macrocommand = MacroCommand()
		#macrocommand.add(SetMtu("eth0",1200))
		#macrocommand.add(SetBroadcast("eth0","152.81.15.255"))
		#macrocommand.add(SetNetmask("eth0","255.255.240.0"))
		#macrocommand.add(SetAddr("eth0","152.81.8.136"))
		
		# Then, make the changes by executing the macro command
		try:
			macrocommand.execute()
			moduleReply = ModuleReply()
		except Exception,exp:
			moduleReply = ModuleReply(
			error_type=ModuleReply.PROTOCOL,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message="Interfaces_Module.py ERROR: "+str(exp))

		# Roll back the macrocommand to not break the testbed :-)
		#macrocommand.rollback()
		
		return moduleReply


	def buildRootNode(self):
		self.doc = implementation.createDocument(self.namespace, None, None)
		interfacesNode = self.doc.createElementNS(self.namespace,"interfaces")
		self.doc.appendChild(interfacesNode)
	

	def lookupInterfaces(self):
		"""
			Lookup interfaces information from the local Operating System
			Stores the info in a interfaces dictionnary which index is the name of the interfaces (e.g.eth0)
		"""
		self.interfaces = {}
		self.lookupIpv6()
		self.lookupIpv4()
		self.lookupStats()
		

	def lookupIpv6(self):
		"""
			Parse /proc/net/if_inet6 and update self.interfaces by filling the IPv6 info of each interface.
		"""

		iflist = open("/proc/net/if_inet6").readlines()
	
		for line in iflist:

			values = line.rstrip("\n").split(" ")
			for i in range(0,values.count("")):
				values.remove("")
			
			name = string.strip(values[5])
	
			interface = self.getInterface(name)
			interface.addV6Address(values)
			

	def lookupIpv4(self):
		"""
			Request the Operating System to find Ipv4 info (not with ifconfig because of the different languages)
		"""
		ifc = Ifconfig()
		ifaces = ifc.getInterfaceList()

		for ifname in ifaces:
			interface = self.getInterface(ifname)
			interface.v4address = ifc.getAddr(ifname)
			interface.macAddress = ifc.getMAC(ifname)
			interface.flagUp = ("false","true")[ifc.getFlagUp(ifname)]
			interface.flagBroadcast = ("false","true")[ifc.getFlagBroadcast(ifname)]
			interface.flagMulticast = ("false","true")[ifc.getFlagMulticast(ifname)]
			interface.flagRunning = ("false","true")[ifc.getFlagRunning(ifname)]
			interface.flagLoopback = ("false","true")[ifc.getFlagLoopback(ifname)]
			interface.v4netmask = ifc.getNetmask(ifname)
			interface.v4broadcast = ifc.getBroadcast(ifname)
			interface.mtu = str(ifc.getMTU(ifname))
			

	def lookupStats(self):
		"""
			Parse /proc/net/dev and lookup the received/transmit packets statistics for each network interface.
		"""
		iflist = open("/proc/net/dev").readlines()
		
		for line in iflist:
			if ':' not in line:
				continue
			words = line.split(":")
			name, rest = words[0],words[1:]
			name = string.strip(name)
			
			interface = self.getInterface(name)
			
			values = rest[0].rstrip("\n").split(" ")
			for i in range(0,values.count("")):
				values.remove("")
			interface.setRcv(values[0:8])
			interface.setTrs(values[8:16])


	def getInterface(self, name):

		interface = None
		if name in self.interfaces.keys():
			interface = self.interfaces[name]
		else:
			interface = Interface(name, self.namespace)
			self.interfaces[name] = interface
		return interface

