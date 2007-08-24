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

from Operations.operationReply import OperationReply
from Operations.operation import Operation
from constants import C
from Ft.Xml import XPath
from xml.dom import Node
from Ft.Xml.Domlette import implementation
from Modules.modulereply import ModuleReply
from Modules import ModuleManager
from Modules import ModuleReader
import sys, os, zipfile


class Manage_modules_operation(Operation):

	def __init__(self): 
		"""
			Constructor of a Manage_modules_operation command.
		"""
		pass


	def execute(self):
		"""
			Execute the Manage_modules operation.
		"""
		
		moduleReply = ModuleReply()

		operationType = self.operationItem["operation"]
		name = self.operationItem["name"]
		moduleManager = ModuleManager.getInstance()

		if operationType == "deploy":

			if moduleManager.getModuleFromName(name) != None:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.BAD_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Module %s is loaded and therefore can not be deployed again."%name)
			
			else:

				# Decode the Foo_Module.zip
				fileContent = self.operationItem["file"]
				fileContent = fileContent.decode("string_escape")

				# Save it in tmp dir:
				f = open("/tmp/toto.zip",'wb')
				f.write(fileContent)
				f.close()

				# Install it in YencaP modules directory:
				os.popen3("unzip /tmp/toto.zip " + '-d ' + C.YENCAP_HOME + '/Modules')
				#self.extract('/tmp/toto.zip', '/tmp/' + name + '_Module')

				# Update modules.xml
				moduleReader = ModuleReader.getInstance()
				moduleReader.addModule(name, self.operationItem["xpath"], self.operationItem["namespace"], self.operationItem["pref"], self.operationItem["cachelifetime"], self.operationItem["dictionnary"])
				moduleReader.writeModules()

				# Update hello.xml: No. This is done automatically after loading the deployed module. See Server.py
		
		elif operationType == "undeploy":

			if moduleManager.getModuleFromName(name) != None:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.BAD_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Module %s is loaded and therefore can not be undeployed. Unload it first."%name)
			else:
				# Remove the module from the file system
				modulepath = C.YENCAP_HOME + '/Modules/' + name + "_Module"
				# Remove the content of the module directory (os.rmdir works only if the dir is empty !!!)
				self.removeall(modulepath)
				# Remove the directory itself
				os.rmdir(modulepath)
			
				# Update modules.xml
				moduleReader = ModuleReader.getInstance()
				moduleReader.removeModule(name)
				moduleReader.writeModules()

				# Update hello.xml: No. This is done automatically. See Server.py

		elif operationType == "load":
			
			res = moduleManager.loadModule(name)

			if res == 0:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.BAD_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Module %s could not be loaded."%name)
			elif res == 2:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.BAD_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Module %s is already loaded."%name)
			elif res == 3:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.BAD_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Module %s does not exist and therefore can not be loaded."%name)
			elif res == 1:
				moduleReply = ModuleReply()

		elif operationType == "unload":
			
			res = moduleManager.unloadModule(name)
		
			if res == 0:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.BAD_ELEMENT,
				error_severity = ModuleReply.ERROR,
				error_message = "Module %s is not loaded and therefore could not be unloaded."%name)
			elif res == 1:
				moduleReply = ModuleReply()
		
		self.operationReply.setNode(moduleReply.getXMLNodeReply())
		return self.operationReply


	def setParameters(self, operation, NSS = None):
		"""
		Set the parameters

		@type  operation: Node
		@param operation: The operation node of the current Netconf request.
		"""

		self.prefixes = NSS
		self.operationItem = None

		for child in operation.childNodes:
			if child.nodeType == Node.ELEMENT_NODE:
				if child.tagName == "deploy":
					self.operationItem = {"operation":"deploy"}
					for elem in child.childNodes:
						if elem.nodeType == Node.ELEMENT_NODE:
							if elem.tagName == "name":
								name = elem.childNodes[0].nodeValue
								self.operationItem["name"] = name
							elif elem.tagName == "xpath":
								xpath = elem.childNodes[0].nodeValue
								self.operationItem["xpath"] = xpath
							elif elem.tagName == "namespace":
								namespace = elem.childNodes[0].nodeValue
								self.operationItem["namespace"] = namespace
								for att in elem.attributes.values():
									if att.name == "pref":
										pref = att.value
										self.operationItem["pref"] = pref
							elif elem.tagName == "cachelifetime":
								cachelifetime = elem.childNodes[0].nodeValue
								self.operationItem["cachelifetime"] = cachelifetime
							elif elem.tagName == "file":
								filecontent = elem.childNodes[0].nodeValue
								self.operationItem["file"] = filecontent
							elif elem.tagName == "parameters":

								dictionnary = {}

								for param in elem.childNodes:
									if param.nodeType == Node.ELEMENT_NODE:
										if param.tagName == "parameter":
											paramname = elem.attributes[0]
											paramvalue = elem.attributes[1]
											dictionnary[paramname] = paramvalue

								self.operationItem["dictionnary"] = dictionnary

				elif child.tagName == "undeploy":
					self.operationItem = {"operation":"undeploy"}
					for elem in child.childNodes:
						if elem.nodeType == Node.ELEMENT_NODE:
							if elem.tagName == "name":
								name = elem.childNodes[0].nodeValue
								self.operationItem["name"] = name
					
				elif child.tagName == "load":
					self.operationItem = {"operation":"load"}
					for elem in child.childNodes:
						if elem.nodeType == Node.ELEMENT_NODE:
							if elem.tagName == "name":
								name = elem.childNodes[0].nodeValue
								self.operationItem["name"] = name
					
				elif child.tagName == "unload":
					self.operationItem = {"operation":"unload"}
					for elem in child.childNodes:
						if elem.nodeType == Node.ELEMENT_NODE:
							if elem.tagName == "name":
								name = elem.childNodes[0].nodeValue
								self.operationItem["name"] = name
					


	def extract(self, filename, dir ):
		zf = zipfile.ZipFile( filename )
		namelist = zf.namelist()
		dirlist = filter( lambda x: x.endswith( '/' ), namelist )
		filelist = filter( lambda x: not x.endswith( '/' ), namelist )
		# make base
		pushd = os.getcwd()
		if not os.path.isdir( dir ):
			os.mkdir( dir )
		os.chdir( dir )
		# create directory structure
		dirlist.sort()
		for dirs in dirlist:
			dirs = dirs.split( '/' )
			prefix = ''
			for dir in dirs:
				dirname = os.path.join( prefix, dir )
				if dir and not os.path.isdir( dirname ):
					os.mkdir( dirname )
				prefix = dirname
		# extract files
		for fn in filelist:
			try:
				out = open( fn, 'wb' )
				buffer = StringIO( zf.read( fn ))
				buflen = 2 ** 20
				datum = buffer.read( buflen )
				while datum:
					out.write( datum )
					datum = buffer.read( buflen )
				out.close()
			finally:
				print fn
		os.chdir( pushd )




	def rmgeneric(self, path, __func__):

		try:
			__func__(path)
			#print 'Removed ', path
		except OSError, (errno, strerror):
			ERROR_STR= """Error removing %(path)s, %(error)s """
			print ERROR_STR % {'path' : path, 'error': strerror }
			

	def removeall(self, path):
	
		if not os.path.isdir(path):
			return
	
		files=os.listdir(path)

		for x in files:
			fullpath=os.path.join(path, x)
			if os.path.isfile(fullpath):
				f = os.remove
				self.rmgeneric(fullpath, f)
			elif os.path.isdir(fullpath):
				self.removeall(fullpath)
				f=os.rmdir
				self.rmgeneric(fullpath, f)

