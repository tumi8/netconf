/*
 * NonFlowKeyDataProvider.java
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
 * DataProvider Klasse fuer Non FLow Key Objekte, siehe Java Server Faces-Dokumentation.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class NonFlowKeyDataProvider extends ObjectListDataProvider{
    List nonFlowKeyList = new ArrayList();
    /** Creates a new instance of NonFlowKeyDataProvider */
    public NonFlowKeyDataProvider() {
            InformationElement firstNonFlowKey = new InformationElement();
            nonFlowKeyList.add(firstNonFlowKey);
            setList(nonFlowKeyList);
    }
    
}
