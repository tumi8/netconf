###############################################################################
#                                                                             #
# YencaP software, LORIA-INRIA LORRAINE, MADYNES RESEARCH TEAM                #
# Copyright (C) 2005  Vincent CRIDLIG                                         #
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
#   Name : Vincent CRIDLIG                                                    #
#   Email: Vincent.Cridlig@loria.fr                                           #
#                                                                             #
###############################################################################

from RIP_Command import RIP_Command


class Redistribute_RC(RIP_Command):

	def __init__(self, source, metric="", route_map="", positive = True):
		self.name = "redistribute"
		if source in ["connected","kernel","ospf","bgp","static"]:
			self.source = source
		else:
			print "Error RIP module. Source must be one of connected, kernel, ospf, bgp, static"
		self.metric = metric
		self.route_map= route_map
		self.positive = positive
		

	def toCLI(self):
		if self.positive:
			tmp = ""
		else:
			tmp = "no "

		tmp = "%sredistribute %s" % (tmp, self.source)
		if (self.metric!=""):
			tmp = tmp + " metric " + self.metric
		if (self.route_map!=""):
			tmp = tmp + " route-map " + self.route_map
		return tmp


	def toXML(self, document, namespace):
		element = document.createElementNS(namespace, self.source)
		if (self.metric!=""):
			element.setAttributeNS(namespace, "metric", self.metric)
		if (self.route_map!=""):
			element.setAttributeNS(namespace, "route-map", self.route_map)
		return element

	def equals(self, command):
		if self.name == command.name:
			if self.source == command.source:
				return True
			else:
				return False
		else:
			return False

