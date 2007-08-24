/*
 * editNext.java
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
import com.sun.rave.web.ui.component.AddRemove;
import com.sun.rave.web.ui.component.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class editNext extends AbstractPageBean {
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

    private AddRemove addRemoveList1 = new AddRemove();

    public AddRemove getAddRemoveList1() {
        return addRemoveList1;
    }

    public void setAddRemoveList1(AddRemove ar) {
        this.addRemoveList1 = ar;
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


    /** 
     * <p>Construct a new Page bean instance.</p>
     */
    public editNext() {
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
            log("editNext Initialization Failure", e);
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
        
        String[] next = getSessionBean1().getSelectedNext();
        List nextList = new ArrayList();
        
        RowKey selectedRowKey = getSessionBean1().getSelectedObservationPointRowKey();
        if(selectedRowKey != null){
            ObservationPoint selectedObservPoint = (ObservationPoint) getSessionBean1().getObservationDataProvider().getObject(selectedRowKey);
            
            for(int i = 0; i < next.length; i++){
                String currentProcess = next[i];
                StringTokenizer currentTokens = new StringTokenizer(currentProcess);
                nextList.add(new Next(currentTokens.nextToken(), Integer.valueOf(currentTokens.nextToken())));
            }
            selectedObservPoint.setNext(nextList);
            getSessionBean1().getObservationDataProvider().setObject(selectedRowKey, selectedObservPoint);
            getSessionBean1().getObservationDataProvider().commitChanges();
        }
        
        selectedRowKey = getSessionBean1().getSelectedCollectingProcessRowKey();
        if(selectedRowKey != null){
            
                CollectingProcess selectedCollecting = (CollectingProcess) getSessionBean1().getCollectingDataProvider().getObject(selectedRowKey);
                
                for(int i = 0; i < next.length; i++){
                    String currentProcess = next[i];
                    StringTokenizer currentTokens = new StringTokenizer(currentProcess);
                    nextList.add(new Next(currentTokens.nextToken(), Integer.valueOf(currentTokens.nextToken())));
                }
                selectedCollecting.setNext(nextList);
                getSessionBean1().getCollectingDataProvider().setObject(selectedRowKey, selectedCollecting);
                getSessionBean1().getCollectingDataProvider().commitChanges();
            
        }
        selectedRowKey = getSessionBean1().getSelectedMeteringProcessRowKey();
        if(selectedRowKey != null){
            
                MeteringProcess selectedMetering = (MeteringProcess) getSessionBean1().getMeteringDataProvider().getObject(selectedRowKey);
                
                for(int i = 0; i < next.length; i++){
                    String currentProcess = next[i];
                    StringTokenizer currentTokens = new StringTokenizer(currentProcess);
                    nextList.add(new Next(currentTokens.nextToken(), Integer.valueOf(currentTokens.nextToken())));
                }
                selectedMetering.setNext(nextList);
                getSessionBean1().getMeteringDataProvider().setObject(selectedRowKey, selectedMetering);
                getSessionBean1().getMeteringDataProvider().commitChanges();
                       
        }
        getSessionBean1().setSelectedNext(new String[]{});
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }


    public String discard_changes_action() {
        if(getSessionBean1().isNewConfig()){
            return "saveChangesNew";
        }
        else return "saveChangesOld";
    }
}

