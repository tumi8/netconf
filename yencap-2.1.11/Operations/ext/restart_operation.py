from Operations.operation import Operation
from Operations.operationReply import OperationReply
from Modules.modulereply import ModuleReply
from Modules import ModuleManager
from xml.dom import Node

class Restart_operation(Operation): 

	""" 
		Operation for VERMONT restart-operation. Only option is the module to restart.
	"""

	def __init__(self): 
		""" Constructor of a Get_config_operation command. """ 
		self.targetModule = None
		self.moduleManager = ModuleManager.getInstance()

	def execute(self): 
		""" Execute the operation. """ 
		
		print "restart Operation executed"
		module = self.moduleManager.getModuleFromName(self.targetModule)
		if module == None:
			moduleReply = ModuleReply(
			error_type = ModuleReply.PROTOCOL,
			error_tag = ModuleReply.BAD_ELEMENT,
			error_severity = ModuleReply.ERROR,
			error_message = "No Module s% ."%self.targetModule)
		else:
			moduleReply = module.restart()
			
		self.operationReply.setNode(moduleReply.getXMLNodeReply())
		return self.operationReply

	def setParameters(self, operation, NSS = None): 
		""" 
			Set the targetModule to restart. 
		"""
 		for child in operation.childNodes:
			if child.nodeType==Node.ELEMENT_NODE:
				if child.tagName == "moduleName":
					for node in child.childNodes:
						if node.nodeType==Node.TEXT_NODE:
							self.targetModule = node.nodeValue

