/*
 * FilterHashSelection.java
 *
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse die FilterHash-Packet Selection repraesentiert.
 * siehe IPFIX Configuration Data Model 
 *
 * @author Maximilian Huetter
 */
public class FilterHashSelection implements packetSelectionType{
    
    /** Creates a new instance of FilterHashSelection */
    public FilterHashSelection() {
    }
    
    public FilterHashSelection(String addtype, String headBits, Integer payLoadByts, Integer payLoadBit, String funct, String functParam, Integer inputBit, Integer outputBit, String outputMas, String select) {
            setAddrType(addtype);
            setHeaderBits(headBits);
            setPayloadBytes(payLoadByts);
            setPayloadBits(payLoadBit);
            setFunction(funct);
            setFuncParam(functParam);
            setInputBits(inputBit);
            setOutputBits(outputBit);
            setOutputMask(outputMas);
            setSelection(select);
    }
    /**
     * Holds value of property addrType.
     */
    private String addrType;

    /**
     * Getter for property addrType.
     * @return Value of property addrType.
     */
    public String getAddrType() {

        return this.addrType;
    }

    /**
     * Setter for property addrType.
     * @param addrType New value of property addrType.
     */
    public void setAddrType(String addrType) {

        this.addrType = addrType;
    }

    /**
     * Holds value of property headerBits.
     */
    private String headerBits;

    /**
     * Getter for property headerBits.
     * @return Value of property headerBits.
     */
    public String getHeaderBits() {

        return this.headerBits;
    }

    /**
     * Setter for property headerBits.
     * @param headerBits New value of property headerBits.
     */
    public void setHeaderBits(String headerBits) {

        this.headerBits = headerBits;
    }

    /**
     * Holds value of property payloadBytes.
     */
    private Integer payloadBytes;

    /**
     * Getter for property payloadBytes.
     * @return Value of property payloadBytes.
     */
    public Integer getPayloadBytes() {

        return this.payloadBytes;
    }

    /**
     * Setter for property payloadBytes.
     * @param payloadBytes New value of property payloadBytes.
     */
    public void setPayloadBytes(Integer payloadBytes) {

        this.payloadBytes = payloadBytes;
    }

    /**
     * Holds value of property payloadBits.
     */
    private Integer payloadBits;

    /**
     * Getter for property payloadBits.
     * @return Value of property payloadBits.
     */
    public Integer getPayloadBits() {

        return this.payloadBits;
    }

    /**
     * Setter for property payloadBits.
     * @param payloadBits New value of property payloadBits.
     */
    public void setPayloadBits(Integer payloadBits) {

        this.payloadBits = payloadBits;
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
     * Holds value of property inputBits.
     */
    private Integer inputBits;

    /**
     * Getter for property inputBits.
     * @return Value of property inputBits.
     */
    public Integer getInputBits() {

        return this.inputBits;
    }

    /**
     * Setter for property inputBits.
     * @param inputBits New value of property inputBits.
     */
    public void setInputBits(Integer inputBits) {

        this.inputBits = inputBits;
    }

    /**
     * Holds value of property outputBits.
     */
    private Integer outputBits;

    /**
     * Getter for property outputBits.
     * @return Value of property outputBits.
     */
    public Integer getOutputBits() {

        return this.outputBits;
    }

    /**
     * Setter for property outputBits.
     * @param outputBits New value of property outputBits.
     */
    public void setOutputBits(Integer outputBits) {

        this.outputBits = outputBits;
    }

    /**
     * Holds value of property outputMask.
     */
    private String outputMask;

    /**
     * Getter for property outputMask.
     * @return Value of property outputMask.
     */
    public String getOutputMask() {

        return this.outputMask;
    }

    /**
     * Setter for property outputMask.
     * @param outputMask New value of property outputMask.
     */
    public void setOutputMask(String outputMask) {

        this.outputMask = outputMask;
    }

    /**
     * Holds value of property selection.
     */
    private String selection;

    /**
     * Getter for property selection.
     * @return Value of property selection.
     */
    public String getSelection() {

        return this.selection;
    }

    /**
     * Setter for property selection.
     * @param selection New value of property selection.
     */
    public void setSelection(String selection) {

        this.selection = selection;
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
        information = "<b>Hash Filtering</b> <ul><li> Addresstype: " + this.addrType 
                + "</li><li> Headerbits: " + this.headerBits + "</li><li>"
                + "Payload bytes: " + this.payloadBytes + "</li><li>"
                + "Payload bits: " + this.payloadBits + "</li><li>"
                + "Input bits: " + this.inputBits + "</li><li>"
                + "Output bits: " + this.outputBits + "</li><li>"
                + "Output mask: " + this.outputMask + "</li><li>"
                + "Function: " + this.function + "</li><li>"
                + "Parameters: " + this.funcParam +"</li></ul>";
        return this.information;
    }

    /**
     * Holds value of property domElement.
     */
    private org.jdom.Element domElement;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repr�sentation des Objekts.
     */
    public org.jdom.Element getDOMElement() {
        Element filterHashElement = new Element("filterHash");
        
        Element addrtyp = new Element("addrType");
        addrtyp.addContent(this.addrType);
        filterHashElement.addContent(addrtyp);
        Element headrBits = new Element("headerBits");
        headrBits.addContent(this.headerBits);
        filterHashElement.addContent(headrBits);
        Element payloadByte = new Element("payloadBytes");
        payloadByte.addContent(this.payloadBytes.toString());
        filterHashElement.addContent(payloadByte);
        Element payloadBit = new Element("payloadBits");
        payloadBit.addContent(this.payloadBits.toString());
        filterHashElement.addContent(payloadBit);
        Element func = new Element("function");
        func.addContent(this.function);
        filterHashElement.addContent(func);
        Element funcPar = new Element("funcParam");
        funcPar.addContent(this.funcParam);
        filterHashElement.addContent(funcPar);
        Element inputBit = new Element("inputBits");
        inputBit.addContent(this.inputBits.toString());
        filterHashElement.addContent(inputBit);
        Element outputBit = new Element("outputBits");
        outputBit.addContent(this.outputBits.toString());
        filterHashElement.addContent(outputBit);
        Element outputMas = new Element("outputMask");
        outputMas.addContent(this.outputMask);
        filterHashElement.addContent(outputMas);
        Element select = new Element("selection");
        select.addContent(this.selection);
        filterHashElement.addContent(select);
        
        domElement = filterHashElement;
        return this.domElement;
    }

    public String getType(){
        return "filterHash";
    }
    
    public String getName(){
        return "Hash Filtering";
    }    
    
}
