/*
 * editRole.java
 *
 * 
 * Copyright root
 */
package ipfixconfig;

import com.sun.data.provider.DataProviderException;
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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.Listbox;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.model.Option;
import java.util.List;
import java.util.Map;
import com.sun.rave.web.ui.component.MessageGroup;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editRole extends AbstractPageBean {
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

    private TextField roleName = new TextField();

    public TextField getRoleName() {
        return roleName;
    }

    public void setRoleName(TextField tf) {
        this.roleName = tf;
    }

    private TextArea descript_textarea = new TextArea();

    public TextArea getDescript_textarea() {
        return descript_textarea;
    }

    public void setDescript_textarea(TextArea ta) {
        this.descript_textarea = ta;
    }

    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }

    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
    }

    private Listbox devices = new Listbox();

    public Listbox getDevices() {
        return devices;
    }

    public void setDevices(Listbox l) {
        this.devices = l;
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

    private Listbox configs = new Listbox();

    public Listbox getConfigs() {
        return configs;
    }

    public void setConfigs(Listbox l) {
        this.configs = l;
    }

    private Button save_mapping_button = new Button();

    public Button getSave_mapping_button() {
        return save_mapping_button;
    }

    public void setSave_mapping_button(Button b) {
        this.save_mapping_button = b;
    }

    private Table mappingtable = new Table();

    public Table getMappingtable() {
        return mappingtable;
    }

    public void setMappingtable(Table t) {
        this.mappingtable = t;
    }

    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
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

    private Button save_role = new Button();

    public Button getSave_role() {
        return save_role;
    }

    public void setSave_role(Button b) {
        this.save_role = b;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private Button removeMappingButton = new Button();

    public Button getRemoveMappingButton() {
        return removeMappingButton;
    }

    public void setRemoveMappingButton(Button b) {
        this.removeMappingButton = b;
    }

    private Button discard = new Button();

    public Button getDiscard() {
        return discard;
    }

    public void setDiscard(Button b) {
        this.discard = b;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editRole() {
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
        
        if(!getSessionBean1().isNewRole()){
            RowKey selectedRole = getSessionBean1().getSelectedRowKey();
            this.role = (Role) getApplicationBean1().getAppRoleDataProvider().getObject(selectedRole);
        }
        
        List deviceList = getApplicationBean1().getAppDeviceDataProvider().getList();
        List configList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        int deviceCount = deviceList.size();
        
        this.deviceOptions = new Option[deviceCount];
        for(int i = 0; i < deviceCount; i = i + 1){
            Device currentDevice = (Device) deviceList.get(i);
            this.deviceOptions[i] = new Option(currentDevice.getName(), currentDevice.getName(), currentDevice.getDescription());
        }
        int configCount = configList.size();
        
        this.configOptions = new Option[configCount];
        for(int i = 0; i < configCount; i++){
            ConfigInfo currentconfig = (ConfigInfo) configList.get(i);
            this.configOptions[i] = new Option(currentconfig.getName(), currentconfig.getName(), currentconfig.getDescription());
        }
        
        /*
        else{
            ArrayList mapList = new ArrayList();
            MappingBean mBean = new MappingBean("", "");
            mapList.add(mBean);
            mappingList.setList(mapList);
        }*/
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("editRole Initialization Failure", e);
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
     * Holds value of property role.
     */
    private ipfixconfig.Role role = new Role();

    /**
     * Getter for property role.
     * @return Value of property role.
     */
    public ipfixconfig.Role getRole() {

        return this.role;
    }

    /**
     * Setter for property role.
     * @param role New value of property role.
     */
    public void setRole(ipfixconfig.Role role) {

        this.role = role;
    }
    
    /**
     * Holds value of property deviceOptions.
     */
    private com.sun.rave.web.ui.model.Option[] deviceOptions;

    /**
     * Getter for property deviceOptions.
     * @return Value of property deviceOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getDeviceOptions() {

        return this.deviceOptions;
    }

    /**
     * Setter for property deviceOptions.
     * @param deviceOptions New value of property deviceOptions.
     */
    public void setDeviceOptions(com.sun.rave.web.ui.model.Option[] deviceOptions) {

        this.deviceOptions = deviceOptions;
    }

    /**
     * Holds value of property configOptions.
     */
    private com.sun.rave.web.ui.model.Option[] configOptions;

    /**
     * Getter for property configOptions.
     * @return Value of property configOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getConfigOptions() {

        return this.configOptions;
    }

    /**
     * Setter for property configOptions.
     * @param configOptions New value of property configOptions.
     */
    public void setConfigOptions(com.sun.rave.web.ui.model.Option[] configOptions) {

        this.configOptions = configOptions;
    }
    /*
     * Remove Device from Role Mapping.
     * Checks if a Device is set on this role and changes role to "None".
     * As there is no mapping anymore.
     * Saves the changed Devicelist directly to file.
     *
     */
    public String remove_action(){
        RowKey selectedRow = this.getTableRowGroup1().getRowKey();
        MappingBean mappingRemoved = (MappingBean) getSessionBean1().getMappingList().getObject(selectedRow);
        try{
        getSessionBean1().getMappingList().removeObject(selectedRow);
        getSessionBean1().getMappingList().commitChanges();
        }
        catch(Exception ex){
                        error("Error changing Mapping!" + ex.getMessage());
                        log("Error changing Mapping!", ex);
        }
        /*
         * Entfernen der Role vom Gerät, dass sie gesetzt hat.
         */
        String removedDevice = mappingRemoved.getDevice();
        List deviceList = getApplicationBean1().getAppDeviceDataProvider().getList();
        
        for(int i = 0;i < deviceList.size(); i++){
            Device currentDevice = (Device) deviceList.get(i);
            String currentName = currentDevice.getName();
            if(removedDevice.equals(currentName)){
                if(this.role.getName().equals(currentDevice.getRole())){
                    currentDevice.setRole("None");
                    deviceList.set(i, currentDevice);
                    getApplicationBean1().getAppDeviceDataProvider().setList(deviceList);
                
                    try{
                        getApplicationBean1().getAppDeviceStorage().saveList(deviceList);
                    } catch(Exception ex){
                        error("Writing DeviceStorage!" + ex.getMessage());
                        log("Error writing configInfo file!", ex);
                    }
                    break;
                }
            }
            
        }
        /*
        RowKey selectedRole = getSessionBean1().getSelectedRowKey();
        getApplicationBean1().getAppRoleDataProvider().setObject(selectedRole, this.role);
        getApplicationBean1().getAppRoleDataProvider().commitChanges();
        */
        return null;
    }
        
    public String save_mapping_button_action() {
        String newDevice = this.getSelectedDevice();
        String newConfig = this.getSelectedConfig();
        
        if(newDevice == ""){
            error("No Device selected!");
            return null;
        }
        if(newConfig == ""){
            error("No Configuration selected");
            return null;
        }
        Map roleMap = getSessionBean1().getMappingList().getMap();
        if(roleMap.containsKey(newDevice)){
            error("Device is already mapped! Devices can only be mapped to one configuration per role.");
            return null;
        }    
        else{
            try{
                getSessionBean1().getMappingList().addObject(new MappingBean(newDevice, newConfig));
                getSessionBean1().getMappingList().commitChanges();
            } catch(DataProviderException ex){
                error("Problem with RoleMappingDataProvider!");
                log("Problem with RoleMappingDataProvider!" + ex.getMessage());
            }
        }
        
        
        return null;
    }

    /**
     * Holds value of property selectedDevice.
     */
    private String selectedDevice = "";

    /**
     * Getter for property selectedDevice.
     * @return Value of property selectedDevice.
     */
    public String getSelectedDevice() {

        return this.selectedDevice;
    }

    /**
     * Setter for property selectedDevice.
     * @param selectedDevice New value of property selectedDevice.
     */
    public void setSelectedDevice(String selectedDevice) {

        this.selectedDevice = selectedDevice;
    }

    /**
     * Holds value of property selectedConfig.
     */
    private String selectedConfig = "";

    /**
     * Getter for property selectedConfig.
     * @return Value of property selectedConfig.
     */
    public String getSelectedConfig() {

        return this.selectedConfig;
    }

    /**
     * Setter for property selectedConfig.
     * @param selectedConfig New value of property selectedConfig.
     */
    public void setSelectedConfig(String selectedConfig) {

        this.selectedConfig = selectedConfig;
    }


    public String save_role_action() {
        HashMap newMapping = (HashMap) getSessionBean1().getMappingList().getMap();
        this.role.setMapping(newMapping);
        if(getSessionBean1().isNewRole()){
            getApplicationBean1().getAppRoleDataProvider().addObject(this.role);
            getApplicationBean1().getAppRoleDataProvider().commitChanges();
        }
        else{
            RowKey selectedRole = getSessionBean1().getSelectedRowKey();
            getApplicationBean1().getAppRoleDataProvider().setObject(selectedRole, this.role);
            getApplicationBean1().getAppRoleDataProvider().commitChanges();
        }
        
        List roleList = getApplicationBean1().getAppRoleDataProvider().getList();
        
        try{
            getApplicationBean1().getAppRoleStorage().saveList(roleList);
        }
        catch(Exception ex){
            error("Error writing to Storagefile!" + ex.getMessage());
            log("Error writing to Storagefile!", ex);
        }
        return "saveRole";
    }
    
    public void roleName_validate(FacesContext context, UIComponent component, Object value) {
        String newRoleName = String.valueOf(value);
        if(!newRoleName.equals(getSessionBean1().getOriginalRoleName())){
        List roleList = getApplicationBean1().getAppRoleDataProvider().getList();
        Iterator listIterator = roleList.iterator();
        ArrayList nameList = new ArrayList(roleList.size());
        while(listIterator.hasNext()){
        Role currentRole = (Role) listIterator.next();
        nameList.add(currentRole.getName());
        }
        if(nameList.contains(newRoleName)) throw new ValidatorException(new FacesMessage
		("Rolename is already used! Please choose a different name."));
        }
    }
}

