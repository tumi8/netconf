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

from constants import C
import ModuleReader
import ModuleFactory
import DatastoreManager


class ModuleManager:
	"""
		ModuleManager is responsible for:
			- parsing the modules.xml file,
			- loading (instantiating) dynamically the modules,
			- storing the liste of modules
	"""

	instance = None

	def __init__(self):
		# Instantiate the module list
		self.modules = []
		
		# Init the namespace prefix list
		self.prefixes = {"ycp":C.YENCAP_XMLNS}

		# Load the modules from the modules.xml file
		self.loadModules()
		

	def loadModules(self):

		# This variable seems to be used nowhere. SHOULD BE REMOVED...
		nbp = 40

		# Parse modules conf file 
		moduleDescrList = ModuleReader.getInstance().readModules()

		# Create modules

		for moduleDescr in moduleDescrList:
			# Set the namespace prefix list
			self.prefixes[moduleDescr["pref"]] = moduleDescr["namespace"]
			# Create the new module from its descriptor
			newModule = ModuleFactory.getInstance().createModule(moduleDescr)
			if newModule == None:
				print moduleDescr
			else:
				# Add the module to the module list
				self.modules.append(newModule)



	def unloadModules(self):
		# Unload the module list
		self.modules = []
		self.prefixes = {"ycp":C.YENCAP_XMLNS}


	def loadModule(self, name):

		for module in self.modules:
			if module.name == name:
				# Should return an error, saying that this module is already loaded.
				return 2

		# Parse modules conf file 
		moduleDescrList = ModuleReader.getInstance().readModules()

		# Create modules
		for moduleDescr in moduleDescrList:
			if moduleDescr["name"] == name:
				# Set the namespace prefix list
				self.prefixes[moduleDescr["pref"]] = moduleDescr["namespace"]
				# Create the new module from its descriptor
				newModule = ModuleFactory.getInstance().createModule(moduleDescr)
				if newModule == None:
					print moduleDescr
					return 0
				else:
					# Add the module to the module list
					self.modules.append(newModule)
					dm = DatastoreManager.getInstance()

					# Build the parent XML nodes from the root up to the module node.
					dm.initConfigModule(newModule, C.RUNNING, "config")
					dm.initConfigModule(newModule, C.RUNNING, "state")
					dm.initConfigModule(newModule, C.STARTUP, "config")
					dm.initConfigModule(newModule, C.STARTUP, "state")

					# Query the module to update the config.
					dm.updateConfig(C.RUNNING, "config")
					dm.updateConfig(C.RUNNING, "state")
					dm.updateConfig(C.STARTUP, "config")
					dm.updateConfig(C.STARTUP, "state")

					# Success
					return 1
		
		return 3


	def unloadModule(self, name):
		for module in self.modules:
			if module.name == name:
				self.modules.remove(module)
				dm = DatastoreManager.getInstance()
				dm.removeModule(module, C.RUNNING, "config")
				dm.removeModule(module, C.RUNNING, "state")
				dm.removeModule(module, C.STARTUP, "config")
				dm.removeModule(module, C.STARTUP, "state")
				# Success
				return 1

		return 0


	def reloadModules(self):
		self.unloadModules()
		self.loadModules()


	def reloadModule(self, name):
		self.unloadModule(name)
		self.loadModule(name)


	def getModules(self):
		return self.modules


	def getModuleFromName(self, name):
		for module in self.modules:
			if (module.name == name):
				return module
		# Not found:
		return None

	def getPrefixes(self):
		return self.prefixes



def getInstance():
	"""
	ModuleManager is a Singleton
	Use getInstance method to get the unique ModuleManager
	
	@rtype: ModuleManager
	@return: The ModuleManager instance itself (which is unique).
	"""
	
	if ModuleManager.instance == None:
		ModuleManager.instance = ModuleManager()
	return ModuleManager.instance
