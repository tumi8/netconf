/*
 * ObservationPoint.java
 *
 * 
 */

package ipfixconfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;


/**
 * Repraesentiert einen Observation Point im System. Alle Eigenschaften leiten sich
 * direkt aus dem IPFIX Configuration Data Model ab, oder sind 
 * Informationseigentschaften f�r das System.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class ObservationPoint extends Object implements AbstractIPFIXProcess, Serializable{
    
    /** Konstruktor ohne Parameter */
    public ObservationPoint() {
        
    }
    /** Konstruktor mit Parametern */
    public ObservationPoint(Integer id, Integer domainId, String type, String parameters, List next){
        this.setId(id);
        this.setDomainId(domainId);
        this.setType(type);
        this.setParameters(parameters);
        this.setNext(next);
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
     * Holds value of property domainId.
     */
    private Integer domainId;

    /**
     * Getter for property domainId.
     * @return Value of property domainId.
     */
    public Integer getDomainId() {

        return this.domainId;
    }

    /**
     * Setter for property domainId.
     * @param domainId New value of property domainId.
     */
    public void setDomainId(Integer domainId) {

        this.domainId = domainId;
    }

    /**
     * Holds value of property type.
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
     * Holds value of property parameters.
     */
    private String parameters;

    /**
     * Getter for property parameters.
     * @return Value of property parameters.
     */
    public String getParameters() {

        return this.parameters;
    }

    /**
     * Setter for property parameters.
     * @param parameters New value of property parameters.
     */
    public void setParameters(String parameters) {

        this.parameters = parameters;
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
     * Gibt die Next Processes zur Anzeige aus.
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
     * Gibt das Objekt als JDOM-Element zur�ck.
     * Implementiert das Interface AbstactIPFIXProcess
     * @return Value of property xmlRepresentation.
     */
    public org.jdom.Element getDOMElement() {
        Element observationPointElement = new Element("observationPoint");
        observationPointElement.setAttribute("id", id.toString());
                
        Element elementObservationDomainId = new Element("observationDomainId");
        elementObservationDomainId.setText(domainId.toString());
        observationPointElement.addContent(elementObservationDomainId);
                
        Element elementType = new Element("type");
        elementType.setText(type);
        observationPointElement.addContent(elementType);
        
        Element elementParameters = new Element("parameters");
        
        if(this.networkInterface != null){
            Element elementInterface = new Element("interface");
            elementInterface.setText(networkInterface);
            elementParameters.addContent(elementInterface);
        }
        
        if(parameters != null){
            Element filterParameters = new Element("pcap_filter");
            filterParameters.setText(parameters);
            elementParameters.addContent(filterParameters);
        }
        observationPointElement.addContent(elementParameters);
        
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
                observationPointElement.addContent(nextElement);
            }
        }
        
        DOMElement = observationPointElement;
        return this.DOMElement;
    }

    /**
     * Holds value of property pcapParameter.
     */
    private String pcapParameter;

    /**
     * Getter for property pcapParameter.
     * @return Value of property pcapParameter.
     */
    public String getPcapParameter() {

        return this.pcapParameter;
    }

    /**
     * Setter for property pcapParameter.
     * @param pcapParameter New value of property pcapParameter.
     */
    public void setPcapParameter(String pcapParameter) {

        this.pcapParameter = pcapParameter;
    }

    /**
     * Holds value of property networkInterface.
     */
    private String networkInterface;

    /**
     * Getter for property networkInterface.
     * @return Value of property networkInterface.
     */
    public String getNetworkInterface() {

        return this.networkInterface;
    }

    /**
     * Setter for property networkInterface.
     * @param networkInterface New value of property networkInterface.
     */
    public void setNetworkInterface(String networkInterface) {

        this.networkInterface = networkInterface;
    }

}
