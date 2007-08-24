/*
 * PacketSelection.java
 *
 * 
 *
 */

package ipfixconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * Repraesentiert Packet Selection-Objekte im System. Alle Eigenschaften leiten sich
 * direkt aus dem IPFIX Configuration Data Model ab, oder sind 
 * Informationseigentschaften fuer das System.
 *
 * @author Maximilian Huetter
 */
public class PacketSelection extends Object{
    /*
     * Listen die, die verschiedenen Packet Selection Typen aufnehmen.
     */
    List packetSelectionTypesList = new ArrayList();
    
    /** Creates a new instance of PacketSelection */
    public PacketSelection() {
        
    }
    /*
     * Konstruktor mit Daten.
     */
    public PacketSelection(List packetSelectionTypes){
            this.setPacketSelectionTypesList(packetSelectionTypes);
            
    }
    
    /**
     * Getter for property countBasedList.
     * @return Value of property countBasedList.
     */
    public java.util.List getPacketSelectionTypesList() {

        return this.packetSelectionTypesList;
    }

    /**
     * Setter for property countBasedList.
     * @param PacketSelectionTypes New value of property countBasedList.
     */
    public void setPacketSelectionTypesList(java.util.List PacketSelectionTypes) {

        this.packetSelectionTypesList = PacketSelectionTypes;
    }
    

    /**
     * Holds value of property packetSelectionDOM.
     */
    private org.jdom.Element packetSelectionDOM;

    /**
     * Gibt das Objekt als JDOM-Element zurueck.
     * 
     * @return org.jdom.Element XMLRepraesentation des Objekts.
     */
    public org.jdom.Element getPacketSelectionDOM() {
        
        Element packetSelectionElement = new Element("packetSelection");
        
        ArrayList packetSelectionMethods = (ArrayList) this.getPacketSelectionTypesList();
        
        
        Iterator packetSelectionIterator = packetSelectionMethods.iterator();
        while(packetSelectionIterator.hasNext()){
            
            packetSelectionType currentSelection = (packetSelectionType) packetSelectionIterator.next();
             
            packetSelectionElement.addContent(currentSelection.getDOMElement());
        }
   
        packetSelectionDOM = packetSelectionElement;
        return this.packetSelectionDOM;
    }
    
}
