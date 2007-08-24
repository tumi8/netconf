/*
 * RawFilter.java
 * 
 * 
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse die die VERMONT-Ur-Packet Selection-Methode repraesentiert.
 *
 * @author Maximilian H�tter
 */
public class RawFilter implements packetSelectionType{
    
    /** Creates a new instance of RawFilter */
    public RawFilter() {
    }
    
    public RawFilter(String set) {
        this.setSettings(set);
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
        information = "<b>Raw Filter</b><ul><li>Settings: " 
                + this.settings + "</li></ul>";
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
        Element rawFilterElement = new Element("rawFilter");
        Element set = new Element("settings");
        set.addContent(this.settings);
        rawFilterElement.addContent(set);
        rawFilterElement = domElement;
        return this.domElement;
    }
    
    public String getType(){
        return "rawFilter";
    }
    
    public String getName(){
        return "Raw Filter";
    }

    /**
     * Holds value of property settings.
     */
    private String settings;

    /**
     * Getter for property settings.
     * @return Value of property settings.
     */
    public String getSettings() {

        return this.settings;
    }

    /**
     * Setter for property settings.
     * @param settings New value of property settings.
     */
    public void setSettings(String settings) {

        this.settings = settings;
    }
}
