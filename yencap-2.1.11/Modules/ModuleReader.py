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


class ModuleReader:

	instance = None	

	def readModules(self):
		moduleDescrList = []
		try:
			self.doc = amara.parse("file:" + C.YENCAP_CONF_HOME + "/modules.xml")

			for elem in self.doc.modules.module:
				name = str(elem.name)
				xpath = str(elem.xpath)
				namespace = str(elem.namespace)
				pref = str(elem.namespace.pref)
				cachelifetime = int(str(elem.cachelifetime))
				dictionnary={}
				try:
					for param in elem.parameters.parameter:
						dictionnary[param.name] = str(param.value)
				except Exception, exp:
					pass

				moduleDescr = {"name":name, "xpath":xpath, "namespace":namespace, "pref":pref, "cachelifetime":cachelifetime, "dictionnary":dictionnary}
				moduleDescrList.append(moduleDescr)

		except Exception, exp:
			print 'Check configuration file of the server : modules.xml'
			print str(exp)

		return moduleDescrList


	def writeModules(self):
		f = open(C.YENCAP_CONF_HOME + '/modules.xml', 'w')
		self.doc.xml(f)
		f.close()


	def addModule(self, name, xpath, namespace, pref, cachelifetime, dictionnary):
		for elem in self.doc.modules.module:
			if elem.name == name:
				# This module name already exists
				return

		moduleNode = self.doc.xml_create_element(u'module')
		self.doc.modules.xml_append(moduleNode)
		nameNode = self.doc.xml_create_element(u'name')
		moduleNode.xml_append(nameNode)
		nameNode.xml_append(unicode(name))
		xpathNode = self.doc.xml_create_element(u'xpath')
		moduleNode.xml_append(xpathNode)
		xpathNode.xml_append(unicode(xpath))
		namespaceNode = self.doc.xml_create_element(u'namespace', attributes={u'pref': unicode(pref)})
		moduleNode.xml_append(namespaceNode)
		namespaceNode.xml_append(unicode(namespace))
		cachelifetimeNode = self.doc.xml_create_element(u'cachelifetime')
		moduleNode.xml_append(cachelifetimeNode)
		cachelifetimeNode.xml_append(unicode(cachelifetime))
		parametersNode = self.doc.xml_create_element(u'parameters')
		moduleNode.xml_append(parametersNode)
		for key in dictionnary.keys():
			parameterNode = self.doc.xml_create_element(u'parameter', attributes={u'name': unicode(key), u'value': unicode(dictionnary[key])})
			moduleNode.xml_append(parameterNode)

		
	def removeModule(self, name):
		for elem in self.doc.modules.module:
			if elem.name == name:
				self.doc.modules.xml_remove_child(elem)
				return


# Singleton : only one instance of the ModuleReader
def getInstance():
	if ModuleReader.instance == None:
		ModuleReader.instance = ModuleReader()
	return ModuleReader.instance

