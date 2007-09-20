###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Vincent CRIDLIG					      #
#									      #
# YencaP Module, University of Tuebingen, Wilhelm Schickard Institut	      #
# Computer Networks and Internet, Prof. Dr. Georg Carle    		      #
# http://www-ri.informatik.uni-tuebingen.de				      #
# 									      #	
# Copyright (C) 2006  Maximilian Huetter                                      #
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
#   Name : Maximilian Huetter                                                 #
#   Email: maxhuetter@web.de			                              #
#                                                                             #
###############################################################################
import os, string

#from xml.dom.minidom import parse, Element, Node
#from xml.dom.ext import PrettyPrint

from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from Ft.Xml import XPath, EMPTY_NAMESPACE, InputSource
from xml.dom import Node
from Ft.Xml.XPath import Evaluate, Compile
from Ft.Xml.XPath.Context import Context
from Ft.Xml.Xslt import Processor, DomWriter

from constants import C

from Modules.modulereply import ModuleReply 
from Modules.module import Module 




import popen2, select, fcntl

import subprocess, signal


class VERMONT_Module(Module):	
	"""
		Module to ENSuite with configuration data following the IPFIX Configuration Data Model 
		(see draft-muenz-ipfix-configuration-00).
		
		The implementation of a IPFIX device it is intended to be used with is the Versatile Monitoring Toolkit (VERMONT).
		See http://vermont.berlios.de/

		Due to the nature of VERMONT, that reads a configuration at startup you can get the running configuration (as there is always a running
		configuration in Netconf, but you can not edit it or copy anything into it. The running configuration just mirrors the startup.
	"""

	# CONFIGURATION DATASTORES
	RUNNING_TARGET = "running"
	CANDIDATE_TARGET = "candidate"
	STARTUP_TARGET = "startup"

	def __init__(self, name, path, namespace, cacheLifetime, parameters):
		Module.__init__(self, name, path, namespace, cacheLifetime)
		print "VERMONT Module loaded"
	
	def close(self): 
		"""
			Should be overrided if the module needs to close some resurces,i.e. sockets.
		"""
		print "VERMONT Module closed"

	def get(self, configDatastore):
		#status = getVERMONTStatus()

		#xmlFile = open(C.YENCAP_HOME + '/Modules/VERMONT_Module/running.xml')
		#doc = parse(xmlFile)
		
		dataFile = C.YENCAP_HOME + '/Modules/VERMONT_Module/' + configDatastore + '.xml'
		doc = NonvalidatingReader.parseUri("file:" + dataFile)
		
		modulereply = ModuleReply(replynode=doc.documentElement)
		
		return modulereply
	
	def getConfig(self, configDatastore):
		#xmlFile = open(C.YENCAP_HOME + '/Modules/VERMONT_Module/running.xml')
		#doc = parse(xmlFile)
		
		dataFile = C.YENCAP_HOME + '/Modules/VERMONT_Module/' + configDatastore + '.xml'
		doc = NonvalidatingReader.parseUri("file:" + dataFile)
		
		modulereply = ModuleReply(replynode=doc.documentElement)
		return modulereply

	def copyConfig(self, sourceName, targetName, sourceNode = None):
		"""
			Copy the sourceNode's XML configuration of the current module to the targenName.
			@type targetName: string
			@param targetName: the target datastore (running, candidate, startup)
			@rtype: ModuleReply
			@return: It should return a success or error message.
			** Relates to the netconf copy-config operation
		"""
		
		if (targetName in [C.URL, C.RUNNING]):
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED")
			return xmlreply
		
		#candidate or startup configurations are supported as sources.
		if sourceName in [C.CANDIDATE, C.STARTUP]:
			if os.path.exists(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + sourceName + '.xml'):
				try:					
					sourcefile = file(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + sourceName + '.xml','r')
					sourcexml = sourcefile.read()
					targetfile = file(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + targetName + '.xml','w')
					targetfile.write(sourcexml)
					sourcefile.close()
					targetfile.close()
				except:
					xmlreply = ModuleReply(
					error_type=ModuleReply.APPLICATION,
					error_tag=ModuleReply.OPERATION_FAILED, 
					error_severity=ModuleReply.ERROR,
					error_message="copying failed")
					return xmlreply
				modulereply = ModuleReply()
				return modulereply
				
		if sourceName == C.CONFIG:
			print "sourceNode in Copyconfig"
			print sourceNode
			return self.editConfig(C.REPLACE, C.SET, C.STOP_ON_ERROR, targetName, sourceNode)
		

	def editConfig(self, defaultoperation, testoption, erroroption, target, confignode, targetnode=None):
		"""
			Apply the request specified in confignode to the targetnode.
			@type defaultoperation: MERGE_OPERATION | REPLACE_OPERATION | NONE_OPERATION 
			@param defaultoperation : as specified in NETCONF protocol
			@type testoption : SET | TEST_AND_SET 
			@param testoption : as specified in NETCONF protocol
			@type erroroption : STOP_ON_ERROR | IGNORE_ERROR | ROLL_BACK_ON_ERROR 
			@param erroroption : as specified in NETCONF protocol
			@type target : RUNNING_TARGET | CANDIDATE_TARGET | STARTUP_TARGET
			@param target : as specified in NETCONF protocol
			@type targetnode : string
			@param targetnode : if the target is RUNNING_TARGET or STARTUP_TARGET it will be ignored otherwise should be the node of the 				CANDIDATE_TARGET that this module should process
			@rtype: ModuleReply
			@return: It should return a success or error message.
			** Relates to the netconf edit-config operation
		"""
		
		error = None

		if (target in [C.URL, C.RUNNING]):
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED")
			return xmlreply

		
			
		#PrettyPrint(configNodeRoot)
		#if replace is the defaultoperation, edit-config works just like copy-config with inline configuration data.
		if defaultoperation == "replace":
			f = open(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml','w')
			PrettyPrint(confignode, f)
			f.close()
			return ModuleReply()

		

		# draft-ietf-netconf-prot-11 says: "the default value for the default-operation is merge"
		# in this datamodel merge will replace all nodes with the same type and id it finds in configNodeRoot
		# and adds all it can't find
		elif defaultoperation == "merge":

			if not os.path.exists(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml'):
				ipfixConfigBase = '<ipfixConfig xmlns="urn:ietf:params:xml:ns:ipfix-config"></ipfixConfig>'
				xmlfile = file(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml','w')
				xmlfile.write(ipfixConfigBase)
				xmlfile.close()
			
			dataFile = C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml'
			configDoc = NonvalidatingReader.parseUri("file:" + dataFile)

			configNodeRoot = configDoc.documentElement

			for newProcess in confignode.childNodes:
				if newProcess.nodeType == Node.ELEMENT_NODE:
					processName = newProcess.localName
					processId = newProcess.getAttributeNS(EMPTY_NAMESPACE, "id")
					
					isNew = True
													
					for oldProcess in configDoc.documentElement.childNodes:

						if oldProcess.nodeType == Node.ELEMENT_NODE:
							
							if oldProcess.tagName == processName:
								#oldProcessId = oldProcess.attributes[(None, u'id')].value
								oldProcessId = oldProcess.getAttributeNS(EMPTY_NAMESPACE, "id")
								print "Old ProcessId:" + oldProcessId
								if oldProcessId == processId:
									isNew = False
									configNodeRoot.replaceChild(newProcess, oldProcess)
									
					if isNew:
						print "appending"
						configNodeRoot.appendChild(newProcess)

		#otherwise, every node has its own operation, create, delete, replace or merge 
		#the data model ipfix-config-data-model has to treat merge just as replace, as detailed
		#editing of some parts of the data are impossible, due to possible ambiguities. 				
		else:
			if not os.path.exists(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml'):
				ipfixConfigBase = '<ipfixConfig xmlns="urn:ietf:params:xml:ns:ipfix-config"></ipfixConfig>'
				xmlfile = file(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml','w')
				xmlfile.write(ipfixConfigBase)
				xmlfile.close()

			dataFile = C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml'
			configDoc = NonvalidatingReader.parseUri("file:" + dataFile)

			for newProcess in confignode.childNodes:
				if newProcess.nodeType == Node.ELEMENT_NODE:
					processName = newProcess.localName
					operation = newProcess.getAttributeNS('ietf:params:xml:ns:netconf:base:1.0', "operation")
					processId = newProcess.getAttributeNS(EMPTY_NAMESPACE, "id")
											
					print processName
					print operation
					print processId

					if processName == "ipfixConfig":
						continue
					if processId == None:
						error = "Config data to add has errors!"
						moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = error)
						return moduleReply
						
					
					if operation == None:	
						error == "If no defaultoperation is chosen, every process has to have its own operation!"
						moduleReply = ModuleReply(
							error_type = ModuleReply.APPLICATION,
							error_tag = ModuleReply.OPERATION_FAILED,
							error_severity = ModuleReply.ERROR,
							error_message = error)
						return moduleReply
					
					if operation == "create":
						configDoc.documentElement.appendChild(newProcess)
					else:
						error = processName + " " +  "not found!"

						for oldProcess in configDoc.documentElement.childNodes:

							if oldProcess.nodeType == Node.ELEMENT_NODE:
								if oldProcess.tagName == processName:
									#oldProcessId = oldProcess.attributes[(None, u'id')].value
									oldProcessId = oldProcess.getAttributeNS(EMPTY_NAMESPACE, "id")
									if oldProcessId == processId:
								
										if operation == "delete":
											configDoc.documentElement.removeChild(oldProcess)
											error = None
										elif operation == "replace" or operation == "merge":
											configDoc.documentElement.replaceChild(newProcess, oldProcess)
											error = None
										if error != None:
											if erroroption == "stop-on-error":
												modulereply = ModuleReply(error_type=ModuleReply.APPLICATION, error_tag=ModuleReply.OPERATION_FAILED, error_severity=ModuleReply.ERROR, error_message=error)
												return modulereply
		
		xmlFile = open(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + target + '.xml','w')
		PrettyPrint(configDoc, xmlFile)
		xmlFile.close()
		
		if error == None:
			modulereply = ModuleReply()
		else:
			modulereply = ModuleReply(error_type=ModuleReply.APPLICATION, error_tag=ModuleReply.OPERATION_FAILED, error_severity=ModuleReply.ERROR, error_message=error)
			return modulereply
		return modulereply

	def deleteConfig(self, targetName):
		
		if (targetName in [C.URL, C.RUNNING]):
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED")
			return xmlreply

		if os.path.exists(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + targetName + '.xml'):
			os.remove(C.YENCAP_HOME + '/Modules/VERMONT_Module/' + targetName + '.xml')
			xmlreply = ModuleReply()
			return xmlreply
		else:
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED, 
			error_severity=ModuleReply.ERROR,
			error_message="Datastore is already empty.")
			return xmlreply

	def validate(self, targetName, moduleNode = None):
		"""
			Validates the configuration of the targetnode by first calling xmllint to valdidate against the VERMONT-Config-Schema.xsd
			and then check the datapath of the configuration.
			@rtype: ModuleReply
			@return: It should return an error if the configuration does not validate 
		"""
		outdata = ""

		if (targetName in [C.URL, C.RUNNING]):
			xmlreply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_NOT_SUPPORTED, 
			error_severity=ModuleReply.ERROR,
			error_message="OPERATION-NOT-SUPPORTED")
			return xmlreply

		# call xmllint to validate against the schema

		cmd = "xmllint --schema " + C.YENCAP_HOME + "/Modules/VERMONT_Module/VERMONT-Config-Schema.xsd " + C.YENCAP_HOME + '/Modules/VERMONT_Module/' + targetName + '.xml'
		#cmd = 'xmllint --schema VERMONT-Config-Schema.xsd ' + targetName + '.xml'
		p = subprocess.Popen(cmd, shell=True, bufsize=1, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, close_fds=True)
		(child_stdin,  child_stdout, child_stderr) = (p.stdin, p.stdout, p.stderr)
		child_stdin.close()
				
		err = child_stderr.readlines()
		child_stdout.close()
		child_stderr.close()
		errdata = err[0]
		errdataparts = string.split(errdata)
		
		if errdataparts[1] != "validates":
			modulereply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED, 
			error_severity=ModuleReply.ERROR,
			error_message= errdata)
			return modulereply	
										
		
		#Data is valid according to the schema VERMONT-Config-Schema.xsd

		#Now check for the correct order of the processes 
		if moduleNode == None:
			dataFile = C.YENCAP_HOME + '/Modules/VERMONT_Module/' + targetName + '.xml'
			#dataFile = targetName + '.xml'
			
			configDoc = NonvalidatingReader.parseUri("file:" + dataFile)
			#PrettyPrint(configDoc)
			moduleNode = configDoc.documentElement
		
		#first check the observationPoints process order, if a observationPoint is followed by packetSelection
		#there has to be a MeteringProcess with packetReporting as well.

		#first get all meteringProcesses following the observationPoint: 
		nextMeteringProcesses = []
		for node in moduleNode.childNodes:
			if node.nodeType == Node.ELEMENT_NODE:
				if node.tagName == "observationPoint":
					#print "Found observationPoint, checking next process"				
					for childnode in node.childNodes:						
						if childnode.nodeType == Node.ELEMENT_NODE:
							if childnode.tagName == "next":
								for nextChild in childnode.childNodes:
									if nextChild.nodeType == Node.ELEMENT_NODE:
										if nextChild.tagName == "meteringProcessId":
											#print "Found next-meteringProcessId"
											for textChild in nextChild.childNodes:
												if textChild.nodeType == Node.TEXT_NODE:
													#print textChild.nodeValue
													nextMeteringProcesses.append(textChild.nodeValue)
				
		#Check if the meteringProcess follows a observationPoint and if it has packetSelection. If it does, it may have packetReporting as well.
		#If not it has to have a following meteringProcess which does.
		SearchPR = False
		for node in moduleNode.childNodes:
			if node.nodeType == Node.ELEMENT_NODE:
				if node.tagName == "meteringProcess":
					meteringProcessId = node.getAttributeNS(EMPTY_NAMESPACE, "id")
					print "Found Metering Process "
					print meteringProcessId
					if meteringProcessId in nextMeteringProcesses:
						#print "The meteringprocess is next for a OP"
						for childnode in node.childNodes:
							if childnode.nodeType == Node.ELEMENT_NODE:
								if childnode.tagName == "packetSelection":
									print "It has packetSelection"
									SearchPR = True
						if SearchPR:
							for childnode in node.childNodes:
								if childnode.nodeType == Node.ELEMENT_NODE:
									if childnode.tagName == "packetReporting":
										print "It also has packetReporting -> ok"
										SearchPR = False
					
					#if there is not packetReporting in the same meteringProcess, check the next process. Therefore get the processId.
					if SearchPR:
						print "It doesn't have packetReporting, checking next meteringProcess"
						for childnode in node.childNodes:
							if childnode.nodeType == Node.ELEMENT_NODE:
								if childnode.tagName == "next":
									for nextChild in childnode.childNodes:
										if nextChild.nodeType == Node.ELEMENT_NODE:
											if nextChild.tagName == "meteringProcessId":
												for textChild in nextChild.childNodes:
													if textChild.nodeType == Node.TEXT_NODE:
														followUpMeteringProcessId = textChild.nodeValue
														print "followUp MeteringProcessId: " 
														print followUpMeteringProcessId
						#Is there such a meteringProcess with packetReporting:
						for node in moduleNode.childNodes:
							if node.nodeType == Node.ELEMENT_NODE:
								if node.tagName == "meteringProcess":
									#print "Checking meteringprocess: "
									meteringProcessId = node.getAttributeNS(EMPTY_NAMESPACE, "id")
									print meteringProcessId
									if meteringProcessId == followUpMeteringProcessId:
										#print "MeteringProcess is the follow up process"
										for childnode in node.childNodes:
											if childnode.nodeType == Node.ELEMENT_NODE:
												if childnode.tagName == "packetReporting":
													print "Found the follow up process with packetreporting"											
													SearchPR = False
					#If there is no packetReporting, that is an error.
					if SearchPR:
						modulereply = ModuleReply(
						error_type=ModuleReply.APPLICATION,
						error_tag=ModuleReply.OPERATION_FAILED, 
						error_severity=ModuleReply.ERROR,
						error_message="Validation error: No packetReporting following a packetSelection.")
						return modulereply

		#A collectingProcess can only be followed by a MeteringProcess with flowMetering, so get the following meteringProcesses to check.
		nextMeteringProcesses = []
		for node in moduleNode.childNodes:
			if node.nodeType == Node.ELEMENT_NODE:
				if node.tagName == "collectingProcess":
					for childnode in node.childNodes:
						if childnode.nodeType == Node.ELEMENT_NODE:
							if childnode.tagName == "next":
								for nextChild in childnode.childNodes:
									if nextChild.nodeType == Node.ELEMENT_NODE:
										if nextChild.tagName == "meteringProcessId":
											for textChild in nextChild.childNodes:
												if textChild.nodeType == Node.TEXT_NODE:
													nextMeteringProcesses.append(textChild.nodeValue)

		#Check all following meteringProcess, if they have packetSelection or packetReporting, which will raise an error.
		for node in moduleNode.childNodes:
			if node.nodeType == Node.ELEMENT_NODE:
				if node.tagName == "meteringProcess":
					meteringProcessId = node.getAttributeNS(EMPTY_NAMESPACE, "id")
					if meteringProcessId in nextMeteringProcesses:
						for childnode in node.childNodes:
							if childnode.nodeType == Node.ELEMENT_NODE:
								print childnode.tagName
								if childnode.tagName in ["packetSelection", "packetReporting"]:
									modulereply = ModuleReply(
									error_type=ModuleReply.APPLICATION,
									error_tag=ModuleReply.OPERATION_FAILED, 
									error_severity=ModuleReply.ERROR,
									error_message="Validation error: packetSelection or packetReporting follows collectingProcess")
									return modulereply

		#If everything is correct, just return a ok.
		modulereply = ModuleReply()
		return modulereply
	
	def restart(self):
		""" 
		Restart VERMONT
		Forks a new child process with VERMONT to run independently.
		The new pid is writen to /var/run/vermont.pid
		When called, it will look for this file an kill the process with the number in it.
		
		
 
		"""
		import fcntl

		# vermont already running?
    		if os.path.exists("/var/run/vermont.pid"):
			try:
				pidfile = file("/var/run/vermont.pid", 'r')
				oldPid = pidfile.read()
				pidfile.close()
				#print "killing process " + oldPid
				os.kill(int(oldPid), signal.SIGTERM)

    			except:
				modulereply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_FAILED, 
				error_severity=ModuleReply.ERROR,
				error_message="Restart error: Could not kill old VERMONT Process")
				return modulereply
		
		#old process gone...
		startupPath = "-f" + C.YENCAP_HOME + "/Modules/VERMONT_Module/startup.xml"
		vermontProcess = subprocess.Popen([C.VERMONT, startupPath], stdin=None, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=False, close_fds=True)	
		returnvalue = vermontProcess.poll()
		
		if returnvalue == None:
			child_stdout = vermontProcess.stdout
			flags = fcntl.fcntl(child_stdout, fcntl.F_GETFL)
    			fcntl.fcntl(child_stdout, fcntl.F_SETFL, flags | os.O_NONBLOCK)
			output = ""
			while 1:
				[i, o, e] = select.select([child_stdout], [], [], 5)
				if i: s_ = child_stdout.read(1000)
				else: s_ = ''
				if s_:
					output = output + s_
					
				else:
					break
			
			outputSplit = string.split(output)	
			if "FATAL" in outputSplit:
				modulereply = ModuleReply(
				error_type=ModuleReply.APPLICATION,
				error_tag=ModuleReply.OPERATION_FAILED, 
				error_severity=ModuleReply.ERROR,
				error_message=output)
				return modulereply
			print output
			newPid = vermontProcess.pid
			pidfile = file("/var/run/vermont.pid", 'w')
			oldPid = pidfile.write(str(newPid))
			pidfile.close()

			modulereply = ModuleReply()
			return modulereply
		else: 
			#stderr = file("stderr.txt","r")
			#errData = stderr.read()
			#stderr.close()
			modulereply = ModuleReply(
			error_type=ModuleReply.APPLICATION,
			error_tag=ModuleReply.OPERATION_FAILED, 
			error_severity=ModuleReply.ERROR,
			error_message="Restart error: VERMONT died during startup.")
			return modulereply 
			

		

	

	
		
		

		
		
