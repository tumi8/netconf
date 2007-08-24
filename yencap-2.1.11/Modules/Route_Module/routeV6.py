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

class RouteV6:
	
	def __init__(self, target, target_mask, blub, blub_mask, next_hop, metric, use, ref, indic, ifname):
		self.target = target
		self.target_mask = target_mask
		self.blub = blub
		self.blub_mask = blub_mask
		self.next_hop = next_hop
		self.metric = metric
		self.use = use
		self.ref = ref
		self.indic = indic
		self.ifname = ifname

	def serialize(self, doc, namespace):
		
		entryelement = doc.createElementNS(namespace,"route")

		targetelement = doc.createElementNS(namespace,"target")
		targettext = doc.createTextNode(self.target + "/" + str(self.target_mask))
		targetelement.appendChild(targettext)
		entryelement.appendChild(targetelement)

		next_hopelement = doc.createElementNS(namespace,"next-hop")
		next_hoptext = doc.createTextNode(self.next_hop)
		next_hopelement.appendChild(next_hoptext)
		entryelement.appendChild(next_hopelement)
		
		indicelement = doc.createElementNS(namespace,"flags")
		indictext = doc.createTextNode(self.indic)
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


