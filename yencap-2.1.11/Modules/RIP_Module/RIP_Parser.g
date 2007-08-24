from RIP_Commands.Redistribute_RC import Redistribute_RC
from RIP_Commands.Network_RC import Network_RC
from RIP_Commands.Neighbor_RC import Neighbor_RC
from RIP_Commands.PassiveInterface_RC import PassiveInterface_RC
from RIP_Commands.Route_RC import Route_RC
from RIP_Commands.DistributeList_RC import DistributeList_RC


%%

parser RIP_Parser:
    ignore:						r"[ \t\r]"
    ignore:						r"![\-a-zA-Z0-9_! \./]*[\n]*"
    token EOF:					"$"
    token EOL:					r"\n"
    token NUM:					r"[0-9]+"
    token ALPHANUM:				r"[0-9a-zA-Z]+"
	token STRING:				r"[\-a-zA-Z0-9_!]*"
	token IP_ADDR:				r"((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)"
	token IP_ADDR:				r"((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)"

	token RIP:					r"rip"
    token ROUTER:				r"router"
    token VERSION:				r"version"
	token REDISTRIBUTE:			r"redistribute"
	token KERNEL:				r"kernel"
	token STATIC:				r"static"
	token OSPF:					r"ospf"
	token CONNECTED:			r"connected"
	token BGP:					r"bgp"
	token RIP:					r"rip"
	token METRIC:				r"metric"
	token ROUTE_MAP:			r"route-map"
	token NETWORK:				r"network"
	token NEIGHBOR:				r"neighbor"
	token PASSIVE_INTERFACE:	r"passive-interface"
	token DISTRIBUTE_LIST:		r"distribute-list"
	token IN:					r"in"
	token OUT:					r"out"
	token ROUTE:				r"route"


    rule goal: {{ commands = [] }}	RIP_MAIN RIP_COMMAND<<commands>> EOF    {{ return commands }}

	rule RIP_MAIN: 				ROUTER RIP EOL 

	rule RIP_COMMAND<<commands>>:			(VERSION_RC)*
								(REDISTRIBUTE_RC	{{ commands.append(REDISTRIBUTE_RC) }}	)*
								(NETWORK_RC			{{ commands.append(NETWORK_RC) 		}} 	)*
								(NEIGHBOR_RC		{{ commands.append(NEIGHBOR_RC) 	}}	)*
								(PASSIVE_IF_RC		{{ commands.append(PASSIVE_IF_RC)	}} 	)*
								(DL_RC				{{ commands.append(DL_RC) 			}}	)*
								(ROUTE_RC			{{ commands.append(ROUTE_RC) 		}}	)*

	rule VERSION_RC:			VERSION "2" EOL

	rule REDISTRIBUTE_RC: {{m="" }}	{{r="" }}	REDISTRIBUTE SOURCE (METRIC_PARAM {{m=METRIC_PARAM}})* (ROUTE_MAP_PARAM {{r=ROUTE_MAP_PARAM}})* EOL   {{ return Redistribute_RC(SOURCE, metric=m, route_map=r) }}

	rule NETWORK_RC:			NETWORK NETWORK_VALUE EOL		{{ return Network_RC(NETWORK_VALUE) }}

	rule NEIGHBOR_RC:			NEIGHBOR IP_ADDR EOL	{{ return Neighbor_RC(IP_ADDR) }}

	rule PASSIVE_IF_RC:			PASSIVE_INTERFACE IFNAME EOL	{{ return PassiveInterface_RC(IFNAME) }}

    rule DL_RC:					DISTRIBUTE_LIST LISTNAME DIRECT IFNAME EOL	{{ return DistributeList_RC(LISTNAME, DIRECT, IFNAME) }}

	rule ROUTE_RC:				ROUTE IP_ADDR "/" NUM EOL	{{ return Route_RC(IP_ADDR + "/" + NUM) }}

    rule IFNAME: 			ALPHANUM {{ return ALPHANUM }}
	rule LISTNAME: 			STRING {{ return STRING }}
	rule DIRECT: 			(IN {{ return IN }} | OUT {{ return OUT }}) 
	rule METRIC_PARAM:		METRIC NUM {{ return NUM }}
	rule ROUTE_MAP_PARAM:	ROUTE_MAP ALPHANUM {{ return ALPHANUM }}
	rule SOURCE:			KERNEL {{ return KERNEL}}
							| OSPF  {{ return OSPF}}
							| CONNECTED  {{ return CONNECTED}}
							| BGP  {{ return BGP}}
							| RIP {{ return RIP}}
							| STATIC {{ return STATIC}}

	rule NETWORK_VALUE:		IP_ADDR "/" NUM		{{ return (IP_ADDR + "/" + NUM) }}
							| IFNAME			{{ return (IFNAME) }}
