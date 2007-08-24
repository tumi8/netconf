/*
 * editFlowMetering.java
 *
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
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Button;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import java.util.List;
import javax.faces.convert.IntegerConverter;
import com.sun.rave.web.ui.component.Listbox;
import java.util.ArrayList;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editFlowMetering extends AbstractPageBean {
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

    private Button add_new_template_button = new Button();

    public Button getAdd_new_template_button() {
        return add_new_template_button;
    }

    public void setAdd_new_template_button(Button b) {
        this.add_new_template_button = b;
    }

    private Button save_all_changes_button = new Button();

    public Button getSave_all_changes_button() {
        return save_all_changes_button;
    }

    public void setSave_all_changes_button(Button b) {
        this.save_all_changes_button = b;
    }

    private Button discard_button = new Button();

    public Button getDiscard_button() {
        return discard_button;
    }

    public void setDiscard_button(Button b) {
        this.discard_button = b;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
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

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private TableColumn tableColumn13 = new TableColumn();

    public TableColumn getTableColumn13() {
        return tableColumn13;
    }

    public void setTableColumn13(TableColumn tc) {
        this.tableColumn13 = tc;
    }

    private StaticText staticText11 = new StaticText();

    public StaticText getStaticText11() {
        return staticText11;
    }

    public void setStaticText11(StaticText st) {
        this.staticText11 = st;
    }

    private TableColumn tableColumn14 = new TableColumn();

    public TableColumn getTableColumn14() {
        return tableColumn14;
    }

    public void setTableColumn14(TableColumn tc) {
        this.tableColumn14 = tc;
    }

    private StaticText staticText12 = new StaticText();

    public StaticText getStaticText12() {
        return staticText12;
    }

    public void setStaticText12(StaticText st) {
        this.staticText12 = st;
    }

    private Button change_template_button = new Button();

    public Button getChange_template_button() {
        return change_template_button;
    }

    public void setChange_template_button(Button b) {
        this.change_template_button = b;
    }

    private HtmlPanelGrid gridPanel2 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel2() {
        return gridPanel2;
    }

    public void setGridPanel2(HtmlPanelGrid hpg) {
        this.gridPanel2 = hpg;
    }

    private TextField active_timeout_field = new TextField();

    public TextField getActive_timeout_field() {
        return active_timeout_field;
    }

    public void setActive_timeout_field(TextField tf) {
        this.active_timeout_field = tf;
    }

    private DropDown active_timeout_unit = new DropDown();

    public DropDown getActive_timeout_unit() {
        return active_timeout_unit;
    }

    public void setActive_timeout_unit(DropDown dd) {
        this.active_timeout_unit = dd;
    }

    private TextField inactive_timeout = new TextField();

    public TextField getInactive_timeout() {
        return inactive_timeout;
    }

    public void setInactive_timeout(TextField tf) {
        this.inactive_timeout = tf;
    }

    private DropDown inactive_timeout_unit = new DropDown();

    public DropDown getInactive_timeout_unit() {
        return inactive_timeout_unit;
    }

    public void setInactive_timeout_unit(DropDown dd) {
        this.inactive_timeout_unit = dd;
    }

    private Button show_details = new Button();

    public Button getShow_details() {
        return show_details;
    }

    public void setShow_details(Button b) {
        this.show_details = b;
    }

    private Listbox rules = new Listbox();

    public Listbox getRules() {
        return rules;
    }

    public void setRules(Listbox l) {
        this.rules = l;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private HtmlPanelGrid gridPanel1 = new HtmlPanelGrid();

    public HtmlPanelGrid getGridPanel1() {
        return gridPanel1;
    }

    public void setGridPanel1(HtmlPanelGrid hpg) {
        this.gridPanel1 = hpg;
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
    public editFlowMetering() {
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
        
        flowMeteringData = (FlowMetering) getSessionBean1().getFlowMeteringData();
        List rulesList = flowMeteringData.getRulesList();
        int rulesCount = rulesList.size();
        ruleOptions = new Option[rulesList.size()];
         
        for(int i = 0; i < rulesCount; i++) {
            FlowMeteringRule currentRule = (FlowMeteringRule) rulesList.get(i);
            //Setze den Wert der Option auf den Index der Rule, da dies einfacher ist
            ruleOptions[i] = new Option(String.valueOf(i), "Template " + currentRule.getTemplateId());
        }
        
        if(rulesList.size() != 0){
            
            Integer selectedRule = Integer.valueOf(getSessionBean1().getRuleTemplate());
            FlowMeteringRule currentRule = (FlowMeteringRule) rulesList.get(selectedRule.intValue());
            rules.setSelected(selectedRule);
            getSessionBean1().getFlowkeyDataProvider().setList(currentRule.getFlowKeyList());
            getSessionBean1().getFlowkeyDataProvider().setUserResizable(true);
            getSessionBean1().getNonFlowKeyDataProvider().setList(currentRule.getNonFlowKeyList());
            getSessionBean1().getNonFlowKeyDataProvider().setUserResizable(true);
        }
        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("editFlowMetering Initialization Failure", e);
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
     * Holds value of property ruleOptions.
     */
    private com.sun.rave.web.ui.model.Option[] ruleOptions;

    /**
     * Getter for property ruleOptions.
     * @return Value of property ruleOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getRuleOptions() {

        return this.ruleOptions;
    }

    /**
     * Setter for property ruleOptions.
     * @param ruleOptions New value of property ruleOptions.
     */
    public void setRuleOptions(com.sun.rave.web.ui.model.Option[] ruleOptions) {

        this.ruleOptions = ruleOptions;
    }

    /**
     * Holds value of property flowMeteringData.
     */
    private ipfixconfig.FlowMetering flowMeteringData = new FlowMetering();

    /**
     * Getter for property flowMeteringData.
     * @return Value of property flowMeteringData.
     */
    public ipfixconfig.FlowMetering getFlowMeteringData() {

        return this.flowMeteringData;
    }

    /**
     * Setter for property flowMeteringData.
     * @param flowMeteringData New value of property flowMeteringData.
     */
    public void setFlowMeteringData(ipfixconfig.FlowMetering flowMeteringData) {

        this.flowMeteringData = flowMeteringData;
    }

  
    public String change_template_button_action() {
        String selected = (String) rules.getSelected();
        Integer selectedRule;
        if(selected != null){
            selectedRule = Integer.valueOf(selected);
            FlowMetering flowMeteringData = (FlowMetering) getSessionBean1().getFlowMeteringData();
            FlowMeteringRule flowMeteringRule = (FlowMeteringRule) flowMeteringData.getRulesList().get(selectedRule.intValue());
            getSessionBean1().getFlowkeyDataProvider().setList(flowMeteringRule.getFlowKeyList());
            getSessionBean1().getNonFlowKeyDataProvider().setList(flowMeteringRule.getNonFlowKeyList());
            getSessionBean1().setNewFlowMeteringRule(false);
            return "case1";
        }
        else{
            return null;
        }
                
    }
 
    public String show_details_action() {
        return null;
    }


    public String add_new_template_button_action() {
        getSessionBean1().getFlowkeyDataProvider().setList(new ArrayList());
        getSessionBean1().getNonFlowKeyDataProvider().setList(new ArrayList());
        getSessionBean1().setNewFlowMeteringRule(true);
        return "case2";
    }



    public String save_all_changes_button_action() {
        RowKey selectedRow = getSessionBean1().getSelectedMeteringProcessRowKey();
        
        if (selectedRow != null) {
            try {
                MeteringProcess editMeteringProcess = (MeteringProcess) getSessionBean1().getMeteringDataProvider().getObject(selectedRow);
                FlowMetering flowMeteringData = getSessionBean1().getFlowMeteringData();
                editMeteringProcess.setFlowMeteringData(flowMeteringData);
                Integer newId = getSessionBean1().getMeteringProcessId();
                editMeteringProcess.setId(newId);
                getSessionBean1().getMeteringDataProvider().setObject(selectedRow, editMeteringProcess);
                getSessionBean1().getMeteringDataProvider().commitChanges();
            }catch (Exception e) {
                error("Cannot find FlowMetering " + selectedRow.toString());
                log("Cannot find FlowMetering " + selectedRow.toString(), e);
            }
        } else {
            error("No FlowMetering for this available");
            log("No FlowMetering for this available");
        }
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }


    public String discard_button_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }
}

