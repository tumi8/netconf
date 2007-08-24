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

from xml.dom import Node
from Modules.modulereply import ModuleReply
from Ft.Xml.Domlette import PrettyPrint, implementation
from constants import C
import util
from Operations import OperationManager
import LogManager

class Rpc:

	def __init__(self, rpc, session):
		"""

		"""

		# Initialize rpcReply
		self.rpcReply = ModuleReply()
		# Set the session object
		self.session = session
		# Extract the different parameters
		self.setParameters(rpc)
		

	def execute(self):

		if self.rpcReply.isError():
			nodeReply = self.rpcReply.getXMLNodeReply()
			response =  self.addReplyToRpc(nodeReply)
			return util.convertNodeToString(response)

		try:
			
			name = self.operation.tagName
			
			op = OperationManager.getInstance().getOperation(name)

			if op == None:
				moduleReply = ModuleReply(
				error_type=ModuleReply.PROTOCOL,
				error_tag=ModuleReply.OPERATION_FAILED,
				error_severity=ModuleReply.ERROR,
				error_message="Operation %s couldn't be loaded." % (name))
				nodeReply = moduleReply.getXMLNodeReply()
			else:
				
				op.setOperationReply()
				op.setSession(self.session)
				op.setParameters(self.operation, NSS = self.prefixes)
				
				# Execute the command
				operationReply = op.execute()
				
				# Print the result of the command
				nodeReply = operationReply.getNode()

		except Exception,exp:
			import traceback
			traceback.print_exc()
			LogManager.getInstance().logError("rpc.py:ERROR: %s" % str(exp))
			moduleReply = ModuleReply(
			error_type=ModuleReply.PROTOCOL,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message=str(exp))
			nodeReply = moduleReply.getXMLNodeReply()

		response =  self.addReplyToRpc(nodeReply)

		return util.convertNodeToString(response)


		
	def addReplyToRpc(self, nodeReply):
		"""
		Create an <rpc-reply> node containing the stringResponse parameter.

		@type  stringResponse: string
		@param stringResponse: the serialized XML response to be embedded into <rpc-reply>.
		@rtype: string
		@return: The serialized XML <rpc-reply> node.
		"""

		doc = implementation.createDocument(C.NETCONF_XMLNS, None, None)
		rpcReplyNode = doc.createElementNS(C.NETCONF_XMLNS, C.RPC_REPLY)
		
		if self.messageId != None:
			rpcReplyNode.setAttributeNS(C.NETCONF_XMLNS, C.MESSAGE_ID, str(self.messageId))

		rpcReplyNode.appendChild(nodeReply)
		
		return rpcReplyNode


	def setParameters(self, rpc):
		"""

		"""
		
		message_Id_Attribute_Found = False
		self.prefixes = {}
		
		for att in rpc.attributes.values():
			if att.name == C.MESSAGE_ID:
				message_Id_Attribute_Found = True
				try:
					self.messageId = int(att.value)
				except Exception, exp:
					self.rpcReply = ModuleReply(
					error_type = ModuleReply.PROTOCOL,
					error_tag = ModuleReply.BAD_ATTRIBUTE,
					error_severity = ModuleReply.ERROR,
					error_message = "An attribute value is not correct")
					self.rpcReply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO, att.name)
					self.rpcReply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO, rpc.tagName)
					return
			
			elif att.name == C.XMLNS or att.name == C.XMLNS+":xc":
				if att.value != C.NETCONF_XMLNS:
					self.rpcReply = ModuleReply(
					error_type = ModuleReply.PROTOCOL,
					error_tag = ModuleReply.BAD_ATTRIBUTE,
					error_severity = ModuleReply.ERROR,
					error_message = "An attribute value is not correct")
					self.rpcReply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO, att.name)
					self.rpcReply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO, rpc.tagName)
					return

			# This is commented just to try prefixes. It generates errors otherwise because of the prefix declaration.
			else:
				l = att.name.split(":")
				if l[0] == "xmlns":
					p = l[1] 
					self.prefixes[p] = att.value
					
			#	self.rpcReply = ModuleReply(
			#	error_type = ModuleReply.PROTOCOL,
			#	error_tag = ModuleReply.UNKNOWN_ATTRIBUTE,
			#	error_severity = ModuleReply.ERROR,
			#	error_message = "An unexpected attribute is present")
			#	self.rpcReply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO, att.name)
			#	self.rpcReply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO, rpc.tagName)
			#	return
				
		if message_Id_Attribute_Found == False:
			self.rpcReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.BAD_ATTRIBUTE,
			error_severity = ModuleReply.ERROR,
			error_message = "An attribute value is not correct")
			self.rpcReply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO, att.name)
			self.rpcReply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO, rpc.tagName)
			return

		for operation in rpc.childNodes:
			if (operation.nodeType==Node.ELEMENT_NODE):
				self.operation = operation
