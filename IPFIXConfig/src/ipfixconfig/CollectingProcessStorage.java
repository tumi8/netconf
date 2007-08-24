/*
 * CollectingProcessStorage.java
 * 
 * 
 */

package ipfixconfig;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * siehe AbstractStorage-Klasse.
 *
 * @author Maximilian Huetter
 */
public class CollectingProcessStorage extends AbstractStorage{
    
    
    /** Creates a new instance of CollectingProcessStorage */
    public CollectingProcessStorage(String path, String namespace, String fileName, String rootElementName) {
        super(path, namespace, fileName, rootElementName);
    
    }
    /*
     * Liest die Collecting Process Informationen in die entsprechenden Objekte
     * und gibt eine Liste davon zurueck.
     * @param fileName Dateiname der Storagedatei
     * @return Liste mit den Collecting Process-Objekten.
     */
    public List getList(String fileName){
        ObjList = new ArrayList();
        try{
            //Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + fileName);
            Element CollectingProcessRoot = StorageDoc.getRootElement();
            List CollectingProcesssChildren = CollectingProcessRoot.getChildren("collectingProcess", ipfixConfigNS);
            //log("Children: " + String.valueOf(CollectingProcesssChildren.size()));
            Iterator listIterator = CollectingProcesssChildren.iterator();
            Element currentElement;
            
            while(listIterator.hasNext()){
                CollectingProcess currentProcess = new CollectingProcess();
                currentElement = (Element) listIterator.next();
                Integer id = Integer.valueOf(currentElement.getAttributeValue("id"));
                currentProcess.setId(id);
                
                List listenerList = new ArrayList();
                List listenerChildren = currentElement.getChildren("listener", ipfixConfigNS);
                Iterator listenerIterator = listenerChildren.iterator();
                
                while(listenerIterator.hasNext()){
                    Collector currentCollector = new Collector();
                    Element listenerElement = (Element) listenerIterator.next();
                    String ipAddtype = listenerElement.getChildText("ipAddressType", ipfixConfigNS);
                    currentCollector.setIpAddresstype(ipAddtype);
                    String ipAdd = listenerElement.getChildText("ipAddress", ipfixConfigNS);
                    currentCollector.setIpAddress(ipAdd);
                    String transProt = listenerElement.getChildText("transportProtocol", ipfixConfigNS);
                    currentCollector.setTransportProtocol(transProt);
                    Integer port = Integer.valueOf(listenerElement.getChildText("port", ipfixConfigNS));
                    currentCollector.setPort(port);
                    listenerList.add(currentCollector);
                }
                currentProcess.setListenerList(listenerList);
                Element observationDomainId = currentElement.getChild("observationDomainId", ipfixConfigNS);
                if(observationDomainId != null){
                    Integer domainId = Integer.valueOf(observationDomainId.getText());
                    currentProcess.setObservationDomainId(domainId);
                }
                
                Element udptempEl = currentElement.getChild("udpTemplateLifetime", ipfixConfigNS);
                if(udptempEl != null){
                    Integer TemplateLifetime = Integer.valueOf(udptempEl.getText());
                    currentProcess.setUdpTemplateLifetime(TemplateLifetime);
                    currentProcess.setUdpTemplateUnit(udptempEl.getAttributeValue("unit"));
                }
                
                List next = new ArrayList();
                Element nextElement = currentElement.getChild("next", ipfixConfigNS);
                if(nextElement != null){
                    List nextProcesses = nextElement.getChildren();
                    Iterator nextIterator = nextProcesses.iterator();
                    
                    while(nextIterator.hasNext()){
                        Element NextProcess = (Element) nextIterator.next();
                        next.add(new Next(NextProcess.getName(),Integer.valueOf(NextProcess.getText())));
                    }
                }
                currentProcess.setNext(next);                
                ObjList.add(currentProcess);
             }
            
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return ObjList;
    }
    
    /*
     * Save collecting processes back to XML file.
     * 
     * @param collectingProcessList list of collecting process objects to save
     *
     
     public void saveList(List collectingProcessList) throws Exception{
        Iterator collectingProcessIterator = collectingProcessList.iterator();
        Element collectingProcessRoot = new Element("collectingProcesses");
        
        while(collectingProcessIterator.hasNext()){
            CollectingProcess currentCollectingProcess = (CollectingProcess) collectingProcessIterator.next();
            collectingProcessRoot.addContent(currentCollectingProcess.getDOMElement());
        }
        Document collectingProcessStorage = new Document(collectingProcessRoot);
        
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath + "CollectingProcessStorage.xml");
        documentOutputter.output(collectingProcessStorage, xmlFileWriter);
        xmlFileWriter.close(); 
     }
    */
}
