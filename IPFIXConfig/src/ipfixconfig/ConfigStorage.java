/*
 * ConfigStorage.java
 * 
 * 
 */

package ipfixconfig;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

/**
 * siehe AbstractStorage-Klasse
 *
 * @author Maximilian Huetter
 */
public class ConfigStorage {
    String appPath = "";
    Namespace basicNS;
    /** Creates a new instance of ConfigStorage */
    public ConfigStorage(String path, String namespace) {
        appPath = path;
        basicNS = Namespace.getNamespace(namespace);
    }
    /*
     * Speichert eine Konfiguration in einer Datei, uebergeben werden die
     * Listen mit den Objekten, die Bestandteil der Konfiguration werden sollen.
     * @param fileName Dateiname der neuen Konfiguration
     * @param observationPointsList Liste der OB-Point-Objekte
     * @param collectingProcessList Liste der CP-Objekte
     * @param meteringProcessList Liste der MP-Objekte
     * @param exportingProcessList Liste der EP-Objekte
     *
     */
    public void SaveConfig(String fileName, 
            List observationPointsList, 
            List collectingProcessList, 
            List meteringProcessList, 
            List exportingProcessList) throws Exception{
        
        Element ipfixConfigRoot = new Element("ipfixConfig", basicNS);
        
        Iterator observationPointsIterator = observationPointsList.iterator();       
        while(observationPointsIterator.hasNext()){
            ObservationPoint currentObservationPoint = (ObservationPoint) observationPointsIterator.next();
            ipfixConfigRoot.addContent(currentObservationPoint.getDOMElement());
        }
        
        Iterator collectingProcessIterator = collectingProcessList.iterator();
        
        while(collectingProcessIterator.hasNext()){
            CollectingProcess currentCollectingProcess = (CollectingProcess) collectingProcessIterator.next();
            ipfixConfigRoot.addContent(currentCollectingProcess.getDOMElement());
        }
        
        Iterator meteringProcessIterator = meteringProcessList.iterator();
        
        while(meteringProcessIterator.hasNext()){
            MeteringProcess currentMeteringProcess = (MeteringProcess) meteringProcessIterator.next();
            ipfixConfigRoot.addContent(currentMeteringProcess.getDOMElement());
        }
        
        Iterator exportingProcessIterator = exportingProcessList.iterator();
        
        while(exportingProcessIterator.hasNext()){
            ExportingProcess currentExportingProcess = (ExportingProcess) exportingProcessIterator.next();
            ipfixConfigRoot.addContent(currentExportingProcess.getDOMElement());
        }
        removeNamespace(ipfixConfigRoot);
        
        Document ipfixConfigDoc = new Document(ipfixConfigRoot);
        
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath + fileName);
        documentOutputter.output(ipfixConfigDoc, xmlFileWriter);
        xmlFileWriter.close();
        
    }
    
    /*
     * "Entfernt" die leeren Namespace tags, die JDOM (ganz XML Namespace conform) 
     * immer einf�gen w�rde. Was wiederrum dazu f�hrt, dass die Dokumente mit den leeren
     * Namespaces nicht mehr gelesen werden k�nnen, denn die Elemente m�ssen im Namespace
     * urn:ietf:params:xml:ns:ipfix-config liegen. 
     */
    public void removeNamespace(Element element) {
        
        Namespace removeNS = basicNS;
        
        if (element.getNamespace() == Namespace.NO_NAMESPACE) {
            element.setNamespace(removeNS);
        }
        
        List childElements = element.getChildren();
        Iterator iterator = childElements.iterator();
        while (iterator.hasNext()) {
            Element child = (Element) iterator.next();
            removeNamespace(child);
        }
        
    }
            
    
}
