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


from Modules.module import Module
from Modules.modulereply import ModuleReply

from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from Ft.Xml import XPath, InputSource
from Ft.Xml.Xslt import Processor, DomWriter

import util, os
from constants import C

#metaFile = "Modules/meta.xsl"
metaFile = "//%s/Modules/meta.xsl"%(C.YENCAP_HOME)

class EasyModule(Module):
	"""
		This class can be inherited by each module that want to be added to the Agent.
		Its specified the main functions needed for the Agent to follow the NETCONF protocol.
		EasyModule inherits from Module and implements edit-config.
		A module inheriting EasyModule must implement copy-config and get-config
		If your configuration data is an XML file, nothing has to be implemented: just
		give the name of the file in self.dataFile (See RBACModule for an example.)
	"""

	def __init__(self, name, path, namespace, cacheLifetime):
		Module.__init__(self, name, path, namespace, cacheLifetime)
		self.files = {}
		for source in [C.STARTUP, C.RUNNING, C.CANDIDATE]:
			self.files[source] = "%s/%s-%s.xml" % (C.YENCAP_CONF_HOME, self.name, source)
		

	# This method must return the root node
	# corresponding to the current module
	def getConfig(self, configName):		

		if self.files.has_key(configName):
			doc = NonvalidatingReader.parseUri("file:" + self.files[configName])
			moduleReply = ModuleReply(replynode=doc.documentElement)
		else:
			moduleReply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message="Unknown source: " + configName)
			
		return moduleReply


	# This method must return the root node
	# corresponding to the current module
	def get(self, configName):

		if self.files.has_key(configName):
			doc = NonvalidatingReader.parseUri("file:" + self.files[configName])
			moduleReply = ModuleReply(replynode=doc.documentElement)
		else:
			moduleReply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message="Unknown source: " + configName)
			
		return moduleReply

	
	# This method must return the root node
	# corresponding to the current module
	def copyConfig(self, sourceName, targetName, sourceNode = None):

		if (targetName in [C.RUNNING, C.CANDIDATE, C.STARTUP]):
			if sourceName in [C.RUNNING, C.CANDIDATE, C.STARTUP]:
				moduleReply = self.getConfig(sourceName)
				if moduleReply.isError():
					return moduleReply
				else:
					util.printNodeToFile(moduleReply.getXMLNodeReply(), self.files[targetName])
					return ModuleReply()
			elif sourceName in [C.CONFIG, C.URL]:
				util.printNodeToFile(sourceNode, self.files[targetName])
				return ModuleReply()

			moduleReply = ModuleReply()
			return moduleReply


	def deleteConfig(self, targetName):
		if (targetName in [C.CANDIDATE, C.STARTUP]):
			try:
				os.system("rm %s" % (self.files[targetName]))
				moduleReply = ModuleReply()
			except Exception,exp:
				moduleReply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_FAILED,
				error_severity=ModuleReply.ERROR,
				error_message=str(exp))

		return moduleReply
			

	def editConfig(self, defaultoperation, testoption, erroroption, target, confignode, targetnode=None):
		"""
		Apply a edit-config request from the confignode to the targetnode.
		@type defaultoperation: MERGE_OPERATION | REPLACE_OPERATION | NONE_OPERATION 
		@param defaultoperation : as specified in NETCONF protocol
		@type testoption : SET | TEST_AND_SET 
		@param testoption : as specified in NETCONF protocol
		@type erroroption : STOP_ON_ERROR | IGNORE_ERROR | ROLL_BACK_ON_ERROR 
		@param erroroption : as specified in NETCONF protocol
		@type target : RUNNING_TARGET | CANDIDATE_TARGET | STARTUP_TARGET
		@param target : as specified in NETCONF protocol
		@type targetnode : string
		@param targetnode : if the target is RUNNING_TARGET or STARTUP_TARGET it will be ignored otherwise should be the node of the CANDIDATE_TARGET that this module should procees
		@rtype: ModuleReply
		@return: It returns a success or error message.
		** Relates to the netconf edit-config operation
		"""

		try:
			# Generate a stylesheet equivalent to the edit-config
			df = InputSource.DefaultFactory
			editXMLRequest = df.fromString(util.convertNodeToString(confignode), 'urn:dummy')
			stylesheet = df.fromUri("file:"+metaFile, 'urn:sty')
			p = Processor.Processor()
			p.appendStylesheet(stylesheet)
			wr = DomWriter.DomWriter()
			p.run(editXMLRequest, writer=wr)
			generatedStyleSheet = wr.getResult()

			# Apply the generated stylesheet to the source document
			inputStyleSheet = df.fromString(util.convertNodeToString(generatedStyleSheet), 'urn:sty')
			oldXMLDocument = self.getConfig(target).getXMLNodeReply()
			inputDocument = df.fromString(util.convertNodeToString(oldXMLDocument), 'urn:dummy')
			p = Processor.Processor()
			p.appendStylesheet(inputStyleSheet)
			wr = DomWriter.DomWriter()
			p.run(inputDocument, writer=wr)
			newXMLDoc = wr.getResult()
			
			# Copy the new document over the old one
			xmlReply = self.copyConfig("config", target, sourceNode = newXMLDoc)
			return xmlReply

		except Exception,exp:
			print str(exp)
			moduleReply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message=str(exp))
			return moduleReply


