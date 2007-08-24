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

import os, shutil

from Ft.Xml.XPath import Evaluate
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from Ft.Xml.XPath.Context import Context
from constants import C
from Ft.Xml import EMPTY_NAMESPACE

ASTERISK_MODULE_HOME = '/usr/local/ensuite/yencap/Modules/ASTERISK_Module'
ASTERISK_HOME = '/etc/asterisk/'


class Generator:

	def __init__(self, asteriskRootNode, namespace):

		self.namespace = namespace

		NS = {}
		NS['voip'] = self.namespace
		NS['netconf'] = C.NETCONF_XMLNS
		NS['xc'] = C.NETCONF_XMLNS

		self.ctx = Context(asteriskRootNode, processorNss = NS)
		self.root = asteriskRootNode
		
		
	def generateFile(self, name, fileNode):
		"""
			Create an Asterisk configuration file
			@type  name : string
			@param name : Name of the configuration file to create
			@type  file : XML Node 
			@param file : XML Node for the file
		"""	
		print "generateFile:"
		print name

		f = open('%s%s'%(ASTERISK_HOME,name),'w')
		
		for node in fileNode.childNodes:
			if node.nodeType == node.ELEMENT_NODE:
				name = node.getAttributeNS(EMPTY_NAMESPACE,'name')
				if node.nodeName == 'section':
					f.write('\n['+name+']\n')
					
					for attributeNode in node.childNodes:
						if attributeNode.nodeType == attributeNode.ELEMENT_NODE:
							name = node.getAttributeNS(EMPTY_NAMESPACE,'name')	
							value = attributeNode.childNodes[0].nodeValue
							f.write(name + '=' + value+'\n')
							
				elif node.nodeName == 'attribute':
					value = node.childNodes[0].nodeValue
					f.write(name + '=' + value+'\n')
		
		f.close()
		
		
	def generate(self):
		"""
			Generate all Asterisk configuration files
		"""
		
		nodes = Evaluate('//voip:file',self.root,self.ctx)
		
		for fileNode in nodes:
			# Attribute has no namespace here
			name = fileNode.getAttributeNS(EMPTY_NAMESPACE,'name')
			
			self.generateFile(name,fileNode)
			
	def replace(self):
	
		for f in os.listdir('%s/tmp/'%(ASTERISK_MODULE_HOME)):
			try:
				shutil.copy('%s/tmp/%s'%(ASTERISK_MODULE_HOME,f), ASTERISK_HOME)
			except Exception ,exp:
								
				print str(exp)
				
				
				
				
