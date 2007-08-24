def appendBGPBestPath(bgpconf,BGPBestPath):
	"""
		Add the BGPBestPath data, if is not empty, to the bgpconf data. 
	"""
	if (len(BGPBestPath) > 0 ):
		bgpconf["bestpath"] = BGPBestPath 

def appendCapability(dataconf,capability,value):
	"""
		Add the capability data, to the bgpconf data and create a capability key in case it doesn't exist. 
	"""
	
	if (not dataconf.has_key("capability")):
		dataconf["capability"] = {"route-refresh":True}
	dataconf["capability"][capability] = value 

def appendRedistribute(dataconf,redistribute):
	"""
		Add or replace, in case another redistribute has the same type, the redistribute data to the dataconf data. 
	"""
	
	if (not dataconf.has_key("redistributes")):
		dataconf["redistributes"] = []
	for pos in range(0,len(dataconf["redistributes"])):
		if(dataconf["redistributes"][pos]["type"] == redistribute["type"]):
			dataconf["redistributes"][pos] = redistribute
			break
	else:
		dataconf["redistributes"].append(redistribute)
		
def getNetworkFrom(network,dataconf):
	"""
		Get or create, in case doesn't exist in dataconf, the network data representating the network data passed as parameter
	"""
	if (not dataconf.has_key("networks")):
		dataconf["networks"] = []
	dataconf["networks"].append(network)
	return network

def	getAggregateAddressFrom(address,dataconf):
	"""
		Get or create, in case doesn't exist in dataconf, the aggregate-address data representating the address data passed as parameter
	"""
	if (not dataconf.has_key("aggregate-addresses")):
		dataconf["aggregate-addresses"] = []
	dataconf["aggregate-addresses"].append(address)
	return address

def getNeighborFrom(neighbor,dataconf,defaultactivate=False):
	"""
		Get or create, in case doesn't exist in dataconf, the neighbor or group data representating the neighbor data passed as parameter
		@type defaultactivate: boolean
		@param defaultactivate: It will create a key activate and set it to True if the value is True.
	"""
	
	if (not "activate" in neighbor):
		neighbor["activate"] = defaultactivate
		
	if (neighbor.has_key("ip-address")):
		if (not dataconf.has_key("neighbors")):
			dataconf["neighbors"] = []
			dataconf["neighbors"].append(neighbor)
		else:
			for x in dataconf["neighbors"] : 
				if (x["ip-address"] == neighbor["ip-address"]):
					neighbor = x
					break
			else :
				dataconf["neighbors"].append(neighbor)
	else: # neighbor.has_key("name")
		if (not dataconf.has_key("peer-groups")):
			dataconf["peer-groups"] = []
			dataconf["peer-groups"].append(neighbor)
		else:
			for x in dataconf["peer-groups"] : 
				if (x["name"] == neighbor["name"]):
					neighbor = x
					break
			else :
				dataconf["peer-groups"].append(neighbor)
	
	return neighbor

def	bindFilterTo(typefilter,filter,dataconf):
	"""
		It will add the filter to the list of filters corresponding to the typefilter.
		@type typefilter: "distribute-list" | "prefix-list"| "filter-list" | "route-map"|"unsuppress-map" 							
	"""
		
	if (not dataconf.has_key("bind-filters")):
		dataconf["bind-filters"] = {}
	if (not dataconf["bind-filters"].has_key(typefilter)):
		dataconf["bind-filters"][typefilter] = []
	
	dataconf["bind-filters"][typefilter].append(filter)

def getListFrom(listtype,listdata,dataconf):
	"""
		Gets the list represented by listdata in dataconf from the listtype or add it if doesn't exists
	"""
	if (not dataconf.has_key(listtype)):
		dataconf[listtype] = []
		dataconf[listtype].append(listdata)
	else:
		for x in dataconf[listtype] :
			for key in listdata.keys():
				if (x[key] != listdata[key]):
					break
			else:
				listdata = x
				break
		else :
			dataconf[listtype].append(listdata)

	return listdata

def getCommunityFrom(commtype,commdata,dataconf):
	"""
		Get or create, in case doesn't exist in dataconf, the community list data representating the commdata data passed as parameter
		@type commtype: "community-list" | "extcommunity-list"
	"""
	
	if (not dataconf.has_key(commtype)):
		dataconf[commtype] = []

	dataconf[commtype].append(commdata)

	return commdata

def appendSequence(sequence,data):
	"""
		Append a sequence, or create a list of sequence if none exists, to the data
	"""
	
	if (not "sequences" in data):
		data["sequences"]= []
	data["sequences"].append(sequence)
	
def	bindDistanceTo(distance,dataconf):
	"""
		Append a distance, or create a list of distances if none exists, to the dataconf
	"""
	
	if (not dataconf.has_key("distances")):
		dataconf["distances"] = []
	dataconf["distances"].append(distance)

def appendAddressFamilies(bgpconf,Address_Family):
	"""
		Append the Families if are non empty to the bgpconf
	"""

	if (len(Address_Family) > 0) :
		bgpconf["address-families"] = Address_Family

def getMatchFrom(route_map):
	"""
		Return the match structure, if exists, or creates it.
	"""
	if (not "match" in route_map):
		route_map["match"] = {}
	return route_map["match"]

def getSetFrom(route_map):
	"""
		Return the set structure, if exists, or creates it.
	"""
	
	if (not "set" in route_map):
		route_map["set"] = {}
	return route_map["set"]

def appendValueTo(data,group,item,value):
	"""
		Append a group, or create a list of group if none exists, to the data
	"""
	if (not group in data):
		data[group] = []
	data[group].append(value)


 	
%%
parser BGPParser:
	
	ignore: 		r"[ \t\r]"
	ignore:	 	r"[ \t\r]*![^\n]*\n"
	token ENDFILE:  r"$"
	token END:   	r'[ \t\r]*\n'
	token NUMBER:	r'[0-9]+'
	token STR:	 	r'[\-a-zA-Z0-9_!]*'
	token EXP:	 	r'[\-a-zA-Z0-9_.:$*^()|/!]*'
	token AS_NUMBER:r'[0-9]+'
	token IPV4: 	r'((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)'
	token IPV6:		r'([0-9a-fA-F]{0,4}:){0,7}[0-9a-fA-F]{0,4}'
	token LEN_MASK: r'[0-9]+'
	
	rule Parser: {{ data = {"bgp": {"bgprouter":[]} } }}
				(BGPConf 										{{ data["bgp"]["bgprouter"].append(BGPConf) }})*
				Communities										{{ data["bgp"]["communities"] = Communities }}
				Filters											{{ data["bgp"]["filters"] = Filters }}
				ENDFILE
				{{ return data }}
				
	
	rule Communities: {{ communities = {} }}
				  (												{{ community = {} }}
				  	("ip community-list"						{{ communitytype= "community-list" }}
					|"ip extcommunity-list"						{{ communitytype= "extcommunity-list" }})
																{{ community = getListFrom(communitytype,community,communities) }}			
					["standard"									{{ community["type"] = "standard" }}
					|"expanded"									{{ community["type"] = "expanded" }}]
							STR									{{ community["name"] = STR }}
					("permit"									{{ community["state"] = "permit" }}
					|"deny"										{{ community["state"] = "deny" }})
																{{ community["value"] = [] }}
					(EXP										{{ community["value"].append(EXP) }})*
				  END
				  )*
				  {{ return communities }} 
				
				
	rule Filters: {{ filters = {} }}
				  ( ("ipv6"										{{ listtype = "ipv6-" }}
						( Access_list<<listtype,filters>>
						| Prefix_list<<listtype,filters>>))
				  | ("ip"										{{ listtype = "" }}
						( Prefix_list<<listtype,filters>>
						| AS_Path<<"as-path",filters>>))
				  | (Access_list<<"",filters>>
					|Route_map<<"route-map",filters>>)
				  )*
				  {{ return filters }}
				  
	rule Access_list<<listtype, dataconf>>:                     {{ accesslist = {} }} 
					( "access-list" STR							{{ accesslist["list-name"] = STR }}
																{{ accesslist = getListFrom(listtype+ "access-list",accesslist,dataconf) }}
						("remark" EXP							{{ accesslist["remark"] = EXP }}
						|(                                      {{ list = {} }}
                           ("permit"							{{ list["state"] = "permit" }}
						   |"deny" 								{{ list["state"] = "deny" }})
						   ["ip"								{{ list["protocol"]= "ip" }}]
						   source_Access_list<<list,"source","source-wild">>
						   source_Access_list<<list,"destination","dest-wild">>
						                                        {{ appendValueTo(accesslist,"list",None,list) }}   
						 )
						) 
					END)


	rule source_Access_list<<accesslist,source,wildcard>>:
				   ("any"										{{ accesslist[source]= "any" }}
				   |("host"	 								{{ accesslist[source]= "host" }}
						[  IPV4									{{ accesslist[wildcard]= IPV4 }}
						| IPV6									{{ accesslist[wildcard]= IPV6 }}])
				   |( (IPV4		 								{{ accesslist[source]= IPV4 }}
					   |IPV6									{{ accesslist[source]= IPV6 }}
					   )
					   [ ("/" NUMBER							{{ accesslist[wildcard]= NUMBER }}
						  	 		["exact-match"				{{ accesslist["type"]= "exact-match" }}])
					   | IPV4			  						{{ accesslist[wildcard]= IPV4 }}
					   | IPV6						   			{{ accesslist[wildcard]= IPV6 }}])
				   )
	

	rule AS_Path<<listtype,dataconf>>: {{ aspath = {} }}
					("as-path""access-list" STR				 {{ aspath= {"name": STR} }}
																{{ aspath = getListFrom(listtype,aspath,dataconf) }}
								("permit"						{{ aspath["state"] = "permit" }}
								|"deny" 						{{ aspath["state"] = "deny" }})
								EXP								{{ aspath["regexp"] = EXP }}
					END
					)
					
	rule Prefix_list<<listtype,dataconf>>: {{ prefixlist = {} }}
					( "prefix-list" STR							{{ prefixlist["name"] = STR }}
																{{ prefixlist = getListFrom(listtype+"prefix-list",prefixlist,dataconf) }}
						("description"						  {{ prefixlist["description"] = "" }}
								(EXP							{{ prefixlist["description"] = prefixlist["description"] +" "+ EXP }} )+
						|(										{{ sequence = {} }}
						  "seq" NUMBER							{{ sequence["seq"] = NUMBER }}
						  ("permit"								{{ sequence["state"] = "permit" }}
						  |"deny" 								{{ sequence["state"] = "deny" }})
						  ("any"								{{ sequence["ip-prefix"]= "any" }}
						  |( (IPV4 								{{ sequence["ip-prefix"]= IPV4 }}
						  	 |IPV6 								{{ sequence["ip-prefix"]= IPV6 }})
						  		"/" NUMBER						{{ sequence["mask"]= NUMBER }}
						 	 	["ge" NUMBER					{{ sequence["ge"]= NUMBER }}]
								["le" NUMBER					{{ sequence["le"]= NUMBER }}])
						  )
						  										{{appendSequence(sequence,prefixlist)}}
						  )
						)
					END)
	
	rule Route_map<<listtype,dataconf>>: {{ routemap = {} }}
					( "route-map" STR							{{ routemap["map-tag"] = STR }}
																{{ routemap = getListFrom(listtype,routemap,dataconf) }}
																{{ sequence = {} }}
						 ("permit"								{{ sequence["state"] = "permit" }}
						 |"deny" 								{{ sequence["state"] = "deny" }})
						 NUMBER									{{ sequence["seq-number"]= NUMBER }}
						  										{{appendSequence(sequence,routemap)}}
					END)
					Match<<sequence>>*
					Set<<sequence>>*
					
	rule Match<<route_map>> :  
				("match"										{{ match = getMatchFrom(route_map) }}
					("metric" (NUMBER							{{ appendValueTo(match,"metric","metric-value",NUMBER) }})+
					|"origin" EXP								{{ match["origin"] = EXP }}
					|"as-path" (STR								{{ appendValueTo(match,"as-path","as-path-name",STR) }})+
					| "ip"
						(("address"
							("prefix-list"	(STR				{{ appendValueTo(match,"prefix-list","list-name",STR) }})+
							| (STR								{{ appendValueTo(match,"access-list","list-name",STR) }})+))
						|("next-hop"
							("prefix-list"	(STR				{{ appendValueTo(match,"next-hop-prefix-list","list-name",STR) }})+
							| (STR								{{ appendValueTo(match,"next-hop-access-list","list-name",STR) }})+))
						)
					| ("ipv6"
						(("address"
							("prefix-list"	(STR				{{ appendValueTo(match,"ipv6-prefix-list","list-name",STR) }})+
							| (STR								{{ appendValueTo(match,"ipv6-access-list","list-name",STR) }})+))
						|"next-hop" (IPV6						{{ appendValueTo(match,"ipv6-next-hop","ip",IPV6) }})+) 
					  )
					| ("community" STR							{{ match["community"] = {"name": STR} }}
							["exact-match"]						{{ match["community"]["exact-match"] = True }} )
					| "extcommunity" STR						{{ match["extcommunity"] = STR }}
					)
				 END)
	
	rule Set<<route_map>> :
				("set"										{{ set = getSetFrom(route_map) }}
					(("metric"									{{ metric = {} }}  
					  		["\+"								{{ metric["negative"] = False }}
							|"-"								{{ metric["negative"] = True }} ]
							NUMBER								{{ metric["value"] = NUMBER }} 
																{{ set["metric"] = metric }} )
					|"weight" NUMBER							{{ set["weight"] = NUMBER }}
					|"local-preference" NUMBER					{{ set["local-preference"] = NUMBER }}
					|"origin" EXP								{{ set["origin"] = EXP }}
					|"originator-id" IPV4						{{ set["originator-id"] = IPV4 }}
					|"atomic-aggregate" 						{{ set["atomic-aggregate"] = True }}
					|"aggregator as" NUMBER IPV4				{{ set["aggregator"] = {"router-as":NUMBER,"router-id":IPV4} }}
					|"as-path prepend" (NUMBER					{{ appendValueTo(set,"as-path","prepend",NUMBER) }})*
					| "ip" "next-hop" ("peer-group" STR					   {{ set["next-hop"] = { "peer-group" : STR} }}
									  | IPV4							   {{ set["next-hop"] = { "ipv4address" : IPV4} }}
										)
	
					| "vpnv4" "next-hop" IPV4					{{ set["vpnv4-next-hop"] = IPV4 }}
					| ("ipv6" "next-hop" 						{{ if (not "ipv6-next-hop" in set) : set["ipv6-next-hop"] = {} }}
							("local"	IPV6					{{ set["ipv6-next-hop"]["local"] = IPV6 }}
							|"global"	IPV6					{{ set["ipv6-next-hop"]["global"] = IPV6 }}) 
					  )
					| "comm-list" STR "delete"					{{ match["comm-list-del"] = STR }}
					| ("community"
							("none"								{{ match["communities"] = [] }} 
							| (EXP								{{ appendValueTo(set,"communities","comunity",EXP) }})+ ))
					| ("extcommunity" 
							("rt"								{{ community= {"type":"rt" ,"value":[]} }}
							|"soo"								{{ community= {"type":"soo","value":[]}  }})
							(EXP								{{ community["value"].append(EXP) }})+ 
																{{ appendValueTo(set,"extcommunities","extcomunity",community) }})
					)
				 END)
		
	rule BGPConf: {{ bgpconf = {"ipv4-unicast": True} }}
				 	("router bgp" AS_NUMBER END					{{ bgpconf["as-number"] = AS_NUMBER }})
					["no bgp fast-external-failover" END		{{ bgpconf["fast-external-failover"] = False }}]
					["bgp router-id" IPV4 END 					{{ bgpconf["id"] = IPV4 }}]
					["bgp log-neighbor-changes" END				{{ bgpconf["log-neighbor-changes"] = True }}]
					["bgp always-compare-med" END 				{{ bgpconf["always-compare-med"] = True }}]
					["no bgp default ipv4-unicast" END 			{{ bgpconf["ipv4-unicast"] = False }}]
					["bgp default local-preference" NUMBER END	{{ bgpconf["local-preference"] = NUMBER }}]
					["no bgp client-to-client reflection" END	{{ bgpconf["client-to-client-reflection"] = False }}]
					["bgp cluster-id" (IPV4 					{{ bgpconf["cluster-id"] = IPV4 }}
									  |NUMBER					{{ bgpconf["cluster-id"] = NUMBER }})
					 END]
					["bgp confederation identifier" NUMBER END	{{ bgpconf["confederation-id"] = NUMBER }}]
					["bgp confederation peers" 					{{ bgpconf["confederation-peers"]= [] }}
							(NUMBER 							{{ bgpconf["confederation-peers"].append(NUMBER) }} )+
					  END]
					["bgp enforce-first-as" END					{{ bgpconf["enforce-first-as"] = True }}]
					["bgp deterministic-med" END				{{ bgpconf["deterministic-med"] = True }}]
					[BGPBestPath								{{ appendBGPBestPath(bgpconf,BGPBestPath) }}]
					["bgp network import-check" END 			{{ bgpconf["network-import-check"] = False }}]
					["bgp scan-time" NUMBER END					{{ bgpconf["scan-time"] = NUMBER }}]
					["bgp dampening" 							{{ bgpconf["dampening"] = {}  }}
									[ NUMBER 					{{ bgpconf["dampening"]["half-life"] = NUMBER }} 
										[NUMBER 				{{ bgpconf["dampening"]["reuse"] = NUMBER }}
										 NUMBER 				{{ bgpconf["dampening"]["suppress"] = NUMBER }}
										 NUMBER 				{{ bgpconf["dampening"]["max-suppress-time"] = NUMBER }}]]
					  END]
					(Network_IPv4<<bgpconf>>)*
					(Redistribute_IPv4Unicast<<bgpconf>>)*
					(AggregateAddress_IPV4<<bgpconf>>)*
					(Neighbor<<bgpconf,bgpconf["ipv4-unicast"]>>)*
					["distance bgp" NUMBER 						{{ dist = {"external":NUMBER} }}
									NUMBER 						{{ dist["internal"] = NUMBER }}
									NUMBER 						{{ dist["local"] = NUMBER }}
																{{ bgpconf["distance-bgp"] = dist }}
					  END]
					(Distance<<bgpconf>>)*
					[Address_Family								{{ appendAddressFamilies(bgpconf,Address_Family) }}]
				 {{ return bgpconf }}

	rule BGPBestPath: {{ bestpath = {} }}
					["bgp bestpath"	"as-path" "ignore" END 		{{ bestpath["as-path-ignore"] = True }}]
					["bgp bestpath"	"compare-routerid" END 		{{ bestpath["compare-routerid"] = True }}]
					["bgp bestpath"	"med"						{{ bestpath["med"] = {} }}
									[("confed"					{{ bestpath["med"]["confed"] = True }} 
									  ["missing-as-worst" 		{{ bestpath["med"]["missing-as-worst"] = True }}])
									|("missing-as-worst" 		{{ bestpath["med"]["missing-as-worst"] = True }}
									  ["confed"					{{ bestpath["med"]["confed"] = True }} ])
									] 
					  END]
					{{ return bestpath }}
					
	rule Network_IPv4<<dataconf>>:
					{{ network = {} }}
					"network" IPV4 								{{ network["ip"] = IPV4 }}
																{{ network = getNetworkFrom(network,dataconf) }}
							  ((  ("mask" IPV4 					{{ network["mask"] = IPV4 }} 
								  | "\/" LEN_MASK				{{ network["mask"] = LEN_MASK }}) 
								  ["backdoor"  					{{ network["backdoor"] = True }} 
								  |"route-map" STR 				{{ network["route-map"] = STR }} ])
							   | "route-map" STR 				{{ network["route-map"] = STR }})
					END

	rule Network_IPv6Unicast<<dataconf>>:
					{{ network = {} }}
					"network" IPV6 								{{ network["ip"] = IPV6 }}
																{{ network = getNetworkFrom(network,dataconf) }}
							  "\/" LEN_MASK						{{ network["mask"] = LEN_MASK }} 
							  ["route-map" STR 					{{ network["route-map"] = STR }}]
					END
					
	rule Network_VPNv4Unicast<<dataconf>>:
					{{ network = {} }}
					"network" IPV4 								{{ network["ip"] = IPV4 }}
																{{ network = getNetworkFrom(network,dataconf) }}
							  "\/" LEN_MASK						{{ network["mask"] = LEN_MASK }} 
							  "rd" (AS_NUMBER  					{{ network["rd"] = AS_NUMBER }}
							  	   |IPV4						{{ network["rd"] = IPV4 }})
							   "tag" STR		 				{{ network["tag"] = STR }}
					END
				
	rule Redistribute_IPv4Unicast<<dataconf>>:
					{{ redistribute = {} }}
					"redistribute" 
							("kernel"							{{ redistribute["type"] = "kernel" }}		
							|"connected"						{{ redistribute["type"] = "connected" }}
							|"static"							{{ redistribute["type"] = "static" }}
							|"rip"								{{ redistribute["type"] = "rip" }}
							|"ospf"								{{ redistribute["type"] = "ospf" }}
							)
							["metric" NUMBER  					{{ redistribute["metric"] = NUMBER }} 
								["route-map" STR 				{{ redistribute["route-map"] = STR }} ]
							|"route-map" STR 					{{ redistribute["route-map"] = STR }} 
								["metric" NUMBER  				{{ redistribute["metric"] = NUMBER }}]
							]
					END
					{{ appendRedistribute(dataconf,redistribute) }}
					
	rule Redistribute_IPv6Unicast<<dataconf>>:
					{{ redistribute = {} }}
					"redistribute" 
							("kernel"							{{ redistribute["type"] = "kernel" }}		
							|"connected"						{{ redistribute["type"] = "connected" }}
							|"static"							{{ redistribute["type"] = "static" }}
							|"ripng"							{{ redistribute["type"] = "ripng" }}
							|"ospf6"							{{ redistribute["type"] = "ospf6" }}
							)
							["metric" NUMBER  					{{ redistribute["metric"] = NUMBER }} 
								["route-map" STR 				{{ redistribute["route-map"] = STR }} ]
							|"route-map" STR 					{{ redistribute["route-map"] = STR }} 
								["metric" NUMBER  				{{ redistribute["metric"] = NUMBER }}]
							]
					END
					{{ appendRedistribute(dataconf,redistribute) }}
	
	rule AggregateAddress_IPV4<<dataconf>>:
					{{ address ={} }}
					"aggregate-address" IPV4 					{{ address["ip-prefix"] = IPV4 }}
																{{ address = getAggregateAddressFrom(address,dataconf) }}
							("\/" LEN_MASK 						{{ address["mask"] = LEN_MASK }}
							| IPV4 								{{ address["mask"] = IPV4 }})
							["as-set" 							{{ address["as-set"] = True }}
							|"summary-only" 					{{ address["summary-only"] = True }} ]
							["as-set" 							{{ address["as-set"] = True }}
							|"summary-only" 					{{ address["summary-only"] = True }} ]
					END

	rule Neighbor<<dataconf,default_activate>>: {{ neighbor = {} }}
					(("neighbor" 
							( IPV4 								{{ neighbor["ip-address"] = IPV4 }} 
							  | IPV6			 				{{ neighbor["ip-address"] = IPV6 }} 
							  | STR 							{{ neighbor["name"] = STR }} )
							  									{{ neighbor = getNeighborFrom(neighbor,dataconf,default_activate) }}
																 
							(Neighbor__<<neighbor>>	
							|NeighborConf__<<neighbor>>)
					  )
					| ("no neighbor"
							( IPV4 								{{ neighbor["ip-address"] = IPV4 }} 
							  | IPV6 							{{ neighbor["ip-address"] = IPV6 }} 
							  | STR 							{{ neighbor["name"] = STR }} )
							  									{{ neighbor = getNeighborFrom(neighbor,dataconf,default_activate) }}
							(NoNeighbor__<<neighbor>>
							|NoNeighborConf__<<neighbor>>)
					  )
					)
					END
				  
	rule Neighbor__<<neighbor>> :
							("remote-as" AS_NUMBER 				{{ neighbor["remote-as"]				= AS_NUMBER }}
							#| "peer-group" # Its commented otherwise conflicts with the peer-group of the NeighborConf (Parser generator problem)	
							|"description"					  {{ neighbor["description"] = "" }}
								(STR 							{{ neighbor["description"] = neighbor["description"] + " " + STR }})+
							|"shutdown" 	 					{{ neighbor["shutdown"]					= True }}
							|"port" NUMBER	 					{{ neighbor["port"]						= NUMBER }}
							|"interface" EXP 					{{ neighbor["interface"]				= EXP }}
							|"passive" 		 					{{ neighbor["passive"]					= True }}
							|"ebgp-multihop"					{{ neighbor["ebgp-multihop"]			= {} }}
									[NUMBER						{{ neighbor["ebgp-multihop"]["ttl"]		= NUMBER }}]
							|"enforce-multihop"					{{ neighbor["enforce-multihop"]			= True }}
							|"update-source" EXP				{{ neighbor["update-source"]			= EXP }}
							|"version" "4-" 					{{ neighbor["version"]					= "4-" }}
							|"advertisement-interval" NUMBER	{{ neighbor["advertisement-interval"]	= NUMBER }}
							|"timers" ((NUMBER					{{ neighbor["timers"]= {"keep-alive": NUMBER} }}
										NUMBER					{{ neighbor["timers"]["hold-time"]		= NUMBER }})
									  |"connect" NUMBER			{{ neighbor["timers-connect"]			= NUMBER }})
							|"weight" NUMBER					{{ neighbor["weight"]					= NUMBER }}
							|"capability" "dynamic"				{{ appendCapability(neighbor,"dynamic",True)  }}
							|"dont-capability-negotiate"		{{ neighbor["dont-capability-negotiate"]= True }}
							|"strict-capability-match"			{{ neighbor["strict-capability-match"]	= True }}
							|"override-capability"				{{ neighbor["override-capability"]		= True }}
							)
	
	rule NoNeighbor__<<neighbor>> :
							("capability" "route-refresh"		{{ appendCapability(neighbor,"route-refresh",False) }})
	
	rule NeighborConf<<dataconf>>: {{ neighbor = {} }}
					(("neighbor" 
							( IPV4 								{{ neighbor["ip-address"] = IPV4 }} 
							  | IPV6 							{{ neighbor["ip-address"] = IPV6 }} 
							  | STR 							{{ neighbor["name"] = STR }} )
							  									{{ neighbor = getNeighborFrom(neighbor,dataconf) }}
							(NeighborConf__<<neighbor>>)
					  )
					| ("no neighbor"
							( IPV4 								{{ neighbor["ip-address"] = IPV4 }} 
							  | IPV6 							{{ neighbor["ip-address"] = IPV6 }} 
							  | STR 							{{ neighbor["name"] = STR }} )
							  									{{ neighbor = getNeighborFrom(neighbor,dataconf) }}
							(NoNeighborConf__<<neighbor>>)
					  )
					)
					END
					
	rule NeighborConf__<<neighbor>>:
					( "peer-group" 
									[STR 						{{ neighbor["activated-group"] = STR }}]	
					| "activate"								{{ neighbor["activate"]					= True }}
					| "capability orf" "prefix-list" STR		{{ neighbor["capability-orf"]			= {"prefix-list": STR } }}
					| "route-reflector-client" 					{{ neighbor["route-reflector-client"]	= True  }}
					| "next-hop-self"							{{ neighbor["next-hop-self"]			= True }}
					| "remove-private-AS"						{{ neighbor["remove-private-as"]		= True }}
					| "default-originate"						{{ neighbor["default-originate"]		= {} }}
								["route-map" STR				{{ neighbor["default-originate"]["route-map"] = STR }}]
					| "soft-reconfiguration" "inbound"			{{ neighbor["soft-reconfiguration"]		= "inbound" }}
					| "maximum-prefix"	NUMBER					{{ neighbor["maximum-prefix"]			= {"maximum":NUMBER} }}
								["warning-only"					{{ neighbor["maximum-prefix"]["warning-only"] = True }}]
					| "route-server-client"						{{ neighbor["router-server-client"]			= True }}
					| "allowas-in"								{{ neighbor["allowas-in"]				= {} }}
								[NUMBER							{{ neighbor["allowas-in"]["quant"]		= NUMBER }}]
					| BindFilters<<neighbor>>	
					| "attribute-unchanged"						{{ neighbor["attribute-unchanged"]		= {} }}
								["as-path"						{{ neighbor["attribute-unchanged"]["as-path"] = True }}]
								["next-hop"						{{ neighbor["attribute-unchanged"]["next-hop"] = True }}]
								["med"							{{ neighbor["attribute-unchanged"]["med"] = True }}]
					)
					
	rule NoNeighborConf__<<neighbor>>:
					{{ neighbor["send-community"] = {"type":"both"} }}
					(( "send-community"							{{ neighbor["send-community"]["type"] ="extended" }}
								["both"							{{ del(neighbor["send-community"]) }}
								|"extended" 					{{ neighbor["send-community"]["type"] = "standard"  }}
								]
					 )
					| "activate"								{{ neighbor["activate"]					= False }}
					)
					
	rule BindFilters<<neighbor>>:
					{{ filter = {} }}
					("distribute-list"  						{{ filtertype  = "distribute-list" }}
					|"prefix-list"  							{{ filtertype  = "prefix-list" }}
					|"filter-list"  							{{ filtertype  = "filter-list" }}
					|"route-map" 	 							{{ filtertype  = "route-map" }}
					|"unsuppress-map" 							{{ filtertype  = "unsuppress-map" }}
					)
	 				STR											{{ filter["name"]   = STR }}
					("in"										{{ filter["direct"] = "in" }}
					|"out"										{{ filter["direct"] = "out" }})
																{{ bindFilterTo(filtertype,filter,neighbor) }}
 					
																
	rule Distance<<dataconf>>:
					{{ distance = {} }}
					"distance" 	NUMBER 							{{ distance["value"] = NUMBER }}
								IPV4 							{{ distance["ip"] = IPV4 }}
								"\/" NUMBER						{{ distance["mask"] = NUMBER }}
								[STR							{{ distance["word"] = STR }}]
					END											{{ bindDistanceTo(distance,dataconf) }}
	
	rule Address_Family: {{ address_family = {} }}
					["address-family ipv4"  ["multicast"] END 
						IPv4Multicast_Address_Family {{ address_family["ipv4-multicast"] =IPv4Multicast_Address_Family }} 
					 "exit-address-family" END]
					["address-family vpnv4" ["unicast"]	END  
						VPNv4_Address_Family {{ address_family["vpnv4-unicast"] =VPNv4_Address_Family }} 
					 "exit-address-family" END]
					["address-family ipv6"  ["unicast"]	END  
						IPv6Unicast_Address_Family {{ address_family["ipv6-unicast"] =IPv6Unicast_Address_Family }}
					 "exit-address-family" END]
					 {{ return address_family }}
 
	rule IPv4Multicast_Address_Family: 
					{{ ipv4conf ={} }}
					(Network_IPv4<<ipv4conf>>)*
					(AggregateAddress_IPV4<<ipv4conf>>)*
					(NeighborConf<<ipv4conf>>)*
					{{ return ipv4conf }}
					
	rule VPNv4_Address_Family:
					{{ vpnv4conf ={} }}
					(Network_VPNv4Unicast<<vpnv4conf>>)*
					(NeighborConf<<ipv4conf>>)*
					{{ return ipv4conf }}
	
	rule IPv6Unicast_Address_Family:
					{{ ipv6conf ={} }}
					(Network_IPv6Unicast<<ipv6conf>>)*
					(Redistribute_IPv6Unicast<<ipv6conf>>)*
					(NeighborConf<<ipv6conf>>)*

					{{ return ipv6conf }}

%%


