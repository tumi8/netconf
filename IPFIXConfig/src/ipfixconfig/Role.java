/*
 * Role.java
 * 
 * 
 */

package ipfixconfig;

import java.util.Iterator;
import java.util.Map;
import org.jdom.Element;

/**
 * Klasse um eine Role im System zu repraesentieren.
 * Eigenschaften sind:
 * Name, Description, Mapping
 *
 * @author Maximilian H�tter
 */
public class Role extends Object implements AbstractIPFIXProcess{
    
    /** Creates a new instance of Role */
    public Role() {
    }

    /**
     * Holds value of property name.
     */
    private String name;

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {

        return this.name;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Holds value of property description.
     */
    private String description;

    /**
     * Getter for property description.
     * @return Value of property description.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Holds value of property id.
     */
    private Integer id;

    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public Integer getId() {

        return this.id;
    }

    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * Holds value of property mapping.
     */
    private java.util.HashMap mapping;

    /**
     * Getter for property mapping.
     * @return Value of property mapping.
     */
    public java.util.HashMap getMapping() {

        return this.mapping;
    }

    /**
     * Setter for property mapping.
     * @param mapping New value of property mapping.
     */
    public void setMapping(java.util.HashMap mapping) {

        this.mapping = mapping;
    }

    /**
     * Holds value of property dOMElement.
     */
    private org.jdom.Element DOMElement;

    /**
     * Getter for property dOMElement.
     * Gibt die JDOM-Repr�sentation der Role zurueck.
     * @return org.jdom.Element JDOM-Element der Role
     */
    public org.jdom.Element getDOMElement() {
        Element roleElement = new Element("role");
        //roleElement.setAttribute("id", this.id.toString());
        
        Element nameElement = new Element("name");
        nameElement.addContent(this.name);
        roleElement.addContent(nameElement);
        
        Element descriptionElement = new Element("description");
        descriptionElement.addContent(this.description);
        roleElement.addContent(descriptionElement);
        
        Element mappingElement = new Element("mapping");
        Iterator entryIterator = this.mapping.entrySet().iterator();
        
        while(entryIterator.hasNext()){
           Map.Entry entry = (Map.Entry) entryIterator.next();
           Element mappingItem = new Element("item");
           
           String deviceName = (String)entry.getKey();
           Element deviceElement = new Element("deviceId");
           deviceElement.addContent(deviceName);
           mappingItem.addContent(deviceElement);
           
           String configName = (String)entry.getValue();
           Element configElement = new Element("configId");
           configElement.addContent(configName);
           mappingItem.addContent(configElement);
           
           mappingElement.addContent(mappingItem);
        }
        
        roleElement.addContent(mappingElement);
      
        this.DOMElement = roleElement;
        
        return this.DOMElement;
    }
    
}
