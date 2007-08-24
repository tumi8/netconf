/*
 * PacketReportingObject.java
 *
 * 
 *
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse die ein Packet Reporting Template repraesentiert.
 * siehe IPFIX Configuration Data Model.
 *
 * @author Maximilian H�tter
 */
public class PacketReportingTemplate extends Object{
    
    /** Creates a new instance of PacketReportingObject */
    public PacketReportingTemplate() {
        reportedIEList.add(new InformationElement());
        setTemplateId(0);
    }
    /*
     * Konstruktor fuer ein PacketReportingTemplate-Objekt
     * @param List reportedList Liste der Template Info-Elements
     * @param int id Id des Templates
     */
    public PacketReportingTemplate(List reportedList, int id){
        setReportedIEList(reportedList);
        setTemplateId(id);
    }
    /**
     * Holds value of property templateId.
     */
    private int templateId;

    /**
     * Getter for property templateId.
     * @return Value of property templateId.
     */
    public int getTemplateId() {

        return this.templateId;
    }

    /**
     * Setter for property templateId.
     * @param templateId New value of property templateId.
     */
    public void setTemplateId(int templateId) {

        this.templateId = templateId;
    }

    /**
     * Holds value of property reportedIEList.
     */
    private java.util.List reportedIEList = new ArrayList();

    /**
     * Getter for property reportedIEList.
     * @return Value of property reportedIEList.
     */
    public java.util.List getReportedIEList() {

        return this.reportedIEList;
    }

    /**
     * Setter for property reportedIEList.
     * @param reportedIEList New value of property reportedIEList.
     */
    public void setReportedIEList(java.util.List reportedIEList) {

        this.reportedIEList = reportedIEList;
    }
    
}
