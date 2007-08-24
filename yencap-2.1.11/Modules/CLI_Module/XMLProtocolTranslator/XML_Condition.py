###############################################################################
#                                                                             #
# PYenca software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Humberto ABDELNUR                                       #
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
#   Name : Humberto ABDELNUR                                                  #
#   Email: Humberto.Abdelnur@loria.fr                                         #
#                                                                             #
###############################################################################

from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint

TEXT_NODE_NAME = "#text"

CONDITION_ITEM = "condition"

CONDITION_STRING 		= "string"
CONDITION_VALUE 		= "value"
CONDITION_PATH 			= "path"
CONDITION_HASKEY		= "haskey"
CONDITION_EXIST_XPATH 	= "exist-xpath"
CONDITION_XPATH 		= "xpath"
CONDITION_EQUAL 		= "equal"
CONDITION_ISDIGIT 		= "isdigit"
CONDITION_NOT 			= "not"
CONDITION_OR 			= "or"
CONDITION_AND 			= "and"
CONDITION_FALSE			= "false"
CONDITION_TRUE 			= "true"

def getConditionValue(struct,structcondition):
	"""
		Evaluate the value of the struct according to the conditions specified in structcondition
		@type struct : cDomelette or an structure of primities types
		@param struct : Used for extract data in order to evaluate the condition
		@type structcondition: cDomelette
		@param structcondition : should be an <condition> xml node extracted from the xml structure to generate the protocol
		@rtype : boolean
		@return : The condition value evaluated with the data from struct
	"""
	return readCondition(struct,structcondition)[0]

def readCondition(struct,structcondition):
	"""
		Evaluate the value of the structs according to the conditions specified in structcondition
		@type struct : cDomelette or an structure of primities types
		@param struct : Used for extract data in order to evaluate the condition
		@type structcondition: cDomelette
		@param structcondition : should be an "boolean node" defined for the <condition> xml node extracted from the 
								 xml structure to generate the protocol
		@rtype : [boolean]
		@return : The condition value evaluated with the data from struct for each of the "boolean node"s in structcondition
	"""
	conditionvalue = []
	
	for item in structcondition.childNodes:
		if (item.nodeName != TEXT_NODE_NAME):
			if (item.nodeName == CONDITION_VALUE):
				listpath = item.xpath("text()")[-1].nodeValue.split('/')
				conditionvalue.append(struct.getStruct(listpath))
			elif (item.nodeName == CONDITION_EQUAL):
				values = readValues(struct,item)
				conditionvalue.append(values[0] == values[1])
			elif (item.nodeName == CONDITION_ISDIGIT):
				values = readValues(struct,item)
				conditionvalue.append(values[0].isdigit())
			elif (item.nodeName == CONDITION_HASKEY):
				listpath = item.xpath("text()")[-1].nodeValue.split('/')
				conditionvalue.append(struct.has_key(listpath))
			elif (item.nodeName == CONDITION_EXIST_XPATH):
				itemvalue = item.xpath("text()")[0].nodeValue
				itemvalue = struct.xpath(itemvalue)
				if (itemvalue == []):
					conditionvalue.append(False)
				else:
					conditionvalue.append(True)
			elif (item.nodeName == CONDITION_NOT):
				values = readCondition(struct,item)
				conditionvalue.append(not values[0]) 
			elif (item.nodeName == CONDITION_OR):
				values = readCondition(struct,item)
				value = False
				for x in values:
					value = value or x
				conditionvalue.append(value)
			elif (item.nodeName == CONDITION_AND):
				values = readCondition(struct,item)
				value = True
				for x in values:
					value = value and x
				conditionvalue.append(value)
			elif (item.nodeName == CONDITION_TRUE):
				conditionvalue.append(True)
			elif (item.nodeName == CONDITION_FALSE):
				conditionvalue.append(False)
			else:
				raise Exception					
	return conditionvalue
	

def readValues(struct,structvalue):
	"""
		Evaluate the value of the structs according to the conditions specified in structcondition
		@type struct : cDomelette or an structure of primities types
		@param struct : Used for extract data in order to evaluate the condition
		@type structcondition: cDomelette
		@param structcondition : should be an "non boolean node" defined for the <condition> xml node extracted from the 
								 xml structure to generate the protocol
		@rtype : [primities types]
		@return : The values obtained for the data specified in the <struct> node
	"""
	values = []
	
	for item in structvalue.childNodes:
		if (item.nodeName != TEXT_NODE_NAME):
			if (item.nodeName == CONDITION_STRING):
				values.append(item.xpath("text()")[0].nodeValue)
			elif (item.nodeName == CONDITION_PATH):
				listpath = item.xpath("text()")[-1].nodeValue.split('/')
				values.append(struct.getStruct(listpath))
			elif (item.nodeName == CONDITION_XPATH):
				itemvalue = item.xpath("text()")[0].nodeValue
				itemvalue = struct.xpath(itemvalue)
				if (itemvalue == []):
					values.append("")
				else:
					values.append(itemvalue[0].nodeValue)
			else:
				raise Exception					
	return values
