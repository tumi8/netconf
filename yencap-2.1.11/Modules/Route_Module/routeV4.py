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
from Ft.Xml import XPath, EMPTY_NAMESPACE
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
import string

class RouteV4:
	
	def __init__(self, target, gateway, genmask, flags, metric, ref, use, ifname, mtu, window, irtt):
		self.target = target
		self.gateway = gateway
		self.genmask = genmask
		self.flags = flags
		self.metric = metric
		self.ref = ref
		self.use = use
		self.ifname = ifname

	def serialize(self, doc, namespace):
		
		entryelement = doc.createElementNS(namespace,"route")
		

		targetelement = doc.createElementNS(namespace,"target")
		targettext = doc.createTextNode(self.target)
		targetelement.appendChild(targettext)
		entryelement.appendChild(targetelement)

		gatewayelement = doc.createElementNS(namespace,"gateway")
		gatewaytext = doc.createTextNode(self.gateway)
		gatewayelement.appendChild(gatewaytext)
		entryelement.appendChild(gatewayelement)
		
		genmaskelement = doc.createElementNS(namespace,"genmask")
		genmasktext = doc.createTextNode(self.genmask)
		genmaskelement.appendChild(genmasktext)
		entryelement.appendChild(genmaskelement)

		indicelement = doc.createElementNS(namespace,"flags")
		indictext = doc.createTextNode(self.flags)
		indicelement.appendChild(indictext)
		entryelement.appendChild(indicelement)

		metricelement = doc.createElementNS(namespace,"metric")
		metrictext = doc.createTextNode(str(self.metric))
		metricelement.appendChild(metrictext)
		entryelement.appendChild(metricelement)
		
		refelement = doc.createElementNS(namespace,"ref")
		reftext = doc.createTextNode(str(self.ref))
		refelement.appendChild(reftext)
		entryelement.appendChild(refelement)
		
		useelement = doc.createElementNS(namespace,"use")
		usetext = doc.createTextNode(str(self.use))
		useelement.appendChild(usetext)
		entryelement.appendChild(useelement)
		
		ifnameelement = doc.createElementNS(namespace,"ifname")
		ifnametext = doc.createTextNode(self.ifname)
		ifnameelement.appendChild(ifnametext)
		entryelement.appendChild(ifnameelement)
		
		
		return entryelement

