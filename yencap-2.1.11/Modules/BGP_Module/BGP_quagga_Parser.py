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


 	

from string import *
import re
from yapps2lc.yappsrt import *

class BGPParserScanner(Scanner):
    patterns = [
        ('"address-family ipv6"', re.compile('address-family ipv6')),
        ('"unicast"', re.compile('unicast')),
        ('"address-family vpnv4"', re.compile('address-family vpnv4')),
        ('"exit-address-family"', re.compile('exit-address-family')),
        ('"multicast"', re.compile('multicast')),
        ('"address-family ipv4"', re.compile('address-family ipv4')),
        ('"distance"', re.compile('distance')),
        ('"out"', re.compile('out')),
        ('"in"', re.compile('in')),
        ('"unsuppress-map"', re.compile('unsuppress-map')),
        ('"filter-list"', re.compile('filter-list')),
        ('"distribute-list"', re.compile('distribute-list')),
        ('"extended"', re.compile('extended')),
        ('"both"', re.compile('both')),
        ('"send-community"', re.compile('send-community')),
        ('"attribute-unchanged"', re.compile('attribute-unchanged')),
        ('"allowas-in"', re.compile('allowas-in')),
        ('"route-server-client"', re.compile('route-server-client')),
        ('"warning-only"', re.compile('warning-only')),
        ('"maximum-prefix"', re.compile('maximum-prefix')),
        ('"inbound"', re.compile('inbound')),
        ('"soft-reconfiguration"', re.compile('soft-reconfiguration')),
        ('"default-originate"', re.compile('default-originate')),
        ('"remove-private-AS"', re.compile('remove-private-AS')),
        ('"next-hop-self"', re.compile('next-hop-self')),
        ('"route-reflector-client"', re.compile('route-reflector-client')),
        ('"capability orf"', re.compile('capability orf')),
        ('"activate"', re.compile('activate')),
        ('"route-refresh"', re.compile('route-refresh')),
        ('"override-capability"', re.compile('override-capability')),
        ('"strict-capability-match"', re.compile('strict-capability-match')),
        ('"dont-capability-negotiate"', re.compile('dont-capability-negotiate')),
        ('"dynamic"', re.compile('dynamic')),
        ('"capability"', re.compile('capability')),
        ('"connect"', re.compile('connect')),
        ('"timers"', re.compile('timers')),
        ('"advertisement-interval"', re.compile('advertisement-interval')),
        ('"4-"', re.compile('4-')),
        ('"version"', re.compile('version')),
        ('"update-source"', re.compile('update-source')),
        ('"enforce-multihop"', re.compile('enforce-multihop')),
        ('"ebgp-multihop"', re.compile('ebgp-multihop')),
        ('"passive"', re.compile('passive')),
        ('"interface"', re.compile('interface')),
        ('"port"', re.compile('port')),
        ('"shutdown"', re.compile('shutdown')),
        ('"remote-as"', re.compile('remote-as')),
        ('"no neighbor"', re.compile('no neighbor')),
        ('"neighbor"', re.compile('neighbor')),
        ('"summary-only"', re.compile('summary-only')),
        ('"as-set"', re.compile('as-set')),
        ('"aggregate-address"', re.compile('aggregate-address')),
        ('"ospf6"', re.compile('ospf6')),
        ('"ripng"', re.compile('ripng')),
        ('"ospf"', re.compile('ospf')),
        ('"rip"', re.compile('rip')),
        ('"static"', re.compile('static')),
        ('"connected"', re.compile('connected')),
        ('"kernel"', re.compile('kernel')),
        ('"redistribute"', re.compile('redistribute')),
        ('"tag"', re.compile('tag')),
        ('"rd"', re.compile('rd')),
        ('"backdoor"', re.compile('backdoor')),
        ('"\\/"', re.compile('\\/')),
        ('"mask"', re.compile('mask')),
        ('"network"', re.compile('network')),
        ('"missing-as-worst"', re.compile('missing-as-worst')),
        ('"confed"', re.compile('confed')),
        ('"med"', re.compile('med')),
        ('"compare-routerid"', re.compile('compare-routerid')),
        ('"ignore"', re.compile('ignore')),
        ('"bgp bestpath"', re.compile('bgp bestpath')),
        ('"distance bgp"', re.compile('distance bgp')),
        ('"bgp dampening"', re.compile('bgp dampening')),
        ('"bgp scan-time"', re.compile('bgp scan-time')),
        ('"bgp network import-check"', re.compile('bgp network import-check')),
        ('"bgp deterministic-med"', re.compile('bgp deterministic-med')),
        ('"bgp enforce-first-as"', re.compile('bgp enforce-first-as')),
        ('"bgp confederation peers"', re.compile('bgp confederation peers')),
        ('"bgp confederation identifier"', re.compile('bgp confederation identifier')),
        ('"bgp cluster-id"', re.compile('bgp cluster-id')),
        ('"no bgp client-to-client reflection"', re.compile('no bgp client-to-client reflection')),
        ('"bgp default local-preference"', re.compile('bgp default local-preference')),
        ('"no bgp default ipv4-unicast"', re.compile('no bgp default ipv4-unicast')),
        ('"bgp always-compare-med"', re.compile('bgp always-compare-med')),
        ('"bgp log-neighbor-changes"', re.compile('bgp log-neighbor-changes')),
        ('"bgp router-id"', re.compile('bgp router-id')),
        ('"no bgp fast-external-failover"', re.compile('no bgp fast-external-failover')),
        ('"router bgp"', re.compile('router bgp')),
        ('"soo"', re.compile('soo')),
        ('"rt"', re.compile('rt')),
        ('"none"', re.compile('none')),
        ('"delete"', re.compile('delete')),
        ('"comm-list"', re.compile('comm-list')),
        ('"global"', re.compile('global')),
        ('"local"', re.compile('local')),
        ('"vpnv4"', re.compile('vpnv4')),
        ('"peer-group"', re.compile('peer-group')),
        ('"as-path prepend"', re.compile('as-path prepend')),
        ('"aggregator as"', re.compile('aggregator as')),
        ('"atomic-aggregate"', re.compile('atomic-aggregate')),
        ('"originator-id"', re.compile('originator-id')),
        ('"local-preference"', re.compile('local-preference')),
        ('"weight"', re.compile('weight')),
        ('"-"', re.compile('-')),
        ('"\\+"', re.compile('\\+')),
        ('"set"', re.compile('set')),
        ('"extcommunity"', re.compile('extcommunity')),
        ('"community"', re.compile('community')),
        ('"next-hop"', re.compile('next-hop')),
        ('"address"', re.compile('address')),
        ('"origin"', re.compile('origin')),
        ('"metric"', re.compile('metric')),
        ('"match"', re.compile('match')),
        ('"route-map"', re.compile('route-map')),
        ('"le"', re.compile('le')),
        ('"ge"', re.compile('ge')),
        ('"seq"', re.compile('seq')),
        ('"description"', re.compile('description')),
        ('"prefix-list"', re.compile('prefix-list')),
        ('"as-path"', re.compile('as-path')),
        ('"exact-match"', re.compile('exact-match')),
        ('"/"', re.compile('/')),
        ('"host"', re.compile('host')),
        ('"any"', re.compile('any')),
        ('"remark"', re.compile('remark')),
        ('"access-list"', re.compile('access-list')),
        ('"ip"', re.compile('ip')),
        ('"ipv6"', re.compile('ipv6')),
        ('"deny"', re.compile('deny')),
        ('"permit"', re.compile('permit')),
        ('"expanded"', re.compile('expanded')),
        ('"standard"', re.compile('standard')),
        ('"ip extcommunity-list"', re.compile('ip extcommunity-list')),
        ('"ip community-list"', re.compile('ip community-list')),
        ('[ \\t\\r]', re.compile('[ \\t\\r]')),
        ('[ \\t\\r]*![^\\n]*\\n', re.compile('[ \\t\\r]*![^\\n]*\\n')),
        ('ENDFILE', re.compile('$')),
        ('END', re.compile('[ \\t\\r]*\\n')),
        ('NUMBER', re.compile('[0-9]+')),
        ('STR', re.compile('[\\-a-zA-Z0-9_!]*')),
        ('EXP', re.compile('[\\-a-zA-Z0-9_.:$*^()|/!]*')),
        ('AS_NUMBER', re.compile('[0-9]+')),
        ('IPV4', re.compile('((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)')),
        ('IPV6', re.compile('([0-9a-fA-F]{0,4}:){0,7}[0-9a-fA-F]{0,4}')),
        ('LEN_MASK', re.compile('[0-9]+')),
    ]
    def __init__(self, str):
        Scanner.__init__(self,None,['[ \\t\\r]', '[ \\t\\r]*![^\\n]*\\n'],str)

class BGPParser(Parser):
    def Parser(self):
        data = {"bgp": {"bgprouter":[]} }
        while self._peek('"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"router bgp"':
            BGPConf = self.BGPConf()
            data["bgp"]["bgprouter"].append(BGPConf)
        Communities = self.Communities()
        data["bgp"]["communities"] = Communities
        Filters = self.Filters()
        data["bgp"]["filters"] = Filters
        ENDFILE = self._scan('ENDFILE')
        return data

    def Communities(self):
        communities = {}
        while self._peek('"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') in ['"ip community-list"', '"ip extcommunity-list"']:
            community = {}
            _token_ = self._peek('"ip community-list"', '"ip extcommunity-list"')
            if _token_ == '"ip community-list"':
                self._scan('"ip community-list"')
                communitytype= "community-list"
            else:# == '"ip extcommunity-list"'
                self._scan('"ip extcommunity-list"')
                communitytype= "extcommunity-list"
            community = getListFrom(communitytype,community,communities)
            if self._peek('"standard"', '"expanded"', 'STR') != 'STR':
                _token_ = self._peek('"standard"', '"expanded"')
                if _token_ == '"standard"':
                    self._scan('"standard"')
                    community["type"] = "standard"
                else:# == '"expanded"'
                    self._scan('"expanded"')
                    community["type"] = "expanded"
            STR = self._scan('STR')
            community["name"] = STR
            _token_ = self._peek('"permit"', '"deny"')
            if _token_ == '"permit"':
                self._scan('"permit"')
                community["state"] = "permit"
            else:# == '"deny"'
                self._scan('"deny"')
                community["state"] = "deny"
            community["value"] = []
            while self._peek('EXP', 'END') == 'EXP':
                EXP = self._scan('EXP')
                community["value"].append(EXP)
            END = self._scan('END')
        return communities

    def Filters(self):
        filters = {}
        while self._peek('"ipv6"', '"ip"', '"access-list"', '"route-map"', '"set"', 'ENDFILE') not in ['"set"', 'ENDFILE']:
            _token_ = self._peek('"ipv6"', '"ip"', '"access-list"', '"route-map"')
            if _token_ == '"ipv6"':
                self._scan('"ipv6"')
                listtype = "ipv6-"
                _token_ = self._peek('"access-list"', '"prefix-list"')
                if _token_ == '"access-list"':
                    Access_list = self.Access_list(listtype,filters)
                else:# == '"prefix-list"'
                    Prefix_list = self.Prefix_list(listtype,filters)
            elif _token_ == '"ip"':
                self._scan('"ip"')
                listtype = ""
                _token_ = self._peek('"prefix-list"', '"as-path"')
                if _token_ == '"prefix-list"':
                    Prefix_list = self.Prefix_list(listtype,filters)
                else:# == '"as-path"'
                    AS_Path = self.AS_Path("as-path",filters)
            else:# in ['"access-list"', '"route-map"']
                _token_ = self._peek('"access-list"', '"route-map"')
                if _token_ == '"access-list"':
                    Access_list = self.Access_list("",filters)
                else:# == '"route-map"'
                    Route_map = self.Route_map("route-map",filters)
        return filters

    def Access_list(self, listtype, dataconf):
        accesslist = {}
        self._scan('"access-list"')
        STR = self._scan('STR')
        accesslist["list-name"] = STR
        accesslist = getListFrom(listtype+ "access-list",accesslist,dataconf)
        _token_ = self._peek('"remark"', '"permit"', '"deny"')
        if _token_ == '"remark"':
            self._scan('"remark"')
            EXP = self._scan('EXP')
            accesslist["remark"] = EXP
        else:# in ['"permit"', '"deny"']
            list = {}
            _token_ = self._peek('"permit"', '"deny"')
            if _token_ == '"permit"':
                self._scan('"permit"')
                list["state"] = "permit"
            else:# == '"deny"'
                self._scan('"deny"')
                list["state"] = "deny"
            if self._peek('"ip"', '"any"', '"host"', 'IPV4', 'IPV6') == '"ip"':
                self._scan('"ip"')
                list["protocol"]= "ip"
            source_Access_list = self.source_Access_list(list,"source","source-wild")
            source_Access_list = self.source_Access_list(list,"destination","dest-wild")
            appendValueTo(accesslist,"list",None,list)
        END = self._scan('END')

    def source_Access_list(self, accesslist,source,wildcard):
        _token_ = self._peek('"any"', '"host"', 'IPV4', 'IPV6')
        if _token_ == '"any"':
            self._scan('"any"')
            accesslist[source]= "any"
        elif _token_ == '"host"':
            self._scan('"host"')
            accesslist[source]= "host"
            if self._peek('IPV4', 'IPV6', '"any"', '"host"', 'END') in ['IPV4', 'IPV6']:
                _token_ = self._peek('IPV4', 'IPV6')
                if _token_ == 'IPV4':
                    IPV4 = self._scan('IPV4')
                    accesslist[wildcard]= IPV4
                else:# == 'IPV6'
                    IPV6 = self._scan('IPV6')
                    accesslist[wildcard]= IPV6
        else:# in ['IPV4', 'IPV6']
            _token_ = self._peek('IPV4', 'IPV6')
            if _token_ == 'IPV4':
                IPV4 = self._scan('IPV4')
                accesslist[source]= IPV4
            else:# == 'IPV6'
                IPV6 = self._scan('IPV6')
                accesslist[source]= IPV6
            if self._peek('"/"', 'IPV4', 'IPV6', '"any"', '"host"', 'END') in ['"/"', 'IPV4', 'IPV6']:
                _token_ = self._peek('"/"', 'IPV4', 'IPV6')
                if _token_ == '"/"':
                    self._scan('"/"')
                    NUMBER = self._scan('NUMBER')
                    accesslist[wildcard]= NUMBER
                    if self._peek('"exact-match"', '"any"', '"host"', 'IPV4', 'IPV6', 'END') == '"exact-match"':
                        self._scan('"exact-match"')
                        accesslist["type"]= "exact-match"
                elif _token_ == 'IPV4':
                    IPV4 = self._scan('IPV4')
                    accesslist[wildcard]= IPV4
                else:# == 'IPV6'
                    IPV6 = self._scan('IPV6')
                    accesslist[wildcard]= IPV6

    def AS_Path(self, listtype,dataconf):
        aspath = {}
        self._scan('"as-path"')
        self._scan('"access-list"')
        STR = self._scan('STR')
        aspath= {"name": STR}
        aspath = getListFrom(listtype,aspath,dataconf)
        _token_ = self._peek('"permit"', '"deny"')
        if _token_ == '"permit"':
            self._scan('"permit"')
            aspath["state"] = "permit"
        else:# == '"deny"'
            self._scan('"deny"')
            aspath["state"] = "deny"
        EXP = self._scan('EXP')
        aspath["regexp"] = EXP
        END = self._scan('END')

    def Prefix_list(self, listtype,dataconf):
        prefixlist = {}
        self._scan('"prefix-list"')
        STR = self._scan('STR')
        prefixlist["name"] = STR
        prefixlist = getListFrom(listtype+"prefix-list",prefixlist,dataconf)
        _token_ = self._peek('"description"', '"seq"')
        if _token_ == '"description"':
            self._scan('"description"')
            prefixlist["description"] = ""
            while 1:
                EXP = self._scan('EXP')
                prefixlist["description"] = prefixlist["description"] +" "+ EXP
                if self._peek('EXP', 'END') != 'EXP': break
        else:# == '"seq"'
            sequence = {}
            self._scan('"seq"')
            NUMBER = self._scan('NUMBER')
            sequence["seq"] = NUMBER
            _token_ = self._peek('"permit"', '"deny"')
            if _token_ == '"permit"':
                self._scan('"permit"')
                sequence["state"] = "permit"
            else:# == '"deny"'
                self._scan('"deny"')
                sequence["state"] = "deny"
            _token_ = self._peek('"any"', 'IPV4', 'IPV6')
            if _token_ == '"any"':
                self._scan('"any"')
                sequence["ip-prefix"]= "any"
            else:# in ['IPV4', 'IPV6']
                _token_ = self._peek('IPV4', 'IPV6')
                if _token_ == 'IPV4':
                    IPV4 = self._scan('IPV4')
                    sequence["ip-prefix"]= IPV4
                else:# == 'IPV6'
                    IPV6 = self._scan('IPV6')
                    sequence["ip-prefix"]= IPV6
                self._scan('"/"')
                NUMBER = self._scan('NUMBER')
                sequence["mask"]= NUMBER
                if self._peek('"ge"', '"le"', 'EXP', 'END') == '"ge"':
                    self._scan('"ge"')
                    NUMBER = self._scan('NUMBER')
                    sequence["ge"]= NUMBER
                if self._peek('"le"', 'EXP', 'END') == '"le"':
                    self._scan('"le"')
                    NUMBER = self._scan('NUMBER')
                    sequence["le"]= NUMBER
            appendSequence(sequence,prefixlist)
        END = self._scan('END')

    def Route_map(self, listtype,dataconf):
        routemap = {}
        self._scan('"route-map"')
        STR = self._scan('STR')
        routemap["map-tag"] = STR
        routemap = getListFrom(listtype,routemap,dataconf)
        sequence = {}
        _token_ = self._peek('"permit"', '"deny"')
        if _token_ == '"permit"':
            self._scan('"permit"')
            sequence["state"] = "permit"
        else:# == '"deny"'
            self._scan('"deny"')
            sequence["state"] = "deny"
        NUMBER = self._scan('NUMBER')
        sequence["seq-number"]= NUMBER
        appendSequence(sequence,routemap)
        END = self._scan('END')
        while self._peek('"match"', '"set"', '"ipv6"', '"ip"', '"access-list"', '"route-map"', 'ENDFILE') == '"match"':
            Match = self.Match(sequence)
        while self._peek('"set"', '"ipv6"', '"ip"', '"access-list"', '"route-map"', 'ENDFILE') == '"set"':
            Set = self.Set(sequence)

    def Match(self, route_map):
        self._scan('"match"')
        match = getMatchFrom(route_map)
        _token_ = self._peek('"metric"', '"origin"', '"as-path"', '"ip"', '"ipv6"', '"community"', '"extcommunity"')
        if _token_ == '"metric"':
            self._scan('"metric"')
            while 1:
                NUMBER = self._scan('NUMBER')
                appendValueTo(match,"metric","metric-value",NUMBER)
                if self._peek('NUMBER', 'STR', 'IPV6', 'END') != 'NUMBER': break
        elif _token_ == '"origin"':
            self._scan('"origin"')
            EXP = self._scan('EXP')
            match["origin"] = EXP
        elif _token_ == '"as-path"':
            self._scan('"as-path"')
            while 1:
                STR = self._scan('STR')
                appendValueTo(match,"as-path","as-path-name",STR)
                if self._peek('STR', 'NUMBER', 'IPV6', 'END') != 'STR': break
        elif _token_ == '"ip"':
            self._scan('"ip"')
            _token_ = self._peek('"address"', '"next-hop"')
            if _token_ == '"address"':
                self._scan('"address"')
                _token_ = self._peek('"prefix-list"', 'STR')
                if _token_ == '"prefix-list"':
                    self._scan('"prefix-list"')
                    while 1:
                        STR = self._scan('STR')
                        appendValueTo(match,"prefix-list","list-name",STR)
                        if self._peek('STR', 'NUMBER', 'IPV6', 'END') != 'STR': break
                else:# == 'STR'
                    while 1:
                        STR = self._scan('STR')
                        appendValueTo(match,"access-list","list-name",STR)
                        if self._peek('STR', 'NUMBER', 'IPV6', 'END') != 'STR': break
            else:# == '"next-hop"'
                self._scan('"next-hop"')
                _token_ = self._peek('"prefix-list"', 'STR')
                if _token_ == '"prefix-list"':
                    self._scan('"prefix-list"')
                    while 1:
                        STR = self._scan('STR')
                        appendValueTo(match,"next-hop-prefix-list","list-name",STR)
                        if self._peek('STR', 'NUMBER', 'IPV6', 'END') != 'STR': break
                else:# == 'STR'
                    while 1:
                        STR = self._scan('STR')
                        appendValueTo(match,"next-hop-access-list","list-name",STR)
                        if self._peek('STR', 'NUMBER', 'IPV6', 'END') != 'STR': break
        elif _token_ == '"ipv6"':
            self._scan('"ipv6"')
            _token_ = self._peek('"address"', '"next-hop"')
            if _token_ == '"address"':
                self._scan('"address"')
                _token_ = self._peek('"prefix-list"', 'STR')
                if _token_ == '"prefix-list"':
                    self._scan('"prefix-list"')
                    while 1:
                        STR = self._scan('STR')
                        appendValueTo(match,"ipv6-prefix-list","list-name",STR)
                        if self._peek('STR', 'IPV6', 'NUMBER', 'END') != 'STR': break
                else:# == 'STR'
                    while 1:
                        STR = self._scan('STR')
                        appendValueTo(match,"ipv6-access-list","list-name",STR)
                        if self._peek('STR', 'IPV6', 'NUMBER', 'END') != 'STR': break
            else:# == '"next-hop"'
                self._scan('"next-hop"')
                while 1:
                    IPV6 = self._scan('IPV6')
                    appendValueTo(match,"ipv6-next-hop","ip",IPV6)
                    if self._peek('IPV6', 'STR', 'NUMBER', 'END') != 'IPV6': break
        elif _token_ == '"community"':
            self._scan('"community"')
            STR = self._scan('STR')
            match["community"] = {"name": STR}
            if self._peek('"exact-match"', 'NUMBER', 'STR', 'IPV6', 'END') == '"exact-match"':
                self._scan('"exact-match"')
            match["community"]["exact-match"] = True
        else:# == '"extcommunity"'
            self._scan('"extcommunity"')
            STR = self._scan('STR')
            match["extcommunity"] = STR
        END = self._scan('END')

    def Set(self, route_map):
        self._scan('"set"')
        set = getSetFrom(route_map)
        _token_ = self._peek('"metric"', '"weight"', '"local-preference"', '"origin"', '"originator-id"', '"atomic-aggregate"', '"aggregator as"', '"as-path prepend"', '"ip"', '"vpnv4"', '"ipv6"', '"comm-list"', '"community"', '"extcommunity"')
        if _token_ == '"metric"':
            self._scan('"metric"')
            metric = {}
            if self._peek('"\\+"', '"-"', 'NUMBER') != 'NUMBER':
                _token_ = self._peek('"\\+"', '"-"')
                if _token_ == '"\\+"':
                    self._scan('"\\+"')
                    metric["negative"] = False
                else:# == '"-"'
                    self._scan('"-"')
                    metric["negative"] = True
            NUMBER = self._scan('NUMBER')
            metric["value"] = NUMBER
            set["metric"] = metric
        elif _token_ == '"weight"':
            self._scan('"weight"')
            NUMBER = self._scan('NUMBER')
            set["weight"] = NUMBER
        elif _token_ == '"local-preference"':
            self._scan('"local-preference"')
            NUMBER = self._scan('NUMBER')
            set["local-preference"] = NUMBER
        elif _token_ == '"origin"':
            self._scan('"origin"')
            EXP = self._scan('EXP')
            set["origin"] = EXP
        elif _token_ == '"originator-id"':
            self._scan('"originator-id"')
            IPV4 = self._scan('IPV4')
            set["originator-id"] = IPV4
        elif _token_ == '"atomic-aggregate"':
            self._scan('"atomic-aggregate"')
            set["atomic-aggregate"] = True
        elif _token_ == '"aggregator as"':
            self._scan('"aggregator as"')
            NUMBER = self._scan('NUMBER')
            IPV4 = self._scan('IPV4')
            set["aggregator"] = {"router-as":NUMBER,"router-id":IPV4}
        elif _token_ == '"as-path prepend"':
            self._scan('"as-path prepend"')
            while self._peek('NUMBER', 'EXP', 'END') == 'NUMBER':
                NUMBER = self._scan('NUMBER')
                appendValueTo(set,"as-path","prepend",NUMBER)
        elif _token_ == '"ip"':
            self._scan('"ip"')
            self._scan('"next-hop"')
            _token_ = self._peek('"peer-group"', 'IPV4')
            if _token_ == '"peer-group"':
                self._scan('"peer-group"')
                STR = self._scan('STR')
                set["next-hop"] = { "peer-group" : STR}
            else:# == 'IPV4'
                IPV4 = self._scan('IPV4')
                set["next-hop"] = { "ipv4address" : IPV4}
        elif _token_ == '"vpnv4"':
            self._scan('"vpnv4"')
            self._scan('"next-hop"')
            IPV4 = self._scan('IPV4')
            set["vpnv4-next-hop"] = IPV4
        elif _token_ == '"ipv6"':
            self._scan('"ipv6"')
            self._scan('"next-hop"')
            if (not "ipv6-next-hop" in set) : set["ipv6-next-hop"] = {}
            _token_ = self._peek('"local"', '"global"')
            if _token_ == '"local"':
                self._scan('"local"')
                IPV6 = self._scan('IPV6')
                set["ipv6-next-hop"]["local"] = IPV6
            else:# == '"global"'
                self._scan('"global"')
                IPV6 = self._scan('IPV6')
                set["ipv6-next-hop"]["global"] = IPV6
        elif _token_ == '"comm-list"':
            self._scan('"comm-list"')
            STR = self._scan('STR')
            self._scan('"delete"')
            match["comm-list-del"] = STR
        elif _token_ == '"community"':
            self._scan('"community"')
            _token_ = self._peek('"none"', 'EXP')
            if _token_ == '"none"':
                self._scan('"none"')
                match["communities"] = []
            else:# == 'EXP'
                while 1:
                    EXP = self._scan('EXP')
                    appendValueTo(set,"communities","comunity",EXP)
                    if self._peek('EXP', 'NUMBER', 'END') != 'EXP': break
        else:# == '"extcommunity"'
            self._scan('"extcommunity"')
            _token_ = self._peek('"rt"', '"soo"')
            if _token_ == '"rt"':
                self._scan('"rt"')
                community= {"type":"rt" ,"value":[]}
            else:# == '"soo"'
                self._scan('"soo"')
                community= {"type":"soo","value":[]}
            while 1:
                EXP = self._scan('EXP')
                community["value"].append(EXP)
                if self._peek('EXP', 'NUMBER', 'END') != 'EXP': break
            appendValueTo(set,"extcommunities","extcomunity",community)
        END = self._scan('END')

    def BGPConf(self):
        bgpconf = {"ipv4-unicast": True}
        self._scan('"router bgp"')
        AS_NUMBER = self._scan('AS_NUMBER')
        END = self._scan('END')
        bgpconf["as-number"] = AS_NUMBER
        if self._peek('"no bgp fast-external-failover"', '"bgp router-id"', '"bgp log-neighbor-changes"', '"bgp always-compare-med"', '"no bgp default ipv4-unicast"', '"bgp default local-preference"', '"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"no bgp fast-external-failover"':
            self._scan('"no bgp fast-external-failover"')
            END = self._scan('END')
            bgpconf["fast-external-failover"] = False
        if self._peek('"bgp router-id"', '"bgp log-neighbor-changes"', '"bgp always-compare-med"', '"no bgp default ipv4-unicast"', '"bgp default local-preference"', '"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp router-id"':
            self._scan('"bgp router-id"')
            IPV4 = self._scan('IPV4')
            END = self._scan('END')
            bgpconf["id"] = IPV4
        if self._peek('"bgp log-neighbor-changes"', '"bgp always-compare-med"', '"no bgp default ipv4-unicast"', '"bgp default local-preference"', '"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp log-neighbor-changes"':
            self._scan('"bgp log-neighbor-changes"')
            END = self._scan('END')
            bgpconf["log-neighbor-changes"] = True
        if self._peek('"bgp always-compare-med"', '"no bgp default ipv4-unicast"', '"bgp default local-preference"', '"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp always-compare-med"':
            self._scan('"bgp always-compare-med"')
            END = self._scan('END')
            bgpconf["always-compare-med"] = True
        if self._peek('"no bgp default ipv4-unicast"', '"bgp default local-preference"', '"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"no bgp default ipv4-unicast"':
            self._scan('"no bgp default ipv4-unicast"')
            END = self._scan('END')
            bgpconf["ipv4-unicast"] = False
        if self._peek('"bgp default local-preference"', '"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp default local-preference"':
            self._scan('"bgp default local-preference"')
            NUMBER = self._scan('NUMBER')
            END = self._scan('END')
            bgpconf["local-preference"] = NUMBER
        if self._peek('"no bgp client-to-client reflection"', '"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"no bgp client-to-client reflection"':
            self._scan('"no bgp client-to-client reflection"')
            END = self._scan('END')
            bgpconf["client-to-client-reflection"] = False
        if self._peek('"bgp cluster-id"', '"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp cluster-id"':
            self._scan('"bgp cluster-id"')
            _token_ = self._peek('IPV4', 'NUMBER')
            if _token_ == 'IPV4':
                IPV4 = self._scan('IPV4')
                bgpconf["cluster-id"] = IPV4
            else:# == 'NUMBER'
                NUMBER = self._scan('NUMBER')
                bgpconf["cluster-id"] = NUMBER
            END = self._scan('END')
        if self._peek('"bgp confederation identifier"', '"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp confederation identifier"':
            self._scan('"bgp confederation identifier"')
            NUMBER = self._scan('NUMBER')
            END = self._scan('END')
            bgpconf["confederation-id"] = NUMBER
        if self._peek('"bgp confederation peers"', '"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp confederation peers"':
            self._scan('"bgp confederation peers"')
            bgpconf["confederation-peers"]= []
            while 1:
                NUMBER = self._scan('NUMBER')
                bgpconf["confederation-peers"].append(NUMBER)
                if self._peek('NUMBER', 'END') != 'NUMBER': break
            END = self._scan('END')
        if self._peek('"bgp enforce-first-as"', '"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp enforce-first-as"':
            self._scan('"bgp enforce-first-as"')
            END = self._scan('END')
            bgpconf["enforce-first-as"] = True
        if self._peek('"bgp deterministic-med"', '"bgp network import-check"', '"bgp bestpath"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp deterministic-med"':
            self._scan('"bgp deterministic-med"')
            END = self._scan('END')
            bgpconf["deterministic-med"] = True
        if 1:
            BGPBestPath = self.BGPBestPath()
            appendBGPBestPath(bgpconf,BGPBestPath)
        if self._peek('"bgp network import-check"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp network import-check"':
            self._scan('"bgp network import-check"')
            END = self._scan('END')
            bgpconf["network-import-check"] = False
        if self._peek('"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp scan-time"':
            self._scan('"bgp scan-time"')
            NUMBER = self._scan('NUMBER')
            END = self._scan('END')
            bgpconf["scan-time"] = NUMBER
        if self._peek('"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp dampening"':
            self._scan('"bgp dampening"')
            bgpconf["dampening"] = {}
            if self._peek('NUMBER', 'END') == 'NUMBER':
                NUMBER = self._scan('NUMBER')
                bgpconf["dampening"]["half-life"] = NUMBER
                if self._peek('NUMBER', 'END') == 'NUMBER':
                    NUMBER = self._scan('NUMBER')
                    bgpconf["dampening"]["reuse"] = NUMBER
                    NUMBER = self._scan('NUMBER')
                    bgpconf["dampening"]["suppress"] = NUMBER
                    NUMBER = self._scan('NUMBER')
                    bgpconf["dampening"]["max-suppress-time"] = NUMBER
            END = self._scan('END')
        while self._peek('"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"network"':
            Network_IPv4 = self.Network_IPv4(bgpconf)
        while self._peek('"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"redistribute"':
            Redistribute_IPv4Unicast = self.Redistribute_IPv4Unicast(bgpconf)
        while self._peek('"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"aggregate-address"':
            AggregateAddress_IPV4 = self.AggregateAddress_IPV4(bgpconf)
        while self._peek('"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') in ['"neighbor"', '"no neighbor"']:
            Neighbor = self.Neighbor(bgpconf,bgpconf["ipv4-unicast"])
        if self._peek('"distance bgp"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"distance bgp"':
            self._scan('"distance bgp"')
            NUMBER = self._scan('NUMBER')
            dist = {"external":NUMBER}
            NUMBER = self._scan('NUMBER')
            dist["internal"] = NUMBER
            NUMBER = self._scan('NUMBER')
            dist["local"] = NUMBER
            bgpconf["distance-bgp"] = dist
            END = self._scan('END')
        while self._peek('"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"distance"':
            Distance = self.Distance(bgpconf)
        if 1:
            Address_Family = self.Address_Family()
            appendAddressFamilies(bgpconf,Address_Family)
        return bgpconf

    def BGPBestPath(self):
        bestpath = {}
        if self._peek('"bgp bestpath"', '"bgp network import-check"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp bestpath"':
            self._scan('"bgp bestpath"')
            self._scan('"as-path"')
            self._scan('"ignore"')
            END = self._scan('END')
            bestpath["as-path-ignore"] = True
        if self._peek('"bgp bestpath"', '"bgp network import-check"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp bestpath"':
            self._scan('"bgp bestpath"')
            self._scan('"compare-routerid"')
            END = self._scan('END')
            bestpath["compare-routerid"] = True
        if self._peek('"bgp bestpath"', '"bgp network import-check"', '"bgp scan-time"', '"bgp dampening"', '"network"', '"redistribute"', '"aggregate-address"', '"distance bgp"', '"neighbor"', '"no neighbor"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"exit-address-family"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"bgp bestpath"':
            self._scan('"bgp bestpath"')
            self._scan('"med"')
            bestpath["med"] = {}
            if self._peek('"confed"', '"missing-as-worst"', 'END') != 'END':
                _token_ = self._peek('"confed"', '"missing-as-worst"')
                if _token_ == '"confed"':
                    self._scan('"confed"')
                    bestpath["med"]["confed"] = True
                    if self._peek('"missing-as-worst"', 'END') == '"missing-as-worst"':
                        self._scan('"missing-as-worst"')
                        bestpath["med"]["missing-as-worst"] = True
                else:# == '"missing-as-worst"'
                    self._scan('"missing-as-worst"')
                    bestpath["med"]["missing-as-worst"] = True
                    if self._peek('"confed"', 'END') == '"confed"':
                        self._scan('"confed"')
                        bestpath["med"]["confed"] = True
            END = self._scan('END')
        return bestpath

    def Network_IPv4(self, dataconf):
        network = {}
        self._scan('"network"')
        IPV4 = self._scan('IPV4')
        network["ip"] = IPV4
        network = getNetworkFrom(network,dataconf)
        _token_ = self._peek('"mask"', '"\\/"', '"route-map"')
        if _token_ != '"route-map"':
            _token_ = self._peek('"mask"', '"\\/"')
            if _token_ == '"mask"':
                self._scan('"mask"')
                IPV4 = self._scan('IPV4')
                network["mask"] = IPV4
            else:# == '"\\/"'
                self._scan('"\\/"')
                LEN_MASK = self._scan('LEN_MASK')
                network["mask"] = LEN_MASK
            if self._peek('"backdoor"', '"route-map"', 'END') != 'END':
                _token_ = self._peek('"backdoor"', '"route-map"')
                if _token_ == '"backdoor"':
                    self._scan('"backdoor"')
                    network["backdoor"] = True
                else:# == '"route-map"'
                    self._scan('"route-map"')
                    STR = self._scan('STR')
                    network["route-map"] = STR
        else:# == '"route-map"'
            self._scan('"route-map"')
            STR = self._scan('STR')
            network["route-map"] = STR
        END = self._scan('END')

    def Network_IPv6Unicast(self, dataconf):
        network = {}
        self._scan('"network"')
        IPV6 = self._scan('IPV6')
        network["ip"] = IPV6
        network = getNetworkFrom(network,dataconf)
        self._scan('"\\/"')
        LEN_MASK = self._scan('LEN_MASK')
        network["mask"] = LEN_MASK
        if self._peek('"route-map"', 'END') == '"route-map"':
            self._scan('"route-map"')
            STR = self._scan('STR')
            network["route-map"] = STR
        END = self._scan('END')

    def Network_VPNv4Unicast(self, dataconf):
        network = {}
        self._scan('"network"')
        IPV4 = self._scan('IPV4')
        network["ip"] = IPV4
        network = getNetworkFrom(network,dataconf)
        self._scan('"\\/"')
        LEN_MASK = self._scan('LEN_MASK')
        network["mask"] = LEN_MASK
        self._scan('"rd"')
        _token_ = self._peek('AS_NUMBER', 'IPV4')
        if _token_ == 'AS_NUMBER':
            AS_NUMBER = self._scan('AS_NUMBER')
            network["rd"] = AS_NUMBER
        else:# == 'IPV4'
            IPV4 = self._scan('IPV4')
            network["rd"] = IPV4
        self._scan('"tag"')
        STR = self._scan('STR')
        network["tag"] = STR
        END = self._scan('END')

    def Redistribute_IPv4Unicast(self, dataconf):
        redistribute = {}
        self._scan('"redistribute"')
        _token_ = self._peek('"kernel"', '"connected"', '"static"', '"rip"', '"ospf"')
        if _token_ == '"kernel"':
            self._scan('"kernel"')
            redistribute["type"] = "kernel"
        elif _token_ == '"connected"':
            self._scan('"connected"')
            redistribute["type"] = "connected"
        elif _token_ == '"static"':
            self._scan('"static"')
            redistribute["type"] = "static"
        elif _token_ == '"rip"':
            self._scan('"rip"')
            redistribute["type"] = "rip"
        else:# == '"ospf"'
            self._scan('"ospf"')
            redistribute["type"] = "ospf"
        if self._peek('"metric"', '"route-map"', 'END') != 'END':
            _token_ = self._peek('"metric"', '"route-map"')
            if _token_ == '"metric"':
                self._scan('"metric"')
                NUMBER = self._scan('NUMBER')
                redistribute["metric"] = NUMBER
                if self._peek('"route-map"', 'END') == '"route-map"':
                    self._scan('"route-map"')
                    STR = self._scan('STR')
                    redistribute["route-map"] = STR
            else:# == '"route-map"'
                self._scan('"route-map"')
                STR = self._scan('STR')
                redistribute["route-map"] = STR
                if self._peek('"metric"', 'END') == '"metric"':
                    self._scan('"metric"')
                    NUMBER = self._scan('NUMBER')
                    redistribute["metric"] = NUMBER
        END = self._scan('END')
        appendRedistribute(dataconf,redistribute)

    def Redistribute_IPv6Unicast(self, dataconf):
        redistribute = {}
        self._scan('"redistribute"')
        _token_ = self._peek('"kernel"', '"connected"', '"static"', '"ripng"', '"ospf6"')
        if _token_ == '"kernel"':
            self._scan('"kernel"')
            redistribute["type"] = "kernel"
        elif _token_ == '"connected"':
            self._scan('"connected"')
            redistribute["type"] = "connected"
        elif _token_ == '"static"':
            self._scan('"static"')
            redistribute["type"] = "static"
        elif _token_ == '"ripng"':
            self._scan('"ripng"')
            redistribute["type"] = "ripng"
        else:# == '"ospf6"'
            self._scan('"ospf6"')
            redistribute["type"] = "ospf6"
        if self._peek('"metric"', '"route-map"', 'END') != 'END':
            _token_ = self._peek('"metric"', '"route-map"')
            if _token_ == '"metric"':
                self._scan('"metric"')
                NUMBER = self._scan('NUMBER')
                redistribute["metric"] = NUMBER
                if self._peek('"route-map"', 'END') == '"route-map"':
                    self._scan('"route-map"')
                    STR = self._scan('STR')
                    redistribute["route-map"] = STR
            else:# == '"route-map"'
                self._scan('"route-map"')
                STR = self._scan('STR')
                redistribute["route-map"] = STR
                if self._peek('"metric"', 'END') == '"metric"':
                    self._scan('"metric"')
                    NUMBER = self._scan('NUMBER')
                    redistribute["metric"] = NUMBER
        END = self._scan('END')
        appendRedistribute(dataconf,redistribute)

    def AggregateAddress_IPV4(self, dataconf):
        address ={}
        self._scan('"aggregate-address"')
        IPV4 = self._scan('IPV4')
        address["ip-prefix"] = IPV4
        address = getAggregateAddressFrom(address,dataconf)
        _token_ = self._peek('"\\/"', 'IPV4')
        if _token_ == '"\\/"':
            self._scan('"\\/"')
            LEN_MASK = self._scan('LEN_MASK')
            address["mask"] = LEN_MASK
        else:# == 'IPV4'
            IPV4 = self._scan('IPV4')
            address["mask"] = IPV4
        if self._peek('"as-set"', '"summary-only"', 'END') != 'END':
            _token_ = self._peek('"as-set"', '"summary-only"')
            if _token_ == '"as-set"':
                self._scan('"as-set"')
                address["as-set"] = True
            else:# == '"summary-only"'
                self._scan('"summary-only"')
                address["summary-only"] = True
        if self._peek('"as-set"', '"summary-only"', 'END') != 'END':
            _token_ = self._peek('"as-set"', '"summary-only"')
            if _token_ == '"as-set"':
                self._scan('"as-set"')
                address["as-set"] = True
            else:# == '"summary-only"'
                self._scan('"summary-only"')
                address["summary-only"] = True
        END = self._scan('END')

    def Neighbor(self, dataconf,default_activate):
        neighbor = {}
        _token_ = self._peek('"neighbor"', '"no neighbor"')
        if _token_ == '"neighbor"':
            self._scan('"neighbor"')
            _token_ = self._peek('IPV4', 'IPV6', 'STR')
            if _token_ == 'IPV4':
                IPV4 = self._scan('IPV4')
                neighbor["ip-address"] = IPV4
            elif _token_ == 'IPV6':
                IPV6 = self._scan('IPV6')
                neighbor["ip-address"] = IPV6
            else:# == 'STR'
                STR = self._scan('STR')
                neighbor["name"] = STR
            neighbor = getNeighborFrom(neighbor,dataconf,default_activate)
            _token_ = self._peek('"remote-as"', '"description"', '"shutdown"', '"port"', '"interface"', '"passive"', '"ebgp-multihop"', '"enforce-multihop"', '"update-source"', '"version"', '"advertisement-interval"', '"timers"', '"weight"', '"capability"', '"dont-capability-negotiate"', '"strict-capability-match"', '"override-capability"', '"peer-group"', '"activate"', '"capability orf"', '"route-reflector-client"', '"next-hop-self"', '"remove-private-AS"', '"default-originate"', '"soft-reconfiguration"', '"maximum-prefix"', '"route-server-client"', '"allowas-in"', '"attribute-unchanged"', '"distribute-list"', '"prefix-list"', '"filter-list"', '"route-map"', '"unsuppress-map"')
            if _token_ not in ['"peer-group"', '"activate"', '"capability orf"', '"route-reflector-client"', '"next-hop-self"', '"remove-private-AS"', '"default-originate"', '"soft-reconfiguration"', '"maximum-prefix"', '"route-server-client"', '"allowas-in"', '"attribute-unchanged"', '"distribute-list"', '"prefix-list"', '"filter-list"', '"route-map"', '"unsuppress-map"']:
                Neighbor__ = self.Neighbor__(neighbor)
            else:
                NeighborConf__ = self.NeighborConf__(neighbor)
        else:# == '"no neighbor"'
            self._scan('"no neighbor"')
            _token_ = self._peek('IPV4', 'IPV6', 'STR')
            if _token_ == 'IPV4':
                IPV4 = self._scan('IPV4')
                neighbor["ip-address"] = IPV4
            elif _token_ == 'IPV6':
                IPV6 = self._scan('IPV6')
                neighbor["ip-address"] = IPV6
            else:# == 'STR'
                STR = self._scan('STR')
                neighbor["name"] = STR
            neighbor = getNeighborFrom(neighbor,dataconf,default_activate)
            _token_ = self._peek('"capability"', '"send-community"', '"activate"')
            if _token_ == '"capability"':
                NoNeighbor__ = self.NoNeighbor__(neighbor)
            else:# in ['"send-community"', '"activate"']
                NoNeighborConf__ = self.NoNeighborConf__(neighbor)
        END = self._scan('END')

    def Neighbor__(self, neighbor):
        _token_ = self._peek('"remote-as"', '"description"', '"shutdown"', '"port"', '"interface"', '"passive"', '"ebgp-multihop"', '"enforce-multihop"', '"update-source"', '"version"', '"advertisement-interval"', '"timers"', '"weight"', '"capability"', '"dont-capability-negotiate"', '"strict-capability-match"', '"override-capability"')
        if _token_ == '"remote-as"':
            self._scan('"remote-as"')
            AS_NUMBER = self._scan('AS_NUMBER')
            neighbor["remote-as"]				= AS_NUMBER
        elif _token_ == '"description"':
            self._scan('"description"')
            neighbor["description"] = ""
            while 1:
                STR = self._scan('STR')
                neighbor["description"] = neighbor["description"] + " " + STR
                if self._peek('STR', 'END') != 'STR': break
        elif _token_ == '"shutdown"':
            self._scan('"shutdown"')
            neighbor["shutdown"]					= True
        elif _token_ == '"port"':
            self._scan('"port"')
            NUMBER = self._scan('NUMBER')
            neighbor["port"]						= NUMBER
        elif _token_ == '"interface"':
            self._scan('"interface"')
            EXP = self._scan('EXP')
            neighbor["interface"]				= EXP
        elif _token_ == '"passive"':
            self._scan('"passive"')
            neighbor["passive"]					= True
        elif _token_ == '"ebgp-multihop"':
            self._scan('"ebgp-multihop"')
            neighbor["ebgp-multihop"]			= {}
            if self._peek('NUMBER', 'STR', 'END') == 'NUMBER':
                NUMBER = self._scan('NUMBER')
                neighbor["ebgp-multihop"]["ttl"]		= NUMBER
        elif _token_ == '"enforce-multihop"':
            self._scan('"enforce-multihop"')
            neighbor["enforce-multihop"]			= True
        elif _token_ == '"update-source"':
            self._scan('"update-source"')
            EXP = self._scan('EXP')
            neighbor["update-source"]			= EXP
        elif _token_ == '"version"':
            self._scan('"version"')
            self._scan('"4-"')
            neighbor["version"]					= "4-"
        elif _token_ == '"advertisement-interval"':
            self._scan('"advertisement-interval"')
            NUMBER = self._scan('NUMBER')
            neighbor["advertisement-interval"]	= NUMBER
        elif _token_ == '"timers"':
            self._scan('"timers"')
            _token_ = self._peek('NUMBER', '"connect"')
            if _token_ == 'NUMBER':
                NUMBER = self._scan('NUMBER')
                neighbor["timers"]= {"keep-alive": NUMBER}
                NUMBER = self._scan('NUMBER')
                neighbor["timers"]["hold-time"]		= NUMBER
            else:# == '"connect"'
                self._scan('"connect"')
                NUMBER = self._scan('NUMBER')
                neighbor["timers-connect"]			= NUMBER
        elif _token_ == '"weight"':
            self._scan('"weight"')
            NUMBER = self._scan('NUMBER')
            neighbor["weight"]					= NUMBER
        elif _token_ == '"capability"':
            self._scan('"capability"')
            self._scan('"dynamic"')
            appendCapability(neighbor,"dynamic",True)
        elif _token_ == '"dont-capability-negotiate"':
            self._scan('"dont-capability-negotiate"')
            neighbor["dont-capability-negotiate"]= True
        elif _token_ == '"strict-capability-match"':
            self._scan('"strict-capability-match"')
            neighbor["strict-capability-match"]	= True
        else:# == '"override-capability"'
            self._scan('"override-capability"')
            neighbor["override-capability"]		= True

    def NoNeighbor__(self, neighbor):
        self._scan('"capability"')
        self._scan('"route-refresh"')
        appendCapability(neighbor,"route-refresh",False)

    def NeighborConf(self, dataconf):
        neighbor = {}
        _token_ = self._peek('"neighbor"', '"no neighbor"')
        if _token_ == '"neighbor"':
            self._scan('"neighbor"')
            _token_ = self._peek('IPV4', 'IPV6', 'STR')
            if _token_ == 'IPV4':
                IPV4 = self._scan('IPV4')
                neighbor["ip-address"] = IPV4
            elif _token_ == 'IPV6':
                IPV6 = self._scan('IPV6')
                neighbor["ip-address"] = IPV6
            else:# == 'STR'
                STR = self._scan('STR')
                neighbor["name"] = STR
            neighbor = getNeighborFrom(neighbor,dataconf)
            NeighborConf__ = self.NeighborConf__(neighbor)
        else:# == '"no neighbor"'
            self._scan('"no neighbor"')
            _token_ = self._peek('IPV4', 'IPV6', 'STR')
            if _token_ == 'IPV4':
                IPV4 = self._scan('IPV4')
                neighbor["ip-address"] = IPV4
            elif _token_ == 'IPV6':
                IPV6 = self._scan('IPV6')
                neighbor["ip-address"] = IPV6
            else:# == 'STR'
                STR = self._scan('STR')
                neighbor["name"] = STR
            neighbor = getNeighborFrom(neighbor,dataconf)
            NoNeighborConf__ = self.NoNeighborConf__(neighbor)
        END = self._scan('END')

    def NeighborConf__(self, neighbor):
        _token_ = self._peek('"peer-group"', '"activate"', '"capability orf"', '"route-reflector-client"', '"next-hop-self"', '"remove-private-AS"', '"default-originate"', '"soft-reconfiguration"', '"maximum-prefix"', '"route-server-client"', '"allowas-in"', '"attribute-unchanged"', '"distribute-list"', '"prefix-list"', '"filter-list"', '"route-map"', '"unsuppress-map"')
        if _token_ == '"peer-group"':
            self._scan('"peer-group"')
            if self._peek('STR', 'END') == 'STR':
                STR = self._scan('STR')
                neighbor["activated-group"] = STR
        elif _token_ == '"activate"':
            self._scan('"activate"')
            neighbor["activate"]					= True
        elif _token_ == '"capability orf"':
            self._scan('"capability orf"')
            self._scan('"prefix-list"')
            STR = self._scan('STR')
            neighbor["capability-orf"]			= {"prefix-list": STR }
        elif _token_ == '"route-reflector-client"':
            self._scan('"route-reflector-client"')
            neighbor["route-reflector-client"]	= True
        elif _token_ == '"next-hop-self"':
            self._scan('"next-hop-self"')
            neighbor["next-hop-self"]			= True
        elif _token_ == '"remove-private-AS"':
            self._scan('"remove-private-AS"')
            neighbor["remove-private-as"]		= True
        elif _token_ == '"default-originate"':
            self._scan('"default-originate"')
            neighbor["default-originate"]		= {}
            if self._peek('"route-map"', 'STR', 'END') == '"route-map"':
                self._scan('"route-map"')
                STR = self._scan('STR')
                neighbor["default-originate"]["route-map"] = STR
        elif _token_ == '"soft-reconfiguration"':
            self._scan('"soft-reconfiguration"')
            self._scan('"inbound"')
            neighbor["soft-reconfiguration"]		= "inbound"
        elif _token_ == '"maximum-prefix"':
            self._scan('"maximum-prefix"')
            NUMBER = self._scan('NUMBER')
            neighbor["maximum-prefix"]			= {"maximum":NUMBER}
            if self._peek('"warning-only"', 'STR', 'END') == '"warning-only"':
                self._scan('"warning-only"')
                neighbor["maximum-prefix"]["warning-only"] = True
        elif _token_ == '"route-server-client"':
            self._scan('"route-server-client"')
            neighbor["router-server-client"]			= True
        elif _token_ == '"allowas-in"':
            self._scan('"allowas-in"')
            neighbor["allowas-in"]				= {}
            if self._peek('NUMBER', 'STR', 'END') == 'NUMBER':
                NUMBER = self._scan('NUMBER')
                neighbor["allowas-in"]["quant"]		= NUMBER
        elif _token_ != '"attribute-unchanged"':
            BindFilters = self.BindFilters(neighbor)
        else:# == '"attribute-unchanged"'
            self._scan('"attribute-unchanged"')
            neighbor["attribute-unchanged"]		= {}
            if self._peek('"as-path"', '"next-hop"', '"med"', 'STR', 'END') == '"as-path"':
                self._scan('"as-path"')
                neighbor["attribute-unchanged"]["as-path"] = True
            if self._peek('"next-hop"', '"med"', 'STR', 'END') == '"next-hop"':
                self._scan('"next-hop"')
                neighbor["attribute-unchanged"]["next-hop"] = True
            if self._peek('"med"', 'STR', 'END') == '"med"':
                self._scan('"med"')
                neighbor["attribute-unchanged"]["med"] = True

    def NoNeighborConf__(self, neighbor):
        neighbor["send-community"] = {"type":"both"}
        _token_ = self._peek('"send-community"', '"activate"')
        if _token_ == '"send-community"':
            self._scan('"send-community"')
            neighbor["send-community"]["type"] ="extended"
            if self._peek('"both"', '"extended"', 'END', 'STR') in ['"both"', '"extended"']:
                _token_ = self._peek('"both"', '"extended"')
                if _token_ == '"both"':
                    self._scan('"both"')
                    del(neighbor["send-community"])
                else:# == '"extended"'
                    self._scan('"extended"')
                    neighbor["send-community"]["type"] = "standard"
        else:# == '"activate"'
            self._scan('"activate"')
            neighbor["activate"]					= False

    def BindFilters(self, neighbor):
        filter = {}
        _token_ = self._peek('"distribute-list"', '"prefix-list"', '"filter-list"', '"route-map"', '"unsuppress-map"')
        if _token_ == '"distribute-list"':
            self._scan('"distribute-list"')
            filtertype  = "distribute-list"
        elif _token_ == '"prefix-list"':
            self._scan('"prefix-list"')
            filtertype  = "prefix-list"
        elif _token_ == '"filter-list"':
            self._scan('"filter-list"')
            filtertype  = "filter-list"
        elif _token_ == '"route-map"':
            self._scan('"route-map"')
            filtertype  = "route-map"
        else:# == '"unsuppress-map"'
            self._scan('"unsuppress-map"')
            filtertype  = "unsuppress-map"
        STR = self._scan('STR')
        filter["name"]   = STR
        _token_ = self._peek('"in"', '"out"')
        if _token_ == '"in"':
            self._scan('"in"')
            filter["direct"] = "in"
        else:# == '"out"'
            self._scan('"out"')
            filter["direct"] = "out"
        bindFilterTo(filtertype,filter,neighbor)

    def Distance(self, dataconf):
        distance = {}
        self._scan('"distance"')
        NUMBER = self._scan('NUMBER')
        distance["value"] = NUMBER
        IPV4 = self._scan('IPV4')
        distance["ip"] = IPV4
        self._scan('"\\/"')
        NUMBER = self._scan('NUMBER')
        distance["mask"] = NUMBER
        if self._peek('STR', 'END') == 'STR':
            STR = self._scan('STR')
            distance["word"] = STR
        END = self._scan('END')
        bindDistanceTo(distance,dataconf)

    def Address_Family(self):
        address_family = {}
        if self._peek('"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"address-family ipv4"':
            self._scan('"address-family ipv4"')
            if self._peek('"multicast"', 'END') == '"multicast"':
                self._scan('"multicast"')
            END = self._scan('END')
            IPv4Multicast_Address_Family = self.IPv4Multicast_Address_Family()
            address_family["ipv4-multicast"] =IPv4Multicast_Address_Family
            self._scan('"exit-address-family"')
            END = self._scan('END')
        if self._peek('"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"address-family vpnv4"':
            self._scan('"address-family vpnv4"')
            if self._peek('"unicast"', 'END') == '"unicast"':
                self._scan('"unicast"')
            END = self._scan('END')
            VPNv4_Address_Family = self.VPNv4_Address_Family()
            address_family["vpnv4-unicast"] =VPNv4_Address_Family
            self._scan('"exit-address-family"')
            END = self._scan('END')
        if self._peek('"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"address-family ipv6"':
            self._scan('"address-family ipv6"')
            if self._peek('"unicast"', 'END') == '"unicast"':
                self._scan('"unicast"')
            END = self._scan('END')
            IPv6Unicast_Address_Family = self.IPv6Unicast_Address_Family()
            address_family["ipv6-unicast"] =IPv6Unicast_Address_Family
            self._scan('"exit-address-family"')
            END = self._scan('END')
        return address_family

    def IPv4Multicast_Address_Family(self):
        ipv4conf ={}
        while self._peek('"network"', '"aggregate-address"', '"neighbor"', '"no neighbor"', '"redistribute"', '"distance bgp"', '"exit-address-family"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"network"':
            Network_IPv4 = self.Network_IPv4(ipv4conf)
        while self._peek('"aggregate-address"', '"neighbor"', '"no neighbor"', '"distance bgp"', '"exit-address-family"', '"distance"', '"address-family ipv4"', '"address-family vpnv4"', '"address-family ipv6"', '"router bgp"', '"ip community-list"', '"ip extcommunity-list"', '"ipv6"', '"ip"', 'ENDFILE', '"access-list"', '"route-map"', '"set"') == '"aggregate-address"':
            AggregateAddress_IPV4 = self.AggregateAddress_IPV4(ipv4conf)
        while self._peek('"neighbor"', '"no neighbor"', '"exit-address-family"') != '"exit-address-family"':
            NeighborConf = self.NeighborConf(ipv4conf)
        return ipv4conf

    def VPNv4_Address_Family(self):
        vpnv4conf ={}
        while self._peek('"network"', '"neighbor"', '"no neighbor"', '"exit-address-family"') == '"network"':
            Network_VPNv4Unicast = self.Network_VPNv4Unicast(vpnv4conf)
        while self._peek('"neighbor"', '"no neighbor"', '"exit-address-family"') != '"exit-address-family"':
            NeighborConf = self.NeighborConf(ipv4conf)
        return ipv4conf

    def IPv6Unicast_Address_Family(self):
        ipv6conf ={}
        while self._peek('"network"', '"redistribute"', '"neighbor"', '"no neighbor"', '"exit-address-family"') == '"network"':
            Network_IPv6Unicast = self.Network_IPv6Unicast(ipv6conf)
        while self._peek('"redistribute"', '"neighbor"', '"no neighbor"', '"exit-address-family"') == '"redistribute"':
            Redistribute_IPv6Unicast = self.Redistribute_IPv6Unicast(ipv6conf)
        while self._peek('"neighbor"', '"no neighbor"', '"exit-address-family"') != '"exit-address-family"':
            NeighborConf = self.NeighborConf(ipv6conf)
        return ipv6conf


def parse(rule, text):
    P = BGPParser(BGPParserScanner(text))
    return wrap_error_reporter(P, rule)





