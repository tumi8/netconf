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


from Ft.Xml import XPath
from xml.dom import Node
from Modules.modulereply import ModuleReply
from Ft.Xml.Domlette import PrettyPrint, implementation
from Ft.Xml.XPath import Evaluate
from Ft.Xml.XPath.Context import Context
from abstractFilter import AbstractFilter
from constants import C
from xml.dom import XMLNS_NAMESPACE
import util


class XpathFilter(AbstractFilter):
	
	def __init__(self, initialNode, xpathRequestList, NSS = {}):
		"""
			initialNode is the node that will be filtered.
			initialNode will not be modified.
			Only the matching nodes are cloned from initialNode and rearranged to make a new document.
		"""

		self.initialNode = initialNode
		self.xpathRequestList = xpathRequestList
		self.NSS = NSS
		self.resultDocument = implementation.createDocument(C.YENCAP_XMLNS, "netconf", None)


	def applyFilter(self):
		"""
			initialNode will not be modified.
			A clone will be produced.
		"""
		
		selectedNodes = self.selectNodes()
		
		if len(selectedNodes) == 0:
			moduleReply = ModuleReply(
			error_type = ModuleReply.APPLICATION,
			error_tag = ModuleReply.UNKNOWN_ELEMENT,
			error_severity = ModuleReply.ERROR,
			error_message = "XPathFilter: the node set (selected with XPath expressions) is empty.")
			return moduleReply
		
		if self.initialNode in selectedNodes:
			clone = self.resultDocument.importNode(self.initialNode, True)
			self.resultDocument.replaceChild(clone, self.resultDocument.documentElement)
			return ModuleReply(self.resultDocument.documentElement)

		parentNodes = self.selectParents(selectedNodes)
		
		# Let's clone the selected nodes (deeply)
		clonedSelectedNodes = []
		for node in selectedNodes:
			clone = self.resultDocument.importNode(node, True)
			clonedSelectedNodes.append(clone)
				
		# Let's clone their parent's nodes (not deeply)
		clonedParentNodes = []
		
		for node in parentNodes:
			clone = self.resultDocument.importNode(node, False)
			clonedParentNodes.append(clone)
			if node == self.initialNode:
				self.resultDocument.replaceChild(clone, self.resultDocument.documentElement)
				
		# Now linking the clones together
		# 1. First, link the selected nodes to their cloned parents
		for i in range(0,len(selectedNodes)):
			selectedNode = selectedNodes[i]
			if selectedNode.parentNode != None and selectedNode.parentNode in parentNodes:
				index = parentNodes.index(selectedNode.parentNode)
				clonedParentNodes[index].appendChild(clonedSelectedNodes[i])

		# 2. Second, link the parents together
		for i in range(0,len(parentNodes)):
			parentNode = parentNodes[i]
			if (parentNode.parentNode != None and parentNode.parentNode in parentNodes):
				index = parentNodes.index(parentNode.parentNode)
				clonedParentNodes[index].appendChild(clonedParentNodes[i])

		# Why 0 ? because this is the clone of self.initialNode which is the "root" node
		return ModuleReply(clonedParentNodes[0])


	def selectParents(self, selectedNodes):
		"""
			Return a list made of the parents of selectedNodes
			Each parent must appear only once.
		"""
		# If the root node is selected, the selectedparents list is empty
		if self.initialNode in selectedNodes:
			return []

		parentNodes = [self.initialNode]
		toBeRemoved = []

		for elem in selectedNodes:
			tmpParentNodes = []
			tmp = elem
			while (tmp.parentNode != None and tmp.parentNode != self.initialNode):
				if tmp.parentNode in selectedNodes:
					# It means that elem is a child (direct or not) of another SelectedNode
					tmpParentNodes = []
					# Remove the selected node 'elem' as it will be included by another SelectedNode
					toBeRemoved.append(elem)
					break
				elif tmp.parentNode in parentNodes:
					# It means that the next parent nodes are already in the parent node list
					break
				else:
					tmpParentNodes.append(tmp.parentNode)
					tmp = tmp.parentNode

			parentNodes = parentNodes + tmpParentNodes
			
		for elem in toBeRemoved:
			selectedNodes.remove(elem)

		return parentNodes


	def selectNodes(self):

		nodes = []
		# Select the nodes
		for xpathRequest in self.xpathRequestList:
			try:
				ctx = Context(self.initialNode, processorNss = self.NSS)
				selectednodes = Evaluate(xpathRequest, ctx)
				for node in selectednodes:
					if node.nodeType == Node.DOCUMENT_NODE:
						node = node.documentElement
					# Append the node if not already in the list
					if node not in nodes:
						nodes.append(node)

			except Exception, exp:
				print str(exp)

		# return the result
		return nodes



