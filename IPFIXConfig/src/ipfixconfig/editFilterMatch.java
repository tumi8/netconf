/*
 * editFilterMatch.java
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
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.TextField;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.StaticText;
import javax.faces.convert.IntegerConverter;
import com.sun.rave.web.ui.component.MessageGroup;
import java.util.List;
import com.sun.rave.web.ui.model.DefaultTableDataProvider;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editFilterMatch extends AbstractPageBean {
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
    
    private Button save_all_changes = new Button();
    
    public Button getSave_all_changes() {
        return save_all_changes;
    }
    
    public void setSave_all_changes(Button b) {
        this.save_all_changes = b;
    }
    
    private Button discard_all_changes = new Button();
    
    public Button getDiscard_all_changes() {
        return discard_all_changes;
    }
    
    public void setDiscard_all_changes(Button b) {
        this.discard_all_changes = b;
    }
    
    private ObjectListDataProvider objectListDataProvider1 = new ObjectListDataProvider();
    
    public ObjectListDataProvider getObjectListDataProvider1() {
        return objectListDataProvider1;
    }
    
    public void setObjectListDataProvider1(ObjectListDataProvider oldp) {
        this.objectListDataProvider1 = oldp;
    }
    
    private Button save_new_rule = new Button();
    
    public Button getSave_new_rule() {
        return save_new_rule;
    }
    
    public void setSave_new_rule(Button b) {
        this.save_new_rule = b;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }

    private TextField id_field = new TextField();

    public TextField getId_field() {
        return id_field;
    }

    public void setId_field(TextField tf) {
        this.id_field = tf;
    }

    private TextField enterpriseNr = new TextField();

    public TextField getEnterpriseNr() {
        return enterpriseNr;
    }

    public void setEnterpriseNr(TextField tf) {
        this.enterpriseNr = tf;
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

    private Button remove_button = new Button();

    public Button getRemove_button() {
        return remove_button;
    }

    public void setRemove_button(Button b) {
        this.remove_button = b;
    }
    
    // </editor-fold>
    
    
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public editFilterMatch() {
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
    
    
    private FilterMatchSelection selectedMatch;
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
            log("editFilterMatch Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        int selectedMethodNr = getSessionBean1().getSelectedMethodNr();
        if(!getSessionBean1().isNewMethod()){
            selectedMatch = (FilterMatchSelection) getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().get(selectedMethodNr);
            getSessionBean1().getSesFilterMatchDataProvider().setList(selectedMatch.getFilterMatchIEList());
        }
        
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
        if(getSessionBean1().isNewFilterMatch()){
            try{
                getSessionBean1().getSesFilterMatchDataProvider().addObject(this.filterMatchRule);
                getSessionBean1().getSesFilterMatchDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem with the Filter Match Data Provider.");
                getSessionBean1().getSesFilterMatchDataProvider().revertChanges();
                throw e;
            }
        }
        else{
            try{
                getSessionBean1().getSesFilterMatchDataProvider().setObject(getSessionBean1().getSelectedRowKey(), this.filterMatchRule);
                getSessionBean1().getSesFilterMatchDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem with the Filter Match Data Provider.");
                getSessionBean1().getSesFilterMatchDataProvider().revertChanges();
                throw e;
            }
        }
        this.filterMatchRule = new InformationElement();
        getSessionBean1().setNewFilterMatch(true);
        return null;
    }
    
    
    public String save_all_changes_action() {
        List changedList = getSessionBean1().getSesFilterMatchDataProvider().getList();
        
        if(!getSessionBean1().isNewMethod()){
            int selectedMethodNr = getSessionBean1().getSelectedMethodNr();
            FilterMatchSelection changedMatchSel = (FilterMatchSelection) getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().get(selectedMethodNr);
            changedMatchSel.setFilterMatchIEList(changedList);
            getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().set(selectedMethodNr, changedMatchSel);
        }
        else{
            FilterMatchSelection changedMatchSel = new FilterMatchSelection();
            changedMatchSel.setFilterMatchIEList(changedList);
            getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().add(changedMatchSel);
        }
        getSessionBean1().setNewFilterMatch(false);
        return "case1";
    }
    
    /**
     * Holds value of property filterMatchSelection.
     */
    private ipfixconfig.InformationElement filterMatchRule = new InformationElement();

    /**
     * Getter for property filterMatchSelection.
     * @return Value of property filterMatchSelection.
     */
    public ipfixconfig.InformationElement getFilterMatchRule() {

        return this.filterMatchRule;
    }

    /**
     * Setter for property filterMatchSelection.
     * @param filterMatchRule New value of property filterMatchRule.
     */
    public void setFilterMatchRule(ipfixconfig.InformationElement filterMatchRule) {

        this.filterMatchRule = filterMatchRule;
    }


    public String edit_button_action() {
        RowKey selectedRow = getTableRowGroup1().getRowKey();
        filterMatchRule = (InformationElement) getSessionBean1().getSesFilterMatchDataProvider().getObject(selectedRow);
        getSessionBean1().setSelectedRowKey(selectedRow);
        getSessionBean1().setNewFilterMatch(false);
        
        return null;
         
    }


    public String remove_button_action() {
        
        RowKey selectedRowKey = getTableRowGroup1().getRowKey();
        if(selectedRowKey != null){
            try{
                getSessionBean1().getSesFilterMatchDataProvider().removeRow(selectedRowKey);
                getSessionBean1().getSesFilterMatchDataProvider().commitChanges();
            } catch (DataProviderException e) {
                error("Problem with the Filter Match Data Provider.");
                getSessionBean1().getSesFilterMatchDataProvider().revertChanges();
                throw e;
            }
        }
         
        
        return null;
    }
}

