/*
 * editConfig.java
 *
 * 
 * Copyright max
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
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Hyperlink;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Label;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.model.Option;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import com.sun.rave.web.ui.component.Alert;



/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editConfig extends AbstractPageBean {
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

    private Button discard_changes_button1 = new Button();

    public Button getDiscard_changes_button1() {
        return discard_changes_button1;
    }

    public void setDiscard_changes_button1(Button b) {
        this.discard_changes_button1 = b;
    }

    private Button save_config_button1 = new Button();

    public Button getSave_config_button1() {
        return save_config_button1;
    }

    public void setSave_config_button1(Button b) {
        this.save_config_button1 = b;
    }

    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }

    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
    }

    private TextField configName1 = new TextField();

    public TextField getConfigName1() {
        return configName1;
    }

    public void setConfigName1(TextField tf) {
        this.configName1 = tf;
    }

    private TextField fileName1 = new TextField();

    public TextField getFileName1() {
        return fileName1;
    }

    public void setFileName1(TextField tf) {
        this.fileName1 = tf;
    }

    private TextArea descript1 = new TextArea();

    public TextArea getDescript1() {
        return descript1;
    }

    public void setDescript1(TextArea ta) {
        this.descript1 = ta;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
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

    private Button add_ObservationPoint = new Button();

    public Button getAdd_ObservationPoint() {
        return add_ObservationPoint;
    }

    public void setAdd_ObservationPoint(Button b) {
        this.add_ObservationPoint = b;
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

    private Hyperlink observ_next = new Hyperlink();

    public Hyperlink getObserv_next() {
        return observ_next;
    }

    public void setObserv_next(Hyperlink h) {
        this.observ_next = h;
    }

    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }

    private Button edit_Observationpoint_button = new Button();

    public Button getEdit_Observationpoint_button() {
        return edit_Observationpoint_button;
    }

    public void setEdit_Observationpoint_button(Button b) {
        this.edit_Observationpoint_button = b;
    }

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private Button remove_observation_point = new Button();

    public Button getRemove_observation_point() {
        return remove_observation_point;
    }

    public void setRemove_observation_point(Button b) {
        this.remove_observation_point = b;
    }

    private HtmlPanelGrid gridPanel4 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel4() {
        return gridPanel4;
    }

    public void setGridPanel4(HtmlPanelGrid hpg) {
        this.gridPanel4 = hpg;
    }

    private Button add_CollectingProcess = new Button();

    public Button getAdd_CollectingProcess() {
        return add_CollectingProcess;
    }

    public void setAdd_CollectingProcess(Button b) {
        this.add_CollectingProcess = b;
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

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }

    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }

    private TableColumn tableColumn10 = new TableColumn();

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tc) {
        this.tableColumn10 = tc;
    }

    private Hyperlink collecting_next = new Hyperlink();

    public Hyperlink getCollecting_next() {
        return collecting_next;
    }

    public void setCollecting_next(Hyperlink h) {
        this.collecting_next = h;
    }

    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private Button edit_CollectingProcess1 = new Button();

    public Button getEdit_CollectingProcess1() {
        return edit_CollectingProcess1;
    }

    public void setEdit_CollectingProcess1(Button b) {
        this.edit_CollectingProcess1 = b;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private Button remove_CollectingProcess1 = new Button();

    public Button getRemove_CollectingProcess1() {
        return remove_CollectingProcess1;
    }

    public void setRemove_CollectingProcess1(Button b) {
        this.remove_CollectingProcess1 = b;
    }

    private HtmlPanelGrid gridPanel5 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel5() {
        return gridPanel5;
    }

    public void setGridPanel5(HtmlPanelGrid hpg) {
        this.gridPanel5 = hpg;
    }

    private Button add_Metering = new Button();

    public Button getAdd_Metering() {
        return add_Metering;
    }

    public void setAdd_Metering(Button b) {
        this.add_Metering = b;
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

    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tc) {
        this.tableColumn16 = tc;
    }

    private TableColumn tableColumn17 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tc) {
        this.tableColumn17 = tc;
    }

    private TableColumn tableColumn18 = new TableColumn();

    public TableColumn getTableColumn18() {
        return tableColumn18;
    }

    public void setTableColumn18(TableColumn tc) {
        this.tableColumn18 = tc;
    }

    private TableColumn tableColumn19 = new TableColumn();

    public TableColumn getTableColumn19() {
        return tableColumn19;
    }

    public void setTableColumn19(TableColumn tc) {
        this.tableColumn19 = tc;
    }

    private Hyperlink metering_next = new Hyperlink();

    public Hyperlink getMetering_next() {
        return metering_next;
    }

    public void setMetering_next(Hyperlink h) {
        this.metering_next = h;
    }

    private TableColumn tableColumn20 = new TableColumn();

    public TableColumn getTableColumn20() {
        return tableColumn20;
    }

    public void setTableColumn20(TableColumn tc) {
        this.tableColumn20 = tc;
    }

    private Button remove_MeteringProcess = new Button();

    public Button getRemove_MeteringProcess() {
        return remove_MeteringProcess;
    }

    public void setRemove_MeteringProcess(Button b) {
        this.remove_MeteringProcess = b;
    }

    private Hyperlink edit_PacketSelection = new Hyperlink();

    public Hyperlink getEdit_PacketSelection() {
        return edit_PacketSelection;
    }

    public void setEdit_PacketSelection(Hyperlink h) {
        this.edit_PacketSelection = h;
    }

    private Hyperlink edit_PacketReporting = new Hyperlink();

    public Hyperlink getEdit_PacketReporting() {
        return edit_PacketReporting;
    }

    public void setEdit_PacketReporting(Hyperlink h) {
        this.edit_PacketReporting = h;
    }

    private Hyperlink edit_FlowMetering = new Hyperlink();

    public Hyperlink getEdit_FlowMetering() {
        return edit_FlowMetering;
    }

    public void setEdit_FlowMetering(Hyperlink h) {
        this.edit_FlowMetering = h;
    }

    private HtmlPanelGrid gridPanel6 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel6() {
        return gridPanel6;
    }

    public void setGridPanel6(HtmlPanelGrid hpg) {
        this.gridPanel6 = hpg;
    }

    private Button add_exportingProcess = new Button();

    public Button getAdd_exportingProcess() {
        return add_exportingProcess;
    }

    public void setAdd_exportingProcess(Button b) {
        this.add_exportingProcess = b;
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

    private TableColumn tableColumn21 = new TableColumn();

    public TableColumn getTableColumn21() {
        return tableColumn21;
    }

    public void setTableColumn21(TableColumn tc) {
        this.tableColumn21 = tc;
    }

    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private TableColumn tableColumn22 = new TableColumn();

    public TableColumn getTableColumn22() {
        return tableColumn22;
    }

    public void setTableColumn22(TableColumn tc) {
        this.tableColumn22 = tc;
    }

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }

    private TableColumn tableColumn23 = new TableColumn();

    public TableColumn getTableColumn23() {
        return tableColumn23;
    }

    public void setTableColumn23(TableColumn tc) {
        this.tableColumn23 = tc;
    }

    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }

    private TableColumn tableColumn24 = new TableColumn();

    public TableColumn getTableColumn24() {
        return tableColumn24;
    }

    public void setTableColumn24(TableColumn tc) {
        this.tableColumn24 = tc;
    }

    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText st) {
        this.staticText13 = st;
    }

    private TableColumn tableColumn25 = new TableColumn();

    public TableColumn getTableColumn25() {
        return tableColumn25;
    }

    public void setTableColumn25(TableColumn tc) {
        this.tableColumn25 = tc;
    }

    private StaticText staticText14 = new StaticText();

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText st) {
        this.staticText14 = st;
    }

    private TableColumn tableColumn26 = new TableColumn();

    public TableColumn getTableColumn26() {
        return tableColumn26;
    }

    public void setTableColumn26(TableColumn tc) {
        this.tableColumn26 = tc;
    }

    private StaticText staticText15 = new StaticText();

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText st) {
        this.staticText15 = st;
    }

    private TableColumn tableColumn27 = new TableColumn();

    public TableColumn getTableColumn27() {
        return tableColumn27;
    }

    public void setTableColumn27(TableColumn tc) {
        this.tableColumn27 = tc;
    }

    private StaticText staticText16 = new StaticText();

    public StaticText getStaticText16() {
        return staticText16;
    }

    public void setStaticText16(StaticText st) {
        this.staticText16 = st;
    }

    private TableColumn tableColumn28 = new TableColumn();

    public TableColumn getTableColumn28() {
        return tableColumn28;
    }

    public void setTableColumn28(TableColumn tc) {
        this.tableColumn28 = tc;
    }

    private StaticText staticText17 = new StaticText();

    public StaticText getStaticText17() {
        return staticText17;
    }

    public void setStaticText17(StaticText st) {
        this.staticText17 = st;
    }

    private TableColumn tableColumn29 = new TableColumn();

    public TableColumn getTableColumn29() {
        return tableColumn29;
    }

    public void setTableColumn29(TableColumn tc) {
        this.tableColumn29 = tc;
    }

    private Button edit_ExportingProcess = new Button();

    public Button getEdit_ExportingProcess() {
        return edit_ExportingProcess;
    }

    public void setEdit_ExportingProcess(Button b) {
        this.edit_ExportingProcess = b;
    }

    private TableColumn tableColumn30 = new TableColumn();

    public TableColumn getTableColumn30() {
        return tableColumn30;
    }

    public void setTableColumn30(TableColumn tc) {
        this.tableColumn30 = tc;
    }

    private Button remove_ExportingProcess = new Button();

    public Button getRemove_ExportingProcess() {
        return remove_ExportingProcess;
    }

    public void setRemove_ExportingProcess(Button b) {
        this.remove_ExportingProcess = b;
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
    public editConfig() {
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
            log("editConfig Initialization Failure", e);
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
     * Holds value of property configInformation.
     */
    private ipfixconfig.ConfigInfo configInformation;

    /**
     * Getter for property configInformation.
     * @return Value of property configInformation.
     */
    public ipfixconfig.ConfigInfo getConfigInformation() {

        return this.configInformation;
    }

    /**
     * Setter for property configInformation.
     * @param configInformation New value of property configInformation.
     */
    public void setConfigInformation(ipfixconfig.ConfigInfo configInformation) {

        this.configInformation = configInformation;
    }
  


    public String discard_changes_button1_action() {
        return "discard_changes";
    }


    public String add_Metering_action() {
        getSessionBean1().getMeteringDataProvider().appendRow();
        getSessionBean1().getMeteringDataProvider().commitChanges();
        return null;
    }


    public String add_ObservationPoint_action() {
        getSessionBean1().setSelectedObservationPointRowKey(null);
        getSessionBean1().setNewObservationPoint(true);
        return "addObservationPoint";
    }


    public String add_CollectingProcess_action() {
        getSessionBean1().setSelectedCollectingProcessRowKey(null);
        getSessionBean1().setNewCollectingProcess(true);
        getSessionBean1().setNewMethod(true);
        return "addCollecting";
    }


    public String add_exportingProcess_action() {
        getSessionBean1().setSelectedExportingProcessRowKey(null);
        getSessionBean1().setNewExportingProcess(true);
        getSessionBean1().setNewMethod(true);
        return "addExporting";
    }
    


    public String edit_Observationpoint_button_action() {
        getSessionBean1().setSelectedObservationPointRowKey(tableRowGroup1.getRowKey());
        getSessionBean1().setNewObservationPoint(false);
        return "addObservationPoint";
    }
    


    public String remove_observation_point_action() {
        RowKey removeRow = tableRowGroup1.getRowKey();
        try {
             getSessionBean1().getObservationDataProvider().removeObject(removeRow);
             getSessionBean1().getObservationDataProvider().commitChanges();
            }catch (Exception ex) {
                this.alert1.setType("error");
                this.alert1.setSummary("Can't remove row from Data Provider.");
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find Row to remove " + removeRow.toString(), ex);
            }
        
        return null;
    }


    public String edit_CollectingProcess1_action() {
        RowKey selectedRow = this.getTableRowGroup2().getRowKey();
        getSessionBean1().setSelectedCollectingProcessRowKey(selectedRow);
        List listenerList = (List) getSessionBean1().getCollectingDataProvider().getValue("listenerList", selectedRow);
        getSessionBean1().getListenerDataProvider().setList(listenerList);
        getSessionBean1().getListenerDataProvider().setUserResizable(true);
        getSessionBean1().setNewCollectingProcess(false);
        getSessionBean1().setNewMethod(true);
        return "addCollecting";
    }


    public String remove_CollectingProcess1_action() {
        RowKey selectedRow = this.getTableRowGroup2().getRowKey();
        try{
            getSessionBean1().getCollectingDataProvider().removeObject(selectedRow);
        getSessionBean1().getCollectingDataProvider().commitChanges();
        }catch(DataProviderException ex){
            log("Can't remove row from Data Provider.");
            this.alert1.setType("error");
            this.alert1.setSummary("Can't remove row from Data Provider.");
            this.alert1.setDetail(ex.getMessage());
            getSessionBean1().getCollectingDataProvider().revertChanges();
        }
        
        return null;
    }


    public String edit_PacketSelection_action() {
        RowKey selectedRow = tableRowGroup3.getRowKey();
        getSessionBean1().setSelectedMeteringProcessRowKey(selectedRow);
        PacketSelection packetSelection = new PacketSelection();
        if (selectedRow != null) {
            try {
                packetSelection = (PacketSelection) getSessionBean1().getMeteringDataProvider().getValue("packetSelectionData", selectedRow);
                getSessionBean1().setMeteringProcessId((Integer) getSessionBean1().getMeteringDataProvider().getValue("id", selectedRow));
            }catch (Exception ex) {
                this.alert1.setType("error");
                this.alert1.setSummary("Cannot find PacketSelection "+ selectedRow.toString());
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find PacketSelection " + selectedRow.toString(), ex);
            }
        } else {
            error("No packetSelection for this available");
            log("No packetSelection for this available");
        }
        getSessionBean1().setPacketSelectionData(packetSelection);
        return "packetSelection";
    }


    public String edit_PacketReporting_action() {
        RowKey selectedRow = tableRowGroup3.getRowKey();
        getSessionBean1().setSelectedMeteringProcessRowKey(selectedRow);
        PacketReporting packetReporting = new PacketReporting();
        
        if (selectedRow != null) {
            try {
                packetReporting = (PacketReporting) getSessionBean1().getMeteringDataProvider().getValue("packetReportingData", selectedRow);
                getSessionBean1().setMeteringProcessId((Integer) getSessionBean1().getMeteringDataProvider().getValue("id", selectedRow));
            }catch (Exception ex) {
                this.alert1.setType("error");
                this.alert1.setSummary("Cannot find PacketReporting "+ selectedRow.toString());
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find PacketReporting " + selectedRow.toString(), ex);
            }
        } else {
            error("No packetReporting for this available");
            log("No packetReporting for this available");
        }
        getSessionBean1().setPacketReportingData(packetReporting);
        getSessionBean1().getSesPacketReportingDataProvider().setList(packetReporting.getPacketReportingIEList());
        getSessionBean1().getSesPacketReportingDataProvider().setUserResizable(true);
        getSessionBean1().setNewMethod(true);
        return "packetReporting";
    }


    public String edit_FlowMetering_action() {
        RowKey selectedRow = tableRowGroup3.getRowKey();
        getSessionBean1().setSelectedMeteringProcessRowKey(selectedRow);
        FlowMetering flowMetering = new FlowMetering();
        
        if (selectedRow != null) {
            try {
                flowMetering = (FlowMetering) getSessionBean1().getMeteringDataProvider().getValue("flowMeteringData", selectedRow);
                getSessionBean1().setMeteringProcessId((Integer) getSessionBean1().getMeteringDataProvider().getValue("id", selectedRow));
            }catch (Exception ex) {
                this.alert1.setType("error");
                this.alert1.setSummary("Cannot find FlowMetering"+ selectedRow.toString());
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find FlowMetering " + selectedRow.toString(), ex);
            }
        } else {
            error("No flowMetering for this available");
            log("No flowMetering for this available");
        }
        getSessionBean1().setFlowMeteringData(flowMetering);
        getSessionBean1().setRuleTemplate("0");
        
        return "flowMetering";
    }


    public String edit_ExportingProcess_action() {
        RowKey selectedRow = this.getTableRowGroup4().getRowKey();
        getSessionBean1().setSelectedExportingProcessRowKey(selectedRow);
        if (selectedRow != null) {
            try {
                List collectorList = (List) getSessionBean1().getExportingDataProvider().getValue("collectorList", selectedRow);
                getSessionBean1().getReceivingCollectorsDataProvider().setList(collectorList);
            }catch (Exception ex) {
                this.alert1.setType("error");
                this.alert1.setSummary("Cannot find ExportingProcess"+ selectedRow.toString());
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find ExportingProcess " + selectedRow.toString(), ex);
                return null;
            }
        } else {
            this.alert1.setType("error");
            this.alert1.setSummary("No ExportingProcess available "+ selectedRow.toString());
            log("No ExportingProcess for this available");
            return null;
        }
        
        getSessionBean1().setNewExportingProcess(false);
        getSessionBean1().setNewMethod(true);
        return "addExporting";
    }


    public String remove_ExportingProcess_action() {
        RowKey selectedRow = this.getTableRowGroup4().getRowKey();
        try{
            getSessionBean1().getExportingDataProvider().removeObject(selectedRow);
        getSessionBean1().getExportingDataProvider().commitChanges();
        }catch(DataProviderException ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Can't remove row from Data Provider!");
            this.alert1.setDetail(ex.getMessage());
            log("Can't remove row from Data Provider.");            
            getSessionBean1().getExportingDataProvider().revertChanges();
        }
        return null;
    }


    public String observ_next_action() {
        List meteringProcessList = getSessionBean1().getMeteringDataProvider().getList();
        int optionsSize = meteringProcessList.size();
        Option[] meteringOptions = new Option[optionsSize];
        for(int i = 0; i < optionsSize; i++){
            MeteringProcess currentMetering = (MeteringProcess) meteringProcessList.get(i);
            meteringOptions[i] = new Option("meteringProcessId " + currentMetering.getId(), "MeteringProcess " + currentMetering.getId());
        }
        getSessionBean1().setNextProcessOptions(meteringOptions);
        
        RowKey selectedRow = this.getTableRowGroup1().getRowKey();
        ObservationPoint selectedObservPoint = (ObservationPoint) getSessionBean1().getObservationDataProvider().getObject(selectedRow);
        
        List nextList = selectedObservPoint.getNext();
        String[] selectedNext = new String[]{};
        if(!nextList.isEmpty()){
            selectedNext = new String[nextList.size()];
            for(int j = 0; j < nextList.size(); j++){
                Next currentNext = (Next) nextList.get(j);
                selectedNext[j] = new String(currentNext.getName() + " " + currentNext.getId());
            }
        }
        getSessionBean1().setSelectedNext(selectedNext);
        getSessionBean1().setSelectedObservationPointRowKey(selectedRow);
        getSessionBean1().setSelectedCollectingProcessRowKey(null);
        getSessionBean1().setSelectedMeteringProcessRowKey(null);
        return "set_Next";
    }


    public String collecting_next_action() {
        List meteringProcessList = getSessionBean1().getMeteringDataProvider().getList();
        int meteringSize = meteringProcessList.size();
        List exportingProcessList = getSessionBean1().getExportingDataProvider().getList();
        int exportingSize = exportingProcessList.size();
        Option[] nextOptions = new Option[meteringSize + exportingSize];
        
        for(int i = 0; i < meteringSize; i++){
            MeteringProcess currentMetering = (MeteringProcess) meteringProcessList.get(i);
            nextOptions[i] = new Option("meteringProcessId " + currentMetering.getId(), "MeteringProcess " + currentMetering.getId());
        }
        for(int j = 0; j < exportingSize; j++){
            ExportingProcess currentExporting = (ExportingProcess) exportingProcessList.get(j);
            nextOptions[meteringSize + j] = new Option("exportingProcessId " + currentExporting.getId(), "ExportingProcess " + currentExporting.getId());
        }
        getSessionBean1().setNextProcessOptions(nextOptions);
        
        RowKey selectedRow = this.getTableRowGroup2().getRowKey();
        CollectingProcess selectedCollecting = (CollectingProcess) getSessionBean1().getCollectingDataProvider().getObject(selectedRow);
        
        List nextList = selectedCollecting.getNext();
        if(!nextList.isEmpty()){
            String[] selectedNext;
            selectedNext = new String[nextList.size()];
            for(int j = 0; j < nextList.size(); j++){
                Next currentNext = (Next) nextList.get(j);
                selectedNext[j] = new String(currentNext.getName() + " " + currentNext.getId());
            }
           getSessionBean1().setSelectedNext(selectedNext); 
        }
        
        getSessionBean1().setSelectedObservationPointRowKey(null);
        getSessionBean1().setSelectedCollectingProcessRowKey(selectedRow);
        getSessionBean1().setSelectedMeteringProcessRowKey(null);
        
        return "set_Next";
    }


    public String metering_next_action() {
        List meteringProcessList = getSessionBean1().getMeteringDataProvider().getList();
        int meteringSize = meteringProcessList.size();
        List exportingProcessList = getSessionBean1().getExportingDataProvider().getList();
        int exportingSize = exportingProcessList.size();
        Option[] nextOptions = new Option[meteringSize + exportingSize];
        
        for(int i = 0; i < meteringSize; i++){
            MeteringProcess currentMetering = (MeteringProcess) meteringProcessList.get(i);
            nextOptions[i] = new Option("meteringProcessId " + currentMetering.getId(), "MeteringProcess " + currentMetering.getId());
        }
        for(int j = 0; j < exportingSize; j++){
            ExportingProcess currentExporting = (ExportingProcess) exportingProcessList.get(j);
            nextOptions[meteringSize + j] = new Option("exportingProcessId " + currentExporting.getId(), "ExportingProcess " + currentExporting.getId());
        }
        getSessionBean1().setNextProcessOptions(nextOptions);
        
        RowKey selectedRow = this.getTableRowGroup3().getRowKey();
        MeteringProcess selectedMetering = (MeteringProcess) getSessionBean1().getMeteringDataProvider().getObject(selectedRow);
        
        List nextList = selectedMetering.getNext();
        if(!nextList.isEmpty()){
            String[] selectedNext;
            selectedNext = new String[nextList.size()];
            for(int j = 0; j < nextList.size(); j++){
                Next currentNext = (Next) nextList.get(j);
                selectedNext[j] = new String(currentNext.getName() + " " + currentNext.getId());
            }
           getSessionBean1().setSelectedNext(selectedNext); 
        }
        
        getSessionBean1().setSelectedObservationPointRowKey(null);
        getSessionBean1().setSelectedCollectingProcessRowKey(null);
        getSessionBean1().setSelectedMeteringProcessRowKey(selectedRow);
        return "set_Next";
    }


    public String save_config_button1_action() {
        
        List observationPointsList = getSessionBean1().getObservationDataProvider().getList();
        
        List collectingProcessList = getSessionBean1().getCollectingDataProvider().getList();
        
        List meteringProcessList = getSessionBean1().getMeteringDataProvider().getList();
        
        List exportingProcessList = getSessionBean1().getExportingDataProvider().getList();
        
        String fileName = getSessionBean1().getConfigInfo().getFileName();        
        
        try{
            getApplicationBean1().getConfigStorage().SaveConfig(fileName, observationPointsList, collectingProcessList, meteringProcessList, exportingProcessList);
        }
        catch(Exception ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Could not write configuration file!");
            this.alert1.setDetail(ex.getMessage());
            log("Error writing Config to file:" + ex.getMessage());
        }
        this.alert1.setType("success");
        this.alert1.setSummary("Config file saved");
        
        Date now = new Date();
        DateFormat formatDate = DateFormat.getDateInstance();
        String lastmodified = formatDate.format(now);
        getSessionBean1().getConfigInfo().setLastmodified(lastmodified);
        try{
        getApplicationBean1().getAppConfigInfoDataProvider().setObject(getSessionBean1().getSelectedConfig(), getSessionBean1().getConfigInfo());
        getApplicationBean1().getAppConfigInfoDataProvider().commitChanges();
        }
        catch(DataProviderException ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Problem with the Config Info Dataprovider.");
            this.alert1.setDetail(ex.getMessage());
            log("Problem with the Config Info Dataprovider." + ex.getMessage());
        }
        
        List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        try{
            getApplicationBean1().getAppConfigInfoStorage().saveList(configInfoList);
        }
        catch(Exception ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Error writing ConfigInfo Storagefile.");
            this.alert1.setDetail(ex.getMessage());
            log("Error writing configInfo file!", ex);
        }
        return null;
    }
    /*
     * Überprüft, ob der Name der Konfiguration schon verwendet wird.
     */
    
    public void configName_validate(FacesContext context, UIComponent component, Object value) {
        String newConfigName = String.valueOf(value);
        if(!newConfigName.equals(getSessionBean1().getOriginalConfigName())){
        List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        Iterator listIterator = configInfoList.iterator();
        ArrayList nameList = new ArrayList(configInfoList.size());
        while(listIterator.hasNext()){
        ConfigInfo currentConfigInfo = (ConfigInfo) listIterator.next();
        nameList.add(currentConfigInfo.getName());
        }
        if(nameList.contains(newConfigName)) throw new ValidatorException(new FacesMessage
		("Configurationname is already used! Please choose a different name."));
        }
    }
    
    /*
     * Überprüft, ob er Dateiname schon verwendet wird.
     */
    public void fileName_validate(FacesContext context, UIComponent component, Object value) {
        String newFileName = String.valueOf(value);
        if(!newFileName.equals(getSessionBean1().getOriginalFileName())){
        List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        Iterator listIterator = configInfoList.iterator();
        ArrayList fileNameList = new ArrayList(configInfoList.size());
        while(listIterator.hasNext()){
        ConfigInfo currentConfigInfo = (ConfigInfo) listIterator.next();
        fileNameList.add(currentConfigInfo.getFileName());
        }
        if(fileNameList.contains(newFileName)) throw new ValidatorException(new FacesMessage
		("Filename is already used! Please choose a different filename."));
        }
    }
    


    public String remove_MeteringProcess_action() {
        RowKey removeRow = tableRowGroup3.getRowKey();
        try {
             getSessionBean1().getMeteringDataProvider().removeObject(removeRow);
             getSessionBean1().getMeteringDataProvider().commitChanges();
            }catch (Exception ex) {
                this.alert1.setType("error");
                this.alert1.setSummary("Can't remove row from Data Provider.");
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find Row to remove " + removeRow.toString(), ex);
            }
        
        return null;
    }
}

