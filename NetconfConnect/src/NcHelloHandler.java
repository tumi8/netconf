

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.logging.*;
/*
 * NcHelloHandler.java
 * SAXHandler für Netconf Hello-Messages.
 * Gibt eine Liste mit den Capabilities des Agents zurück.
 */

/**
 *
 * @author max
 */
public class NcHelloHandler extends DefaultHandler{
    boolean lookForHello = false;
    boolean getCapability = false;
    
    public boolean helloOK = false;
    public boolean RBACCapability = false;
    public List capabilitiesList = new ArrayList();
    
    private static Logger mylogger;
    
    /** Creates a new instance of NcHelloHandler */
    public NcHelloHandler() {
        mylogger = Logger.getLogger("NetConfManagerLog");
    }
    
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs)
    throws SAXException{
        
        if(qName.equals("hello")) lookForHello = true;
        
        if(qName.equals("capability")) getCapability = true;
    }
    public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException {
        if(qName.equals("capability")) getCapability = false;
        if(qName.equals("hello")){
            lookForHello = false;
            helloOK = true;
        }
    }
    
    public void characters( char[] buf, int offset, int len )
    throws SAXException {
        
        if(getCapability){
            String capability = new String(buf, offset, len);
            capabilitiesList.add(capability);
            mylogger.info(capability);
        }
        //mylogger.info(new String(buf, offset, len));
        
    }
}
