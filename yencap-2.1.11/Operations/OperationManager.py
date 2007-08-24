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
import OperationReader
import OperationFactory
import LogManager


class OperationManager:
	"""
		OperationManager is responsible for:
			- parsing the operations.xml file,
			- loading (instantiating) dynamically the operations,
			- storing the liste of operations
	"""

	instance = None

	def __init__(self):
		# Instantiate the operation list
		self.operations = {}

		# Load the operations from the operations.xml file
		self.loadOperations()
	
	
	def loadOperations(self):

		# Parse modules conf file 
		self.operations = OperationReader.getInstance().readOperations()


	def getOperation(self, name):
		if self.operations.has_key(name):
			mainFileName = self.operations[name]["mainFileName"]
			className = self.operations[name]["className"]
			operation = OperationFactory.getInstance().createOperation(name, mainFileName, className)
		else:
			LogManager.getInstance().logError("Operation %s is not known." % (name))
			operation = None
		
		return operation



def getInstance():
	"""
	OperationManager is a Singleton
	Use getInstance method to get the unique OperationManager
	
	@rtype: OperationManager
	@return: The OperationManager instance itself (which is unique).
	"""
	
	if OperationManager.instance == None:
		OperationManager.instance = OperationManager()
	return OperationManager.instance
