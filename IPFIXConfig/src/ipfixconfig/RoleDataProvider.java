/*
 * RoleDataProvider.java
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
 * DataProvider Klasse fuer Role Objekte, siehe Java Server Faces-Dokumentation.
 * 
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class RoleDataProvider extends ObjectListDataProvider{
    List RoleList = new ArrayList();
    /** Creates a new instance of RoleDataProvider */
    public RoleDataProvider() {
        Role firstRole = new Role();
        RoleList.add(firstRole);
        setList(RoleList);
    }
    
}
