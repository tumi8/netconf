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


class RIP_Command:

	def __init__(self):
		pass

	def do(self, vty_conex, vty_conex_id):
		id,text = vty_conex.sendCommand(vty_conex_id, self.toCLI())
		if text != "":
			raise Exception(text)
			

	def undo(self, vty_conex, vty_conex_id):
		self.revert()
		id,text = vty_conex.sendCommand(vty_conex_id, self.toCLI())
		self.revert()

	def toCLI(self):
		pass

	def toXML(self, document):
		pass

	def revert(self):
		if self.positive:
			self.positive = False
		else:
			self.positive = True
