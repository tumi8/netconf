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
#   Name : Jerome BOURDELLON                                                  #
#   Email: Jerome.Bourdellon@loria.fr                                         #
#                                                                             #
###############################################################################

from Modules.modulereply import ModuleReply 
from Modules.module import Module
from constants import C

from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from Ft.Xml import XPath, InputSource
from Ft.Xml.Xslt import Processor, DomWriter

import util, os

from Parser import Parser
from Generator import Generator


MADYNES_NS='netconf:fr:loria:madynes'
metaFile = "//%s/Modules/meta.xsl"%(C.YENCAP_HOME)


class ASTERISK_Module(Module):

		
	def __init__(self, name, path, namespace, cacheLifetime, arguments): 
		"""
			This method should be overrided by the module.
			@type arguments : dictionary, 
			@param arguments : will be the data specified by the module Linker for creating the module.
		"""			
		Module.__init__(self, name, path, namespace, cacheLifetime)
		self.parser = Parser(self.namespace)

	
	def get(self, configName):
		"""
			Generate the device's XML state data if any of the current module.
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
			** Relates to the netconf get-config operation
		"""		
		get =  self.parser.parse()
		
		if ( get != None):
		
			xmlreply = ModuleReply ( replynode = get.documentElement)
			
		else:
		
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED")
		
		return xmlreply 


	def getConfig(self, configName):
		"""
			Generate the device's XML configuration of the current module.
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
			** Relates to the netconf get-config operation
		"""

		if configName == C.STARTUP:
			get_config =  self.parser.parse()
			if ( get_config != None):
				xmlreply = ModuleReply ( replynode = get_config.documentElement)
			else:
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
				error_severity=ModuleReply.ERROR,
				error_message="Parser from Module %s replied with None." % self.name)	
		else:
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED: Module %s only supports startup configuration." % self.name)
		
		return xmlreply 
	

	def copyConfig(self, sourceName, targetName, sourceNode = None):
		
		if sourceName in [C.CANDIDATE, C.RUNNING] or targetName in [C.CANDIDATE, C.RUNNING]:
			xmlReply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity = ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED: Module %s only supports startup configuration." % self.name)
 			return xmlReply

		if sourceName == C.STARTUP:
			gcr = self.getConfig(sourceName)
			if gcr.isError():
				xmlReply = ModuleReply(
				error_type = ModuleReply.APPLICATION,
				error_tag = ModuleReply.OPERATION_NOT_SUPPORTED,
				error_severity = ModuleReply.ERROR,
				error_message="Module %s error: %s" % (self.name, str(exp)))	
 				return xmlReply			
			else:
				sourceNode = gcr.getXMLNodeReply()

		generator = Generator(sourceNode, self.namespace)
		try:
			generator.generate()
			#generator.replace()
			xmlReply = ModuleReply()
		except Exception, exp:
			xmlReply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_NOT_SUPPORTED,
			error_severity = ModuleReply.ERROR,
			error_message="Module %s error: %s" % (self.name, str(exp)))

		return xmlReply


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
			xmlReply = self.copyConfig(C.CONFIG, target, sourceNode = newXMLDoc.documentElement)
			return xmlReply

		except Exception,exp:
			import traceback
			traceback.print_exc()
			moduleReply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message=str(exp))
			return moduleReply


"""
<asterisk xmlns="urn:loria:madynes:ensuite:yencap:module:ASTERISK:1.0">
    <file name="rtp.conf" xc:operation="delete">
     <section name="general">
      <attribute name="rtpstart">10000</attribute>
      <attribute name="rtpend">20000</attribute>
     </section>
    </file>
</asterisk>
"""
