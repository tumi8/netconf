from routeV4 import RouteV4
import socket
import struct

def convert(ipv4string):
	dotted = ".".join([str(int(ipv4string[(2*i) : (2*i+2)],16)) for i in range(3, -1, -1)])
	return dotted


from string import *
import re
from yappsrt import *

class Ipv4_Route_ParserScanner(Scanner):
    patterns = [
        ('[ \\t\\r]', re.compile('[ \\t\\r]')),
        ('EOF', re.compile('$')),
        ('EOL', re.compile('\\n')),
        ('NUM', re.compile('[0-9]+')),
        ('HEXA4', re.compile('[0-9A-F]{4}')),
        ('HEXA8', re.compile('[0-9A-F]{8}')),
        ('STRING', re.compile('[0-9a-zA-Z]+')),
    ]
    def __init__(self, str):
        Scanner.__init__(self,None,['[ \\t\\r]'],str)

class Ipv4_Route_Parser(Parser):
    def goal(self):
        routes = []
        while self._peek('STRING', 'EOL') == 'STRING':
            STRING = self._scan('STRING')
        EOL = self._scan('EOL')
        ROUTES = self.ROUTES(routes)
        EOF = self._scan('EOF')
        return routes

    def ROUTES(self, routes):
        while self._peek('EOF', 'STRING') == 'STRING':
            ROUTE = self.ROUTE()
            routes.append(ROUTE)

    def ROUTE(self):
        ifname = self.ifname()
        target = self.target()
        gateway = self.gateway()
        flags = self.flags()
        ref = self.ref()
        use = self.use()
        metric = self.metric()
        genmask = self.genmask()
        mtu = self.mtu()
        window = self.window()
        irtt = self.irtt()
        EOL = self._scan('EOL')
        return RouteV4(target, gateway, genmask, flags, metric, ref, use, ifname, mtu, window, irtt)

    def ifname(self):
        STRING = self._scan('STRING')
        return STRING

    def target(self):
        HEXA8 = self._scan('HEXA8')
        return convert(HEXA8)

    def gateway(self):
        HEXA8 = self._scan('HEXA8')
        return convert(HEXA8)

    def flags(self):
        HEXA4 = self._scan('HEXA4')
        return HEXA4

    def ref(self):
        NUM = self._scan('NUM')
        return NUM

    def use(self):
        NUM = self._scan('NUM')
        return NUM

    def metric(self):
        NUM = self._scan('NUM')
        return NUM

    def genmask(self):
        HEXA8 = self._scan('HEXA8')
        return convert(HEXA8)

    def mtu(self):
        NUM = self._scan('NUM')
        return NUM

    def window(self):
        NUM = self._scan('NUM')
        return NUM

    def irtt(self):
        NUM = self._scan('NUM')
        return NUM


def parse(rule, text):
    P = Ipv4_Route_Parser(Ipv4_Route_ParserScanner(text))
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
