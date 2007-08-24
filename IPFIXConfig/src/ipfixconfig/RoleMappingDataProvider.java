/*
 * RoleMappingDataProvider.java
 *
 * 
 *
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * DataProvider um das Device - Config - Mapping der Role darzustellen.
 *
 * Dataprovider der zur Anzeige der Mappings in der Rolle gebraucht wird.
 * Intern arbeitet er mit einer einfachen Liste von MappingBean-Objekten
 * und nicht mit einer Java HashMap.
 * 
 * roleMappingList ist die Basisliste von MappingBean-Objekten.
 * roleMapping ist die Hashmap-Version der Liste.
 *
 * @author Maximilian Huetter
 */
public class RoleMappingDataProvider extends ObjectListDataProvider{
    Map roleMapping = new HashMap();
    List roleMappingList = new ArrayList();
    /** Creates a new instance of RoleMappingDataProvider */
    public RoleMappingDataProvider() {
        //this.roleMappingList.add(new MappingBean());
        setList(this.roleMappingList);
        
    }
    /*
     * Erzeugt aus einer Map wieder eine Liste der MappingBean-Objekte,
     * die angezeigt werden kann.
     */
    public void setMap(Map mapping){
        List newMappingList = new ArrayList();
                
        Iterator entryIterator = mapping.entrySet().iterator();
               
        while(entryIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entryIterator.next();
            String device = (String)entry.getKey();
            String config = (String)entry.getValue();
            MappingBean mBean = new MappingBean(device, config);
            newMappingList.add(mBean);
        }
        this.setList(newMappingList);        
    }
    /*
     * Erzeugt die Hashmap aus der Liste der MappingBeans.
     *  
     */ 
    
     public Map getMap(){
                  
         Iterator mappingIterator = this.getList().iterator();
         
         while(mappingIterator.hasNext()){
             MappingBean currentBean = (MappingBean) mappingIterator.next();
             this.roleMapping.put(currentBean.getDevice(), currentBean.getConfig());
         }
         return this.roleMapping;
     }  
    
    
    
}
