
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
 * NcReplyHandler.java
 *
 * Ein einfacher SAX Handler für Netconf Replys.
 * Die Rückgabewerte werde als public properties
 * bereitgestellt.
 */

/**
 *
 * @author max
 */
public class NcReplyHandler extends DefaultHandler{
    
    boolean lookForOk = false;
    boolean lookForError = false;
    
    boolean getErrorType = false;
    boolean getErrorTag = false;
    boolean getData = false;
    
    /*
     * Diese Properties können gelesen werden, um die Rückgabewerte zu erhalten.
     */
    public boolean ok = false;
    public String errorType, errorTag, data, messageId;
    /** Creates a new instance of NcReplyHandler */
    
    public NcReplyHandler() {
        data = "";
        messageId = "";
        errorType = "";
        errorTag = "";
    }
        
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs)
    throws SAXException{
        
        if(qName.equals("rpc-reply")){
            lookForOk = true;
            messageId = attrs.getValue("message-id");
        }
        if(qName.equals("rpc-error")){
            lookForError = true;
            ok = false;
            messageId = attrs.getValue("message-id");
        }
        
        if(lookForOk){
            if(qName.equals("ok")) ok = true;
            
            if(qName.equals("data")){
                ok = true;
                getData = true;
            }
        }
                
        if(lookForError){
            if(qName.equals("error-type")) getErrorType = true;
            
            if(qName.equals("error-tag")) getErrorTag = true;
            
            if(qName.equals("error-message")) getData = true;
        }
    }
  
    public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException
    {
        if(qName.equals("rpc-reply")) lookForOk = false;
        
        if(qName.equals("rpc-error")) lookForError = false;
                    
        if(qName.equals("data")) getData = false;
                
        if(lookForError){
            if(qName.equals("error-type")) getErrorType = false;
            
            if(qName.equals("error-tag")) getErrorTag = false;
            
            if(qName.equals("error-message")) getData = false;
        }
    }

    public void characters( char[] buf, int offset, int len )
    throws SAXException
    {
     if(getErrorType) errorType = buf.toString();
     
     if(getErrorTag) errorTag = buf.toString();
     
     if(getData) data = buf.toString();
     
    }

}
