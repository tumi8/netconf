class SA:
	def __init__(self,src,dst,protocol,extensions,spi,algo):
		self._src_address=src
		self._dst_address=dst
		self._protocol=protocol
		self._extensions=extensions
		self._spi_number=spi
		self._algorithm=algo
		

	def get_src_address(self):
		return self._src_address
	def get_dst_address(self):
		return self._dst_address
	def get_protocol(self):
		return self._protocol
	def get_extensions(self):
		return self._extensions
	def get_spi_number(self):
		return self._spi_number
	def get_algorithm(self):
		return self._algorithm
	
	def serialize(self,doc,NS):

		SA_node=doc.createElementNS(NS,"SA")

		SA_child=doc.createElementNS(NS,"src_address")
		textNode = doc.createTextNode(self.get_src_address())
		SA_child.appendChild(textNode)
		SA_node.appendChild(SA_child)
		
		SA_child=doc.createElementNS(NS,"dst_address")
		textNode = doc.createTextNode(self.get_dst_address())
		SA_child.appendChild(textNode)
		SA_node.appendChild(SA_child)

		SA_child=doc.createElementNS(NS,"protocol")
		textNode = doc.createTextNode(self.get_protocol())
		SA_child.appendChild(textNode)
		SA_node.appendChild(SA_child)		
		
		SA_child=doc.createElementNS(NS,"extensions_mode")
		textNode = doc.createTextNode(self.get_extensions())
		SA_child.appendChild(textNode)
		SA_node.appendChild(SA_child)

		SA_child=doc.createElementNS(NS,"spi_number")
		textNode = doc.createTextNode(self.get_spi_number())
		SA_child.appendChild(textNode)
		SA_node.appendChild(SA_child)

		SA_child=doc.createElementNS(NS,"algorithm")
		textNode = doc.createTextNode(self.get_algorithm())
		SA_child.appendChild(textNode)
		SA_node.appendChild(SA_child)

		
		return SA_node
