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


import socket, traceback, os, sys, time, util, logger, syslog
from threading import *
from struct import *
from NetconfService import NetconfService


class Server:


	def __init__(self, ipVersion, port):
		"""
			Instantiates a new Server.
			@type port: Integer
			@param port: Port where the server will listening
			@rtype: Server
			@return: The Server itself
		"""
		
		# Socket parameters
		self.ipVersion = ipVersion
		self.host = ''
		self.port = port

		# Thread pool and socket pool management
		self.MAXTHREADS = 20
		# lockpool protects busylist and waitinglist.
		self.lockpool = Lock()
		# Dictionnary that indicates if the thread (identified by its name in the dictionnary keys) is busy (1) or not (deleted).
		self.busylist = {}
		# Dictionnary that indicates if the thread is ready for serving (i.e. waiting, otherwise he is busy).
		self.waitinglist = {}
		self.queue = []
		self.sem = Semaphore(0)


	def handleconnection(self, clientsock):
		"""
			Handles incoming connection.
			@type clientsock: socket
			@param clientsock: The client socket
		"""

		self.lockpool.acquire()
		try:
			if len(self.waitinglist)==0 and (activeCount()-1)>=self.MAXTHREADS:
				#Too many connections
				clientsock.close()
				return
			if len(self.waitinglist)==0:
				self.startthread()
			
			self.queue.append(clientsock)
			self.sem.release()

		finally:
			self.lockpool.release()


	def startthread(self):
		"""
			Start a new thread. This new thread calls the threadworker method.
		"""
		# Create a new thread. Target is the method that will be invoked when starting this thread.
		t = Thread(target=self.threadworker)
		# This new thread won't keep the software from closing
		t.setDaemon(1)
		# Start the new thread. (results in calling the threadworker method.)
		t.start()


	def threadworker(self):
		time.sleep(1)
		name=currentThread().getName()
		try:
			self.lockpool.acquire()
			try:
				self.waitinglist[name]=1
			finally:
				self.lockpool.release()

			self.processclients()
			
		finally:
			# Thread died
			if name in waitinglist:
				del self.waitinglist[name]
			# Since this thread died, start a new one...
			self.startthread()
	

	def processclients(self):
		name = currentThread().getName()
		while 1:
			self.sem.acquire()
			self.lockpool.acquire()
			try:
				clientsock = self.queue.pop()
				
				if clientsock == None:
					print "server.py: socket is None"

				del self.waitinglist[name]
				self.busylist[name] = 1
			finally:
				self.lockpool.release()
			
			try:

				netconfService = NetconfService(clientsock, self.username)
				netconfService.loop()

			except(KeyboardInterrupt, SystemExit):
				raise
			except :
				traceback.print_exc()
			
			try:
				clientsock.close()
			except KeyboardInterrupt :
				raise
			except:
				traceback.print_exc()
			
			self.lockpool.acquire()
			try:
				del self.busylist[name]
				self.waitinglist[name]=1
			finally:
				self.lockpool.release()


	def listener(self):
		"""
			Listening to new client connections requests.
			ServerSock creates new client sockets (fork), like a multi-client server.
			Append the client socket (for a Netconf Manager) to the socket queue.
		"""

		# Create the main socket:
		if self.ipVersion == 4:
			main_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		elif self.ipVersion == 6:
			main_socket = socket.socket(socket.AF_INET6, socket.SOCK_STREAM)
		
		# Set the main socket options:
		main_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR,1)

		# Bind the main socket to local host and port:
		main_socket.bind((self.host, self.port))

		# Start listenning to the connection requests:
		main_socket.listen(1)

		while 1:
			try:
				clientsock, clientaddr = main_socket.accept()
			except KeyboardInterrupt:
				raise
			except:
				traceback.print_exc()
				continue
			self.handleconnection(clientsock)
						


		
