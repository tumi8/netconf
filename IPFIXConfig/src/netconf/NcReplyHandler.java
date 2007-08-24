package netconf;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
 * NcReplyHandler.java
 *
 * 
 */

/**
 * Ein einfacher SAX Handler für Netconf-Replys.
 * Die Rueckgabewerte werde als public properties
 * bereitgestellt.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class NcReplyHandler extends DefaultHandler{
    
    private boolean lookForOk = false;
    private boolean lookForError = false;
    
    private boolean getErrorType = false;
    private boolean getErrorTag = false;
    private boolean getErrorData = false;
    private boolean getRawXML = false;
    
    /*
     * Diese Properties können gelesen werden, um die Rückgabewerte zu erhalten.
     */
    public boolean ok = false;
    public String errorType, errorTag, errorData, messageId;
    public String rawXMLOut;
    private StringBuffer rawXML;
    /** Creates a new instance of NcReplyHandler */
    
    public NcReplyHandler() {
        errorData = "";
        messageId = "";
        errorType = "";
        errorTag = "";
        rawXMLOut = "";
        rawXML = new StringBuffer();
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
            //messageId = attrs.getValue("message-id");
        }
        if(lookForOk){
            if(qName.equals("ok")) ok = true;
            
            if(qName.equals("data")){
                ok = true;
                getRawXML = true;
            }
        }
        if(lookForError){
            if(qName.equals("error-type")) getErrorType = true;
            
            if(qName.equals("error-tag")) getErrorTag = true;
            
            if(qName.equals("error-message")) getErrorData = true;
        }
        if(getRawXML){
            rawXML.append("<" + qName + " ");
            for(int i = 0; i < attrs.getLength(); i++){
                rawXML.append(attrs.getQName(i) + "=\"" + attrs.getValue(i) + "\" "); 
            }
            rawXML.append(">");
        }
    }
  
    public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException
    {
        if(qName.equals("rpc-reply")) lookForOk = false;
        
        if(qName.equals("rpc-error")) lookForError = false;
                    
        if(qName.equals("data")){
            getRawXML = false;
            rawXMLOut = rawXML.toString();
        }
                
        if(lookForError){
            if(qName.equals("error-type")) getErrorType = false;
            
            if(qName.equals("error-tag")) getErrorTag = false;
            
            if(qName.equals("error-message")) getErrorData = false;
        }
        if(getRawXML){
            rawXML.append("</" + qName + "> ");
        }
    }

    public void characters( char[] buf, int offset, int len )
    throws SAXException
    {
     if(getErrorType) errorType = new String(buf, offset, len);
     
     if(getErrorTag) errorTag = new String(buf, offset, len);
     
     if(getErrorData) errorData = new String(buf, offset, len);
     
     if(getRawXML) rawXML.append(buf, offset, len);
     
    }

}
