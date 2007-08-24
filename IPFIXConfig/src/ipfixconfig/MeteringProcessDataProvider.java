/*
 * MeteringProcessDataProvider.java
 *
 * 
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse fuer Metering Process, siehe Java Server Faces-Dokumentation.
 * 
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class MeteringProcessDataProvider extends ObjectListDataProvider{
    List meteringProcessList = new ArrayList();
    /** Creates a new instance of MeteringProcessDataProvider */
    public MeteringProcessDataProvider() {
        MeteringProcess firstmeteringProcess = new MeteringProcess();
        meteringProcessList.add(firstmeteringProcess);
        this.setList(meteringProcessList);
    }
    
}
