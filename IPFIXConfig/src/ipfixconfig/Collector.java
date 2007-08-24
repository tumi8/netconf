/*
 * Collector.java
 *
 * 
 */

package ipfixconfig;

import java.io.Serializable;

/**
 * Kollektor-Klasse siehe IPFIX Config. Data Model 
 * 
 * @author Maximilian H�tter
 */
public class Collector extends Object implements Serializable{
    
    /** Creates a new instance of Listener */
    public Collector() {
        
    }
    /*
     * Konstruktor mit Parametern:
     */
    public Collector(String Addresstype, String Address, String protocol, Integer por){
        this.setIpAddresstype(Addresstype);
        this.setIpAddress(Address);
        this.setTransportProtocol(protocol);
        this.setPort(por);
    }
    /**
     * Holds value of property ipAddresstype.
     */
    private String ipAddresstype;

    /**
     * Getter for property ipAddresstype.
     * @return Value of property ipAddresstype.
     */
    public String getIpAddresstype() {

        return this.ipAddresstype;
    }

    /**
     * Setter for property ipAddresstype.
     * @param ipAddresstype New value of property ipAddresstype.
     */
    public void setIpAddresstype(String ipAddresstype) {

        this.ipAddresstype = ipAddresstype;
    }

    /**
     * Holds value of property ipAddress.
     */
    private String ipAddress;

    /**
     * Getter for property ipAddress.
     * @return Value of property ipAddress.
     */
    public String getIpAddress() {

        return this.ipAddress;
    }

    /**
     * Setter for property ipAddress.
     * @param ipAddress New value of property ipAddress.
     */
    public void setIpAddress(String ipAddress) {

        this.ipAddress = ipAddress;
    }

    /**
     * Holds value of property transportProtocol.
     */
    private String transportProtocol;

    /**
     * Getter for property transportProtocol.
     * @return Value of property transportProtocol.
     */
    public String getTransportProtocol() {

        return this.transportProtocol;
    }

    /**
     * Setter for property transportProtocol.
     * @param transportProtocol New value of property transportProtocol.
     */
    public void setTransportProtocol(String transportProtocol) {

        this.transportProtocol = transportProtocol;
    }

    /**
     * Holds value of property port.
     */
    private Integer port;

    /**
     * Getter for property port.
     * @return Value of property port.
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
     * Holds value of property ipAddresstypeString.
     */
    private String ipAddresstypeString = new String("");

    /**
     * Getter for property ipAddresstypeString.
     * Zur Darstellung des IP-Addresstyps in den Tabellen.
     * @return Einen String der den Typ lesbar angibt.
     */
    public String getIpAddresstypeString() {
        if(ipAddresstype != null){
            if(ipAddresstype.equals("4")) ipAddresstypeString = "IPv4";
            else if(ipAddresstype.equals("41")) ipAddresstypeString = "IPv6";
            else ipAddresstypeString = "unknown addresstype";
        } 
        else ipAddresstypeString = "not set";
        return this.ipAddresstypeString;
    }

    /**
     * Holds value of property transportProtocolString.
     */
    private String transportProtocolString = new String("");

    /**
     * Getter for property transportProtocolString.
     * Zur Darstellung des Transportprotokolls in den Tabellen.
     * @return Einen String der das Transportprotokoll angibt.
     */
    public String getTransportProtocolString() {
        if(transportProtocol != null){
            if(transportProtocol.equals("17")) transportProtocolString = "UDP";
            else if(transportProtocol.equals("6")) transportProtocolString = "TCP";
            else if(transportProtocol.equals("132"))  transportProtocolString = "SCTP";
            else transportProtocolString = "unknown protocol";
        } else transportProtocolString = "not set";
        return this.transportProtocolString;
    }
    
}
