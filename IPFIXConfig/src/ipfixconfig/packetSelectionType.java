/*
 * packetSelectionType.java
 *
 * 
 */

package ipfixconfig;

/**
 * Basisinterface fuer alle PacketSelectiontsypen,
 * der Sinn davon ist bei alle Typen mit den gleichen Methoden bestimmte
 * Eigentschaften abzufragen.
 *
 * @author Maximilian Huetter
 */
public interface packetSelectionType {
    
    String getInformation();
    
    org.jdom.Element getDOMElement();
        
    String getType();
    
    String getName();
}
