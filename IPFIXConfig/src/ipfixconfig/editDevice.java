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
import com.sun.rave.web.ui.component.PasswordField;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.model.Option;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.convert.IntegerConverter;
import javax.faces.validator.LongRangeValidator;
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
public class editDevice extends AbstractPageBean {
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
    
    private Label label1 = new Label();
    
    public Label getLabel1() {
        return label1;
    }
    
    public void setLabel1(Label l) {
        this.label1 = l;
    }
    
    private TextField name_field = new TextField();
    
    public TextField getName_field() {
        return name_field;
    }
    
    public void setName_field(TextField tf) {
        this.name_field = tf;
    }
    
    private TextField type_field = new TextField();
    
    public TextField getType_field() {
        return type_field;
    }
    
    public void setType_field(TextField tf) {
        this.type_field = tf;
    }
    
    private TextField host_field = new TextField();
    
    public TextField getHost_field() {
        return host_field;
    }
    
    public void setHost_field(TextField tf) {
        this.host_field = tf;
    }
    
    private TextField user_field = new TextField();
    
    public TextField getUser_field() {
        return user_field;
    }
    
    public void setUser_field(TextField tf) {
        this.user_field = tf;
    }
    
    private Label label2 = new Label();
    
    public Label getLabel2() {
        return label2;
    }
    
    public void setLabel2(Label l) {
        this.label2 = l;
    }
    
    private Label label3 = new Label();
    
    public Label getLabel3() {
        return label3;
    }
    
    public void setLabel3(Label l) {
        this.label3 = l;
    }
    
    private PasswordField password_field = new PasswordField();
    
    public PasswordField getPassword_field() {
        return password_field;
    }
    
    public void setPassword_field(PasswordField pf) {
        this.password_field = pf;
    }
    
    private TextField netconf_role_field = new TextField();
    
    public TextField getNetconf_role_field() {
        return netconf_role_field;
    }
    
    public void setNetconf_role_field(TextField tf) {
        this.netconf_role_field = tf;
    }
    
    private Label label4 = new Label();
    
    public Label getLabel4() {
        return label4;
    }
    
    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    private TextField monitored_network_field = new TextField();
    
    public TextField getMonitored_network_field() {
        return monitored_network_field;
    }
    
    public void setMonitored_network_field(TextField tf) {
        this.monitored_network_field = tf;
    }
    
    private Button save_button = new Button();
    
    public Button getSave_button() {
        return save_button;
    }
    
    public void setSave_button(Button b) {
        this.save_button = b;
    }
    
    private Button discard_button = new Button();
    
    public Button getDiscard_button() {
        return discard_button;
    }
    
    public void setDiscard_button(Button b) {
        this.discard_button = b;
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();
    
    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }
    
    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    private TextArea descript_textarea = new TextArea();
    
    public TextArea getDescript_textarea() {
        return descript_textarea;
    }
    
    public void setDescript_textarea(TextArea ta) {
        this.descript_textarea = ta;
    }
    
    private Label label5 = new Label();
    
    public Label getLabel5() {
        return label5;
    }
    
    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private DropDown roleSelect = new DropDown();

    public DropDown getRoleSelect() {
        return roleSelect;
    }

    public void setRoleSelect(DropDown dd) {
        this.roleSelect = dd;
    }

    private StaticText roleN = new StaticText();

    public StaticText getRoleN() {
        return roleN;
    }

    public void setRoleN(StaticText st) {
        this.roleN = st;
    }

    private HtmlPanelGrid basicPanel = new HtmlPanelGrid();

    public HtmlPanelGrid getBasicPanel() {
        return basicPanel;
    }

    public void setBasicPanel(HtmlPanelGrid hpg) {
        this.basicPanel = hpg;
    }

    private HtmlPanelGrid connectionPanel = new HtmlPanelGrid();

    public HtmlPanelGrid getConnectionPanel() {
        return connectionPanel;
    }

    public void setConnectionPanel(HtmlPanelGrid hpg) {
        this.connectionPanel = hpg;
    }

    private TextField port_field = new TextField();

    public TextField getPort_field() {
        return port_field;
    }

    public void setPort_field(TextField tf) {
        this.port_field = tf;
    }

    private IntegerConverter integerConverter1 = new IntegerConverter();

    public IntegerConverter getIntegerConverter1() {
        return integerConverter1;
    }

    public void setIntegerConverter1(IntegerConverter ic) {
        this.integerConverter1 = ic;
    }

    private HtmlPanelGrid informationPanel = new HtmlPanelGrid();

    public HtmlPanelGrid getInformationPanel() {
        return informationPanel;
    }

    public void setInformationPanel(HtmlPanelGrid hpg) {
        this.informationPanel = hpg;
    }

    private LongRangeValidator longRangeValidator1 = new LongRangeValidator();

    public LongRangeValidator getLongRangeValidator1() {
        return longRangeValidator1;
    }

    public void setLongRangeValidator1(LongRangeValidator lrv) {
        this.longRangeValidator1 = lrv;
    }

    private HtmlPanelGrid agent_panel = new HtmlPanelGrid();

    public HtmlPanelGrid getAgent_panel() {
        return agent_panel;
    }

    public void setAgent_panel(HtmlPanelGrid hpg) {
        this.agent_panel = hpg;
    }

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private TextField netconf_Namespace = new TextField();

    public TextField getNetconf_Namespace() {
        return netconf_Namespace;
    }

    public void setNetconf_Namespace(TextField tf) {
        this.netconf_Namespace = tf;
    }

    private TextField path = new TextField();

    public TextField getPath() {
        return path;
    }

    public void setPath(TextField tf) {
        this.path = tf;
    }

    private Label label8 = new Label();

    public Label getLabel8() {
        return label8;
    }

    public void setLabel8(Label l) {
        this.label8 = l;
    }
    
    // </editor-fold>
    
    
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public editDevice() {
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
    
    private Integer roleId;
    
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
        
        if(!getSessionBean1().isNewDevice()){
            RowKey selectedDevice = getSessionBean1().getSelectedRowKey();
            this.device = (Device) getApplicationBean1().getAppDeviceDataProvider().getObject(selectedDevice);
            
            List roleList = getApplicationBean1().getAppRoleDataProvider().getList();
            
            List possibleRoles = new ArrayList();
            Iterator rolesIterator = roleList.iterator();
            while(rolesIterator.hasNext()){
                Role currentRole = (Role) rolesIterator.next();
                HashMap roleMap = currentRole.getMapping();
                /*
                 * Nur wenn die Rolle überhaupt eine Konfiguration für das Geräte spezifiziert,
                 * wird diese als mögliche Rolle angezeigt.
                 */
                if(roleMap.containsKey(this.device.getName())){
                    possibleRoles.add(currentRole);
                }
                
            }
            /*
             * Als erstes existiert immer die None Option, wenn keine Rolle gesetzt ist.
             */
            if(possibleRoles.size() != 0){
                roleOptions = new Option[possibleRoles.size() + 1 ];
                roleOptions[0] = new Option("None","None");
                for(int i = 0; i < possibleRoles.size() ; i++){
                    Role actualRole = (Role) possibleRoles.get(i);
                    roleOptions[i + 1] = new Option(actualRole.getName(), actualRole.getName());
                }
            } else{
                roleOptions = new Option[]{new Option("None","None")};
            }
            
            
        } else{
            roleOptions = new Option[]{new Option("None","None")};
            
        }
        
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
    
    
    /**
     * Holds value of property device.
     */
    private ipfixconfig.Device device = new Device();
    
    /**
     * Getter for property device.
     * @return Value of property device.
     */
    public ipfixconfig.Device getDevice() {
        
        return this.device;
    }
    
    /**
     * Setter for property device.
     * @param device New value of property device.
     */
    public void setDevice(ipfixconfig.Device device) {
        
        this.device = device;
    }
    
    
    public String save_button_action() {
        if(!getSessionBean1().isNewDevice()){
            
            RowKey selectedDevice = getSessionBean1().getSelectedRowKey();
            try{
                getApplicationBean1().getAppDeviceDataProvider().setObject(selectedDevice, this.device);
                getApplicationBean1().getAppDeviceDataProvider().commitChanges();
            } catch(DataProviderException ex){
                error("Problem with the Device Data Provider.");
                getApplicationBean1().getAppDeviceDataProvider().revertChanges();
                throw ex;
            }
        } else{
            try{
                
                getApplicationBean1().getAppDeviceDataProvider().addObject(this.device);
                getApplicationBean1().getAppDeviceDataProvider().commitChanges();
            } catch(DataProviderException ex){
                error("Problem with the Device Data Provider.");
                getApplicationBean1().getAppDeviceDataProvider().revertChanges();
                throw ex;
            }
        }
        
        List deviceList = getApplicationBean1().getAppDeviceDataProvider().getList();
                
        try{
            getApplicationBean1().getAppDeviceStorage().saveList(deviceList);
        }
        catch(Exception ex){
            error("Error writing to Storagefile!" + ex.getMessage());
            log("Error writing to Storagefile!", ex);
        }
        return "case1";
    }
    
    private String roleName;
    /**
     * Getter for property roleName.
     * @return Value of property roleName.
     */
    public String getRoleName() {
        
        return this.roleName;
    }
    
    /**
     * Holds value of property roleDescription.
     */
    private String roleDescription = "";
    
    /**
     * Getter for property roleDescription.
     * @return Value of property roleDescription.
     */
    public String getRoleDescription() {
        
        return this.roleDescription;
    }
    
    
    public void host_field_validate(FacesContext context, UIComponent component, Object value) {
        String address = String.valueOf(value);
        StringTokenizer addressTokens = new StringTokenizer(address, ".");
        boolean wrongByte = false;
        while(addressTokens.hasMoreTokens()){
            String aByte = addressTokens.nextToken();
            if(Integer.parseInt(aByte) < 0 || Integer.parseInt(aByte) > 255) wrongByte = true;
        }
        if(wrongByte) throw new ValidatorException(new FacesMessage("Not a valid IP Address."));
        
    }

    /**
     * Holds value of property roleOptions.
     */
    private com.sun.rave.web.ui.model.Option[] roleOptions;

    /**
     * Getter for property roleOptions.
     * @return Value of property roleOptions.
     */
    public com.sun.rave.web.ui.model.Option[] getRoleOptions() {

        return this.roleOptions;
    }

    /**
     * Setter for property roleOptions.
     * @param roleOptions New value of property roleOptions.
     */
    public void setRoleOptions(com.sun.rave.web.ui.model.Option[] roleOptions) {

        this.roleOptions = roleOptions;
    }


    public String selectRole_action() {
        
        
        return null;
    }


    public void name_field_validate(FacesContext context, UIComponent component, Object value) {
        String newDeviceName = String.valueOf(value);
        if(!newDeviceName.equals(getSessionBean1().getOriginalDeviceName())){
        List deviceList = getApplicationBean1().getAppDeviceDataProvider().getList();
        Iterator listIterator = deviceList.iterator();
        ArrayList nameList = new ArrayList(deviceList.size());
        while(listIterator.hasNext()){
        Device currentDevice = (Device) listIterator.next();
        nameList.add(currentDevice.getName());
        }
        if(nameList.contains(newDeviceName)) throw new ValidatorException(new FacesMessage
		("Devicename is already used! Please choose a different name."));
        }
        
    }

}

