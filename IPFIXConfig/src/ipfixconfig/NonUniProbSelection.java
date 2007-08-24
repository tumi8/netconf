/*
 * NonUniProbSelection.java
 * 
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse die Non-Uniform Probability Packet Selection repraesentiert.
 * siehe IPFIX Configuration Data Model
 *
 * @author Maximilian Huetter
 */
public class NonUniProbSelection implements packetSelectionType{
    
    /** Creates a new instance of NonUniProbSelection */
    public NonUniProbSelection() {
    }
    
    public NonUniProbSelection(String funct, String functParam) {
        setFunction(funct);
        setFuncParam(functParam);
    }

    /**
     * Holds value of property function.
     */
    private String function;

    /**
     * Getter for property function.
     * @return Value of property function.
     */
    public String getFunction() {

        return this.function;
    }

    /**
     * Setter for property function.
     * @param function New value of property function.
     */
    public void setFunction(String function) {

        this.function = function;
    }

    /**
     * Holds value of property funcParam.
     */
    private String funcParam;

    /**
     * Getter for property funcParam.
     * @return Value of property funcParam.
     */
    public String getFuncParam() {

        return this.funcParam;
    }

    /**
     * Setter for property funcParam.
     * @param funcParam New value of property funcParam.
     */
    public void setFuncParam(String funcParam) {

        this.funcParam = funcParam;
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
        information = "<b>Non-Uniform Probabilistic Sampling</b> <ul> <li>Function: " 
                + this.function + "</li> <li>Parameters: " + this.funcParam + "</li></ul>";
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
        Element nonUniProbElement = new Element("nonUniProb");
        Element func = new Element("function");
        func.addContent(this.function.toString());
        nonUniProbElement.addContent(func);
        Element funcPar = new Element("funcParam");
        funcPar.addContent(this.funcParam.toString());
        nonUniProbElement.addContent(funcPar);
        nonUniProbElement = domElement;
        return this.domElement;
    }
    
    public String getType(){
        return "nonUniProb";
    }
    
    public String getName(){
        return "Uniform Probabilistic Selection";
    }
}
