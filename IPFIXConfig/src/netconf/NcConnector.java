package netconf;

/*
 * NcConnector.java
 * 
 */

import java.io.*;
import java.net.*;
import java.util.logging.*;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;



public class NcConnector {
    /* Connection basics*/
    Connection sshConnection;
    Session sshSession;
            
    /* Connection Werte*/
    private String hostName, loginName, passwordName;
    private int portName;
    private InetAddress addr;
    
    /*Connection IO */
    InputStream stdOut;
    OutputStream stdIn;
    BufferedReader stdOutBufferedReader;
    
    private static Logger mylogger;
    
    
    /* Immer mit message-id 1 anfangen. */
    private static int message_id = 1;
    
    /*Netconf namespace*/
    private String namespace = "xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\"";
    
    
    /**
     * NcConnector Konstruktor
     * Connection Werte setzen.
     * @param host der Name des Host mit dem verbunden wird
     * @param port der Port mit dem verbunden wird
     * @param login SSH login name fuer den Host
     * @param password SSH Password fuer den Host
     * @param v6 Wenn gesetzt Verbindung mit IPV6 - nicht unterstuetzt 
     */
    
    public NcConnector(String host, int port, String login, String password, boolean v6) {
        /*Setze die Variablen */
        hostName = host;
        loginName = login;
        passwordName = password;
        portName = port;
        
    }
    /**
     * Oeffnet die Connection mit dem Host 
     * 
     * @return List Capabiltitesliste oder Liste mit den Fehlern
     * */
    public List connect(){
        StringBuffer readInBuffer = new StringBuffer();
        //mylogger = Logger.getLogger("NetConfManagerLog");
        List errorList = new ArrayList();
        
        /* Connection Instanz */
        
        sshConnection = new Connection(hostName, portName);
        
        /* connect */
        try{
        sshConnection.connect();
        
        
        /* Authentifizieren */
        
        boolean isAuthenticated = sshConnection.authenticateWithPassword(loginName, passwordName);
        if(!isAuthenticated) {
            errorList.add("Error: Authentication failed");
            return errorList;
        }
        
        /* Session öffnen */
        
        sshSession = sshConnection.openSession();
        }
        catch(IOException ioEx){
            errorList.add("Error: IOException encountered: " + ioEx.getMessage());
            return errorList;
        }
        /* In Zukunft Netconf wird ein SSH-Subsystem */
        //sshSession.startSubSystem("netconf");
        
        stdOut = sshSession.getStdout();
        stdIn = sshSession.getStdin();
        
        stdOutBufferedReader = new BufferedReader(new InputStreamReader(stdOut));
        
        String hello = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                " <hello xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\">" +
                " <capabilities> <capability>urn:ietf:params:netconf:base:1.0</capability>" +
                " </capabilities>" +
                " </hello> ]]>]]>";
        
        NcHelloHandler helloHandler = new NcHelloHandler();
        String reply = "";
        try{
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        reply = send(hello);
        XMLReader reader = saxParser.getXMLReader();
        reader.setContentHandler(helloHandler);
        StringReader stringReader = new StringReader(reply);
        InputSource replySource = new InputSource(stringReader);
        reader.parse(replySource);
        }
        catch(Exception pEx){
            errorList.add("Error: SAX Exception encountered: " + pEx.getMessage());
            return errorList;
        }
        if(helloHandler.helloOK){
            return helloHandler.capabilitiesList;
        }        
        else{
            errorList.add("Error: Error when receiving <hello>");
            return errorList;
        }
        
    } // connect
    
    /**
     * Erstellt den RPC-Wrapper fuer eine Nachricht
     * 
     * @param message die Nachricht die Rpc-Wrapper bekommt.
     * 
     * 
     */
        
    public String sendRPC(String message){
        message_id++; //Zähle die verschickte Nachrichten und verwende das als ID
        NcReplyHandler replyHandler = new NcReplyHandler();
        try{
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        
        String reply = send("<rpc message-id=\"" + String.valueOf(message_id) + "\" " + namespace + "> " + message + " </rpc> ]]>]]>");
        
        XMLReader reader = saxParser.getXMLReader();
        reader.setContentHandler(replyHandler);
        StringReader stringReader = new StringReader(reply);
        InputSource replySource = new InputSource(stringReader);
        reader.parse(replySource);
        }
        catch(Exception ex){
            return "Error: Exception occured " + ex.getMessage();
        }
        String parsedReply = "";
        
        if(replyHandler.ok){
            if(!replyHandler.rawXMLOut.equals("")){
                parsedReply = replyHandler.rawXMLOut;
            }
            else parsedReply = "ok" ;
            }  
        else{
            parsedReply = "Error: Type: " + replyHandler.errorType + " Tag: " + replyHandler.errorTag
                        + " Info: " + replyHandler.errorData;
        }
        return parsedReply;
                
    }
    /**
     * Sendet eine Nachricht an den Agent und holt das Reply.
     * 
     * @param message die Nachricht, die gesendet werden soll
     * @return String reply als String
     */
    private String send(String message){
        StringBuffer readInBuffer = new StringBuffer("<?xml version=\"1.0\" ?>");
        
        try{
            stdIn.write(message.getBytes());
                                   
            String line;
            line = stdOutBufferedReader.readLine();
            
            while (!line.equals("]]>]]>")){
            readInBuffer.append(line + "\n");
            line = stdOutBufferedReader.readLine();
            }
        }
        catch(IOException ioEx){
            
            return "Exception while writing!" + ioEx.getMessage();
        }       
        return readInBuffer.toString();
    }
    
    /** 
     * Beenden der Connection
     * Schliesse Socket und In/Output Streams
     * @return String Reply auf den close-session-Request.
     */
    public String disconnect(){
            
            //Session beenden
            String reply = sendRPC("<close-session/>");
            
            
            if(reply.equals("ok")){
                
                sshSession.close();
                sshConnection.close();
                return "Disconnected successfully.";
            }  
            else{
                sendRPC("<kill-session/>");
                try{
                sshSession.close();
                sshConnection.close();
                }
                catch(Exception ex){
                    return "Exception while writing!" + ex.getMessage();
                }
                return reply;
            }
            
            
        
    } // disconnect
} // NcConnector
