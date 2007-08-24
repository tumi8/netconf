import string

class AddressV6:

	def __init__(self, liste):
		"""
			liste contains "address", "index", "prefix", "scope", "flag", "name"
		"""
		
		self.address = string.strip(liste[0])
		self.index = string.strip(liste[1])
		self.prefix = string.strip(liste[2])
		self.scope = string.strip(liste[3])
		self.flag = string.strip(liste[4])


