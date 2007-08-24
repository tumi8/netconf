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
from Operations.base.copy_config_operation import Copy_config_operation
from Operations.base.get_config_operation import Get_config_operation
from constants import C
from Ft.Xml import XPath
from xml.dom import Node
from Modules.modulereply import ModuleReply
from rbac import sessionManager
from Ft.Xml.Domlette import PrettyPrint, NonvalidatingReader, implementation
from Ft.Xml import EMPTY_NAMESPACE
import cStringIO, string

class Commit_operation(Operation):
	
	def __init__(self): 
		"""
			Constructor of a Copy_config_operation command.
		"""
		
		pass


	def execute(self):
		"""
			Execute the copy-config operation.
		"""
		
		if self.operationReply.isError():
			return self.operationReply
		
		sessionId = sessionManager.getInstance().getLockOwnerSessionId(C.RUNNING)

		if sessionId >= 0 and sessionId != self.session.getSessionId():
			# Somebody else locked the target.
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.LOCK_DENIED,
			error_severity = ModuleReply.ERROR,
			error_message = "The running datastore is locked by another entity.")
			moduleReply.addErrorInfo("session-id",sessionId)
			self.operationReply.setError()
			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return self.operationReply
		
		# Just do a copy-config from candidate to running
		op = Copy_config_operation()
		op.setOperationReply()
		op.setSession(self.session)
		op.source = C.CANDIDATE
		op.target = C.RUNNING
		
		# Execute the command
		operationReply = op.execute()
		
		return operationReply


	def setParameters(self, operation, NSS = None):
		"""
		Set the source and target parameters

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""

		for child in operation.childNodes:
			# Commit operation has no child
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


