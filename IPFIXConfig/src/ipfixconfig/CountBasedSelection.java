/*
 * CountBasedSelection.java
 * 
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse die Count based-Packet Selection repr�sentiert.
 * siehe IPFIX Configuration Data Model
 *
 * @author Maximilian Huetter
 */
public class CountBasedSelection implements packetSelectionType{
    
    /** Creates a new instance of CountBasedSelection */
    public CountBasedSelection() {
        setInterval(new Integer(0));
        setSpacing(new Integer(0));
    }
    public CountBasedSelection(Integer inter, Integer space){
        setInterval(inter);
        setSpacing(space);
    }
    /**
     * Holds value of property interval.
     */
    private Integer interval;

    /**
     * Getter for property interval.
     * @return Value of property interval.
     */
    public Integer getInterval() {

        return this.interval;
    }

    /**
     * Setter for property interval.
     * @param interval New value of property interval.
     */
    public void setInterval(Integer interval) {

        this.interval = interval;
    }

    /**
     * Holds value of property spacing.
     */
    private Integer spacing;

    /**
     * Getter for property spacing.
     * @return Value of property spacing.
     */
    public Integer getSpacing() {

        return this.spacing;
    }

    /**
     * Setter for property spacing.
     * @param spacing New value of property spacing.
     */
    public void setSpacing(Integer spacing) {

        this.spacing = spacing;
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
        information = "<b>Systematic Count-based Sampling</b> <ul> <li>Interval: " 
                + this.interval + "</li> <li>Spacing: " + this.spacing + "</li></ul>";
        return this.information;
    }

    /**
     * Holds value of property dOMElement.
     */
    private org.jdom.Element dOMElement;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repr�sentation des Objekts.
     */
    public org.jdom.Element getDOMElement() {
        Element countBasedElement = new Element("countBased");
        Element interv = new Element("interval");
        interv.addContent(this.interval.toString());
        countBasedElement.addContent(interv);
        Element spac = new Element("spacing");
        spac.addContent(this.spacing.toString());
        countBasedElement.addContent(spac);
        dOMElement = countBasedElement;
        return this.dOMElement;
    }
    
    public String getType(){
        return "countBased";
    }
    
    public String getName(){
        return "Systematic Count based Selection";
    }
    
}
