/*
 * editScenario.java
 * 
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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.AddRemove;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.model.Option;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editScenario extends AbstractPageBean {
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

    private TextField name = new TextField();

    public TextField getName() {
        return name;
    }

    public void setName(TextField tf) {
        this.name = tf;
    }

    private TextArea description = new TextArea();

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea ta) {
        this.description = ta;
    }

    private AddRemove addRemoveList1 = new AddRemove();

    public AddRemove getAddRemoveList1() {
        return addRemoveList1;
    }

    public void setAddRemoveList1(AddRemove ar) {
        this.addRemoveList1 = ar;
    }

    private Button save = new Button();

    public Button getSave() {
        return save;
    }

    public void setSave(Button b) {
        this.save = b;
    }

    private Button discard = new Button();

    public Button getDiscard() {
        return discard;
    }

    public void setDiscard(Button b) {
        this.discard = b;
    }
    
    // </editor-fold>


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editScenario() {
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
        
            RowKey selectedScenario = getSessionBean1().getSelectedRowKey();
            this.scenario = (Scenario) getApplicationBean1().getAppScenarioDataProvider().getObject(selectedScenario);
            List selectedDeviceList = this.scenario.getDeviceList();
            this.selectedDevices = new String[selectedDeviceList.size()];
            for(int i = 0; i < selectedDeviceList.size(); i++){
            this.selectedDevices[i] = (String) selectedDeviceList.get(i);
            }
        
        List deviceList = getApplicationBean1().getAppDeviceDataProvider().getList();
        int deviceCount = deviceList.size();
        
        this.devices = new Option[deviceCount];
        for(int i = 0; i < deviceCount; i = i + 1){
                Device currentDevice = (Device) deviceList.get(i);
                this.devices[i] = new Option(currentDevice.getName(), currentDevice.getName(), currentDevice.getDescription());
        }

        // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("editScenario Initialization Failure", e);
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
     * Holds value of property scenario.
     */
    private ipfixconfig.Scenario scenario = new Scenario();

    /**
     * Getter for property scenario.
     * @return Value of property scenario.
     */
    public ipfixconfig.Scenario getScenario() {

        return this.scenario;
    }

    /**
     * Setter for property scenario.
     * @param scenario New value of property scenario.
     */
    public void setScenario(ipfixconfig.Scenario scenario) {

        this.scenario = scenario;
    }

    public String discard_action() {
        
        return "discard";
    }

    /**
     * Holds value of property selectedDevices.
     */
    private String[] selectedDevices = new String[]{};

    /**
     * Getter for property selectedDevices.
     * @return Value of property selectedDevices.
     */
    public String[] getSelectedDevices() {

        return this.selectedDevices;
    }

    /**
     * Setter for property selectedDevices.
     * @param selectedDevices New value of property selectedDevices.
     */
    public void setSelectedDevices(String[] selectedDevices) {

        this.selectedDevices = selectedDevices;
    }


    public String save_action() {
        
        this.scenario.setDeviceList(Arrays.asList(selectedDevices));
        RowKey selectedScenario = getSessionBean1().getSelectedRowKey();
        getApplicationBean1().getAppScenarioDataProvider().setObject(selectedScenario, this.scenario);
        getApplicationBean1().getAppScenarioDataProvider().commitChanges();
               
        List scenarioList = getApplicationBean1().getAppScenarioDataProvider().getList();
        
        try{
            getApplicationBean1().getAppScenarioStorage().saveList(scenarioList);
        }
        catch(Exception ex){
            error("Error writing to file!");
            log("Error writing to file!", ex);
        }
        
        return "save";
    }
    
    public void name_validate(FacesContext context, UIComponent component, Object value) {
        String newScenarioName = String.valueOf(value);
        if(!newScenarioName.equals(getSessionBean1().getOriginalScenarioName())){
        List scenarioList = getApplicationBean1().getAppScenarioDataProvider().scenarioList;
        Iterator listIterator = scenarioList.iterator();
        ArrayList nameList = new ArrayList(scenarioList.size());
        while(listIterator.hasNext()){
        Scenario currentScenario = (Scenario) listIterator.next();
        nameList.add(currentScenario.getName());
        }
        if(nameList.contains(newScenarioName)) throw new ValidatorException(new FacesMessage
		("Scenario name is already used! Please choose a different name."));
        }
        
    }

    /**
     * Holds value of property devices.
     */
    private com.sun.rave.web.ui.model.Option[] devices;

    /**
     * Getter for property devices.
     * @return Value of property devices.
     */
    public com.sun.rave.web.ui.model.Option[] getDevices() {

        return this.devices;
    }

    /**
     * Setter for property devices.
     * @param devices New value of property devices.
     */
    public void setDevices(com.sun.rave.web.ui.model.Option[] devices) {

        this.devices = devices;
    }
}

