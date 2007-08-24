/*
 * RoleStorage.java
 * 
 * 
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * siehe AbstrctStorage.java
 *
 * @author Maximilian Huetter
 */
public class RoleStorage extends AbstractStorage{
    Document StorageDoc;
    Element Root;
    List RoleList;
    /** Creates a new instance of RoleStorage */
    public RoleStorage(String path, String namespace, String fileName, String rootElementName){
        super(path, namespace, fileName, rootElementName);
    }
    /*
     * Liest die Role Informationen in die entsprechenden Objekte
     * und gibt eine Liste davon zur�ck. Siehe dazu auch MeteringProcessStorage.java.
     * @param fileName Dateiname der Storagedatei
     * @return Liste mit den Role-Objekte.
     */
    public List getList() {
        RoleList = new ArrayList();
        try{
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + storageFileName);
            Root = StorageDoc.getRootElement();
            List RoleChildren = Root.getChildren("role", ipfixConfigNS);
            Iterator listIterator = RoleChildren.iterator();                
            Element currentElement;
            
            while(listIterator.hasNext()){
                  currentElement = (Element) listIterator.next();
                  Role currentRole = new Role();
                  //currentRole.setId(Integer.valueOf(currentElement.getAttributeValue("id",ipfixConfigNS)));
                  currentRole.setName(currentElement.getChildText("name", ipfixConfigNS));
                  currentRole.setDescription(currentElement.getChildText("description", ipfixConfigNS));
                  Element roleMapping = currentElement.getChild("mapping", ipfixConfigNS);
                  HashMap roleMap = new HashMap();
                  List items = roleMapping.getChildren("item", ipfixConfigNS);
                  Iterator itemsIterator = items.iterator();
                  while(itemsIterator.hasNext()){
                      Element currentItem = (Element) itemsIterator.next();
                      roleMap.put(currentItem.getChildText("deviceId", ipfixConfigNS), currentItem.getChildText("configId", ipfixConfigNS));
                  }
                  currentRole.setMapping(roleMap);
                  
                  RoleList.add(currentRole);
            }
            
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
        return RoleList;
        
    }
}
