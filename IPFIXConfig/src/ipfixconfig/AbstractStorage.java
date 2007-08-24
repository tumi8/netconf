/*
 * AbstractStorage.java
 * 
 * 
 * 
 */

package ipfixconfig;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

/**
 * Basisklasse von der alle Storage-Klassen erben.
 * Storageklassen sind dazu da, um Konfigurationen und die Process-Speicherdateien
 * auszulesen und zu schreiben.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class AbstractStorage {
    Document StorageDoc;
    Element Root;
    List ObjList;
    String appPath;
    String storageFileName;
    Namespace ipfixConfigNS;
    String storageRootElementName;
    
    /** Neue Instanz von AbstractStorage */
    public AbstractStorage(String path, String namespace, String fileName, String rootElementName) {
        appPath = path;
        ipfixConfigNS = Namespace.getNamespace(namespace);
        storageFileName = fileName;
        storageRootElementName = rootElementName;
    }
    
    /*
     * Processobjekte in XML-Datei speichern.
     * 
     * @param observationPointList List
     */
    
    public void saveList(List processObjList) throws IOException{
        
        Iterator processObjIterator = processObjList.iterator();
        Element rootElement = new Element(storageRootElementName,ipfixConfigNS);
        
        while(processObjIterator.hasNext()){
            AbstractIPFIXProcess currentProcess = (AbstractIPFIXProcess) processObjIterator.next();
            rootElement.addContent(currentProcess.getDOMElement());
        }
        removeNamespace(rootElement);
        Document storageDocument = new Document(rootElement);
        
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath + storageFileName);
        documentOutputter.output(storageDocument, xmlFileWriter);
        xmlFileWriter.close();
                
    }
    /*
     * Diese Methode ist noetig, damit JDOM nicht lauter leere Namespaces für alle Knoten
     * einfuegt. Siehe Namespace-Deklarationen xmlns=""
     * xmlns="" muss entfernt werden, da sonst nicht mehr richtig eingelesen werden kann.
     * 
     * 
     * @param org.jdom.Element das (Root-) Element dessen Kindknoten die Namespace-Angabe verlieren sollen.
     */
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
    
}
