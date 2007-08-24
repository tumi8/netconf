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

from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint, implementation
from Ft.Xml import XPath , EMPTY_NAMESPACE

import XML_Condition

TEXT_NODE_NAME = "#text"
DOCUMENT_NODE  = "#document"

class XML_Constructor:
	"""
		This clas is responsible for interprete the typeData and XML Translation Language Structure to generate
		the Device's XML Configuration
		
		The attributes of the class:
		   - structXML: it is the XML structure representing the Meta Language Translation for the protocol.
		   - namespaceURI : specifies the namespace that the XML document configuration should has.
		   - instance: used to specified different instances of a module adding in this way an attribute "instance" to the main node of the XML configuration document with the value specified for it. If it None, means that not argument will be specified.
		   - doc : it is the target XML document generated at the moment.
	"""

	PARSE_ITEM = "parser"
	
	PATH_ITEM = "path"
	SETVALUE_ITEM = "setvalue"
	
	VALUETYPE_TAG = "valuetype"
	SCANTIME_TAG = "scantime"
	ALLOW_EMPTY_TAG = "allowempty"
	SHOWDEFAULT_TAG = "showdefault"
	REPRINTPARENT_TAG = "reprintparent"
	
	FIELD_TAG = "field"
	
	LIST_TYPE = "list"
	BOOL_TYPE = "boolean"
	DICT_TYPE = "dict"
	STRING_TYPE = "string"
	
	CONDITION_ITEM = "condition"

	def __init__(self,structXML,namespaceURI,instance = None):
		"""
			Class constructor
			@type structXML: cDocument
			@param structXML: The Meta Language Translation structure for the desire Protocol
			@type namespaceURI: string
			@param namespaceURI: specifies the namespace of the xml configuration.
			@type instance: string
			@param instance: Instance of the module 
		"""
		self.structXML = structXML
		self.namespaceURI = namespaceURI
		self.instance = instance
		
	def createNode(self,parent,tag,attrs=None,value=None):
		"""
			Creates a node in the xml document.
			@type parent: cDomelette
			@param parent: It is the node where the new created node will hung
			@type tag: string
			@param tag: The desire name for the new node
			@type attrs : dictionary
			@param attrs : The attributes of the node, the key will be used as the attribute name and the value as value (Not Implemented at the moment)
			@type value : string
			@param value: The text value for the new node
			@rtype : cDomelette
			@return: It returns the created node.
		"""
		element = self.doc.createElementNS(self.namespaceURI,tag)
		parent.appendChild(element)
		if (attrs != None):
			for attr in attrs:
				#do it
				pass
		if (value != None):
			text = self.doc.createTextNode(value)
			element.appendChild(text)
		return element
	
	def createNodeIfValue(self,parent,tag=None,attrs=None,value=None,checkvalue=False,default=False):
		"""
			If checkvalue is set to False, it just create the node. Otherwise it will check the value paramiter. 
				1. If set to true it will create an empty node according to the paramiter values.
				2. If is None but the default paramiter is set to True, it will also create the node.
				3. Otherwise the node won't be created.
			@type parent: cDomelette
			@param parent: It is the node where the new created node will hung
			@type tag: string
			@param tag: The desire name for the new node
			@type attrs : dictionary
			@param attrs : The attributes of the node, the key will be used as the attribute name and the value as value (Not Implemented at the moment)
			@type value : string
			@param value: The text value for the new node in case checkvalue is False, otherwise the value that will be check for creating or not the node.
			@type checkvalue: boolean
			@param checkvalue: The value that specified if the value paramiter should be check or not.
			@type default: boolean
			@param default: If value is equal to None, that means that a value wasn't specified, so this paramiter will set the default action for creating it or not.
			@rtype : cDomelette
			@return: It returns the created node.
		"""
		if (checkvalue):
			if (value):
				value = None
			elif(value == None and default):
				pass
			else:
				return
		return self.createNode(parent,tag,attrs,value)
	
	def getParserStructure(self,structnode,parsestruct,dinamicdata, modifyDefaultPath = True,listpos=None):
		"""
			It reads from the Meta Translation Language the <parser> nodes, which condition validate to True, of the current structnode.
			@type structnode: cDomelette
			@param structnode: It is the current node in the Meta Translation Language
			@type parsestruct: ParseStruct
			@param parsestruct: Specify the current offset of the typeData structure.
			@type dinamicdata: dictionary
			@param dinamicdata: It is used for the "setvalue" tag, it contains all, in the scope, the values specified for the XML Translation Language.
			@type modifyDefaultPath: boolean
			@param modifyDefaultPath: Used when parser specified to not change the offset.
			@type listpos: number
			@param listpos: If the parent is a "list" typevalue, this node should be the current element of the list.
			@rtype: [ParseValue]
			@return: It is the list of the <parser> nodes that validate the condition to true.
		"""
		parseitems = []
		iterator = 0
		condition = True
		structnodelist = structnode.xpath(self.PARSE_ITEM)
		childstruct = parsestruct.clone()
		
		for structoption in structnodelist:
			scantime = structoption.getAttributeNS(EMPTY_NAMESPACE,self.SCANTIME_TAG)
			if (scantime == ""):
				scantime = None
			else:
				scantime = int(scantime)
				
			valuetype = structoption.getAttributeNS(EMPTY_NAMESPACE,self.VALUETYPE_TAG)
			if (valuetype == ""):
				if (structoption.xpath("childs") != []):
					valuetype = self.DICT_TYPE
				else:
					valuetype = self.STRING_TYPE
					
			allowempty = structoption.getAttributeNS(EMPTY_NAMESPACE,self.ALLOW_EMPTY_TAG)
			if (allowempty == "true"):
				allowempty = True
			else:
				allowempty = False
				
			showdefault = structoption.getAttributeNS(EMPTY_NAMESPACE,self.SHOWDEFAULT_TAG)
			if (showdefault == "true"):
				showdefault = True
			else:
				showdefault = False
			
			reprintparent = structoption.getAttributeNS(EMPTY_NAMESPACE,self.REPRINTPARENT_TAG)
			if (reprintparent == "true"):
				reprintparent = True
			else:
				reprintparent = False
			
			fieldValues = {}
			condition = True
				
			for childoption in structoption.childNodes:
				if (childoption != TEXT_NODE_NAME):
					if (childoption.nodeName == self.CONDITION_ITEM):
						condition = XML_Condition.getConditionValue(childstruct,childoption)
						if (not condition):
							childstruct.copyOffset(parsestruct)
							break
					elif(childoption.nodeName == self.PATH_ITEM):
						path = childoption.xpath("text()")
						if (path != [] ):
							listpath = (path[-1]).nodeValue.split('/')
							childstruct.setOffset(listpath)
						modifyDefaultPath = False
					elif(childoption.nodeName == self.SETVALUE_ITEM):
						field = childoption.getAttributeNS(EMPTY_NAMESPACE,self.FIELD_TAG)
						value = childoption.xpath("text()")
						if (value != []):
							value = value[0].nodeValue
						else :
							value = ""
						fieldValues[field] = value
			
			if(modifyDefaultPath):
				if (childstruct.has_key([structnode.nodeName])):
					childstruct.setOffset([structnode.nodeName])
				else:
					condition = False
					
			if (condition):
				parseitem = ParseValue(childstruct,valuetype,allowempty,fieldValues,scantime,showdefault,reprintparent)
				parseitem.addFieldValues(dinamicdata)
				parseitems.append(parseitem)
				childstruct = parsestruct.clone()
			
		if(structnodelist == []):
			if (not modifyDefaultPath):
				parseitem = ParseValue(pathstruct=childstruct,fieldValues=dinamicdata)
				parseitems.append(parseitem)
			elif (childstruct.has_key([structnode.nodeName])):
				childstruct.setOffset([structnode.nodeName])
				parseitem = ParseValue(pathstruct=childstruct,fieldValues=dinamicdata)
				parseitems.append(parseitem)
			elif (dinamicdata.has_key(structnode.nodeName)):
				parseitem = ParseValue(fieldValues={structnode.nodeName: dinamicdata[structnode.nodeName]})
				parseitems.append(parseitem)
			
		return parseitems
		
	def makeXMLNode(self,parentnode,structnode,parseitem,dinamicdata):
		"""
			It creates the xml node according to the values.
			@type parentnode: cDomelette
			@param parentnode: It is the node where the new created node will hung
			@type structnode: cDomelette
			@param structnode: It is the current node in the Meta Translation Language
			@type parseitem: ParseStruct
			@param parseitem: Specify the current offset of the typeData structure.
			@type dinamicdata: dictionary
			@param dinamicdata: It is used for the "setvalue" tag, it contains all, in the scope, the values specified for the XML Translation Language.
			@rtype: cDomelette
			@return: It is the node created or None.
		"""
		
		if (structnode.xpath("childs") != []):
			value=None
			checkvalue=False
		else:
			if (parseitem.getTypeValue() == self.BOOL_TYPE):
				value = parseitem.getPathStruct().getStruct()
				checkvalue=True
			elif (parseitem.hasField(structnode.nodeName)):
				checkvalue=False
				value = parseitem.getFieldValue(structnode.nodeName)
			else:
				value = parseitem.getPathStruct().getStruct()
				checkvalue=False
					
		node = self.createNodeIfValue(parent=parentnode,attrs=None,
												tag=structnode.nodeName,
												value=value,checkvalue=checkvalue,
												default=parseitem.getShowDefault())
												
											   
		return node  
	
	def makeNodeFromData(self, parentnode, structnode, data,dinamicdata,actualPath=False):
		"""
			It parse the XML Translation Language and creates the xml node according to the values in data.
			@type parentnode: cDomelette
			@param parentnode: It is the node where the new created node will hung
			@type structnode: cDomelette
			@param structnode: It is the current node in the Meta Translation Language
			@type data: ParseStruct
			@param data: Specify the current offset of the typeData structure.
			@type dinamicdata: dictionary
			@param dinamicdata: It is used for the "setvalue" tag, it contains all, in the scope, the values specified for the XML Translation Language.
			@type actualPath: boolean
			@param actualPath: Used when parser specified to change the offset.
			@rtype: boolean
			@return: If was able to create the node from the data.
		"""
		reachleaf = False
		
		for structchild in structnode.childNodes:
			if (structchild.nodeName != TEXT_NODE_NAME):
				parseitems = self.getParserStructure(structchild,data,dinamicdata,not actualPath)
				if (parseitems != []):
					parseitem = parseitems.pop(0)
					while (parseitem.getScanTime() > 0):
						node = self.makeXMLNode(parentnode,structchild,parseitem,dinamicdata)
						
						if (node != None):
							
							childs = structchild.xpath("childs")
				   
							if (childs != []):
								notempty = parseitem.getAllowEmpty()
							
								if (parseitem.getTypeValue() == self.LIST_TYPE):
									childactualPath = not parseitem.getReprintParentNode()
								else:
									childactualPath = False
									
								for itemdata in  parseitem.getPathStruct() :
									if (node == None):
										node = self.makeXMLNode(parentnode,structchild,parseitem,dinamicdata)
									
									notempty = self.makeNodeFromData(node,childs[0], itemdata,parseitem.getFieldValues(structchild.nodeName),childactualPath) or notempty
									if (parseitem.getReprintParentNode()):
										node = None
										
								if (not notempty):
									parentnode.removeChild(node)
							else:
								notempty = True
							
							reachleaf = reachleaf or notempty  
							
						parseitem.decreaceScanTime()
						if (parseitems != [] and parseitem.getScanTime() == 0): 
							parseitem = parseitems.pop(0)
	
		return reachleaf
						
	def construct_XML(self,data):
		"""
			It Creates the XML configuration document according to the data argument.
			@type data: primities types
			@param data: It is the typeData
			@rtype : cDocument
			@return : The actual created document.
		"""
		self.doc = implementation.createDocument(self.namespaceURI, None, None)
		structnode = self.structXML.rootNode.xpath("/documentlink")[0]
		createnode = self.makeNodeFromData(self.doc,structnode, ParseStruct(data),{})
		
		if (self.instance != None):
			self.doc.documentElement.setAttributeNS(self.namespaceURI, "instance", self.instance)
		
		return self.doc


class ParseValue:
	"""
		This class is used as a container for store the information read from the <parser> node of the Meta Translation Language
	"""
	
	def __init__(self,pathstruct=None,typevalue=XML_Constructor.DICT_TYPE,allowempty=False,fieldValues=None,scantime=None,showdefault=False,reprintParent=False):
		self.pathstruct = pathstruct
		self.typevalue = typevalue
		self.allowempty = allowempty
		if (fieldValues == None):
			self.fieldValues = {}
		else:
			self.fieldValues = fieldValues
		if (scantime == None):
			self.scantime = 1
		else:
			self.scantime = scantime 
		self.showdefault = showdefault
		self.reprintParent = reprintParent
		
	def setPathStruct(self,pathstruct):
		self.pathstruct = pathstruct
	
	def getPathStruct(self):
		return self.pathstruct
	
	def getReprintParentNode(self):
		return self.reprintParent
		
	def setReprintParentNode(self,value=True):
		self.reprintParent = value
		
	def setTypeValue(self,typevalue):
		self.typevalue =typevalue
		
	def getTypeValue(self):
		return self.typevalue
		
	def setAllowEmpty(self,allowempty=True):
		self.allowempty = allowempty
		
	def getAllowEmpty(self):
		return self.allowempty
		
	def addFieldValue(self,field,value):
		self.fieldValues[field] = value
	
	def hasField(self,field):
		return self.fieldValues.has_key(field)
		
	def getFieldValue(self,field):
		return self.fieldValues[field]
	
	def addFieldValues(self,values):
		for item in values.keys():
			self.fieldValues[item] = values[item]
	
	def getFieldValues(self,offset):
		values = {}
		for item in self.fieldValues.keys():
			if (item.startswith(offset+"/")):
				values[item[len(offset)+1:]] = self.fieldValues[item]
		return values
		
	def setScanTime(self,scantime):
		if (scantime != None):
			self.scantime = scantime
		else:
			self.scantime = 1
		
	def getScanTime(self):
		return self.scantime
		
	def decreaceScanTime(self):
		self.scantime = self.scantime - 1
		
	def setShowDefault(self,value=True):
		self.showdefault = value
		
	def getShowDefault(self):
		return self.showdefault

class ParseStruct:
	"""
		This class is used to manage the offset of the typedData structure.
	"""

	def __init__(self,parsestruct,offset = None):
		self.parsestruct = parsestruct
		if (offset == None):
			self.offset = [""]
		else:
			self.offset = offset
		self.offsetstruct = self.findOffset()
	
	def __iter__(self):
		offsetstruct = self.offsetstruct
		offset = self.offset
		if (isinstance(offsetstruct,list)) :
			for pos in range(0,len(self.offsetstruct)):
				self.offsetstruct = offsetstruct[pos]
				self.offset = offset[:]
				self.offset.append(pos)
				yield self
		else:
			yield self  
		self.offsetstruct = offsetstruct
		self.offset = offset
			
	def clone(self):
		"""
			Make a cloned instance
			@rtype : ParseStruct
		"""
		item = ParseStruct(self.parsestruct)
		item.offset = self.offset[:]
		item.offsetstruct = self.offsetstruct
		return item
		
	def findOffset(self,offsetpos=0,tooffsetpos=None,offset=None):
		"""
			Find the specified offset from the ParseStruct instance offset.
			@type offsetpos: number
			@param offsetpos: it specified from which element in the offset argument it should find the offset
			@type tooffsetpos: number
			@param tooffsetpos: it specified to which element in the offset argument it should find the offset
			@type offset: [string]
			@param offset: It is a list of possibles keys
			@rtype : primities type
			@return : It return the offset looking for, or raise an exception if doesn't exists
		"""
		if(offset ==None):
			offset = self.offset
			
		if (offset[offsetpos] == ""):
			offsetstruct = self.parsestruct
			offsetpos = offsetpos + 1
		else:
			offsetstruct = self.offsetstruct
		
		for item in offset[offsetpos:tooffsetpos]:
			if(item == ".."):
				if (offsetpos == 0 ):
					raise Exception
				offsetstruct = self.findOffset(0,offsetpos-1,offset=offset[0:offsetpos-1]+offset[offsetpos+1:])
			else:
				offsetstruct = offsetstruct[item]
			offsetpos = offsetpos + 1
		return offsetstruct

	def setOffset(self,offset):
		"""
			Change the offset of the actual instance.
			@type offset: [string]
			@param offset: It is a list of possibles keys
		"""
		if (offset[0] == ""):
			self.offset = offset 
			self.offsetstruct = self.findOffset()
		else:
			offsetpos = len(self.offset)
			self.offset.extend(offset)
			self.offsetstruct = self.findOffset(offsetpos)
	
	def getOffset(self):
		"""
			Return the current offset of the structure
			@rtype : [string]
			@return : It is a list of keys from teh structure
		"""
	
		return self.offset[:]
	
	def copyOffset(self,item):
		"""
			Copy the offset from the item to the actual instance
			@type item: ParseStruct
			@param item : The instance from where it is desire to copy the offset
		"""
		self.offset = item.getOffset() 
		self.offsetstruct = item.getStruct()
		
	def getStruct(self,key=None):
		"""
			Get the primities type pointed by the offset of the instance
			@type key: [string]
			@param key: If is desire to get the struct of a nested primities type.
			@rtype: primities types
		"""
		if (key != None):
			return self.findOffset(offset=self.offset[:]+ key)
		else:
			return self.offsetstruct
	
	def has_key(self,key):
		"""
			Checks if the key exists in the structure.
			@type key: [string]
			@param key: List of possibles keys
			@rtype: boolean
		"""
		
		haskey = True
		if (key[0] == ""):
			offsetstruct = self.parsestruct
			offsetpos = 1
		else:
			offsetstruct = self.offsetstruct
			offsetpos = 0
	
		for item in key[offsetpos:]:
			if (not isinstance(item,int)):
				if(not offsetstruct.has_key(item)):
					haskey = False
					break
				offsetstruct = offsetstruct[item]
			else:
				if(item > len(offsetstruct) or item < -len(offsetstruct) ):
					haskey = False
					break
				offsetstruct = offsetstruct[item]
	
		return haskey
