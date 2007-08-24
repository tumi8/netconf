from routeV4 import RouteV4
import socket
import struct

def convert(ipv4string):
	dotted = ".".join([str(int(ipv4string[(2*i) : (2*i+2)],16)) for i in range(3, -1, -1)])
	return dotted

%%

parser Ipv4_Route_Parser:
    ignore: r"[ \t\r]"
    token EOF: "$"
    token EOL: r"\n"
    token NUM: r"[0-9]+"
    token HEXA4: r"[0-9A-F]{4}"
    token HEXA8: r"[0-9A-F]{8}"
    token STRING: r"[0-9a-zA-Z]+"
    
    rule goal: {{ routes = [] }}      (STRING)* EOL ROUTES<<routes>> EOF         {{ return routes }}

    # An expression is the sum and difference of factors
    rule ROUTES<<routes>>:       (ROUTE {{ routes.append(ROUTE) }})*

    # A factor is the product and division of terms
    rule ROUTE:     ifname target gateway flags ref use metric genmask mtu window irtt EOL
                    {{ return RouteV4(target, gateway, genmask, flags, metric, ref, use, ifname, mtu, window, irtt)   }}


    rule ifname: 		STRING {{ return STRING }}
    rule target: 		HEXA8  {{ return convert(HEXA8) }}
    rule gateway: 		HEXA8  {{ return convert(HEXA8) }}
    rule flags: 		HEXA4  {{ return HEXA4 }}
    rule ref: 			NUM    {{ return NUM }}
    rule use: 			NUM    {{ return NUM }}
    rule metric: 		NUM    {{ return NUM }}
    rule genmask: 		HEXA8  {{ return convert(HEXA8) }}
    rule mtu: 			NUM    {{ return NUM }}
    rule window: 		NUM    {{ return NUM }}
    rule irtt: 			NUM    {{ return NUM }}


