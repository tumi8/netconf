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


from Ft.Xml.Domlette import NonvalidatingReader, implementation
from Ft.Xml import XPath
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
import string, ipv6_route_parser, socket, struct


class RouteManagerV6:

	
	def __init__(self, namespace):
		self.namespace = namespace
		f = open('/proc/net/ipv6_route','r')
		self.routes = ipv6_route_parser.parse("goal", f.read())
		

	def serialize(self):
		doc = implementation.createDocument(self.namespace, None, None)
		element = doc.createElementNS(self.namespace,"routes")
		element.setAttributeNS(self.namespace, "ip-version", "IPv6")
		doc.appendChild(element)
		
		for route in self.routes:
			
			routeEntryNode = route.serialize(doc, self.namespace)
			element.appendChild(routeEntryNode)
		
		return doc.documentElement


	def dump(self):
		
		PrettyPrint(self.serialize())

