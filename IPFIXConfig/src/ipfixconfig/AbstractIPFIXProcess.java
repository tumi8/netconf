/*
 * AbstractIPFIXProcess.java
 * 
 */

package ipfixconfig;

/**
 * Interface damit die AbstractStorage saveList-Methode mit allen
 * Processes arbeitet. Holt fuer einen Process die JDOM Repraesentation.
 * 
 * @author Maximilian Huetter
 * e-mail: maxhuetter@web.de
 */
public interface AbstractIPFIXProcess {
    
    org.jdom.Element getDOMElement();
    
}
