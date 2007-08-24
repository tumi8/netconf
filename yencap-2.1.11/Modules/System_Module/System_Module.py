###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Vincent CRIDLIG                                         #
#                                                                             #
# This program is free software; you can redistribute it and/or               #
# modify it under the terms of the GNU General Public License                 #
# as published by the Free Software Foundation; either version 2              #
# of the License, or (at your option) any later version.                      #
#                                                                             #
# This program is distributed in the hope that it will be useful,             #
# but WITHOUT ANY WARRANTY; without even the implied warranty of              #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               #
# GNU General Public License for more details.                                #
#                                                                             #
# You should have received a copy of the GNU General Public License           #
# along with this program; if not, write to the Free Software                 #
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. #
#                                                                             #
# Author Info:                                                                #
#   Name : Vincent CRIDLIG                                                    #
#   Email: Vincent.Cridlig@loria.fr                                           #
#                                                                             #
###############################################################################


import os

from Ft.Xml.Domlette import NonvalidatingReader, implementation
from Ft.Xml import XPath, EMPTY_NAMESPACE
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint

from Modules.modulereply import ModuleReply 
from Modules.module import Module 



class System_Module(Module):
	"""
		It is the main class of local system state data for the NetConf Agent.
	"""
	
	
	def __init__(self, name, path, namespace, cacheLifetime, parameters):
		"""
		Create an instance and initialize the structure needed by it.
		@type  parameters: dictionary
		@param parameters : should be a dictionary containning the follow keys
		"""
		Module.__init__(self, name, path, namespace, cacheLifetime)
		
		

	def getConfig(self):
		dict = ["sysname", "nodename", "release", "version", "machine"]
		tuple = os.uname()

		doc = implementation.createDocument(EMPTY_NAMESPACE, None, None)
		element = doc.createElementNS(EMPTY_NAMESPACE,"system")
		doc.appendChild(element)
		i=0
		for t in tuple:
			node = doc.createElementNS(EMPTY_NAMESPACE,dict[i])
			element.appendChild(node)
			textNode = doc.createTextNode(t)
			node.appendChild(textNode)
			i=i+1

		# Execute shell command
		#res = os.popen3("rpmquery -a | grep xorg")
		res = os.popen3("rpmquery -a")
		# Read stdout output
		resultat = res[1].read()
		# Split the result to get rpm names in an array
		rpmlist = resultat.split("\n")

		rpmsElement = doc.createElementNS(EMPTY_NAMESPACE,"rpm-list")
		element.appendChild(rpmsElement)
		i=0
		for rpm in rpmlist:
			if rpm!="":
				node = doc.createElementNS(EMPTY_NAMESPACE,"rpm")
				rpmsElement.appendChild(node)
				textNode = doc.createTextNode(rpm)
				node.appendChild(textNode)
			i=i+1

		modulereply = ModuleReply(replynode=doc.documentElement)
		return modulereply


