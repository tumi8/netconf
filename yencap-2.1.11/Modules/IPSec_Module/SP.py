class SP:

	def __init__(self,src,dst,upperspec,policy):
		self._src_range=src
		self._dst_range=dst
		self._upperspec=upperspec
		self._policy=policy
	
	def get_src_range(self):
		return self._src_range
	def get_dst_range(self):
		return self._dst_range
	def get_upperspec(self):
		return self._upperspec
	def get_policy(self):
		return self._policy
	
	def serialize(self,doc,NS):

		SP_node=doc.createElementNS(NS,"SP")

		SP_child=doc.createElementNS(NS,"src_range")
		textNode = doc.createTextNode(self.get_src_range())
		SP_child.appendChild(textNode)
		SP_node.appendChild(SP_child)
		
		SP_child=doc.createElementNS(NS,"dst_range")
		textNode = doc.createTextNode(self.get_dst_range())
		SP_child.appendChild(textNode)
		SP_node.appendChild(SP_child)

		SP_child=doc.createElementNS(NS,"upperspec")
		textNode = doc.createTextNode(self.get_upperspec())
		SP_child.appendChild(textNode)
		SP_node.appendChild(SP_child)	
		
		SP_child=doc.createElementNS(NS,"policy")
		textNode = doc.createTextNode(self.get_policy())
		SP_child.appendChild(textNode)
		SP_node.appendChild(SP_child)		
			
		return SP_node
