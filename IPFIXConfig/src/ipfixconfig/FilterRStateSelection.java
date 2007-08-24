/*
 * FilterRState.java
 * 
 */

package ipfixconfig;

import org.jdom.Element;

/**
 * Klasse fuer die Filter Routerstate Selection, siehe IPFIX Confuig. Data Model 
 * @author Max
 */
public class FilterRStateSelection implements packetSelectionType{
    
    /** Creates a new instance of FilterRState */
    public FilterRStateSelection() {
    }
    
    public FilterRStateSelection(String funct, boolean nega, Integer ifInd, Integer start, Integer end, String vendorF) {
        setFunction(funct);
        setNegate(nega);
        setIfIndex(ifInd);
        setStartAs(start);
        setEndAs(end);
        setVendorFunc(vendorF);
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
     * Holds value of property negate.
     */
    private boolean negate;

    /**
     * Getter for property negate.
     * @return Value of property negate.
     */
    public boolean isNegate() {

        return this.negate;
    }

    /**
     * Setter for property negate.
     * @param negate New value of property negate.
     */
    public void setNegate(boolean negate) {

        this.negate = negate;
    }

    /**
     * Holds value of property ifIndex.
     */
    private Integer ifIndex;

    /**
     * Getter for property ifIndex.
     * @return Value of property ifIndex.
     */
    public Integer getIfIndex() {

        return this.ifIndex;
    }

    /**
     * Setter for property ifIndex.
     * @param ifIndex New value of property ifIndex.
     */
    public void setIfIndex(Integer ifIndex) {

        this.ifIndex = ifIndex;
    }

    /**
     * Holds value of property startAs.
     */
    private Integer startAs;

    /**
     * Getter for property startAs.
     * @return Value of property startAs.
     */
    public Integer getStartAs() {

        return this.startAs;
    }

    /**
     * Setter for property startAs.
     * @param startAs New value of property startAs.
     */
    public void setStartAs(Integer startAs) {

        this.startAs = startAs;
    }

    /**
     * Holds value of property endAs.
     */
    private Integer endAs;

    /**
     * Getter for property endAs.
     * @return Value of property endAs.
     */
    public Integer getEndAs() {

        return this.endAs;
    }

    /**
     * Setter for property endAs.
     * @param endAs New value of property endAs.
     */
    public void setEndAs(Integer endAs) {

        this.endAs = endAs;
    }

    /**
     * Holds value of property vendorFunc.
     */
    private String vendorFunc;

    /**
     * Getter for property vendorFunc.
     * @return Value of property vendorFunc.
     */
    public String getVendorFunc() {

        return this.vendorFunc;
    }

    /**
     * Setter for property vendorFunc.
     * @param vendorFunc New value of property vendorFunc.
     */
    public void setVendorFunc(String vendorFunc) {

        this.vendorFunc = vendorFunc;
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
        information = "<b>Router State Filtering</b> <ul><li> Function: " + this.function 
                + "</li><li> Negate: " + this.negate + "</li><li>"
                + "ifIndex: " + this.ifIndex + "</li><li>"
                + "Start AS: " + this.startAs + "</li><li>"
                + "End AS: " + this.endAs + "</li><li>"
                + "Vendor Function: " + this.vendorFunc + "</li></ul>";
        return this.information;
    }

    /**
     * Holds value of property domElement.
     */
    private org.jdom.Element domElement;

    /**
     * Getter for property domElement.
     * @return Value of property domElement.
     */
    public org.jdom.Element getDOMElement() {
        Element filterRStateElement = new Element("filterRState");
        
        Element func = new Element("function");
        func.addContent(this.function);
        filterRStateElement.addContent(func);
        
        Element neg = new Element("negate");
        if(this.negate) neg.addContent("true");
        else neg.addContent("false");
        filterRStateElement.addContent(neg);
        
        Element ifInd = new Element("ifIndex");
        ifInd.addContent(this.ifIndex.toString());
        filterRStateElement.addContent(ifInd);
        
        Element startA = new Element("startAS");
        startA.addContent(this.startAs.toString());
        filterRStateElement.addContent(startA);
        
        Element endA = new Element("endAS");
        endA.addContent(this.endAs.toString());
        filterRStateElement.addContent(endA);
        
        Element vfunc = new Element("vendorFunc");
        vfunc.addContent(this.vendorFunc);
        filterRStateElement.addContent(vfunc);
        
        domElement = filterRStateElement;
        return this.domElement;
    }
    
    public String getType(){
        return "filterRState";
    }
    
    public String getName(){
        return "Router State Filtering";
    }
}
