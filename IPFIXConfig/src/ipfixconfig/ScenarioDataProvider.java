/*
 * ScenarioDataProvider.java
 * 
 * 
 */

package ipfixconfig;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * DataProvider Klasse f�r Scenario Objekte, siehe Java Server Faces-Dokumentation.
 *
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public class ScenarioDataProvider extends ObjectListDataProvider{
    List scenarioList = new ArrayList();
    /** Creates a new instance of ScenarioDataProvider */
    public ScenarioDataProvider() {
        //Scenario firstScenario = new Scenario();
        //scenarioList.add(firstScenario);
        setList(scenarioList);
    }
    
}
