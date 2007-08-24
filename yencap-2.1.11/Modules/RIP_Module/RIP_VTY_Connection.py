###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
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
# Modified by:                                                                #
#   Name : Vincent CRIDLIG                                                    #
#   Email: Vincent.Cridlig@loria.fr                                           #
#                                                                             #
###############################################################################

import telnetlib
import datetime
from constants import C

class ConnectError (Exception):
	def __init__(self, **values):
		self.values = values
	
	def getError(self):
		return self.values["error"]
	
	def getErrorMessage(self):
		return self.values["msg"]
		
	def __str__(self):
		tmp =  "%s at port %s" % (self.values["host"], self.values["port"])
		error = self.values["error"]
		if (error == "Connection_Refused") :
			return "Couldn't connect to the host %s\nError message:\n%s" % (tmp, self.values["msg"])
		elif (error == "Connection_Closed") :
			return "The Connection unexpectely closed at host %s" % (tmp)
		elif (error == "UnStablish_Connection") :
			return "Could not stablish telnet connection with the host %s" % (tmp)
		elif (error == "Invalid_Password") :
			return "The password was not accepted at the host %s" % (tmp)
		elif (error == "Invalid_Enable_Password") :
			return "The enable password was not accepted at the host %s" % (tmp)
		else :
			return "Unknow Error at the host %s\nError message:\n%s" % (tmp, self.values["msg"])

class VTY_Connection :
	"""
		It is the class that actually comunicate with the real device, in this case Quagga
		@type  tn: dictionary
		@param tn: contains all the telnet connections open associated with it identifier as key.
		@type  host: string
		@param host:It is the host where the device implementing the BGP protocol is running.
		@type  port:string
		@param port: The port at which the device is listenning.
		@type  password:string
		@param password: The password to open a connection to the device.
		@type  enable_pass:string
		@param enable_pass: The password with right access, if any.
	"""

	TIMEOUT = 2
	PROMP = "> "
	ENABLE_PROMP = "# "
	CONFIGURE_PROMP =")# "
	
	def __init__(self,host,port,password,enable_pass):
		self.tn = {}
		self.host = host
		self.port = port
		self.password = password
		self.enable_pass = enable_pass
	
	def readUntil(self,id,*texts):
		"""
			Reads the data in the socket until it find one of the text values or a TIMEOUT occurs
			@type id: string
			@param id: The id identifying the telnet connection
			@type *texts: strings
			@param *texts: if one of them match the data read from the connection it will stop reading and return all the data read until that moment.
			@rtype : (number,string)
			@param: A tuple specifying the possition of the text that match the readed data, or None if any, and all the data readed.
		"""
		readtext = ""
		timeout = datetime.timedelta(seconds=self.TIMEOUT)
		timeout = timeout + datetime.datetime.now()
		try :
			while (timeout >= datetime.datetime.now()):
				readtext =  readtext + self.tn[id].read_very_eager()
				iterator = 0 
				for item in texts:
					if (readtext.endswith(item)):
						return iterator,readtext
					iterator = iterator + 1
			return None,readtext
		except EOFError,exp:
			raise ConnectError(error="Connection_Closed",host=self.host,port=self.port,msg=str(exp))
			
	def stablishConnection(self):
		"""
			It stablish the telnet connection to device and set it to enable.
			It open a telnet session to the port and host specified by the instance. Once the connection is open, it will send the password and set the connection's status to enabled.  
			@rtype : string
			@return: the id identifying the telnet connection created
		"""
	
		try :
			connection = telnetlib.Telnet(self.host,self.port)
			id = hash(connection)
			self.tn[id] = connection
		
			matchpos,state = self.readUntil(id,"Password: ")
			if (matchpos == None) :
				raise ConnectError(error="UnStablish_Connection",host=self.host,port=self.port)
			
			self.tn[id].write(self.password + "\n")
			matchpos,state = self.readUntil(id,self.PROMP,"Password: ")
			if (matchpos == None) :
				raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=state)
			elif (matchpos == 1) :
				raise ConnectError(error="Invalid_Password",host=self.host,port=self.port)
			
			self.tn[id].write("enable \n")
			matchpos,state = self.readUntil(id,self.ENABLE_PROMP,"Password: ")
			
			if (matchpos == 1) :
				self.tn[id].write(self.enable_pass + "\n")
				matchpos,state = self.readUntil(id,self.ENABLE_PROMP,"Password: ")
				if (matchpos == 1) :
					raise ConnectError(error="Invalid_Enable_Password",host=self.host,port=self.port)
					
			if (matchpos == None) :
				raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=state)
			
			return id
		except telnetlib.socket.error, msg:
			raise ConnectError(error="Connection_Refused",host=self.host,port=self.port,msg=str(msg))
		
	
	def closeConnection(self,id):
		"""
			Closes the telnet connection to the device.
			@type id: string
			@param id: The id identifying the telnet connection
		"""
	
		if (self.tn[id] != None):
			self.tn[id].close()
			del(self.tn[id])
	
	def setConfigureState(self,id,enable=True):
		"""
			It locks or unlock the use of the device.
			@type id: string
			@param id: The id identifying the telnet connection
			@type enable: boolean
			@param enable: for lock or unlock.
			@rtype : string
			@return: the id identifying the telnet connection, it could be different that the received paramiter
		"""
		if (enable):
			id = self.restablishConnection(id)
		
			self.tn[id].write("configure terminal \n")
			matchpos,state = self.readUntil(id,self.CONFIGURE_PROMP)
			if (matchpos == None) :
				raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=state)
		else:
			try:
				self.tn[id].write("\n")
				matchpos,state = self.readUntil(id,self.CONFIGURE_PROMP,self.ENABLE_PROMP)
					
				while (not state.endswith(self.ENABLE_PROMP)):
					self.tn[id].write("exit \n")
					matchpos,state = self.readUntil(id,self.CONFIGURE_PROMP,self.ENABLE_PROMP)
									
			except telnetlib.socket.error :
				#do nothing
				pass
			except ConnectError,exp:
				#do nothing
				pass
				
		return id
		
	def restablishConnection(self,id,configureState=False):
		"""
			Every time a new interaction with the connection occurs, it should be check that this is open.
			@type id: string
			@param id: The id identifying the telnet connection
			@type configureState: boolean
			@param configureState: if set to True, it will lock the device.
			@rtype : string
			@return: the id identifying the telnet connection, it could be different that the received paramiter
		"""
		try :
			self.tn[id].write("\n")
			matchpos,state = self.readUntil(id,self.ENABLE_PROMP,self.CONFIGURE_PROMP)
			
			if (matchpos == None) :
				raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=state)
			
			if (configureState and not state.endswith(self.CONFIGURE_PROMP)):
				self.setConfigureState(id)
			
		except ConnectError:
			del(self.tn[id])
			id = self.stablishConnection()
			if(configureState):
				id = self.setConfigureState(id)
		except telnetlib.socket.error:
			del(self.tn[id])
			id = self.stablishConnection()
			if(configureState):
				id = self.setConfigureState(id)
		
		return id
		
	def getConfiguration(self,id, configName, protocol="bgp"):
		"""
			Ask the configuration of the device and parse the header and footer of it, returning the BGP configuration by default.
			Can be also rip or ospf.
			@param id: The id identifying the telnet connection
			@type configureState: boolean
			@rtype : (string,string)
			@return : The first element is the id of the connection, could be different if the connection was restablished,
					  and the second the BGP configuration.
		"""
		try :
			id = self.restablishConnection(id)
			if configName == C.RUNNING:
				self.tn[id].write("show running-config \n")
			elif configName == C.STARTUP:
				self.tn[id].write("show startup-config \n")

			#############
			# This:
			# self.tn[id].write("show %s-config \n" % configName)
			# Does not work because the parameter is considered as a string !??
			#############

			matchpos,text = self.readUntil(id,self.ENABLE_PROMP)
			if (matchpos == None):
				raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=text)
			
			text = text[text.find("router " + protocol): text.rfind("line vty")]
			
			return id,text 
		except telnetlib.socket.error :
			raise ConnectError(error="Connection_Closed",host=self.host,port=self.port)
		



	def copyConfiguration(self, id, sourceName, targetName):
		try:
			id = self.restablishConnection(id)
			if sourceName == C.RUNNING and targetName == C.STARTUP:
				self.tn[id].write("copy running-config startup-config \n")
			elif sourceName == C.STARTUP and targetName == C.RUNNING:
				self.tn[id].write("copy startup-config running-config \n")

			matchpos,text = self.readUntil(id,self.ENABLE_PROMP)
			
			if (matchpos == None):
				raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=text)
			
			return id, text

		except telnetlib.socket.error :
			raise ConnectError(error="Connection_Closed",host=self.host,port=self.port)



	def sendCommand(self,id,command):
		"""
			Sends the command to the device
			@param id: The id identifying the telnet connection
			@type configureState: boolean
			@type command: string
			@param command: The command to send to the device.
			@rtype: (string,string)
			@return: The first element is the id of the connection, could be different if the connection was restablished,
					  and the reply, if any, from the device.
		"""
		
		id = self.restablishConnection(id,True)
			
		self.tn[id].write(command + "\n")
		
		matchpos,text = self.readUntil(id,self.CONFIGURE_PROMP)
		if (matchpos == None):
			raise ConnectError(error="Unknow_error",host=self.host,port=self.port,msg=text)
		
		result = text[text.find("\n")+1:text.rfind("\n")]
		if (result > 0) :
			return id,result
		return id,None
