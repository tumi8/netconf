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


from Ft.Xml.Domlette import PrettyPrint, NonvalidatingReader
from Ft.Xml import XPath
import util, time, os
from constants import C

class Logger:

	instance = None
	
	def __init__(self):
		"""
			Creating new modules for Netconf
			This somewhat define the set of capabilities
			Building the Module Register Table (MRT)
		"""

		self.logFile = C.YENCAP_HOME + "/Modules/LogModule/log.xml"
		self.doc = NonvalidatingReader.parseUri("file:"+self.logFile)
		

	def increment(self, operation):
		"""
			Updates the total number of operation:
			get-config, get, edit-config, ...
			@type operation: String
			@param operation: Netconf operation (get-config, edit-config, ...)
		"""

		try:
			node = XPath.Evaluate('/log/nbOp/'+operation,self.doc)[0]
			oldTextNode = node.childNodes[0]
			nb = oldTextNode.nodeValue
			newnb = int(nb) + 1
			newTextNode = self.doc.createTextNode(str(newnb))
			node.removeChild(oldTextNode)
			node.appendChild(newTextNode)
			util.printNodeToFile(self.doc.documentElement, self.logFile)
			
		except Exception,exp:
			print str(exp)

	
	def updateSessionAverageTime(self, duration):
		"""
			Increment the number of sessions
			Increment in log.xml the average sessions time since agent is installed
			@type duration: int
			@param duration: Session processing time
		"""
		
		try:
			rnNode = XPath.Evaluate("/log/sessions/number",self.doc)[0]
			oldTextNode = rnNode.childNodes[0]
			nb = oldTextNode.nodeValue
			newnb = int(nb) + 1
			newTextNode = self.doc.createTextNode(str(newnb))
			rnNode.removeChild(oldTextNode)
			rnNode.appendChild(newTextNode)
			
			avgnode = XPath.Evaluate('/log/sessions/average-time',self.doc)[0]
			oldAvgNode = avgnode.childNodes[0]
			averageTime = float(oldAvgNode.nodeValue)
			newnb = (averageTime*(newnb-1) + duration)/newnb
			newAvgNode = self.doc.createTextNode(str(newnb))
			avgnode.removeChild(oldAvgNode)
			avgnode.appendChild(newAvgNode)
			util.printNodeToFile(self.doc.documentElement, self.logFile)
			
		except Exception,exp:
			print str(exp)

	def updateRequestAverageTime(self, duration):
		"""
			Increment in log.xml the average requests time since agent is installed
			@type duration: int
			@param duration: Request processing time
		"""

		try:
			rnNode = XPath.Evaluate("/log/requests/number",self.doc)[0]
			oldTextNode = rnNode.childNodes[0]
			nb = oldTextNode.nodeValue
			newnb = int(nb) + 1
			newTextNode = self.doc.createTextNode(str(newnb))
			rnNode.removeChild(oldTextNode)
			rnNode.appendChild(newTextNode)
			
			avgnode = XPath.Evaluate('/log/requests/average-time',self.doc)[0]
			oldAvgNode = avgnode.childNodes[0]
			averageTime = float(oldAvgNode.nodeValue)
			newnb = (averageTime*(newnb-1) + duration)/newnb
			newAvgNode = self.doc.createTextNode(str(newnb))
			avgnode.removeChild(oldAvgNode)
			avgnode.appendChild(newAvgNode)
			util.printNodeToFile(self.doc.documentElement, self.logFile)

		except Exception,exp:
			print str(exp)


	def updateFilteringAverageTime(self, method, duration):
		"""
			Increment in log.xml the filtering average request time since agent is installed
			@type method: string
			@param method: Filtering method (xpath or subtree)
			@type duration: int
			@param duration: Request processing time
		"""

		try:
			rnNode = XPath.Evaluate("/log/filtering/"+ method +"/number",self.doc)[0]
			oldTextNode = rnNode.childNodes[0]
			nb = oldTextNode.nodeValue
			newnb = int(nb) + 1
			newTextNode = self.doc.createTextNode(str(newnb))
			rnNode.removeChild(oldTextNode)
			rnNode.appendChild(newTextNode)
			
			avgnode = XPath.Evaluate("/log/filtering/"+ method +"/average-time",self.doc)[0]
			oldAvgNode = avgnode.childNodes[0]
			averageTime = float(oldAvgNode.nodeValue)
			newnb = (averageTime*(newnb-1) + duration)/newnb
			newAvgNode = self.doc.createTextNode(str(newnb))
			avgnode.removeChild(oldAvgNode)
			avgnode.appendChild(newAvgNode)
			util.printNodeToFile(self.doc.documentElement, self.logFile)
			
		except Exception,exp:
			print str(exp)
		

 
def getInstance():
	"""
		Logger is a Singleton
		Use getInstance method to get the unique Logger
		@rtype: Logger
		@return: Logger singleton instance
	"""
	
	if Logger.instance == None:
		Logger.instance = Logger()
	return Logger.instance

