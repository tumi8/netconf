/*
 * randOutOfNSelection.java
 * 
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse die Random out of N-Packet Selection repraesentiert.
 * siehe IPFIX Configuration Data Model
 *
 * @author Maximilian Huetter
 */
public class RandOutOfNSelection implements packetSelectionType{
    
    /** Creates a new instance of randOutOfNSelection */
    public RandOutOfNSelection() {
    }
    public RandOutOfNSelection(Integer pop, Integer samp) {
        setPopulation(pop);
        setSample(samp);
    }
    /**
     * Holds value of property population.
     */
    private Integer population;

    /**
     * Getter for property population.
     * @return Value of property population.
     */
    public Integer getPopulation() {

        return this.population;
    }

    /**
     * Setter for property population.
     * @param population New value of property population.
     */
    public void setPopulation(Integer population) {

        this.population = population;
    }

    /**
     * Holds value of property sample.
     */
    private Integer sample;

    /**
     * Getter for property sample.
     * @return Value of property sample.
     */
    public Integer getSample() {

        return this.sample;
    }

    /**
     * Setter for property sample.
     * @param sample New value of property sample.
     */
    public void setSample(Integer sample) {

        this.sample = sample;
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
        information = "<b>Random out of N Sampling</b><ul><li>Population: " 
                + this.population + "</li><li>Sample: " + this.sample + "</li></ul>";
        return this.information;
    }

    /**
     * Holds value of property domElement.
     */
    private org.jdom.Element domElement;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repraesentation des Objekts.
     */
    public org.jdom.Element getDOMElement() {
        Element randOutOfNElement = new Element("randOutOfN");
        Element pop = new Element("population");
        pop.addContent(this.population.toString());
        randOutOfNElement.addContent(pop);
        Element sam = new Element("sample");
        sam.addContent(this.sample.toString());
        randOutOfNElement.addContent(sam);
        domElement = randOutOfNElement;
        return this.domElement;
    }
    
    public String getType(){
        return "randOutOfN";
    }
    
    public String getName(){
        return "Random out of N Selection";
    }
    
}
