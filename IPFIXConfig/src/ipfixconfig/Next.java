/*
 * Next.java
 *
 * 
 *
 * 
 */

package ipfixconfig;

/**
 *
 * Next Objekt fuer die Next Processes, siehe IPFIX Config. Data Model
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class Next {
    
    /** Creates a new instance of Next */
    public Next() {
    }
    
    public Next(String name, Integer id) {
        setName(name);
        setId(id);
        
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
    
}
