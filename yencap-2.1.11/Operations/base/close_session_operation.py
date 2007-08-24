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


class Close_session_operation(Operation):

	def __init__(self): 
		"""
			Constructor of a Close_Session_operation command.
		"""
		pass


	def execute(self):
		"""
			Execute the close-session operation.
		"""

		if self.operationReply.isError():
			return self.operationReply

		self.session.mustBeClosed = 1
		moduleReply = ModuleReply()
		self.operationReply.setNode(moduleReply.getXMLNodeReply())
		return self.operationReply


	def setParameters(self, operation, NSS = None):
		"""
		Set the parameters

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""

		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.UNKNOWN_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "An element is not known.")
				moduleReply.addErrorInfo("bad-element",child.tagName)
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return
