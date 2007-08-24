/*
 * editFlowMeteringRule.java
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
import javax.faces.convert.IntegerConverter;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.MessageGroup;
import javax.faces.component.html.HtmlPanelGrid;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editFlowMeteringRule extends AbstractPageBean {
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

    private TextField template_id_field = new TextField();

    public TextField getTemplate_id_field() {
        return template_id_field;
    }

    public void setTemplate_id_field(TextField tf) {
        this.template_id_field = tf;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private TextField id_field = new TextField();

    public TextField getId_field() {
        return id_field;
    }

    public void setId_field(TextField tf) {
        this.id_field = tf;
    }

    private TextField enterprise_Nr_field = new TextField();

    public TextField getEnterprise_Nr_field() {
        return enterprise_Nr_field;
    }

    public void setEnterprise_Nr_field(TextField tf) {
        this.enterprise_Nr_field = tf;
    }

    private TextField name_field = new TextField();

    public TextField getName_field() {
        return name_field;
    }

    public void setName_field(TextField tf) {
        this.name_field = tf;
    }

    private TextField length_field = new TextField();

    public TextField getLength_field() {
        return length_field;
    }

    public void setLength_field(TextField tf) {
        this.length_field = tf;
    }

    private TextField match_field = new TextField();

    public TextField getMatch_field() {
        return match_field;
    }

    public void setMatch_field(TextField tf) {
        this.match_field = tf;
    }

    private TextField modifier_field = new TextField();

    public TextField getModifier_field() {
        return modifier_field;
    }

    public void setModifier_field(TextField tf) {
        this.modifier_field = tf;
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

    private Button add_new_flowkey_button = new Button();

    public Button getAdd_new_flowkey_button() {
        return add_new_flowkey_button;
    }

    public void setAdd_new_flowkey_button(Button b) {
        this.add_new_flowkey_button = b;
    }

    private Button add_new_nonflowkey_button = new Button();

    public Button getAdd_new_nonflowkey_button() {
        return add_new_nonflowkey_button;
    }

    public void setAdd_new_nonflowkey_button(Button b) {
        this.add_new_nonflowkey_button = b;
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

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
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

    private TableColumn tableColumn10 = new TableColumn();

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tc) {
        this.tableColumn10 = tc;
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

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }

    private Button save_changes_button = new Button();

    public Button getSave_changes_button() {
        return save_changes_button;
    }

    public void setSave_changes_button(Button b) {
        this.save_changes_button = b;
    }

    private Button discard_changes_button = new Button();

    public Button getDiscard_changes_button() {
        return discard_changes_button;
    }

    public void setDiscard_changes_button(Button b) {
        this.discard_changes_button = b;
    }

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private TableColumn tableColumn15 = new TableColumn();

    public TableColumn getTableColumn15() {
        return tableColumn15;
    }

    public void setTableColumn15(TableColumn tc) {
        this.tableColumn15 = tc;
    }

    private TableColumn tableColumn16 = new TableColumn();

    public TableColumn getTableColumn16() {
        return tableColumn16;
    }

    public void setTableColumn16(TableColumn tc) {
        this.tableColumn16 = tc;
    }

    private Button edit_nonflowkey_button = new Button();

    public Button getEdit_nonflowkey_button() {
        return edit_nonflowkey_button;
    }

    public void setEdit_nonflowkey_button(Button b) {
        this.edit_nonflowkey_button = b;
    }

    private Button remove_nonflowkey_button = new Button();

    public Button getRemove_nonflowkey_button() {
        return remove_nonflowkey_button;
    }

    public void setRemove_nonflowkey_button(Button b) {
        this.remove_nonflowkey_button = b;
    }

    private Button edit_flowkey_button = new Button();

    public Button getEdit_flowkey_button() {
        return edit_flowkey_button;
    }

    public void setEdit_flowkey_button(Button b) {
        this.edit_flowkey_button = b;
    }

    private Button remove_flowkey_button = new Button();

    public Button getRemove_flowkey_button() {
        return remove_flowkey_button;
    }

    public void setRemove_flowkey_button(Button b) {
        this.remove_flowkey_button = b;
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

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editFlowMeteringRule() {
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
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        if(!getSessionBean1().isNewFlowMeteringRule()){
            Integer selectedRule = Integer.valueOf(getSessionBean1().getRuleTemplate());
            FlowMetering flowMeteringData = (FlowMetering) getSessionBean1().getFlowMeteringData();
            flowMeteringRule = (FlowMeteringRule) flowMeteringData.getRulesList().get(selectedRule.intValue());
        }
        
        
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("editFlowMeteringRule Initialization Failure", e);
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
     * Holds value of property flowMeteringRule.
     */
    private ipfixconfig.FlowMeteringRule flowMeteringRule = new FlowMeteringRule();

    /**
     * Getter for property flowMeteringRule.
     * @return Value of property flowMeteringRule.
     */
    public ipfixconfig.FlowMeteringRule getFlowMeteringRule() {

        return this.flowMeteringRule;
    }

    /**
     * Setter for property flowMeteringRule.
     * @param flowMeteringRule New value of property flowMeteringRule.
     */
    public void setFlowMeteringRule(ipfixconfig.FlowMeteringRule flowMeteringRule) {

        this.flowMeteringRule = flowMeteringRule;
    }
      
    /**
     * Holds value of property newKey.
     */
    private ipfixconfig.InformationElement newKey = new InformationElement();

    /**
     * Getter for property newKey.
     * @return Value of property newKey.
     */
    public ipfixconfig.InformationElement getNewKey() {

        return this.newKey;
    }

    /**
     * Setter for property newKey.
     * @param newKey New value of property newKey.
     */
    public void setNewKey(ipfixconfig.InformationElement newKey) {

        this.newKey = newKey;
    }


    public String add_new_flowkey_button_action() {
        if(getSessionBean1().isNewKey()){
            try{
                getSessionBean1().getFlowkeyDataProvider().setObject(getSessionBean1().getSelectedRowKey(), this.newKey);
                getSessionBean1().getFlowkeyDataProvider().commitChanges();
            } catch(DataProviderException dataProvEx){
                getSessionBean1().getFlowkeyDataProvider().revertChanges();
                error("FlowkeyDataProvider problem");
            }
        } else{
            try{
                getSessionBean1().getFlowkeyDataProvider().addObject(this.newKey);
                getSessionBean1().getFlowkeyDataProvider().commitChanges();
            } catch(DataProviderException dataProvEx){
                getSessionBean1().getFlowkeyDataProvider().revertChanges();
                error("FlowkeyDataProvider problem");
            }
        }
        getSessionBean1().setNewKey(false);
        return null;
    }


    public String add_new_nonflowkey_button_action() {
        if(getSessionBean1().isNewKey()){
            try{
            getSessionBean1().getNonFlowKeyDataProvider().setObject(getSessionBean1().getSelectedRowKey(), this.newKey);
            getSessionBean1().getNonFlowKeyDataProvider().commitChanges();
        }
        catch(DataProviderException dataProvEx){
            getSessionBean1().getNonFlowKeyDataProvider().revertChanges();
            error("NonFlowkeyDataProvider problem");
        }
        }else{
            try{
            getSessionBean1().getNonFlowKeyDataProvider().addObject(this.newKey);
            getSessionBean1().getNonFlowKeyDataProvider().commitChanges();
        }
        catch(DataProviderException dataProvEx){
            getSessionBean1().getNonFlowKeyDataProvider().revertChanges();
            error("NonFlowkeyDataProvider problem");
        }
        }
        
        getSessionBean1().setNewKey(false);
        return null;
    }


    public String save_changes_button_action() {
        if(getSessionBean1().isNewFlowMeteringRule()){
            this.flowMeteringRule.setFlowKeyList(getSessionBean1().getFlowkeyDataProvider().getList());
            this.flowMeteringRule.setNonFlowKeyList(getSessionBean1().getNonFlowKeyDataProvider().getList());
            getSessionBean1().getFlowMeteringData().getRulesList().add(this.flowMeteringRule);
            getSessionBean1().setRuleTemplate(String.valueOf(getSessionBean1().getFlowMeteringData().getRulesList().size() - 1));
        }
        else{
            Integer selectedRule = Integer.valueOf(getSessionBean1().getRuleTemplate());
            this.flowMeteringRule.setFlowKeyList(getSessionBean1().getFlowkeyDataProvider().getList());
            this.flowMeteringRule.setNonFlowKeyList(getSessionBean1().getNonFlowKeyDataProvider().getList());
            getSessionBean1().getFlowMeteringData().getRulesList().set(selectedRule.intValue(), this.flowMeteringRule);
            
        }
        getSessionBean1().setNewKey(false);
        return "case3";
    }


    public String edit_flowkey_button_action() {
        RowKey selectedRow = getTableRowGroup1().getRowKey();
        newKey = (InformationElement) getSessionBean1().getFlowkeyDataProvider().getObject(selectedRow);
        getSessionBean1().setSelectedRowKey(selectedRow);
        getSessionBean1().setNewKey(true);
        return null;
    }



    public String edit_nonflowkey_button_action() {
        RowKey selectedRow = getTableRowGroup2().getRowKey();
        newKey = (InformationElement) getSessionBean1().getNonFlowKeyDataProvider().getObject(selectedRow);
        getSessionBean1().setSelectedRowKey(selectedRow);
        getSessionBean1().setNewKey(true);
        return null;
    }


    public String remove_flowkey_button_action() {
        RowKey selectedRow = getTableRowGroup1().getRowKey();
        try{
            getSessionBean1().getFlowkeyDataProvider().removeObject(selectedRow);
            getSessionBean1().getFlowkeyDataProvider().commitChanges();
        }
        catch(DataProviderException dataProvEx){
            getSessionBean1().getFlowkeyDataProvider().revertChanges();
            error("FlowkeyDataProvider problem");
        }
        
        return null;
    }


    public String remove_nonflowkey_button_action() {
        RowKey selectedRow = getTableRowGroup2().getRowKey();
        try{
            getSessionBean1().getNonFlowKeyDataProvider().removeObject(selectedRow);
            getSessionBean1().getNonFlowKeyDataProvider().commitChanges();
        }
        catch(DataProviderException dataProvEx){
            getSessionBean1().getNonFlowKeyDataProvider().revertChanges();
            error("FlowkeyDataProvider problem");
        }
        
        return null;
    }
}

