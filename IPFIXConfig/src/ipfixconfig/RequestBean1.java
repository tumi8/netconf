/*
 * RequestBean1.java
 *
 * Created on 23. Juli 2006, 15:44
 * Copyright Max
 */
package ipfixconfig;

import com.sun.data.provider.RowKey;
import com.sun.rave.web.ui.appbase.AbstractRequestBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;

/**
 * <p>Request scope data bean for your application.  Create properties
 *  here to represent data that should be made available across different
 *  pages in the same HTTP request, so that the page bean classes do not
 *  have to be directly linked to each other.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class RequestBean1 extends AbstractRequestBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>


    /** 
     * <p>Construct a new request data bean instance.</p>
     */
    public RequestBean1() {
    }

    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
    }


    /** 
     * <p>This method is called when this bean is initially added to
     * request scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * request scope.</p>
     * 
     * <p>You may customize this method to allocate resources that are required
     * for the lifetime of the current request.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("RequestBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        
    }

    /** 
     * <p>This method is called when this bean is removed from
     * request scope.  This occurs automatically when the corresponding
     * HTTP response has been completed and sent to the client.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the request.</p>
     */
    public void destroy() {
    }

    /**
     * Holds value of property collectingProcessManagedId.
     */
    private int collectingProcessManagedId;

    /**
     * Getter for property collectingProcessManagedId.
     * @return Value of property collectingProcessManagedId.
     */
    public int getCollectingProcessManagedId() {

        return this.collectingProcessManagedId;
    }

    /**
     * Setter for property collectingProcessManagedId.
     * @param collectingProcessManagedId New value of property collectingProcessManagedId.
     */
    public void setCollectingProcessManagedId(int collectingProcessManagedId) {

        this.collectingProcessManagedId = collectingProcessManagedId;
    }

    /**
     * Holds value of property selectedRowKey.
     */
    private com.sun.data.provider.RowKey selectedRowKey;

    /**
     * Getter for property selectedRowKey.
     * @return Value of property selectedRowKey.
     */
    public com.sun.data.provider.RowKey getSelectedRowKey() {

        return this.selectedRowKey;
    }

    /**
     * Setter for property selectedRowKey.
     * @param selectedRowKey New value of property selectedRowKey.
     */
    public void setSelectedRowKey(com.sun.data.provider.RowKey selectedRowKey) {

        this.selectedRowKey = selectedRowKey;
    }

    /**
     * Holds value of property selectedMethod.
     */
    private String selectedMethod;

    /**
     * Getter for property selectedMethod.
     * @return Value of property selectedMethod.
     */
    public String getSelectedMethod() {

        return this.selectedMethod;
    }

    /**
     * Setter for property selectedMethod.
     * @param selectedMethod New value of property selectedMethod.
     */
    public void setSelectedMethod(String selectedMethod) {

        this.selectedMethod = selectedMethod;
    }

    /**
     * Holds value of property interval.
     */
    private String interval;

    /**
     * Getter for property interval.
     * @return Value of property interval.
     */
    public String getInterval() {

        return this.interval;
    }

    /**
     * Setter for property interval.
     * @param interval New value of property interval.
     */
    public void setInterval(String interval) {

        this.interval = interval;
    }

    /**
     * Holds value of property spacing.
     */
    private String spacing;

    /**
     * Getter for property spacing.
     * @return Value of property spacing.
     */
    public String getSpacing() {

        return this.spacing;
    }

    /**
     * Setter for property spacing.
     * @param spacing New value of property spacing.
     */
    public void setSpacing(String spacing) {

        this.spacing = spacing;
    }

    /**
     * Holds value of property population.
     */
    private String population;

    /**
     * Getter for property population.
     * @return Value of property population.
     */
    public String getPopulation() {

        return this.population;
    }

    /**
     * Setter for property population.
     * @param population New value of property population.
     */
    public void setPopulation(String population) {

        this.population = population;
    }

    /**
     * Holds value of property sample.
     */
    private String sample;

    /**
     * Getter for property sample.
     * @return Value of property sample.
     */
    public String getSample() {

        return this.sample;
    }

    /**
     * Setter for property sample.
     * @param sample New value of property sample.
     */
    public void setSample(String sample) {

        this.sample = sample;
    }

    /**
     * Holds value of property probability.
     */
    private String probability;

    /**
     * Getter for property probability.
     * @return Value of property probability.
     */
    public String getProbability() {

        return this.probability;
    }

    /**
     * Setter for property probability.
     * @param probability New value of property probability.
     */
    public void setProbability(String probability) {

        this.probability = probability;
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
     * Holds value of property filterMatchList.
     */
    private java.util.List filterMatchList;

    /**
     * Getter for property filterMatchList.
     * @return Value of property filterMatchList.
     */
    public java.util.List getFilterMatchList() {

        return this.filterMatchList;
    }

    /**
     * Setter for property filterMatchList.
     * @param filterMatchList New value of property filterMatchList.
     */
    public void setFilterMatchList(java.util.List filterMatchList) {

        this.filterMatchList = filterMatchList;
    }

    /**
     * Holds value of property reqFilterMatchDataProv.
     */
    private ipfixconfig.FilterMatchDataProvider reqFilterMatchDataProv = new FilterMatchDataProvider();

    /**
     * Getter for property reqFilterMatchDataProv.
     * @return Value of property reqFilterMatchDataProv.
     */
    public ipfixconfig.FilterMatchDataProvider getReqFilterMatchDataProv() {

        return this.reqFilterMatchDataProv;
    }

    /**
     * Setter for property reqFilterMatchDataProv.
     * @param reqFilterMatchDataProv New value of property reqFilterMatchDataProv.
     */
    public void setReqFilterMatchDataProv(ipfixconfig.FilterMatchDataProvider reqFilterMatchDataProv) {

        this.reqFilterMatchDataProv = reqFilterMatchDataProv;
    }

    /**
     * Holds value of property selectedMethodNr.
     */
    private int selectedMethodNr = -1;

    /**
     * Getter for property selectedMethodNr.
     * @return Value of property selectedMethodNr.
     */
    public int getSelectedMethodNr() {

        return this.selectedMethodNr;
    }

    /**
     * Setter for property selectedMethodNr.
     * @param selectedMethodNr New value of property selectedMethodNr.
     */
    public void setSelectedMethodNr(int selectedMethodNr) {

        this.selectedMethodNr = selectedMethodNr;
    }

    /**
     * Holds value of property isNewMethod.
     */
    private boolean isNewMethod = true;

    /**
     * Getter for property isNewMethod.
     * @return Value of property isNewMethod.
     */
    public boolean isIsNewMethod() {

        return this.isNewMethod;
    }

    /**
     * Setter for property isNewMethod.
     * @param isNewMethod New value of property isNewMethod.
     */
    public void setIsNewMethod(boolean isNewMethod) {

        this.isNewMethod = isNewMethod;
    }

    /**
     * Holds value of property newCollector.
     */
    private boolean newCollector;

    /**
     * Getter for property newCollector.
     * @return Value of property newCollector.
     */
    public boolean isNewCollector() {

        return this.newCollector;
    }

    /**
     * Setter for property newCollector.
     * @param newCollector New value of property newCollector.
     */
    public void setNewCollector(boolean newCollector) {

        this.newCollector = newCollector;
    }
    
}
