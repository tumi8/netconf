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

import amara
from constants import C


class OperationReader:

	instance = None	

	def readOperations(self):
		operationDict = {}
		try:
			self.doc = amara.parse("file:" + C.YENCAP_CONF_HOME + "/operations.xml")

			for elem in self.doc.operations.operation:
				name = str(elem.name)
				mainFileName = str(elem.mainFileName)
				className = str(elem.className)
				
				operationDict[name] = {"mainFileName":mainFileName, "className":className}
				
		except Exception, exp:
			print 'Check configuration file of the server : operations.xml'
			print str(exp)

		return operationDict


	def writeOperations(self):
		f = open(C.YENCAP_CONF_HOME + '/operations.xml', 'w')
		self.doc.xml(f)
		f.close()


	def addOperation(self, name, mainFileName, className):
		for elem in self.doc.operations.operation:
			if elem.name == name:
				# This operation name already exists
				return

		operationNode = self.doc.xml_create_element(u'operation')
		self.doc.operations.xml_append(operationNode)
		nameNode = self.doc.xml_create_element(u'name')
		operationNode.xml_append(nameNode)
		nameNode.xml_append(unicode(name))
		mainFileNameNode = self.doc.xml_create_element(u'mainFileName')
		operationNode.xml_append(mainFileNameNode)
		mainFileNameNode.xml_append(unicode(mainFileName))
		classNameNode = self.doc.xml_create_element(u'className')
		operationNode.xml_append(classNameNode)
		classNameNode.xml_append(unicode(className))

		
	def removeOperation(self, name):
		for elem in self.doc.operations.operation:
			if elem.name == name:
				self.doc.operations.xml_remove_child(elem)
				return


# Singleton : only one instance of the OperationReader
def getInstance():
	if OperationReader.instance == None:
		OperationReader.instance = OperationReader()
	return OperationReader.instance
