###############################################################################
#																			 #
# PYenca software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM				#
# Copyright (C) 2005  Humberto ABDELNUR									   #
#																			 #
# This program is free software; you can redistribute it and/or			   #
# modify it under the terms of the GNU General Public License				 #
# as published by the Free Software Foundation; either version 2			  #
# of the License, or (at your option) any later version.					  #
#																			 #
# This program is distributed in the hope that it will be useful,			 #
# but WITHOUT ANY WARRANTY; without even the implied warranty of			  #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			   #
# GNU General Public License for more details.								#
#																			 #
# You should have received a copy of the GNU General Public License		   #
# along with this program; if not, write to the Free Software				 #
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. #
#																			 #
# Author Info:																#
#   Name : Humberto ABDELNUR												  #
#   Email: Humberto.Abdelnur@loria.fr										 #
#																			 #
###############################################################################

from Modules.modulereply import ModuleReply 
from Ft.Xml.Domlette import NonvalidatingReader,PrettyPrint
from Ft.Xml import XPath
from Ft.Xml import EMPTY_NAMESPACE
from Modules.module import Module
import XML_Condition
from constants import C

TEXT_NODE_NAME = "#text"
DOCUMENT_NODE  = "#document"

   
class Command:
	"""
		A class container for the data use to create a command on the device.
	"""
	
	def __init__(self,command=None,heading=None,negate=False,absolutepath=False,recursion=True):
		"""
			@type command: string
			@type heading: Command
			@type negate: boolean
			@type absolutepath: boolean
			@type recursion : boolean
		"""
		self.command = command
		self.heading = heading
		self.negate = negate
		self.absolutepath = absolutepath  
		self.recursion = recursion
		
	def setCommand(self,command):
		"""
			@type command: string
		"""
		self.command = command
		
	def setHeading(self,heading):
		"""
			@type heading: Command
		"""
		self.heading = heading
	
	def neg(self):
		"""
			Negates the negate attribute of the class
		"""
		self.negate = not self.negate
	
	def setNegate(self):
		self.negate = True
	
	def setAbsolutePath(self,value = True):
		"""
			@type absolutepath: boolean
		"""

		self.absolutepath = value 
	
	def setRecursion(self,value=True):
		"""
			@type recursion : boolean
		"""

		self.recursion = value
	
	def getRecursion(self): 
		"""
			@rtype : boolean
		"""
		return self.recursion
	
	def getCommandStr(self,neg):
		"""
			Generates the string representing the full command
			@type neg: boolean
			@rtype recursion : string
		"""

		negate = (not neg and self.negate) or (neg and not self.negate)
		
		if (negate):
			strcomm = "no "
		else :
			strcomm = ""
		
		if (not self.absolutepath):
			if (self.heading != None):
				strcomm = self.heading.getCommandStr(negate) 
			
		if (self.command != None):
			strcomm = strcomm + " " + self.command  
		
		return strcomm.strip()
	
	def __str__(self):
		"""
			@rtype recursion : string
		"""
		return self.getCommandStr(False)
		
	def clone(self):
		"""
			@rtype : Command
		"""

		command = Command()
		command.command = self.command 
		command.heading = self.heading 
		command.negate  = self.negate 
		command.absolutepath = self.absolutepath  
		command.recursion = self.recursion
		return command
		
		
class CommandList:
	"""
		A class container for keeping the list of command generated
	"""
	
	def __init__(self):
		self.enteringcommands = []
		self.leavingcommands =[]
		self.commands = []
	
	def newList(self):
		"""
			Move the actual list of command to the end of the definitive list of commands.
		"""
		self.commands.extend(self.enteringcommands)
		self.commands.extend(self.leavingcommands)
		self.enteringcommands = []
		self.leavingcommands =[]
	
	def appendCommands(self,commandslist):
		"""
			Append the commandslist list of commands to the envelop generated.
			@type commandslist: CommandList
			@param commandslist: The list to be appended
		"""
		self.enteringcommands.extend(commandslist.commands)
		self.enteringcommands.extend(commandslist.enteringcommands)
		self.enteringcommands.extend(commandslist.leavingcommands)
		
	def addCommand(self,enteringcommand=None,leavingcommand=None):
		"""
			Add the enteringcommand to the beginning of the actual list and the leavingcommand to the end of it. It will make the envelop. 
			Note that the new commands will be added in the middle of this 2 commands.
			@type enteringcommand : string
			@type leavingcommand : string
		"""
		if (enteringcommand != None):
			self.enteringcommands.append(str(enteringcommand))
		if (leavingcommand != None):
			self.leavingcommands.insert(0,str(leavingcommand))
	
	def getList(self):
		"""
			Get the list of commands.
			@rtype : [string]
		"""
		commands = self.commands + self.enteringcommands + self.leavingcommands
		return commands
		
class XML_Interpreter:
	"""
		This class is the one in charge of apply the request XML modification to the configuration.
	"""

	KEY_TAG = "key"
	XPATH_TAG = "xpath"
	NEGATION_TAG = "neg"
	ABSOLUTECOMM_TAG = "absolutecomm"

	RECURSION_TAG = "recursion"

	COMMAND_ITEM = "command"
	VALUE_ITEM = "value"
	EXITCOMMAND_ITEM = "exitcommand"
	CREATINGCOMMAND_ITEM = "creatingcommand"
	DELETINGCOMMAND_ITEM = "deletingcommand"
	FOLLOWCOMMAND_ITEM = "followcommand"
	
	CONDITION_ITEM = "condition"

	VALUE_STRING = "string"

	def __init__(self,structXML,namespaceURI,acceptXPathTag=False):
		"""
			Class constructor
			@type structXML: cDocument
			@param structXML: The Meta Language Translation structure for the desire Protocol
			@type namespaceURI: string
			@param namespaceURI: specifies the namespace of the xml configuration.
			@type acceptXPathTag : boolean
			@param acceptXPathTag: If the "Looking inside the values" capability should be used or not. It allows an xpath tag 
									in the nodes of an editconfig request.
		"""
		self.structXML = structXML
		self.namespaceURI = namespaceURI
		self.acceptXPathTag = acceptXPathTag

	def getXpathFromXMLNode(self,node,ignoreAttributes=None,allowAttributes=None,ignoreChilds=None,allowChilds=None):
		"""
			Creates from an xml node a xpath expression that represent it.
			If the allow paramiters is None, means that allow all.
			@type ignoreAttributes: [string]
			@param ignoreAttributes: The list of attributes that should be not include in the xpath
			@type allowAttributes: [string]
			@param allowAttributes: The list of attributes that only should be include in the xpath
			@type ignoreChilds: [cDomelette]
			@param ignoreChilds: The list of childs nodes that should be not include in the xpath
			@type allowChilds: [cDomelette]
			@param allowChilds: The list of childs nodes that only should be include in the xpath
			@rtype :string
			@return : The xpath expression
		"""
		xpath = ""
		conditions = []
		if (ignoreChilds == None):
			ignoreChilds =[]
		
		if (node.attributes != None):
			if (ignoreAttributes == None):
				ignoreAttributes =[]
			for attr in node.attributes:
				#if (not attr.name in ignoreAttributes ):
				#allowAttributes
				pass
				#do it
		
		evaluateattr = node.getAttributeNS(C.NETCONF_XMLNS, C.XPATH)
		if ( evaluateattr != "" ) :
			conditions.append(evaluateattr)
				
		for child in node.childNodes :
			if (child.nodeName == TEXT_NODE_NAME 
				or (not child in ignoreChilds and allowChilds == None) 
				or (allowChilds != None and child in allowChilds)
				or (self.acceptXPathTag and 
					child.xpath("//*[@*[local-name() ='"+C.XPATH +"' and namespace-uri()='"+C.NETCONF_XMLNS+"']]") != []) ):
				if (child.nodeName == TEXT_NODE_NAME):
					value = child.nodeValue.strip()
					if (value != ""):
						value = ".='" + value + "'"
						conditions.append(value)
				else : # (node.hasChildNodes()):
					xpath = self.getXpathFromXMLNode(child,ignoreAttributes,allowAttributes,ignoreChilds,allowChilds)
					conditions.append(xpath)
			
		xpath =  "ns:" + node.nodeName  
		if (len(conditions) > 0 ):
			xpath = xpath +"["	
			for cond in conditions[:-1]:
				xpath = xpath + cond + " and "
			else:
				xpath = xpath + conditions[-1] + "]"
		
		return xpath

	def getInvolvedNodes(self,defaultoperation,targetnode,editnode):
		"""
			Look for the parent, in targetnode, of every node that match the editnode request ignoring the node with the 'operation' attribute.
			@type defaultoperation : Module.MERGE_OPERATION | Module.REPLACE_OPERATION | Module.NONE_OPERATION  
			@type targetnode: cDomelette
			@param targetnode : Where the editnode should be look for.
			@type editnode: cDomelette
			@param editnode: The editconfig node representing the request
			@rtype : [cDomelette]
			@return: the list of parent matching the editnode in the targetnode or None in case the matching were in the root node.
		"""
		
		operationNode = editnode.xpath("//*[@*[local-name() ='"+C.OPERATION +"' and namespace-uri()='"+C.NETCONF_XMLNS+"']]")
		if (len(operationNode) == 0):
			self.issueNode = editnode
			self.operationType = defaultoperation
			matchnodes = [None] 
		elif (len(operationNode) > 1):
			reply = ModuleReply(
			error_type=ModuleReply.APLICATION,
			error_tag=ModuleReply.BAD_ATTRIBUTE,
			error_severity=ModuleReply.ERROR,
			error_message="Exists more than one node with attribute 'operation'")
			reply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO, C.OPERATION)
			for node in operationNode:
				reply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO,node.nodeName)
			
			raise XML_Interpreter_Exception(reply) 
		else:
			self.issueNode = operationNode[0]
			self.operationType = self.issueNode.getAttributeNS(C.NETCONF_XMLNS, C.OPERATION)
			
			xpath = ""
			parent = self.issueNode
			ignorechild = None
			while (parent != editnode):
				ignorechild = parent
				parent = parent.parentNode
					
				xpath = "/" + self.getXpathFromXMLNode(parent,ignoreChilds=[ignorechild]) + xpath
			xpath =  ".." + xpath
			
			matchnodes = targetnode.xpath(xpath,{"ns":self.namespaceURI})
			
		return  matchnodes

	def makeCommand(self,issuenode,structnode,commandName,absolutepathdefault=False,recursiondefault=True):
		"""
			Generates the appropiate device commands according to the protocol XML Meta Translation Languag eStructure
			@type issuenode: cDomelette
			@param issuenode: is a the xml node that we want to make the commands of
			@type structnode : cDomelette
			@param structnode: is the parent in the device's XML Configuration of the equivalent node of issuenode in the XML structure
			@type commandName: COMMAND_ITEM | EXITCOMMAND_ITEM | CREATINGCOMMAND_ITEM | DELETINGCOMMAND_ITEM | FOLLOWCOMMAND_ITEM 
			@param commandName: is the kind of command that should be look for.
			@type absolutepathdefault: boolean
			@param absolutepathdefault: When creates the commands it ignore the followcommand.
			@type recursiondefault: boolean
			@param recursiondefault: The recursion default value for the type of command
			@rtype: CommandList
			@return: the list of command generated for this issuenode.
		"""
		
		commands = None
		
		for structoption in structnode.xpath(commandName):
			if (commands == None) :
				commands =[]
				
			condition = structoption.xpath(self.CONDITION_ITEM)
			if (condition != []):
				conditionvalue = XML_Condition.getConditionValue(issuenode,condition[0])
			else:
				conditionvalue = True	
				
			if (conditionvalue):
				command = Command(absolutepath=absolutepathdefault,recursion=recursiondefault)
			
				textlist = structoption.xpath("text()")
				strcommand = ""
				for text in textlist:
					strcommand = strcommand + text.nodeValue.strip()
				
				tempvalue = structoption.getAttributeNS(EMPTY_NAMESPACE,self.NEGATION_TAG)
				if (tempvalue == "true"):
					command.setNegate()
				
				tempvalue = structoption.getAttributeNS(EMPTY_NAMESPACE,self.ABSOLUTECOMM_TAG)
				if (tempvalue == "true"):
					command.setAbsolutePath()
				elif (tempvalue == "false"):
					command.setAbsolutePath(False)
	
				tempvalue = structoption.getAttributeNS(EMPTY_NAMESPACE,self.RECURSION_TAG)
				if (tempvalue == "true"):
					command.setRecursion(True)
				elif (tempvalue == "false"):
					command.setRecursion(False)
			
				values = []
				
				for structoption in structoption.xpath(self.VALUE_ITEM):
					
					condition = structoption.xpath(self.CONDITION_ITEM)
					if (condition != []):
						conditionvalue = XML_Condition.getConditionValue(issuenode,condition[0])
					else :
						conditionvalue = True
					
					value = ""
					if (conditionvalue):
						for item in structoption.childNodes:
							if (item.nodeName == TEXT_NODE_NAME):
								if (item.nodeValue.strip() != ""):
									for xpathvalue in issuenode.xpath(item.nodeValue.strip()):
										if (xpathvalue.nodeName == TEXT_NODE_NAME):
											value = value + " " + xpathvalue.nodeValue
										else :
											value = value + " " + xpathvalue.nodeName
							elif (item.nodeName == self.VALUE_STRING):
								value = value + " " + item.firstChild.nodeValue
					
					values.append(value.strip())
				   
				if (strcommand != ""):
					strcommand = strcommand % tuple(values)
					command.setCommand(strcommand)
				
				commands.append(command)
		return commands
					
	def makeNode(self,node, structnode, followcommand,stepping, creating,deleting):
		"""
			Creates the device's command for the node paramiters, the commands created will de could be to 
			delete, create or making the envelop for the node in the device.
			@type node: cDomelette
			@param node: Issue Node
			@type structnode: cDomelette
			@param structnode: It is the equivalent node of "node" but in the XML Translation Language
			@type followcommand: Command
			@param followcommand: It is the command that will be appended to the beginning of all the commands generated by
								  this function.
			@type stepping : boolean
			@param stepping: If set to true, it will just look for the <command> and <followcommand> nodes in the structnode, 
							 this is the case for generating the envelop.
			@type creating: boolean
			@param creating: If set to true, it will look for the <creatingcommand> nodes in the structnode, if any , 
							 otherwise will use the <command> node to generated the commands
			@type deleting: boolean
			@param deleting: If set to true, it will look for the <deletingcommand> nodes in the structnode, if any , 
							 otherwise will negate the <command> node and use it to generated the commands
		"""
		
		commandlist = CommandList()
		
		if (structnode != None):
			structnode = structnode.xpath(node.nodeName)
			if(structnode != []):
				structnode = structnode[0]
			else:
				structnode = None
		
		if (structnode != None):
			deletingvar = deleting 
			steppingvar = stepping
			creatingvar = creating 
			while (deletingvar or steppingvar or creatingvar):
				
				if (deletingvar):
					deletingvar = False
					followrecursion = False
					commandstr = self.DELETINGCOMMAND_ITEM
				elif (creatingvar):
					creatingvar = False
					followrecursion = False
					commandstr = self.CREATINGCOMMAND_ITEM
				elif (steppingvar):
					steppingvar = False
					followrecursion = True
					commandstr = self.COMMAND_ITEM
				
				commands = self.makeCommand(node,structnode,commandstr,recursiondefault=False)
				commandadd = False
				if (commands != None):
					commandadd = True
					for subcommand in commands:
						followrecursion = followrecursion or subcommand.getRecursion()
						subcommand.setHeading(followcommand)
						commandlist.addCommand(subcommand)
				commands = self.makeCommand(node,structnode,"exit"+commandstr,absolutepathdefault=True,recursiondefault=False)
				if (commands != None):
					commandadd = True
					for subcommand in commands:
						followrecursion = followrecursion or subcommand.getRecursion()
						subcommand.setHeading(followcommand) 
						commandlist.addCommand(leavingcommand=subcommand)
				
				if (not commandadd and not stepping):
					commands = self.makeCommand(node,structnode,self.COMMAND_ITEM)
					if (commands != None):
						if(deleting):
							tempfollowcommand = followcommand.clone()
							tempfollowcommand.neg()
						else:
							tempfollowcommand = followcommand
						for subcommand in commands:
							followrecursion = followrecursion or subcommand.getRecursion()
							subcommand.setHeading(tempfollowcommand) 
							commandlist.addCommand(subcommand)
					else:
						followrecursion = True
						
				if (followrecursion):
					tempfollowcommand = self.makeCommand(node,structnode,self.FOLLOWCOMMAND_ITEM)
					if (tempfollowcommand != None and tempfollowcommand != []):
						tempfollowcommand[0].setHeading(followcommand)
						tempfollowcommand = tempfollowcommand[0]
					else:
						tempfollowcommand = followcommand
					if (structnode.childNodes != []):
						structchild = structnode.xpath("childs")
						if (structchild != []):
							structchild = structchild[0]
						else :
							structchild = None
						for child in node.childNodes :
							if (child.nodeName != TEXT_NODE_NAME):
								commandlistchild = self.makeNode(child, structchild, tempfollowcommand, stepping, creating,deleting)
								commandlist.appendCommands(commandlistchild)
				
				commandlist.newList()
		else:
			reply = ModuleReply(error_type=ModuleReply.APPLICATION, error_tag=ModuleReply.BAD_ELEMENT, 
						error_severity=ModuleReply.ERROR, error_message="The element not belong to this protocol")
			reply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO,node.nodeName)
			raise XML_Interpreter_Exception(reply)
		return commandlist
	
	def requestNodes(self,node, structnode):
		"""
			Using the key items from strucnode and the values of them in the editconfig request, it search for the nodes that 
			match them in the node paramiter. Also the if the acceptXPathTag is set to true it checks for the xpath attribute tags.
			@type node: cDomelette
			@param node: The node where the search will be apply
			@type strucnode: cDomelette
			@param strucnode: The current node from XML Translation Language equivalent to node.
			Return a list of nodes that are childs from 'node' and the key elements match with the ones from 'self.issueNode'
		"""
		
		requestNodes = None
		if (structnode != None):
			structnode = structnode.xpath(self.issueNode.nodeName)
		
			if (structnode == []):
				reply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.BAD_ELEMENT,
				error_severity=ModuleReply.ERROR,
				error_message="The element not belong to this protocol")
				reply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO,node.nodeName)
				raise XML_Interpreter_Exception(reply)
			else:
				structnode = structnode[0]
				if("true" == structnode.getAttributeNS(EMPTY_NAMESPACE,self.KEY_TAG)):
					reply = ModuleReply(
					error_type=ModuleReply.APPLICATION,
					error_tag=ModuleReply.BAD_ATTRIBUTE,
					error_severity=ModuleReply.ERROR,
					error_message="Attemp to do an operation over a key node")
					reply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO,self.issueNode.nodeName)
					reply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO,self.operationType)
					raise XML_Interpreter_Exception(reply)
		
			keynodes = structnode.xpath("childs/*[@"+self.KEY_TAG+"='true']")
			allowfields = []
			
			for keynode in keynodes:
				if self.namespaceURI == None :
					allowfields.extend(self.issueNode.xpath("*[local-name()='"+keynode.nodeName+"']"))
				else:
					allowfields.extend(self.issueNode.xpath("*[local-name()='"+keynode.nodeName+"' and namespace-uri()='"+self.namespaceURI+"']"))

				
			xpath =self.getXpathFromXMLNode(self.issueNode,allowChilds=allowfields)
			requestNodes = node.xpath(xpath,{"ns":self.namespaceURI})
			
		else:
			reply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.BAD_ELEMENT,
			error_severity=ModuleReply.ERROR,
			error_message="The element not belong to this protocol")
			reply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO,node.nodeName)
			raise XML_Interpreter_Exception(reply)

		return requestNodes


	def makeRequest(self, parentnode,structnode, followcommand):
		""" 
			Create the list of commands made for the issueNode into the parentnode 
			@type parentnode: cDomelette
			@param parentnode: where the request take place
			@type structnode: cDomelette
			@param structnode: the equivalent node of parentNode but in the XML Language Translation
			@type followcommand : Command
			@param followcommand: The command generated by the envelop that should be appended at the beginning of every	
								  command generated by makeRequest
			@rtype: boolean
			@return: if the request was acomplish (i.e. if it was a create request and the node allready exists it return false)
		"""
		requestmade = True
		
		requestNodes = self.requestNodes(parentnode, structnode)
		if (self.operationType == C.CREATE):
			if(requestNodes == []):
				nodecommands = self.makeNode(self.issueNode,structnode, followcommand,True,True,False)
				self.commands.appendCommands(nodecommands)
			else :
				requestmade = False
		elif (self.operationType == C.MERGE):
			if(requestNodes != []):
				nodecommands = self.makeNode(self.issueNode,structnode, followcommand,True,True,False)
				self.commands.appendCommands(nodecommands)
			else :
				requestmade = False
		elif (self.operationType == C.REPLACE):
			if(requestNodes == []):
				requestmade = False
			else:
				for requestnode in requestNodes:
					nodecommands = self.makeNode(requestnode,structnode, followcommand,False,False,True)
					self.commands.appendCommands(nodecommands)
				nodecommands = self.makeNode(self.issueNode,structnode, followcommand,True,True,False)
				self.commands.appendCommands(nodecommands)				
		elif (self.operationType == C.DELETE):
			if(requestNodes == []):
				requestmade = False
			else :
				for requestnode in requestNodes:
					nodecommands = self.makeNode(requestnode,structnode, followcommand,False,False,True)
					self.commands.appendCommands(nodecommands)
		else :
			reply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.BAD_ATTRIBUTE,
			error_severity=ModuleReply.ERROR,
			error_message="Attribute value unknown")
			reply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO,C.OPERATION)
			reply.addErrorInfo(ModuleReply.BAD_ELEMENT_INFO,self.operationType)
			raise XML_Interpreter_Exception(reply)
			
		return requestmade
		

	def makeEnvelop(self, structnode, followcommand,nodes):
		""" 
			Create the list of commands for generated the envelop (i.e. generates the commands to get to the path for apply the actual request)
			@type structnode: cDomelette
			@param structnode: The current node of the XML Translation Language equivalent to the node where the request will be apply.
			@type followcommand : Command
			@param followcommand: The command that should be appended at the beginning of every command generated by it.
			@type nodes: [cDomelette]
			@param nodes: The ancestors nodes in the Device's XML Configuration of the node where the request will be aplied
			@rtype: (cDomelette,Command)
			@return: The first elemet of the tuple is the current offset of the XML Translation Language and the second if the 
					followcommand created by the envelop if any.
		"""
	
		for node in nodes:
			
			if (structnode != None):
				structnode = structnode.xpath(node.nodeName)
				if (structnode == []):
					structnode = None
				else:
					structnode = structnode[0]
				
			if (structnode != None and structnode.childNodes != []):
				commands = self.makeCommand(node,structnode,self.COMMAND_ITEM)
				if (commands != None):
					for subcommand in commands:
						subcommand.setHeading(followcommand)
						self.commands.addCommand(subcommand)
					
				commands = self.makeCommand(node,structnode,self.FOLLOWCOMMAND_ITEM)
				if (commands != None and commands != []):
					commands[0].setHeading(followcommand)
					followcommand =  commands[0]
						
				commands = self.makeCommand(node,structnode,self.EXITCOMMAND_ITEM,absolutepathdefault=True)
				if (commands != None):
					for subcommand in commands:
						subcommand.setHeading(followcommand)
						self.commands.addCommand(leavingcommand=subcommand)

				structnode = structnode.xpath("childs")
				if (structnode != []):
					structnode = structnode[0]
				else:
					structnode = None
			else:
				reply = ModuleReply(error_type=ModuleReply.APPLICATION, error_tag=ModuleReply.BAD_ELEMENT, 
						error_severity=ModuleReply.ERROR, error_message="The element not belong to this protocol")
				reply.addErrorInfo(ModuleReply.BAD_ATTRIBUTE_INFO,node.nodeName)
				raise XML_Interpreter_Exception(reply)

		return structnode, followcommand
		
	def returnDescendantFromTo(self,parent,node):
		"""
			Get the list of nodes, ordered by is-parent-of relation, from the parent to the node.
			@type parent: cDomelette
			@type node: cDomelette
			@rtype: [cDomelette]
		"""
		desc = []
		
		while (parent != node):
			desc.insert(0,node)
			node = node.parentNode
		desc.insert(0,node)
		return desc
		
	def interpretXMLRequest(self,defaultoperation,targetnode,editnode):
		"""
			Generates the devices commands request by the editnode applied to targetnode configuration
			@type defaultoperation : Module.MERGE_OPERATION | Module.REPLACE_OPERATION | Module.NONE_OPERATION  
			@type targetnode: cDomelette
			@param targetnode : Where the editnode should be apply.
			@type editnode: cDomelette
			@param editnode: The editconfig node representing the request
			@rtype : [string]
			@return: the list of commands generated by the request.
		"""
		
		#if (editnode.getAttributeNS(self.namespaceURI, "instance") != targetnode.getAttributeNS(self.namespaceURI, "instance")):
		# 4suite bug, the attribute doesn't have the namespace
			# Incorret instance, this instance is not suppose to evaluate the target node specified.
			#reply = ModuleReply()
			#raise XML_Interpreter_Exception(reply)
		
		matchnodes = self.getInvolvedNodes(defaultoperation,targetnode,editnode)
		
		self.commands = CommandList()
		requestmade = False
		
		if (self.operationType != C.NONE):
			for node in matchnodes:
				self.commands.newList()
				structnode = self.structXML.documentElement
					
				if (node != None):
					parentnodes = self.returnDescendantFromTo(targetnode,node)
					structnode, followcommand = self.makeEnvelop(structnode,Command(),parentnodes)
				else:
					node = targetnode
					followcommand = Command()
					
				requestmade = requestmade or self.makeRequest(node,structnode, followcommand)
				
		elif (matchnodes == []):
			reply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.DATA_MISSING, 
			error_severity=ModuleReply.ERROR,
			error_message="The hierarchy of the BGP document doesn't exists")
			raise XML_Interpreter_Exception(reply)
		
		if (not requestmade):
			if (self.operationType == C.CREATE):
				reply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.DATA_EXISTS, 
				error_severity=ModuleReply.ERROR,
				error_message="A 'create' operation was attempted on data which already exists.")
			elif (self.operationType == C.REPLACE or self.operationType == C.MERGE):
				reply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.DATA_MISSING, 
				error_severity=ModuleReply.ERROR,
				error_message="A "+ self.operationType +" operation was attempted on data which does not exists.")
			
			raise XML_Interpreter_Exception(reply)
			
		return self.commands.getList()
	
class XML_Interpreter_Exception(Exception):
	def __init__(self,errorReply):
		self.errorReply= errorReply
	
	def getErrorReply(self):
		return self.errorReply
