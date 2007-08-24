/*
 * ConfigInfoDataProvider.java
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
 * DataProvider Klasse f�r die Config Info Objekte, siehe Java Server Faces-Dokumentation. 
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class ConfigInfoDataProvider extends ObjectListDataProvider{
    List configInfoList = new ArrayList();
    /** Creates a new instance of ConfigInfoDataProvider */
    public ConfigInfoDataProvider() {
        ConfigInfo firstConfInfo = new ConfigInfo();
        configInfoList.add(firstConfInfo);
        this.setList(configInfoList);
    }
    
}
