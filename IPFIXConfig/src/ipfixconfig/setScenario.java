/*
 * setScenario.java
 * 
 * Copyright max
 */
package ipfixconfig;

import com.sun.data.provider.RowKey;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.Page;
import javax.faces.FacesException;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Alert;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import netconf.NcConnector;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class setScenario extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    
    private Page page1 = new Page();
    
    public Page getPage1() {
        return page1;
    }
    
    public void setPage1(Page p) {
        this.page1 = p;
    }
    
    private Html html1 = new Html();
    
    public Html getHtml1() {
        return html1;
    }
    
    public void setHtml1(Html h) {
        this.html1 = h;
    }
    
    private Head head1 = new Head();
    
    public Head getHead1() {
        return head1;
    }
    
    public void setHead1(Head h) {
        this.head1 = h;
    }
    
    private Link link1 = new Link();
    
    public Link getLink1() {
        return link1;
    }
    
    public void setLink1(Link l) {
        this.link1 = l;
    }
    
    private Body body1 = new Body();
    
    public Body getBody1() {
        return body1;
    }
    
    public void setBody1(Body b) {
        this.body1 = b;
    }
    
    private Form form1 = new Form();
    
    public Form getForm1() {
        return form1;
    }
    
    public void setForm1(Form f) {
        this.form1 = f;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private StaticText namefield = new StaticText();

    public StaticText getNamefield() {
        return namefield;
    }

    public void setNamefield(StaticText st) {
        this.namefield = st;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    private StaticText descriptionfield = new StaticText();

    public StaticText getDescriptionfield() {
        return descriptionfield;
    }

    public void setDescriptionfield(StaticText st) {
        this.descriptionfield = st;
    }

    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }

    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }

    private Button setScenarioButton = new Button();

    public Button getSetScenarioButton() {
        return setScenarioButton;
    }

    public void setSetScenarioButton(Button b) {
        this.setScenarioButton = b;
    }

    private Alert alert1 = new Alert();

    public Alert getAlert1() {
        return alert1;
    }

    public void setAlert1(Alert a) {
        this.alert1 = a;
    }

    private Button returnButton = new Button();

    public Button getReturnButton() {
        return returnButton;
    }

    public void setReturnButton(Button b) {
        this.returnButton = b;
    }

    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }

    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }

    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }

    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }

    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }

    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public setScenario() {
    }

    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
    }


    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1)getBean("SessionBean1");
    }


    /** 
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1)getBean("ApplicationBean1");
    }


    /** 
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        RowKey selectedScenario = getSessionBean1().getSelectedRowKey();
        this.scenario = (Scenario) getApplicationBean1().getAppScenarioDataProvider().getObject(selectedScenario);
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("setScenario Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

    }

    /** 
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    public void preprocess() {
    }

    /** 
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    public void prerender() {
    }

    /** 
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
     */
    public void destroy() {
    }
    
    /*
     * 
     *
     */

    public String setScenarioButton_action() {
        int errorCounter = 0;
        StringBuffer errorMsgs = new StringBuffer();
        
        List scenarioDevicesList = this.scenario.getDeviceList();
        List allDevicesList = getApplicationBean1().getAppDeviceDataProvider().getList();
        List devicesList = new ArrayList();
        
        for(int i = 0; i < scenarioDevicesList.size(); i++){
            Device deviceToSet = null;
            String deviceName = (String) scenarioDevicesList.get(i);
            Iterator devicesIterator = allDevicesList.iterator();
            
            while(devicesIterator.hasNext()){
                Device currentDevice = (Device) devicesIterator.next();
                if(currentDevice.getName().equals(deviceName)){
                    deviceToSet = currentDevice;
                    continue;
                }
            }
            if(deviceToSet == null){
                errorMsgs.append("Error " + deviceName + " does not exist in Devices. \n");
                errorCounter = errorCounter + 1;
                continue;
            }
            String host = deviceToSet.getHostIP();
            int port = deviceToSet.getPort().intValue();
            String login = deviceToSet.getUser();
            String password = deviceToSet.getPassword();
            NcConnector NCconnect = new NcConnector(host, port, login, password, false);
            List capabilities = NCconnect.connect();
            
            
            if(capabilities.size() == 0){
                errorMsgs.append("Error connecting to Agent " + deviceToSet.getName() + "\n");
                errorCounter = errorCounter + 1;
                deviceToSet.setStatus("Error");
                devicesList.add(deviceToSet);
                continue;
            }
            String err = (String) capabilities.get(0);
            if(err.substring(0, 5).equals("Error")){
                errorMsgs.append("Error connecting to Agent " + deviceToSet.getName() + " " + err + "\n");
                errorCounter = errorCounter + 1;
                deviceToSet.setStatus("Error");
                devicesList.add(deviceToSet);
                continue;
            }
            
         /*
          * Check ob die Netconf Role gesetzt werden muss. ENSuite nutzt RBAC um
          * die Berechtigungen zu verwalten. Dies wird auch als Capability angegeben.
          * Wenn die RBAC Capability angegeben ist, muss die Netconf Role aktiviert werden.
          */
            if(capabilities.contains("urn:madynes:params:xml:ns:netconf:capability:rbac:1.0")){
                String message = "<rbac> <activate> <roles> <role>" + deviceToSet.getNetconfRole() +
                        "</role> </roles> </activate> </rbac>";
                String reply = NCconnect.sendRPC(message);
                if(!reply.equals("ok")){
                    errorMsgs.append("Error sending RBAC role to Agent " + deviceToSet.getName() + "\n");
                    errorCounter = errorCounter + 1;
                    deviceToSet.setStatus("Error");
                    devicesList.add(deviceToSet);
                    continue;
                }
            }
            ConfigInfo deviceConfig = new ConfigInfo();
            Role deviceRole = new Role();
            List roleList = getApplicationBean1().getAppRoleDataProvider().getList();
            
            for(int j=0; j< roleList.size(); j++){
                Role currentRole = (Role) roleList.get(j);
                
                if(currentRole.getName().equals(deviceToSet.getRole()) ){
                    deviceRole = currentRole;
                    continue;
                }
                
            }
            HashMap roleMapping = deviceRole.getMapping();
            
            String configName = (String) roleMapping.get(deviceToSet.getName());
            List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
            for(int j=0; j < configInfoList.size(); j++){
                ConfigInfo currentConfig = (ConfigInfo) configInfoList.get(j);
                if(currentConfig.getName().equals(configName)){
                    deviceConfig = currentConfig;
                    continue;
                }
            }
            if(deviceConfig.getFileName() == null){
                errorMsgs.append("No role set for device: " + deviceToSet.getName() + "\n");
                errorCounter = errorCounter + 1;
                deviceToSet.setStatus("Error");
                devicesList.add(deviceToSet);
                continue;
            }
            String appPath = getApplicationBean1().getAppPath();
            String fileName = appPath + deviceConfig.getFileName();
            StringBuffer configBuffer = new StringBuffer();
            
            try{
                FileReader inputStream = new FileReader(fileName);
                
                boolean EOF = false;
                int readByte;
                
                while(!EOF){
                    readByte = inputStream.read();
                    if(readByte == -1) EOF = true;
                    else{
                        configBuffer.append( (char) readByte);
                    }
                }
                inputStream.close();
            } catch(Exception e){
                
                errorMsgs.append("Error reading configuration for device: " + deviceToSet.getName() + " " + e.getMessage() + "\n");
                errorCounter = errorCounter + 1;
                deviceToSet.setStatus("Error");
                devicesList.add(deviceToSet);
                continue;
            }
            
            String configXML = configBuffer.toString();
        /*
         * Der XML Prolog muss entfernt werden, da der copy-config
         * Befehl mit Inline Daten diesen nicht verarbeiten kann.
         */
            if (configXML.startsWith("<?xml ")) {
                configXML = configXML.substring(configXML.indexOf("?>") + 2);
            }
            String namespace = deviceToSet.getNetconfNS();
            StringBuffer ensuiteFramingStart = new StringBuffer();
            StringBuffer ensuiteFramingEnd = new StringBuffer();
            String path = deviceToSet.getNetconfPath();
            String[] pathParts = path.split("/");
            for(int j = 1; j < pathParts.length; j++){
                ensuiteFramingStart.append("<" + pathParts[j] + " xmlns=\"" + namespace +"\" > \n");
                
            }
            
            for(int j = pathParts.length - 1; j > 0; j--){
                ensuiteFramingEnd.append("</" + pathParts[j] + "> \n");
            }
            String copyConfigRequest = "<copy-config>\n <target>\n <running/>\n </target>\n <source>\n <config>\n" +
                    ensuiteFramingStart.toString() + configXML + ensuiteFramingEnd.toString()+ "\n </config>\n </source>\n </copy-config>";
            String reply = NCconnect.sendRPC(copyConfigRequest);
            
            if(!reply.equals("ok")){
                errorMsgs.append("Error sending Configuration to agent " + deviceToSet.getName() + "\n");
                errorCounter = errorCounter + 1;
                deviceToSet.setStatus("Error");
                devicesList.set(i,deviceToSet);
            } else {
                deviceToSet.setStatus("Set");
            }
            
            reply = NCconnect.disconnect();
            if(reply.substring(0, 5).equals("Error")){
                errorMsgs.append("Error disconnecting from agent " + deviceToSet.getName() + "\n");
                errorCounter = errorCounter + 1;
            }
            devicesList.add(deviceToSet);
        }
        getSessionBean1().getScenarioDeviceDataProvider().setList(devicesList);
        getSessionBean1().getScenarioDeviceDataProvider().commitChanges();
 
        if(errorCounter > 0){
            this.alert1.setType("error");
            this.alert1.setSummary(String.valueOf(errorCounter) + " agents had errors!");
            this.alert1.setDetail(errorMsgs.toString());
            
        } else{
            
            this.alert1.setType("success");
            this.alert1.setSummary("All agents set to new configurations!");
            this.alert1.setDetail(errorMsgs.toString());
            
        }
        return null;
    }

    /**
     * Holds value of property scenario.
     */
    private ipfixconfig.Scenario scenario = new Scenario();

    /**
     * Getter for property scenario.
     * @return Value of property scenario.
     */
    public ipfixconfig.Scenario getScenario() {

        return this.scenario;
    }

    /**
     * Setter for property scenario.
     * @param scenario New value of property scenario.
     */
    public void setScenario(ipfixconfig.Scenario scenario) {

        this.scenario = scenario;
    }

}

