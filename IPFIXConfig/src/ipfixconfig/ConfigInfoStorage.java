/*
 * ConfigInfoStorage.java
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
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * siehe AbstractStorage.java
 * @author Maximilian Huetter
 */
public class ConfigInfoStorage extends AbstractStorage{
    
    /** 
     * Konstruktor fuer ConfigInfoStorage
     * @param path Application-pfad
     * @param namespace Namespace in dem gespeichert werden soll.
     * @param fileName Dateiname der ConfigInfoStorage-XML Datei. 
     * @param rootElementName Name des Rootelements zum speichern.
     * 
     */
    public ConfigInfoStorage(String path, String namespace, String fileName, String rootElementName) {
        super(path, namespace, fileName, rootElementName);
    }

    /**
     * Liest die Exporting Process Informationen in die entsprechenden Objekte
     * und gibt eine Liste davon zurueck. Siehe dazu auch MeteringProcessStorage.java.
     *
     * 
     * @return Liste mit den ConfigInfo-Objekten.
     */
    public List getList() {
        List ConfigInfoList = new ArrayList();
        try{
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + storageFileName);
            Element Root = StorageDoc.getRootElement();
            List configChildren = Root.getChildren("configInfo", ipfixConfigNS);
            Iterator listIterator = configChildren.iterator();                
            Element currentElement;
            
            while(listIterator.hasNext()){
                ConfigInfo currentConf = new ConfigInfo();
                currentElement = (Element) listIterator.next();
                currentConf.setName(currentElement.getChildText("name", ipfixConfigNS));
                currentConf.setLastmodified(currentElement.getChildText("lastModified", ipfixConfigNS));
                currentConf.setFileName(currentElement.getChildText("fileName", ipfixConfigNS));
                currentConf.setDescription(currentElement.getChildText("description", ipfixConfigNS));
                ConfigInfoList.add(currentConf);
            }
            
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        return ConfigInfoList;
        
    }
    /**
     * Save List of ConfigInfo Objects to XML File
     * @return Value of property list.
     
    public void saveConfigInfo(List configInfoList) throws Exception{
        
        Iterator configInfoIterator = configInfoList.iterator();
        Element configInfosRoot = new Element("configInfos");
        
        while(configInfoIterator.hasNext()){
            ConfigInfo currentConfigInfo = (ConfigInfo) configInfoIterator.next();
            configInfosRoot.addContent(currentConfigInfo.getDOMElement());
        }
        Document configInfoStorage = new Document(configInfosRoot);
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath);
        documentOutputter.output(configInfoStorage, xmlFileWriter);
        xmlFileWriter.close();
    }
    */
}
