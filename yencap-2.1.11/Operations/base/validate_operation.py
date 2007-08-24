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
from Ft.Xml import XPath, InputSource
from Ft.Xml.Xslt import Processor, DomWriter
from xml.dom import Node
from Modules.modulereply import ModuleReply
from Modules import DatastoreManager
from Modules import ModuleManager
from rbac import sessionManager
from Ft.Xml.Domlette import PrettyPrint, NonvalidatingReader, implementation
import util
from rbac import rbacManager
from Ft.Xml.XPath import Evaluate
from Ft.Xml.XPath.Context import Context

class Validate_operation(Operation):
	
	def __init__(self): 
		"""
			Instanciates the validate operation
		"""
		self.datastoreManager = DatastoreManager.getInstance()
		self.moduleManager = ModuleManager.getInstance()
		self.rbacManager = rbacManager.getInstance()
		self.config = None
		
		
		
	def execute(self):
		"""
			Execute the validate operation.
		"""
		if self.operationReply.isError():
			return self.operationReply
		
		# Execute validate
		if (self.target in [C.CANDIDATE, C.STARTUP, C.RUNNING]):
			moduleReply = None
			for module in self.moduleManager.getModules():
				
				moduleReply = module.validate(self.target)
				
    				if moduleReply.isError():
        				self.operationReply.setNode(moduleReply.getXMLNodeReply())
					return self.operationReply
			
			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return self.operationReply
				
		elif (self.target == C.CONFIG):
			moduleReply = None
			for module in self.moduleManager.getModules():
				currentPath = module.path
				# Finding the nodes (related to the current module)
				# in the received configuration
				ctx = Context(self.config, processorNss = self.moduleManager.getPrefixes())
				nodeList = Evaluate(currentPath, ctx)

				# Distributing the nodes to the current module
				if len(nodeList)>1:
					moduleReply = ModuleReply(
					error_type = ModuleReply.APPLICATION,
					error_tag = ModuleReply.UNKNOWN_ELEMENT,
					error_severity = ModuleReply.ERROR,
					error_message = "More than one node found in the received data for the same module" + module.name)
					self.operationReply.setError()
					self.operationReply.setNode(moduleReply.getXMLNodeReply())
					return self.operationReply

				elif len(nodeList)==1:
					#Get the configuration to validate.
					node = nodeList[0]
					
					# Send the operation to the module.
					moduleReply = module.validate(self.target, self.config)

					if (moduleReply.isError()):
						self.operationReply.setError()
						self.operationReply.setNode(moduleReply.getXMLNodeReply())
						return self.operationReply
	

			if (moduleReply == None):
				moduleReply = ModuleReply(
				error_type = ModuleReply.APPLICATION,
				error_tag = ModuleReply.UNKNOWN_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Not any module matched")
				self.operationReply.setError()

			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return self.operationReply

		#If somebody has the strange idea to try this:
		elif (self.target == C.URL):
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED")
			
			self.operationReply.setNode(xmlReply.getXMLNodeReply())
			return self.operationReply

			

	def setParameters(self, operation, NSS = None):
		"""
		Set the source parameter and the config parameter, config then contains the configuration to validate.

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""
		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:
				if child.tagName == C.SOURCE:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName in [C.CANDIDATE, C.STARTUP, C.RUNNING]:
								self.target = node.tagName			
							elif node.tagName == C.CONFIG:
								self.target = node.tagName
								for blub in node.childNodes:
									if blub.nodeType==Node.ELEMENT_NODE:
										if blub.localName == "netconf" and blub.namespaceURI == C.YENCAP_XMLNS:
											# We build a new document containing the "filter" to compare more easily
											self.config =  implementation.createDocument(C.YENCAP_XMLNS, "netconf", None)
											clone = self.config.importNode(blub, True)
											# self.config is a document which documentElement is "netconf"
											self.config.replaceChild(clone, self.config.documentElement)
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
		
