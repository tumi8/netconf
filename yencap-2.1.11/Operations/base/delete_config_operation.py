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

from Operations.operationReply import OperationReply
from Operations.operation import Operation
from constants import C
from Ft.Xml import XPath
from xml.dom import Node
from Modules.modulereply import ModuleReply
import os
from Modules import DatastoreManager
from rbac import rbacManager


class Delete_config_operation(Operation):
	

	def __init__(self): 
		"""
			Constructor of a Lock_operation command.
		"""
		# Get the unique instance of DatastoreManager class
		self.datastoreManager = DatastoreManager.getInstance()
		self.rbacManager = rbacManager.getInstance()

		# "running", "startup", "candidate", "url"
		self.target = None
		# target url text value (e.g.: ftp://madynes.loria.fr/config.txt)
		self.urlTarget = None


	def execute(self):
		"""
			Execute the delete-config operation.
		"""

		if self.operationReply.isError():
			return self.operationReply

		
		# Check access here
		if self.rbacManager.isActive():
			operation_allowed = self.rbacManager.checkAccessDeleteConfig(self.session)

			if (not operation_allowed):
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.ACCESS_DENIED,
				error_severity = ModuleReply.ERROR,
				error_message = "Access denied.")
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return self.operationReply


		if self.target in [C.CANDIDATE, C.STARTUP]:
			moduleReply = self.datastoreManager.removeConfig(self.target, "config")

		elif (self.target == C.URL):
			# BUG : should delete the specified url (e.g. on a ftp server)
			moduleReply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity = ModuleReply.ERROR,
			error_message = "Cannot delete the document at the specified url.")
			
		if moduleReply.isError():
			self.operationReply.setError()

		self.operationReply.setNode(moduleReply.getXMLNodeReply())

		return self.operationReply


	def  setParameters(self, operation, NSS = None):
		"""
		Set the target parameter

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""

		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:
				if child.tagName == C.TARGET:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName in [C.CANDIDATE, C.STARTUP]:
								self.target = node.tagName
							elif node.tagName == C.URL:
								self.urlTarget = string.strip(str(node.childNodes[0].nodeValue))
							elif node.tagName == C.RUNNING:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.INVALID_VALUE,
								error_severity = ModuleReply.ERROR,
								error_message = "The running configuration can not be deleted.")
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.UNKNOWN_ELEMENT,
								error_severity = ModuleReply.ERROR,
								error_message = "An element is not known.")
								moduleReply.addErrorInfo("bad-element",node.tagName)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return
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

