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
#   Name : Humberto ABDELNUR and Vincent CRIDLIG                              #
#   Email: Humberto.Abdelnur@loria.fr, Vincent.Cridlig@loria.fr               #
#                                                                             #
###############################################################################

import time
from modulereply import ModuleReply
from constants import C
from Ft.Xml.Domlette import PrettyPrint


class Module:
	"""
		This class should be inherance by each module that want to be add to the Agent.
		Its specified the main functions needed for the Agent to follow the NETCONF protocol.
	"""

	
	def __init__(self, name, path, namespace, cacheLifetime): 
		"""
			This method should be overrided by the module.
			@type name : string, 
			@param name : module name
			@type path : string, 
			@param path : module registration path (XPath) in the global datastore.
			@type namespace : string, 
			@param namespace : module namespace
			@type cacheLifetime : int, 
			@param cacheLifetime : period of validity for the module data that is stored in the cache.
		"""

		self.name = name
		self.path = path
		self.namespace = namespace
		self.cacheLifetime = cacheLifetime
		self.cacheFile = "%s/caches/%s.xml " % (C.YENCAP_CONF_HOME, name)

		# configTime is the time of the last call to get on the current module
		self.configTime = {}

		self.configTime[C.RUNNING] = {}
		self.configTime[C.CANDIDATE] = {}
		self.configTime[C.STARTUP] = {}

		self.configTime[C.RUNNING]["state"] = None
		self.configTime[C.RUNNING]["config"] = None
		self.configTime[C.CANDIDATE]["state"] = None
		self.configTime[C.CANDIDATE]["config"] = None
		self.configTime[C.STARTUP]["state"] = None
		self.configTime[C.STARTUP]["config"] = None


	def close(self): 
		"""
			Should be overrided if the module needs to close some resources, i.e. sockets.
		"""
		pass	


	def resetConfigTime(self, configName):

		self.configTime[configName]["state"] = None
		self.configTime[configName]["config"] = None


	def updateConfigTime(self, configName, configType):

		self.configTime[configName][configType] = time.time()


	def isConfigFresh(self, configName, configType):

		configTime = self.configTime[configName][configType]

		if configTime == None:
			return False
		else:
			return (time.time() - configTime < self.cacheLifetime )


	def deleteConfig(self, targetName):
		xmlreply = ModuleReply(
		error_type=ModuleReply.APPLICATION,
		error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
		error_severity=ModuleReply.ERROR,
		error_message="delete-config OPERATION-NOT-SUPPORTED by module %s" % self.name)
		return xmlreply


	def get(self, configName):
		"""
			Generate the device's XML state data if any of the current module.
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
			** Relates to the netconf get-config operation
		"""

		# By default, their is no statistics data (see route_Module).
		# So just return exactly the same response as getConfig()
		# Every particular module may want to override it in its code.
		return self.getConfig(configName)


	def getConfig(self, configName):
		"""
			Generate the device's XML configuration of the current module.
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
			** Relates to the netconf get-config operation
		"""
		
		xmlreply = ModuleReply(
		error_type=ModuleReply.APPLICATION,
		error_tag=ModuleReply.OPERATION_NOT_SUPPORTED,
		error_severity=ModuleReply.ERROR,
		error_message="get-config OPERATION-NOT-SUPPORTED by module %s" % self.name)
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

		# This is a default implementation:
		
		if (sourceName in [C.RUNNING, C.CANDIDATE, C.STARTUP]):
			moduleReply = self.getConfig(sourceName)
			if moduleReply.isError():
				return moduleReply
			else:
				return self.editConfig(C.REPLACE, C.SET, C.STOP_ON_ERROR, targetName, moduleReply.getXMLNodeReply())
		elif sourceName == C.CONFIG:
			return self.editConfig(C.REPLACE, C.SET, C.STOP_ON_ERROR, targetName, sourceNode)
			

	def editConfig(self, defaultoperation, testoption, erroroption, target, confignode, targetnode=None):
		"""
			Apply the request specified in confignode to the targetnode.
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
			@return: It should return a success or error message.
			** Relates to the netconf edit-config operation
		"""
		
		xmlreply = ModuleReply(
		error_type=ModuleReply.APPLICATION,
		error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
		error_severity=ModuleReply.ERROR,
		error_message="edit-config OPERATION-NOT-SUPPORTED by module %s" % self.name)
		return xmlreply 
	

	def rollBack(self):
		"""
			It restablishes the last state of the device's configuration.
			Note that the last editConfig should had received erroroption equal
			to ROLL_BACK_ON_ERROR in order to achived this request, otherwise returns in error.
			@rtype: ModuleReply
			@return: It should return the device's configuration or an error 
		"""

		xmlreply = ModuleReply(
		error_type=ModuleReply.APPLICATION,
		error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
		error_severity=ModuleReply.ERROR,
		error_message="Rollback OPERATION-NOT-SUPPORTED by module %s" % self.name)
		return xmlreply 


	def validate(self, targetname, moduleNode = None):
		"""
			Validates the configuration of the targetnode.
			@rtype: ModuleReply
			@return: It should return an error if the configuration does not validate 
		"""

		xmlreply = ModuleReply(
		error_type=ModuleReply.APPLICATION,
		error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
		error_severity=ModuleReply.ERROR,
		error_message="validate OPERATION-NOT-SUPPORTED by module %s" % self.name)
		return xmlreply

