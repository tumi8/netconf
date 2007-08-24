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
#   Name : Humberto ABDELNUR, Vincent CRIDLIG                                 #
#   Email: Humberto.Abdelnur@loria.fr, Vincent.Cridlig@loria.fr               #
#                                                                             #
###############################################################################

import threading, base64
import paramiko
from paramiko import ServerInterface
from rbac import rbacManager


class SSHServerModule (ServerInterface):
	
	def __init__(self):
		self.event = threading.Event()


	def check_channel_request(self, kind, chanid):
		if kind == 'session':
			return paramiko.OPEN_SUCCEEDED
		return paramiko.OPEN_FAILED_ADMINISTRATIVELY_PROHIBITED


	def check_auth_password(self, username, password):
		"""
			Password-based Authentication.
		"""

		# Get the singleton instance of the RBAC manager
		rm = rbacManager.getInstance()

		# Use RBAC manager to retrieve the User object from its login
		u = rm.getUserFromLogin(username)

		if u == None:
			# If user does not exist, authentication must fail:
			return paramiko.AUTH_FAILED
		else:
			# Else compare the known password with the received one:
			if (password == u.password):
				self.user = username
				return paramiko.AUTH_SUCCESSFUL
			else:
				return paramiko.AUTH_FAILED


	def check_auth_publickey(self, username, key):
		"""
			PKI-based Authentication.
		"""

		# Get the singleton instance of the RBAC manager
		rm = rbacManager.getInstance()

		# Use RBAC manager to retrieve the User object from its login
		u = rm.getUserFromLogin(username)
		
		if u == None:
			# If user does not exist, authentication must fail:
			return paramiko.AUTH_FAILED
		else:
			# If user public key does not exist in the RBAC manager, authentication must fail:
			if u.publickey == "":
				return paramiko.AUTH_FAILED
			else:
				# Build the known manager public key object from the RBAC manager.
				if u.publickeytype == "rsa":
					manager_public_key = paramiko.RSAKey(data=base64.decodestring(u.publickey))
				elif u.publickeytype == "dss":
					manager_public_key = paramiko.DSSKey(data=base64.decodestring(u.publickey))
				else:
					return paramiko.AUTH_FAILED

				# Compare the known public key with the received one:
				if (key == manager_public_key):
					self.user = username
					return paramiko.AUTH_SUCCESSFUL
				else:
					return paramiko.AUTH_FAILED


	def get_allowed_auths(self, username):
		"""
			Define the allowed authentication methods.
		"""

		# password-based and public key-based authentications are allowed:
		return 'password,publickey'


	def check_channel_subsystem_request(self, channel, name):
		"""
			Check that the channel subsystem is netconf
		"""

		if (name == "netconf"):
			self.event.set()
			return True
		return False
			


