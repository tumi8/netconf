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

	/* Connection values*/
	private String hostName, loginName, passwordName;
	private int portName;
	private InetAddress addr;

	/*Connection IO */
	InputStream stdOut;
	OutputStream stdIn;
	BufferedReader stdOutBufferedReader;

	private static Logger mylogger;


	/*always start with message-id 101 */
	private static int message_id = 101;

	/*netconf namespace*/
	private String namespace = "xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\"";


	/**
	 * NcConnector Constructor
	 * Sets the Connection values.
	 * @param host the name of the host to connect to
	 * @param port the port to connect to
	 * @param login SSH login name on the host
	 * @param password SSH password on the host
	 * @param v6 if true, use a IPV6 Connection
	 */

	public NcConnector(String host, int port, String login, String password, boolean v6) {
		/*Set all variables */
		hostName = host;
		loginName = login;
		passwordName = password;
		portName = port;

	}
	/**
	 * Opens the Connection to host with the values specified in the Constructor
	 * @param none
	 * @return nothing
	 * */
	public List connect() throws Exception {
		StringBuffer readInBuffer = new StringBuffer();
		//mylogger = Logger.getLogger("NetConfManagerLog");
		List errorList = new ArrayList();
		/* Create a connection instance */

		sshConnection = new Connection(hostName, portName);

		/* Now connect */
		sshConnection.connect();

		/* Authenticate. */

		boolean isAuthenticated = sshConnection.authenticateWithPassword(loginName, passwordName);
		if(!isAuthenticated) {
			throw new Exception("Authentication failed");
		}

		/* Create a session */

		sshSession = sshConnection.openSession();
		/* In the future netconf should be a subsystem of SSH */
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
		SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		reply = send(hello);
		XMLReader reader = saxParser.getXMLReader();
		reader.setContentHandler(helloHandler);
		StringReader stringReader = new StringReader(reply);
		InputSource replySource = new InputSource(stringReader);
		reader.parse(replySource);

		if(!helloHandler.helloOK) {
			throw new Exception("Error when receiving <hello>");
		}

		return helloHandler.capabilitiesList;     
	} // connect

	/**
	 * Creates message with the RPC wrapper
	 * @param message the message to be wrapped in the rpc.
	 * @param messageID the message ID to be used.
	 * */

	public String sendRPC(String message) throws IOException {
		message_id++; //Count the number of messages sent
		String reply = send("<rpc message-id=\"" + String.valueOf(message_id) + "\" " + namespace + "> " + message + " </rpc> ]]>]]>");
		return reply;
	}
	/**
	 * Sendet eine Netconf-Nachricht an den Netconf-Agent.
	 * @param message die Nachricht die gesendet werden soll.
	 */
	private String send(String message) throws IOException {
		StringBuffer readInBuffer = new StringBuffer("<?xml version=\"1.0\" ?>");

		stdIn.write(message.getBytes());

		String line;
		line = stdOutBufferedReader.readLine();

		while (!line.equals("]]>]]>")){
			readInBuffer.append(line + "\n");
			line = stdOutBufferedReader.readLine();
		}
		return readInBuffer.toString();
	}

	/* 
	 *  Beendet die Verbindung.
	 *  Im Falle eines Fehlers beim geordneten Beenden ueber close-session,
	 *  wird kill-session ausgefuehrt und die SSHSession geschlossen.
	 */

	public String disconnect() throws Exception {
		NcReplyHandler replyHandler = new NcReplyHandler();
		SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		//closing the session
		String reply = sendRPC("<close-session/>");
		//mylogger.info("Send close session");
		XMLReader reader = saxParser.getXMLReader();
		reader.setContentHandler(replyHandler);
		StringReader stringReader = new StringReader(reply);
		InputSource replySource = new InputSource(stringReader);
		reader.parse(replySource);
		if(replyHandler.ok){

			sshSession.close();
			sshConnection.close();
			return "disconnected successfully.";
		}  
		else{
			reply = send("<kill-session/>");
			sshSession.close();
			sshConnection.close();
			return "An error was encountered while closing the session. Type: "
				+ replyHandler.errorType + " Tag: " + replyHandler.errorTag
				+ " Info: " + replyHandler.data;
		}
	} // disconnect
} // NcConnector
