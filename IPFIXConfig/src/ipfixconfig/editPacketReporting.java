/*
 * newPage.java
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
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.Button;
import javax.faces.convert.IntegerConverter;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.MessageGroup;
import java.util.List;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editPacketReporting extends AbstractPageBean {
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

    private TextField templateid_field = new TextField();

    public TextField getTemplateid_field() {
        return templateid_field;
    }

    public void setTemplateid_field(TextField tf) {
        this.templateid_field = tf;
    }

    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }

    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
    }

    private TextField id_field1 = new TextField();

    public TextField getId_field1() {
        return id_field1;
    }

    public void setId_field1(TextField tf) {
        this.id_field1 = tf;
    }

    private TextField enterpriseNr_field1 = new TextField();

    public TextField getEnterpriseNr_field1() {
        return enterpriseNr_field1;
    }

    public void setEnterpriseNr_field1(TextField tf) {
        this.enterpriseNr_field1 = tf;
    }

    private TextField length_field1 = new TextField();

    public TextField getLength_field1() {
        return length_field1;
    }

    public void setLength_field1(TextField tf) {
        this.length_field1 = tf;
    }

    private TextField name_field1 = new TextField();

    public TextField getName_field1() {
        return name_field1;
    }

    public void setName_field1(TextField tf) {
        this.name_field1 = tf;
    }

    private TextField match_field1 = new TextField();

    public TextField getMatch_field1() {
        return match_field1;
    }

    public void setMatch_field1(TextField tf) {
        this.match_field1 = tf;
    }

    private TextField modifier_field1 = new TextField();

    public TextField getModifier_field1() {
        return modifier_field1;
    }

    public void setModifier_field1(TextField tf) {
        this.modifier_field1 = tf;
    }

    private Button save_new_rule1 = new Button();

    public Button getSave_new_rule1() {
        return save_new_rule1;
    }

    public void setSave_new_rule1(Button b) {
        this.save_new_rule1 = b;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
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

    private Button remove_button = new Button();

    public Button getRemove_button() {
        return remove_button;
    }

    public void setRemove_button(Button b) {
        this.remove_button = b;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }

    private Button save_all_button = new Button();

    public Button getSave_all_button() {
        return save_all_button;
    }

    public void setSave_all_button(Button b) {
        this.save_all_button = b;
    }

    private Button discard_button = new Button();

    public Button getDiscard_button() {
        return discard_button;
    }

    public void setDiscard_button(Button b) {
        this.discard_button = b;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private Button edit_button = new Button();

    public Button getEdit_button() {
        return edit_button;
    }

    public void setEdit_button(Button b) {
        this.edit_button = b;
    }

    private TextField meteringProcess_id1 = new TextField();

    public TextField getMeteringProcess_id1() {
        return meteringProcess_id1;
    }

    public void setMeteringProcess_id1(TextField tf) {
        this.meteringProcess_id1 = tf;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editPacketReporting() {
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
                
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("newPage Initialization Failure", e);
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


    public String save_new_rule_action() {
        if(getSessionBean1().isNewMethod()){
            try{
                getSessionBean1().getSesPacketReportingDataProvider().addObject(this.reportedIE);
                getSessionBean1().getSesPacketReportingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem with the PacketReporting Data Provider.");
                getSessionBean1().getSesPacketReportingDataProvider().revertChanges();
                throw e;
            }
        }else{
            try{
                getSessionBean1().getSesPacketReportingDataProvider().setObject(getSessionBean1().getSelectedRowKey(), this.reportedIE);
                getSessionBean1().getSesPacketReportingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem with the PacketReporting Data Provider.");
                getSessionBean1().getSesPacketReportingDataProvider().revertChanges();
                throw e;
            }
        }
        this.reportedIE = new InformationElement();
        getSessionBean1().setNewMethod(true);
        return null;
    }

    /**
     * Holds value of property reportedIE.
     */
    private ipfixconfig.InformationElement reportedIE = new InformationElement();

    /**
     * Getter for property reportedIE.
     * @return Value of property reportedIE.
     */
    public ipfixconfig.InformationElement getReportedIE() {

        return this.reportedIE;
    }

    /**
     * Setter for property reportedIE.
     * @param reportedIE New value of property reportedIE.
     */
    public void setReportedIE(ipfixconfig.InformationElement reportedIE) {

        this.reportedIE = reportedIE;
    }


    public String remove_button_action() {
        
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        if(selectedRowKey != null){
            try{
                getSessionBean1().getSesPacketReportingDataProvider().removeRow(selectedRowKey);
                getSessionBean1().getSesPacketReportingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem with the Packet Reporting Data Provider.");
                getSessionBean1().getSesPacketReportingDataProvider().revertChanges();
                
            }
        }
        return null;
    }


    public String save_all_button_action() {
        RowKey selectedRow = getSessionBean1().getSelectedMeteringProcessRowKey();
        List changedList = getSessionBean1().getSesPacketReportingDataProvider().getList();
        getSessionBean1().getPacketReportingData().setPacketReportingIEList(changedList);
        if (selectedRow != null) {
            try {
                MeteringProcess editMeteringProcess = (MeteringProcess) getSessionBean1().getMeteringDataProvider().getObject(selectedRow);
                PacketReporting packetReportingData = getSessionBean1().getPacketReportingData();
                editMeteringProcess.setPacketReportingData(packetReportingData);
                Integer newId = getSessionBean1().getMeteringProcessId();
                editMeteringProcess.setId(newId);
                getSessionBean1().getMeteringDataProvider().setObject(selectedRow, editMeteringProcess);
                getSessionBean1().getMeteringDataProvider().commitChanges();
            }catch (Exception e) {
                error("Cannot find PacketReporting " + selectedRow.toString());
                log("Cannot find PacketReporting " + selectedRow.toString(), e);
            }
        } else {
            error("No packetReporting for this available");
            log("No packetReporting for this available");
        }
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }


    public String edit_button_action() {
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        this.reportedIE = (InformationElement) getSessionBean1().getSesPacketReportingDataProvider().getObject(selectedRowKey);
        getSessionBean1().setNewMethod(false);
        getSessionBean1().setSelectedRowKey(selectedRowKey);
        
        return null;
    }


    public String discard_button_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }
}

