/*
 * ExportingDataProvider.java
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
 * DataProvider Klasse fuer Exporting Process, siehe Java Server Faces-Dokumentation. 
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class ExportingProcessDataProvider extends ObjectListDataProvider{
    List exportingList = new ArrayList();
    /** Creates a new instance of ExportingDataProvider */
    public ExportingProcessDataProvider() {
        ExportingProcess firstExporting = new ExportingProcess();
        exportingList.add(firstExporting);
        setList(exportingList);
    }
    
}
