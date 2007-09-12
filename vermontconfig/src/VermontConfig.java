package netconf;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;


import java.util.List;
import java.util.ArrayList;


public class VermontConfig {
	private NcConnector connection = null;
	private Document document = null;
	private List capabilities = null;
	private String role;
	private XPath xpath;

	public VermontConfig()
	{
		capabilities = new ArrayList();
		XPathFactory xfactory = XPathFactory.newInstance();
		xpath = xfactory.newXPath();
	}

	private static String rmXMLTag(String s)
	{
		String r ="";

		// 1. remove <?xml ...?> tag
		String search = "<?xml";
		int i = s.indexOf(search);
		if ( i != -1 ) {
			int start = 0;
			int end = 0;
			start = i;
			String sub = s.substring(start+5);
			int j = sub.indexOf("?>");
			if (j != -1) {
				end = j;
				r = sub.substring(end+2);
			} else {
				// read line(s) to fininsh removing ...
				r = "";
			}
			return r;
		} else
			return s;
	}


	public void connectToVermont(String hostname, int port, String username, String passwd, String role) throws Exception
	{
		StringBuffer messageBuffer = new StringBuffer();

		connection = new NcConnector(hostname, port, username, passwd, false);
		this.role = role;

		capabilities = connection.connect();

		messageBuffer.append("Capabilities: \n");
		for(int i = 0; i < capabilities.size(); i++){
			messageBuffer.append(capabilities.get(i) + "\n");
		}
	}


	public void readConfig(String filename) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse( new File( filename ) );
		} catch (SAXException sxe) {
			// Error generated during parsing
			Exception  x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			throw x;
		}
	}

	public void changeItem(String xpathQuery, String newValue) throws Exception
	{
		if ( document == null ) {
			throw new Exception( "Could not change document, because document does not exist" );
		}
		Object result = xpath.evaluate( xpathQuery, document, XPathConstants.NODE);
		if (result == null ) {
			throw new Exception( "XPathQuery gave no result" );
		}
		Node xmlNode = (Node) result;
		if (xmlNode == null) {
			throw new Exception( "Could not cast XPathQuery result into xmlNode" );
		}
		if (!xmlNode.getNodeName().equals("#text")) {
			throw new Exception( "XPathQuery did not result in a text node! xmlNode.getNodeName() was \""
			+ xmlNode.getNodeName() + "\"");
		}
		xmlNode.setNodeValue( newValue );
	}

	public String sendRoleData( ) throws Exception
	{
		if ( connection == null ) {
			throw new Exception( "not connected!" );
		}
		String message = "<rbac> <activate> <roles> <role>" + role + "</role> </roles> </activate> </rbac>";
		return connection.sendRPC( message );
	}
	

	public String sendConfig() throws Exception
	{
		if ( connection == null ) {
			throw new Exception( "not connected" );
		}
		if ( document == null ) {
			throw new Exception( "cannot send configuration because we don't have any yet!" );
		}
		String message;
                StringWriter output = new StringWriter();
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(output));
		message = "<copy-config>";
		message += "<target><startup/></target>";
		message += "<source>";
		message += "<config>";
		message += "<netconf xmlns=\"urn:loria:madynes:ensuite:yencap:1.0\">";
		message += "<monitoring xmlns=\"urn:loria:madynes:ensuite:yencap:1.0\">";
		message += rmXMLTag( output.toString() );
		message += "</monitoring>";
		message += "</netconf>";
		message += "</config>";
		message += "</source>";
		message += "</copy-config>";

		return connection.sendRPC( message );
	}

	public String restart() throws Exception
	{
		String message = "<restart><moduleName>VERMONT</moduleName></restart>";
		return connection.sendRPC( message );
	}
}
