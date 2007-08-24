/*
 * DeviceStorage.java
 *
 * 
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * siehe AbstractStorage-Klasse.
 *
 * @author Maximilian Huetter
 */
public class DeviceStorage extends AbstractStorage{
    
    /** Creates a new instance of DeviceStorage */
    public DeviceStorage(String path, String namespace, String fileName, String rootElementName) {
        super(path, namespace, fileName, rootElementName);
    }
    /* Liest die Device Informationen in die entsprechenden Objekte
     * und gibt eine Liste davon zurueck.
     * @param fileName Dateiname der Storagedatei
     * @return Liste mit den Device-Objekten.
     */
    public List getList() {
        List DeviceList = new ArrayList();
        try{
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + storageFileName);
            Element Root = StorageDoc.getRootElement();
            List deviceChildren = Root.getChildren("device", ipfixConfigNS);
            Iterator listIterator = deviceChildren.iterator();                
            Element currentElement;
            
            while(listIterator.hasNext()){
                  currentElement = (Element) listIterator.next();
                  Device currentDevice = new Device();
                  //currentDevice.setId(Integer.valueOf(currentElement.getAttributeValue("id")));
                  currentDevice.setName(currentElement.getChildText("name", ipfixConfigNS));
                  currentDevice.setType(currentElement.getChildText("type", ipfixConfigNS));
                  currentDevice.setHostIP(currentElement.getChildText("host", ipfixConfigNS));
                  currentDevice.setUser(currentElement.getChildText("user", ipfixConfigNS));
                  currentDevice.setPassword(currentElement.getChildText("password", ipfixConfigNS));
                  currentDevice.setNetconfRole(currentElement.getChildText("netconfRole", ipfixConfigNS));
                  currentDevice.setNetconfNS(currentElement.getChildText("netconfNS", ipfixConfigNS));
                  currentDevice.setNetconfPath(currentElement.getChildText("netconfPath", ipfixConfigNS));
                  currentDevice.setDescription(currentElement.getChildText("descript", ipfixConfigNS));
                  currentDevice.setProtocol(currentElement.getChildText("protocol", ipfixConfigNS));
                  currentDevice.setPort(Integer.valueOf(currentElement.getChildText("port", ipfixConfigNS)));
                  currentDevice.setKey(currentElement.getChildText("key", ipfixConfigNS));
                  currentDevice.setMonitoredNetwork(currentElement.getChildText("monitoredNetwork", ipfixConfigNS));
                  currentDevice.setRole(currentElement.getChildText("role", ipfixConfigNS));
                  
                  DeviceList.add(currentDevice);
            }
            
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        return DeviceList;
        
    }
}
