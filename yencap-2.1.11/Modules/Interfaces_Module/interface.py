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


from Ft.Xml.Domlette import NonvalidatingReader, implementation
from Ft.Xml import XPath
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from addressV6 import AddressV6
import string

class Interface:
	
	def __init__(self, ifname, namespace):
		self.name = ifname
		self.namespace = namespace
		self.mtu = ""
		self.macAddress = ""

		self.flagUp = "false"
		self.flagBroadcast = "false"
		self.flagRunning = "false"
		self.flagMulticast = "false"
		self.flagLoopback = "false"
		
		self.v4netmask = ""
		self.v4broadcast = ""
		self.v4address = ""
		self.addressesV6 = []

		self.receivedList = ["bytes", "packets", "errs", "drop", "fifo", "frame", "compressed", "multicast"]
		self.transmitList = ["bytes", "packets", "errs", "drop", "fifo", "colls", "carrier", "compressed"]
		
		self.receive = {}
		self.transmit = {}
		
		
	def serialize(self, doc, ifacenode):
		
		self.serializeConfig(doc, ifacenode)
		self.serializeStats(doc, ifacenode)


	def serializeStats(self, doc, ifacenode):

		statsnode = doc.createElementNS(self.namespace,"stats")
		ifacenode.appendChild(statsnode)
		receivenode = doc.createElementNS(self.namespace,"received")
		statsnode.appendChild(receivenode)
		transmitnode = doc.createElementNS(self.namespace,"transmit")
		statsnode.appendChild(transmitnode)
		
		for elem in self.receive.keys():
			node = doc.createElementNS(self.namespace,elem)
			receivenode.appendChild(node)
			value = self.receive[elem]
			textNode = doc.createTextNode(value)
			node.appendChild(textNode)

		for elem in self.transmit.keys():
			node = doc.createElementNS(self.namespace,elem)
			transmitnode.appendChild(node)
			value = self.transmit[elem]
			textNode = doc.createTextNode(value)
			node.appendChild(textNode)


	def serializeConfig(self, doc, ifacenode):

		
		self.addChild(doc, ifacenode, "name", self.name)
		self.addChild(doc, ifacenode, "mac-address", self.macAddress)
		self.addChild(doc, ifacenode, "mtu", self.mtu)
		
		flagsnode = doc.createElementNS(self.namespace,"flags")
		flagsnode.setAttributeNS(self.namespace, "up", self.flagUp)
		flagsnode.setAttributeNS(self.namespace, "broadcast", self.flagBroadcast)
		flagsnode.setAttributeNS(self.namespace, "running", self.flagRunning)
		flagsnode.setAttributeNS(self.namespace, "multicast", self.flagMulticast)
		flagsnode.setAttributeNS(self.namespace, "loopback", self.flagLoopback)
		ifacenode.appendChild(flagsnode)

		ipv4node = doc.createElementNS(self.namespace,"ipv4")
		self.addChild(doc, ipv4node, "netmask", self.v4netmask)
		self.addChild(doc, ipv4node, "broadcast", self.v4broadcast)
		self.addChild(doc, ipv4node, "address-v4", self.v4address)
		ifacenode.appendChild(ipv4node)

		ipv6node = doc.createElementNS(self.namespace,"ipv6")
		for addv6 in self.addressesV6:
			addressnode = doc.createElementNS(self.namespace, "address-v6")
			if addv6.scope=="00":
				addressnode.setAttributeNS(self.namespace, "scope", "global")
			elif addv6.scope=="10":
				addressnode.setAttributeNS(self.namespace, "scope", "node-local")
			elif addv6.scope=="20":
				addressnode.setAttributeNS(self.namespace, "scope", "link-local")
			else:
				addressnode.setAttributeNS(self.namespace, "scope", addv6.scope)
			addressnode.setAttributeNS(self.namespace, "prefix", addv6.prefix)
			textNode = doc.createTextNode(addv6.address)
			addressnode.appendChild(textNode)
			ipv6node.appendChild(addressnode)
		ifacenode.appendChild(ipv6node)
			
	
	def addV6Address(self, liste):
		"""
			liste contains the values of "address", "index", "prefix", "scope", "flag", "name"
		"""
		av6 = AddressV6(liste)
		self.addressesV6.append(av6)
		

	def setRcv(self, liste):
		i=0
		for elem in liste:
			self.receive[self.receivedList[i]] = string.strip(elem)
			i = i+1
		
	def setTrs(self, liste):
		i=0
		for elem in liste:
			self.transmit[self.transmitList[i]] = string.strip(elem)
			i = i+1


	def addChild(self, doc, parent, name, value):
		node = doc.createElementNS(self.namespace, name)
		parent.appendChild(node)
		textNode = doc.createTextNode(value)
		node.appendChild(textNode)


