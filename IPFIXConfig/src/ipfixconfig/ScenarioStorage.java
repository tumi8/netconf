/*
 * ScenarioStorage.java
 *
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
 * siehe AbstractStorage.java
 *
 * @author Maximilian Huetter
 */
public class ScenarioStorage extends AbstractStorage{
    
    /** Creates a new instance of ScenarioStorage */
    public ScenarioStorage(String path, String namespace, String fileName, String rootElementName) {
        super(path, namespace, fileName, rootElementName);
    }
    /*
     * Liest die Scenario Informationen in die entsprechenden Objekte
     * und gibt eine Liste davon zurueck. Siehe dazu auch MeteringProcessStorage.java.
     * @param fileName Dateiname der Storagedatei
     * @return Liste mit den Scenario-Objekte.
     */
    public List getList() {
        List scenariosList = new ArrayList();
        try{
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + storageFileName);
            Root = StorageDoc.getRootElement();
            List scenarioChildren = Root.getChildren("scenario", ipfixConfigNS);
            Iterator listIterator = scenarioChildren.iterator();                
            Element currentElement;
            
            while(listIterator.hasNext()){
                  currentElement = (Element) listIterator.next();
                  Scenario currentScenario = new Scenario();
                  //currentscenario.setId(Integer.valueOf(currentElement.getAttributeValue("id")));
                  currentScenario.setName(currentElement.getChildText("name", ipfixConfigNS));
                  currentScenario.setDescription(currentElement.getChildText("descript", ipfixConfigNS));
                  List deviceList = new ArrayList();
                  Element devices = currentElement.getChild("devices", ipfixConfigNS);
                  List childList = devices.getChildren("device", ipfixConfigNS);
                  Iterator devicesIterator = childList.iterator();
                  while(devicesIterator.hasNext()){
                      Element currentDevice = (Element) devicesIterator.next();
                      deviceList.add(currentDevice.getText());
                  }
                  currentScenario.setDeviceList(deviceList);
            scenariosList.add(currentScenario);      
            }
            
            
            
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        return scenariosList;
        
    }
}
