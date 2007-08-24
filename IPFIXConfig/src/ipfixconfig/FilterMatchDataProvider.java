package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;
/*
 * FilterMatchDataProvider.java
 *
 * 
 *
 */

/**
 * DataProvider Klasse f�r Filter Match Objekte, siehe Java Server Faces-Dokumentation. 
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class FilterMatchDataProvider extends ObjectListDataProvider{
    List filterMatchIEList = new ArrayList();
    /** Creates a new instance of FilterMatchDataProvider */
    public FilterMatchDataProvider() {
        /*
        InformationElement firstIE = new InformationElement();
        filterMatchIEList.add(firstIE);
        this.setList(filterMatchIEList);
         */
    }
    
}
