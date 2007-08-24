/*
 * methodIdenifier.java
 *
 * 
 */

package ipfixconfig;

/**
 *
 * @author Maximilian Huetter
 */
public class SelectionMethodIdentifier extends Object{
    
    /** Creates a new instance of methodIdenifier */
    public SelectionMethodIdentifier() {
    }
    public SelectionMethodIdentifier(int methodListNr, String methodname, String methodDescrip) {
        setMethodNr(methodListNr);
        setMethodName(methodname);
        setMethodDescription(methodDescrip);
    }
    /**
     * Holds value of property methodName.
     */
    private String methodName;

    /**
     * Getter for property methodName.
     * @return Value of property methodName.
     */
    public String getMethodName() {

        return this.methodName;
    }

    /**
     * Setter for property methodName.
     * @param methodName New value of property methodName.
     */
    public void setMethodName(String methodName) {

        this.methodName = methodName;
    }

    /**
     * Holds value of property methodNr.
     */
    private int methodNr;

    /**
     * Getter for property methodNr.
     * @return Value of property methodNr.
     */
    public int getMethodNr() {

        return this.methodNr;
    }

    /**
     * Setter for property methodNr.
     * @param methodNr New value of property methodNr.
     */
    public void setMethodNr(int methodNr) {

        this.methodNr = methodNr;
    }

    /**
     * Holds value of property methodDescription.
     */
    private String methodDescription;

    /**
     * Getter for property methodDescription.
     * @return Value of property methodDescription.
     */
    public String getMethodDescription() {

        return this.methodDescription;
    }

    /**
     * Setter for property methodDescription.
     * @param methodDescription New value of property methodDescription.
     */
    public void setMethodDescription(String methodDescription) {

        this.methodDescription = methodDescription;
    }
    
}
