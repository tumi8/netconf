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


from Operations.operation import Operation
from Operations.operationReply import OperationReply
from Modules.modulereply import ModuleReply
from Modules import DatastoreManager
from constants import C
from Ft.Xml import XPath, EMPTY_NAMESPACE
from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from xml.dom import Node
import string, time
from Filters.xpathFilter import XpathFilter
from Filters.subtreeFilter import SubtreeFilter
from rbac import rbacManager

class Get_config_operation(Operation):
	"""
		Concrete command (see command design pattern) for Netconf get-config operation.
	"""
	
	def __init__(self):
		"""
			Constructor of a Get_config_operation command.
		"""
		self.datastoreManager = DatastoreManager.getInstance()
		# "running", "startup", "candidate"
		self.source = C.RUNNING
		# filter node
		self.filter = None
		# Initializes the default value for filtering method
		self.filterType = C.SUBTREE
		self.subtree = None
		

	def execute(self):
		"""
			Execute the get-config operation.
		"""

		if self.operationReply.isError():
			return self.operationReply

		if self.source in [C.RUNNING, C.CANDIDATE, C.STARTUP]:
			self.getConfig(self.source)
			
		if self.operationReply.isError():
			return self.operationReply
		
		self.filterResponse()

		if self.operationReply.isError():
			return self.operationReply

		doc = implementation.createDocument(C.NETCONF_XMLNS, 'data', None)
		dataNode = doc.documentElement
		dataNode.appendChild(self.operationReply.getNode())
		self.operationReply.setNode(dataNode)
		return self.operationReply


	def setParameters(self, operation, NSS = None):
		"""
		Set the source and filter parameters

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""
		self.prefixes = NSS
		
		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:

				if child.tagName == C.SOURCE:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName in [C.RUNNING, C.CANDIDATE, C.STARTUP]:
								self.source = node.tagName
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.BAD_ELEMENT,
								error_severity = ModuleReply.ERROR,
								error_message = "An element value is not correct.")
								moduleReply.addErrorInfo("bad-element",node.tagName)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return

				elif child.tagName == C.FILTER:
					self.filter = child
					for att in self.filter.attributes.values():
						if att.name == "type":
							if att.value == C.SUBTREE:
								self.filterType = C.SUBTREE
							elif att.value == C.XPATH:
								self.filterType = C.XPATH
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.BAD_ATTRIBUTE,
								error_severity = ModuleReply.ERROR,
								error_message = "An attribute value is not correct")
								moduleReply.addErrorInfo("bad-attribute",att.name)
								moduleReply.addErrorInfo("bad-element",child.tagName)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return
						else:
							moduleReply = ModuleReply(
							error_type = ModuleReply.PROTOCOL,
							error_tag = ModuleReply.UNKNOWN_ATTRIBUTE,
							error_severity = ModuleReply.ERROR,
							error_message = "An unexpected attribute is present")
							moduleReply.addErrorInfo("bad-attribute",att.name)
							moduleReply.addErrorInfo("bad-element",child.tagName)
							self.operationReply.setError()
							self.operationReply.setNode(moduleReply.getXMLNodeReply())
							return

					if self.filterType == C.XPATH:
						tmpval = self.filter.childNodes[0].nodeValue
						self.xpathrequest = string.strip(str(tmpval))
						if self.xpathrequest == "":
							moduleReply = ModuleReply(
							error_type = ModuleReply.PROTOCOL,
							error_tag = ModuleReply.INVALID_VALUE,
							error_severity = ModuleReply.ERROR,
							error_message = "The request specifies an unacceptable value for one or more parameters.")
							self.operationReply.setError()
							self.operationReply.setNode(moduleReply.getXMLNodeReply())
							return

					elif self.filterType == C.SUBTREE:
						for node in self.filter.childNodes:
							if (node.nodeType==Node.ELEMENT_NODE):
								self.subtree = node
				else:
					moduleReply = ModuleReply(
					error_type = ModuleReply.PROTOCOL,
					error_tag = ModuleReply.UNKNOWN_ELEMENT,
					error_severity = ModuleReply.ERROR,
					error_message = "An element is not known.")
					moduleReply.addErrorInfo("bad-element",child.tagName)
					self.operationReply.setError()
					self.operationReply.setNode(moduleReply.getXMLNodeReply())
					return

		if self.filter == None:
			self.subtree = implementation.createDocument(C.YENCAP_XMLNS, 'netconf', None).documentElement


	def getConfig(self, sourceName):

		doc = self.datastoreManager.getConfig(sourceName, "config")
		if doc == None:
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.UNKNOWN_ELEMENT,
			error_severity = ModuleReply.ERROR,
			error_message = "%s configuration does not exist." % sourceName)
			self.operationReply.setError()
			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return
		else:
			self.operationReply.setNode(doc.documentElement)


	def filterResponse(self):
		# Creating the XML response filter
		moduleReply = None

		abstractFilter = None
		
		if self.filterType == C.SUBTREE:
			if self.subtree != None:
				abstractFilter = SubtreeFilter(self.operationReply.getNode(), self.subtree)
				moduleReply = abstractFilter.applyFilter()
			else:
				moduleReply = ModuleReply(replynode=self.operationReply.getNode())

		elif self.filterType == C.XPATH:
			abstractFilter = XpathFilter(self.operationReply.getNode(), [self.xpathrequest], self.prefixes)
			moduleReply = abstractFilter.applyFilter()

		if moduleReply.isError():
			self.operationReply.setError()
			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return
		else:
			self.operationReply.setNode(moduleReply.getXMLNodeReply())

		rm = rbacManager.getInstance()
		
		if rm.isActive():
			moduleReply = rm.filterNode(self.operationReply.getNode(), self.session)
			if moduleReply.isError():
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return
			else:
				self.operationReply.setNode(moduleReply.getXMLNodeReply())


