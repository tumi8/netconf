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


class Session:

	def __init__(self, sessionId, clientsock, time, user):
		"""
			Instantiates a new session.
		
			@type  sessionId: int
			@param sessionId: Session identifier that is used in hello message.
			@type  socket: socket
			@param socket: Socket related to the current session.
		"""

		self.socket = clientsock
		self.rbac = 0
		self.creationTime = time
		self.mustBeClosed = 0
		self.locks=[]
		self.role = "default"
		# A session has an Id:
		self.sessionId = sessionId
		# A session belongs to one and only one user:
		self.user = user
		# A session can have several roles activated:
		self.roles = []
		

	def getSessionId(self):
		"""
			Return the session identifier "session-id" of the current session.
		
			@type  targetName: string
			@param targetName: Target name (one of <candidate>, <startup>, ...)
			@rtype: int
			@return: The session identifier
		"""
		
		return self.sessionId

	def hasLock(self, target):
		if target in self.locks:
			return 1

	def lock(self, target):
		self.locks.append(target)

	def unlock(self, target):
		self.locks.remove(target)

	def setRole(self, role):
		self.role = role

	def getRole(self):
		return self.role

	def getRbac(self):
		return self.rbac
	
	def setRbac(self, rbac):
		self.rbac = rbac
		
	def close(self):
		"""
			Close the current session.
		"""
		self.socket.close()


	def mustBeClosed(self):
		"""
			Return true if this session has to be closed after a <close-session> or <kill-session>.
			It means that the related socket must be closed also.
		
			@rtype: int
			@return: true if session must be closed.
		"""
		
		return self.mustBeClosed

