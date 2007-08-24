/*
 * editCollectingProcess.java
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
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import java.util.List;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.MessageGroup;
import java.util.StringTokenizer;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.convert.IntegerConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
import javax.faces.validator.LongRangeValidator;
import com.sun.rave.web.ui.component.Message;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editCollectingProcess extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        longRangeValidator1.setMaximum(65535L);
        longRangeValidator1.setMinimum(1L);
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
    
    private Label label2 = new Label();
    
    public Label getLabel2() {
        return label2;
    }
    
    public void setLabel2(Label l) {
        this.label2 = l;
    }
    
    private Button save_changes = new Button();
    
    public Button getSave_changes() {
        return save_changes;
    }
    
    public void setSave_changes(Button b) {
        this.save_changes = b;
    }
    
    private Button discard = new Button();
    
    public Button getDiscard() {
        return discard;
    }
    
    public void setDiscard(Button b) {
        this.discard = b;
    }
    
    private Button add_Listener = new Button();
    
    public Button getAdd_Listener() {
        return add_Listener;
    }
    
    public void setAdd_Listener(Button b) {
        this.add_Listener = b;
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();
    
    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }
    
    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();
    
    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }
    
    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
    }
    
    private TextField idField = new TextField();
    
    public TextField getIdField() {
        return idField;
    }
    
    public void setIdField(TextField tf) {
        this.idField = tf;
    }
    
    private TextField UDPLifetimeField = new TextField();
    
    public TextField getUDPLifetimeField() {
        return UDPLifetimeField;
    }
    
    public void setUDPLifetimeField(TextField tf) {
        this.UDPLifetimeField = tf;
    }
    
    private DropDown UDPUnitField = new DropDown();
    
    public DropDown getUDPUnitField() {
        return UDPUnitField;
    }
    
    public void setUDPUnitField(DropDown dd) {
        this.UDPUnitField = dd;
    }
    
    private IntegerConverter integerConverter1 = new IntegerConverter();
    
    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }
    
    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }
    
    private IntegerConverter integerConverter2 = new IntegerConverter();
    
    public IntegerConverter getIntegerConverter2() {
        return integerConverter2;
    }
    
    public void setIntegerConverter2(IntegerConverter ic) {
        this.integerConverter2 = ic;
    }
    
    private HtmlPanelGrid gridPanel2 = new HtmlPanelGrid();
    
    public HtmlPanelGrid getGridPanel2() {
        return gridPanel2;
    }
    
    public void setGridPanel2(HtmlPanelGrid hpg) {
        this.gridPanel2 = hpg;
    }
    
    private DropDown ipAdresstypeField = new DropDown();
    
    public DropDown getIpAdresstypeField() {
        return ipAdresstypeField;
    }
    
    public void setIpAdresstypeField(DropDown dd) {
        this.ipAdresstypeField = dd;
    }
    
    private TextField ipAdressField = new TextField();
    
    public TextField getIpAdressField() {
        return ipAdressField;
    }
    
    public void setIpAdressField(TextField tf) {
        this.ipAdressField = tf;
    }
    
    private DropDown transportProtocolField = new DropDown();
    
    public DropDown getTransportProtocolField() {
        return transportProtocolField;
    }
    
    public void setTransportProtocolField(DropDown dd) {
        this.transportProtocolField = dd;
    }
    
    private TextField portField = new TextField();
    
    public TextField getPortField() {
        return portField;
    }
    
    public void setPortField(TextField tf) {
        this.portField = tf;
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
    
    private Button edit_Listener = new Button();
    
    public Button getEdit_Listener() {
        return edit_Listener;
    }
    
    public void setEdit_Listener(Button b) {
        this.edit_Listener = b;
    }
    
    private TableColumn tableColumn6 = new TableColumn();
    
    public TableColumn getTableColumn6() {
        return tableColumn6;
    }
    
    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }
    
    private Button remove_Listener = new Button();
    
    public Button getRemove_Listener() {
        return remove_Listener;
    }
    
    public void setRemove_Listener(Button b) {
        this.remove_Listener = b;
    }

    private LongRangeValidator longRangeValidator1 = new LongRangeValidator();

    public LongRangeValidator getLongRangeValidator1() {
        return longRangeValidator1;
    }

    public void setLongRangeValidator1(LongRangeValidator lrv) {
        this.longRangeValidator1 = lrv;
    }

    private TextField domainId_field = new TextField();

    public TextField getDomainId_field() {
        return domainId_field;
    }

    public void setDomainId_field(TextField tf) {
        this.domainId_field = tf;
    }

    private Message message1 = new Message();

    public Message getMessage1() {
        return message1;
    }

    public void setMessage1(Message m) {
        this.message1 = m;
    }
    
    // </editor-fold>
    
    
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public editCollectingProcess() {
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
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1)getBean("RequestBean1");
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
    
    private RowKey collectingProcessKey;
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        collectingProcessKey = getSessionBean1().getSelectedCollectingProcessRowKey();
        if(!getSessionBean1().isNewCollectingProcess()){
            collectingProcess = (CollectingProcess) getSessionBean1().getCollectingDataProvider().getObject(collectingProcessKey);
        }
        
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("editCollectingProcess Initialization Failure", e);
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
    
        
    public String discard_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
        
    }
    
    
    public String add_Listener_action() {
        if(getSessionBean1().isNewMethod()){
            try{
            getSessionBean1().getListenerDataProvider().addObject(this.listener);
            getSessionBean1().getListenerDataProvider().commitChanges();
        } catch (DataProviderException e) {
            error("Error adding new Listener!");
            log("Error adding new Listener!");
            getSessionBean1().getListenerDataProvider().revertChanges();
        }
        }
        else{
            try{
            getSessionBean1().getListenerDataProvider().setObject(getSessionBean1().getSelectedRowKey(), this.listener);
            getSessionBean1().getListenerDataProvider().commitChanges();
        } catch (DataProviderException e) {
            error("Error adding new Listener!");
            log("Error adding new Listener!");
            getSessionBean1().getListenerDataProvider().revertChanges();
        }
        }
        this.listener = new Collector();
        getSessionBean1().setNewMethod(true);
        return null;
    }
    
    
    public String save_changes_action() {
               
        if(getSessionBean1().isNewCollectingProcess()){
            try{
                //Einmal commitChanges, weil sonst die Aenderungen verloren gehen.
                getSessionBean1().getListenerDataProvider().commitChanges();
                List selectedListenerList = getSessionBean1().getListenerDataProvider().getList();
                this.collectingProcess.setListenerList(selectedListenerList);
                getSessionBean1().getCollectingDataProvider().addObject(this.collectingProcess);
                getSessionBean1().getCollectingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Error setting changes in the DataProvider.");
                log("Error setting changes in the DataProvider.");
                getSessionBean1().getCollectingDataProvider().revertChanges();
                
            }
        } else{
            try{
                //Einmal commitChanges, weil sonst die Aenderungen verloren gehen.
                getSessionBean1().getListenerDataProvider().commitChanges();
                List selectedListenerList = getSessionBean1().getListenerDataProvider().getList();
                this.collectingProcess.setListenerList(selectedListenerList);
                RowKey collectingProcessKey = getSessionBean1().getSelectedCollectingProcessRowKey();
                getSessionBean1().getCollectingDataProvider().setObject(collectingProcessKey, this.collectingProcess);
                getSessionBean1().getCollectingDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Error setting changes in the DataProvider.");
                log("Error setting changes in the DataProvider.");
                getSessionBean1().getCollectingDataProvider().revertChanges();
            }
        }
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }
    
     
    /**
     * Holds value of property collectingProcess.
     */
    private ipfixconfig.CollectingProcess collectingProcess = new CollectingProcess();
    
    /**
     * Getter for property collectingProcess.
     * @return Value of property collectingProcess.
     */
    public ipfixconfig.CollectingProcess getCollectingProcess() {
        
        return this.collectingProcess;
    }
    
    /**
     * Setter for property collectingProcess.
     * @param collectingProcess New value of property collectingProcess.
     */
    public void setCollectingProcess(ipfixconfig.CollectingProcess collectingProcess) {
        
        this.collectingProcess = collectingProcess;
    }
    
    /**
     * Holds value of property listener.
     */
    private ipfixconfig.Collector listener = new Collector();
    
    /**
     * Getter for property listener.
     * @return Value of property listener.
     */
    public ipfixconfig.Collector getListener() {
        
        return this.listener;
    }
    
    /**
     * Setter for property listener.
     * @param listener New value of property listener.
     */
    public void setListener(ipfixconfig.Collector listener) {
        
        this.listener = listener;
    }


    public String edit_Listener_action() {
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        this.listener = (Collector) getSessionBean1().getListenerDataProvider().getObject(selectedRowKey);
        getSessionBean1().setNewMethod(false);
        getSessionBean1().setSelectedRowKey(selectedRowKey);
        return null;
    }


    public String remove_Listener_action() {
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        if(selectedRowKey != null){
            try{
                getSessionBean1().getListenerDataProvider().removeObject(selectedRowKey);
                getSessionBean1().getListenerDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem mith dem Collector Data Provider.");
                getSessionBean1().getListenerDataProvider().revertChanges();
            }
        }
        return null;
    }

    public void ipAdressField_validate(FacesContext context, UIComponent component, Object value) {
        if(!this.ipAdresstypeField.getValue().equals("41")){ // If it is a IPv6 address don't check.
        String address = String.valueOf(value);
        StringTokenizer addressTokens = new StringTokenizer(address, ".");
        boolean wrongByte = false;
        while(addressTokens.hasMoreTokens()){
            String aByte = addressTokens.nextToken();
            if(Integer.parseInt(aByte) < 0 || Integer.parseInt(aByte) > 255) wrongByte = true;
        }
        if(wrongByte) throw new ValidatorException(new FacesMessage ("Not a valid IP Address."));
        }
    }
    
}

