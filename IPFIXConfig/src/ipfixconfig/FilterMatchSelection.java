/*
 * filterMatchSelection.java
 *
 * 
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * Klasse die die Filter Match Packet Selection repr�sentiert.
 * siehe IPFIX Configuration Data Model
 *
 * @author Maximilian Hütter
 */
public class FilterMatchSelection implements packetSelectionType{
    
    /** Creates a new instance of filterMatchSelection */
    public FilterMatchSelection() {
        
    }
    public FilterMatchSelection(List matchIEList){
        this.setFilterMatchIEList(matchIEList);
    }
    /**
     * Holds value of property filterMatchIEList.
     */
    private List filterMatchIEList = new ArrayList();
    
    /**
     * Getter for property filterMatchIEList.
     * @return Value of property filterMatchIEList.
     */
    public List getFilterMatchIEList() {
        
        return this.filterMatchIEList;
    }
    
    /**
     * Setter for property filterMatchIEList.
     * @param filterMatchIEList New value of property filterMatchIEList.
     */
    public void setFilterMatchIEList(List filterMatchIEList) {
        
        this.filterMatchIEList = filterMatchIEList;
    }

    /**
     * Holds value of property information.
     */
    private String information;

    /**
     * Liefert eine HTML-Darstellung der Methode
     * @return String HTML-ausgezeichnete Information �ber das 
     * Methodenobjekt 
     */

    public String getInformation() {
        information = "<b>Match Filter</b> <ul><li>" 
                + Integer.toString(filterMatchIEList.size()) + " Info Elements </li></ul>";
        return this.information;
    }

    /**
     * Holds value of property domElement.
     */
    private org.jdom.Element domElement;

    /**
     * Gibt das Objekt als JDOM-Element zur�ck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repr�sentation des Objekts.
     */
    public org.jdom.Element getDOMElement() {
        Element filterMatchElement = new Element("filterMatch");
        
        Iterator filterMatchIterator = filterMatchIEList.iterator();
        
        while(filterMatchIterator.hasNext()){
            Element infoElement = new Element("infoElementId");
            InformationElement currentIE = (InformationElement) filterMatchIterator.next();
            
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
            
            filterMatchElement.addContent(infoElement);
        }
        domElement = filterMatchElement;
        return this.domElement;
    }
    
    public String getType(){
        return "filterMatch";
    }
    
    public String getName(){
        return "Match Filtering";
    }
}
