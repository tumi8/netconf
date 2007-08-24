/*
 * CollectingProcess.java
 * 
 * Repraesentiert einen CollectingProcess im System. Alle Eigenschaften leiten sich
 * direkt aus dem IPFIX Configuration Data Model ab, oder sind 
 * Informationseigentschaften
 *
 */

package ipfixconfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Maximilian H�tter
 * e-mail: maxhuetter@web.de
 */
public class CollectingProcess extends Object implements AbstractIPFIXProcess, Serializable{
    private List listenerList = new ArrayList();
    
    /** CollectingProcess Konstruktor */
    public CollectingProcess() {
        
    }
    /*
     * Konstruktor mit Parametern.
     */
     public CollectingProcess(Integer id, List listenerList, Integer udptemplatetime, String udptemplateunit, List next){
        this.setId(id);
        this.setListenerList(listenerList);
        this.setUdpTemplateLifetime(udptemplatetime);
        this.setUdpTemplateUnit(udptemplateunit);
        this.setNext(next);
    }
    /**
     * Holds value of property udpTemplateLifetime.
     */
    private Integer udpTemplateLifetime;

    /**
     * Getter for property udpTemplateLifetime.
     * @return Value of property udpTemplateLifetime.
     */
    public Integer getUdpTemplateLifetime() {
        
        return this.udpTemplateLifetime;
    }

    /**
     * Setter for property udpTemplateLifetime.
     * @param udpTemplateLifetime New value of property udpTemplateLifetime.
     */
    public void setUdpTemplateLifetime(Integer udpTemplateLifetime) {

        this.udpTemplateLifetime = udpTemplateLifetime;
    }

    /**
     * Holds value of property udpTemplateUnit.
     */
    private String udpTemplateUnit;

    /**
     * Getter for property udpTemplateUnit.
     * @return Value of property udpTemplateUnit.
     */
    public String getUdpTemplateUnit() {

        return this.udpTemplateUnit;
    }

    /**
     * Setter for property udpTemplateUnit.
     * @param udpTemplateUnit New value of property udpTemplateUnit.
     */
    public void setUdpTemplateUnit(String udpTemplateUnit) {

        this.udpTemplateUnit = udpTemplateUnit;
    }

    /**
     * Getter for property listenerList.
     * @return Value of property listenerList.
     */
    public java.util.List getListenerList() {

        return this.listenerList;
    }

    /**
     * Setter for property listenerList.
     * @param listenerList New value of property listenerList.
     */
    public void setListenerList(java.util.List listenerList) {

        this.listenerList = listenerList;
    }

    /**
     * Holds value of property listenerListLink.
     */
    private String listenerListLink;

    /**
     * Getter for property listenerListLink.
     * Der listenerListLink ist dazu da, eine einfache HTML-Liste der Listener eines
     * Collecting Process anzuzeigen. Dazu werden alle listener des Objekts ausgelesen.
     * @return Value of property listenerListLink.
     */
    public String getListenerListLink() {
        listenerListLink = "No Listeners set";
        if(!listenerList.isEmpty()){
            int i = 1;
            StringBuffer linkList = new StringBuffer();
            linkList.append("<ul>");
            Iterator listIterator = listenerList.iterator();
            
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
            listenerListLink = linkList.toString();
        }
        return this.listenerListLink;
    }

    /**
     * Setter for property listenerListLink.
     * @param listenerListLink New value of property listenerListLink.
     */
    public void setListenerListLink(String listenerListLink) {

        this.listenerListLink = listenerListLink;
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
     * Holds value of property next.
     */
    private java.util.List next = new ArrayList();

    /**
     * Getter for property next.
     * @return Value of property next.
     */
    public java.util.List getNext() {

        return this.next;
    }

    /**
     * Setter for property next.
     * @param next New value of property next.
     */
    public void setNext(java.util.List next) {

        this.next = next;
    }

    /**
     * Holds value of property nextInfo.
     */
    private String nextInfo;

    /**
     * Getter for property nextInfo.
     * Gibt die Information �ber die Next-Processes aus.
     * @return Value of property nextInfo.
     */
    public String getNextInfo() {
        nextInfo = "No next process set";
        if(next != null){
            if(!next.isEmpty()){
                StringBuffer Info = new StringBuffer();
                List nextList = getNext();
                Iterator nextIterator = nextList.iterator();
                while(nextIterator.hasNext()){
                    Next nextProcess = (Next) nextIterator.next();
                    Info.append(nextProcess.getName() + " " + nextProcess.getId() + " ");
                }
                nextInfo = Info.toString();
            }
        }
        
        return this.nextInfo;
    }

    /**
     * Setter for property nextInfo.
     * @param nextInfo New value of property nextInfo.
     */
    public void setNextInfo(String nextInfo) {

        this.nextInfo = nextInfo;
    }

    /**
     * Holds value of property xmlRepresentation.
     */
    private org.jdom.Element DOMElement;

    /**
     * 
     * Gibt das Objekt als JDOM-Element zur�ck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repr�sentation des Objekts.
     */
    public org.jdom.Element getDOMElement() {
        Element collectingProcessElement = new Element("collectingProcess");
        collectingProcessElement.setAttribute("id", this.id.toString());
        
        Iterator listenerIterator = this.listenerList.iterator();
        
        while(listenerIterator.hasNext()){
            Collector currentListener = (Collector) listenerIterator.next();
            Element listenerElement = new Element("listener");
            
            Element ipAddressType = new Element("ipAddressType");
            ipAddressType.addContent(currentListener.getIpAddresstype());
            listenerElement.addContent(ipAddressType);
            
            Element ipAddress = new Element("ipAddress");
            ipAddress.addContent(currentListener.getIpAddress());
            listenerElement.addContent(ipAddress);
            
            Element transportProtocol = new Element("transportProtocol");
            transportProtocol.addContent(currentListener.getTransportProtocol());
            listenerElement.addContent(transportProtocol);
            
            Element port = new Element("port");
            port.addContent(currentListener.getPort().toString());
            listenerElement.addContent(port);
            collectingProcessElement.addContent(listenerElement);    
        }
        
        if(this.udpTemplateLifetime != null){
            Element udpTemplateLifetimeElement = new Element("udpTemplateLifetime");
            if(this.udpTemplateUnit != null) udpTemplateLifetimeElement.setAttribute("unit", this.udpTemplateUnit);
            udpTemplateLifetimeElement.addContent(this.udpTemplateLifetime.toString());
            collectingProcessElement.addContent(udpTemplateLifetimeElement);
        }
                
        if(this.next != null){
            if(!this.next.isEmpty()){
                Element nextElement = new Element("next");
                List nextList = this.getNext();
                Iterator nextIterator = nextList.iterator();
                while(nextIterator.hasNext()){
                    Next nextProcess = (Next) nextIterator.next();
                    Element nextProcessElement = new Element(nextProcess.getName());
                    nextProcessElement.addContent(nextProcess.getId().toString());
                    nextElement.addContent(nextProcessElement);
                }
                collectingProcessElement.addContent(nextElement);
            }
        }
        
        DOMElement = collectingProcessElement;
        
        return this.DOMElement;
    }

    /**
     * Holds value of property observationDomainId.
     */
    private Integer observationDomainId;

    /**
     * Getter for property observationDomainId.
     * @return Value of property observationDomainId.
     */
    public Integer getObservationDomainId() {

        return this.observationDomainId;
    }

    /**
     * Setter for property observationDomainId.
     * @param observationDomainId New value of property observationDomainId.
     */
    public void setObservationDomainId(Integer observationDomainId) {

        this.observationDomainId = observationDomainId;
    }
  
}
