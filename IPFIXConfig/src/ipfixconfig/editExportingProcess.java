/*
 * editExportingProcess.java
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
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Label;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.convert.IntegerConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
import javax.faces.validator.LongRangeValidator;
import javax.faces.event.ValueChangeEvent;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editExportingProcess extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        longRangeValidator1.setMaximum(65536L);
        longRangeValidator1.setMinimum(0L);
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

    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }

    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
    }

    private Button save_changes1 = new Button();

    public Button getSave_changes1() {
        return save_changes1;
    }

    public void setSave_changes1(Button b) {
        this.save_changes1 = b;
    }

    private Button add_collector = new Button();

    public Button getAdd_collector() {
        return add_collector;
    }

    public void setAdd_collector(Button b) {
        this.add_collector = b;
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

    private DropDown ipAdresstypeField1 = new DropDown();

    public DropDown getIpAdresstypeField1() {
        return ipAdresstypeField1;
    }

    public void setIpAdresstypeField1(DropDown dd) {
        this.ipAdresstypeField1 = dd;
    }

    private TextField ipAdressField1 = new TextField();

    public TextField getIpAdressField1() {
        return ipAdressField1;
    }

    public void setIpAdressField1(TextField tf) {
        this.ipAdressField1 = tf;
    }

    private DropDown transportProtocolField1 = new DropDown();

    public DropDown getTransportProtocolField1() {
        return transportProtocolField1;
    }

    public void setTransportProtocolField1(DropDown dd) {
        this.transportProtocolField1 = dd;
    }

    private TextField portField1 = new TextField();

    public TextField getPortField1() {
        return portField1;
    }

    public void setPortField1(TextField tf) {
        this.portField1 = tf;
    }

    private Button discard1 = new Button();

    public Button getDiscard1() {
        return discard1;
    }

    public void setDiscard1(Button b) {
        this.discard1 = b;
    }

    private HtmlPanelGrid gridPanel3 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel3() {
        return gridPanel3;
    }

    public void setGridPanel3(HtmlPanelGrid hpg) {
        this.gridPanel3 = hpg;
    }

    private TextField idField1 = new TextField();

    public TextField getIdField1() {
        return idField1;
    }

    public void setIdField1(TextField tf) {
        this.idField1 = tf;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private TextField refreshtimeout = new TextField();

    public TextField getRefreshtimeout() {
        return refreshtimeout;
    }

    public void setRefreshtimeout(TextField tf) {
        this.refreshtimeout = tf;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private DropDown timeout_unit = new DropDown();

    public DropDown getTimeout_unit() {
        return timeout_unit;
    }

    public void setTimeout_unit(DropDown dd) {
        this.timeout_unit = dd;
    }

    private TextField refreshrate = new TextField();

    public TextField getRefreshrate() {
        return refreshrate;
    }

    public void setRefreshrate(TextField tf) {
        this.refreshrate = tf;
    }

    private TextField maxpacketsize = new TextField();

    public TextField getMaxpacketsize() {
        return maxpacketsize;
    }

    public void setMaxpacketsize(TextField tf) {
        this.maxpacketsize = tf;
    }

    private TextField maxExportDelay = new TextField();

    public TextField getMaxExportDelay() {
        return maxExportDelay;
    }

    public void setMaxExportDelay(TextField tf) {
        this.maxExportDelay = tf;
    }

    private DropDown exportdelay_unit = new DropDown();

    public DropDown getExportdelay_unit() {
        return exportdelay_unit;
    }

    public void setExportdelay_unit(DropDown dd) {
        this.exportdelay_unit = dd;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
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

    private Button edit_collector = new Button();

    public Button getEdit_collector() {
        return edit_collector;
    }

    public void setEdit_collector(Button b) {
        this.edit_collector = b;
    }

    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }

    private Button remove_collector = new Button();

    public Button getRemove_collector() {
        return remove_collector;
    }

    public void setRemove_collector(Button b) {
        this.remove_collector = b;
    }

    private LongRangeValidator longRangeValidator1 = new LongRangeValidator();

    public LongRangeValidator getLongRangeValidator1() {
        return longRangeValidator1;
    }

    public void setLongRangeValidator1(LongRangeValidator lrv) {
        this.longRangeValidator1 = lrv;
    }

    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editExportingProcess() {
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
        RowKey exportingProcessKey = getSessionBean1().getSelectedExportingProcessRowKey();
        if(!getSessionBean1().isNewExportingProcess()){
            exportingProcess = (ExportingProcess) getSessionBean1().getExportingDataProvider().getObject(exportingProcessKey);
        }
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("editExportingProcess Initialization Failure", e);
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


    public String save_changes_action() {
        if(getSessionBean1().isNewExportingProcess()){
            try{
                //Einmal commitChanges, weil sonst die Aenderungen verloren gehen.
                getSessionBean1().getReceivingCollectorsDataProvider().commitChanges();
                List selectedCollectorList = getSessionBean1().getReceivingCollectorsDataProvider().getList();
                this.exportingProcess.setCollectorList(selectedCollectorList);
                getSessionBean1().getExportingDataProvider().addObject(this.exportingProcess);
                getSessionBean1().getExportingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Error setting changes in the DataProvider.");
                log("Error setting changes in the DataProvider.");
                getSessionBean1().getExportingDataProvider().revertChanges();
                
            }
        } else{
            try{
                //Einmal commitChanges, weil sonst die Aenderungen verloren gehen.
                getSessionBean1().getListenerDataProvider().commitChanges();
                List selectedListenerList = getSessionBean1().getListenerDataProvider().getList();
                this.exportingProcess.setCollectorList(selectedListenerList);
                RowKey exportingProcessKey = getSessionBean1().getSelectedExportingProcessRowKey();
                getSessionBean1().getExportingDataProvider().setObject(exportingProcessKey, this.exportingProcess);
                getSessionBean1().getExportingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Error setting changes in the DataProvider.");
                log("Error setting changes in the DataProvider.");
                getSessionBean1().getExportingDataProvider().revertChanges();
            }
        }
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }


    public String add_Listener_action() {
        if(getSessionBean1().isNewMethod()){
            try{
            getSessionBean1().getReceivingCollectorsDataProvider().addObject(this.collector);
            getSessionBean1().getReceivingCollectorsDataProvider().commitChanges();
        } catch (DataProviderException e) {
            error("Error adding new Listener!");
            log("Error adding new Listener!");
            getSessionBean1().getReceivingCollectorsDataProvider().revertChanges();
        }
        }else{
            try{
            getSessionBean1().getReceivingCollectorsDataProvider().setObject(this.selectedRow, this.collector);
            getSessionBean1().getReceivingCollectorsDataProvider().commitChanges();
        } catch (DataProviderException e) {
            error("Error adding new Listener!");
            log("Error adding new Listener!");
            getSessionBean1().getReceivingCollectorsDataProvider().revertChanges();
        }
        }
        this.collector = new Collector();
        getSessionBean1().setNewMethod(true);
        return null;
    }

    public String discard_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }

    /**
     * Holds value of property exportingProcess.
     */
    private ipfixconfig.ExportingProcess exportingProcess = new ExportingProcess();

    /**
     * Getter for property exportingProcess.
     * @return Value of property exportingProcess.
     */
    public ipfixconfig.ExportingProcess getExportingProcess() {

        return this.exportingProcess;
    }

    /**
     * Setter for property exportingProcess.
     * @param exportingProcess New value of property exportingProcess.
     */
    public void setExportingProcess(ipfixconfig.ExportingProcess exportingProcess) {

        this.exportingProcess = exportingProcess;
    }

    /**
     * Holds value of property collector.
     */
    private ipfixconfig.Collector collector = new Collector();

    /**
     * Getter for property collector.
     * @return Value of property collector.
     */
    public ipfixconfig.Collector getCollector() {

        return this.collector;
    }

    /**
     * Setter for property collector.
     * @param collector New value of property collector.
     */
    public void setCollector(ipfixconfig.Collector collector) {

        this.collector = collector;
    }

    /**
     * Holds value of property selectedRow.
     */
    private com.sun.data.provider.RowKey selectedRow;

    /**
     * Getter for property selectedRow.
     * @return Value of property selectedRow.
     */
    public com.sun.data.provider.RowKey getSelectedRow() {

        return this.selectedRow;
    }

    /**
     * Setter for property selectedRow.
     * @param selectedRow New value of property selectedRow.
     */
    public void setSelectedRow(com.sun.data.provider.RowKey selectedRow) {

        this.selectedRow = selectedRow;
    }


    public String edit_collector_action() {
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        this.collector = (Collector) getSessionBean1().getReceivingCollectorsDataProvider().getObject(selectedRowKey);
        this.setSelectedRow(selectedRowKey);
        getSessionBean1().setNewMethod(false);
        return null;
    }


    public String remove_collector_action() {
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        if(selectedRowKey != null){
            try{
                getSessionBean1().getReceivingCollectorsDataProvider().removeObject(selectedRowKey);
                getSessionBean1().getReceivingCollectorsDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem mith dem Collector Data Provider.");
                getSessionBean1().getReceivingCollectorsDataProvider().revertChanges();
                
            }
        }
        getSessionBean1().setNewMethod(true);
        return null;
    }


    public void ipAdressField1_validate(FacesContext context, UIComponent component, Object value) {
        if(!this.ipAdresstypeField1.getValue().equals("41")){ // Wenn es eine IPv6 Adresse nicht prüfen.
            String address = String.valueOf(value);
            StringTokenizer addressTokens = new StringTokenizer(address, ".");
            boolean wrongByte = false;
            while(addressTokens.hasMoreTokens()){
                String aByte = addressTokens.nextToken();
                if(Integer.parseInt(aByte) < 0 || Integer.parseInt(aByte) > 255) wrongByte = true;
            }
            if(wrongByte) throw new ValidatorException(new FacesMessage("Not a valid IP Address."));
        }
    }

}

