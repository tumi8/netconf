from routeV6 import RouteV6
import socket

def convert(ipv6string):
	dotted = ":".join([ipv6string[(4*i) : (4*i+4)] for i in range(8)])
	hexa = socket.inet_pton(socket.AF_INET6, dotted)
	res = socket.inet_ntop(socket.AF_INET6, hexa)
	if (res=="::"):
		res = "*"
	return res

%%

parser Ipv6_Route_Parser:
    ignore: r"[ \t\r]"
    token EOF: "$"
    token EOL: r"\n"
    token NUM: r"[0-9]+"
    token HEXA32: r"[0-9a-f]{32}"
    token HEXA2: r"[0-9a-f]{2}"
    token HEXA8: r"[0-9a-f]{8}"
    token STRING: r"[0-9a-zA-Z]+"
    
    rule goal: {{ routes = [] }}        ROUTES<<routes>> EOF         {{ return routes }}

    # An expression is the sum and difference of factors
    rule ROUTES<<routes>>:       (ROUTE         {{ routes.append(ROUTE) }})*

    # A factor is the product and division of terms
    rule ROUTE:     target target_mask blub blub_mask next_hop metric use ref indic ifname EOL 
                    {{ return RouteV6(target, target_mask, blub, blub_mask, next_hop, metric, use, ref, indic, ifname)   }}

    rule target: 		HEXA32 {{ return convert(HEXA32) }}
    rule target_mask: 	HEXA2  {{ return int(HEXA2,16) }}
    rule blub: 			HEXA32 {{ return convert(HEXA32) }}
    rule blub_mask: 	HEXA2  {{ return int(HEXA2,16) }}
    rule next_hop: 		HEXA32 {{ return convert(HEXA32) }}
    rule metric: 		HEXA8  {{ return long(HEXA8,16) }}
    rule use: 			HEXA8  {{ return long(HEXA8,16) }}
    rule ref: 			HEXA8  {{ return long(HEXA8,16) }}
    rule indic: 		HEXA8  {{ return HEXA8 }}
    rule ifname: 		STRING {{ return STRING }}
