###############################################################################
#                                                                             #
# PYenca software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
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


import os, sys
import string
from Ft.Xml.Domlette import PrettyPrint, NonvalidatingReader


def createNode(doc, nodeName, textContent):
	mainFileNode = doc.createElementNS(None,nodeName)
	mainFileText = doc.createTextNode(textContent)
	mainFileNode.appendChild(mainFileText)
	return mainFileNode




if (len(sys.argv)!=4):
	print ("Usage: python moduleGenerator moduleName InheritedModuleName Xpath")
	sys.exit(0)

moduleName = sys.argv[1]
inheritedClassName = sys.argv[2]
xpathExpression= sys.argv[3]


if (inheritedClassName != "Module" and inheritedClassName != "EasyModule"):
	print ("Inherited module must be either \"Module\" or \"EasyModule\".")
	sys.exit(0)

name = moduleName + "Module"
className = moduleName + "_Module"
mainFileName = moduleName + "_Module"
dirName = moduleName + "Module"
fileName = "Modules/toto.xml"


# Create new module directory

os.mkdir(dirName)


# Create main module file content

if (inheritedClassName == "Module"):

	fc = "from Modules.module import "+ inheritedClassName + "\n"
	fc = fc + "\n"

	fc = fc + "class " + className + "(" + inheritedClassName + "):\n\n"

	fc = fc + "\t self.fileName=\"" + fileName + "\"\n\n"

	fc = fc + "\t def getConfig(self, ...):\n"
	fc = fc + "\t\t\"\"\"\n"
	fc = fc + "\t\tPlease add your comments here.\n"
	fc = fc + "\t\t@param bla\n"
	fc = fc + "\t\tbla bla\n"
	fc = fc + "\t\t\"\"\"\n"
	fc = fc + "\t\t\n"
	fc = fc + "\t\t# Please put your code for get-config here.\n"
	fc = fc + "\t\t\n\n"

	fc = fc + "\t def editConfig(self, ...):\n"
	fc = fc + "\t\t\"\"\"\n"
	fc = fc + "\t\tPut your code for copy-config in this method\n"
	fc = fc + "\t\tAlso add your comments here.\n"
	fc = fc + "\t\t\"\"\"\n"
	fc = fc + "\t\t\n"
	fc = fc + "\n"

elif (inheritedClassName == "EasyModule"):
	fc = "from Modules.easyModule import EasyModule\n"
	fc = fc + "\n"

	fc = fc + "class " + className + "(" + inheritedClassName + "):\n"
	fc = fc + "\n"

	fc = fc + "\t self.fileName=\"" + fileName + "\"\n"
	fc = fc + "\n"




# Create file with fc
# -------------------

f = open(dirName + "/" + mainFileName+".py",'w')
f.write(fc)
f.close()


# Create __init__.py for new package 
# -------------------

f = open(dirName + "/__init__.py",'w')
f.write(" \n ")
f.close()


# Move the new directory to "Modules" directory
# -------------------

os.popen4("mv " + dirName + " ../Modules")

print "The module (" + name + ") was successfully created in \"server/Modules\" directory."

# Update modules.xml
# -------------------

path = "file:../conf/modules.xml"

doc = NonvalidatingReader.parseUri(path)

moduleNode = doc.createElementNS(None,'module')
doc.documentElement.appendChild(moduleNode)

moduleNode.appendChild(createNode(doc, 'name', name))
moduleNode.appendChild(createNode(doc, 'mainFileName', mainFileName))
moduleNode.appendChild(createNode(doc, 'className', className))
moduleNode.appendChild(createNode(doc, 'xpath', xpathExpression))
moduleNode.appendChild(createNode(doc, 'xsdfile', moduleName+".xsd"))


# Print modules document to modules.conf
# -------------------

f = open("../conf/modules.xml",'w')
PrettyPrint(doc, f)
f.close()

print "\"modules.xml\" file update succeeded."


