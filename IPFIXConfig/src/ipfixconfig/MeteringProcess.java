/*
 * MeteringProcess.java
 * 
 *
 * */

package ipfixconfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * Repraesentiert einen MeteringProcess im System. Alle Eigenschaften leiten sich
 * direkt aus dem IPFIX Configuration Data Model ab, oder sind 
 * Informationseigentschaften 
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class MeteringProcess extends Object implements AbstractIPFIXProcess, Serializable{
    
    /** Creates a new instance of MeteringProcess */
    public MeteringProcess() {
        setId(new Integer(0));
        setPacketSelectionData(new PacketSelection());
        setPacketReportingData(new PacketReporting());
        setFlowMeteringData(new FlowMetering());
        
    }

    /**
     * Holds value of property packetSelectionData.
     */
    private PacketSelection packetSelectionData;

    /**
     * Getter for property packetSelectionData.
     * @return Value of property packetSelectionData.
     */
    public PacketSelection getPacketSelectionData() {

        return this.packetSelectionData;
    }

    /**
     * Setter for property packetSelectionData.
     * @param packetSelectionData New value of property packetSelectionData.
     */
    public void setPacketSelectionData(PacketSelection packetSelectionData) {

        this.packetSelectionData = packetSelectionData;
    }

    /**
     * Holds value of property packetReportingData.
     */
    private PacketReporting packetReportingData;

    /**
     * Getter for property packetReportingData.
     * @return Value of property packetReportingData.
     */
    public PacketReporting getPacketReportingData() {

        return this.packetReportingData;
    }

    /**
     * Setter for property packetReportingData.
     * @param packetReportingData New value of property packetReportingData.
     */
    public void setPacketReportingData(PacketReporting packetReportingData) {

        this.packetReportingData = packetReportingData;
    }

    /**
     * Holds value of property flowMeteringData.
     */
    private FlowMetering flowMeteringData;

    /**
     * Getter for property flowMeteringData.
     * @return Value of property flowMeteringData.
     */
    public FlowMetering getFlowMeteringData() {

        return this.flowMeteringData;
    }

    /**
     * Setter for property flowMeteringData.
     * @param flowMeteringData New value of property flowMeteringData.
     */
    public void setFlowMeteringData(FlowMetering flowMeteringData) {

        this.flowMeteringData = flowMeteringData;
    }

    /**
     * Holds value of property packetSelectionInfo.
     */
    private String packetSelectionInfo;

    /**
     * Getter for property packetSelectionInfo.
     * Gibt einen Informations String �ber die PacketSelection Daten des Process 
     * zur Anzeige aus.
     * @return Value of property packetSelectionInfo.
     */
    public String getPacketSelectionInfo() {
        packetSelectionInfo = "No Packet Selection Data";
        
        if(packetSelectionData != null){
            StringBuffer selectionInfo = new StringBuffer();
            List selectionMethods = this.getPacketSelectionData().getPacketSelectionTypesList();
            for(int i = 0; i < selectionMethods.size(); i++){
                packetSelectionType currentSelectionType = (packetSelectionType) selectionMethods.get(i);
                selectionInfo.append(currentSelectionType.getName() + ", ");
            }
            if(!(selectionInfo.length() == 0)){
                packetSelectionInfo = selectionInfo.toString();
            }
        }
        return this.packetSelectionInfo;
    }

    /**
     * Setter for property packetSelectionInfo.
     * @param packetSelectionInfo New value of property packetSelectionInfo.
     */
    public void setPacketSelectionInfo(String packetSelectionInfo) {

        this.packetSelectionInfo = packetSelectionInfo;
    }

    /**
     * Holds value of property packetReportingInfo.
     */
    private String packetReportingInfo;

    /**
     * Getter for property packetReportingInfo.
     * Gibt einen Informations String �ber die PacketReporting Daten des Process 
     * zur Anzeige aus.
     * @return Value of property packetReportingInfo.
     */
    public String getPacketReportingInfo() {
        packetReportingInfo = "No Packet Reporting Data";
        
        if(packetReportingData != null){
            StringBuffer Info = new StringBuffer("");
            Integer id = packetReportingData.getTemplateId();
            if(id != null){
                Info.append("Template ID: " + packetReportingData.getTemplateId() + " ");
                Info.append(packetReportingData.getPacketReportingIEList().size() + " Packet Reporting Elements");
            packetReportingInfo = Info.toString();
            }
            
            
        }
        
        return this.packetReportingInfo;
    }

    /**
     * Setter for property packetReportingInfo.
     * @param packetReportingInfo New value of property packetReportingInfo.
     */
    public void setPacketReportingInfo(String packetReportingInfo) {
        
        this.packetReportingInfo = packetReportingInfo;
    }

    /**
     * Holds value of property flowMeteringInfo.
     */
    private String flowMeteringInfo;

    /**
     * Getter for property flowMeteringInfo.
     * Gibt einen Informations String �ber die FlowMetering Daten des Process 
     * zur Anzeige aus.
     * @return Value of property flowMeteringInfo.
     */
    public String getFlowMeteringInfo() {
        flowMeteringInfo = "No Flow Metering Data";
        
        if(flowMeteringData != null){
        StringBuffer Info = new StringBuffer("");
            if(!flowMeteringData.getRulesList().isEmpty()){
            Info.append(flowMeteringData.getRulesList().size() + " Flow Metering Rules, ");
            if(flowMeteringData.getFlowExpirationActiveTimeout() != null) {
                Info.append(" Expiration Active Timeout: " + flowMeteringData.getFlowExpirationActiveTimeout()
                    + " " + flowMeteringData.getFlowExpirationActiveTimeoutUnit() + ", ");
            } 
            if(flowMeteringData.getFlowExpirationInactiveTimeout() != null){
                Info.append(" Expiration Inactive Timeout: " + flowMeteringData.getFlowExpirationInactiveTimeout()
            + " " + flowMeteringData.getFlowExpirationInactiveTimeoutUnit());
            }
            flowMeteringInfo = Info.toString();
         }
        
        }
               
        return this.flowMeteringInfo;
    }

    /**
     * Setter for property flowMeteringInfo.
     * @param flowMeteringInfo New value of property flowMeteringInfo.
     */
    public void setFlowMeteringInfo(String flowMeteringInfo) {

        this.flowMeteringInfo = flowMeteringInfo;
    }

    /**
     * Holds value of property managedId.
     */
    private int managedId;

    /**
     * Getter for property managedId.
     * @return Value of property managedId.
     */
    public int getManagedId() {

        return this.managedId;
    }

    /**
     * Setter for property managedId.
     * @param managedId New value of property managedId.
     */
    public void setManagedId(int managedId) {

        this.managedId = managedId;
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
     * Gibt die Next Processes zur Anzeige aus.
     * @return Value of property nextInfo.
     */
    public String getNextInfo() {
        nextInfo = "No next process set";
        if(next != null){
        if(!next.isEmpty()){
            StringBuffer Info = new StringBuffer("");
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
    private org.jdom.Element domElement;

    /**
     * Getter for property xmlRepresentation.
     * Gibt das Objekt als JDOM-Element zur�ck.
     * Implementiert das Interface AbstactIPFIXProcess
     * @return Value of property xmlRepresentation.
     */
    public org.jdom.Element getDOMElement() {
        Element meteringProcessElement = new Element("meteringProcess");
        meteringProcessElement.setAttribute("id", this.id.toString());
        Element packetSelectionElement = this.packetSelectionData.getPacketSelectionDOM();
        if(packetSelectionElement.getContentSize() != 0) meteringProcessElement.addContent(packetSelectionElement);
        Element packetReportingElement = this.packetReportingData.getPacketReportingDOM();
        if(packetReportingElement.getContentSize() != 0) meteringProcessElement.addContent(packetReportingElement);
        Element flowMeteringElement = this.flowMeteringData.getFlowMeteringDOM();
        if(flowMeteringElement.getContentSize() != 0) meteringProcessElement.addContent(flowMeteringElement);
        
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
                meteringProcessElement.addContent(nextElement);
            }
        }
        domElement = meteringProcessElement;
        return this.domElement;
    }
       
}
