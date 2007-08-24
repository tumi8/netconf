/*
 * ObservationPointDataProvider.java
 *
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;


/**
 * DataProvider Klasse f�r Observation Points, siehe Java Server Faces-Dokumentation.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class ObservationPointDataProvider extends ObjectListDataProvider{
    List observationPointList = new ArrayList();
    
    /** Creates a new instance of ObservationPointDataProvider */
    public ObservationPointDataProvider() {
        observationPointList.add(new ObservationPoint());
        this.setList(observationPointList);
    }
}