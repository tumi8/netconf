/*
 * Scenario.java
 * 
 * 
 */

package ipfixconfig;

import java.util.Iterator;
import org.jdom.Element;

/**
 * Klasse um ein Scenario im System zu repraesentieren.
 * Eigenschaften sind:
 * Name, Description, DeviceList
 *
 * @author Maximilian H�tter
 */
public class Scenario extends Object implements AbstractIPFIXProcess{
    
    /** Creates a new instance of Scenario */
    public Scenario() {
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
     * Holds value of property deviceList.
     */
    private java.util.List deviceList;

    /**
     * Getter for property deviceList.
     * @return Value of property deviceList.
     */
    public java.util.List getDeviceList() {

        return this.deviceList;
    }

    /**
     * Setter for property deviceList.
     * @param deviceList New value of property deviceList.
     */
    public void setDeviceList(java.util.List deviceList) {

        this.deviceList = deviceList;
    }

    /**
     * Holds value of property dOMElement.
     */
    private org.jdom.Element DOMElement;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * 
     * @return org.jdom.Element XMLRepr�sentation des Objekts.
     */
    public org.jdom.Element getDOMElement() {
        Element scenarioElement = new Element("scenario");
        //scenarioElement.setAttribute("id", this.id.toString());
        
        Element nameElement = new Element("name");
        nameElement.addContent(this.name);
        scenarioElement.addContent(nameElement);
        
        Element descriptionElement = new Element("descript");
        descriptionElement.addContent(this.description);
        scenarioElement.addContent(descriptionElement);
        
        Element devicesElement = new Element("devices");
        Iterator deviceIterator = this.deviceList.iterator();
        
        while(deviceIterator.hasNext()){
           String deviceName = (String) deviceIterator.next();
                    
           Element deviceElement = new Element("device");
           deviceElement.addContent(deviceName);
           
           devicesElement.addContent(deviceElement);
        }
        
        scenarioElement.addContent(devicesElement);
        return this.DOMElement;
    }
    
}
