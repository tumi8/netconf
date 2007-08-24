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

class Edit_config_operation(Operation):
	
	def __init__(self): 
		"""
			Instanciates the edit-config operation
		"""
		self.datastoreManager = DatastoreManager.getInstance()
		self.moduleManager = ModuleManager.getInstance()
		self.rbacManager = rbacManager.getInstance()

		# Set default values
		self.defaultOperation = C.MERGE
		self.testOption = C.SET
		self.errorOption = C.STOP_ON_ERROR

		
	def execute(self):
		"""
			Execute the edit-config operation.
		"""
		if self.operationReply.isError():
			return self.operationReply

		sessionId = sessionManager.getInstance().getLockOwnerSessionId(self.target)
		
		if sessionId >= 0 and sessionId != self.session.getSessionId():
			# Somebody else locked the target.
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.IN_USE,
			error_severity = ModuleReply.ERROR,
			error_message = "Operation failed, target is locked by another session.")
			moduleReply.addErrorInfo("session-id",sessionId)
			self.operationReply.setError()
			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return self.operationReply
		
		# Check access here
		if self.rbacManager.isActive():
			operation_allowed = self.rbacManager.checkAccessEditConfig(self.config, self.session)

			if (not operation_allowed):
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.ACCESS_DENIED,
				error_severity = ModuleReply.ERROR,
				error_message = "Access denied.")
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return self.operationReply

		# Execute edit-config
		#if (self.target == C.RUNNING or self.target == C.URL):
		if (self.target in [C.RUNNING, C.STARTUP, C.CANDIDATE, C.URL]):
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
					defaultOperation = self.defaultOperation
					node = nodeList[0]
					# Search for the first parent node of 'node' having xc:operation attribute and set the default value to its value:
					tmpNode = node.parentNode
					boolean = False
					while tmpNode != None and tmpNode.nodeType != Node.DOCUMENT_NODE and not boolean:
						for att in tmpNode.attributes:
							ns, name = att
							if name == 'operation' and ns == C.NETCONF_XMLNS:
								# We found the first parent node having an operation attribute.
								# This is the new default op for this module.
								defaultOperation = tmpNode.getAttributeNS(ns, str(name))
								# Stop the loop.
								boolean = True
						tmpNode = tmpNode.parentNode

					# Send the operation to the module.
					moduleReply = module.editConfig(defaultOperation, self.testOption, self.errorOption, self.target, node)
					
					# Update the cache according to the new config.
					module.resetConfigTime(self.target)
					
					if (moduleReply.isError()):
						if self.errorOption == C.STOP_ON_ERROR:
							self.operationReply.setError()
							self.operationReply.setNode(moduleReply.getXMLNodeReply())
							return self.operationReply
						elif self.errorOption == C.CONTINUE_ON_ERROR:
							pass
						elif self.errorOption == C.ROLLBACK_ON_ERROR:
							# ROLLBACK IS EXPERIMENTAL
							for previousmodule in self.moduleManager.getModules():
								if previousmodule is module:
									moduleReply = ModuleReply(
									error_type = ModuleReply.APPLICATION,
									error_tag = ModuleReply.UNKNOWN_ELEMENT,
									error_severity = ModuleReply.ERROR,
									error_message = "%s module failed and the whole operation has been rolled back." % module.name)
									self.operationReply.setError()
									self.operationReply.setNode(moduleReply.getXMLNodeReply())
									return self.operationReply
								else:
									moduleReply = previousmodule.rollBack()
									if moduleReply.isError():
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
			

	def setParameters(self, operation, NSS = None):
		"""
		Set the source and filter parameters

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
								if node.tagName == C.URL:
									self.urlTarget = string.strip(str(node.childNodes[0].nodeValue))
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
				
				elif child.tagName == C.CONFIG:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.localName == "netconf" and node.namespaceURI == C.YENCAP_XMLNS:
								# We build a new document containing the "filter" to compare more easily
								self.config =  implementation.createDocument(C.YENCAP_XMLNS, "netconf", None)
								clone = self.config.importNode(node, True)
								# self.config is a document which documentElement is "netconf"
								self.config.replaceChild(clone, self.config.documentElement)
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.UNKNOWN_ELEMENT,
								error_severity = ModuleReply.ERROR,
								error_message = "The root node of the configuration must be netconf.")
								moduleReply.addErrorInfo("bad-element",node.localName)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return

				elif child.tagName == C.DEFAULT_OPERATION:
					for node in child.childNodes:
						if node.nodeType==Node.TEXT_NODE:
							if node.nodeValue in [C.MERGE,C.REPLACE,C.NONE]:
								self.defaultOperation = node.nodeValue
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.BAD_ELEMENT,
								error_severity = ModuleReply.ERROR,
								error_message = "An element value is not correct.")
								moduleReply.addErrorInfo("bad-element",node.nodeValue)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return

				elif child.tagName == C.TEST_OPTION:
					for node in child.childNodes:
						if node.nodeType==Node.TEXT_NODE:
							if node.nodeValue in [C.TEST_THEN_SET,C.SET]:
								self.testOption = node.nodeValue
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.INVALID_VALUE,
								error_severity = ModuleReply.ERROR,
								error_message = "An element value is not correct.")
								moduleReply.addErrorInfo("bad-element",node.nodeValue)
								self.operationReply.setError()
								self.operationReply.setNode(moduleReply.getXMLNodeReply())
								return

				elif child.tagName == C.ERROR_OPTION:
					for node in child.childNodes:
						if node.nodeType==Node.TEXT_NODE:
							if node.nodeValue in [C.STOP_ON_ERROR, C.CONTINUE_ON_ERROR, C.ROLLBACK_ON_ERROR]:
								self.errorOption = node.nodeValue
							else:
								moduleReply = ModuleReply(
								error_type = ModuleReply.PROTOCOL,
								error_tag = ModuleReply.INVALID_VALUE,
								error_severity = ModuleReply.ERROR,
								error_message = "An element value is not correct.")
								moduleReply.addErrorInfo("bad-element",node.nodeValue)
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
		
		self.checkNestingValidity(NSS)


	def applyEditToXMLNode(self, confignode, target):
		
		metaFile = "Modules/meta.xsl"
		try:
			# Generate a stylesheet equivalent to the edit-config
			
			df = InputSource.DefaultFactory
			editXMLRequest = df.fromString(util.convertNodeToString(confignode), 'urn:dummy')
			stylesheet = df.fromUri("file:" + C.YENCAP_HOME + "/" + metaFile, 'urn:sty')
			p = Processor.Processor()
			p.appendStylesheet(stylesheet)
			wr = DomWriter.DomWriter()
			p.run(editXMLRequest, writer=wr)
			generatedStyleSheet = wr.getResult()
			
			# Apply the generated stylesheet to the source document
			inputStyleSheet = df.fromString(util.convertNodeToString(generatedStyleSheet), 'urn:sty')
			inputDocument = df.fromString(util.convertNodeToString(target), 'urn:dummy')
			p = Processor.Processor()
			p.appendStylesheet(inputStyleSheet)
			wr = DomWriter.DomWriter()
			p.run(inputDocument, writer=wr)
			newXMLDoc = wr.getResult()
			
			# Return the transformed document
			return newXMLDoc

		except Exception,exp:
			moduleReply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message=str(exp))
			return moduleReply


	def checkNestingValidity(self, prefixes):
		
		prefixes["xc"] = C.NETCONF_XMLNS
		xpathRequest = "//*[@xc:operation]"
		ctx = Context(self.config, processorNss = prefixes)
		selectedNodes = Evaluate(xpathRequest, ctx)

		if self.defaultOperation == "replace":
			if len(selectedNodes) != 0:
				#print "When default operation is replace, no other operation attribute is allowed."
				moduleReply = ModuleReply(
				error_type = ModuleReply.APPLICATION,
				error_tag = ModuleReply.UNKNOWN_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "When default operation is replace, no other operation attribute is allowed.")
				moduleReply.addErrorInfo("bad-element",selectedNodes[0])
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return
			else:
				return

		elif self.defaultOperation == "none":
			if len(selectedNodes) == 0:
				#print "When default operation is none, at least one operation attribute must appear."
				moduleReply = ModuleReply(
				error_type = ModuleReply.APPLICATION,
				error_tag = ModuleReply.MISSING_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "When default operation is none, at least one operation attribute must appear.")
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return
			else:
				# Check the internal nestings, independently of default operation.
				self.checkInternalNestingValidity(selectedNodes)

		elif self.defaultOperation == "merge":
			# Check the internal nestings, independently of default operation.
			valid = self.checkInternalNestingValidity(selectedNodes)


	def checkInternalNestingValidity(self, selectedNodes):

		for elem in selectedNodes:
			deepvalue = elem.getAttributeNS("urn:ietf:params:xml:ns:netconf:base:1.0", "operation")
			tmp = elem
			while (tmp.parentNode != elem.ownerDocument.documentElement):
				if tmp.parentNode in selectedNodes:
					# It means that elem is a child (direct or not) of another SelectedNode
					highvalue = tmp.parentNode.getAttributeNS("urn:ietf:params:xml:ns:netconf:base:1.0", "operation")
					if highvalue != "merge":
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.MISSING_ELEMENT,
						error_severity = ModuleReply.ERROR,
						error_message = "%s can not have %s operation in its subelements." % (highvalue, deepvalue))
						self.operationReply.setError()
						self.operationReply.setNode(moduleReply.getXMLNodeReply())
						return
						#return "%s can not have %s operation in its subelements." % (highvalue, deepvalue)
				else:
					tmp = tmp.parentNode


