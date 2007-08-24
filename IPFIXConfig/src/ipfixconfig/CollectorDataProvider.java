/*
 * CollectorDataProvider.java
 *
 *   
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse fuer Collector-Objekte, siehe Java Server Faces-Dokumentation. 
 * 
 * @author Maximilian H�tter
 */
public class CollectorDataProvider extends ObjectListDataProvider{
    List collectorList = new ArrayList();
    /** Creates a new instance of CollectorDataProvider */
    public CollectorDataProvider() {
        /*
        Collector firstCollector = new Collector();
        collectorList.add(firstCollector);
        this.setList(collectorList);
         */
    }
    
}
