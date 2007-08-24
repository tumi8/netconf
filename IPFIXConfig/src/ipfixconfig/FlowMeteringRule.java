/*
 * FlowMeteringRule.java
 *
 * 
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * Klasse die eine FlowMetering Rule repraesentiert.
 * siehe IPFIX Configuration Data Model
 *
 * @author Maximilian Huetter
 */
public class FlowMeteringRule {
    
    /** Creates a new instance of FlowMeteringRule */
    public FlowMeteringRule() {
    }
    /*
     * Konstruktor mit Parametern
     * @param Integer templateId Id des Rule-Template
     * @param List flowKeys Liste der Flowkeys der Rule
     * @param List nonflowKeys Liste der NonFlowkeys der Rule
     *
     */
    public FlowMeteringRule(Integer templateid, List flowKeys, List nonFlowKeys) {
        setTemplateId(templateid);
        setFlowKeyList(flowKeys);
        setNonFlowKeyList(nonFlowKeys);
    }
    /**
     * Holds value of property templateId.
     */
    private Integer templateId;

    /**
     * Getter for property templateId.
     * @return Value of property templateId.
     */
    public Integer getTemplateId() {

        return this.templateId;
    }

    /**
     * Setter for property templateId.
     * @param templateId New value of property templateId.
     */
    public void setTemplateId(Integer templateId) {

        this.templateId = templateId;
    }

    /**
     * Holds value of property flowKeyList.
     */
    private java.util.List flowKeyList = new ArrayList();

    /**
     * Getter for property flowKeyList.
     * @return Value of property flowKeyList.
     */
    public java.util.List getFlowKeyList() {

        return this.flowKeyList;
    }

    /**
     * Setter for property flowKeyList.
     * @param flowKeyList New value of property flowKeyList.
     */
    public void setFlowKeyList(java.util.List flowKeyList) {

        this.flowKeyList = flowKeyList;
    }

    /**
     * Holds value of property nonFlowKeyList.
     */
    private java.util.List nonFlowKeyList = new ArrayList();

    /**
     * Getter for property nonFlowKeyList.
     * @return Value of property nonFlowKeyList.
     */
    public java.util.List getNonFlowKeyList() {

        return this.nonFlowKeyList;
    }

    /**
     * Setter for property nonFlowKeyList.
     * @param nonFlowKeyList New value of property nonFlowKeyList.
     */
    public void setNonFlowKeyList(java.util.List nonFlowKeyList) {

        this.nonFlowKeyList = nonFlowKeyList;
    }

    /**
     * Holds value of property flowMeteringDOM.
     */
    private org.jdom.Element flowMeteringRuleDOM;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * Implementiert das Interface AbstactIPFIXProcess 
     * @return org.jdom.Element JDOMElement Repr�sentation des Objekts.
     */
    public org.jdom.Element getFlowMeteringRuleDOM() {
        Element flowMeteringRuleElement = new Element("rule");
        
        Element ruleTemplateId = new Element("templateId");
        ruleTemplateId.addContent(this.templateId.toString());
        flowMeteringRuleElement.addContent(ruleTemplateId);        
        
        Iterator flowKeyListIterator = this.flowKeyList.iterator();
        while(flowKeyListIterator.hasNext()){
            Element infoElement = new Element("flowKey");
            InformationElement currentIE = (InformationElement) flowKeyListIterator.next();
            
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
            
            flowMeteringRuleElement.addContent(infoElement); 
        }
        
        Iterator nonFlowKeyListIterator = this.nonFlowKeyList.iterator();
        while(flowKeyListIterator.hasNext()){
            Element infoElement = new Element("nonflowKey");
            InformationElement currentIE = (InformationElement) nonFlowKeyListIterator.next();
            
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
            
            flowMeteringRuleElement.addContent(infoElement); 
        }
        
        flowMeteringRuleDOM = flowMeteringRuleElement;
        
        return this.flowMeteringRuleDOM;
    }
    
}
