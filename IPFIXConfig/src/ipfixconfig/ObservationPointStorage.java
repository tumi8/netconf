/*
 * ObservationPointStorage.java
 * 
 * 
 */

package ipfixconfig;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * siehe AbstractStorage-Klasse
 *
 * @author Maximilian Huetter
 */
public class ObservationPointStorage extends AbstractStorage{
        
    /**
     * Creates a new instance of ObservationPointStorage 
     */
     
    public ObservationPointStorage(String path, String namespace, String fileName, String rootElementName) {
        super(path,namespace,fileName, rootElementName);
    }
    /*
     * Liest die XML Datei ein, in der die Observation Points gespeichert sind
     * und gibt eine Liste von ObservationPoint Objekten zur�ck.
     * 
     * @param fileName Dateiname der Storage-Datei.
     */
    public List getList(String fileName){
        ObjList = new ArrayList();
        try{
            //Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + fileName);
            Root = StorageDoc.getRootElement();
            List observationPointsChildren = Root.getChildren("observationPoint", ipfixConfigNS);
            //log("Children: " + String.valueOf(observationPointsChildren.size()));
            Iterator listIterator = observationPointsChildren.iterator();                
            Element currentElement;
            
            while(listIterator.hasNext()){
                ObservationPoint currentObservationPoint = new ObservationPoint();
                currentElement = (Element) listIterator.next();
                Integer id = Integer.valueOf(currentElement.getAttributeValue("id"));
                currentObservationPoint.setId(id);
                Integer domainId = Integer.valueOf(currentElement.getChildText("observationDomainId", ipfixConfigNS));
                currentObservationPoint.setDomainId(domainId);
                String type = currentElement.getChildText("type", ipfixConfigNS);
                currentObservationPoint.setType(type);
                String parameters = currentElement.getChildText("parameters", ipfixConfigNS);
                currentObservationPoint.setParameters(parameters);
                String networkInterface = currentElement.getChildText("interface", ipfixConfigNS);
                currentObservationPoint.setNetworkInterface(networkInterface);
                
                List next = new ArrayList();
                Element nextElement = currentElement.getChild("next", ipfixConfigNS);
                if(nextElement != null) {
                    List nextProcesses = nextElement.getChildren();
                    Iterator nextIterator = nextProcesses.iterator();
                    
                    while(nextIterator.hasNext()){
                    Element NextProcess = (Element) nextIterator.next();
                    next.add(new Next(NextProcess.getName(),Integer.valueOf(NextProcess.getText())));
                    }
                
                }
                currentObservationPoint.setNext(next);
                ObjList.add(currentObservationPoint);
            }
            
        }
        catch(Exception ex){
            
                ex.printStackTrace();
        }
        return ObjList;
    }
    /*
     * Save observationPoints to XML File
     * 
     * @param observationPointList List
     */
    /*
    public void saveList(List observationPointList) throws IOException{
        
        Iterator observationPointsIterator = observationPointList.iterator();
        Element observationPointRoot = new Element("observationPoints",ipfixConfigNS);
        
        while(observationPointsIterator.hasNext()){
            ObservationPoint currentObservationPoint = (ObservationPoint) observationPointsIterator.next();
            observationPointRoot.addContent(currentObservationPoint.getDOMElement());
        }
        Document observationPointStorage = new Document(observationPointRoot);
        
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath + "ObservationPointStorage.xml");
        documentOutputter.output(observationPointStorage, xmlFileWriter);
        xmlFileWriter.close();
                
    }
    
    public static void removeNamespace(Element element) {
        
        Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
        
        if (element.getNamespace() == Namespace.NO_NAMESPACE) {
            element.setNamespace(ipfixConfigNS);
        }
        
        List childElements = element.getChildren();
        Iterator iterator = childElements.iterator();
        while (iterator.hasNext()) {
            Element child = (Element) iterator.next();
            removeNamespace(child);
        }
        
    }
     */
}
