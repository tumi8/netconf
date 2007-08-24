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
from Operations import operation


class OperationFactory:
	"""
		OperationFactory is responsible for:
			- loading (instantiating) dynamically the operations,
	"""

	instance = None


	def createOperation(self, name, mainFileName, className):
		
		newOperation = None

		# Load operation name
		#name = name + "_Operation"
			
		try:
			mod = self.importName("Operations." + mainFileName, className)
			newOperation = mod()
			
		except Exception,exp:
			import traceback
			traceback.print_exc()

			LogManager.getInstance().logError("operation %s couldn't be loaded. Error:%s" % (className, str(exp)))

		return newOperation


	def importName(self, operationname, name):
		"""
			Import a named object from a operation in the context of this function,
			which means you should use fully qualified operation paths.

			operationname is the Python module path.
			name is the class name inside the module.
			Return None on failure.
		"""

		try:
			operation = __import__(operationname, globals(), locals(), [name])
		except ImportError:
			return None

   		return vars(operation)[name]



def getInstance():
	"""
	OperationFactory is a Singleton
	Use getInstance method to get the unique OperationFactory
	
	@rtype: OperationFactory
	@return: The OperationFactory instance itself (which is unique).
	"""
	
	if OperationFactory.instance == None:
		OperationFactory.instance = OperationFactory()
	return OperationFactory.instance

