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
#   Name : Jerome BOURDELLON                                                  #
#   Email: Jerome.Bourdellon@loria.fr                                         #
#                                                                             #
###############################################################################

from Ft.Xml.Domlette import implementation,PrettyPrint
from Ft.Xml import EMPTY_NAMESPACE
import Grammar 
import os

ASTERISK_HOME="/etc/asterisk/"

class Parser:
	
	def __init__(self, namespace):
		self.namespace = namespace
		

	def parseFile(self, name, doc):
		"""
			Parse an Asterisk configuration file
			@type  name : string
			@param name : Configuration file to use
			@rtype: XML Node
			@return: Corresponding XML node for the file
		"""
		
		f = open(ASTERISK_HOME + name, 'r')
		
		output = Grammar.parse('newgoal', f.read())
		f.close()
		node = doc.createElementNS(self.namespace, 'file')
		node.setAttributeNS(self.namespace, 'name', name)		
		
		tmp = node

		for line in output:
			try:
				if line.has_key('section'):
					section = doc.createElementNS(self.namespace, 'section')
					section.setAttributeNS(self.namespace, 'name', line['section'])
					node.appendChild(section)
					tmp = section
					
				elif line.has_key('attribute'):
					attribute = doc.createElementNS(self.namespace, 'attribute')
					attribute.setAttributeNS(self.namespace, 'name', line['attribute'])
									
					value = doc.createTextNode(str(line['value']))
									
					attribute.appendChild(value)
								
					tmp.appendChild(attribute)
						
				elif line.has_key('comment'):
					pass
					
				elif line.has_key('include'):
					
					includeNode = doc.createElementNS(self.namespace,'include')
					include = doc.createTextNode(line['include'])
					includeNode.appendChild(include)
					tmp.appendChild(includeNode)
					
				elif line.has_key(''):
					
					pass
						
			except:
				raise
				
		return node


	def parse(self):
		"""
			Parse all Asterisk configuration files
		"""
		doc = implementation.createDocument(self.namespace, 'asterisk', None)

		root = doc.documentElement
		
		files = os.listdir(ASTERISK_HOME)
		
		for f in files:
			
			if f.endswith('.conf'):
				
				node = self.parseFile(f,doc)
				
				root.appendChild(node)
		
		
		return doc
	

		
