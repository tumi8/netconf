from SOAPpy import SOAPServer
from M2Crypto import SSL


#from Servers.server import Server
from constants import C

##########################################
# This class must be implemented...      #
##########################################

class ServerSOAPHTTPS(SOAPServer):

	def __init__(self, ipVersion):
		ssl_context = SSL.Context()
		ssl_context.load_cert(certfile='ca.pem', keyfile='ca.key')
		SOAPServer.__init__(("localhost", C.NETCONF_SOAPHTTP_PORT), ssl_context = ssl_context)
		# Call super class (Server) constructor:
		#Server.__init__(self, ipVersion, C.NETCONF_SOAPHTTP_PORT)


	def authenticate(self, login, password):
		return True
		# TO DO...

	def hello():
		return "Hello World"








# This SOAP server is working over HTTPS as a standalone file. But it must be integrated to YencaP.
"""
from SOAPpy import SOAPServer
from M2Crypto import SSL


def hello():
	return "Hello World"

ssl_context = SSL.Context()
ssl_context.load_cert(certfile='ca.pem', keyfile='ca.key')

server = SOAPServer(("localhost", 832), ssl_context = ssl_context)
server.registerFunction(hello)
server.serve_forever()
"""
