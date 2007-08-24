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

import LogManager
from Modules import module


class ModuleFactory:
	"""
		ModuleFactory is responsible for:
			- loading (instantiating) dynamically the modules,
	"""

	instance = None


	def createModule(self, moduleDescr):
		
			logm = LogManager.getInstance()
			newModule = None

			# Load module name
			name = moduleDescr["name"] + "_Module"
			
			try:
				logm.logInfo("Now trying to load module %s..." % (name))

				mod = self.importName("Modules." + name +"."+name, name)

				newModule = mod(moduleDescr["name"], moduleDescr["xpath"], moduleDescr["namespace"], moduleDescr["cachelifetime"], moduleDescr["dictionnary"])
				
				# Pretty print module loading...OK
				logm.logInfo("module %s has been loaded successfully." % (name))

			except Exception,exp:
				import traceback
				traceback.print_exc()
				logm.logError("module %s couldn't be loaded: %s" % (name, str(exp)))

			return newModule


	def importName(self, modulename, name):
		""" Import a named object from a module in the context of this function,
			which means you should use fully qualified module paths.
			
			Return None on failure.
		"""
		module = __import__(modulename, globals(), locals(), [name])
		return vars(module)[name]



def getInstance():
	"""
	ModuleFactory is a Singleton
	Use getInstance method to get the unique ModuleFactory
	
	@rtype: ModuleFactory
	@return: The ModuleFactory instance itself (which is unique).
	"""
	
	if ModuleFactory.instance == None:
		ModuleFactory.instance = ModuleFactory()
	return ModuleFactory.instance
