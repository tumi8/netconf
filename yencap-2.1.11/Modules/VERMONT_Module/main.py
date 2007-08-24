import VERMONT_Module
from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from xml.dom.minidom import parse, Element, Node

moduleInstanz = VERMONT_Module.VERMONT_Module( "VERMONT_Module", "/ycp:netconf/ycp:monitoring/mon:ipfixConfig", "urn:urn:company:yencap:module:vermont:1.0", 100000, [] )
#print "Hole Config:"
#m = moduleInstanz.getConfig('startup')

#PrettyPrint(m.getXMLNodeReply())

testconfig = """
<ipfixConfig xmlns='urn:ietf:params:xml:ns:ipfix-config' xmlns:xc='ietf:params:xml:ns:netconf:base:1.0'>
	<observationPoint id="5">
		<observationDomain>123</observationDomain>
		<type>pcap</type>
		<parameters>eth0</parameters>
	</observationPoint>
</ipfixConfig>	
"""
#print "Die neue Konfiguration:"
#print testconfig
#confignode = NonvalidatingReader.parseString(testconfig, 'urn:ietf:params:xml:ns:ipfix-config')

defaultoperation = "replace"
testoption = None
erroroption = "stop-on-error"
target = "startup"

#m = moduleInstanz.validate("startup")
#PrettyPrint(m.getXMLNodeReply())
#m = moduleInstanz.editConfig(defaultoperation, testoption, erroroption, target, confignode, targetnode=None)

#PrettyPrint(m.getXMLNodeReply())
#print "Nochmal ausgeben:"
#m = moduleInstanz.getConfig('startup')

#PrettyPrint(m.getXMLNodeReply())

#m = moduleInstanz.deleteConfig('candidate')
#PrettyPrint(m.getXMLNodeReply())
#m = moduleInstanz.restart()
#PrettyPrint(m.getXMLNodeReply())

m = moduleInstanz.restart()
PrettyPrint(m.getXMLNodeReply())
