/*
 * ApplicationBean1.java
 *
 * 
 * Copyright Max
 */
package ipfixconfig;
import com.sun.data.provider.impl.MapDataProvider;
import com.sun.rave.web.ui.appbase.AbstractApplicationBean;
import com.sun.rave.web.ui.model.Option;
import java.io.File;
import java.util.List;
import javax.faces.FacesException;
import javax.servlet.ServletContext;

/**
 * <p>Application scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available to all users
 *  and pages in the application.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class ApplicationBean1 extends AbstractApplicationBean {
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

    private String appPath;
    /** 
     * <p>Construct a new application data bean instance.</p>
     */
    public ApplicationBean1() {
    }
    
    /** 
     * <p>This method is called when this bean is initially added to
     * application scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * application scope.</p>
     * 
     * <p>You may customize this method to initialize and cache application wide
     * data values (such as the lists of valid options for dropdown list
     * components), or to allocate resources that are required for the
     * lifetime of the application.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        
        ServletContext ApplicationsServletContext = (ServletContext) this.getExternalContext().getContext();
        appPath = ApplicationsServletContext.getRealPath("/DataStorage") + File.separator;
        
        String ipfixConfigNS = "urn:ietf:params:xml:ns:ipfix-config";
        
        configStorage = new ConfigStorage(appPath, ipfixConfigNS);
        
        appConfigInfoStorage = new ConfigInfoStorage(appPath, ipfixConfigNS, "ConfigInfoStorage.xml", "configInfoElements");
        List configInfoList = appConfigInfoStorage.getList();
        appConfigInfoDataProvider.setList(configInfoList);
        appConfigInfoDataProvider.setUserResizable(true);
                               
        appRoleStorage = new RoleStorage(appPath, ipfixConfigNS, "RoleStorage.xml", "roles");
        List roleList = appRoleStorage.getList();
        appRoleDataProvider.setList(roleList);
        appRoleDataProvider.setUserResizable(true);
                
        appDeviceStorage = new DeviceStorage(appPath, ipfixConfigNS, "DeviceStorage.xml", "devices");
        List deviceList = appDeviceStorage.getList();
        appDeviceDataProvider.setList(deviceList);
        appDeviceDataProvider.setUserResizable(true);
        
        appScenarioStorage = new ScenarioStorage(appPath, ipfixConfigNS, "ScenarioStorage.xml", "scenarios");
        List scenarioList = appScenarioStorage.getList();
        appScenarioDataProvider.setList(scenarioList);
        appScenarioDataProvider.setUserResizable(true);
                
        
        appObservationPointStorage = new ObservationPointStorage(appPath, ipfixConfigNS, "ObservationPointStorage.xml", "observationPoints");
                
        appCollectingProcessStorage = new CollectingProcessStorage(appPath, ipfixConfigNS, "CollectingProcessStorage.xml", "collectingProcesses");
        
        appMeteringProcessStorage = new MeteringProcessStorage(appPath, ipfixConfigNS, "MeteringProcessStorage.xml", "meteringProcesses");
        
        appExportingProcessStorage = new ExportingProcessStorage(appPath, ipfixConfigNS, "ExportingProcessStorage.xml", "exportingProcesses");
        
        
        
        //managedIdDevice = 0;
        //managedIdRole = 0;
        //managedIdConfig = 0;
        
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("ApplicationBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        
        addressTypeOptions = new com.sun.rave.web.ui.model.Option[] {
            new Option("4", "IPv4"),
            new Option("41","IPv6")
        };
        transportProtocolOptions = new com.sun.rave.web.ui.model.Option[] {
            new Option("6", "TCP"),
            new Option("17","UDP"),
            new Option("132","SCTP")
        };
        
        templateTimeoutUnitOptions = new com.sun.rave.web.ui.model.Option[] {
            new Option("sec", "sec"),
            new Option("msec","msec"),
            new Option("usec","usec")
        };
        
        packetSelectionTypeOptions = new com.sun.rave.web.ui.model.Option[]{
            new Option("none", "Choose a method"),
            new Option("rawFilter", "Raw Filter"),
            new Option("countBased", "Count based Sampling"),
            new Option("timeBased", "Time based Sampling"),
            new Option("randOutOfN", "Random out of N Sampling"),
            new Option("uniProb", "Uniform Probabilistic Sampling"),
            new Option("nonUniProb", "Non Uniform Probabilistic Sampling"),
            new Option("flowState","Flow State Sampling"),
            new Option("filterMatch","Match Filtering"),
            new Option("filterHash","Hash Filtering"),
            new Option("filterRState","Router State Filtering")
        };
    }

    /** 
     * <p>This method is called when this bean is removed from
     * application scope.  Typically, this occurs as a result of
     * the application being shut down by its owning container.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }
    
    
    /**
     * <p>Return an appropriate character encoding based on the
     * <code>Locale</code> defined for the current JavaServer Faces
     * view.  If no more suitable encoding can be found, return
     * "UTF-8" as a general purpose default.</p>
     *
     * <p>The default implementation uses the implementation from
     * our superclass, <code>AbstractApplicationBean</code>.</p>
     */
    public String getLocaleCharacterEncoding() {
        return super.getLocaleCharacterEncoding();
    }

    /**
     * Holds value of property appObservationPointDataProvider.
     */
    private ObservationPointDataProvider appObservationPointDataProvider = new ObservationPointDataProvider();

    /**
     * Getter for property appObservationPointDataProvider.
     * @return Value of property appObservationPointDataProvider.
     */
    public ObservationPointDataProvider getAppObservationPointDataProvider() {

        return this.appObservationPointDataProvider;
    }

    /**
     * Setter for property appObservationPointDataProvider.
     * @param appObservationPointDataProvider New value of property appObservationPointDataProvider.
     */
    public void setAppObservationPointDataProvider(ObservationPointDataProvider appObservationPointDataProvider) {

        this.appObservationPointDataProvider = appObservationPointDataProvider;
    }
    
    /**
     * Holds value of property appCollectingProcessDataProvider.
     */
    private CollectingProcessDataProvider appCollectingProcessDataProvider = new CollectingProcessDataProvider();

    /**
     * Getter for property appCollectingProcessDataProvider.
     * @return Value of property appCollectingProcessDataProvider.
     */
    public CollectingProcessDataProvider getAppCollectingProcessDataProvider() {

        return this.appCollectingProcessDataProvider;
    }

    /**
     * Setter for property appCollectingProcessDataProvider.
     * @param appCollectingProcessDataProvider New value of property appCollectingProcessDataProvider.
     */
    public void setAppCollectingProcessDataProvider(CollectingProcessDataProvider appCollectingProcessDataProvider) {

        this.appCollectingProcessDataProvider = appCollectingProcessDataProvider;
    }

    /**
     * Holds value of property addressTypeOptions.
     */
    private com.sun.rave.web.ui.model.Option[] addressTypeOptions;

    /**
     * Getter for property addressTypeOptions.
     * @return Value of property addressTypeOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getAddressTypeOptions() {

        return this.addressTypeOptions;
    }

    /**
     * Setter for property addressTypeOptions.
     * @param addressTypeOptions New value of property addressTypeOptions.
     */
    public void setAddressTypeOptions(com.sun.rave.web.ui.model.Option[] addressTypeOptions) {

        this.addressTypeOptions = addressTypeOptions;
    }

    /**
     * Holds value of property transportProtocolOptions.
     */
    private com.sun.rave.web.ui.model.Option[] transportProtocolOptions;

    /**
     * Getter for property transportProtocolOptions.
     * @return Value of property transportProtocolOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getTransportProtocolOptions() {

        return this.transportProtocolOptions;
    }

    /**
     * Setter for property transportProtocolOptions.
     * @param transportProtocolOptions New value of property transportProtocolOptions.
     */
    public void setTransportProtocolOptions(com.sun.rave.web.ui.model.Option[] transportProtocolOptions) {

        this.transportProtocolOptions = transportProtocolOptions;
    }

    /**
     * Holds value of property templateTimeoutUnitOptions.
     */
    private com.sun.rave.web.ui.model.Option[] templateTimeoutUnitOptions;

    /**
     * Getter for property templateTimeoutUnitOptions.
     * @return Value of property templateTimeoutUnitOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getTemplateTimeoutUnitOptions() {

        return this.templateTimeoutUnitOptions;
    }

    /**
     * Setter for property templateTimeoutUnitOptions.
     * @param templateTimeoutUnitOptions New value of property templateTimeoutUnitOptions.
     */
    public void setTemplateTimeoutUnitOptions(com.sun.rave.web.ui.model.Option[] templateTimeoutUnitOptions) {

        this.templateTimeoutUnitOptions = templateTimeoutUnitOptions;
    }
    
    private ObservationPointStorage appObservationPointStorage;
    /**
     * Getter for property appObservationPointStorage.
     * @return Value of property appObservationPointStorage.
     */
    public ipfixconfig.ObservationPointStorage getAppObservationPointStorage() {

        return this.appObservationPointStorage;
    }

    /**
     * Getter for property appPath.
     * @return Value of property appPath.
     */
    public String getAppPath() {

        return this.appPath;
    }

    /**
     * Holds value of property appMeteringProcessDataProvider.
     */
    private ipfixconfig.MeteringProcessDataProvider appMeteringProcessDataProvider = new MeteringProcessDataProvider();

    /**
     * Getter for property appMeteringProcessDataProvider.
     * @return Value of property appMeteringProcessDataProvider.
     */
    public ipfixconfig.MeteringProcessDataProvider getAppMeteringProcessDataProvider() {

        return this.appMeteringProcessDataProvider;
    }

    /**
     * Setter for property appMeteringProcessDataProvider.
     * @param appMeteringProcessDataProvider New value of property appMeteringProcessDataProvider.
     */
    public void setAppMeteringProcessDataProvider(ipfixconfig.MeteringProcessDataProvider appMeteringProcessDataProvider) {

        this.appMeteringProcessDataProvider = appMeteringProcessDataProvider;
    }

    /**
     * Holds value of property appMeteringProcessStorage.
     */
    private MeteringProcessStorage appMeteringProcessStorage;

    /**
     * Getter for property appMeteringProcessStorage.
     * @return Value of property appMeteringProcessStorage.
     */
    public MeteringProcessStorage getAppMeteringProcessStorage() {

        return this.appMeteringProcessStorage;
    }

    /**
     * Setter for property appMeteringProcessStorage.
     * @param appMeteringProcessStorage New value of property appMeteringProcessStorage.
     */
    public void setAppMeteringProcessStorage(MeteringProcessStorage appMeteringProcessStorage) {

        this.appMeteringProcessStorage = appMeteringProcessStorage;
    }

    /**
     * Holds value of property packetSelectionTypeOptions.
     */
    private com.sun.rave.web.ui.model.Option[] packetSelectionTypeOptions;

    /**
     * Getter for property packetSelectionTypeOptions.
     * @return Value of property packetSelectionTypeOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getPacketSelectionTypeOptions() {

        return this.packetSelectionTypeOptions;
    }
    private CollectingProcessStorage appCollectingProcessStorage;
    /**
     * Getter for property appCollectingProcessStorage.
     * @return Value of property appCollectingProcessStorage.
     */
    public ipfixconfig.CollectingProcessStorage getAppCollectingProcessStorage() {

        return this.appCollectingProcessStorage;
    }

    /**
     * Setter for property appCollectingProcessStorage.
     * @param appCollectingProcessStorage New value of property appCollectingProcessStorage.
     */
    public void setAppCollectingProcessStorage(ipfixconfig.CollectingProcessStorage appCollectingProcessStorage) {

        this.appCollectingProcessStorage = appCollectingProcessStorage;
    }

    /**
     * Holds value of property appConfigInfoStorage.
     */
    private ipfixconfig.ConfigInfoStorage appConfigInfoStorage;

    /**
     * Getter for property appConfigInfoStorage.
     * @return Value of property appConfigInfoStorage.
     */
    public ipfixconfig.ConfigInfoStorage getAppConfigInfoStorage() {

        return this.appConfigInfoStorage;
    }

    /**
     * Setter for property appConfigInfoStorage.
     * @param appConfigInfoStorage New value of property appConfigInfoStorage.
     */
    public void setAppConfigInfoStorage(ipfixconfig.ConfigInfoStorage appConfigInfoStorage) {

        this.appConfigInfoStorage = appConfigInfoStorage;
    }

    /**
     * Holds value of property appConfigInfoDataProvider.
     */
    private ipfixconfig.ConfigInfoDataProvider appConfigInfoDataProvider = new ConfigInfoDataProvider();

    /**
     * Getter for property appConfigInfoDataProvider.
     * @return Value of property appConfigInfoDataProvider.
     */
    public ipfixconfig.ConfigInfoDataProvider getAppConfigInfoDataProvider() {

        return this.appConfigInfoDataProvider;
    }

    /**
     * Setter for property appConfigInfoDataProvider.
     * @param appConfigInfoDataProvider New value of property appConfigInfoDataProvider.
     */
    public void setAppConfigInfoDataProvider(ipfixconfig.ConfigInfoDataProvider appConfigInfoDataProvider) {

        this.appConfigInfoDataProvider = appConfigInfoDataProvider;
    }

    /**
     * Holds value of property appDeviceStorage.
     */
    private ipfixconfig.DeviceStorage appDeviceStorage;

    /**
     * Getter for property appDeviceStorage.
     * @return Value of property appDeviceStorage.
     */
    public ipfixconfig.DeviceStorage getAppDeviceStorage() {

        return this.appDeviceStorage;
    }

    /**
     * Setter for property appDeviceStorage.
     * @param appDeviceStorage New value of property appDeviceStorage.
     */
    public void setAppDeviceStorage(ipfixconfig.DeviceStorage appDeviceStorage) {

        this.appDeviceStorage = appDeviceStorage;
    }

    /**
     * Holds value of property appDeviceDataProvider.
     */
    private ipfixconfig.DeviceDataProvider appDeviceDataProvider = new DeviceDataProvider();

    /**
     * Getter for property appDeviceDataProvider.
     * @return Value of property appDeviceDataProvider.
     */
    public ipfixconfig.DeviceDataProvider getAppDeviceDataProvider() {

        return this.appDeviceDataProvider;
    }

    /**
     * Setter for property appDeviceDataProvider.
     * @param appDeviceDataProvider New value of property appDeviceDataProvider.
     */
    public void setAppDeviceDataProvider(ipfixconfig.DeviceDataProvider appDeviceDataProvider) {

        this.appDeviceDataProvider = appDeviceDataProvider;
    }

    /**
     * Holds value of property appRoleStorage.
     */
    private ipfixconfig.RoleStorage appRoleStorage;

    /**
     * Getter for property appRoleStorage.
     * @return Value of property appRoleStorage.
     */
    public ipfixconfig.RoleStorage getAppRoleStorage() {

        return this.appRoleStorage;
    }

    /**
     * Setter for property appRoleStorage.
     * @param appRoleStorage New value of property appRoleStorage.
     */
    public void setAppRoleStorage(ipfixconfig.RoleStorage appRoleStorage) {

        this.appRoleStorage = appRoleStorage;
    }

    /**
     * Holds value of property appRoleDataProvider.
     */
    private ipfixconfig.RoleDataProvider appRoleDataProvider = new RoleDataProvider();

    /**
     * Getter for property appRoleDataProvider.
     * @return Value of property appRoleDataProvider.
     */
    public ipfixconfig.RoleDataProvider getAppRoleDataProvider() {

        return this.appRoleDataProvider;
    }

    /**
     * Setter for property appRoleDataProvider.
     * @param appRoleDataProvider New value of property appRoleDataProvider.
     */
    public void setAppRoleDataProvider(ipfixconfig.RoleDataProvider appRoleDataProvider) {

        this.appRoleDataProvider = appRoleDataProvider;
    }

    /**
     * Holds value of property appExportingProcessStorage.
     */
    private ipfixconfig.ExportingProcessStorage appExportingProcessStorage;

    /**
     * Getter for property appExportingProcessStorage.
     * @return Value of property appExportingProcessStorage.
     */
    public ipfixconfig.ExportingProcessStorage getAppExportingProcessStorage() {

        return this.appExportingProcessStorage;
    }

    /**
     * Setter for property appExportingProcessStorage.
     * @param appExportingProcessStorage New value of property appExportingProcessStorage.
     */
    public void setAppExportingProcessStorage(ipfixconfig.ExportingProcessStorage appExportingProcessStorage) {

        this.appExportingProcessStorage = appExportingProcessStorage;
    }

    /**
     * Holds value of property appScenarioDataProvider.
     */
    private ipfixconfig.ScenarioDataProvider appScenarioDataProvider = new ScenarioDataProvider();

    /**
     * Getter for property appScenarioDataProvider.
     * @return Value of property appScenarioDataProvider.
     */
    public ipfixconfig.ScenarioDataProvider getAppScenarioDataProvider() {

        return this.appScenarioDataProvider;
    }

    /**
     * Setter for property appScenarioDataProvider.
     * @param appScenarioDataProvider New value of property appScenarioDataProvider.
     */
    public void setAppScenarioDataProvider(ipfixconfig.ScenarioDataProvider appScenarioDataProvider) {

        this.appScenarioDataProvider = appScenarioDataProvider;
    }

    /**
     * Holds value of property appScenarioStorage.
     */
    private ipfixconfig.ScenarioStorage appScenarioStorage;

    /**
     * Getter for property appScenarioStorage.
     * @return Value of property appScenarioStorage.
     */
    public ipfixconfig.ScenarioStorage getAppScenarioStorage() {

        return this.appScenarioStorage;
    }

    /**
     * Setter for property appScenarioStorage.
     * @param appScenarioStorage New value of property appScenarioStorage.
     */
    public void setAppScenarioStorage(ipfixconfig.ScenarioStorage appScenarioStorage) {

        this.appScenarioStorage = appScenarioStorage;
    }

    /**
     * Holds value of property configStorage.
     */
    private ipfixconfig.ConfigStorage configStorage;

    /**
     * Getter for property configStorage.
     * @return Value of property configStorage.
     */
    public ipfixconfig.ConfigStorage getConfigStorage() {

        return this.configStorage;
    }

    /**
     * Setter for property configStorage.
     * @param configStorage New value of property configStorage.
     */
    public void setConfigStorage(ipfixconfig.ConfigStorage configStorage) {

        this.configStorage = configStorage;
    }

    
}
