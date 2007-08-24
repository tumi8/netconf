README zu

VERMONT mit Extended Netconf Suite (ENSuite)

http://libresource.inria.fr/projects/ensuite


Autor: Maximilian Hütter
e-mail: maxhuetter@web.de

ENSuite-INFORMATION:
-------------------------------------------------------------------------------

Release: yencap-2.1.11 (ENSuite)

License: Lesser GNU General Public License (LGPL)

Bitte die Installationshinweise zu ENSuite beachten! Diese sind hier nicht nochmal aufgeführt.

Bezeichner-Konvention: Yencap-Home ist das Verzeichnis, in dem Yencap installiert ist. Z.B.: /opt/yencap-2.1.11/

Folgende Dateien müssen zu ENSuite hinzugefügt werden:

In Yencap-home/Modules/VERMONT_Module:

- VERMONT_Module.py

- VERMONT-Config-Schema.xsd

(Alte Dummy-Dateien löschen)

In Yencap-Home/Operations/ext:

- restart_operation.py 

Die validate_operation wurde in das Standard-ENSuite-Paket übernommen, daher ist da nichts zu tun.
Die Datastore-Dateien sollten auch existieren: startup.xml, candidate.xml und running.xml

Folgende Dateien müssen angepasst werden:

(Achtung, dies kann sich bei späteren ENSuite-Versionen ändern! Bevor man einfach diese Änderungen
 durchführt, sollte man nachfragen was sich geändert hat. Insbesondere das RBAC-System ändert sich 
ständig und ebenso die Handhabung der Namespaces)

modules.xml:

Wenn das VERMONT-Modul nicht schon eingetragen ist, es hinzufügen. Auf jeden Fall muss der Namespace 
geändert werden auf: urn:ietf:params:xml:ns:ipfix-config (prefix "mon" ist ok)

Es sollte so aus sehen:

<module>
		<name>VERMONT</name>
		<xpath>/ycp:netconf/ycp:monitoring/mon:ipfixConfig</xpath>
		<namespace pref="mon">urn:ietf:params:xml:ns:ipfix-config</namespace>
		<cachelifetime>10000</cachelifetime>
		<parameters/>
</module>

operations.xml:

Hinzufügen:
<operation>
		<name>restart</name>
		<mainFileName>ext.restart_operation</mainFileName>
		<className>Restart_operation</className>
</operation>

RBAC-startup.xml:

Hinzufügen/Ändern des Namespace:

<prefix name="mon" value="urn:ietf:params:xml:ns:ipfix-config"/>

Dann entsprechende Benutzer, Rollen und Permissions anlegen. (Siehe ENSuite Dokumentation) 
Es muss eine Permission existieren, die den Zugriff auf /ycp:netconf/ycp:monitoring erlaubt.
Am einfachsten ist es eine für den ganzen /ycp:netconf - Baum anzulegen.

netconfd.xml

Prüfen, dass die IP-Protokoll-version 4 ist.

hello.xml

Hier stehen die Capabilities. Eventuell die Capabilities:

Validate <capability>urn:ietf:params:netconf:capability:validate:1.0</capability>

und die Datastores anlegen:

<capability>urn:ietf:params:netconf:capability:candidate:1.0</capability>
<capability>urn:ietf:params:netconf:capability:startup:1.0</capability>

Dann natürlich noch die Capability für restart:

<capability>urn:ri-inf:params:xml:ns:netconf:capability:restart:1.0</capability>

In der Restart-Operation im VERMONT-Modul muss der Pfad zur startup.xml angepasst werden, auf 
das richtige Yencap-Home-Verzeichnis.

VERMONT Informationen:

VERMONT ab Version ?? (muss XML-Konfigurations-fähig sein)

VERMONT muss in ein bin-Verzeichnis installiert oder verlinkt werden, damit die restart-Operation
es starten kann.


