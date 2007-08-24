from RIP_Commands.Redistribute_RC import Redistribute_RC
from RIP_Commands.Network_RC import Network_RC
from RIP_Commands.Neighbor_RC import Neighbor_RC
from RIP_Commands.PassiveInterface_RC import PassiveInterface_RC
from RIP_Commands.Route_RC import Route_RC
from RIP_Commands.DistributeList_RC import DistributeList_RC



from string import *
import re
from yapps2lc.yappsrt import *

class RIP_ParserScanner(Scanner):
    patterns = [
        ('"/"', re.compile('/')),
        ('"2"', re.compile('2')),
        ('[ \\t\\r]', re.compile('[ \\t\\r]')),
        ('![\\-a-zA-Z0-9_! \\./]*[\\n]*', re.compile('![\\-a-zA-Z0-9_! \\./]*[\\n]*')),
        ('EOF', re.compile('$')),
        ('EOL', re.compile('\\n')),
        ('NUM', re.compile('[0-9]+')),
        ('ALPHANUM', re.compile('[0-9a-zA-Z]+')),
        ('STRING', re.compile('[\\-a-zA-Z0-9_!]*')),
        ('IP_ADDR', re.compile('((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)')),
        ('IP_ADDR', re.compile('((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)')),
        ('RIP', re.compile('rip')),
        ('ROUTER', re.compile('router')),
        ('VERSION', re.compile('version')),
        ('REDISTRIBUTE', re.compile('redistribute')),
        ('KERNEL', re.compile('kernel')),
        ('STATIC', re.compile('static')),
        ('OSPF', re.compile('ospf')),
        ('CONNECTED', re.compile('connected')),
        ('BGP', re.compile('bgp')),
        ('RIP', re.compile('rip')),
        ('METRIC', re.compile('metric')),
        ('ROUTE_MAP', re.compile('route-map')),
        ('NETWORK', re.compile('network')),
        ('NEIGHBOR', re.compile('neighbor')),
        ('PASSIVE_INTERFACE', re.compile('passive-interface')),
        ('DISTRIBUTE_LIST', re.compile('distribute-list')),
        ('IN', re.compile('in')),
        ('OUT', re.compile('out')),
        ('ROUTE', re.compile('route')),
    ]
    def __init__(self, str):
        Scanner.__init__(self,None,['[ \\t\\r]', '![\\-a-zA-Z0-9_! \\./]*[\\n]*'],str)

class RIP_Parser(Parser):
    def goal(self):
        commands = []
        RIP_MAIN = self.RIP_MAIN()
        RIP_COMMAND = self.RIP_COMMAND(commands)
        EOF = self._scan('EOF')
        return commands

    def RIP_MAIN(self):
        ROUTER = self._scan('ROUTER')
        RIP = self._scan('RIP')
        EOL = self._scan('EOL')

    def RIP_COMMAND(self, commands):
        while self._peek('VERSION', 'REDISTRIBUTE', 'NETWORK', 'NEIGHBOR', 'PASSIVE_INTERFACE', 'DISTRIBUTE_LIST', 'ROUTE', 'EOF') == 'VERSION':
            VERSION_RC = self.VERSION_RC()
        while self._peek('REDISTRIBUTE', 'NETWORK', 'NEIGHBOR', 'PASSIVE_INTERFACE', 'DISTRIBUTE_LIST', 'ROUTE', 'EOF') == 'REDISTRIBUTE':
            REDISTRIBUTE_RC = self.REDISTRIBUTE_RC()
            commands.append(REDISTRIBUTE_RC)
        while self._peek('NETWORK', 'NEIGHBOR', 'PASSIVE_INTERFACE', 'DISTRIBUTE_LIST', 'ROUTE', 'EOF') == 'NETWORK':
            NETWORK_RC = self.NETWORK_RC()
            commands.append(NETWORK_RC)
        while self._peek('NEIGHBOR', 'PASSIVE_INTERFACE', 'DISTRIBUTE_LIST', 'ROUTE', 'EOF') == 'NEIGHBOR':
            NEIGHBOR_RC = self.NEIGHBOR_RC()
            commands.append(NEIGHBOR_RC)
        while self._peek('PASSIVE_INTERFACE', 'DISTRIBUTE_LIST', 'ROUTE', 'EOF') == 'PASSIVE_INTERFACE':
            PASSIVE_IF_RC = self.PASSIVE_IF_RC()
            commands.append(PASSIVE_IF_RC)
        while self._peek('DISTRIBUTE_LIST', 'ROUTE', 'EOF') == 'DISTRIBUTE_LIST':
            DL_RC = self.DL_RC()
            commands.append(DL_RC)
        while self._peek('ROUTE', 'EOF') == 'ROUTE':
            ROUTE_RC = self.ROUTE_RC()
            commands.append(ROUTE_RC)

    def VERSION_RC(self):
        VERSION = self._scan('VERSION')
        self._scan('"2"')
        EOL = self._scan('EOL')

    def REDISTRIBUTE_RC(self):
        m=""
        r=""
        REDISTRIBUTE = self._scan('REDISTRIBUTE')
        SOURCE = self.SOURCE()
        while self._peek('METRIC', 'EOL', 'ROUTE_MAP') == 'METRIC':
            METRIC_PARAM = self.METRIC_PARAM()
            m=METRIC_PARAM
        while self._peek('EOL', 'ROUTE_MAP') == 'ROUTE_MAP':
            ROUTE_MAP_PARAM = self.ROUTE_MAP_PARAM()
            r=ROUTE_MAP_PARAM
        EOL = self._scan('EOL')
        return Redistribute_RC(SOURCE, metric=m, route_map=r)

    def NETWORK_RC(self):
        NETWORK = self._scan('NETWORK')
        NETWORK_VALUE = self.NETWORK_VALUE()
        EOL = self._scan('EOL')
        return Network_RC(NETWORK_VALUE)

    def NEIGHBOR_RC(self):
        NEIGHBOR = self._scan('NEIGHBOR')
        IP_ADDR = self._scan('IP_ADDR')
        EOL = self._scan('EOL')
        return Neighbor_RC(IP_ADDR)

    def PASSIVE_IF_RC(self):
        PASSIVE_INTERFACE = self._scan('PASSIVE_INTERFACE')
        IFNAME = self.IFNAME()
        EOL = self._scan('EOL')
        return PassiveInterface_RC(IFNAME)

    def DL_RC(self):
        DISTRIBUTE_LIST = self._scan('DISTRIBUTE_LIST')
        LISTNAME = self.LISTNAME()
        DIRECT = self.DIRECT()
        IFNAME = self.IFNAME()
        EOL = self._scan('EOL')
        return DistributeList_RC(LISTNAME, DIRECT, IFNAME)

    def ROUTE_RC(self):
        ROUTE = self._scan('ROUTE')
        IP_ADDR = self._scan('IP_ADDR')
        self._scan('"/"')
        NUM = self._scan('NUM')
        EOL = self._scan('EOL')
        return Route_RC(IP_ADDR + "/" + NUM)

    def IFNAME(self):
        ALPHANUM = self._scan('ALPHANUM')
        return ALPHANUM

    def LISTNAME(self):
        STRING = self._scan('STRING')
        return STRING

    def DIRECT(self):
        _token_ = self._peek('IN', 'OUT')
        if _token_ == 'IN':
            IN = self._scan('IN')
            return IN
        else:# == 'OUT'
            OUT = self._scan('OUT')
            return OUT

    def METRIC_PARAM(self):
        METRIC = self._scan('METRIC')
        NUM = self._scan('NUM')
        return NUM

    def ROUTE_MAP_PARAM(self):
        ROUTE_MAP = self._scan('ROUTE_MAP')
        ALPHANUM = self._scan('ALPHANUM')
        return ALPHANUM

    def SOURCE(self):
        _token_ = self._peek('KERNEL', 'OSPF', 'CONNECTED', 'BGP', 'RIP', 'STATIC')
        if _token_ == 'KERNEL':
            KERNEL = self._scan('KERNEL')
            return KERNEL
        elif _token_ == 'OSPF':
            OSPF = self._scan('OSPF')
            return OSPF
        elif _token_ == 'CONNECTED':
            CONNECTED = self._scan('CONNECTED')
            return CONNECTED
        elif _token_ == 'BGP':
            BGP = self._scan('BGP')
            return BGP
        elif _token_ == 'RIP':
            RIP = self._scan('RIP')
            return RIP
        else:# == 'STATIC'
            STATIC = self._scan('STATIC')
            return STATIC

    def NETWORK_VALUE(self):
        _token_ = self._peek('IP_ADDR', 'ALPHANUM')
        if _token_ == 'IP_ADDR':
            IP_ADDR = self._scan('IP_ADDR')
            self._scan('"/"')
            NUM = self._scan('NUM')
            return (IP_ADDR + "/" + NUM)
        else:# == 'ALPHANUM'
            IFNAME = self.IFNAME()
            return (IFNAME)


def parse(rule, text):
    P = RIP_Parser(RIP_ParserScanner(text))
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
