/*
 * InformationElement.java
 *
 * 
 *
 * 
 */

package ipfixconfig;

/**
 * Basisklasse fuer alle Information Element Objekte, entsprechend dem 
 * IPFIX Configuration Data Model. Also Flow KEys, etc. 
 *
 * @author Maximilian H�tter
 * e-mail: maxhuetter@web.de
 */
public class InformationElement {
    
    /** Creates a new instance of InformationElement */
    public InformationElement() {
    }
   /* Konstruktor mit Parametern */
    public InformationElement(Integer enterprNr, String ieName, Integer ieId, Integer ieLength, String match, String modifier) {
        this.setEnterpriseNumber(enterprNr);
        this.setIeName(ieName);
        this.setIeId(ieId);
        this.setIeLength(ieLength);
        this.setMatch(match);
        this.setModifier(modifier);
    }
    /**
     * Holds value of property enterpriseNumber.
     */
    private Integer enterpriseNumber;

    /**
     * Getter for property enterpriseNumber.
     * @return Value of property enterpriseNumber.
     */
    public Integer getEnterpriseNumber() {

        return this.enterpriseNumber;
    }

    /**
     * Setter for property enterpriseNumber.
     * @param enterpriseNumber New value of property enterpriseNumber.
     */
    public void setEnterpriseNumber(Integer enterpriseNumber) {

        this.enterpriseNumber = enterpriseNumber;
    }

    /**
     * Holds value of property ieName.
     */
    private String ieName;

    /**
     * Getter for property ieName.
     * @return Value of property ieName.
     */
    public String getIeName() {

        return this.ieName;
    }

    /**
     * Setter for property ieName.
     * @param ieName New value of property ieName.
     */
    public void setIeName(String ieName) {

        this.ieName = ieName;
    }

    /**
     * Holds value of property ieId.
     */
    private Integer ieId;

    /**
     * Getter for property ieId.
     * @return Value of property ieId.
     */
    public Integer getIeId() {

        return this.ieId;
    }

    /**
     * Setter for property ieId.
     * @param ieId New value of property ieId.
     */
    public void setIeId(Integer ieId) {

        this.ieId = ieId;
    }

    /**
     * Holds value of property ieLength.
     */
    private Integer ieLength;

    /**
     * Getter for property ieLength.
     * @return Value of property ieLength.
     */
    public Integer getIeLength() {

        return this.ieLength;
    }

    /**
     * Setter for property ieLength.
     * @param ieLength New value of property ieLength.
     */
    public void setIeLength(Integer ieLength) {

        this.ieLength = ieLength;
    }

    /**
     * Holds value of property match.
     */
    private String match;

    /**
     * Getter for property match.
     * @return Value of property match.
     */
    public String getMatch() {

        return this.match;
    }

    /**
     * Setter for property match.
     * @param match New value of property match.
     */
    public void setMatch(String match) {

        this.match = match;
    }

    /**
     * Holds value of property modifier.
     */
    private String modifier;

    /**
     * Getter for property modifier.
     * @return Value of property modifier.
     */
    public String getModifier() {

        return this.modifier;
    }

    /**
     * Setter for property modifier.
     * @param modifier New value of property modifier.
     */
    public void setModifier(String modifier) {

        this.modifier = modifier;
    }
    
}
