###############################################################################
#                                                                             #
# PYenca software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
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
#   Name : Humberto ABDELNUR and Vincent CRIDLIG                              #
#   Email: Humberto.Abdelnur@loria.fr, Vincent.Cridlig@loria.fr               #
#                                                                             #
###############################################################################


from Ft.Xml.Domlette import NonvalidatingReader, implementation
from constants import C


class ModuleReply:
	"""
		This class is used as a container of data for every module reply to the Agent.
	"""
	
	###	Tag ###
	
	IN_USE					= "IN_USE"
	INVALID_VALUE			= "INVALID_VALUE"
	TOO_BIG 				= "TOO_BIG" 
	MISSING_ATTRIBUTE 		= "MISSING_ATTRIBUTE"
	BAD_ATTRIBUTE 			= "BAD_ATTRIBUTE"
	UNKNOWN_ATTRIBUTE		= "UNKNOWN_ATTRIBUTE"
	MISSING_ELEMENT			= "MISSING_ELEMENT"
	BAD_ELEMENT				= "BAD_ELEMENT"
	UNKNOWN_ELEMENT			= "UNKNOWN_ELEMENT"
	ACCESS_DENIED			= "ACCESS_DENIED"
	LOCK_DENIED				= "LOCK_DENIED"
	RESOURCE_DENIED			= "RESOURCE_DENIED"
	ROLLBACK_FAILED			= "ROLLBACK_FAILED"
	DATA_EXISTS				= "DATA_EXISTS"
	DATA_MISSING			= "DATA_MISSING"
	OPERATION_NOT_SUPPORTED	= "OPERATION_NOT_SUPPORTED"
	OPERATION_FAILED		= "OPERATION_FAILED"
	PARTIAL_OPERATION		= "PARTIAL_OPERATION"
	
	###  Error-type ###
	
	TRANSPORT 				= "transport"
	RPC 					= "rpc"
	PROTOCOL				= "protocol"
	APPLICATION				= "application"
	
	###  Severity  ###
	
	ERROR					= "error"
	WARNING				= "warning"
	
	### Error-info ###
	
	BAD_ATTRIBUTE_INFO		= "bad-attribute" 
	BAD_ELEMENT_INFO		= "bad-element" 
	SESSION_ID_INFO			= "session-id"
	OK_ELEMENT_INFO			= "ok-element"
	ERR_ELEMENT_INFO		= "err-element" 
	NOOP_ELEMENT_INFO		= "noop-element"

	def __init__(self,replynode=None ,error_type=None, error_tag=None, error_severity=None, error_tag_app= None, error_path= None, error_message=None):
		"""
			It creates an Struture that will be used in every function that a module has been called by the Agent
			If the request was succesfull it should create an instance with any parameters.
			If the request produced an XML node that should be included in the reply the instance should be created just with the replynode parameter.
			Otherwise, it shoud specify the error according to the NETCONF Protocol
		"""
		
		self.replynode = replynode
		self.error_type = error_type
		self.error_tag = error_tag
		self.error_severity = error_severity
		self.error_tag_app = error_tag_app
		self.error_path = error_path
		self.error_message = error_message
		self.error_info = []
		
	def setReplyNode(self,xmlnode): 
		self.replynode = xmlnode
	
	def setErrorType(self,error_type):
		self.error_type = error_type
		
	def setErrorTag(self,error_tag):
		self.error_tag = error_tag
		
	def setSeverityError(self,error_severity):
		self.error_severity = error_severity
	
	def setErrorAppTag(self,error_tag_app):
		self.error_tag_app = error_tag_app
	
	def setErrorPath(self,error_path):
		self.error_path = error_path
	
	def setErrorMessage(self,error_message):
		self.error_message = error_message
		
	def addErrorInfo(self,error_info_tag,error_message):
		self.error_info.append((error_info_tag,error_message))
		
	def isError(self):
		return self.error_type != None
	
	def createNode(self,doc,parent,tag,value=None):
		element = doc.createElementNS(C.NETCONF_XMLNS,tag)
		parent.appendChild(element)
		if (value != None):
			text = doc.createTextNode(value)
			element.appendChild(text)
		return element
		
	def getXMLNodeReply(self):
		"""
			Generate the XML node with the contents of the instance.
			@rtype: cDomlette
			@return: It is an XML node.
		"""
		if (self.isError()):
			doc = implementation.createDocument(C.NETCONF_XMLNS, None, None)
			
			rpcerrornode = self.createNode(doc=doc, parent=doc,tag="rpc-error",value=None)
			self.createNode(doc=doc,parent=rpcerrornode,tag="error-type",value=self.error_type)
			self.createNode(doc=doc,parent=rpcerrornode,tag="error-tag",value=self.error_tag)
			self.createNode(doc=doc,parent=rpcerrornode,tag="error-severity",value=self.error_severity)
			if ( self.error_tag_app != None):
				self.createNode(doc=doc,parent=rpcerrornode,tag="error-app-tag",value=self.error_tag_app)
			if ( self.error_path != None):
				self.createNode(doc=doc,parent=rpcerrornode,tag="error-path",value=self.error_path)
			if ( self.error_message != None):
				self.createNode(doc=doc,parent=rpcerrornode,tag="error-message",value=self.error_message)
			if ( self.error_info != {}):
				infonode = self.createNode(doc=doc,parent=rpcerrornode,tag="error-info",value=None)
				for key,item in self.error_info:
					self.createNode(doc=doc,parent=infonode,tag=key,value=item)
			self.replynode = doc.documentElement
		elif (self.replynode == None):
			doc = implementation.createDocument(C.NETCONF_XMLNS, None, None)
			self.createNode(doc=doc,parent=doc,tag=C.OK,value=None)
			self.replynode = doc.documentElement
			
		return self.replynode

