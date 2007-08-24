/*
 * PacketReportingDataProvider.java
 *
 * 
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse f�r Packet Reporting Objekte
 * siehe Java Server Faces-Dokumentation. 
 *
 * @author Maximilian Huetter
 * e-mail:maxhuetter@web.de
 */
public class PacketReportingDataProvider extends ObjectListDataProvider{
    List reportingElementList = new ArrayList();
    /** Creates a new instance of PacketReportingDataProvider */
    public PacketReportingDataProvider() {
        InformationElement firstReportedIE = new InformationElement();
        reportingElementList.add(firstReportedIE);
        setList(reportingElementList);
    }
    
}
