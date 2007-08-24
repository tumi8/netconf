/*
 * Page1.java
 *
 * 
 * Copyright Max
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
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Hyperlink;
import java.util.List;
import com.sun.data.provider.impl.MapDataProvider;
import com.sun.data.provider.impl.TableRowDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.model.Option;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.jdom.Namespace;
import com.sun.rave.web.ui.component.Message;
import com.sun.rave.web.ui.component.Alert;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class createConfig extends AbstractPageBean {
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

    private Table observationPointTable = new Table();

    public Table getObservationPointTable() {
        return observationPointTable;
    }

    public void setObservationPointTable(Table t) {
        this.observationPointTable = t;
    }

    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }

    private Button add_ObservationPoint = new Button();

    public Button getAdd_ObservationPoint() {
        return add_ObservationPoint;
    }

    public void setAdd_ObservationPoint(Button b) {
        this.add_ObservationPoint = b;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }

    private Table collectingProcessTable = new Table();

    public Table getCollectingProcessTable() {
        return collectingProcessTable;
    }

    public void setCollectingProcessTable(Table t) {
        this.collectingProcessTable = t;
    }

    private TableRowGroup tableRowGroup2 = new TableRowGroup();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup trg) {
        this.tableRowGroup2 = trg;
    }

    private Button add_CollectingProcess = new Button();

    public Button getAdd_CollectingProcess() {
        return add_CollectingProcess;
    }

    public void setAdd_CollectingProcess(Button b) {
        this.add_CollectingProcess = b;
    }

    private Table meteringProcessTable = new Table();

    public Table getMeteringProcessTable() {
        return meteringProcessTable;
    }

    public void setMeteringProcessTable(Table t) {
        this.meteringProcessTable = t;
    }

    private TableRowGroup tableRowGroup3 = new TableRowGroup();

    public TableRowGroup getTableRowGroup3() {
        return tableRowGroup3;
    }

    public void setTableRowGroup3(TableRowGroup trg) {
        this.tableRowGroup3 = trg;
    }

    private MapDataProvider mapDataProvider1 = new MapDataProvider();

    public MapDataProvider getMapDataProvider1() {
        return mapDataProvider1;
    }

    public void setMapDataProvider1(MapDataProvider mdp) {
        this.mapDataProvider1 = mdp;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private Button add_metering_process = new Button();

    public Button getAdd_metering_process() {
        return add_metering_process;
    }

    public void setAdd_metering_process(Button b) {
        this.add_metering_process = b;
    }

    private TextField configName = new TextField();

    public TextField getConfigName() {
        return configName;
    }

    public void setConfigName(TextField tf) {
        this.configName = tf;
    }

    private TextField fileName = new TextField();

    public TextField getFileName() {
        return fileName;
    }

    public void setFileName(TextField tf) {
        this.fileName = tf;
    }

    private TextArea descript = new TextArea();

    public TextArea getDescript() {
        return descript;
    }

    public void setDescript(TextArea ta) {
        this.descript = ta;
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

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
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

    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }

    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
    }

    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private TableColumn tableColumn11 = new TableColumn();

    public TableColumn getTableColumn11() {
        return tableColumn11;
    }

    public void setTableColumn11(TableColumn tc) {
        this.tableColumn11 = tc;
    }

    private Button edit_CollectingProcess = new Button();

    public Button getEdit_CollectingProcess() {
        return edit_CollectingProcess;
    }

    public void setEdit_CollectingProcess(Button b) {
        this.edit_CollectingProcess = b;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private Button remove_CollectingProcess = new Button();

    public Button getRemove_CollectingProcess() {
        return remove_CollectingProcess;
    }

    public void setRemove_CollectingProcess(Button b) {
        this.remove_CollectingProcess = b;
    }

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private Hyperlink edit_PacketSelection = new Hyperlink();

    public Hyperlink getEdit_PacketSelection() {
        return edit_PacketSelection;
    }

    public void setEdit_PacketSelection(Hyperlink h) {
        this.edit_PacketSelection = h;
    }

    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }

    private Hyperlink edit_PacketReporting = new Hyperlink();

    public Hyperlink getEdit_PacketReporting() {
        return edit_PacketReporting;
    }

    public void setEdit_PacketReporting(Hyperlink h) {
        this.edit_PacketReporting = h;
    }

    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tc) {
        this.tableColumn16 = tc;
    }

    private Hyperlink edit_FlowMetering = new Hyperlink();

    public Hyperlink getEdit_FlowMetering() {
        return edit_FlowMetering;
    }

    public void setEdit_FlowMetering(Hyperlink h) {
        this.edit_FlowMetering = h;
    }

    private TableColumn tableColumn17 = new TableColumn();

    public TableColumn getTableColumn17() {
        return tableColumn17;
    }

    public void setTableColumn17(TableColumn tc) {
        this.tableColumn17 = tc;
    }

    private Hyperlink metering_next = new Hyperlink();

    public Hyperlink getMetering_next() {
        return metering_next;
    }

    public void setMetering_next(Hyperlink h) {
        this.metering_next = h;
    }

    private TableColumn tableColumn18 = new TableColumn();

    public TableColumn getTableColumn18() {
        return tableColumn18;
    }

    public void setTableColumn18(TableColumn tc) {
        this.tableColumn18 = tc;
    }

    private Checkbox checkbox1 = new Checkbox();

    public Checkbox getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(Checkbox c) {
        this.checkbox1 = c;
    }

    private TableColumn tableColumn19 = new TableColumn();

    public TableColumn getTableColumn19() {
        return tableColumn19;
    }

    public void setTableColumn19(TableColumn tc) {
        this.tableColumn19 = tc;
    }

    private TableColumn tableColumn20 = new TableColumn();

    public TableColumn getTableColumn20() {
        return tableColumn20;
    }

    public void setTableColumn20(TableColumn tc) {
        this.tableColumn20 = tc;
    }

    private Hyperlink observ_next = new Hyperlink();

    public Hyperlink getObserv_next() {
        return observ_next;
    }

    public void setObserv_next(Hyperlink h) {
        this.observ_next = h;
    }

    private TableColumn tableColumn21 = new TableColumn();

    public TableColumn getTableColumn21() {
        return tableColumn21;
    }

    public void setTableColumn21(TableColumn tc) {
        this.tableColumn21 = tc;
    }

    private Button edit_Observationpoint_button = new Button();

    public Button getEdit_Observationpoint_button() {
        return edit_Observationpoint_button;
    }

    public void setEdit_Observationpoint_button(Button b) {
        this.edit_Observationpoint_button = b;
    }

    private TableColumn tableColumn22 = new TableColumn();

    public TableColumn getTableColumn22() {
        return tableColumn22;
    }

    public void setTableColumn22(TableColumn tc) {
        this.tableColumn22 = tc;
    }

    private Button remove_observation_point = new Button();

    public Button getRemove_observation_point() {
        return remove_observation_point;
    }

    public void setRemove_observation_point(Button b) {
        this.remove_observation_point = b;
    }

    private TableColumn tableColumn23 = new TableColumn();

    public TableColumn getTableColumn23() {
        return tableColumn23;
    }

    public void setTableColumn23(TableColumn tc) {
        this.tableColumn23 = tc;
    }

    private Checkbox checkbox2 = new Checkbox();

    public Checkbox getCheckbox2() {
        return checkbox2;
    }

    public void setCheckbox2(Checkbox c) {
        this.checkbox2 = c;
    }

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private Checkbox checkbox3 = new Checkbox();

    public Checkbox getCheckbox3() {
        return checkbox3;
    }

    public void setCheckbox3(Checkbox c) {
        this.checkbox3 = c;
    }

    private Button remove_MeteringProcess = new Button();

    public Button getRemove_MeteringProcess() {
        return remove_MeteringProcess;
    }

    public void setRemove_MeteringProcess(Button b) {
        this.remove_MeteringProcess = b;
    }

    private Table exportingProcessTable = new Table();

    public Table getExportingProcessTable() {
        return exportingProcessTable;
    }

    public void setExportingProcessTable(Table t) {
        this.exportingProcessTable = t;
    }

    private TableRowGroup tableRowGroup4 = new TableRowGroup();

    public TableRowGroup getTableRowGroup4() {
        return tableRowGroup4;
    }

    public void setTableRowGroup4(TableRowGroup trg) {
        this.tableRowGroup4 = trg;
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

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }

    private TableColumn tableColumn26 = new TableColumn();

    public TableColumn getTableColumn26() {
        return tableColumn26;
    }

    public void setTableColumn26(TableColumn tc) {
        this.tableColumn26 = tc;
    }

    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }

    private TableColumn tableColumn27 = new TableColumn();

    public TableColumn getTableColumn27() {
        return tableColumn27;
    }

    public void setTableColumn27(TableColumn tc) {
        this.tableColumn27 = tc;
    }

    private StaticText staticText13 = new StaticText();

    public StaticText getStaticText13() {
        return staticText13;
    }

    public void setStaticText13(StaticText st) {
        this.staticText13 = st;
    }

    private TableColumn tableColumn28 = new TableColumn();

    public TableColumn getTableColumn28() {
        return tableColumn28;
    }

    public void setTableColumn28(TableColumn tc) {
        this.tableColumn28 = tc;
    }

    private StaticText staticText14 = new StaticText();

    public StaticText getStaticText14() {
        return staticText14;
    }

    public void setStaticText14(StaticText st) {
        this.staticText14 = st;
    }

    private TableColumn tableColumn29 = new TableColumn();

    public TableColumn getTableColumn29() {
        return tableColumn29;
    }

    public void setTableColumn29(TableColumn tc) {
        this.tableColumn29 = tc;
    }

    private StaticText staticText15 = new StaticText();

    public StaticText getStaticText15() {
        return staticText15;
    }

    public void setStaticText15(StaticText st) {
        this.staticText15 = st;
    }

    private TableColumn tableColumn30 = new TableColumn();

    public TableColumn getTableColumn30() {
        return tableColumn30;
    }

    public void setTableColumn30(TableColumn tc) {
        this.tableColumn30 = tc;
    }

    private StaticText staticText16 = new StaticText();

    public StaticText getStaticText16() {
        return staticText16;
    }

    public void setStaticText16(StaticText st) {
        this.staticText16 = st;
    }

    private TableColumn tableColumn31 = new TableColumn();

    public TableColumn getTableColumn31() {
        return tableColumn31;
    }

    public void setTableColumn31(TableColumn tc) {
        this.tableColumn31 = tc;
    }

    private StaticText staticText17 = new StaticText();

    public StaticText getStaticText17() {
        return staticText17;
    }

    public void setStaticText17(StaticText st) {
        this.staticText17 = st;
    }

    private TableColumn tableColumn32 = new TableColumn();

    public TableColumn getTableColumn32() {
        return tableColumn32;
    }

    public void setTableColumn32(TableColumn tc) {
        this.tableColumn32 = tc;
    }

    private Button edit_ExportingProcess = new Button();

    public Button getEdit_ExportingProcess() {
        return edit_ExportingProcess;
    }

    public void setEdit_ExportingProcess(Button b) {
        this.edit_ExportingProcess = b;
    }

    private TableColumn tableColumn33 = new TableColumn();

    public TableColumn getTableColumn33() {
        return tableColumn33;
    }

    public void setTableColumn33(TableColumn tc) {
        this.tableColumn33 = tc;
    }

    private Button remove_ExportingProcess = new Button();

    public Button getRemove_ExportingProcess() {
        return remove_ExportingProcess;
    }

    public void setRemove_ExportingProcess(Button b) {
        this.remove_ExportingProcess = b;
    }

    private TableColumn tableColumn34 = new TableColumn();

    public TableColumn getTableColumn34() {
        return tableColumn34;
    }

    public void setTableColumn34(TableColumn tc) {
        this.tableColumn34 = tc;
    }

    private Checkbox checkbox4 = new Checkbox();

    public Checkbox getCheckbox4() {
        return checkbox4;
    }

    public void setCheckbox4(Checkbox c) {
        this.checkbox4 = c;
    }

    private Button add_exportingProcess = new Button();

    public Button getAdd_exportingProcess() {
        return add_exportingProcess;
    }

    public void setAdd_exportingProcess(Button b) {
        this.add_exportingProcess = b;
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

    private HtmlPanelGrid gridPanel6 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel6() {
        return gridPanel6;
    }

    public void setGridPanel6(HtmlPanelGrid hpg) {
        this.gridPanel6 = hpg;
    }

    private Button save_config_button = new Button();

    public Button getSave_config_button() {
        return save_config_button;
    }

    public void setSave_config_button(Button b) {
        this.save_config_button = b;
    }

    private Button save_observationpoints = new Button();

    public Button getSave_observationpoints() {
        return save_observationpoints;
    }

    public void setSave_observationpoints(Button b) {
        this.save_observationpoints = b;
    }

    private Button save_collecting = new Button();

    public Button getSave_collecting() {
        return save_collecting;
    }

    public void setSave_collecting(Button b) {
        this.save_collecting = b;
    }

    private Button save_metering = new Button();

    public Button getSave_metering() {
        return save_metering;
    }

    public void setSave_metering(Button b) {
        this.save_metering = b;
    }

    private Button save_exporting = new Button();

    public Button getSave_exporting() {
        return save_exporting;
    }

    public void setSave_exporting(Button b) {
        this.save_exporting = b;
    }

    private Button discard_changes_button = new Button();

    public Button getDiscard_changes_button() {
        return discard_changes_button;
    }

    public void setDiscard_changes_button(Button b) {
        this.discard_changes_button = b;
    }

    private Message message1 = new Message();

    public Message getMessage1() {
        return message1;
    }

    public void setMessage1(Message m) {
        this.message1 = m;
    }

    private Message message2 = new Message();

    public Message getMessage2() {
        return message2;
    }

    public void setMessage2(Message m) {
        this.message2 = m;
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
    public createConfig() {
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
        getSessionBean1().setSelectedRowKey(null);
// <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
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
     * neuen Observation Point zur Liste hinzufuegen.
     */

    public String add_ObservationPoint_action() {
        getSessionBean1().setSelectedObservationPointRowKey(null);
        getSessionBean1().setNewObservationPoint(true);
        return "addObservPoint";
    }
    
    public String edit_Observationpoint_button_action() {
        getSessionBean1().setSelectedObservationPointRowKey(tableRowGroup1.getRowKey());
        getSessionBean1().setNewObservationPoint(false);
        return "editObservPoint";
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
    /*
     * Speichere die Liste der ObservationPoints zurueck in die Datei. 
    

    public String save_ObservPointChanges_action() {
        try{
            getApplicationBean1().getAppObservationPointDataProvider().commitChanges();
        }
        catch (DataProviderException ex) {
            this.alert1.setType("error");
            this.alert1.setSummary("Problem with the Observation Point Data Provider.");
            this.alert1.setDetail(ex.getMessage());
            getApplicationBean1().getAppObservationPointDataProvider().revertChanges();
            log("Problem with the Observation Point Data Provider.", ex);
        }
        List observationPointList = getApplicationBean1().getAppObservationPointDataProvider().getList();
        getApplicationBean1().getAppObservationPointStorage().saveList(observationPointList);
        return null;
    }
    */
    public String add_CollectingProcess_action() {
        getSessionBean1().setSelectedCollectingProcessRowKey(null);
        getSessionBean1().getListenerDataProvider().setList(new ArrayList());
        getSessionBean1().setNewCollectingProcess(true);
        getSessionBean1().setNewMethod(true);
        return "addCollectingProcess";
    }
    
    public String add_metering_process_action() {
        
        getSessionBean1().getMeteringDataProvider().appendRow();
        getSessionBean1().getMeteringDataProvider().commitChanges();
        return null;
    }
  

    /**
     * Holds value of property configInformation.
     */
    private ipfixconfig.ConfigInfo configInformation = new ConfigInfo();

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

    public String edit_CollectingProcess_action() {
        RowKey selectedRow = this.getTableRowGroup2().getRowKey();
        getSessionBean1().setSelectedCollectingProcessRowKey(selectedRow);
        List listenerList = (List) getSessionBean1().getCollectingDataProvider().getValue("listenerList", selectedRow);
        getSessionBean1().getListenerDataProvider().setList(listenerList);
        getSessionBean1().getListenerDataProvider().setUserResizable(true);
        getSessionBean1().setNewCollectingProcess(false);
        getSessionBean1().setNewMethod(true);
        return "editCollectingProcess";
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
                this.alert1.setSummary("Cannot find PacketSelection");
                this.alert1.setDetail(ex.getMessage());
                log("Cannot find PacketSelection " + selectedRow.toString(), ex);
            }
        } else {
            error("No packetSelection for this available");
            log("No packetSelection for this available");
        }
        getSessionBean1().setPacketSelectionData(packetSelection);
        return "editPacketSelection";
        
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
                this.alert1.setSummary("Cannot find PacketReporting");
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
        return "editPacketReporting";
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
                this.alert1.setSummary("Cannot find FlowMetering");
                this.alert1.setDetail(ex.getMessage());
                
                log("Cannot find FlowMetering " + selectedRow.toString(), ex);
            }
        } else {
            error("No flowMetering for this available");
            log("No flowMetering for this available");
        }
        getSessionBean1().setFlowMeteringData(flowMetering);
        getSessionBean1().setRuleTemplate("0");
        return "editFlowMetering";
    }


    public String remove_CollectingProcess_action() {
        RowKey selectedRow = this.getTableRowGroup2().getRowKey();
        try{
            getSessionBean1().getCollectingDataProvider().removeObject(selectedRow);
            getSessionBean1().getCollectingDataProvider().commitChanges();
        }
        catch(DataProviderException ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Can't remove row from Data Provider.");
            this.alert1.setDetail(ex.getMessage());
            getSessionBean1().getCollectingDataProvider().revertChanges();
            log("Can't remove row from Data Provider.", ex);
        }
        
        return null;
    }


    public String remove_MeteringProcess_action() {
        RowKey selectedRow = this.getTableRowGroup3().getRowKey();
        try{
            getSessionBean1().getMeteringDataProvider().removeObject(selectedRow);
        getSessionBean1().getMeteringDataProvider().commitChanges();
        }catch(DataProviderException ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Can't remove row from Data Provider.");
            this.alert1.setDetail(ex.getMessage());
            getSessionBean1().getMeteringDataProvider().revertChanges();
            log("Can't remove row from Data Provider.", ex);
        }
        return null;
    }


    public String add_exportingProcess_action() {
        getSessionBean1().setSelectedExportingProcessRowKey(null);
        getSessionBean1().setNewExportingProcess(true);
        getSessionBean1().setNewMethod(true);
        return "addExportingProcess";
    }


    public String edit_ExportingProcess_action() {
        RowKey selectedRow = this.getTableRowGroup4().getRowKey();
        getSessionBean1().setSelectedExportingProcessRowKey(selectedRow);
        getSessionBean1().setNewExportingProcess(false);
        getSessionBean1().setNewMethod(true);
        return "editExportingProcess";
    }


    public String remove_ExportingProcess_action() {
        RowKey selectedRow = this.getTableRowGroup4().getRowKey();
        try{
            getSessionBean1().getExportingDataProvider().removeObject(selectedRow);
        getSessionBean1().getExportingDataProvider().commitChanges();
        }catch(DataProviderException ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Can't remove row from Data Provider.");
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
        return "case1";
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
        return "case2";
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
        return "case3";
    }

    public String save_observationpoints_action() {
        
        List observationPointList = getSessionBean1().getObservationDataProvider().getList();
        try{
            getApplicationBean1().getAppObservationPointStorage().saveList(observationPointList);
        }
        catch(Exception ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Error writing to file!");
            this.alert1.setDetail(ex.getMessage());
            log("Error writing file!", ex);
        }
        this.alert1.setType("success");
        this.alert1.setSummary("Observation points saved");
        
        return null;
    }


    public String save_collecting_action() {
        String path = getApplicationBean1().getAppPath();
        List collectingProcessList = getSessionBean1().getCollectingDataProvider().getList();
        
        try{
            getApplicationBean1().getAppCollectingProcessStorage().saveList(collectingProcessList);
        }
        catch(Exception ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Error writing to file!");
            this.alert1.setDetail(ex.getMessage());
            log("Error writing to file!", ex);
        }
        this.alert1.setType("success");
        this.alert1.setSummary("Collecting Processes saved");
        
        return null;
    }


    public String save_metering_action() {
        
        List meteringProcessList = getSessionBean1().getMeteringDataProvider().getList();
        
        try{
        getApplicationBean1().getAppMeteringProcessStorage().saveList(meteringProcessList);
        }
        catch(Exception ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Error writing to file!");
            this.alert1.setDetail(ex.getMessage());
            log("Error writing to file!", ex);
        }
        this.alert1.setType("success");
        this.alert1.setSummary("Metering Processes saved");
        
        return null;
    }


    public String save_exporting_action() {
        String path = getApplicationBean1().getAppPath();
        List exportingProcessList = getSessionBean1().getCollectingDataProvider().getList();
        
        try{
            getApplicationBean1().getAppExportingProcessStorage().saveList(exportingProcessList);
        }
        catch(Exception ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Error writing to file!");
            this.alert1.setDetail(ex.getMessage());
        }
        this.alert1.setType("success");
        this.alert1.setSummary("Exporting Processes saved");
        
        return null;
    }


    public String save_config_button_action() {
                       
        Iterator observationPointsIterator = this.selectedObservationPoints.iterator();
        List observationPointsList = new ArrayList();       
        while(observationPointsIterator.hasNext()){
            String rowId = (String) observationPointsIterator.next();
            RowKey rowKey = getSessionBean1().getCollectingDataProvider().getRowKey(rowId);
            observationPointsList.add(getSessionBean1().getObservationDataProvider().getObject(rowKey));
           
        }
        
        Iterator collectingProcessIterator = this.selectedCollectingProcesses.iterator();
        List collectingProcessList = new ArrayList();
        while(collectingProcessIterator.hasNext()){
            String rowId = (String) collectingProcessIterator.next();
            RowKey rowKey = getSessionBean1().getCollectingDataProvider().getRowKey(rowId);
            collectingProcessList.add(getSessionBean1().getCollectingDataProvider().getObject(rowKey));            
        }
        
        Iterator meteringProcessIterator = this.selectedMeteringProcesses.iterator();
        List meteringProcessList = new ArrayList();
        while(meteringProcessIterator.hasNext()){
            String rowId = (String) meteringProcessIterator.next();
            RowKey rowKey = getSessionBean1().getMeteringDataProvider().getRowKey(rowId);
            meteringProcessList.add(getSessionBean1().getMeteringDataProvider().getObject(rowKey));
        }
        
        Iterator exportingProcessIterator = this.selectedExportingProcesses.iterator();
        List exportingProcessList = new ArrayList();
        while(exportingProcessIterator.hasNext()){
            String rowId = (String) exportingProcessIterator.next();
            RowKey rowKey = getSessionBean1().getMeteringDataProvider().getRowKey(rowId);
            exportingProcessList.add(getSessionBean1().getExportingDataProvider().getObject(rowKey));
            
        }
        String fileName = this.configInformation.getFileName();
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
        this.configInformation.setLastmodified(lastmodified);
        try{
        getApplicationBean1().getAppConfigInfoDataProvider().addObject(this.configInformation);
        getApplicationBean1().getAppConfigInfoDataProvider().commitChanges();
        }
        catch(DataProviderException ex){
            this.alert1.setType("error");
            this.alert1.setSummary("Problem with the Config Info Dataprovider.");
            this.alert1.setDetail(ex.getMessage());
            log("Problem with the Config Info Dataprovider.", ex);
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


    public void configName_validate(FacesContext context, UIComponent component, Object value) {
        String newConfigName = String.valueOf(value);
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


    public void fileName_validate(FacesContext context, UIComponent component, Object value) {
        String newFileName = String.valueOf(value);
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
    private Set selectedObservationPoints = new HashSet();
    /**
     * Getter for property selectedObservationPoint.
     * @return Value of property selectedObservationPoint.
     */
    public boolean isSelectedObservationPoint() {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData == null) return false;
        RowKey rowId= rowData.getTableRow();
        if(selectedObservationPoints.contains(rowId)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Setter for property selectedObservationPoint.
     * @param selectedObservationPoint New value of property selectedObservationPoint.
     */
    public void setSelectedObservationPoint(boolean selectedObservationPoint) {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData != null){
            String rowId = rowData.getTableRow().getRowId();
            if(selectedObservationPoint){
                this.selectedObservationPoints.add(rowId);
            } else {
                this.selectedObservationPoints.remove(rowId);
            }
        }
    }
    
    private Set selectedCollectingProcesses = new HashSet();
    
    /**
     * Getter for property selectedObservationPoint.
     * @return Value of property selectedObservationPoint.
     */
    public boolean isSelectedCollectingProcess() {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData == null) return false;
        String rowId = rowData.getTableRow().getRowId();
        if(selectedCollectingProcesses.contains(rowId)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Setter for property selectedObservationPoint.
     * @param selectedCollectingProcess New value of property selectedObservationPoint.
     */
    public void setSelectedCollectingProcess(boolean selectedCollectingProcess) {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData != null){
            String rowId = rowData.getTableRow().getRowId();
            if(selectedCollectingProcess){
                this.selectedCollectingProcesses.add(rowId);
            } else {
                this.selectedCollectingProcesses.remove(rowId);
            }
        }
    }
    
    private Set selectedMeteringProcesses = new HashSet();
    
    /**
     * Getter for property selectedObservationPoint.
     * @return Value of property selectedObservationPoint.
     */
    public boolean isSelectedMeteringProcess() {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData == null) return false;
        String rowId = rowData.getTableRow().getRowId();
        if(selectedMeteringProcesses.contains(rowId)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Setter for property selectedObservationPoint.
     * @param selectedMeteringProcess New value of property selectedObservationPoint.
     */
    public void setSelectedMeteringProcess(boolean selectedMeteringProcess) {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData != null){
            String rowId = rowData.getTableRow().getRowId();
            if(selectedMeteringProcess){
                this.selectedMeteringProcesses.add(rowId);
            } else {
                this.selectedMeteringProcesses.remove(rowId);
            }
        }
    }
    
    private Set selectedExportingProcesses = new HashSet();
    
    /**
     * Getter for property selectedObservationPoint.
     * @return Value of property selectedObservationPoint.
     */
    public boolean isSelectedExportingProcess() {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData == null) return false;
        String rowId = rowData.getTableRow().getRowId();
        if(selectedExportingProcesses.contains(rowId)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Setter for property selectedObservationPoint.
     * @param selectedExportingProcess New value of property selectedObservationPoint.
     */
    public void setSelectedExportingProcess(boolean selectedExportingProcess) {
        TableRowDataProvider rowData = (TableRowDataProvider) getBean("currentRow");
        if(rowData != null){
            String rowId = rowData.getTableRow().getRowId();
            if(selectedExportingProcess){
                this.selectedExportingProcesses.add(rowId);
            } else {
                this.selectedExportingProcesses.remove(rowId);
            }
        }
    }
    
    public static void removeNamespace(Element element) {
        
        Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
        
        if (element.getNamespace() == Namespace.NO_NAMESPACE) {
            element.setNamespace(ipfixConfigNS);
        }
        
        List childElements = element.getChildren();
        Iterator iterator = childElements.iterator();
        while (iterator.hasNext()) {
            Element child = (Element) iterator.next();
            removeNamespace(child);
        }
        
    }
}

