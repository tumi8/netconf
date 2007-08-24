/*
 * Start.java
 *
 * 
 * Copyright root
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
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import java.util.List;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.Hyperlink;
import java.util.ArrayList;
import com.sun.rave.web.ui.component.Alert;
import java.util.Iterator;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Start extends AbstractPageBean {
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

    private Button new_config_button = new Button();

    public Button getNew_config_button() {
        return new_config_button;
    }

    public void setNew_config_button(Button b) {
        this.new_config_button = b;
    }

    private Button new_device_button = new Button();

    public Button getNew_device_button() {
        return new_device_button;
    }

    public void setNew_device_button(Button b) {
        this.new_device_button = b;
    }

    private Button new_scenario = new Button();

    public Button getNew_scenario() {
        return new_scenario;
    }

    public void setNew_scenario(Button b) {
        this.new_scenario = b;
    }

    private Button new_role = new Button();

    public Button getNew_role() {
        return new_role;
    }

    public void setNew_role(Button b) {
        this.new_role = b;
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

    private Table table2 = new Table();

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table t) {
        this.table2 = t;
    }

    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }

    private Table table3 = new Table();

    public Table getTable3() {
        return table3;
    }

    public void setTable3(Table t) {
        this.table3 = t;
    }

    private TableRowGroup tableRowGroup3 = new TableRowGroup();

    public TableRowGroup getTableRowGroup3() {
        return tableRowGroup3;
    }

    public void setTableRowGroup3(TableRowGroup trg) {
        this.tableRowGroup3 = trg;
    }

    private Table table4 = new Table();

    public Table getTable4() {
        return table4;
    }

    public void setTable4(Table t) {
        this.table4 = t;
    }

    private TableRowGroup tableRowGroup4 = new TableRowGroup();

    public TableRowGroup getTableRowGroup4() {
        return tableRowGroup4;
    }

    public void setTableRowGroup4(TableRowGroup trg) {
        this.tableRowGroup4 = trg;
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

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText st) {
        this.staticText13 = st;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private Button edit_config = new Button();

    public Button getEdit_config() {
        return edit_config;
    }

    public void setEdit_config(Button b) {
        this.edit_config = b;
    }

    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }

    private Button remove_config = new Button();

    public Button getRemove_config() {
        return remove_config;
    }

    public void setRemove_config(Button b) {
        this.remove_config = b;
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

    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tc) {
        this.tableColumn16 = tc;
    }

    private StaticText staticText14 = new StaticText();

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText st) {
        this.staticText14 = st;
    }

    private TableColumn tableColumn17 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tc) {
        this.tableColumn17 = tc;
    }

    private StaticText staticText15 = new StaticText();

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText st) {
        this.staticText15 = st;
    }

    private TableColumn tableColumn20 = new TableColumn();

    public TableColumn getTableColumn20() {
        return tableColumn20;
    }

    public void setTableColumn20(TableColumn tc) {
        this.tableColumn20 = tc;
    }

    private StaticText staticText18 = new StaticText();

    public StaticText getStaticText18() {
        return staticText18;
    }

    public void setStaticText18(StaticText st) {
        this.staticText18 = st;
    }

    private TableColumn tableColumn21 = new TableColumn();

    public TableColumn getTableColumn21() {
        return tableColumn21;
    }

    public void setTableColumn21(TableColumn tc) {
        this.tableColumn21 = tc;
    }

    private Button edit_device = new Button();

    public Button getEdit_device() {
        return edit_device;
    }

    public void setEdit_device(Button b) {
        this.edit_device = b;
    }

    private TableColumn tableColumn22 = new TableColumn();

    public TableColumn getTableColumn22() {
        return tableColumn22;
    }

    public void setTableColumn22(TableColumn tc) {
        this.tableColumn22 = tc;
    }

    private Button remove_device = new Button();

    public Button getRemove_device() {
        return remove_device;
    }

    public void setRemove_device(Button b) {
        this.remove_device = b;
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

    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }

    private Button edit_role = new Button();

    public Button getEdit_role() {
        return edit_role;
    }

    public void setEdit_role(Button b) {
        this.edit_role = b;
    }

    private TableColumn tableColumn23 = new TableColumn();

    public TableColumn getTableColumn23() {
        return tableColumn23;
    }

    public void setTableColumn23(TableColumn tc) {
        this.tableColumn23 = tc;
    }

    private Button remove_role = new Button();

    public Button getRemove_role() {
        return remove_role;
    }

    public void setRemove_role(Button b) {
        this.remove_role = b;
    }

    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }

    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
    }

    private HtmlPanelGrid gridPanel2 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel2() {
        return gridPanel2;
    }

    public void setGridPanel2(HtmlPanelGrid hpg) {
        this.gridPanel2 = hpg;
    }

    private HtmlPanelGrid gridPanel3 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel3() {
        return gridPanel3;
    }

    public void setGridPanel3(HtmlPanelGrid hpg) {
        this.gridPanel3 = hpg;
    }

    private HtmlPanelGrid gridPanel4 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel4() {
        return gridPanel4;
    }

    public void setGridPanel4(HtmlPanelGrid hpg) {
        this.gridPanel4 = hpg;
    }

    private HtmlPanelGrid gridPanel5 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel5() {
        return gridPanel5;
    }

    public void setGridPanel5(HtmlPanelGrid hpg) {
        this.gridPanel5 = hpg;
    }

    private Hyperlink show_config_link = new Hyperlink();

    public Hyperlink getShow_config_link() {
        return show_config_link;
    }

    public void setShow_config_link(Hyperlink h) {
        this.show_config_link = h;
    }

    private Button upload_config_button = new Button();

    public Button getUpload_config_button() {
        return upload_config_button;
    }

    public void setUpload_config_button(Button b) {
        this.upload_config_button = b;
    }

    private TableColumn tableColumn24 = new TableColumn();

    public TableColumn getTableColumn24() {
        return tableColumn24;
    }

    public void setTableColumn24(TableColumn tc) {
        this.tableColumn24 = tc;
    }

    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }

    private TableColumn tableColumn25 = new TableColumn();

    public TableColumn getTableColumn25() {
        return tableColumn25;
    }

    public void setTableColumn25(TableColumn tc) {
        this.tableColumn25 = tc;
    }

    private Button setConfigbutton = new Button();

    public Button getSetConfigbutton() {
        return setConfigbutton;
    }

    public void setSetConfigbutton(Button b) {
        this.setConfigbutton = b;
    }

    private TableColumn tableColumn10 = new TableColumn();

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tc) {
        this.tableColumn10 = tc;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }

    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private Button edit_scenario = new Button();

    public Button getEdit_scenario() {
        return edit_scenario;
    }

    public void setEdit_scenario(Button b) {
        this.edit_scenario = b;
    }

    private TableColumn tableColumn18 = new TableColumn();

    public TableColumn getTableColumn18() {
        return tableColumn18;
    }

    public void setTableColumn18(TableColumn tc) {
        this.tableColumn18 = tc;
    }

    private Button remove_scenario = new Button();

    public Button getRemove_scenario() {
        return remove_scenario;
    }

    public void setRemove_scenario(Button b) {
        this.remove_scenario = b;
    }

    private TableColumn tableColumn19 = new TableColumn();

    public TableColumn getTableColumn19() {
        return tableColumn19;
    }

    public void setTableColumn19(TableColumn tc) {
        this.tableColumn19 = tc;
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
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public Start() {
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

        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Start Initialization Failure", e);
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


    public String new_config_button_action() {
        
        getSessionBean1().setNewConfig(true);
        String appPath = getApplicationBean1().getAppPath();
        
        List observationPointData = getApplicationBean1().getAppObservationPointStorage().getList("ObservationPointStorage.xml");
        getSessionBean1().getObservationDataProvider().setList(observationPointData);
        List collectingProcessData = getApplicationBean1().getAppCollectingProcessStorage().getList("CollectingProcessStorage.xml");
        getSessionBean1().getCollectingDataProvider().setList(collectingProcessData);
        List meteringProcessData = getApplicationBean1().getAppMeteringProcessStorage().getList("MeteringProcessStorage.xml");
        getSessionBean1().getMeteringDataProvider().setList(meteringProcessData);
        List exportingProcessData = getApplicationBean1().getAppExportingProcessStorage().getList("ExportingProcessStorage.xml");
        getSessionBean1().getExportingDataProvider().setList(exportingProcessData);
        return "newConfig";
    }


    public String new_device_button_action() {
        getSessionBean1().setNewDevice(true);
        
        return "newDevice";
    }


    public String edit_device_action() {
        RowKey selectedRow = getTableRowGroup2().getRowKey();
        getSessionBean1().setSelectedRowKey(selectedRow);
        Device device = (Device) getApplicationBean1().getAppDeviceDataProvider().getObject(selectedRow);
        getSessionBean1().setOriginalDeviceName(device.getName());
        getSessionBean1().setNewDevice(false);
        return "editDevice";
    }


    public String edit_config_action() {
        getSessionBean1().setNewConfig(false);
        String appPath = getApplicationBean1().getAppPath();
        RowKey selectedRow = getTableRowGroup1().getRowKey();
        ConfigInfo currentConfig = (ConfigInfo) getApplicationBean1().getAppConfigInfoDataProvider().getObject(selectedRow);
        String configFileName = currentConfig.getFileName();
        List observationPointData = getApplicationBean1().getAppObservationPointStorage().getList(configFileName);
        getSessionBean1().getObservationDataProvider().setList(observationPointData);
        List collectingProcessData = getApplicationBean1().getAppCollectingProcessStorage().getList(configFileName);
        getSessionBean1().getCollectingDataProvider().setList(collectingProcessData);
        List meteringProcessData = getApplicationBean1().getAppMeteringProcessStorage().getList(configFileName);
        getSessionBean1().getMeteringDataProvider().setList(meteringProcessData);
        List exportingProcessData = getApplicationBean1().getAppExportingProcessStorage().getList(configFileName);
        getSessionBean1().getExportingDataProvider().setList(exportingProcessData);
        getSessionBean1().setOriginalConfigName(currentConfig.getName());
        getSessionBean1().setOriginalFileName(configFileName);
        getSessionBean1().setSelectedConfig(selectedRow);
        getSessionBean1().setConfigInfo(currentConfig);
        
        return "editConfig";
    }


    public String new_role_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        getSessionBean1().getMappingList().setList(new ArrayList());
        getSessionBean1().setNewRole(true);
        return "newRole";
    }


    public String edit_role_action() {
        RowKey selectedRow = getTableRowGroup3().getRowKey();
        getSessionBean1().setSelectedRowKey(selectedRow);
        Role role = (Role) getApplicationBean1().getAppRoleDataProvider().getObject(selectedRow);
        getSessionBean1().getMappingList().setMap(role.getMapping());
        getSessionBean1().setOriginalRoleName(role.getName());
        getSessionBean1().setNewRole(false);
        return "editRole";
    }


    public String setConfigbutton_action() {
        RowKey selectedRow = getTableRowGroup2().getRowKey();
        Device selectedDevice = (Device) getApplicationBean1().getAppDeviceDataProvider().getObject(selectedRow);
        
        if(selectedDevice.getRole().equals("None")){
          this.alert1.setId("warning");
          this.alert1.setSummary("Device has no role!");
          this.alert1.setDetail("Without a role, there is no configuration to set. Please select a role for the device.");
          return null;
        }
        else{
        getSessionBean1().setSelectedRowKey(selectedRow);
        return "setConfig";
        }
    }


    public String new_scenario_action() {
        
        return "case1";
    }


    public String edit_scenario_action() {
        RowKey selectedRow = getTableRowGroup4().getRowKey();
        getSessionBean1().setSelectedRowKey(selectedRow);
        Scenario selectedScenario = (Scenario) getApplicationBean1().getAppScenarioDataProvider().getObject(selectedRow);
        getSessionBean1().setOriginalScenarioName(selectedScenario.getName());
        
        return "editScenario";
    }


    public String remove_scenario_action() {
        RowKey selectedRow = getTableRowGroup4().getRowKey();
        getApplicationBean1().getAppScenarioDataProvider().removeObject(selectedRow);
        getApplicationBean1().getAppScenarioDataProvider().commitChanges();
        List listToSave = getApplicationBean1().getAppScenarioDataProvider().getList();
        try{
            getApplicationBean1().getAppScenarioStorage().saveList(listToSave);
        
        }catch(Exception ex){
            error("Error writing Storagefile!" + ex.getMessage());
            log("Error writinh Storagefile!", ex);
        }
        
        return null;
    }


    public String remove_role_action() {
        RowKey selectedRow = getTableRowGroup3().getRowKey();
        getApplicationBean1().getAppRoleDataProvider().removeObject(selectedRow);
        getApplicationBean1().getAppRoleDataProvider().commitChanges();
        List listToSave = getApplicationBean1().getAppRoleDataProvider().getList();
        try{
            getApplicationBean1().getAppRoleStorage().saveList(listToSave);
        
        }catch(Exception ex){
            error("Error writing Storagefile!" + ex.getMessage());
            log("Error writinh Storagefile!", ex);
        }
        return null;
    }


    public String remove_device_action() {
        RowKey selectedRow = getTableRowGroup2().getRowKey();
        getApplicationBean1().getAppDeviceDataProvider().removeObject(selectedRow);
        getApplicationBean1().getAppDeviceDataProvider().commitChanges();
        List listToSave = getApplicationBean1().getAppDeviceDataProvider().getList();
        try{
            getApplicationBean1().getAppDeviceStorage().saveList(listToSave);
        
        }catch(Exception ex){
            error("Error writing Storagefile!" + ex.getMessage());
            log("Error writinh Storagefile!", ex);
        }
        return null;
    }


    public String remove_config_action() {
        RowKey selectedRow = getTableRowGroup1().getRowKey();
        getApplicationBean1().getAppConfigInfoDataProvider().removeObject(selectedRow);
        getApplicationBean1().getAppConfigInfoDataProvider().commitChanges();
        List listToSave = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        try{
            getApplicationBean1().getAppConfigInfoStorage().saveList(listToSave);
        
        }catch(Exception ex){
            error("Error writing Storagefile!" + ex.getMessage());
            log("Error writinh Storagefile!", ex);
        }
        return null;
    }


    public String setScenarioButton_action() {
        RowKey selectedRow = getTableRowGroup4().getRowKey();
        getSessionBean1().setSelectedRowKey(selectedRow);
        Scenario currentScenario = (Scenario) getApplicationBean1().getAppScenarioDataProvider().getObject(selectedRow);
        List scenarioDevices = currentScenario.getDeviceList();
        List allDevices = getApplicationBean1().getAppDeviceDataProvider().getList();
        Iterator deviceIterator = allDevices.iterator();
        while(deviceIterator.hasNext()){
            Device currentDevice = (Device) deviceIterator.next();
            if(scenarioDevices.contains(currentDevice.getName())){
                getSessionBean1().getScenarioDeviceDataProvider().addObject(currentDevice);
                getSessionBean1().getScenarioDeviceDataProvider().commitChanges();
            }
        }
        return "setScenario";
    }
}

