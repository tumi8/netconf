

import ModuleReader

mr = ModuleReader.getInstance()

l = mr.readModules()
print l

mr.addModule("name", "xpath", "namespace", "n1", 15, {"ip":6})
mr.addModule("name2", "xpath2", "namespace2", "n2", 17, {})
mr.writeModules()

mr.removeModule("name2")
mr.writeModules()
