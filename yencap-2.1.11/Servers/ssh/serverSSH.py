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


import traceback, threading
import LogManager
from threading import *
from struct import *
from Ft.Xml import XPath
from Servers.server import Server
import paramiko
from paramiko import Transport
from Servers.ssh.SSHServerModule import SSHServerModule
from constants import C
import socket

class ServerSSH(Server):

	def __init__(self, ipVersion, sshData):
		
		# Call super class (Server) constructor:
		Server.__init__(self, ipVersion, C.NETCONF_SSH_PORT)

		# Setup server parameters:
		self.sshData = sshData
		

	def handleconnection(self, clientsock):
		"""
			Handles incoming connection.
			@type clientsock: socket
			@param clientsock: The client socket
		"""
		#clientsock.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, 1)
		
		if self.ipVersion == 4:
			host, port = clientsock.getpeername()
		elif self.ipVersion == 6:
			host, port, flowinfo, scopeid = clientsock.getpeername()

		# Acquire a lock for accessing the pool of sockets:
		self.lockpool.acquire()

		LogManager.getInstance().logInfo("received new connection from (%s, %i)" % (host,port))
		
		try:
			if len(self.waitinglist)==0 and (activeCount()-1)>=self.MAXTHREADS:
				#Too many connections
				clientsock.close()
				return
			if len(self.waitinglist)==0:
				self.startthread()
			
			# Authenticates the new client.
			clientsock,authenticated,self.username = self.sshAuthentication(clientsock)
			
			if (authenticated):
				self.queue.append(clientsock)
				LogManager.getInstance().logInfo("SSH Connection established. Authentication succeeded: (%s, %i)." % (host,port))
			else:
				# Authentication failed
				LogManager.getInstance().logError("SSH Connection not established. Authentication failed: (%s, %i). Closing socket." % (host,port))
				clientsock.close()
				return
			self.sem.release()
		finally:
			self.lockpool.release()


	def sshAuthentication(self, clientsock):
		# setup logging
		paramiko.util.log_to_file(C.SYSLOG_FILE)

		# Check that SSH server parameters have been set:
		if (self.sshData == None):
			return clientsock, False, None
		else:
			# Load private key of the server
			filekey = self.sshData["hostKeyFile"]
			if (not filekey.startswith("/")):
				filekey = C.YENCAP_CONF_HOME + "/" + filekey

			# Build a key object from the file path:
			if (self.sshData["hostKeyType"] == "dss"):
				priv_host_key = paramiko.DSSKey(filename=filekey)
			elif (self.sshData["hostKeyType"] == "rsa"):
				priv_host_key = paramiko.RSAKey(filename=filekey)

		try:
			event = threading.Event()
			# Create a new SSH session over an existing socket, or socket-like object.
			t = Transport(clientsock)
			# Add a host key to the list of keys used for server mode.
			t.add_server_key(priv_host_key)
			# paramiko.ServerInterface defines an interface for controlling the behavior of paramiko in server mode.
			server = SSHServerModule()
			# Negotiate a new SSH2 session as a server.
			t.start_server(event, server)
			while 1:
				event.wait(0.1)
				if not t.is_active():
					return clientsock, False, None
				if event.isSet():
					break
		
			# Return the next channel opened by the client over this transport, in server mode.
			channel = t.accept(20)
			
			if channel is None:
				return clientsock, False, None
		
		except Exception, e:
			LogManager.getInstance().logError("Caught exception: %s: %s" % (str(e.__class__), str(e)))
			traceback.print_exc()

			try:
				t.close()
			except:
				pass
			return clientsock, False, None
	
		return channel, True, server.user


