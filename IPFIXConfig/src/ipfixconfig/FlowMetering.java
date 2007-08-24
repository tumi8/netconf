/*
 * FlowMetering.java
 *
 * 
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.Iterator;
import org.jdom.Element;

/**
 * Klasse fuer FlowMetering Objekte. Siehe IPFIX Configuration Data Model.
 *
 * @author Maximilian H�tter
 */
public class FlowMetering extends Object{
    
    /** Creates a new instance of FlowMetering */
    public FlowMetering() {
    }

    /**
     * Holds value of property rulesList.
     */
    private java.util.List rulesList = new ArrayList();

    /**
     * Getter for property rulesList.
     * @return Value of property rulesList.
     */
    public java.util.List getRulesList() {

        return this.rulesList;
    }

    /**
     * Setter for property rulesList.
     * @param rulesList New value of property rulesList.
     */
    public void setRulesList(java.util.List rulesList) {

        this.rulesList = rulesList;
    }

    /**
     * Holds value of property flowExpirationActiveTimeout.
     */
    private Integer flowExpirationActiveTimeout;

    /**
     * Getter for property flowExpirationActiveTimeout.
     * @return Value of property flowExpirationActiveTimeout.
     */
    public Integer getFlowExpirationActiveTimeout() {

        return this.flowExpirationActiveTimeout;
    }

    /**
     * Setter for property flowExpirationActiveTimeout.
     * @param flowExpirationActiveTimeout New value of property flowExpirationActiveTimeout.
     */
    public void setFlowExpirationActiveTimeout(Integer flowExpirationActiveTimeout) {

        this.flowExpirationActiveTimeout = flowExpirationActiveTimeout;
    }

    /**
     * Holds value of property flowExpirationInactiveTimeout.
     */
    private Integer flowExpirationInactiveTimeout;

    /**
     * Getter for property flowExpirationInactiveTimeout.
     * @return Value of property flowExpirationInactiveTimeout.
     */
    public Integer getFlowExpirationInactiveTimeout() {

        return this.flowExpirationInactiveTimeout;
    }

    /**
     * Setter for property flowExpirationInactiveTimeout.
     * @param flowExpirationInactiveTimeout New value of property flowExpirationInactiveTimeout.
     */
    public void setFlowExpirationInactiveTimeout(Integer flowExpirationInactiveTimeout) {

        this.flowExpirationInactiveTimeout = flowExpirationInactiveTimeout;
    }

    /**
     * Holds value of property flowExpirationActiveTimeoutUnit.
     */
    private String flowExpirationActiveTimeoutUnit;

    /**
     * Getter for property flowExpirationActiveTimeoutUnit.
     * @return Value of property flowExpirationActiveTimeoutUnit.
     */
    public String getFlowExpirationActiveTimeoutUnit() {

        return this.flowExpirationActiveTimeoutUnit;
    }

    /**
     * Setter for property flowExpirationActiveTimeoutUnit.
     * @param flowExpirationActiveTimeoutUnit New value of property flowExpirationActiveTimeoutUnit.
     */
    public void setFlowExpirationActiveTimeoutUnit(String flowExpirationActiveTimeoutUnit) {

        this.flowExpirationActiveTimeoutUnit = flowExpirationActiveTimeoutUnit;
    }

    /**
     * Holds value of property flowExpirationInactiveTimeoutUnit.
     */
    private String flowExpirationInactiveTimeoutUnit;

    /**
     * Getter for property flowExpirationInactiveTimeoutUnit.
     * @return Value of property flowExpirationInactiveTimeoutUnit.
     */
    public String getFlowExpirationInactiveTimeoutUnit() {

        return this.flowExpirationInactiveTimeoutUnit;
    }

    /**
     * Setter for property flowExpirationInactiveTimeoutUnit.
     * @param flowExpirationInactiveTimeoutUnit New value of property flowExpirationInactiveTimeoutUnit.
     */
    public void setFlowExpirationInactiveTimeoutUnit(String flowExpirationInactiveTimeoutUnit) {

        this.flowExpirationInactiveTimeoutUnit = flowExpirationInactiveTimeoutUnit;
    }

    /**
     * Holds value of property flowMeteringDOM.
     */
    private org.jdom.Element flowMeteringDOM;

    /**
     * Gibt das Objekt als JDOM-Element zur�ck.
     * 
     * @return org.jdom.Element XMLRepr�sentation des Objekts.
     */
    public org.jdom.Element getFlowMeteringDOM() {
        Element flowMeteringElement = new Element("flowMetering");
        
        Iterator rulesIterator = this.rulesList.iterator();
        while(rulesIterator.hasNext()){
            FlowMeteringRule currentRule = (FlowMeteringRule) rulesIterator.next();
            flowMeteringElement.addContent(currentRule.getFlowMeteringRuleDOM());
        }
        
        if(this.flowExpirationActiveTimeout != null){
            Element flowExpirationElement = new Element("expiration");
        
            Element activeTimeoutElement = new Element("activeTimeout");
            if(this.flowExpirationActiveTimeoutUnit != null) activeTimeoutElement.setAttribute("unit", this.flowExpirationActiveTimeoutUnit);
            activeTimeoutElement.addContent(this.flowExpirationActiveTimeout.toString());
            flowExpirationElement.addContent(activeTimeoutElement);
            
            Element inactiveTimeoutElement = new Element("inactiveTimeout");
            if(this.flowExpirationInactiveTimeoutUnit != null) inactiveTimeoutElement.setAttribute("unit", this.flowExpirationInactiveTimeoutUnit);
            inactiveTimeoutElement.addContent(this.flowExpirationInactiveTimeout.toString());
            flowExpirationElement.addContent(inactiveTimeoutElement);
            flowMeteringElement.addContent(flowExpirationElement);
        }
        
        flowMeteringDOM = flowMeteringElement;
        return this.flowMeteringDOM;
    }
    
}
