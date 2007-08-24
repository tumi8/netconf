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

import os, time, string, util
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint, implementation
from Ft.Xml import XPath, EMPTY_NAMESPACE
from constants import C
from xml.dom import Node, XMLNS_NAMESPACE
from role import Role
from user import User
from permission import Permission
from Modules.modulereply import ModuleReply
from Filters.xpathFilter import XpathFilter
from Ft.Xml.XPath import Evaluate
from Ft.Xml.XPath.Context import Context
from Modules import DatastoreManager


class RbacManager:

	instance = None
	
	def __init__(self):
		"""
			Instantiates a new RBAC manager.
		"""
		self.rbacNS = "urn:loria:madynes:ensuite:yencap:module:RBAC:1.0"
		self.active = True
		self.refreshData(C.STARTUP)
		
	

	def setActive(self, active):
		self.active = active


	def isActive(self):
		return self.active


	def refreshData(self, sourceName):
		"""
			This method must be called when the RBAC data has been modified
			using Netconf for instance. It parses the rbac.xml file and build a DOM document
		"""

		self.users = []
		self.roles = []
		self.permissions = []

		sourcefilePath = "%s/%s-%s.xml" % (C.YENCAP_CONF_HOME, "RBAC", sourceName)
		destfilePath = "%s/%s-%s.xml" % (C.YENCAP_CONF_HOME, "RBAC", C.RUNNING)
		os.system("cp %s %s" % (sourcefilePath, destfilePath))

		self.rbacdoc = NonvalidatingReader.parseUri("file:"+sourcefilePath).documentElement
		self.buildRbac()


	def buildRbac(self):
		self.prefixes = {}
		"""for att in self.rbacdoc.attributes:
			a, p = att
			value = self.rbacdoc.getAttributeNS(XMLNS_NAMESPACE, str(p))
			self.prefixes[str(p)] = value"""

		for child in self.rbacdoc.childNodes:
			
			if child.nodeType==Node.ELEMENT_NODE:
				if child.tagName == "prefixes":
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName == "prefix":
								name = node.getAttributeNS(EMPTY_NAMESPACE,'name')
								value = node.getAttributeNS(EMPTY_NAMESPACE,'value')
								self.prefixes[name] = value

				if child.tagName == C.USERS:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName == C.USER:
								userId = node.getAttributeNS(EMPTY_NAMESPACE,'id')
								login = ""
								password = ""
								publickey = ""
								publickeytype = ""
								for nodeee in node.childNodes:
									if nodeee.nodeType==Node.ELEMENT_NODE:
										if nodeee.tagName == C.LOGIN:
											tmpval = nodeee.childNodes[0].nodeValue
											login = string.strip(str(tmpval))
										if nodeee.tagName == C.PASSWORD:
											tmpval = nodeee.childNodes[0].nodeValue
											password = string.strip(str(tmpval))
										if nodeee.tagName == C.PUBLIC_KEY:
											tmpval = nodeee.childNodes[0].nodeValue
											publickey = string.strip(str(tmpval))
											tmpval = nodeee.getAttributeNS(EMPTY_NAMESPACE,'keytype')
											publickeytype = string.strip(str(tmpval))

								user = User(userId, login, password, publickey, publickeytype)
								self.users.append(user)

				if child.tagName == C.ROLES:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName == C.ROLE:
								roleId=""
								roleName=""
								juniorRolesId=[]
								roleId = node.getAttributeNS(EMPTY_NAMESPACE,'id')
								for nodeee in node.childNodes:
									if nodeee.nodeType == Node.ELEMENT_NODE:
										if nodeee.tagName == C.NAME:
											tmpval = nodeee.childNodes[0].nodeValue
											name = string.strip(str(tmpval))
											roleName = name
										if nodeee.tagName == C.JUNIOR_ROLES:
											juniorRoleIds = []
											for n in nodeee.childNodes:
												if n.nodeType == Node.ELEMENT_NODE:
													if n.tagName == C.JUNIOR_ROLE:
														jrid = n.getAttributeNS(EMPTY_NAMESPACE,'roleRef')
														juniorRolesId.append(jrid)
								role = Role(roleId, roleName, juniorRolesId)
								self.roles.append(role)
								

				if child.tagName == C.PERMISSIONS:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName == C.PERMISSION:
								for nodeee in node.childNodes:
									if nodeee.nodeType==Node.ELEMENT_NODE:
										if nodeee.tagName == C.SCOPE:
											tmpval = nodeee.childNodes[0].nodeValue
											scope = string.strip(str(tmpval))
											att_id = node.getAttributeNS(EMPTY_NAMESPACE,'id')
											att_op = node.getAttributeNS(EMPTY_NAMESPACE,'op')
											permission = Permission(att_id, att_op, scope)
											self.permissions.append(permission)
				
				if child.tagName == C.USER_ASSIGNEMENTS:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName == C.USER_ASSIGNEMENT:
								roleId = node.getAttributeNS(EMPTY_NAMESPACE,'roleRef')
								userId = node.getAttributeNS(EMPTY_NAMESPACE,'userRef')
								role = self.getRoleFromId(roleId)
								user = self.getUserFromId(userId)
								role.addUser(user)

				if child.tagName == C.PERMISSION_ASSIGNEMENTS:
					for node in child.childNodes:
						if node.nodeType==Node.ELEMENT_NODE:
							if node.tagName == C.PERMISSION_ASSIGNEMENT:
								roleId = node.getAttributeNS(EMPTY_NAMESPACE,'roleRef')
								permId = node.getAttributeNS(EMPTY_NAMESPACE,'permRef')
								role = self.getRoleFromId(roleId)
								perm = self.getPermFromId(permId)
								if role != None and perm != None:
									role.addPermission(perm)
								else:
									pass
									#print a warning because rbac.xml is not valid. A role or a permission referenced from permission-assignement does not exist.

		

	def getUserFromLogin(self, userName):
		for user in self.users:
			if user.login == userName:
				return user
		return None

	def getRoleFromName(self, roleName):
		for role in self.roles:
			if role.name == roleName:
				return role
		return None

	def getUserFromId(self, userId):
		for user in self.users:
			if user.userId == userId:
				return user
		return None

	def getRoleFromId(self, roleId):
		for role in self.roles:
			if role.roleId == roleId:
				return role
		return None

	def getPermFromId(self, permId):
		for perm in self.permissions:
			if perm.permissionId == permId:
				return perm
		return None

	def getAllowedRoles(self, user):
		l = []
		for role in self.roles:
			if user in role.users:
				l.append(role)
				# JuniorRoles are allowed also.
				l = l + self.getAllJuniorRoles(role)
				# Keep only one occurence of each role
				for elem in l:
					if l.count(elem) > 1:
						l.remove(elem)
		return l


	def getAllJuniorRoles(self, role):
		"""
			return a list containing role and its recursive junior roles
		"""
		tmp = []
		res = [role]
		# Get direct junior roles of role
		for roleId in role.juniorRoleIds:
			juniorRole = self.getRoleFromId(roleId)
			tmp.append(juniorRole)
		# Get junior
		for juniorRole in tmp:
			res = res + self.getAllJuniorRoles(juniorRole)
		
		# Keep only one occurence of each role
		for elem in res:
			if res.count(elem) > 1:
				res.remove(elem)
		return res

	
	def activate(self, session, roles):
		moduleReply = None
		allowedRoles = self.getAllowedRoles(session.user)

		for role in roles:
			if (role in allowedRoles):
				if (role not in session.roles):
					session.roles.append(role)
				else:
					moduleReply = ModuleReply(
					error_type = ModuleReply.PROTOCOL,
					error_tag = ModuleReply.PARTIAL_OPERATION,
					error_severity = ModuleReply.WARNING,
					error_message = "Role " + role.name + " is already active. Some other roles may have been activated.")
					moduleReply.addErrorInfo("bad-element",role.name)
			else:
				moduleReply = ModuleReply(
				error_type = ModuleReply.PROTOCOL,
				error_tag = ModuleReply.PARTIAL_OPERATION,
				error_severity = ModuleReply.WARNING,
				error_message = "User "+ session.user.login +" can not activate role " + role.name + ". Some other roles may have been activated.")
				moduleReply.addErrorInfo("bad-element",role.name)

		if moduleReply == None:
			moduleReply = ModuleReply()

		return moduleReply.getXMLNodeReply()


	def deactivate(self, session, roles):
		for role in roles:
			if (role in session.roles):
				session.roles.remove(role)

		moduleReply = ModuleReply()
		return moduleReply.getXMLNodeReply()


	def getActiveScopes(self, session, op):
		xpathList = []
		allRoles = []
		for role in session.roles:
			allRoles = allRoles + self.getAllJuniorRoles(role)

		for role in allRoles:
			for permission in role.permissions:
				if permission.hasOp(op):
					xpathList.append(permission.scope)
		# BUG: remove double entries here
		return xpathList


	def filterNode(self, node, session):
		scopes = self.getActiveScopes(session, "r")
		abstractFilter = XpathFilter(node, scopes, NSS = self.prefixes)
		moduleReply = abstractFilter.applyFilter()
		return moduleReply


	def checkAccessEditConfig(self, config, session):
		scopes = self.getActiveScopes(session, "w")
		
		# Building the list of authorized nodes
		authorized_nodes = []
		for scope in scopes:
			try:
				ctx = Context(config.documentElement, processorNss = self.prefixes)
				l = Evaluate(scope, ctx)
				for node in l:
					# Il faudrait verifier que 
					#	- ce noeud n'est pas deja dans la liste pour eviter les doublons
					#	- ce n'est pas un fils d'un noeud deja present dans la liste. Car si les droits d'acces en lecture sont donnes pour un parent, ca n'apprend rien de plus d'ajouter ce noeud
					#	- si ce noeud est un parent d'un noeud de la liste, il faut degager l'enfant car il ne sert plus a rien.
					# A FINIR DONC...
					authorized_nodes.append(node)
			except Exception,exp:
				print str(exp)
		
		if len(authorized_nodes) == 0:
			return False

		# Build parent nodes list
		parentNodes = []
		for node in authorized_nodes:
			# Check that we are not a child of another authorized node.
			# If yes, forget about it since we have better privileges with the other one.
			boolean = False
			parent = node.parentNode
			while parent != None and not boolean:
				if parent in authorized_nodes:
					boolean = True
				parent = parent.parentNode

			if not boolean:
				parent = node.parentNode
				while parent != None:
					if ((parent not in parentNodes) and (parent != config)):
						parentNodes.append(parent)
					parent = parent.parentNode
		#print "parentNodes"
		#print parentNodes

		# Check that there is no replace or delete or create in the parentNodes
		for node in parentNodes:
			tmp = node.getAttributeNodeNS(C.NETCONF_XMLNS, 'operation')
			if ((tmp != None) and (tmp.value in ['merge', 'replace', 'create', 'delete'])):
				return False

		# Build children nodes list
		childrenNodes = []
		for authorized_node in authorized_nodes:
			if authorized_node not in childrenNodes:
				childrenNodes.extend(self.buildChildrenSet(authorized_node))
		#print "childrenNodes"
		#print childrenNodes
		
		# Build global list (Union of authorized_nodes, parentNodes, childrenNodes)
		unionNodes = []
		for node in authorized_nodes:
			if (node not in unionNodes):
				unionNodes.append(node)
		for node in parentNodes:
			if (node not in unionNodes):
				unionNodes.append(node)
		for node in childrenNodes:
			if (node not in unionNodes):
				unionNodes.append(node)
		#print "unionNodes"
		#print unionNodes

		# Parcourir l'enszemble des noeuds de config et chercher d'eventuels noeuds qui n'appartiennent pas a globalList
		res = self.checkList(config.documentElement, unionNodes)
		return res


	def buildChildrenSet(self, node):
		result = []

		for item in node.childNodes:
			result.append(item)
			result.extend(self.buildChildrenSet(item))
		
		return result


	def checkList(self, node, unionList):
		if (node in unionList):
			for child in node.childNodes:
				tmp = self.checkList(child, unionList)
				if not tmp:
					return False
		else:
			# Because parents have children that are tab or space text nodes
			if node.nodeType != Node.TEXT_NODE:
				#print unionList
				#print node
				#print node.parentNode
				#print (node in unionList)
				return False

		return True

	
	def checkAccessCopyConfig(self, session):

		config = DatastoreManager.getInstance().getConfig(C.RUNNING, "config")
		scopes = self.getActiveScopes(session, "w")
		
		# Building the list of authorized nodes
		authorized_nodes = []
		for scope in scopes:
			try:
				ctx = Context(config.documentElement, processorNss = self.prefixes)
				l = Evaluate(scope, ctx)
				for node in l:
					# Il faudrait verifier que 
					#	- ce noeud n'est pas deja dans la liste pour eviter les doublons
					#	- ce n'est pas un fils d'un noeud deja present dans la liste. Car si les droits d'acces en lecture sont donnes pour un parent, ca n'apprend rien de plus d'ajouter ce noeud
					#	- si ce noeud est un parent d'un noeud de la liste, il faut degager l'enfant car il ne sert plus a rien.
					# A FINIR DONC...
					authorized_nodes.append(node)
			except Exception,exp:
				print str(exp)
		
		if len(authorized_nodes) == 0:
			return False

		if config.documentElement in authorized_nodes:
			return True
		else:
			return False


	def checkAccessDeleteConfig(self, session):

		return self.checkAccessCopyConfig(session)



def getInstance():
	"""
		RbacManager is a Singleton
		Use getInstance method to get the unique RbacManager
	"""

	if RbacManager.instance == None:
		RbacManager.instance = RbacManager()
	return RbacManager.instance

