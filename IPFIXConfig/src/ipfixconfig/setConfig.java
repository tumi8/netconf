/*
 * setConfig.java
 *
 * 
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
import com.sun.rave.web.ui.component.MessageGroup;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Button;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import netconf.NcConnector;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.Alert;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class setConfig extends AbstractPageBean {
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

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }

    private HtmlPanelGrid devicePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getDevicePanel() {
        return devicePanel;
    }

    public void setDevicePanel(HtmlPanelGrid hpg) {
        this.devicePanel = hpg;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private StaticText deviceName = new StaticText();

    public StaticText getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(StaticText st) {
        this.deviceName = st;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    private StaticText deviceType = new StaticText();

    public StaticText getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(StaticText st) {
        this.deviceType = st;
    }

    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }

    private StaticText deviceDescript = new StaticText();

    public StaticText getDeviceDescript() {
        return deviceDescript;
    }

    public void setDeviceDescript(StaticText st) {
        this.deviceDescript = st;
    }

    private HtmlPanelGrid connectionPanel = new HtmlPanelGrid();

    public HtmlPanelGrid getConnectionPanel() {
        return connectionPanel;
    }

    public void setConnectionPanel(HtmlPanelGrid hpg) {
        this.connectionPanel = hpg;
    }

    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private StaticText hostIP = new StaticText();

    public StaticText getHostIP() {
        return hostIP;
    }

    public void setHostIP(StaticText st) {
        this.hostIP = st;
    }

    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }

    private StaticText user = new StaticText();

    public StaticText getUser() {
        return user;
    }

    public void setUser(StaticText st) {
        this.user = st;
    }

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private StaticText netconfRole = new StaticText();

    public StaticText getNetconfRole() {
        return netconfRole;
    }

    public void setNetconfRole(StaticText st) {
        this.netconfRole = st;
    }

    private HtmlPanelGrid rolePanel = new HtmlPanelGrid();

    public HtmlPanelGrid getRolePanel() {
        return rolePanel;
    }

    public void setRolePanel(HtmlPanelGrid hpg) {
        this.rolePanel = hpg;
    }

    private Label label8 = new Label();

    public Label getLabel8() {
        return label8;
    }

    public void setLabel8(Label l) {
        this.label8 = l;
    }

    private StaticText role = new StaticText();

    public StaticText getRole() {
        return role;
    }

    public void setRole(StaticText st) {
        this.role = st;
    }

    private Label label9 = new Label();

    public Label getLabel9() {
        return label9;
    }

    public void setLabel9(Label l) {
        this.label9 = l;
    }

    private StaticText configuration = new StaticText();

    public StaticText getConfiguration() {
        return configuration;
    }

    public void setConfiguration(StaticText st) {
        this.configuration = st;
    }

    private Button set_config_button = new Button();

    public Button getSet_config_button() {
        return set_config_button;
    }

    public void setSet_config_button(Button b) {
        this.set_config_button = b;
    }

    private Button return_button = new Button();

    public Button getReturn_button() {
        return return_button;
    }

    public void setReturn_button(Button b) {
        this.return_button = b;
    }
    
    // </editor-fold>

    /* Connection für die Bean */
    NcConnector connection;

    private Label label10 = new Label();

    public Label getLabel10() {
        return label10;
    }

    public void setLabel10(Label l) {
        this.label10 = l;
    }

    private StaticText port = new StaticText();

    public StaticText getPort() {
        return port;
    }

    public void setPort(StaticText st) {
        this.port = st;
    }

    private Label label11 = new Label();

    public Label getLabel11() {
        return label11;
    }

    public void setLabel11(Label l) {
        this.label11 = l;
    }

    private StaticText config_filename = new StaticText();

    public StaticText getConfig_filename() {
        return config_filename;
    }

    public void setConfig_filename(StaticText st) {
        this.config_filename = st;
    }

    private Button check_connection_button = new Button();

    public Button getCheck_connection_button() {
        return check_connection_button;
    }

    public void setCheck_connection_button(Button b) {
        this.check_connection_button = b;
    }

    private Label label12 = new Label();

    public Label getLabel12() {
        return label12;
    }

    public void setLabel12(Label l) {
        this.label12 = l;
    }

    private TextArea request_area = new TextArea();

    public TextArea getRequest_area() {
        return request_area;
    }

    public void setRequest_area(TextArea ta) {
        this.request_area = ta;
    }

    private Alert alert1 = new Alert();

    public Alert getAlert1() {
        return alert1;
    }

    public void setAlert1(Alert a) {
        this.alert1 = a;
    }

    private HtmlPanelGrid netconf_panel = new HtmlPanelGrid();

    public HtmlPanelGrid getNetconf_panel() {
        return netconf_panel;
    }

    public void setNetconf_panel(HtmlPanelGrid hpg) {
        this.netconf_panel = hpg;
    }

    private Label label13 = new Label();

    public Label getLabel13() {
        return label13;
    }

    public void setLabel13(Label l) {
        this.label13 = l;
    }

    private StaticText namespace_field = new StaticText();

    public StaticText getNamespace_field() {
        return namespace_field;
    }

    public void setNamespace_field(StaticText st) {
        this.namespace_field = st;
    }

    private Label label14 = new Label();

    public Label getLabel14() {
        return label14;
    }

    public void setLabel14(Label l) {
        this.label14 = l;
    }

    private StaticText netconfpath_field = new StaticText();

    public StaticText getNetconfpath_field() {
        return netconfpath_field;
    }

    public void setNetconfpath_field(StaticText st) {
        this.netconfpath_field = st;
    }

    private Button getConfig = new Button();

    public Button getGetConfig() {
        return getConfig;
    }

    public void setGetConfig(Button b) {
        this.getConfig = b;
    }

    private Button validateConfig = new Button();

    public Button getValidateConfig() {
        return validateConfig;
    }

    public void setValidateConfig(Button b) {
        this.validateConfig = b;
    }

    private Button restartDevice = new Button();

    public Button getRestartDevice() {
        return restartDevice;
    }

    public void setRestartDevice(Button b) {
        this.restartDevice = b;
    }
    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public setConfig() {
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
        RowKey selectedDevice = getSessionBean1().getSelectedRowKey();
        this.deviceToSet = (Device) getApplicationBean1().getAppDeviceDataProvider().getObject(selectedDevice);
        
        
        List roleList = getApplicationBean1().getAppRoleDataProvider().getList();
        
        for(int i=0; i< roleList.size(); i++){
                Role currentRole = (Role) roleList.get(i);
                
                if(currentRole.getName().equals(this.deviceToSet.getRole()) ){
                    this.deviceRole = currentRole;
                }
                
        }
        HashMap roleMapping = this.deviceRole.getMapping();
        String configName = (String) roleMapping.get(this.deviceToSet.getName());
        List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        for(int i=0; i < configInfoList.size(); i++){
            ConfigInfo currentConfig = (ConfigInfo) configInfoList.get(i);
            if(currentConfig.getName().equals(configName)){
                this.deviceConfig = currentConfig;
            }
        }
        if(this.deviceConfig.getFileName() == null){
            this.alert1.setType("error");
            this.alert1.setSummary("No Role is set for this device!");
            this.alert1.setDetail("Please check the device and set a role for it.");
        }
        String appPath = getApplicationBean1().getAppPath();
        String fileName = appPath + this.deviceConfig.getFileName();
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
        }
        catch(Exception e){
            error("Exception while reading the Configuration file: " + e.getMessage());
        }
        
        String configXML = configBuffer.toString();
        /*
         * Der XML Prolog muss entfernt werden, da der copy-config
         * Befehl mit Inline Daten diesen nicht verarbeiten kann.
         */
        if (configXML.startsWith("<?xml ")) {
            configXML = configXML.substring(configXML.indexOf("?>") + 2);
        }
        String namespace = this.deviceToSet.getNetconfNS();
        StringBuffer ensuiteFramingStart = new StringBuffer();
        StringBuffer ensuiteFramingEnd = new StringBuffer();
        String path = this.deviceToSet.getNetconfPath();
        String[] pathParts = path.split("/");
        for(int i = 1; i < pathParts.length; i++){
            ensuiteFramingStart.append("<" + pathParts[i] + " xmlns=\"" + namespace +"\" > \n");
            
        }
        
        for(int i = pathParts.length - 1; i > 0; i--){
            ensuiteFramingEnd.append("</" + pathParts[i] + "> \n");
        }
        /*
        String open1 = "<netconf xmlns=\"urn:loria:madynes:ensuite:yencap:1.0\">";
        String close1 = "</netconf>";
        
        String open2 = "<monitoring xmlns=\"urn:loria:madynes:ensuite:yencap:1.0\">";
        String close2 = "</monitoring>";
        */
         this.copyConfigRequest = "<copy-config>\n <target>\n <startup/>\n </target>\n <source>\n <config>\n" +  
                 ensuiteFramingStart.toString() + configXML + ensuiteFramingEnd.toString()+ "\n </config>\n </source>\n </copy-config>";
        
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("setConfig Initialization Failure", e);
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

    /**
     * Holds value of property deviceToSet.
     */
    private ipfixconfig.Device deviceToSet = new Device();

    /**
     * Getter for property deviceToSet.
     * @return Value of property deviceToSet.
     */
    public ipfixconfig.Device getDeviceToSet() {

        return this.deviceToSet;
    }

    /**
     * Setter for property deviceToSet.
     * @param deviceToSet New value of property deviceToSet.
     */
    public void setDeviceToSet(ipfixconfig.Device deviceToSet) {

        this.deviceToSet = deviceToSet;
    }

    /**
     * Holds value of property deviceRole.
     */
    private ipfixconfig.Role deviceRole = new Role();

    /**
     * Getter for property deviceRole.
     * @return Value of property deviceRole.
     */
    public ipfixconfig.Role getDeviceRole() {

        return this.deviceRole;
    }

    /**
     * Setter for property deviceRole.
     * @param deviceRole New value of property deviceRole.
     */
    public void setDeviceRole(ipfixconfig.Role deviceRole) {

        this.deviceRole = deviceRole;
    }

    /**
     * Holds value of property deviceConfig.
     */
    private ipfixconfig.ConfigInfo deviceConfig = new ConfigInfo();

    /**
     * Getter for property deviceConfig.
     * @return Value of property deviceConfig.
     */
    public ipfixconfig.ConfigInfo getDeviceConfig() {

        return this.deviceConfig;
    }

    /**
     * Setter for property deviceConfig.
     * @param deviceConfig New value of property deviceConfig.
     */
    public void setDeviceConfig(ipfixconfig.ConfigInfo deviceConfig) {

        this.deviceConfig = deviceConfig;
    }
    
    /**
     * Parses the hello message (or the error message returned)
     * @param messageString The message to parse
     */
    private String parseHello(String messageString){
        String parsedMessage = "";
        
        
        
        return parsedMessage;
    }


    public String return_button_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        
        return "return";
    }
    /*
     * Send die Konfiguration an den Agent.
     *
     */

    public String set_config_button_action() {
        
        String host = this.deviceToSet.getHostIP();
        int port = this.deviceToSet.getPort().intValue();
        String login = this.deviceToSet.getUser();
        String password = this.deviceToSet.getPassword();
        NcConnector NCconnect = new NcConnector(host, port, login, password, false);
        List capabilities = NCconnect.connect();
        
        if(capabilities.size() != 0){
            String err = (String) capabilities.get(0);
            if(err.substring(0, 5).equals("Error")){
                this.alert1.setType("error");
                this.alert1.setSummary("Error connecting to Agent!");
                this.alert1.setDetail(err);
                return null;
            }
        }
               
        /*
         * Check ob die Netconf Role gesetzt werden muss. ENSuite nutzt RBAC um 
         * die Berechtigungen zu verwalten. Dies wird auch als Capability angegeben.
         * Wenn die RBAC Capability angegeben ist, muss die Netconf-Role aktiviert werden.
         */
        if(capabilities.contains("urn:madynes:params:xml:ns:netconf:capability:rbac:1.0")){
            String message = "<rbac> <activate> <roles> <role>" + this.deviceToSet.getNetconfRole() +
                    "</role> </roles> </activate> </rbac>";
            
            String reply = NCconnect.sendRPC(message);
            if(!reply.equals("ok")){
                this.alert1.setType("error");
                this.alert1.setSummary("Could not activate Netconf Role on Agent!");
                this.alert1.setDetail(reply);
                NCconnect.disconnect();
                return null;
            } 
        }
                
        String reply = NCconnect.sendRPC(this.copyConfigRequest);
        if(!reply.equals("ok")){
            this.alert1.setType("error");
            this.alert1.setSummary("Error sending configuration!");
            this.alert1.setDetail(reply);           
        }
        else {
            this.alert1.setType("success");
            this.alert1.setSummary("Configuration was sent to agent!");
            this.alert1.setDetail(reply);          
        }
        
        reply = NCconnect.disconnect();
        if(reply.substring(0, 5).equals("Error")){
            this.alert1.setType("error");
            this.alert1.setSummary("Error disconnecting!");
            this.alert1.setDetail(reply);    
        }
        
        
        return null;
    }


    public String check_connection_button_action() {
        String host = this.deviceToSet.getHostIP();
        int port = this.deviceToSet.getPort().intValue();
        String login = this.deviceToSet.getUser();
        String password = this.deviceToSet.getPassword();
        NcConnector NCconnect = new NcConnector(host, port, login, password, false);
        List capabilities = NCconnect.connect();
        
        if(capabilities.size() != 0){
            String err = (String) capabilities.get(0);
            if(err.substring(0, 5).equals("Error")){
                this.alert1.setType("error");
                this.alert1.setSummary("Connection to Device failed!");
                this.alert1.setDetail(err);
                
            }
            else{
                this.alert1.setType("success");
                this.alert1.setSummary("Connection established!");
                StringBuffer capaBuffer = new StringBuffer();                
                for(int i = 0; i < capabilities.size(); i++){
                String capability = (String) capabilities.get(i);
                capaBuffer.append(capability + " ");
                this.alert1.setDetail("Capabilities:" + capaBuffer);
                }
            }
        }
        NCconnect.disconnect();
        
        return null;
    }

    /**
     * Holds value of property copyConfigRequest.
     */
    private String copyConfigRequest;

    /**
     * Getter for property copyConfigRequest.
     * @return Value of property copyConfigRequest.
     */
    public String getCopyConfigRequest() {

        return this.copyConfigRequest;
    }
    
    public void setCopyConfigRequest(String newCopyConfigRequest){
        this.copyConfigRequest = newCopyConfigRequest;
    }


    public String getConfig_action() {
        String host = this.deviceToSet.getHostIP();
        int port = this.deviceToSet.getPort().intValue();
        String login = this.deviceToSet.getUser();
        String password = this.deviceToSet.getPassword();
        NcConnector NCconnect = new NcConnector(host, port, login, password, false);
        List capabilities = NCconnect.connect();
        
        if(capabilities.size() != 0){
            String err = (String) capabilities.get(0);
            if(err.substring(0, 5).equals("Error")){
                this.alert1.setType("error");
                this.alert1.setSummary("Connection to Device failed!");
                this.alert1.setDetail(err);
                return null;
            }
        /*
         * Check ob die Netconf Role gesetzt werden muss. ENSuite nutzt RBAC um 
         * die Berechtigungen zu verwalten. Dies wird auch als Capability angegeben.
         * Wenn die RBAC Capability angegeben ist, muss die Netconf-Role aktiviert werden.
         */
        if(capabilities.contains("urn:madynes:params:xml:ns:netconf:capability:rbac:1.0")){
            String message = "<rbac> <activate> <roles> <role>" + this.deviceToSet.getNetconfRole() +
                    "</role> </roles> </activate> </rbac>";
            
            String reply = NCconnect.sendRPC(message);
            if(!reply.equals("ok")){
                this.alert1.setType("error");
                this.alert1.setSummary("Could not activate Netconf Role on Agent!");
                this.alert1.setDetail(reply);
                NCconnect.disconnect();
                return null;
            } 
        }
        
        String getConfigRequest = "<get-config> <source> <startup/> </source> </get-config>";
                
        String reply = NCconnect.sendRPC(getConfigRequest);
        if(reply.length() >= 5){
        if(!reply.substring(0, 5).equals("Error")){
            this.alert1.setType("success");
            this.alert1.setSummary("Get configuration success!");
            this.copyConfigRequest = reply;
            
        } else{
            this.alert1.setType("error");
            this.alert1.setSummary("Get configuration failed!");
            this.alert1.setDetail(reply);
        }
        }
        else{
           this.alert1.setType("error");
           this.alert1.setSummary("Get configuration failed!");
           this.alert1.setDetail(reply); 
        }
    }
    
    NCconnect.disconnect();
    
    return null;
        
       
    }


    public String validateConfig_action() {
        String host = this.deviceToSet.getHostIP();
        int port = this.deviceToSet.getPort().intValue();
        String login = this.deviceToSet.getUser();
        String password = this.deviceToSet.getPassword();
        NcConnector NCconnect = new NcConnector(host, port, login, password, false);
        List capabilities = NCconnect.connect();
        String validateConfigRequest = "<validate> <source> <startup/> </source> </validate>";
        
        if(capabilities.size() != 0){
            String err = (String) capabilities.get(0);
            if(err.substring(0, 5).equals("Error")){
                this.alert1.setType("error");
                this.alert1.setSummary("Connection to Device failed!");
                this.alert1.setDetail(err);
                return null;
            }
            else{
                String reply = NCconnect.sendRPC(validateConfigRequest);
                if(reply.equals("ok")){
                    this.alert1.setType("success");
                    this.alert1.setSummary("Configuration is valid!");                    
                }
                else{
                    this.alert1.setType("error");
                    this.alert1.setSummary("Validation of the configuration failed!");
                    this.alert1.setDetail(reply);                    
                }
                }
            }
        
        NCconnect.disconnect();
        
        return null;
        
        
    }


    public String restartDevice_action() {
        String host = this.deviceToSet.getHostIP();
        int port = this.deviceToSet.getPort().intValue();
        String login = this.deviceToSet.getUser();
        String password = this.deviceToSet.getPassword();
        NcConnector NCconnect = new NcConnector(host, port, login, password, false);
        List capabilities = NCconnect.connect();
        String validateConfigRequest = "<restart> <moduleName>VERMONT</moduleName> </restart>";
        
        if(capabilities.size() != 0){
            String err = (String) capabilities.get(0);
            if(err.substring(0, 5).equals("Error")){
                this.alert1.setType("error");
                this.alert1.setSummary("Connection to Device failed!");
                this.alert1.setDetail(err);
                return null;
            }
            else{
                String reply = NCconnect.sendRPC(validateConfigRequest);
                if(reply.equals("ok")){
                    this.alert1.setType("success");
                    this.alert1.setSummary("VERMONT restarted!");                    
                }
                else{
                    this.alert1.setType("error");
                    this.alert1.setSummary("Restart of VERMONT failed!");
                    this.alert1.setDetail(reply);
                }
                }
            }
        
        NCconnect.disconnect();
        
        return null;
    }
}

