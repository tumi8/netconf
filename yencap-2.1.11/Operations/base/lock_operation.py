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
from rbac import sessionManager
from xml.dom import Node
from constants import C

class Lock_operation(Operation):
	
	def __init__(self): 
		"""
			Constructor of a Lock_operation command.
		"""
		
		# "running", "startup", "candidate", "url"
		self.target = None
		

	def execute(self):
		"""
			Execute the lock operation.
		"""
		nodeReply = sessionManager.getInstance().lock(self.session, self.target)
		self.operationReply.setNode(nodeReply)
		return self.operationReply


	def setParameters(self, operation, NSS = None):
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
							if node.tagName in [C.CANDIDATE, C.STARTUP, C.RUNNING]:
								self.target = node.tagName
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.UNKNOWN_ELEMENT,
								error_severity = ModuleReply.ERROR,
								error_message = "An element is not known.")
								moduleReply.addErrorInfo("unknown-element",node.tagName)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return

				else:
					moduleReply = ModuleReply(
					error_type = ModuleReply.PROTOCOL,
					error_tag = ModuleReply.BAD_ELEMENT,
					error_severity = ModuleReply.ERROR,
					error_message = "An element is not known.")
					moduleReply.addErrorInfo("unknown-element",child.tagName)
					self.operationReply.setError()
					self.operationReply.setNode(moduleReply.getXMLNodeReply())
					return
