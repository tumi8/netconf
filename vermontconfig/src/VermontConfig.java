import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;


import java.util.List;
import java.util.ArrayList;


class VermontConfig {
	NcConnector connection;
	Document document;
	String configFile;
	List capabilities;

	VermontConfig(String filename, String hostname, int port, String username, String passwd)
	{
		configFile = filename;
		connection = new NcConnector(hostname, port, username, passwd, false);
		capabilities = new ArrayList();
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


	void  connectToVermont() throws Exception
	{
		StringBuffer messageBuffer = new StringBuffer();

		capabilities = connection.connect();

		messageBuffer.append("Capabilities: \n");
		for(int i = 0; i < capabilities.size(); i++){
			messageBuffer.append(capabilities.get(i) + "\n");
		}
	}


	void readConfig() throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse( new File( configFile ) );
		} catch (SAXException sxe) {
			// Error generated during parsing
			Exception  x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			throw x;
		}
	}

	String sendRoleData( String role ) throws IOException
	{
		String message = "<rbac> <activate> <roles> <role>" + role + "</role> </roles> </activate> </rbac>";
		return connection.sendRPC( message );
	}
	

	String sendConfig() throws Exception
	{
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

	String restart() throws Exception
	{
		String message = "<restart><moduleName>VERMONT</moduleName></restart>";
		return connection.sendRPC( message );
	}
}
