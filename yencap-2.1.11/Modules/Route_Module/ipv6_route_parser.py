from routeV6 import RouteV6
import socket

def convert(ipv6string):
	dotted = ":".join([ipv6string[(4*i) : (4*i+4)] for i in range(8)])
	hexa = socket.inet_pton(socket.AF_INET6, dotted)
	res = socket.inet_ntop(socket.AF_INET6, hexa)
	if (res=="::"):
		res = "*"
	return res


from string import *
import re
from yappsrt import *

class Ipv6_Route_ParserScanner(Scanner):
    patterns = [
        ('[ \\t\\r]', re.compile('[ \\t\\r]')),
        ('EOF', re.compile('$')),
        ('EOL', re.compile('\\n')),
        ('NUM', re.compile('[0-9]+')),
        ('HEXA32', re.compile('[0-9a-f]{32}')),
        ('HEXA2', re.compile('[0-9a-f]{2}')),
        ('HEXA8', re.compile('[0-9a-f]{8}')),
        ('STRING', re.compile('[0-9a-zA-Z]+')),
    ]
    def __init__(self, str):
        Scanner.__init__(self,None,['[ \\t\\r]'],str)

class Ipv6_Route_Parser(Parser):
    def goal(self):
        routes = []
        ROUTES = self.ROUTES(routes)
        EOF = self._scan('EOF')
        return routes

    def ROUTES(self, routes):
        while self._peek('EOF', 'HEXA32') == 'HEXA32':
            ROUTE = self.ROUTE()
            routes.append(ROUTE)

    def ROUTE(self):
        target = self.target()
        target_mask = self.target_mask()
        blub = self.blub()
        blub_mask = self.blub_mask()
        next_hop = self.next_hop()
        metric = self.metric()
        use = self.use()
        ref = self.ref()
        indic = self.indic()
        ifname = self.ifname()
        EOL = self._scan('EOL')
        return RouteV6(target, target_mask, blub, blub_mask, next_hop, metric, use, ref, indic, ifname)

    def target(self):
        HEXA32 = self._scan('HEXA32')
        return convert(HEXA32)

    def target_mask(self):
        HEXA2 = self._scan('HEXA2')
        return int(HEXA2,16)

    def blub(self):
        HEXA32 = self._scan('HEXA32')
        return convert(HEXA32)

    def blub_mask(self):
        HEXA2 = self._scan('HEXA2')
        return int(HEXA2,16)

    def next_hop(self):
        HEXA32 = self._scan('HEXA32')
        return convert(HEXA32)

    def metric(self):
        HEXA8 = self._scan('HEXA8')
        return long(HEXA8,16)

    def use(self):
        HEXA8 = self._scan('HEXA8')
        return long(HEXA8,16)

    def ref(self):
        HEXA8 = self._scan('HEXA8')
        return long(HEXA8,16)

    def indic(self):
        HEXA8 = self._scan('HEXA8')
        return HEXA8

    def ifname(self):
        STRING = self._scan('STRING')
        return STRING


def parse(rule, text):
    P = Ipv6_Route_Parser(Ipv6_Route_ParserScanner(text))
    return wrap_error_reporter(P, rule)

if __name__ == '__main__':
    from sys import argv, stdin
    if len(argv) >= 2:
        if len(argv) >= 3:
            f = open(argv[2],'r')
        else:
            f = stdin
        print parse(argv[1], f.read())
    else: print 'Args:  <rule> [<filename>]'
