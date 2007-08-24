/*
 * uploadConfig.java
 *
 * 
 * Copyright max
 */
package ipfixconfig;

import com.sun.data.provider.DataProviderException;
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
import com.sun.rave.web.ui.component.Message;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.Upload;
import javax.faces.event.ValueChangeEvent;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.model.UploadedFile;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class uploadConfig extends AbstractPageBean {
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

    private TextField configName = new TextField();

    public TextField getConfigName() {
        return configName;
    }

    public void setConfigName(TextField tf) {
        this.configName = tf;
    }

    private Message message1 = new Message();

    public Message getMessage1() {
        return message1;
    }

    public void setMessage1(Message m) {
        this.message1 = m;
    }

    private TextArea description = new TextArea();

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea ta) {
        this.description = ta;
    }

    private Upload fileUpload1 = new Upload();

    public Upload getFileUpload1() {
        return fileUpload1;
    }

    public void setFileUpload1(Upload u) {
        this.fileUpload1 = u;
    }

    private Button uploadFileButton = new Button();

    public Button getUploadFileButton() {
        return uploadFileButton;
    }

    public void setUploadFileButton(Button b) {
        this.uploadFileButton = b;
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
    public uploadConfig() {
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
            log("uploadConfig Initialization Failure", e);
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

    public String uploadFileButton_action() {
        UploadedFile uploadedFile = fileUpload1.getUploadedFile();
        String uploadedFileName = uploadedFile.getOriginalName();
        
        int index = uploadedFileName.lastIndexOf('/');
        String justFileName;
        if ( index >= 0) {
            justFileName = uploadedFileName.substring( index + 1 );
        } else {
            // Try backslash
            index = uploadedFileName.lastIndexOf('\\');
            if (index >= 0) {
                justFileName = uploadedFileName.substring( index + 1 );
            } else {
                // No forward or back slashes
                justFileName = uploadedFileName;
            }
        }
        
        String uploadedFileType = uploadedFile.getContentType();
        
        if ( uploadedFileType.equals("text/xml")){
            
            List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
            Iterator listIterator = configInfoList.iterator();
            ArrayList fileNameList = new ArrayList(configInfoList.size());
            while(listIterator.hasNext()){
                ConfigInfo currentConfigInfo = (ConfigInfo) listIterator.next();
                fileNameList.add(currentConfigInfo.getFileName());
            }
            
            if(fileNameList.contains(justFileName)){
                error("Filename is already used! Please choose a different filename and try again.");
                return null;
            } else{
                try {
                    
                    File file = new File(getApplicationBean1().getAppPath() + File.separatorChar +
                            justFileName);
                    uploadedFile.write(file);
                } catch (Exception ex) {
                    error("Cannot upload file: " + justFileName);
                    return null;
                }
            }
        } else {
            error("Not a XML file.");
            return null;
        }
        this.configInformation.setFileName(justFileName);
        Date now = new Date();
        DateFormat formatDate = DateFormat.getDateInstance();
        String lastmodified = formatDate.format(now);
        this.configInformation.setLastmodified(lastmodified);
        try{
        getApplicationBean1().getAppConfigInfoDataProvider().addObject(this.configInformation);
        getApplicationBean1().getAppConfigInfoDataProvider().commitChanges();
        }catch(DataProviderException ex){
            error("Dataprovider error! ");
        }
        
        List listToSave = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        try{
            getApplicationBean1().getAppConfigInfoStorage().saveList(listToSave);
        
        }catch(Exception ex){
            error("Error writing Storagefile!" + ex.getMessage());
            log("Error writinh Storagefile!", ex);
        }
        return "case1";
    }
    
    public void configName_validate(FacesContext context, UIComponent component, Object value) {
        String newConfigName = String.valueOf(value);
        List configInfoList = getApplicationBean1().getAppConfigInfoDataProvider().getList();
        Iterator listIterator = configInfoList.iterator();
        ArrayList nameList = new ArrayList(configInfoList.size());
        while(listIterator.hasNext()){
            ConfigInfo currentConfigInfo = (ConfigInfo) listIterator.next();
            nameList.add(currentConfigInfo.getName());
        }
        if(nameList.contains(newConfigName)) throw new ValidatorException(new FacesMessage
                ("Configurationname is already used! Please choose a different name."));
    }

    /**
     * Holds value of property configInformation.
     */
    private ipfixconfig.ConfigInfo configInformation = new ConfigInfo();

    /**
     * Getter for property configInformation.
     * @return Value of property configInformation.
     */
    public ipfixconfig.ConfigInfo getConfigInformation() {

        return this.configInformation;
    }

    /**
     * Setter for property configInformation.
     * @param configInformation New value of property configInformation.
     */
    public void setConfigInformation(ipfixconfig.ConfigInfo configInformation) {

        this.configInformation = configInformation;
    }


    public String discard_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        
        return "case2";
    }

}


