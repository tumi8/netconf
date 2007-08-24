/*
 * FlowkeyDataProvider.java
 *
 * 
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse f�r Flow Key Objekte, siehe Java Server Faces-Dokumentation.
 * 
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class FlowkeyDataProvider extends ObjectListDataProvider{
    List flowKeyList = new ArrayList();
    /** Creates a new instance of FlowkeyDataProvider */
    public FlowkeyDataProvider() {
        InformationElement firstFlowKey = new InformationElement();
            flowKeyList.add(firstFlowKey);
            setList(flowKeyList);
    }
    
}
