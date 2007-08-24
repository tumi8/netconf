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

from xml.dom import Node
from constants import C
import string
from Operations.operationReply import OperationReply

class Operation:
	
	def __init__(self): 
		"""
			Initialize the current command
		"""
		pass

	def execute(self):
		"""
			Execute the current command
		"""
		pass

	
	def setOperationReply(self):
		"""
			Instantiates the OperationReply object that will be returned to the rpc layer.
		"""
		self.operationReply = OperationReply()


	def setSession(self, session):
		"""
			Setup the session for the current command.
		"""
		self.session = session


	def setParameters(self, operation):
		"""
			Parse the command node to setup the command parameters.
		"""
		pass

	"""
	def namespacify(self, xpath):
		result = ""
		tab = xpath.split('/')
		for i in range(1,len(tab)):
			result = result + "/*[local-name()='" + tab[i] + "']"
		return result
	"""
