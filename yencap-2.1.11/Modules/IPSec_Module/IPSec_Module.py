from Ft.Xml.Domlette import NonvalidatingReader, implementation
from Ft.Xml import XPath, EMPTY_NAMESPACE
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint
from xml.dom import Node

from Modules.modulereply import ModuleReply 
from Modules.module import Module 

from constants import C

class IPSec_Module(Module):
	def __init__(self, name, path, namespace, cacheLifetime,parameters):
		Module.__init__(self, name, path, namespace, cacheLifetime)

	def getConfig(self):
	#___
	modulereply = ModuleReply(replynode=self.doc.documentElement)
		return modulereply
	
	def editConfig(self, defaultoperation, testoption, erroroption, target, confignode, targetnode=None):

