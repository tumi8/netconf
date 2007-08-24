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

import util
from threading import Lock
from rbac import sessionManager
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from xml.dom import Node
from Modules.modulereply import ModuleReply
from Modules import ModuleManager
from rpc import Rpc
from constants import C
import LogManager


class NetconfService:


	def __init__(self, clientsock, username):

		self.username = username
		self.data = ''

		########## WARNING !!!############
		# This must be replaced by a NetconfLockManager who is able to order Netconf requests...
		self.netconfLock = Lock()

		# Exchanging capabilities with the Netconf manager
		self.exchangeCapabilities(clientsock)
		
		if self.session == None:
			print "server.py: session is None"


	def loop(self):

		# Loop until ending connection
		while 1 :
			# Receving a new Netconf request
			data = self.receive()
			
			processeddata = ""

			if (data == -1):
				msg = "Problem while receiving message. Please check application protocol and options (sessionId=%s)" % (self.session.getSessionId())
				LogManager.getInstance().logError(msg)
				sessionManager.getInstance().closeSession(self.session)
				break
						
			elif (data == -2):
				# Remote socket was badly closed. Closing session.
				msg = "Remote socket seems closed: (sessionId=%s). Closing Netconf session..." % (self.session.getSessionId())
				LogManager.getInstance().logError(msg)
				sessionManager.getInstance().closeSession(self.session)
				break
						
			else:
				# Processing the Netconf request
				try:
					processeddata = self.processrequest(data)	
				except Exception,exp:
					LogManager.getInstance().logError("remote socket seems closed: (sessionId=%s,error=%s)" % (self.session.getSessionId() , str(exp)))

			if (processeddata == ''):
				moduleReply = ModuleReply(
				error_type=ModuleReply.PROTOCOL,
				error_tag=ModuleReply.UNKNOWN_ELEMENT,
				error_severity=ModuleReply.ERROR,
				error_message="The response is unexpectedly empty.")
				nodeReply = moduleReply.getXMLNodeReply()
				processeddata = util.convertNodeToString(nodeReply)
					
			# Sending the response
			self.send(processeddata)
					
			if self.session.mustBeClosed == 1:
				LogManager.getInstance().logInfo("closing Netconf session: (sessionId=%s)" % (self.session.getSessionId()))
				sessionManager.getInstance().closeSession(self.session)
				break



	def exchangeCapabilities(self, clientsock):
		"""
		Exchange the capabilities with the manager.
		First sends the agent capabilities.
		Then waits for the remote manager capabilities.
		Creates a Netconf session and returns it.
		
		@type  clientsock: socket
		@param clientsock: The socket for the current client
		@rtype: session
		@return: The session created by the SessionManager.
		"""

		# Loading hello element along with static capabilities from hello.xml file
		helloRoot = NonvalidatingReader.parseUri(C.HELLO_URI)
		helloNode = helloRoot.documentElement

		# Finding a unique session-id for that session
		self.session = sessionManager.getInstance().createSession(clientsock, self.username)
		sessionId = self.session.getSessionId()
		LogManager.getInstance().logInfo("opening Netconf session: (sessionId=%s)" % (self.session.getSessionId()))
		
		# Setup the session-id value within session-id node
		sessionIdNode = helloRoot.createElementNS(C.NETCONF_XMLNS, C.SESSION_ID)
		helloNode.appendChild(sessionIdNode)
		sessionIdNode.appendChild(helloRoot.createTextNode(str(sessionId)))
		
		# Get the unique instance of the singleton ModuleManager:
		moduleManager = ModuleManager.getInstance()
		# Add the capabilities related to the modules to the hello message:
		for node in helloNode.childNodes:
			if (node.nodeType== Node.ELEMENT_NODE and node.tagName == C.CAPABILITIES):
				for module in moduleManager.getModules():
					capabNode = helloRoot.createElementNS(C.NETCONF_XMLNS, C.CAPABILITY)
					capabText = helloRoot.createTextNode(module.namespace)
					capabNode.appendChild(capabText)
					node.appendChild(capabNode)

		# Convert the hello element to String before sending
		hellostr = util.convertNodeToString(helloNode)

		# Sending capabilities along with a new session-id
		self.send(hellostr)

		# Receiving capabilities of the manager
		data = self.receive()

		# Printing Manager capabilities
		LogManager.getInstance().logInfo("Manager capabilities received: (sessionId=%s)" % (self.session.getSessionId()))
		


	def processrequest(self, data):
		"""
		Forward the received message to the requestScanner
		and returns its reply

		@type  data: string
		@param data: The message received from the client socket
		@rtype: string
		@return: The serialized XML reply to be sent back to the Netconf Manager
		"""
		
		self.netconfLock.acquire()

		try:
			# try to build the DOM, checking well-formness
			# http://madynes.loria.fr is there to avoid a warning...
			doc = NonvalidatingReader.parseString(data, 'http://madynes.loria.fr')
			
			# XML Schema validation
			#self.valid = util.validate(data,[C.NETCONF_SCHEMA_URI])

			mainNode = doc.documentElement
			if mainNode.tagName == C.RPC:
				rpcRequest = Rpc(mainNode, self.session)
				response = rpcRequest.execute()
			elif mainNode.tagName == C.HELLO:
				response =''
			else:
				moduleReply = ModuleReply(
				error_type=ModuleReply.PROTOCOL,
				error_tag=ModuleReply.UNKNOWN_ELEMENT,
				error_severity=ModuleReply.ERROR,
				error_message = "An element is not known. It should be an rpc or hello tag.")
				moduleReply.addErrorInfo("bad-element",mainNode.tagName)
				nodeReply = moduleReply.getXMLNodeReply()
				response = util.convertNodeToString(nodeReply)

		except Exception,exp:
			moduleReply = ModuleReply(
			error_type=ModuleReply.PROTOCOL,
			error_tag=ModuleReply.UNKNOWN_ELEMENT,
			error_severity=ModuleReply.ERROR,
			error_message="The Netconf message is not well-formed."+str(exp))
			nodeReply = moduleReply.getXMLNodeReply()
			response = util.convertNodeToString(nodeReply)

		self.netconfLock.release()
		return response
		

	def send(self, msg):
		"""
		Sends a message msg via a socket clientsock:
		First, sends the message length on 2 bytes
		Then, sends the message itself

		@type  session: Session
		@param session: The session of the current client
		@type  msg: string
		@param msg: The message to be sent
		"""

		# Sending the message itself
		self.session.socket.sendall("%s%s\r\n" % (msg,C.DELIMITER))


	def receive(self):
		"""
		Receives a Netconf message on the current socket
		First receives the size of the message on 2 bytes.
		Then receives the message itself
		The message can be:
		a Netconf message
		a compressed Netconf message
		an Encrypted-data XML node containing an encrypted Netconf request
		an Encrypted-data XML node containing an encrypted and compressed Netconf request

		@type  session: Session
		@param session: The session of the current client
		@rtype: string
		@return: The serialized XML request to be processed by the agent.
		"""
		
		datarcv = ''

		while (datarcv == ''):
			messages = self.data.split(C.DELIMITER)
			if len(messages) > 1:
				datarcv = messages[0]
				self.data = self.data[len(datarcv)+6::]
				datarcv = datarcv.strip()
				if datarcv != '':
					return datarcv
			tmp = self.session.socket.recv(1024)
			if not tmp:
				return -2
			else:
				self.data = "%s%s" % (self.data, tmp)
			
