/*
 * PacketReporting.java
 *
 * 
 *
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * Repraesentiert Packet Repoting-Objekte im System. Alle Eigenschaften leiten sich
 * direkt aus dem IPFIX Configuration Data Model ab, oder sind 
 * Informationseigentschaften fuer das System.
 *
 * @author Maximilian Huetter
 */
public class PacketReporting extends Object{
    private List packetReportingIEList = new ArrayList();
    
    /** Creates a new instance of PacketReporting */
    public PacketReporting() {
        //packetReportingIEList.add(new ReportedInformationElement());
    }

    /**
     * Getter for property packetReportingTemplateList.
     * @return Value of property packetReportingTemplateList.
     */
    public java.util.List getPacketReportingIEList() {

        return this.packetReportingIEList;
    }

    /**
     * Setter for property packetReportingTemplateList.
     * @param packetReportingIEList New value of property packetReportingTemplateList.
     */
    public void setPacketReportingIEList(java.util.List packetReportingIEList) {

        this.packetReportingIEList = packetReportingIEList;
    }

    /**
     * Holds value of property templateId.
     */
    private Integer templateId;

    /**
     * Getter for property templateId.
     * @return Value of property templateId.
     */
    public Integer getTemplateId() {

        return this.templateId;
    }

    /**
     * Setter for property templateId.
     * @param templateId New value of property templateId.
     */
    public void setTemplateId(Integer templateId) {

        this.templateId = templateId;
    }

    /**
     * Holds value of property packetReportingDOM.
     */
    private org.jdom.Element packetReportingDOM;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * 
     * @return org.jdom.Element XMLRepr�sentation des Objekts.
     */
    public org.jdom.Element getPacketReportingDOM() {
        Element packetReportingElement = new Element("packetReporting");
        
        if(this.templateId != null){
        Element packetReportingTemplateId = new Element("templateId");
        packetReportingTemplateId.addContent(this.templateId.toString());
        }
        
        Iterator IEListIterator = this.packetReportingIEList.iterator();
        while(IEListIterator.hasNext()){
            Element infoElement = new Element("reportedIE");
            InformationElement currentIE = (InformationElement) IEListIterator.next();
            
            if(currentIE.getEnterpriseNumber() != null){
                Element enterpriseNr = new Element("enterpriseNumber");
                enterpriseNr.addContent(currentIE.getEnterpriseNumber().toString());
                infoElement.addContent(enterpriseNr);
            }
            if(currentIE.getIeName() != null){
                Element name = new Element("ieName");
                name.addContent(currentIE.getIeName());
                infoElement.addContent(name);
            }
            if(currentIE.getIeId() != null){
                Element id = new Element("ieId");
                id.addContent(currentIE.getIeId().toString());
                infoElement.addContent(id);
            }
            if(currentIE.getIeLength() != null){
                Element length = new Element("ieLength");
                length.addContent(currentIE.getIeLength().toString());
                infoElement.addContent(length);
            }
            if(currentIE.getMatch() != null){
                Element match = new Element("match");
                match.addContent(currentIE.getMatch());
                infoElement.addContent(match);
            }
            if(currentIE.getModifier() != null){
                Element modifier = new Element("modifier");
                modifier.addContent(currentIE.getModifier());
                infoElement.addContent(modifier);
            }
            
            packetReportingElement.addContent(infoElement); 
        }
        packetReportingDOM = packetReportingElement;
        return this.packetReportingDOM;
    }
}
