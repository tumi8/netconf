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
from CLI_VTY_Connection import VTY_Connection
from ConnectError import ConnectError 
from XMLProtocolTranslator import XML_Constructor, XML_Interpreter

from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from Ft.Xml import XPath, InputSource
from Ft.Xml.Xslt import Processor, DomWriter

import util, os
from constants import C


class CLI_Module(Module):
	"""
		It is the main class of the CLI protocol Module implementation for the NetConf Agent.
		@type deviceConnection: VTY_Connection
		@param deviceConnection:  it provides the specific function to allow the communication with the bgp device.
		@type oldconfig : string
		@param oldconfig: It saves the last configuration before an edit-config request, if specified, in order to restablish this configuration in the device in case of a failiture of this or another node involved in the request.
		@type XMLConstructor: XML Protocol Translator.XML_Constructor 
		@param XMLConstructor:  allows to construct the XML configuration device document from the data obtained by the device.
		@type XMLInterpreter: XML_Protocol_Translator.XML_Interpreter
		@param XMLInterpreter : allows to interprete a XML request from the Agent and generates the equivalent for the Protocol specified by the device. 
	"""


	def __init__(self, name, path, namespace, cacheLifetime, parameters):
		"""
		Create an instance and inicialize the structure needed by it.
		@type  parameters: dictionary
		@param parameters : Should be a dictionary containning the follow keys:
							- host : the host ip-address where the device is locate.
							- port : the port number where the Quagga software router is listen at the Telnet Connection.
							- password and enable\_pass :  The passwords to allow a connection to the device and for set root privileges for configurate it, respectibility.
							- instance : It allows to create more than one instance of the module adding an \textit {instance} attribute with the value specified by it into the root node of the XML BGP Configuration document. If it not specified it will not add anything.
							- allowxpath : It turn on or off the xpath "Looking into the Values" capability as specified by the value. If it is not specified the default value is false.
		"""

		Module.__init__(self, name, path, namespace, cacheLifetime)

		host = parameters["host"]
		port = parameters["port"]
		password = parameters["password"]
		enable_password = parameters["enable_pass"]

		self.deviceConnection = VTY_Connection(host, port, password, enable_password)

		allowxpath = False
		
		if( "allowxpath" in parameters):
			if (parameters["allowxpath"] == "true"):
				allowxpath = True
		
		if( "instance" in parameters):
			instance = parameters["instance"]
		else: 
			instance = None
		
		structXML = NonvalidatingReader.parseUri("file:"+self.PROTOCOL_STRUCTURE_FILE)
		
		self.XMLConstructor = XML_Constructor.XML_Constructor(structXML, self.namespace, instance)
		self.XMLInterpreter = XML_Interpreter.XML_Interpreter(structXML, self.namespace, allowxpath)
		

	def getConfig(self, configName):
		"""
			Generate the BGP device's XML configuration.
			Note that for every request it opens a new connection to the device, this behaivor allows multiple request at the same time. To avoid problems of concurrency, this implementation relies over the lock provided by the device, in this case the Quagga software.
			@rtype: ModuleReply
			@return: the main node of the BGP protocol if it success or the error ocurred.
			** Relates to the netconf get-config operation
		"""
		
		if configName in [C.RUNNING, C.STARTUP]:

			try :
				id = self.deviceConnection.stablishConnection()
				id,config = self.deviceConnection.getConfiguration(id, self.protocol, configName)
				self.deviceConnection.closeConnection(id)
				parserstruct = self.parse("Parser",config)
				confdoc = self.XMLConstructor.construct_XML(parserstruct)
				modulereply = ModuleReply(replynode=confdoc.documentElement)

			except ConnectError , exp :
				modulereply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.RESOURCE_DENIED, 
				error_severity=ModuleReply.ERROR,
				error_tag_app=exp.getError(),
				error_message=str(exp))

			except Exception,exp:
				modulereply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_FAILED, 
				error_severity=ModuleReply.ERROR,
				error_message=str(exp))

		#elif configName == C.STARTUP:
		#	modulereply = ModuleReply(
		#	error_type = ModuleReply.APPLICATION,
		#	error_tag = ModuleReply.OPERATION_FAILED,
		#	error_severity = ModuleReply.ERROR,
		#	error_message = "%s does not support startup yet." % self.name)

		elif configName == C.CANDIDATE:
			modulereply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "%s does not support candidate yet." % self.name)

		else:
			modulereply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "config %s is not known." % configName)

		return modulereply


	def editConfig(self,defaultoperation,testoption,erroroption,target,confignode,targetnode=None):
		"""
			Apply a BGP request from the confignode to the targetnode.
			Note that for every request it opens a new connection to the device, this behaivor allows multiple request at the same time. To avoid problems of concurrency, this implementation relies over the lock provided by the device, in this case the Quagga software.
			@type defaultoperation: MERGE_OPERATION | REPLACE_OPERATION | NONE_OPERATION 
			@param defaultoperation : as specified in NETCONF protocol
			@type testoption : SET | TEST_AND_SET 
			@param testoption : as specified in NETCONF protocol
			@type erroroption : STOP_ON_ERROR | CONTINUE_ON_ERROR | ROLL_BACK_ON_ERROR 
			@param erroroption : as specified in NETCONF protocol
			@type target : RUNNING_TARGET | CANDIDATE_TARGET | STARTUP_TARGET
			@param target : as specified in NETCONF protocol
			@type targetnode : string
			@param targetnode : if the target is RUNNING_TARGET or STARTUP_TARGET it will be ignored otherwise should be the node of the 
								CANDIDATE_TARGET that this module should procees
			@rtype: ModuleReply
			@return: It returns a success or error message.
			** Relates to the netconf edit-config operation
		"""
		if (testoption != C.SET):
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
			error_severity=ModuleReply.ERROR,
			error_message="The %s Interface doesn't support 'test-then-set' option" % self.name)
			return xmlreply 
		
		try:
			id = self.deviceConnection.stablishConnection()
			
			if (target == C.RUNNING):
				id,self.oldconfig = self.deviceConnection.getConfiguration(id, self.protocol, target)
				parserstruct = self.parse("Parser",self.oldconfig)
				confdoc = self.XMLConstructor.construct_XML(parserstruct)
				targetnode = confdoc.documentElement
			else:
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
				error_severity=ModuleReply.ERROR,
				error_message="The %s Interface doesn't support 'edit-config' in candidates" % self.name)
				return xmlreply 
		
			if (erroroption == C.ROLLBACK_ON_ERROR):
				self.oldconfig = None

			commands = self.XMLInterpreter.interpretXMLRequest(defaultoperation,targetnode,confignode)
			error_message="Sending commands to the device:\n"
			errorhappend = False
		
			id = self.deviceConnection.setConfigureState(id,True)
			for i in range(0,len(commands)):
				try:	
					id,value = self.deviceConnection.sendCommand(id,commands[i])
				except ConnectError , exp :
					value = exp.getErrorMessage() + " "
					
				if (value != ""):
					errorhappend = True
					error_message= error_message + "Commands excecuted with error: "+ commands[i]+"\n"
					error_message= error_message + "\t error: "+ value + "\n"
										
					if (erroroption == C.STOP_ON_ERROR or erroroption == C.ROLLBACK_ON_ERROR):
						error_message= error_message + "Commands not executed:\n"
						for j in range(i+1,len(commands)):
							error_message= error_message + "\t" + commands[j] + "\n"
						break
				else:
					error_message= error_message + "Command executed: "+ commands[i]+ "\n"
			
			id = self.deviceConnection.setConfigureState(id,False)
			
			if (errorhappend):
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_FAILED,
				error_severity=ModuleReply.ERROR,
				error_message=error_message)
			else:
				id,value = self.deviceConnection.sendCommand(id, "write file")
				if (value.startswith("Configuration saved")):
					xmlreply = ModuleReply()
				else:
					xmlreply = ModuleReply(
					error_type=ModuleReply.APPLICATION,
					error_tag=ModuleReply.RESOURCE_DENIED, 
					error_severity=ModuleReply.ERROR,
					error_message=value+"\n")

			self.deviceConnection.closeConnection(id)
			
		except XML_Interpreter.XML_Interpreter_Exception, exp:
			xmlreply = exp.getErrorReply()
		except ConnectError , exp :
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.RESOURCE_DENIED,
			error_severity=ModuleReply.ERROR,
			error_tag_app=exp.getError(),
			error_message=str(exp))
		except Exception,exp:
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED,
			error_severity=ModuleReply.ERROR,
			error_message=str(exp))

		return xmlreply
	

	def copyConfig(self, sourceName, targetName, sourceNode = None):
		"""
			Copy the sourceName configuration of the current module to the targetName configuration.
			@type targetName: string
			@param targetName: "running", "startup", "candidate" configuration datastore
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
			** Relates to the netconf copy-config operation
		"""

		if (sourceName in [C.RUNNING, C.STARTUP] and targetName in [C.RUNNING, C.STARTUP]):
			try:
				id = self.deviceConnection.stablishConnection()
				id, text = self.deviceConnection.copyConfiguration(id, sourceName, targetName)
				self.deviceConnection.closeConnection(id)
				
				if text.find("Configuration saved to") != -1:
					xmlreply = ModuleReply()
				else:
					xmlreply = ModuleReply(
					error_type=ModuleReply.APPLICATION,
					error_tag=ModuleReply.RESOURCE_DENIED, 
					error_severity=ModuleReply.ERROR,
					error_message= text+"\n")
				
			except ConnectError , exp :
				import traceback
				traceback.print_exc()
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.RESOURCE_DENIED,
				error_severity=ModuleReply.ERROR,
				error_tag_app=exp.getError(),
				error_message=str(exp))
			except Exception,exp:
				import traceback
				traceback.print_exc()
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_FAILED,
				error_severity=ModuleReply.ERROR,
				error_message=str(exp))

		else:
			xmlreply = Module.copyConfig(self, sourceName, targetName, sourceNode)
		
		return xmlreply


	def rollBack(self):
		"""
			It restablish the last state of the device's configuration.
			Note that the last editConfig should had received erroroption equal
			to ROLL_BACK_ON_ERROR in order to achived this request, otherwise returns in error.
			@rtype: ModuleReply
			@return: It returns a success or error message.
		"""
		try:
			if (self.oldconfig != None):
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
				error_severity=ModuleReply.ERROR,
				error_message="The %s Module doesn't support rollback." % self.name)
				return xmlreply

				# It is necessary to delete the new configuration in order to push the old one,
				# there is no command yet in the 
				# quagga soft to do it, and to delete by hand the quagga soft crash
				id = self.deviceConnection.stablishConnection()
				id = self.deviceConnection.setConfigureState(id,True)
				
				id = self.deviceConnection.sendCommand(id,self.oldconfig)
				xmlreply = ModuleReply()
				id = self.deviceConnection.setConfigureState(id,False)
				self.deviceConnection.closeConnection(id)
			else :
				xmlreply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.ROLL_BACK, 
				error_severity=ModuleReply.ERROR,
				error_message="It is not specified what to rollback")
		except ConnectError , exp :
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.RESOURCE_DENIED, 
			error_severity=ModuleReply.ERROR,
			error_tag_app=exp.getError(),
			error_message=str(exp))
		except Exception , exp :
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED, 
			error_severity=ModuleReply.ERROR,
			error_tag_app=exp.getError(),
			error_message=str(exp))

		return xmlreply
