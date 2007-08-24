/*
 * CollectingProcessDataProvider.java
 *
 *   
 *
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse f�r Collecting Process, siehe Java Server Faces-Dokumentation. 
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class CollectingProcessDataProvider extends ObjectListDataProvider{
    List collectingProcessList = new ArrayList();
    /** Creates a new instance of CollectingProcessDataProvider */
    public CollectingProcessDataProvider() {
        CollectingProcess firstCollectingProcess = new CollectingProcess();
        collectingProcessList.add(firstCollectingProcess);
        this.setList(collectingProcessList);
    }
    
}
