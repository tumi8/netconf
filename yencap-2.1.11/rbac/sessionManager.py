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


import rbacManager, logger, time
from session import Session
from Modules.modulereply import ModuleReply
from constants import C

class SessionManager:
	
	instance = None
	
	def __init__(self):
		"""
			Initializes the SessionManager attributes: nextSessionId and sessions list.
		"""

		self.nextSessionId = 1
		self.sessions = []

	def getLockOwnerSessionId(self, target):
		"""
			Retrieve the lock owner of a target configuration (One of RUNNING, CANDIDATE, STARTUP)
			Return -1 if no one owns the lock for that target.
			@type  target: String
			@param target: The target
			@rtype: Session
			@return: the session Id
		"""

		for session in self.sessions:
			if session.hasLock(target):
				return session.getSessionId()
		
		return -1


	def lock(self, session, target):
		"""
			Try to lock a target configuration (One of RUNNING, CANDIDATE, STARTUP)
			@type  session: Session
			@param session: The current session that tries to acquire the lock
			@type  target: String
			@param target: The target to lock
			@rtype: moduleReply
			@return: A module reply
		"""
		
		sessionId = self.getLockOwnerSessionId(target)
		
		if sessionId >= 0 :
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.IN_USE,
			error_severity = ModuleReply.ERROR,
			error_message = "Lock failed, lock is already held")
			moduleReply.addErrorInfo(C.SESSION_ID,str(sessionId))
		else:
			session.lock(target)
			moduleReply = ModuleReply()

		return moduleReply.getXMLNodeReply()


	def unlock(self, session, target):
		"""
			Try to unlock a target configuration (One of RUNNING, CANDIDATE, STARTUP)
			@type  session: Session
			@param session: The current session that tries to acquire the lock
			@type  target: String
			@param target: The target to unlock
			@rtype: moduleReply
			@return: A module reply
		"""
		
		sessionId = self.getLockOwnerSessionId(target)
		
		if sessionId == session.getSessionId() :
			session.unlock(str(target))
			moduleReply = ModuleReply()
		elif sessionId < 0:
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "The specified target (%s) is not locked." % str(target))
		elif sessionId != session.getSessionId :
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.OPERATION_FAILED,
			error_severity = ModuleReply.ERROR,
			error_message = "Unlock failed, another session holds the lock.")
			moduleReply.addErrorInfo(C.SESSION_ID,str(sessionId))

		return moduleReply.getXMLNodeReply()


	def createSession(self, clientsock, username):
		"""
			Creates a new session and appends it to the sessions list.
			@type  socket: socket
			@param socket: The socket for the current client
			@rtype: Session
			@return: The newly created Session
		"""
		user = rbacManager.getInstance().getUserFromLogin(username)
		session = Session(self.nextSessionId, clientsock, float(time.time()), user)
		self.sessions.append(session)
		self.nextSessionId = self.nextSessionId + 1
		return session


	def getSessionFromId(self, sessionId):
		for session in self.sessions:
			if session.getSessionId() == sessionId:
				return session
		return None


	def closeSessionFromId(self, sessionId):
		session = self.getSessionFromId(sessionId)
		self.closeSession(session)


	def closeSession(self, session):
		"""
			Closes the session. Removes the session from the sessions list.
			@type  session: Session
			@param session: The session to be closed
		"""
		
		session.close()
		self.sessions.remove(session)



def getInstance():
	"""
		SessionManager is a Singleton
		Use getInstance method to get the unique SessionManager
		@rtype: SessionManager
		@return: SessionManager singleton instance
	"""

	if SessionManager.instance == None:
		SessionManager.instance = SessionManager()
	return SessionManager.instance

