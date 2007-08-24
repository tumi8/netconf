/*
 * Device.java
 * 
 * 
 */

package ipfixconfig;


import org.jdom.Element;

/**
 * Klasse, die ein Device im System repraesentiert.
 * Eigenschaften sind:
 * Name, Description, Typ, HostIP, User, Password, NetconfRole, Monitored Network
 * Port, Ptotocol, Key, NetconfNamesapce (NetconfNS), NetconfPath
 * 
 * @author Maximilian Huetter
 */
public class Device extends Object implements AbstractIPFIXProcess{
    
    /** Creates a new instance of Device */
    public Device() {
    }

    /**
     * Unique name to identify the device.
     */
    private String name;

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {

        return this.name;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Type of the IPFIX Device, e.g. VERMONT
     */
    private String type;

    /**
     * Getter for property type.
     * @return Value of property type.
     */
    public String getType() {

        return this.type;
    }

    /**
     * Setter for property type.
     * @param type New value of property type.
     */
    public void setType(String type) {

        this.type = type;
    }

    /**
     * IP-address of the agent.
     */
    private String hostIP;

    /**
     * Getter for property hostIP.
     * @return Value of property hostIP.
     */
    public String getHostIP() {

        return this.hostIP;
    }

    /**
     * Setter for property hostIP.
     * @param hostIP New value of property hostIP.
     */
    public void setHostIP(String hostIP) {

        this.hostIP = hostIP;
    }

    /**
     * SSH username to connect to the agent.
     */
    private String user;

    /**
     * Getter for property user.
     * @return Value of property user.
     */
    public String getUser() {

        return this.user;
    }

    /**
     * Setter for property user.
     * @param user New value of property user.
     */
    public void setUser(String user) {

        this.user = user;
    }

    /**
     * SSH password to connect to the agent.
     */
    private String password;

    /**
     * Getter for property password.
     * @return Value of property password.
     */
    public String getPassword() {

        return this.password;
    }

    /**
     * Setter for property password.
     * @param password New value of property password.
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * The RBAC role used by the agent to identifiy which part
     * of the configuration data a user may change. 
     */
    private String netconfRole;

    /**
     * Getter for property netconfRole.
     * @return Value of property netconfRole.
     */
    public String getNetconfRole() {

        return this.netconfRole;
    }

    /**
     * Setter for property netconfRole.
     * @param netconfRole New value of property netconfRole.
     */
    public void setNetconfRole(String netconfRole) {

        this.netconfRole = netconfRole;
    }

    /**
     * A String describing the network to monitor.
     */
    private String monitoredNetwork;

    /**
     * Getter for property monitoredNetwork.
     * @return Value of property monitoredNetwork.
     */
    public String getMonitoredNetwork() {

        return this.monitoredNetwork;
    }

    /**
     * Setter for property monitoredNetwork.
     * @param monitoredNetwork New value of property monitoredNetwork.
     */
    public void setMonitoredNetwork(String monitoredNetwork) {

        this.monitoredNetwork = monitoredNetwork;
    }

    /**
     * Transport protocol used to connect to the agent.
     * (Currently only SSH is supported)
     */
    private String protocol;

    /**
     * Getter for property protocol.
     * @return Value of property protocol.
     */
    public String getProtocol() {

        return this.protocol;
    }

    /**
     * Setter for property protocol.
     * @param protocol New value of property protocol.
     */
    public void setProtocol(String protocol) {

        this.protocol = protocol;
    }

    /**
     * The Role the device is set to, the role determines the configuration.
     */
    private String role;

    /**
     * Getter for property roles.
     * @return Value of property roles.
     */
    public String getRole() {

        return this.role;
    }

    /**
     * Setter for property roles.
     * @param role New value of property roles.
     */
    public void setRole(String role) {

        this.role = role;
    }

    /**
     * Holds value of property key.
     */
    private String key;

    /**
     * Getter for property key.
     * @return Value of property key.
     */
    public String getKey() {

        return this.key;
    }

    /**
     * Setter for property key.
     * @param key New value of property key.
     */
    public void setKey(String key) {

        this.key = key;
    }

    /**
     * Device description
     */
    private String description;

    /**
     * Getter for property description.
     * @return Value of property description.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Port to connect to the agent.
     */
    private Integer port;

    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public Integer getPort() {

        return this.port;
    }

    /**
     * Setter for property port.
     * @param port New value of property port.
     */
    public void setPort(Integer port) {

        this.port = port;
    }

    /**
     * Netconf Namespace property - the namespace for addressing the IPFIX part
     * of the agent.
     */
    private String netconfNS;

    /**
     * Getter for property netconfNS.
     * @return Value of property netconfNS.
     */
    public String getNetconfNS() {

        return this.netconfNS;
    }

    /**
     * Setter for property netconfNS.
     * @param netconfNS New value of property netconfNS.
     */
    public void setNetconfNS(String netconfNS) {

        this.netconfNS = netconfNS;
    }

    /**
     * Netconf Path - The Path to the IPFIX Data on the agent.
     */
    private String netconfPath;

    /**
     * Getter for property netconfPath.
     * @return Value of property netconfPath.
     */
    public String getNetconfPath() {

        return this.netconfPath;
    }

    /**
     * Setter for property netconfPath.
     * @param netconfPath New value of property netconfPath.
     */
    public void setNetconfPath(String netconfPath) {

        this.netconfPath = netconfPath;
    }

    /**
     * Getter for the XML Representation as DOMElement.
     * @return A org.jdom.Element representing the the Device Information.
     */
    private org.jdom.Element DOMElement;

    /**
     * Liefert die JDOM-Repr�sentation eines Device.
     * @return org.jdom.Element JDOMElement das die Device darstellt.
     */
    public org.jdom.Element getDOMElement() {
        Element deviceElement = new Element("device");
        //deviceElement.setAttribute("id", this.id.toString());
        
        Element nameElement = new Element("name");
        nameElement.addContent(this.name);
        deviceElement.addContent(nameElement);
        
        Element typeElement = new Element("type");
        typeElement.addContent(this.type);
        deviceElement.addContent(typeElement);
        
        Element hostElement = new Element("host");
        hostElement.addContent(this.hostIP);
        deviceElement.addContent(hostElement);

        Element protocolElement = new Element("protocol");
        protocolElement.addContent(this.protocol);
        deviceElement.addContent(protocolElement);
        
        Element portElement = new Element("port");
        portElement.addContent(this.port.toString());
        deviceElement.addContent(portElement);
        
        Element userElement = new Element("user");
        userElement.addContent(this.user);
        deviceElement.addContent(userElement);
        
        Element passwordElement = new Element("password");
        passwordElement.addContent(this.password);
        deviceElement.addContent(passwordElement);
        
        Element ncRoleElement = new Element("netconfRole");
        ncRoleElement.addContent(this.netconfRole);
        deviceElement.addContent(ncRoleElement);
        
        Element ncPathElement = new Element("netconfPath");
        ncPathElement.addContent(this.netconfPath);
        deviceElement.addContent(ncPathElement);
        this.DOMElement = deviceElement;
        
        Element ncNamespaceElement = new Element("netconfNS");
        ncNamespaceElement.addContent(this.netconfNS);
        deviceElement.addContent(ncNamespaceElement);
        
        Element descriptElement = new Element("descript");
        descriptElement.addContent(this.description);
        deviceElement.addContent(descriptElement);
        
        Element roleElement = new Element("role");
        roleElement.addContent(this.role);
        deviceElement.addContent(roleElement);
        
        Element monitoredNetElement = new Element("monitoredNetwork");
        monitoredNetElement.addContent(this.monitoredNetwork);
        deviceElement.addContent(monitoredNetElement);
        
        this.DOMElement = deviceElement;
        
        return this.DOMElement;
    }

    /**
     * Holds value of property status.
     */
    private String status = "Not set";

    /**
     * Getter for property status.
     * @return Value of property status.
     */
    public String getStatus() {

        return this.status;
    }

    /**
     * Setter for property status.
     * @param status New value of property status.
     */
    public void setStatus(String status) {

        this.status = status;
    }
    
}
