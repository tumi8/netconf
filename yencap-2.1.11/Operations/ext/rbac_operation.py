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
from Ft.Xml.Domlette import implementation
from Modules.modulereply import ModuleReply
from rbac import rbacManager, sessionManager
import string

class Rbac_operation(Operation):

	def __init__(self): 
		"""
			Constructor of a Close_Session_operation command.
		"""
		self.action = ""
		self.roles = []


	def execute(self):
		"""
			Execute the close-session operation.
		"""
		if self.operationReply.isError():
			return self.operationReply

		if self.action == C.ACTIVATE:
			nodeReply = rbacManager.getInstance().activate(self.session, self.roles)
		if self.action == C.DEACTIVATE:
			nodeReply = rbacManager.getInstance().deactivate(self.session, self.roles)
		
		self.operationReply.setNode(nodeReply)
		return self.operationReply


	def  setParameters(self, operation, NSS = None):
		"""
		Set the target parameter

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""

		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:
				if child.tagName in [C.ACTIVATE,C.DEACTIVATE]:
					self.action = child.tagName
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName in C.ROLES:
								for nodeee in node.childNodes:
									if nodeee.nodeType==Node.ELEMENT_NODE:
										if nodeee.tagName in C.ROLE:
											tmpval = nodeee.childNodes[0].nodeValue
											roleName = string.strip(str(tmpval))
											role = rbacManager.getInstance().getRoleFromName(roleName)
											if role == None:
												moduleReply = ModuleReply(
												error_type = ModuleReply.PROTOCOL,
												error_tag = ModuleReply.UNKNOWN_ELEMENT,
												error_severity = ModuleReply.ERROR,
												error_message = "Role " + roleName + " does not exist.")
												moduleReply.addErrorInfo("bad-element", roleName)
												self.operationReply.setError()
												self.operationReply.setNode(moduleReply.getXMLNodeReply())
												return
											else:
												self.roles.append(role)
										else:
											moduleReply = ModuleReply(
											error_type = ModuleReply.PROTOCOL,
											error_tag = ModuleReply.UNKNOWN_ELEMENT,
											error_severity = ModuleReply.ERROR,
											error_message = "An element is not known.")
											moduleReply.addErrorInfo("bad-element",nodeee.tagName)
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

