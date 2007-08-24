/*
 * SessionBean1.java
 *
 * 
 * Copyright Max
 */
package ipfixconfig;
import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.MapDataProvider;
import com.sun.data.provider.impl.TableRowDataProvider;
import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import java.util.HashSet;
import javax.faces.FacesException;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class SessionBean1 extends AbstractSessionBean {
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
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean1() {
    }

    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     * 
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
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
            log("SessionBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        //collectingTableSelectPhaseListener.keepSelected(true);
    }

    /** 
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     * 
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    public void passivate() {
    }

    /** 
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     * 
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    public void activate() {
    }

    /** 
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
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
     * Holds value of property collectingTableSelectPhaseListener.
     */
    private com.sun.rave.web.ui.event.TableSelectPhaseListener collectingTableSelectPhaseListener = new TableSelectPhaseListener();

    /**
     * Getter for property collectingTableSelectPhaseListener.
     * @return Value of property collectingTableSelectPhaseListener.
     */
    public com.sun.rave.web.ui.event.TableSelectPhaseListener getCollectingTableSelectPhaseListener() {

        return this.collectingTableSelectPhaseListener;
    }

    /**
     * Setter for property collectingTableSelectPhaseListener.
     * @param collectingTableSelectPhaseListener New value of property collectingTableSelectPhaseListener.
     */
    public void setCollectingTableSelectPhaseListener(com.sun.rave.web.ui.event.TableSelectPhaseListener collectingTableSelectPhaseListener) {

        this.collectingTableSelectPhaseListener = collectingTableSelectPhaseListener;
    }

    /**
     * Holds value of property packetSelectionData.
     */
    private ipfixconfig.PacketSelection packetSelectionData;

    /**
     * Getter for property packetSelectionData.
     * @return Value of property packetSelectionData.
     */
    public ipfixconfig.PacketSelection getPacketSelectionData() {

        return this.packetSelectionData;
    }

    /**
     * Setter for property packetSelectionData.
     * @param packetSelectionData New value of property packetSelectionData.
     */
    public void setPacketSelectionData(ipfixconfig.PacketSelection packetSelectionData) {

        this.packetSelectionData = packetSelectionData;
    }

    /**
     * Holds value of property selectionMethodOptions.
     */
    private com.sun.rave.web.ui.model.Option[] selectionMethodOptions = new Option[]{};

    /**
     * Getter for property selectionMethodOptions.
     * @return Value of property selectionMethodOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getSelectionMethodOptions() {

        return this.selectionMethodOptions;
    }

    /**
     * Setter for property selectionMethodOptions.
     * @param selectionMethodOptions New value of property selectionMethodOptions.
     */
    public void setSelectionMethodOptions(com.sun.rave.web.ui.model.Option[] selectionMethodOptions) {

        this.selectionMethodOptions = selectionMethodOptions;
    }

    /**
     * Holds value of property sesFilterMatchDataProvider.
     */
    private ipfixconfig.FilterMatchDataProvider sesFilterMatchDataProvider = new FilterMatchDataProvider(); 

    /**
     * Getter for property sesFilterMatchDataProvider.
     * @return Value of property sesFilterMatchDataProvider.
     */
    public ipfixconfig.FilterMatchDataProvider getSesFilterMatchDataProvider() {

        return this.sesFilterMatchDataProvider;
    }

    /**
     * Setter for property sesFilterMatchDataProvider.
     * @param sesFilterMatchDataProvider New value of property sesFilterMatchDataProvider.
     */
    public void setSesFilterMatchDataProvider(ipfixconfig.FilterMatchDataProvider sesFilterMatchDataProvider) {

        this.sesFilterMatchDataProvider = sesFilterMatchDataProvider;
    }

    /**
     * Holds value of property selectedMethodNr.
     */
    private int selectedMethodNr;

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
     * Holds value of property newMethod.
     */
    private boolean newMethod;

    /**
     * Getter for property newMethod.
     * @return Value of property newMethod.
     */
    public boolean isNewMethod() {

        return this.newMethod;
    }

    /**
     * Setter for property newMethod.
     * @param newMethod New value of property newMethod.
     */
    public void setNewMethod(boolean newMethod) {

        this.newMethod = newMethod;
    }

    /**
     * Holds value of property observationDataProvider.
     */
    private ipfixconfig.ObservationPointDataProvider observationDataProvider = new ObservationPointDataProvider();

    /**
     * Getter for property observationDataProvider.
     * @return Value of property observationDataProvider.
     */
    public ipfixconfig.ObservationPointDataProvider getObservationDataProvider() {

        return this.observationDataProvider;
    }

    /**
     * Setter for property observationDataProvider.
     * @param observationDataProvider New value of property observationDataProvider.
     */
    public void setObservationDataProvider(ipfixconfig.ObservationPointDataProvider observationDataProvider) {

        this.observationDataProvider = observationDataProvider;
    }

    /**
     * Holds value of property collectingDataProvider.
     */
    private ipfixconfig.CollectingProcessDataProvider collectingDataProvider = new CollectingProcessDataProvider();

    /**
     * Getter for property collectingDataProvider.
     * @return Value of property collectingDataProvider.
     */
    public ipfixconfig.CollectingProcessDataProvider getCollectingDataProvider() {

        return this.collectingDataProvider;
    }

    /**
     * Setter for property collectingDataProvider.
     * @param collectingDataProvider New value of property collectingDataProvider.
     */
    public void setCollectingDataProvider(ipfixconfig.CollectingProcessDataProvider collectingDataProvider) {

        this.collectingDataProvider = collectingDataProvider;
    }

    /**
     * Holds value of property meteringDataProvider.
     */
    private ipfixconfig.MeteringProcessDataProvider meteringDataProvider = new MeteringProcessDataProvider();

    /**
     * Getter for property meteringDataProvider.
     * @return Value of property meteringDataProvider.
     */
    public ipfixconfig.MeteringProcessDataProvider getMeteringDataProvider() {

        return this.meteringDataProvider;
    }

    /**
     * Setter for property meteringDataProvider.
     * @param meteringDataProvider New value of property meteringDataProvider.
     */
    public void setMeteringDataProvider(ipfixconfig.MeteringProcessDataProvider meteringDataProvider) {

        this.meteringDataProvider = meteringDataProvider;
    }

    /**
     * Holds value of property newConfig.
     */
    private boolean newConfig;

    /**
     * Getter for property newConfig.
     * @return Value of property newConfig.
     */
    public boolean isNewConfig() {

        return this.newConfig;
    }

    /**
     * Setter for property newConfig.
     * @param newConfig New value of property newConfig.
     */
    public void setNewConfig(boolean newConfig) {

        this.newConfig = newConfig;
    }

    /**
     * Holds value of property configInfo.
     */
    private ipfixconfig.ConfigInfo configInfo = new ConfigInfo();

    /**
     * Getter for property configInfo.
     * @return Value of property configInfo.
     */
    public ipfixconfig.ConfigInfo getConfigInfo() {

        return this.configInfo;
    }

    /**
     * Setter for property configInfo.
     * @param configInfo New value of property configInfo.
     */
    public void setConfigInfo(ipfixconfig.ConfigInfo configInfo) {

        this.configInfo = configInfo;
    }

    /**
     * Holds value of property newDevice.
     */
    private boolean newDevice;

    /**
     * Getter for property newDevice.
     * @return Value of property newDevice.
     */
    public boolean isNewDevice() {

        return this.newDevice;
    }

    /**
     * Setter for property newDevice.
     * @param newDevice New value of property newDevice.
     */
    public void setNewDevice(boolean newDevice) {

        this.newDevice = newDevice;
    }

    /**
     * Holds value of property sesPacketReportingDataProvider.
     */
    private PacketReportingDataProvider sesPacketReportingDataProvider = new PacketReportingDataProvider();

    /**
     * Getter for property sesPacketReportingDataProvider.
     * @return Value of property sesPacketReportingDataProvider.
     */
    public PacketReportingDataProvider getSesPacketReportingDataProvider() {

        return this.sesPacketReportingDataProvider;
    }

    /**
     * Setter for property sesPacketReportingDataProvider.
     * @param sesPacketReportingDataProvider New value of property sesPacketReportingDataProvider.
     */
    public void setSesPacketReportingDataProvider(PacketReportingDataProvider sesPacketReportingDataProvider) {

        this.sesPacketReportingDataProvider = sesPacketReportingDataProvider;
    }

    /**
     * Holds value of property packetReportingData.
     */
    private ipfixconfig.PacketReporting packetReportingData;

    /**
     * Getter for property packetReportingData.
     * @return Value of property packetReportingData.
     */
    public ipfixconfig.PacketReporting getPacketReportingData() {

        return this.packetReportingData;
    }

    /**
     * Setter for property packetReportingData.
     * @param packetReportingData New value of property packetReportingData.
     */
    public void setPacketReportingData(ipfixconfig.PacketReporting packetReportingData) {

        this.packetReportingData = packetReportingData;
    }

    /**
     * Holds value of property flowMeteringData.
     */
    private ipfixconfig.FlowMetering flowMeteringData;

    /**
     * Getter for property flowMeteringData.
     * @return Value of property flowMeteringData.
     */
    public ipfixconfig.FlowMetering getFlowMeteringData() {

        return this.flowMeteringData;
    }

    /**
     * Setter for property flowMeteringData.
     * @param flowMeteringData New value of property flowMeteringData.
     */
    public void setFlowMeteringData(ipfixconfig.FlowMetering flowMeteringData) {

        this.flowMeteringData = flowMeteringData;
    }

    /**
     * Holds value of property flowkeyDataProvider.
     */
    private ipfixconfig.FlowkeyDataProvider flowkeyDataProvider = new FlowkeyDataProvider();

    /**
     * Getter for property flowkeyDataProvider.
     * @return Value of property flowkeyDataProvider.
     */
    public ipfixconfig.FlowkeyDataProvider getFlowkeyDataProvider() {

        return this.flowkeyDataProvider;
    }

    /**
     * Setter for property flowkeyDataProvider.
     * @param flowkeyDataProvider New value of property flowkeyDataProvider.
     */
    public void setFlowkeyDataProvider(ipfixconfig.FlowkeyDataProvider flowkeyDataProvider) {

        this.flowkeyDataProvider = flowkeyDataProvider;
    }

    /**
     * Holds value of property nonflowkeyDataProvider.
     */
    private ipfixconfig.NonFlowKeyDataProvider nonFlowKeyDataProvider = new NonFlowKeyDataProvider();

    /**
     * Getter for property nonflowkeyDataProvider.
     * @return Value of property nonflowkeyDataProvider.
     */
    public ipfixconfig.NonFlowKeyDataProvider getNonFlowKeyDataProvider() {

        return this.nonFlowKeyDataProvider;
    }

    /**
     * Setter for property nonflowkeyDataProvider.
     * @param nonFlowkeyDataProvider New value of property nonflowkeyDataProvider.
     */
    public void setNonFlowkeyDataProvider(ipfixconfig.NonFlowKeyDataProvider nonFlowkeyDataProvider) {

        this.nonFlowKeyDataProvider = nonFlowKeyDataProvider;
    }

    /**
     * Holds value of property ruleTemplate.
     */
    private String ruleTemplate;

    /**
     * Getter for property ruleTemplate.
     * @return Value of property ruleTemplate.
     */
    public String getRuleTemplate() {

        return this.ruleTemplate;
    }

    /**
     * Setter for property ruleTemplate.
     * @param ruleTemplate New value of property ruleTemplate.
     */
    public void setRuleTemplate(String ruleTemplate) {

        this.ruleTemplate = ruleTemplate;
    }

    /**
     * Holds value of property newFlowMeteringRule.
     */
    private boolean newFlowMeteringRule;

    /**
     * Getter for property newFlowMeteringRule.
     * @return Value of property newFlowMeteringRule.
     */
    public boolean isNewFlowMeteringRule() {

        return this.newFlowMeteringRule;
    }

    /**
     * Setter for property newFlowMeteringRule.
     * @param newFlowMeteringRule New value of property newFlowMeteringRule.
     */
    public void setNewFlowMeteringRule(boolean newFlowMeteringRule) {

        this.newFlowMeteringRule = newFlowMeteringRule;
    }

    /**
     * Holds value of property newKey.
     */
    private boolean newKey;

    /**
     * Getter for property newKey.
     * @return Value of property newKey.
     */
    public boolean isNewKey() {

        return this.newKey;
    }

    /**
     * Setter for property newKey.
     * @param newKey New value of property newKey.
     */
    public void setNewKey(boolean newKey) {

        this.newKey = newKey;
    }

    /**
     * Holds value of property newFilterMatch.
     */
    private boolean newFilterMatch;

    /**
     * Getter for property newFilterMatch.
     * @return Value of property newFilterMatch.
     */
    public boolean isNewFilterMatch() {

        return this.newFilterMatch;
    }

    /**
     * Setter for property newFilterMatch.
     * @param newFilterMatch New value of property newFilterMatch.
     */
    public void setNewFilterMatch(boolean newFilterMatch) {

        this.newFilterMatch = newFilterMatch;
    }

    /**
     * Holds value of property exportingDataProvider.
     */
    private ipfixconfig.ExportingProcessDataProvider exportingDataProvider = new ExportingProcessDataProvider();

    /**
     * Getter for property exportingDataProvider.
     * @return Value of property exportingDataProvider.
     */
    public ipfixconfig.ExportingProcessDataProvider getExportingDataProvider() {

        return this.exportingDataProvider;
    }

    /**
     * Setter for property exportingDataProvider.
     * @param exportingDataProvider New value of property exportingDataProvider.
     */
    public void setExportingDataProvider(ipfixconfig.ExportingProcessDataProvider exportingDataProvider) {

        this.exportingDataProvider = exportingDataProvider;
    }

    /**
     * Holds value of property newObservationPoint.
     */
    private boolean newObservationPoint;

    /**
     * Getter for property newObservationPoint.
     * @return Value of property newObservationPoint.
     */
    public boolean isNewObservationPoint() {

        return this.newObservationPoint;
    }

    /**
     * Setter for property newObservationPoint.
     * @param newObservationPoint New value of property newObservationPoint.
     */
    public void setNewObservationPoint(boolean newObservationPoint) {

        this.newObservationPoint = newObservationPoint;
    }

    /**
     * Holds value of property newCollectingProcess.
     */
    private boolean newCollectingProcess;

    /**
     * Getter for property newCollectingProcess.
     * @return Value of property newCollectingProcess.
     */
    public boolean isNewCollectingProcess() {

        return this.newCollectingProcess;
    }

    /**
     * Setter for property newCollectingProcess.
     * @param newCollectingProcess New value of property newCollectingProcess.
     */
    public void setNewCollectingProcess(boolean newCollectingProcess) {

        this.newCollectingProcess = newCollectingProcess;
    }

    /**
     * Holds value of property selectedMeteringProcessRowKey.
     */
    private com.sun.data.provider.RowKey selectedMeteringProcessRowKey;

    /**
     * Getter for property selectedMeteringProcessRowKe.
     * @return Value of property selectedMeteringProcessRowKe.
     */
    public com.sun.data.provider.RowKey getSelectedMeteringProcessRowKey() {

        return this.selectedMeteringProcessRowKey;
    }

    /**
     * Setter for property selectedMeteringProcessRowKe.
     * @param selectedMeteringProcessRowKey New value of property selectedMeteringProcessRowKe.
     */
    public void setSelectedMeteringProcessRowKey(com.sun.data.provider.RowKey selectedMeteringProcessRowKey) {

        this.selectedMeteringProcessRowKey = selectedMeteringProcessRowKey;
    }

    /**
     * Holds value of property selectedObservationPointRowKey.
     */
    private com.sun.data.provider.RowKey selectedObservationPointRowKey;

    /**
     * Getter for property selectedObservationPointRowKey.
     * @return Value of property selectedObservationPointRowKey.
     */
    public com.sun.data.provider.RowKey getSelectedObservationPointRowKey() {

        return this.selectedObservationPointRowKey;
    }

    /**
     * Setter for property selectedObservationPointRowKey.
     * @param selectedObservationPointRowKey New value of property selectedObservationPointRowKey.
     */
    public void setSelectedObservationPointRowKey(com.sun.data.provider.RowKey selectedObservationPointRowKey) {

        this.selectedObservationPointRowKey = selectedObservationPointRowKey;
    }

    /**
     * Holds value of property selectedCollectingProcessRowKey.
     */
    private com.sun.data.provider.RowKey selectedCollectingProcessRowKey;

    /**
     * Getter for property selectedCollectingProcessRowKey.
     * @return Value of property selectedCollectingProcessRowKey.
     */
    public com.sun.data.provider.RowKey getSelectedCollectingProcessRowKey() {

        return this.selectedCollectingProcessRowKey;
    }

    /**
     * Setter for property selectedCollectingProcessRowKey.
     * @param selectedCollectingProcessRowKey New value of property selectedCollectingProcessRowKey.
     */
    public void setSelectedCollectingProcessRowKey(com.sun.data.provider.RowKey selectedCollectingProcessRowKey) {

        this.selectedCollectingProcessRowKey = selectedCollectingProcessRowKey;
    }

    /**
     * Holds value of property selectedExportingProcessRowKey.
     */
    private com.sun.data.provider.RowKey selectedExportingProcessRowKey;

    /**
     * Getter for property selectedExportingProcessRowKey.
     * @return Value of property selectedExportingProcessRowKey.
     */
    public com.sun.data.provider.RowKey getSelectedExportingProcessRowKey() {

        return this.selectedExportingProcessRowKey;
    }

    /**
     * Setter for property selectedExportingProcessRowKey.
     * @param selectedExportingProcessRowKey New value of property selectedExportingProcessRowKey.
     */
    public void setSelectedExportingProcessRowKey(com.sun.data.provider.RowKey selectedExportingProcessRowKey) {

        this.selectedExportingProcessRowKey = selectedExportingProcessRowKey;
    }

    /**
     * Holds value of property listenerDataProvider.
     */
    private ipfixconfig.CollectorDataProvider listenerDataProvider = new CollectorDataProvider();

    /**
     * Getter for property listenerDataProvider.
     * @return Value of property listenerDataProvider.
     */
    public ipfixconfig.CollectorDataProvider getListenerDataProvider() {

        return this.listenerDataProvider;
    }

    /**
     * Setter for property listenerDataProvider.
     * @param listenerDataProvider New value of property listenerDataProvider.
     */
    public void setListenerDataProvider(ipfixconfig.CollectorDataProvider listenerDataProvider) {

        this.listenerDataProvider = listenerDataProvider;
    }

    /**
     * Holds value of property receivingCollectorsDataProvider.
     */
    private ipfixconfig.CollectorDataProvider receivingCollectorsDataProvider = new CollectorDataProvider();

    /**
     * Getter for property receivingCollectorsDataProvider.
     * @return Value of property receivingCollectorsDataProvider.
     */
    public ipfixconfig.CollectorDataProvider getReceivingCollectorsDataProvider() {

        return this.receivingCollectorsDataProvider;
    }

    /**
     * Setter for property receivingCollectorsDataProvider.
     * @param receivingCollectorsDataProvider New value of property receivingCollectorsDataProvider.
     */
    public void setReceivingCollectorsDataProvider(ipfixconfig.CollectorDataProvider receivingCollectorsDataProvider) {

        this.receivingCollectorsDataProvider = receivingCollectorsDataProvider;
    }

    /**
     * Holds value of property newExportingProcess.
     */
    private boolean newExportingProcess;

    /**
     * Getter for property newExportingProcess.
     * @return Value of property newExportingProcess.
     */
    public boolean isNewExportingProcess() {

        return this.newExportingProcess;
    }

    /**
     * Setter for property newExportingProcess.
     * @param newExportingProcess New value of property newExportingProcess.
     */
    public void setNewExportingProcess(boolean newExportingProcess) {

        this.newExportingProcess = newExportingProcess;
    }

    /**
     * Holds value of property nextProcessOptions.
     */
    private com.sun.rave.web.ui.model.Option[] nextProcessOptions;

    /**
     * Getter for property nextProcessOptions.
     * @return Value of property nextProcessOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getNextProcessOptions() {

        return this.nextProcessOptions;
    }

    /**
     * Setter for property nextProcessOptions.
     * @param nextProcessOptions New value of property nextProcessOptions.
     */
    public void setNextProcessOptions(com.sun.rave.web.ui.model.Option[] nextProcessOptions) {

        this.nextProcessOptions = nextProcessOptions;
    }

    /**
     * Holds value of property selectedNext.
     */
    private String[] selectedNext = new String[]{};

    /**
     * Getter for property selectedNext.
     * @return Value of property selectedNext.
     */
    public String[] getSelectedNext() {

        return this.selectedNext;
    }

    /**
     * Setter for property selectedNext.
     * @param selectedNext New value of property selectedNext.
     */
    public void setSelectedNext(String[] selectedNext) {

        this.selectedNext = selectedNext;
    }

    /**
     * Holds value of property meteringProcessId.
     */
    private Integer meteringProcessId = new Integer(0);

    /**
     * Getter for property meteringProcessId.
     * @return Value of property meteringProcessId.
     */
    public Integer getMeteringProcessId() {

        return this.meteringProcessId;
    }

    /**
     * Setter for property meteringProcessId.
     * @param meteringProcessId New value of property meteringProcessId.
     */
    public void setMeteringProcessId(Integer meteringProcessId) {

        this.meteringProcessId = meteringProcessId;
    }
    
    /**
     * Holds value of property selectedObservationPoints.
     */
    private java.util.Set selectedObservationPoints = new HashSet();

    /**
     * Getter for property selectedObservationPoints.
     * @return Value of property selectedObservationPoints.
     */
    public java.util.Set getSelectedObservationPoints() {

        return this.selectedObservationPoints;
    }

    /**
     * Setter for property selectedObservationPoints.
     * @param selectedObservationPoints New value of property selectedObservationPoints.
     */
    public void setSelectedObservationPoints(java.util.Set selectedObservationPoints) {

        this.selectedObservationPoints = selectedObservationPoints;
    }
    private boolean selectedObservationPoint;
    /**
     * Getter for property selectedObservationPoint.
     * @return Value of property selectedObservationPoint.
     */
    public boolean isSelectedObservationPoint() {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData == null) return false;
            RowKey rowKey= rowData.getTableRow();
            if(selectedObservationPoints.contains(rowKey)) return true;
            else return false;
    }

    /**
     * Setter for property selectedObservationPoint.
     * @param selectedObservationPoint New value of property selectedObservationPoint.
     */
    public void setSelectedObservationPoint(boolean selectedObservationPoint) {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData != null){
        RowKey rowKey= rowData.getTableRow();
        if(selectedObservationPoint){
            this.selectedObservationPoints.add(rowKey);
        }
        else {
            this.selectedObservationPoints.remove(rowKey);
        }
        }
    }

    /**
     * Holds value of property originalFileName.
     */
    private String originalFileName;

    /**
     * Getter for property originalFileName.
     * @return Value of property originalFileName.
     */
    public String getOriginalFileName() {

        return this.originalFileName;
    }

    /**
     * Setter for property originalFileName.
     * @param originalFileName New value of property originalFileName.
     */
    public void setOriginalFileName(String originalFileName) {

        this.originalFileName = originalFileName;
    }

    /**
     * Holds value of property originalConfigName.
     */
    private String originalConfigName;

    /**
     * Getter for property originalConfigName.
     * @return Value of property originalConfigName.
     */
    public String getOriginalConfigName() {

        return this.originalConfigName;
    }

    /**
     * Setter for property originalConfigName.
     * @param originalConfigName New value of property originalConfigName.
     */
    public void setOriginalConfigName(String originalConfigName) {

        this.originalConfigName = originalConfigName;
    }

    /**
     * Holds value of property selectedConfig.
     */
    private com.sun.data.provider.RowKey selectedConfig;

    /**
     * Getter for property selectedConfig.
     * @return Value of property selectedConfig.
     */
    public com.sun.data.provider.RowKey getSelectedConfig() {

        return this.selectedConfig;
    }

    /**
     * Setter for property selectedConfig.
     * @param selectedConfig New value of property selectedConfig.
     */
    public void setSelectedConfig(com.sun.data.provider.RowKey selectedConfig) {

        this.selectedConfig = selectedConfig;
    }
    
    /**
     * Holds value of property mappingList.
     */
    private  ipfixconfig.RoleMappingDataProvider mappingList = new RoleMappingDataProvider();

    /**
     * Getter for property mappingList.
     * @return Value of property mappingList.
     */
    public  ipfixconfig.RoleMappingDataProvider getMappingList() {

        return this.mappingList;
    }

    /**
     * Setter for property mappingList.
     * @param mappingList New value of property mappingList.
     */
    public void setMappingList(ipfixconfig.RoleMappingDataProvider mappingList) {

        this.mappingList = mappingList;
    }

    /**
     * Holds value of property newRole.
     */
    private boolean newRole;

    /**
     * Getter for property newRole.
     * @return Value of property newRole.
     */
    public boolean isNewRole() {

        return this.newRole;
    }

    /**
     * Setter for property newRole.
     * @param newRole New value of property newRole.
     */
    public void setNewRole(boolean newRole) {

        this.newRole = newRole;
    }

    /**
     * Holds value of property orginalRoleName.
     */
    private String originalRoleName;

    /**
     * Getter for property orginalRoleName.
     * @return Value of property orginalRoleName.
     */
    public String getOriginalRoleName() {

        return this.originalRoleName;
    }

    /**
     * Setter for property orginalRoleName.
     * @param originalRoleName New value of property orginalRoleName.
     */
    public void setOriginalRoleName(String originalRoleName) {

        this.originalRoleName = originalRoleName;
    }

    /**
     * Holds value of property originalDeviceName.
     */
    private String originalDeviceName;

    /**
     * Getter for property originalDeviceName.
     * @return Value of property originalDeviceName.
     */
    public String getOriginalDeviceName() {

        return this.originalDeviceName;
    }

    /**
     * Setter for property originalDeviceName.
     * @param originalDeviceName New value of property originalDeviceName.
     */
    public void setOriginalDeviceName(String originalDeviceName) {

        this.originalDeviceName = originalDeviceName;
    }
   
    /**
     * Holds value of property originalScenarioName.
     */
    private String originalScenarioName;

    /**
     * Getter for property originalScenarioName.
     * @return Value of property originalScenarioName.
     */
    public String getOriginalScenarioName() {

        return this.originalScenarioName;
    }

    /**
     * Setter for property originalScenarioName.
     * @param originalScenarioName New value of property originalScenarioName.
     */
    public void setOriginalScenarioName(String originalScenarioName) {

        this.originalScenarioName = originalScenarioName;
    }

    /**
     * Holds value of property scenarioDeviceDataProvider.
     */
    private ipfixconfig.DeviceDataProvider scenarioDeviceDataProvider = new DeviceDataProvider();

    /**
     * Getter for property scenarioDeviceDataProvider.
     * @return Value of property scenarioDeviceDataProvider.
     */
    public ipfixconfig.DeviceDataProvider getScenarioDeviceDataProvider() {

        return this.scenarioDeviceDataProvider;
    }

    /**
     * Setter for property scenarioDeviceDataProvider.
     * @param scenarioDeviceDataProvider New value of property scenarioDeviceDataProvider.
     */
    public void setScenarioDeviceDataProvider(ipfixconfig.DeviceDataProvider scenarioDeviceDataProvider) {

        this.scenarioDeviceDataProvider = scenarioDeviceDataProvider;
    }

    /**
     * Holds value of property setScenarioPageAlert.
     */
    private com.sun.rave.web.ui.component.PageAlert setScenarioPageAlert;

    /**
     * Getter for property setScenarioPageAlert.
     * @return Value of property setScenarioPageAlert.
     */
    public com.sun.rave.web.ui.component.PageAlert getSetScenarioPageAlert() {

        return this.setScenarioPageAlert;
    }

    /**
     * Setter for property setScenarioPageAlert.
     * @param setScenarioPageAlert New value of property setScenarioPageAlert.
     */
    public void setSetScenarioPageAlert(com.sun.rave.web.ui.component.PageAlert setScenarioPageAlert) {

        this.setScenarioPageAlert = setScenarioPageAlert;
    }
    
}
