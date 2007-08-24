/*
 * DeviceDataProvider.java
 * 
 *  
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse fuer die Device-Objekte, siehe Java Server Faces-Dokumentation.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class DeviceDataProvider extends ObjectListDataProvider{
    List deviceList = new ArrayList();
    /** Creates a new instance of DeviceDataProvider */
    public DeviceDataProvider() {
        //Device firstDevice = new Device();
        //deviceList.add(firstDevice);
        setList(deviceList);
    }
    
}
