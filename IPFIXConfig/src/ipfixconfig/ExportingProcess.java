/*
 * ExportingProcess.java
 *
 * 
 *
 */

package ipfixconfig;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * Repraesentiert einen ExportingProcess im System. Alle Eigenschaften leiten sich
 * direkt aus dem IPFIX Configuration Data Model ab, oder sind 
 * Informationseigentschaften
 *
 * @author Maximilian Huetter
 */
public class ExportingProcess extends Object implements AbstractIPFIXProcess, Serializable{
    
    /** Creates a new instance of ExportingProcess */
    public ExportingProcess() {
    }

    public ExportingProcess(Integer id, List collectorList, 
            Integer maxPacketSize, Integer maxExportDelay, String exportDelayUnit,
            Integer refreshTimeout, String refreshtimeoutUnit, Integer refreshRate) {
        
        this.setId(id);
        this.setCollectorList(collectorList);
        this.setRestrictionsMaxPacketSize(maxPacketSize);
        this.setRestrictionsMaxExportDelay(maxExportDelay);
        this.setRestrictionsMaxExportDelayUnit(exportDelayUnit);
        this.setUdpTemplateRefreshTimeout(refreshTimeout);
        this.setUdpTemplateRefreshTimeoutUnit(refreshtimeoutUnit);
        this.setUdpTemplateRefreshrate(refreshRate);
    }
    /**
     * Holds value of property restrictionsMaxPacketSize.
     */
    private Integer restrictionsMaxPacketSize;

    /**
     * Getter for property restrictionsMaxPacketSize.
     * @return Value of property restrictionsMaxPacketSize.
     */
    public Integer getRestrictionsMaxPacketSize() {

        return this.restrictionsMaxPacketSize;
    }

    /**
     * Setter for property restrictionsMaxPacketSize.
     * @param restrictionsMaxPacketSize New value of property restrictionsMaxPacketSize.
     */
    public void setRestrictionsMaxPacketSize(Integer restrictionsMaxPacketSize) {

        this.restrictionsMaxPacketSize = restrictionsMaxPacketSize;
    }

    /**
     * Holds value of property restrictionsMaxExportDelay.
     */
    private Integer restrictionsMaxExportDelay;

    /**
     * Getter for property restrictionsMaxExportDelay.
     * @return Value of property restrictionsMaxExportDelay.
     */
    public Integer getRestrictionsMaxExportDelay() {

        return this.restrictionsMaxExportDelay;
    }

    /**
     * Setter for property restrictionsMaxExportDelay.
     * @param restrictionsMaxExportDelay New value of property restrictionsMaxExportDelay.
     */
    public void setRestrictionsMaxExportDelay(Integer restrictionsMaxExportDelay) {

        this.restrictionsMaxExportDelay = restrictionsMaxExportDelay;
    }

    /**
     * Holds value of property restrictionsMaxExportDelayUnit.
     */
    private String restrictionsMaxExportDelayUnit;

    /**
     * Getter for property restrictionsMaxExportDelayUnit.
     * @return Value of property restrictionsMaxExportDelayUnit.
     */
    public String getRestrictionsMaxExportDelayUnit() {

        return this.restrictionsMaxExportDelayUnit;
    }

    /**
     * Setter for property restrictionsMaxExportDelayUnit.
     * @param restrictionsMaxExportDelayUnit New value of property restrictionsMaxExportDelayUnit.
     */
    public void setRestrictionsMaxExportDelayUnit(String restrictionsMaxExportDelayUnit) {

        this.restrictionsMaxExportDelayUnit = restrictionsMaxExportDelayUnit;
    }

    /**
     * Holds value of property udpTemplateRefreshTimeout.
     */
    private Integer udpTemplateRefreshTimeout;

    /**
     * Getter for property udpTemplateRefreshTimeout.
     * @return Value of property udpTemplateRefreshTimeout.
     */
    public Integer getUdpTemplateRefreshTimeout() {

        return this.udpTemplateRefreshTimeout;
    }

    /**
     * Setter for property udpTemplateRefreshTimeout.
     * @param udpTemplateRefreshTimeout New value of property udpTemplateRefreshTimeout.
     */
    public void setUdpTemplateRefreshTimeout(Integer udpTemplateRefreshTimeout) {

        this.udpTemplateRefreshTimeout = udpTemplateRefreshTimeout;
    }

    /**
     * Holds value of property udpTemplateRefreshTimeoutUnit.
     */
    private String udpTemplateRefreshTimeoutUnit;

    /**
     * Getter for property udpTemplateRefreshTimeoutUnit.
     * @return Value of property udpTemplateRefreshTimeoutUnit.
     */
    public String getUdpTemplateRefreshTimeoutUnit() {

        return this.udpTemplateRefreshTimeoutUnit;
    }

    /**
     * Setter for property udpTemplateRefreshTimeoutUnit.
     * @param udpTemplateRefreshTimeoutUnit New value of property udpTemplateRefreshTimeoutUnit.
     */
    public void setUdpTemplateRefreshTimeoutUnit(String udpTemplateRefreshTimeoutUnit) {

        this.udpTemplateRefreshTimeoutUnit = udpTemplateRefreshTimeoutUnit;
    }

    /**
     * Holds value of property udpTemplateRefreshrate.
     */
    private Integer udpTemplateRefreshrate;

    /**
     * Getter for property udpTemplateRefreshrate.
     * @return Value of property udpTemplateRefreshrate.
     */
    public Integer getUdpTemplateRefreshrate() {

        return this.udpTemplateRefreshrate;
    }

    /**
     * Setter for property udpTemplateRefreshrate.
     * @param udpTemplateRefreshrate New value of property udpTemplateRefreshrate.
     */
    public void setUdpTemplateRefreshrate(Integer udpTemplateRefreshrate) {

        this.udpTemplateRefreshrate = udpTemplateRefreshrate;
    }
        
    /**
     * Holds value of property collectorList.
     */
    private java.util.List collectorList;

    /**
     * Getter for property collectorList.
     * @return Value of property collectorList.
     */
    public java.util.List getCollectorList() {

        return this.collectorList;
    }

    /**
     * Setter for property collectorList.
     * @param collectorList New value of property collectorList.
     */
    public void setCollectorList(java.util.List collectorList) {

        this.collectorList = collectorList;
    }

    /**
     * Holds value of property collectorListLink.
     */
    private String collectorListLink;

    /**
     * Getter for property collectorListLink.
     * @return Value of property collectorListLink.
     */
    public String getCollectorListLink() {
        int i = 1;
        StringBuffer linkList = new StringBuffer();
        linkList.append("<ul>");
        Iterator listIterator = collectorList.iterator();
        
        while(listIterator.hasNext()){
            Collector currentCollector = (Collector) listIterator.next();
            linkList.append("<li>Listener " + String.valueOf(i));
            if(currentCollector.getIpAddresstype().equals("4")) linkList.append(": IPv4, ");
            else linkList.append(": IPv6, ");
            linkList.append(currentCollector.getIpAddress() + ", ");
            if(currentCollector.getTransportProtocol().equals("6")) linkList.append("TCP, ");
            if(currentCollector.getTransportProtocol().equals("17")) linkList.append("UDP, ");
            if(currentCollector.getTransportProtocol().equals("132")) linkList.append("SCTP, ");
            linkList.append("Port:" + String.valueOf(currentCollector.getPort()) + "</li>");
            i = i + 1;
        }
        linkList.append("</ul>");
        collectorListLink = linkList.toString();
        return this.collectorListLink;
    }

    /**
     * Setter for property collectorListLink.
     * @param collectorListLink New value of property collectorListLink.
     */
    public void setCollectorListLink(String collectorListLink) {

        this.collectorListLink = collectorListLink;
    }

    /**
     * Holds value of property id.
     */
    private Integer id;

    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public Integer getId() {

        return this.id;
    }

    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * Holds value of property domElement.
     */
    private org.jdom.Element domElement;

    /**
     * Getter for property domElement.
     * Gibt das Objekt als JDOM-Element zurueck.
     * Implementiert das Interface AbstactIPFIXProcess.
     * @return Value of property domElement.
     */
    public org.jdom.Element getDOMElement() {
        Element exportingProcessElement = new Element("exportingProcess");
        exportingProcessElement.setAttribute("id", this.id.toString());
        
        if(this.restrictionsMaxPacketSize != null || this.restrictionsMaxExportDelay != null){
            Element ipfixPacketRestrictionElement = new Element("ipfixPacketRestrictions");
            if(this.restrictionsMaxPacketSize != null){
                Element maxPacketSizeElement = new Element("maxPacketSize");
                maxPacketSizeElement.addContent(this.restrictionsMaxPacketSize.toString());
                ipfixPacketRestrictionElement.addContent(maxPacketSizeElement);
            }
            if(this.restrictionsMaxExportDelay != null){
                Element maxExportDelayElement = new Element("maxExportDelay");
                if(this.restrictionsMaxExportDelayUnit != null) maxExportDelayElement.setAttribute("unit", this.restrictionsMaxExportDelayUnit);
                maxExportDelayElement.addContent(this.restrictionsMaxExportDelay.toString());
                ipfixPacketRestrictionElement.addContent(maxExportDelayElement);
            }
            exportingProcessElement.addContent(ipfixPacketRestrictionElement);
        }
        
        if(this.udpTemplateRefreshTimeout != null || this.udpTemplateRefreshrate != null){
            Element udpTemplateManElement = new Element("udpTemplateManagement");
            
            if(this.udpTemplateRefreshTimeout != null){
                Element udpTemplateRefreshTimeoutElement = new Element("templateRefreshTimeout");
                if(this.udpTemplateRefreshTimeoutUnit != null) udpTemplateRefreshTimeoutElement.setAttribute("unit", this.udpTemplateRefreshTimeoutUnit);
                udpTemplateRefreshTimeoutElement.addContent(this.udpTemplateRefreshTimeout.toString());
                udpTemplateManElement.addContent(udpTemplateRefreshTimeoutElement);
            }
            
            if(this.udpTemplateRefreshrate != null){
                Element udpTemplateRefreshrateElement = new Element("templateRefreshRate");
                udpTemplateRefreshrateElement.addContent(this.udpTemplateRefreshrate.toString());
                udpTemplateManElement.addContent(udpTemplateRefreshrateElement);
            }
            
            exportingProcessElement.addContent(udpTemplateManElement);
        }
        
        Iterator collectorIterator = this.collectorList.iterator();
        
        while(collectorIterator.hasNext()){
            Collector currentCollector = (Collector) collectorIterator.next();
            Element collectorElement = new Element("collector");
            
            Element ipAddressType = new Element("ipAddressType");
            ipAddressType.addContent(currentCollector.getIpAddresstype());
            collectorElement.addContent(ipAddressType);
            
            Element ipAddress = new Element("ipAddress");
            ipAddress.addContent(currentCollector.getIpAddress());
            collectorElement.addContent(ipAddress);
            
            Element transportProtocol = new Element("transportProtocol");
            transportProtocol.addContent(currentCollector.getTransportProtocol());
            collectorElement.addContent(transportProtocol);
            
            Element port = new Element("port");
            port.addContent(currentCollector.getPort().toString());
            collectorElement.addContent(port);
            exportingProcessElement.addContent(collectorElement);    
        }
               
        domElement = exportingProcessElement;
        return this.domElement;
    }

    
    
}
