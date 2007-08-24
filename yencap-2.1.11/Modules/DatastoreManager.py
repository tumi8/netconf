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


from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from Ft.Xml.XPath.Context import Context
from Ft.Xml.XPath import Evaluate
from constants import C
import util, os
import ModuleManager
import LogManager
from Modules.modulereply import ModuleReply


class DatastoreManager:
	"""
		DatastoreManager is responsible for the management of the different datastores:
			- running (see "get" operation)
			- runningConfig (see "get-config" operation)
			- candidate
			- startup
		DatastoreManager keeps a copy of each datastore, but:
			- running, runningConfig have sometimes to be updated because 
				- config data may change, since YencaP is not working in isolation
				- statistic data may change by nature
			- candidate and startup should be stored on disk and removed from memory when not used.
		DatastoreManager knows how to:
			- build an XML template from the XPath data of each module,
			- fill this template by querring the modules,
			- update the datastores (running, runningConfig) whenever it is out-of-date. 
	"""

	# This is to simulate singleton.
	# From outside of this class, the only way to have the 
	# unique instance of this class is to use "dm = DatastoreManager.getInstance()"
	instance = None


	def __init__(self):
		"""
			DatastoreManager constructor.
		"""

		# Let's get the instance of ModuleManager, since we will use it a lot in this class
		self.moduleManager = ModuleManager.getInstance()
		self.prefixes = self.moduleManager.getPrefixes()

		# Declare running (see get operation), runningConfig (see get-config operation),
		# candidate and startup configuration datastores. These are XML documents, not nodes.
		# Self.configurations dictionnary contains all the configurations.

		self.configurations = {}
		self.configurations[C.STARTUP] = {}
		self.configurations[C.CANDIDATE] = {}
		self.configurations[C.RUNNING] = {}

		self.configurations[C.STARTUP]["state"] = None
		self.configurations[C.STARTUP]["config"] = None
		self.configurations[C.CANDIDATE]["state"] = None
		self.configurations[C.CANDIDATE]["config"] = None
		self.configurations[C.RUNNING]["state"] = None
		self.configurations[C.RUNNING]["config"] = None

		self.initializeAll(C.RUNNING)
		self.initializeAll(C.STARTUP)


	def initializeAll(self, configName):
		"""
			Build the XML datastore named configName.
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
		"""

		# Build the tempates:
		self.initConfig(configName, "config")
		self.initConfig(configName, "state")

		# Fill the templates:
		self.updateConfig(configName, "config")
		self.updateConfig(configName, "state")


	def initConfig(self, configName, configType):
		"""
			Build the XML datastore template.
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
			@type configType : string
			@param configType : the type of the configuration datastore ('state' or 'config') 
		"""

		doc = implementation.createDocument(C.YENCAP_XMLNS, "netconf", None)

		self.configurations[configName][configType] = doc
		
		for module in self.moduleManager.getModules():

			self.initConfigModule(module, configName, configType)



	def initConfigModule(self, module, configName, configType):
		"""
			Build the XML path from the root to the module.
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
			@type configType : string
			@param configType : the type of the configuration datastore ('state' or 'config') 
		"""
		
		configuration = self.configurations[configName][configType]
		
		self.prefixes = self.moduleManager.getPrefixes()

		# Check if module node exists
		ctx = Context(configuration, processorNss = self.prefixes)
		selectednodes = Evaluate(module.path, ctx)
		if len(selectednodes)==1:
			return

		path = module.path
		tab = path.split('/')

		j = len(tab)-1
		prefix, name = tab[j].split(':')

		child = configuration.createElementNS(self.prefixes[prefix], name)

		while j>0:
			xpathRequest = ""
			for i in range(1,j):
				xpathRequest = xpathRequest+"/"+tab[i]

			ctx = Context(configuration, processorNss = self.prefixes)
			selectednodes = Evaluate(xpathRequest, ctx)
				
			if len(selectednodes)==0:
				# The node does not exist: create it now:
				prefix, name = tab[j-1].split(':')
				new = configuration.createElementNS(self.prefixes[prefix], name)
				new.appendChild(child)
				child = new
				j = j-1
			else:
				selectednodes[0].appendChild(child)
				j=0



	def updateConfig(self, configName, configType):
		"""
			Replace the module root nodes when it is unfresh in self.runningConfig.
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
			@type configType : string
			@param configType : the type of the configuration datastore ('state' or 'config') 
		"""
		
		configuration = self.configurations[configName][configType]

		self.prefixes = self.moduleManager.getPrefixes()

		for module in self.moduleManager.getModules():
			
			if (not module.isConfigFresh(configName, configType)):
				try:
				
					ctx = Context(configuration, processorNss = self.prefixes)
					nodes = Evaluate(module.path, ctx)
	
					if len(nodes) == 1:
	
						oldNode = nodes[0]
						parentNode = oldNode.parentNode
						if configType == "config":
							moduleReply = module.getConfig(configName)
						elif configType == "state":
							moduleReply = module.get(configName)

						if moduleReply.isError():
							msg = "Error: DatastoreManager:updateConfig(): Module %s: %s" % (module.name, moduleReply.error_message)
							LogManager.getInstance().logError(msg)
						else:
							module.updateConfigTime(configName, configType)
							newNode = moduleReply.getXMLNodeReply()
							parentNode.replaceChild(newNode, oldNode)

				except Exception,exp:
					LogManager.getInstance().logError("Module %s: %s" % (module.name, str(exp)))
					#LogManager.getInstance().logError("Unloading module %s: %s" % (module.name, str(exp)))
					#self.moduleManager.unloadModule(module.name)


	def getConfig(self, configName, configType):
		"""
			Returns the current datastore configName of type configType.
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
			@type configType : string
			@param configType : the type of the configuration datastore ('state' or 'config')
			@rtype: Document
			@return: the XML document containing the XML data.
		"""

		self.updateConfig(configName, configType)
		return self.configurations[configName][configType]


	def removeConfig(self, configName, configType):
		"""
			Remove the current datastore configName of type configType.
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
			@type configType : string
			@param configType : the type of the configuration datastore ('state' or 'config')
			@rtype: ModuleReply
			@return: An ok reply.
		"""

		for module in self.moduleManager.getModules():
			moduleReply = module.deleteConfig(configName)
			if moduleReply.isError():
				return moduleReply

		self.configurations[configName][configType] = None
		
		moduleReply = ModuleReply()
		return moduleReply


	def removeModule(self, module, configName, configType):
		"""
			Remove the data of a specific module from the XML datastore.
			@type module : Module
			@param module : the module to remove
			@type configName : string
			@param configName : the name of the configuration datastore ('running', 'candidate' or 'startup') 
			@type configType : string
			@param configType : the type of the configuration datastore ('state' or 'config')
		"""

		configuration = self.configurations[configName][configType]

		self.prefixes = self.moduleManager.getPrefixes()

		ctx = Context(configuration, processorNss = self.prefixes)
		selectednodes = Evaluate(module.path, ctx)

		for selectednode in selectednodes:
			selectednode.parentNode.removeChild(selectednode)



def getInstance():
	"""
	DatastoreManager is a Singleton
	Use getInstance method to get the unique DatastoreManager
	
	@rtype: DatastoreManager
	@return: The DatastoreManager instance itself (which is unique).
	"""
	
	if DatastoreManager.instance == None:
		DatastoreManager.instance = DatastoreManager()
	return DatastoreManager.instance
