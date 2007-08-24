/*
 * UniProbSelection.java
 *
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse die Uniform Probability Packet Selection repr�sentiert.
 * siehe IPFIX Configuration Data Model
 *
 * @author Maximilian Huetter
 */
public class UniProbSelection implements packetSelectionType{
    
    /** Creates a new instance of UniProbSelection */
    public UniProbSelection() {
    }
    
    public UniProbSelection(Integer prob){
        setProbability(prob);
    }
    /**
     * Holds value of property probability.
     */
    private Integer probability;

    /**
     * Getter for property probability.
     * @return Value of property probability.
     */
    public Integer getProbability() {

        return this.probability;
    }

    /**
     * Setter for property probability.
     * @param probability New value of property probability.
     */
    public void setProbability(Integer probability) {

        this.probability = probability;
    }

    /**
     * Holds value of property information.
     */
    private String information;

    /**
     * Liefert eine HTML-Darstellung der Methode
     * @return String HTML-ausgezeichnete Information ueber das 
     * Methodenobjekt 
     */
    public String getInformation() {
        information = "<b>Uniform Probabilistic Sampling</b><ul><li>Probability: " 
                + this.probability + "</li></ul>";
        return this.information;
    }

    /**
     * Holds value of property domElement.
     */
    private org.jdom.Element domElement;

    /**
     * Gibt das Objekt als JDOM-Element zur�ck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repraesentation des Objekts.     */
    public org.jdom.Element getDOMElement() {
        Element uniProbElement = new Element("uniProb");
        Element pro = new Element("probability");
        pro.addContent(this.probability.toString());
        uniProbElement.addContent(pro);
        uniProbElement = domElement;
        return this.domElement;
    }
    
    public String getType(){
        return "uniProb";
    }
    
    public String getName(){
        return "Non Uniform Probabilistic Selection";
    }
}
