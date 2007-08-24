/*
 * ConfigInfo.java
 * 
 * 
 */

package ipfixconfig;
import org.jdom.Element;

/**
 * Konfigurationsinformations-Klasse
 * Um die zusaetzlichen Informationen ueber eine Konfiguration (Name, Letzte �nderung, etc.)
 * zu halten.
 * @author Maximilian H�tter
 */
public class ConfigInfo extends Object implements AbstractIPFIXProcess{
    
    /** Creates a new instance of ConfigInfo */
    public ConfigInfo() {
    }
    /* Kosntruktor mit Parametern */
    public ConfigInfo(String nam, String lastmod, String filenam, String descript){
        setName(nam);
        setLastmodified(lastmod);
        setFileName(filenam);
        setDescription(descript);
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
     * Holds value of property lastmodified.
     */
    private String lastmodified;

    /**
     * Getter for property lastmodified.
     * @return Value of property lastmodified.
     */
    public String getLastmodified() {

        return this.lastmodified;
    }

    /**
     * Setter for property lastmodified.
     * @param lastmodified New value of property lastmodified.
     */
    public void setLastmodified(String lastmodified) {

        this.lastmodified = lastmodified;
    }

    /**
     * Holds value of property fileName.
     */
    private String fileName;

    /**
     * Getter for property fileName.
     * @return Value of property fileName.
     */
    public String getFileName() {

        return this.fileName;
    }

    /**
     * Setter for property fileName.
     * @param fileName New value of property fileName.
     */
    public void setFileName(String fileName) {

        this.fileName = fileName;
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
     * Holds XML Represetation of this object.
     */
    private org.jdom.Element DOMElement;

    /**
     * Getter for the XML Representation as DOMElement.
     * Siehe Abstract IPFIXProcess.java
     * @return Ein org.jdom.Element das die Configuration Information darstellt. 
     */
    public org.jdom.Element getDOMElement() {
        Element configInfoElement = new Element("configInfo");
        //configInfoElement.setAttribute("id", this.id.toString());
        
        Element nameElement = new Element("name");
        nameElement.addContent(this.name);
        configInfoElement.addContent(nameElement);
        
        Element lastModElement = new Element("lastModified");
        lastModElement.addContent(this.lastmodified);
        configInfoElement.addContent(lastModElement);
        
        Element fileNameElement = new Element("fileName");
        fileNameElement.addContent(this.fileName);
        configInfoElement.addContent(fileNameElement);

        Element descriptionElement = new Element("description");
        descriptionElement.addContent(this.description);
        configInfoElement.addContent(descriptionElement);
        
        this.DOMElement = configInfoElement;
        
        return this.DOMElement;
    }
    
}
