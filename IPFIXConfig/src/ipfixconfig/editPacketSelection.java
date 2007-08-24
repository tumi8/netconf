/*
 * editPacketSelection.java
 *
 * 
 * Copyright Max
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
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.model.Option;
import java.util.List;
import com.sun.rave.web.ui.component.TextField;
import javax.faces.convert.IntegerConverter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editPacketSelection extends AbstractPageBean {
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

    private StaticText display_Details = new StaticText();

    public StaticText getDisplay_Details() {
        return display_Details;
    }

    public void setDisplay_Details(StaticText st) {
        this.display_Details = st;
    }

    private DropDown new_packet_selection_dropdown = new DropDown();

    public DropDown getNew_packet_selection_dropdown() {
        return new_packet_selection_dropdown;
    }

    public void setNew_packet_selection_dropdown(DropDown dd) {
        this.new_packet_selection_dropdown = dd;
    }

    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private Listbox listbox1 = new Listbox();

    public Listbox getListbox1() {
        return listbox1;
    }

    public void setListbox1(Listbox l) {
        this.listbox1 = l;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    private Button edit_selected = new Button();

    public com.sun.rave.web.ui.component.Button getEdit_selected() {
        return edit_selected;
    }

    public void setEdit_selected(com.sun.rave.web.ui.component.Button b) {
        this.edit_selected = b;
    }

    private Button remove_selected = new Button();

    public Button getRemove_selected() {
        return remove_selected;
    }

    public void setRemove_selected(Button b) {
        this.remove_selected = b;
    }

    private Button save_changes = new Button();

    public Button getSave_changes() {
        return save_changes;
    }

    public void setSave_changes(Button b) {
        this.save_changes = b;
    }

    private Button discard_changes = new Button();

    public Button getDiscard_changes() {
        return discard_changes;
    }

    public void setDiscard_changes(Button b) {
        this.discard_changes = b;
    }
    
    // </editor-fold>
    
    private Button show_details = new Button();

    public Button getShow_details() {
        return show_details;
    }

    public void setShow_details(Button b) {
        this.show_details = b;
    }

    private Button add_new_packet_selection = new Button();

    public Button getAdd_new_packet_selection() {
        return add_new_packet_selection;
    }

    public void setAdd_new_packet_selection(Button b) {
        this.add_new_packet_selection = b;
    }

    private TextField meteringProcess_id = new TextField();

    public TextField getMeteringProcess_id() {
        return meteringProcess_id;
    }

    public void setMeteringProcess_id(TextField tf) {
        this.meteringProcess_id = tf;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private Button up_button = new Button();

    public Button getUp_button() {
        return up_button;
    }

    public void setUp_button(Button b) {
        this.up_button = b;
    }

    private Button down_button = new Button();

    public Button getDown_button() {
        return down_button;
    }

    public void setDown_button(Button b) {
        this.down_button = b;
    }
 public editPacketSelection() {
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
            log("editPacketSelection Initialization Failure", e);
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
        PacketSelection packetSelectionToEdit = getSessionBean1().getPacketSelectionData();
        if(packetSelectionToEdit != null){
        List selectionMethods = packetSelectionToEdit.getPacketSelectionTypesList();
        
        Option[] sMethodOptions = new Option[selectionMethods.size()];
        
        for(int i = 0; i < selectionMethods.size(); i++){
            packetSelectionType currentSelectionType = (packetSelectionType) selectionMethods.get(i);
            
            sMethodOptions[i] = new Option(String.valueOf(i), currentSelectionType.getName(), currentSelectionType.getInformation());
        }
 
        this.setSelectionMethodOptions(sMethodOptions);
        }
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
     * Holds value of property selectionMethodOptions.
     */
    private com.sun.rave.web.ui.model.Option[] selectionMethodOptions = new Option[]{};

    /**
     * Getter for property selectionMethodOptions.
     * @return Value of property selectionMethodOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getSelectionMethodOptions() {

        return this.selectionMethodOptions;
    }

    /**
     * Setter for property selectionMethodOptions.
     * @param MethodOptions New value of property selectionMethodOptions.
     */
    public void setSelectionMethodOptions(com.sun.rave.web.ui.model.Option[] MethodOptions) {

        this.selectionMethodOptions = MethodOptions;
    }

    /**
     * Holds value of property selectedMethod.
     */
    private String selectedMethod;

    /**
     * Getter for property selectedMethod.
     * @return Value of property selectedMethod.
     */
    public String getSelectedMethod() {

        return this.selectedMethod;
    }

    /**
     * Setter for property selectedMethod.
     * @param selectedMethod New value of property selectedMethod.
     */
    public void setSelectedMethod(String selectedMethod) {

        this.selectedMethod = selectedMethod;
    }


    public String save_changes_action() {
        RowKey selectedRow = getSessionBean1().getSelectedMeteringProcessRowKey();
        
        if (selectedRow != null) {
            try {
                MeteringProcess editMeteringProcess = (MeteringProcess) getSessionBean1().getMeteringDataProvider().getObject(selectedRow);
                PacketSelection packetSelectionData = getSessionBean1().getPacketSelectionData();
                editMeteringProcess.setPacketSelectionData(packetSelectionData);
                Integer newId = getSessionBean1().getMeteringProcessId();
                editMeteringProcess.setId(newId);
                getSessionBean1().getMeteringDataProvider().setObject(selectedRow, editMeteringProcess);
                getSessionBean1().getMeteringDataProvider().commitChanges();
            }catch (Exception e) {
                error("Cannot find PacketSelection " + selectedRow.toString());
                log("Cannot find PacketSelection " + selectedRow.toString(), e);
            }
        } else {
            error("No packetSelection for this available");
            log("No packetSelection for this available");
        }
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }

    /**
     * Holds value of property selectedMethodDetails.
     */
    private String selectedMethodDetails = new String("");

    /**
     * Getter for property selectedMethodDetails.
     * @return Value of property selectedMethodDetails.
     */
    public String getSelectedMethodDetails() {
        
        return this.selectedMethodDetails;
    }


    public String edit_selected_action() {
        String selectedPage = null;
        String selected = "";
        if(selectedMethod != null){
            int selectedNr = Integer.parseInt(selectedMethod);
            packetSelectionType method = (packetSelectionType) getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().get(selectedNr);
            selectedPage = method.getType();
            getSessionBean1().setSelectedMethodNr(selectedNr);
            getSessionBean1().setNewMethod(false);
            getSessionBean1().setNewFilterMatch(true);
        }
        
        return selectedPage;
    }


    public String show_details_action() {
        
        if(selectedMethod != null){
       
            int selectedNr = Integer.parseInt(selectedMethod);
            
                packetSelectionType method = (packetSelectionType) getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().get(selectedNr);
                selectedMethodDetails = method.getInformation();
            }
        return null;
    }

    /**
     * Holds value of property selectedRow.
     */
    private Boolean selectedRow;

    /**
     * Getter for property selectedRow.
     * @return Value of property selectedRow.
     */
    public Boolean isSelectedRow() {

        return this.selectedRow;
    }

    /**
     * Setter for property selectedRow.
     * @param selectedRow New value of property selectedRow.
     */
    public void setSelectedRow(Boolean selectedRow) {

        this.selectedRow = selectedRow;
    }


    public String add_new_packet_selection_action() {
        String selectedPage = (String) getNew_packet_selection_dropdown().getSelected();
        getSessionBean1().setNewMethod(true);
        getSessionBean1().setNewFilterMatch(true);
        return selectedPage;
    }


    public String remove_selected_action() {
               
        if(selectedMethod != null){
            int selectedNr = Integer.parseInt(selectedMethod);
            getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().remove(selectedNr);
        }
                
        return null;
    }


    public String discard_changes_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }


    public String up_button_action() {
        
        if(selectedMethod != null){
            int selectedNr = Integer.parseInt(selectedMethod);
            //int listSize = getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().size();
            if(selectedNr - 1 >= 0){
                List packetSelectionList = getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList();
                packetSelectionType selectedMethod = (packetSelectionType) packetSelectionList.get(selectedNr);
                packetSelectionType upperMethod = (packetSelectionType) packetSelectionList.get(selectedNr - 1);
                packetSelectionList.set(selectedNr - 1, selectedMethod);
                packetSelectionList.set(selectedNr, upperMethod);
                getSessionBean1().getPacketSelectionData().setPacketSelectionTypesList(packetSelectionList);
            }
        }
        
        return null;
    }


    public String down_button_action() {
        if(selectedMethod != null){
            int selectedNr = Integer.parseInt(selectedMethod);
            int listSize = getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList().size();
            if(selectedNr + 1 < listSize){
                List packetSelectionList = getSessionBean1().getPacketSelectionData().getPacketSelectionTypesList();
                packetSelectionType selectedMethod = (packetSelectionType) packetSelectionList.get(selectedNr);
                packetSelectionType lowerMethod = (packetSelectionType) packetSelectionList.get(selectedNr + 1);
                packetSelectionList.set(selectedNr + 1, selectedMethod);
                packetSelectionList.set(selectedNr, lowerMethod);
                getSessionBean1().getPacketSelectionData().setPacketSelectionTypesList(packetSelectionList);
            }
        }
        return null;
    }
}

