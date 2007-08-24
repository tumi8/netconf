/*
 * ExportingProcessStorage.java
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
 * siehe AbstractStorage-Klasse
 *
 * @author Maximilian Huetter
 */
public class ExportingProcessStorage extends AbstractStorage{
        
    /** 
     * 
     * Creates a new instance of ExportingProcessStorage
     * @param path Path of the application.
     */
    public ExportingProcessStorage(String path, String namespace, String fileName, String rootElementName){
       super(path, namespace, fileName, rootElementName);
    }
    /*
     * Liest die Exporting Process Informationen in die entsprechenden Objekte
     * und gibt eine Liste davon zurueck. Siehe dazu auch MeteringProcessStorage.java.
     * @param fileName Dateiname der Storagedatei
     * @return Liste mit den Exporting Process Objekten.
     */

    public List getList(String fileName){
        ObjList = new ArrayList();
        try{
            
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + fileName);
            Element ExportingProcessRoot = StorageDoc.getRootElement();
            List ExportingProcesssChildren = ExportingProcessRoot.getChildren("exportingProcess", ipfixConfigNS);
            
            Iterator listIterator = ExportingProcesssChildren.iterator();
            Element currentElement;
            
            while(listIterator.hasNext()){
                currentElement = (Element) listIterator.next();
                ExportingProcess currentProcess = new ExportingProcess();
                Integer id = Integer.valueOf(currentElement.getAttributeValue("id"));
                currentProcess.setId(id);
                
                List collectorList = new ArrayList();
                List ListenerChildren = currentElement.getChildren("collector", ipfixConfigNS);
                Iterator ListenerIterator = ListenerChildren.iterator();
                Element listenerElement;
                
                while(ListenerIterator.hasNext()){
                    listenerElement = (Element) ListenerIterator.next();
                    String ipAddtype = listenerElement.getChildText("ipAddressType", ipfixConfigNS);
                    String ipAdd = listenerElement.getChildText("ipAddress", ipfixConfigNS);
                    String transProt = listenerElement.getChildText("transportProtocol", ipfixConfigNS);
                    Integer port = Integer.valueOf(listenerElement.getChildText("port", ipfixConfigNS));
                    collectorList.add(new Collector(ipAddtype, ipAdd, transProt, port));
                }
                currentProcess.setCollectorList(collectorList);
                
                Element udptempEl = currentElement.getChild("udpTemplateManagement", ipfixConfigNS);
                if(udptempEl != null){
                    Element refreshTimeout = udptempEl.getChild("templateRefreshTimeout", ipfixConfigNS);
                    if(refreshTimeout != null) {
                        currentProcess.setUdpTemplateRefreshTimeout(Integer.valueOf(refreshTimeout.getText()));
                        currentProcess.setUdpTemplateRefreshTimeoutUnit(refreshTimeout.getAttributeValue("unit"));
                    }
                    Element refreshrate = udptempEl.getChild("templateRefreshRate", ipfixConfigNS);
                    if(refreshrate != null){
                        currentProcess.setUdpTemplateRefreshrate(Integer.valueOf(refreshrate.getText()));
                    }
                    
                }
                Element packetRestrictions = currentElement.getChild("ipfixPacketRestrictions", ipfixConfigNS);
                if(packetRestrictions != null){
                    Element maxPacketSize = packetRestrictions.getChild("maxPacketSize", ipfixConfigNS);
                    if(maxPacketSize != null) currentProcess.setRestrictionsMaxPacketSize(Integer.valueOf(maxPacketSize.getText()));
                    Element maxExportDelay = packetRestrictions.getChild("maxExportDelay", ipfixConfigNS);
                    if(maxExportDelay != null){
                        currentProcess.setRestrictionsMaxExportDelay(Integer.valueOf(maxExportDelay.getText()));
                        currentProcess.setRestrictionsMaxExportDelayUnit(maxExportDelay.getAttributeValue("unit"));
                    }
                }
                
                ObjList.add(currentProcess);
            }
            
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return ObjList;
    }
    
    /*
     * Save the List of Metering Processes to Storage XML file.
     * 
     * @param exportingProcessList LIst with the exporting process object to save.
     
    public void saveList(List exportingProcessList) throws Exception{
        Iterator exportingProcessIterator = exportingProcessList.iterator();
        Element exportingProcessRoot = new Element("exportingProcesses");
        
        while(exportingProcessIterator.hasNext()){
            ExportingProcess currentexportingProcess = (ExportingProcess) exportingProcessIterator.next();
            exportingProcessRoot.addContent(currentexportingProcess.getDOMElement());
        }
        Document exportingProcessStorage = new Document(exportingProcessRoot);
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath + "ExportingProcessStorage.xml");
        documentOutputter.output(exportingProcessStorage, xmlFileWriter);
        xmlFileWriter.close();
    }
     */
}
