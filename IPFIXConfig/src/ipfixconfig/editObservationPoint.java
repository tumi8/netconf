/*
 * editObservationPoint.java
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
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Button;
import java.util.ArrayList;
import javax.faces.convert.IntegerConverter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editObservationPoint extends AbstractPageBean {
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

    private TextField domainIdField = new TextField();

    public TextField getDomainIdField() {
        return domainIdField;
    }

    public void setDomainIdField(TextField tf) {
        this.domainIdField = tf;
    }

    private TextField typeField = new TextField();

    public TextField getTypeField() {
        return typeField;
    }

    public void setTypeField(TextField tf) {
        this.typeField = tf;
    }

    private TextField pcapParameter = new TextField();

    public TextField getPcapParameter() {
        return pcapParameter;
    }

    public void setPcapParameter(TextField tf) {
        this.pcapParameter = tf;
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

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private TextField interface_field = new TextField();

    public TextField getInterface_field() {
        return interface_field;
    }

    public void setInterface_field(TextField tf) {
        this.interface_field = tf;
    }

    private Label id_label = new Label();

    public Label getId_label() {
        return id_label;
    }

    public void setId_label(Label l) {
        this.id_label = l;
    }

    private Label domain_label = new Label();

    public Label getDomain_label() {
        return domain_label;
    }

    public void setDomain_label(Label l) {
        this.domain_label = l;
    }

    private Label type_field = new Label();

    public Label getType_field() {
        return type_field;
    }

    public void setType_field(Label l) {
        this.type_field = l;
    }

    private Label pcap_label = new Label();

    public Label getPcap_label() {
        return pcap_label;
    }

    public void setPcap_label(Label l) {
        this.pcap_label = l;
    }

    private Label interface_label = new Label();

    public Label getInterface_label() {
        return interface_label;
    }

    public void setInterface_label(Label l) {
        this.interface_label = l;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editObservationPoint() {
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
    
    private RowKey ObservationPointKey;
    
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
            log("editObservationPoint Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        RowKey observationPointKey = getSessionBean1().getSelectedObservationPointRowKey();
        if(!getSessionBean1().isNewObservationPoint()){
            observationPoint = (ObservationPoint) getSessionBean1().getObservationDataProvider().getObject(observationPointKey);
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


    public String save_changes_action() {
        if(getSessionBean1().isNewObservationPoint()){
            try{
                getSessionBean1().getObservationDataProvider().addObject(this.observationPoint);
                getSessionBean1().getObservationDataProvider().commitChanges();
        
            }catch(DataProviderException ex){
                error("Problem with ObservationPointDataProvider");
                getSessionBean1().getObservationDataProvider().revertChanges();
        
            }
        }
        else{
            try{
                getSessionBean1().getObservationDataProvider().setObject(getSessionBean1().getSelectedObservationPointRowKey(), this.observationPoint);
                getSessionBean1().getObservationDataProvider().commitChanges();
        
            }catch(DataProviderException ex){
                error("Problem with ObservationPointDataProvider");
                getSessionBean1().getObservationDataProvider().revertChanges();
        
            }
        }
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
        
    }

    /**
     * Holds value of property observationPoint.
     */
    private ipfixconfig.ObservationPoint observationPoint = new ObservationPoint();

    /**
     * Getter for property observationPoint.
     * @return Value of property observationPoint.
     */
    public ipfixconfig.ObservationPoint getObservationPoint() {

        return this.observationPoint;
    }

    /**
     * Setter for property observationPoint.
     * @param observationPoint New value of property observationPoint.
     */
    public void setObservationPoint(ipfixconfig.ObservationPoint observationPoint) {

        this.observationPoint = observationPoint;
    }


    public String discard_changes_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
        
        
    }
}

