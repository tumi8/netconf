from Ft.Xml.Domlette import NonvalidatingReader, implementation
from Ft.Xml import XPath, EMPTY_NAMESPACE
from Ft.Xml.Domlette import NonvalidatingReader, PrettyPrint

import string
from xml.dom.minidom import Document
import commands

from SA import SA
from SP import SP
import Parser_SAD
import Parser_SPD

class IPSec:
	#cette classe est comme la classe SAD_SPD; la seule difference est la valeur de retour de serialze()
	def __init__(self,namespace):
		self.namespace=namespace

		commandeSA="/sbin/setkey -D"
		commandeSP="/sbin/setkey -DP"
		texteSA=commands.getoutput(commandeSA)
		texteSP=commands.getoutput(commandeSP)
		#il peut avoir des problemes de droit d'acces aux entrees SA et SP
		#ou bien la SAD et la SPD peuvent etre vides

		self.SAs=None
		self.SPs=None
		try:
			self.SAs=Parser_SAD.parse("goal",texteSA+'\n') #j'ai remarque que si l'on ne rajoute pas '\n' a texteSA le parsing echoue. il ne reconnait pas EOF (End Of File). De meme pour texteSP
		except: print 'contenu SAD (resultat de setkey -D):'+'\n'+texteSA
		
		try:
			self.SPs=Parser_SPD.parse("goal",texteSP+'\n')
		except:print 'contenu SPD (resultat de setkey -DP):'+'\n'+texteSP
		

	def serialize(self):
		doc = implementation.createDocument(self.namespace, None, None)
		#doc=Document()
		ipsec_element=doc.createElementNS(self.namespace,"ipsec")
		sad_element=doc.createElementNS(self.namespace,"sad")
		spd_element=doc.createElementNS(self.namespace,"spd")
		ipsec_element.appendChild(sad_element)
		ipsec_element.appendChild(spd_element)
		doc.appendChild(ipsec_element)
		if self.SAs:
			for sa in self.SAs:
				saNode=sa.serialize(doc,self.namespace)
				sad_element.appendChild(saNode)
		else:print 'No SAD entries.'

		if self.SPs:
			for sp in self.SPs:
				spNode=sp.serialize(doc,self.namespace)
				spd_element.appendChild(spNode)
		else:print 'No SPD entries.'

		#print doc.toprettyxml()
		return doc.documentElement
