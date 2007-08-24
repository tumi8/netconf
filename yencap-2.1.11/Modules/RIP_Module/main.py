import os, string

from Ft.Xml.Domlette import NonvalidatingReader, implementation, PrettyPrint
from Ft.Xml import XPath, EMPTY_NAMESPACE
from xml.dom import Node
from Ft.Xml.XPath import Evaluate, Compile
from Ft.Xml.XPath.Context import Context


from RIP_Manager import RIP_Manager 
from RIP_Commands.Redistribute_RC import Redistribute_RC
from RIP_Commands.Network_RC import Network_RC
from RIP_Commands.Neighbor_RC import Neighbor_RC
from RIP_Commands.PassiveInterface_RC import PassiveInterface_RC
from RIP_Commands.DistributeList_RC import DistributeList_RC
from RIP_Commands.Route_RC import Route_RC


def findEditOp():
	result = {}
	NS_Netconf = "urn:ietf:params:xml:ns:netconf:base:1.0"
	NS_YencaP = "urn:loria:madynes:ensuite:yencap:1.0"
	NS_RIP_Module = "urn:loria:madynes:ensuite:yencap:module:RIP:1.0"

	NSS = {u"xc": NS_Netconf, u"yp": NS_YencaP, u"rip": NS_RIP_Module }
	ctx = Context(doc, processorNss = NSS)
	nodes = Evaluate(u"//*[@xc:operation]",ctx)
	
	print nodes

	for node in nodes:
		result[node] = node.getAttributeNS(NS_Netconf, "operation")

	return result


# Sample database of edit-config XML operations

m1 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <redistribute xc:operation="create">
              <bgp route-map="2"/>
            </redistribute>
          </rip>
"""
m2 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <redistribute>
              <bgp route-map="2" metric="10" xc:operation="create"/>
              <kernel route-map="2" xc:operation="create"/>
            </redistribute>
          </rip>
"""
m3 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <redistribute xc:operation="merge">
              <bgp route-map="2" metric="10" />
              <kernel route-map="2"/>
            </redistribute>
          </rip>
"""
m4 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <redistribute xc:operation="delete">
              <bgp route-map="2" metric="10" />
              <kernel route-map="2"/>
            </redistribute>
          </rip>
"""
m5 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <networks xc:operation="create">
              <network>eth0</network>
              <network>eth1</network>
              <network>10.0.0.0/8</network>
            </networks>
          </rip>
"""
m6 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <networks>
              <network xc:operation="create">eth0</network>
              <network xc:operation="delete">eth1</network>
              <network xc:operation="create">10.0.0.0/8</network>
            </networks>
          </rip>
"""
m7 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <routes>
              <route xc:operation="create">eth0</route>
              <route xc:operation="delete">eth1</route>
              <route xc:operation="create">11.0.0.0/8</route>
            </routes>
          </rip>
"""
m8 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <neighbors>
              <neighbor xc:operation="create">eth0</neighbor>
              <neighbor xc:operation="delete">eth1</neighbor>
              <neighbor xc:operation="merge">11.0.0.0/8</neighbor>
            </neighbors>
          </rip>
"""
m9 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <passive-interfaces xc:operation="delete">
              <passive-interface>eth0</passive-interface>
              <passive-interface>eth1</passive-interface>
              <passive-interface>11.0.0.0/8</passive-interface>
            </passive-interfaces>
          </rip>
"""
m10 = """<rip xmlns="urn:loria:madynes:ensuite:yencap:module:RIP:1.0" xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <distribute-lists xc:operation="delete">
              <distribute-list name="private" direct="in">eth0</distribute-list>
              <distribute-list name="public" direct="in">eth0</distribute-list>
            </distribute-lists>
          </rip>
"""


###################################################################
# Here will be the content of edit-config() method of RIP_Module: #
###################################################################

# First, instantiate a RIP manager
ripManager = RIP_Manager("urn:loria:madynes:ensuite:yencap:module:RIP:1.0")

# The table of commands that will have to be executed:
commands = []

# Build an XML document which is a sub part of an edit-config:
doc = NonvalidatingReader.parseString(m6, "madynes")

# Print the edit-config, just for fun:
PrettyPrint(doc)

# Look for XML nodes having "operation" attribute:
d = findEditOp()

# Parse the running configure commands, building the RIP command objects:
ec = ripManager.readCommands()

for item in d:
	NS_RIP_Module = "urn:loria:madynes:ensuite:yencap:module:RIP:1.0"
	name = item.tagName
	operation = d[item]

	if name == "redistribute":
		
		if operation == "create":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "redistribute":
					print "error: can not create redistribute: redistribute already exists."
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName in ["bgp", "kernel", "connected", "ospf"]:
						metric = child.getAttributeNS(EMPTY_NAMESPACE, "metric")
						route_map = child.getAttributeNS(EMPTY_NAMESPACE, "route-map")
						command = Redistribute_RC(child.tagName, metric, route_map)
						commands.append(command)

		elif operation == "delete":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "redistribute":
					command.revert()
					commands.append(command)
			
		elif operation == "replace":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "redistribute":
					command.revert()
					commands.append(command)
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName in ["bgp", "kernel", "connected", "ospf"]:
						metric = child.getAttributeNS(EMPTY_NAMESPACE, "metric")
						route_map = child.getAttributeNS(EMPTY_NAMESPACE, "route-map")
						command = Redistribute_RC(child.tagName, metric, route_map)
						commands.append(command)
			
		elif operation == "merge":
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName in ["bgp", "kernel", "connected", "ospf"]:
						metric = child.getAttributeNS(EMPTY_NAMESPACE, "metric")
						route_map = child.getAttributeNS(EMPTY_NAMESPACE, "route-map")
						command = Redistribute_RC(child.tagName, metric, route_map)
						commands.append(command)
			
	
	elif name in ["bgp", "kernel", "connected", "ospf"]:
		metric = item.getAttributeNS(EMPTY_NAMESPACE, "metric")
		route_map = item.getAttributeNS(EMPTY_NAMESPACE, "route-map")
		command = Redistribute_RC(name, metric, route_map)
		rc = ripManager.find(command)

		if operation == "create":
			if rc != None:
				print "error: already exists"
			else:
				commands.append(command)

		elif operation == "delete":
			if rc != None:
				command.revert()
				commands.append(command)
			else:
				print "error: does not exist"

		elif operation == "replace":
			if rc != None:
				rc.revert()
				commands.append(rc)

			commands.append(command)
		
		elif operation == "merge":
			commands.append(command)

	elif name == "networks":
		
		if operation == "create":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "network":
					print "error: can not create networks: networks already exists."
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "network":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Network_RC(network=value)
								commands.append(command)

		elif operation == "delete":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "network":
					command.revert()
					commands.append(command)
			
		elif operation == "replace":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "network":
					command.revert()
					commands.append(command)
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "network":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Network_RC(network=value)
								commands.append(command)
			
		elif operation == "merge":
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "network":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Network_RC(network=value)
								commands.append(command)
			
	elif name == "network":
		for node in item.childNodes:
			if node.nodeType==Node.TEXT_NODE:
				value = node.nodeValue
				command = Network_RC(network=value)

		rc = ripManager.find(command)

		if operation == "create":
			if rc != None:
				print "error: already exists"
			else:
				commands.append(command)

		elif operation == "delete":
			if rc != None:
				command.revert()
				commands.append(command)
			else:
				print "error: does not exist"

		elif operation == "replace":
			if rc != None:
				rc.revert()
				commands.append(rc)

			commands.append(command)
		
		elif operation == "merge":
			commands.append(command)
		

	elif name == "neighbors":
		
		if operation == "create":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "neighbor":
					print "error: can not create neighbors: neighbors already exists."
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "neighbor":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Neighbor_RC(neighbor=value)
								commands.append(command)

		elif operation == "delete":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "neighbor":
					command.revert()
					commands.append(command)
			
		elif operation == "replace":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "neighbor":
					command.revert()
					commands.append(command)
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "neighbor":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Neighbor_RC(neighbor=value)
								commands.append(command)
			
		elif operation == "merge":
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "neighbor":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Neighbor_RC(neighbor=value)
								commands.append(command)
			
	elif name == "neighbor":
		for node in item.childNodes:
			if node.nodeType==Node.TEXT_NODE:
				value = node.nodeValue
				command = Neighbor_RC(neighbor=value)

		rc = ripManager.find(command)

		if operation == "create":
			if rc != None:
				print "error: already exists"
			else:
				commands.append(command)

		elif operation == "delete":
			if rc != None:
				command.revert()
				commands.append(command)
			else:
				print "error: does not exist"

		elif operation == "replace":
			if rc != None:
				rc.revert()
				commands.append(rc)

			commands.append(command)
		
		elif operation == "merge":
			commands.append(command)

	elif name == "passive-interfaces":
		
		if operation == "create":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "passive-interface":
					print "error: can not create passive-interfaces: passive-interfaces already exists."
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "passive-interface":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = PassiveInterface_RC(ifname=value)
								commands.append(command)

		elif operation == "delete":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "passive-interface":
					command.revert()
					commands.append(command)
			
		elif operation == "replace":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "passive-interface":
					command.revert()
					commands.append(command)
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "passive-interface":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = PassiveInterface_RC(ifname=value)
								commands.append(command)
			
		elif operation == "merge":
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "passive-interface":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = PassiveInterface_RC(ifname=value)
								commands.append(command)
			
	elif name == "passive-interface":
		for node in item.childNodes:
			if node.nodeType==Node.TEXT_NODE:
				value = node.nodeValue
				command = PassiveInterface_RC(ifname=value)

		rc = ripManager.find(command)

		if operation == "create":
			if rc != None:
				print "error: already exists"
			else:
				commands.append(command)

		elif operation == "delete":
			if rc != None:
				command.revert()
				commands.append(command)
			else:
				print "error: does not exist"

		elif operation == "replace":
			if rc != None:
				rc.revert()
				commands.append(rc)

			commands.append(command)
		
		elif operation == "merge":
			commands.append(command)


	elif name == "distribute-lists":
		
		if operation == "create":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "distribute-list":
					print "error: can not create distribute-lists: distribute-lists already exists."
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "distribute-list":
						name = child.getAttributeNS(EMPTY_NAMESPACE, "name")
						direct = child.getAttributeNS(EMPTY_NAMESPACE, "direct")
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = DistributeList_RC(listname, direct, ifname)
								commands.append(command)

		elif operation == "delete":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "distribute-list":
					command.revert()
					commands.append(command)
			
		elif operation == "replace":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "distribute-list":
					command.revert()
					commands.append(command)
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "distribute-list":
						name = child.getAttributeNS(EMPTY_NAMESPACE, "name")
						direct = child.getAttributeNS(EMPTY_NAMESPACE, "direct")
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = DistributeList_RC(listname, direct, ifname)
								commands.append(command)
			
		elif operation == "merge":
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "distribute-list":
						name = child.getAttributeNS(EMPTY_NAMESPACE, "name")
						direct = child.getAttributeNS(EMPTY_NAMESPACE, "direct")
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = DistributeList_RC(listname, direct, ifname)
								commands.append(command)
			
	elif name == "distribute-list":
		for node in item.childNodes:
			name = child.getAttributeNS(EMPTY_NAMESPACE, "name")
			direct = child.getAttributeNS(EMPTY_NAMESPACE, "direct")
			for node in child.childNodes:
				if node.nodeType==Node.TEXT_NODE:
					value = node.nodeValue
					command = DistributeList_RC(listname, direct, ifname)

		rc = ripManager.find(command)

		if operation == "create":
			if rc != None:
				print "error: already exists"
			else:
				commands.append(command)

		elif operation == "delete":
			if rc != None:
				command.revert()
				commands.append(command)
			else:
				print "error: does not exist"

		elif operation == "replace":
			if rc != None:
				rc.revert()
				commands.append(rc)

			commands.append(command)
		
		elif operation == "merge":
			commands.append(command)


	elif name == "routes":
		
		if operation == "create":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "route":
					print "error: can not create routes: routes already exists."
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "route":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Route_RC(network=value)
								commands.append(command)

		elif operation == "delete":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "route":
					command.revert()
					commands.append(command)
			
		elif operation == "replace":
			# Try to find a redistribute command
			for command in ec:
				if command.name == "route":
					command.revert()
					commands.append(command)
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "route":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Route_RC(network=value)
								commands.append(command)
			
		elif operation == "merge":
			# Build the new commands
			for child in item.childNodes:
				if child.nodeType==Node.ELEMENT_NODE:
					if child.tagName == "route":
						for node in child.childNodes:
							if node.nodeType==Node.TEXT_NODE:
								value = node.nodeValue
								command = Route_RC(network=value)
								commands.append(command)
			
	elif name == "route":
		for node in item.childNodes:
			if node.nodeType==Node.TEXT_NODE:
				value = node.nodeValue
				command = Route_RC(network=value)

		rc = ripManager.find(command)

		if operation == "create":
			if rc != None:
				print "error: already exists"
			else:
				commands.append(command)

		elif operation == "delete":
			if rc != None:
				command.revert()
				commands.append(command)
			else:
				print "error: does not exist"

		elif operation == "replace":
			if rc != None:
				rc.revert()
				commands.append(rc)

			commands.append(command)
		
		elif operation == "merge":
			commands.append(command)
		
#ripManager.processCommands(commands)

for command in commands:
	print command.toCLI()

