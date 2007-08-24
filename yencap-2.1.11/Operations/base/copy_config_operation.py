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
from Operations.base.get_config_operation import Get_config_operation
from constants import C
from Ft.Xml import XPath
from xml.dom import Node
from Modules.modulereply import ModuleReply
from Modules import DatastoreManager
from Modules import ModuleManager
from rbac import sessionManager
from Ft.Xml.Domlette import PrettyPrint, NonvalidatingReader, implementation
from Ft.Xml import EMPTY_NAMESPACE
from ftplib import FTP
import cStringIO, string, util
from Ft.Xml.XPath import Evaluate
from Ft.Xml.XPath.Context import Context
from rbac import rbacManager


class Copy_config_operation(Operation):
	
	def __init__(self): 
		"""
			Constructor of a Copy_config_operation command.
		"""
		self.datastoreManager = DatastoreManager.getInstance()
		self.moduleManager = ModuleManager.getInstance()
		self.rbacManager = rbacManager.getInstance()

		# "running", "startup", "candidate", "url"
		self.source = None
		# "running", "startup", "candidate", "url"
		self.target = None
		# source url text value (e.g.: ftp://madynes.loria.fr/config.txt)
		self.urlSource = None
		# target url text value (e.g.: ftp://madynes.loria.fr/config.txt)
		self.urlTarget = None
		
		self.sourceInbound = None

	def execute(self):
		"""
			Execute the copy-config operation.
		"""
		
		if self.operationReply.isError():
			return self.operationReply

		if self.target == self.source:
			if (self.target in [C.CANDIDATE, C.STARTUP, C.RUNNING] or (self.target == C.URL and self.urlSource == self.urlTarget)):
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.INVALID_VALUE,
				error_severity = ModuleReply.ERROR,
				error_message = "Source and target are equals.")
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return self.operationReply
		
		sessionId = sessionManager.getInstance().getLockOwnerSessionId(self.target)

		if sessionId >= 0 and sessionId != self.session.getSessionId():
			# Somebody else locked the target.
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.LOCK_DENIED,
			error_severity = ModuleReply.ERROR,
			error_message = "Access to the requested lock is denied because the lock is currently held by another entity")
			moduleReply.addErrorInfo("session-id",sessionId)
			self.operationReply.setError()
			self.operationReply.setNode(moduleReply.getXMLNodeReply())
			return self.operationReply
		
		
		# Check access here
		if self.rbacManager.isActive():
			operation_allowed = self.rbacManager.checkAccessCopyConfig(self.session)

			if (not operation_allowed):
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.ACCESS_DENIED,
				error_severity = ModuleReply.ERROR,
				error_message = "Access denied.")
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return self.operationReply


		sourceDoc = None

		if self.source == C.URL:
			sourceUri = self.urlSource
			try:
				# get the DOM tree of the candidate configuration
				sourceDoc = NonvalidatingReader.parseUri(sourceUri).documentElement
			except Exception, exp:
				moduleReply = ModuleReply(
				error_type = ModuleReply.APPLICATION,
				error_tag = ModuleReply.DATA_MISSING,
				error_severity = ModuleReply.ERROR,
				error_message = self.source + " is not well-formed.")
				self.operationReply.setError()
				self.operationReply.setNode(moduleReply.getXMLNodeReply())
				return self.operationReply

		elif self.source == C.CONFIG:
			sourceDoc = self.sourceInbound


		
		if self.target in [C.RUNNING, C.CANDIDATE, C.STARTUP]:
			if self.target == C.CANDIDATE:
				self.datastoreManager.initializeAll(self.target)

			for module in self.moduleManager.getModules():

				sourceNode = None

				if sourceDoc != None:
					currentPath = module.path
					

					# Finding the nodes (related to the current module) in the received configuration
					try:
						ctx = Context(sourceDoc, processorNss = self.moduleManager.prefixes)
						nodeList = Evaluate(currentPath, ctx)
						
					
					except Exception, exp:
						moduleReply = ModuleReply(
						error_type = ModuleReply.APPLICATION,
						error_tag = ModuleReply.UNKNOWN_ELEMENT,
						error_severity = ModuleReply.ERROR,
						error_message = str(exp))
						self.operationReply.setError()
						self.operationReply.setNode(moduleReply.getXMLNodeReply())
						return self.operationReply
				
					# Distributing the nodes to the current module:
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
						print "nodeList"
						print nodeList[0]
						sourceNode = nodeList[0]

				moduleReply = module.copyConfig(self.source, self.target, sourceNode = sourceNode)
				
				# Update the cache according to the new config.
				module.resetConfigTime(self.target)

				if moduleReply.isError():
					self.operationReply.setError()

				self.operationReply.setNode(moduleReply.getXMLNodeReply())

			return self.operationReply


		elif self.target == C.URL:
			# WARNING : THIS HAS NEVER BEEN TESTED
			buf = cStringIO.StringIO()
			PrettyPrint(sourceDoc, stream=buf, encoding='UTF-8')
			# connect to host, default port
			ftp = FTP(self.urlTarget)
			# user anonymous, passwd anonymous@
			ftp.login()
			ftp.storbinary('STOR '+self.urlTarget, buf.read)
			buf.close()
			ftp.quit()



	def setParameters(self, operation, NSS = None):
		"""
		Set the source and target parameters

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""
		
		self.prefixes = NSS

		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:

				if child.tagName == C.SOURCE:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName in [C.CANDIDATE, C.STARTUP, C.RUNNING, C.URL]:
								self.source = node.tagName
								if node.tagName == C.URL:
									self.sourceTarget = string.strip(str(node.childNodes[0].nodeValue))
							elif node.tagName == "config":
								self.source = node.tagName
								for cn in node.childNodes:
									if cn.nodeType==Node.ELEMENT_NODE:
										if cn.localName == "netconf" and cn.namespaceURI == C.YENCAP_XMLNS:
											# We build a new document containing the "filter" to compare more easily
											self.sourceInbound = implementation.createDocument(C.YENCAP_XMLNS, "netconf", None)
											clone = self.sourceInbound.importNode(cn, True)
											# self.config is a document which documentElement is "netconf"
											self.sourceInbound.replaceChild(clone, self.sourceInbound.documentElement)
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

				elif child.tagName == C.TARGET:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName in [C.CANDIDATE, C.STARTUP, C.RUNNING, C.URL]:
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

