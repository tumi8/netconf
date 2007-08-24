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


