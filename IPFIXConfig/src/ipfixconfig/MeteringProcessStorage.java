/*
 * MeteringProcessStorage.java
 *
 * 
 * 
 */

package ipfixconfig;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * siehe AbstractStorage-Klasse. 
 *
 * @author Maximilian Huetter
 */
public class MeteringProcessStorage extends AbstractStorage{
        
    /** Creates a new instance of MeteringProcessStorage */
    public MeteringProcessStorage(String path, String namespace, String fileName, String rootElementName) {
        super(path,namespace,fileName, rootElementName);
    }
    
    /*
     * Gibt eine Liste der verfuegbaren Metering Process zurueck.
     * Das Dokument, dass die meteringProcess Elemente enthaelt (kann eine Konfigurations
     * datei sein, oder eine Storage Datei. Es muss aber auf jeden Fall unterhalb des Root
     * Elements <meteringProcess> Elemente enthalten. Diese duerfen nicht tiefer im Baum sein.
     * @param fileName Datei aus der die Metering Processes gelesen werden sollen.
     * @return Liste mit den Metering Process-Objekten.
     */
    public java.util.List getList(String fileName){
        List meteringProcessesList = new ArrayList();
        
        try{
            
            SAXBuilder builder = new SAXBuilder();
            StorageDoc = builder.build(appPath + fileName);
            Root = StorageDoc.getRootElement();
            List MeteringProcesssChildren = Root.getChildren("meteringProcess", ipfixConfigNS);
            
            Iterator meteringProcessIterator = MeteringProcesssChildren.iterator();
            Element meteringProcessElement;
            MeteringProcess meteringProcess;
            
            while(meteringProcessIterator.hasNext()){
                meteringProcess = new MeteringProcess();
                meteringProcessElement = (Element) meteringProcessIterator.next();
                
                Integer Id = new Integer(meteringProcessElement.getAttributeValue("id"));
                meteringProcess.setId(Id);
                
                PacketSelection packetSelectionObject = new PacketSelection();
                Element packetSelection = meteringProcessElement.getChild("packetSelection", ipfixConfigNS);
                if(packetSelection != null){
                    packetSelectionObject = getPacketSelectionData(packetSelection);
                }
                meteringProcess.setPacketSelectionData(packetSelectionObject);
                
                PacketReporting packetReportingObject = new PacketReporting();
                Element packetReporting = meteringProcessElement.getChild("packetReporting", ipfixConfigNS);
                if(packetReporting != null){
                    packetReportingObject = getPacketReportingData(packetReporting);
                }
                meteringProcess.setPacketReportingData(packetReportingObject);
                
                FlowMetering flowMeteringObject = new FlowMetering();
                Element flowMetering = meteringProcessElement.getChild("flowMetering", ipfixConfigNS);
                if(flowMetering != null){
                    flowMeteringObject = getFlowMeteringData(flowMetering);
                }
                meteringProcess.setFlowMeteringData(flowMeteringObject);
                
                List next = new ArrayList();
                Element nextElement = meteringProcessElement.getChild("next", ipfixConfigNS);
                if(nextElement != null){
                    List nextProcesses = nextElement.getChildren();
                    Iterator nextIterator = nextProcesses.iterator();
                    
                    while(nextIterator.hasNext()){
                        Element NextProcess = (Element) nextIterator.next();
                        next.add(new Next(NextProcess.getName(),Integer.valueOf(NextProcess.getText())));
                    }
                }
                
                meteringProcess.setNext(next);
                
                meteringProcessesList.add(meteringProcess);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return meteringProcessesList;
    }
    
    /*
     * Liest die PacketSelection Daten ein und gibt ein PacketSelection Objekt zurück.
     * Die Daten werden in der Reihenfolge eingelesen, in der sie im XML Dokument auftauchen
     * da hier die Reihenfolge wichtig ist. 
     */
    
    private PacketSelection getPacketSelectionData(Element packetSelectionElement){
        Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
        PacketSelection packetSelectionObject = new PacketSelection();
        List packetSelectionChildren = packetSelectionElement.getChildren();
        Iterator packetSelectionIterator = packetSelectionChildren.iterator();
        Element currentPacketSelection;
        
        List packetSelectionList = new ArrayList();
        
        while(packetSelectionIterator.hasNext()){
            currentPacketSelection = (Element) packetSelectionIterator.next();
            
            
            if(currentPacketSelection.getName().equals("countBased")){
                Integer interval = Integer.valueOf(currentPacketSelection.getChildText("interval", ipfixConfigNS));
                Integer spacing = Integer.valueOf(currentPacketSelection.getChildText("spacing", ipfixConfigNS));
                packetSelectionList.add(new CountBasedSelection(interval, spacing));
            }
            
            if(currentPacketSelection.getName().equals("timeBased")){
                Integer interval = Integer.valueOf(currentPacketSelection.getChildText("interval", ipfixConfigNS));
                Integer spacing = Integer.valueOf(currentPacketSelection.getChildText("spacing", ipfixConfigNS));
                packetSelectionList.add(new TimeBasedSelection(interval, spacing));
            }
            
            if(currentPacketSelection.getName().equals("randOutOfN")){
                Integer population = Integer.valueOf(currentPacketSelection.getChildText("population",ipfixConfigNS));
                Integer sample = Integer.valueOf(currentPacketSelection.getChildText("sample",ipfixConfigNS));
                packetSelectionList.add(new RandOutOfNSelection(population, sample));
            }
            
            if(currentPacketSelection.getName().equals("uniProb")){
                Integer probability = Integer.valueOf(currentPacketSelection.getChildText("probability", ipfixConfigNS));
                packetSelectionList.add(new UniProbSelection(probability));
            }
            
            if(currentPacketSelection.getName().equals("nonUniProb")){
                String function = currentPacketSelection.getChildText("function", ipfixConfigNS);
                String funcParam = currentPacketSelection.getChildText("funcParam", ipfixConfigNS);
                packetSelectionList.add(new NonUniProbSelection(function, funcParam));
            }
            
            if(currentPacketSelection.getName().equals("flowState")){
                String function = currentPacketSelection.getChildText("function", ipfixConfigNS);
                String funcParam = currentPacketSelection.getChildText("funcParam", ipfixConfigNS);
                packetSelectionList.add(new FlowStateSelection(function, funcParam));
            }
            
            if(currentPacketSelection.getName().equals("filterMatch")){
                
                List IEChildren = currentPacketSelection.getChildren("infoElementId",ipfixConfigNS);
                Element infoElement;
                Iterator IEChildrenIterator = IEChildren.iterator();
                List filterMatchIEList = new ArrayList();
                while(IEChildrenIterator.hasNext()){
                    InformationElement currentIE = new InformationElement();
                    infoElement = (Element) IEChildrenIterator.next();
                    if(infoElement.getChildText("ieId", ipfixConfigNS) != null){
                        Integer ieId = Integer.valueOf(infoElement.getChildText("ieId",ipfixConfigNS));
                        currentIE.setIeId(ieId);
                    }
                    
                    if(infoElement.getChildText("enterpriseNumber",ipfixConfigNS) != null){
                        Integer enterpriseNumber = Integer.valueOf(infoElement.getChildText("enterpriseNumber",ipfixConfigNS));
                        currentIE.setEnterpriseNumber(enterpriseNumber);
                    }
                    
                    if(infoElement.getChildText("ieName",ipfixConfigNS) != null){
                        currentIE.setIeName(infoElement.getChildText("ieName",ipfixConfigNS));
                    }
                    
                    if(infoElement.getChildText("ieLength",ipfixConfigNS) != null){
                        Integer ieLength = Integer.valueOf(infoElement.getChildText("ieLength",ipfixConfigNS));
                        currentIE.setIeLength(ieLength);
                    }
                    
                    if(infoElement.getChildText("match", ipfixConfigNS) != null){
                        currentIE.setMatch(infoElement.getChildText("match", ipfixConfigNS));
                    }
                    
                    if(infoElement.getChildText("modifier", ipfixConfigNS) != null){
                        currentIE.setModifier(infoElement.getChildText("modifier", ipfixConfigNS));
                    }
                    
                    filterMatchIEList.add(currentIE);
                }
                
                packetSelectionList.add(new FilterMatchSelection(filterMatchIEList));
            }
            
            if(currentPacketSelection.getName().equals("filterHash")){
                String addrType = currentPacketSelection.getChildText("addrType",ipfixConfigNS);
                String headerBits = currentPacketSelection.getChildText("headerBits",ipfixConfigNS);
                Integer payloadBytes = Integer.valueOf(currentPacketSelection.getChildText("payloadBytes",ipfixConfigNS));
                Integer payloadBits = Integer.valueOf(currentPacketSelection.getChildText("payloadBits",ipfixConfigNS));
                String function = currentPacketSelection.getChildText("function",ipfixConfigNS);
                String funcParam = currentPacketSelection.getChildText("funcParam",ipfixConfigNS);
                Integer inputBits = Integer.valueOf(currentPacketSelection.getChildText("inputBits",ipfixConfigNS));
                Integer outputBits = Integer.valueOf(currentPacketSelection.getChildText("outputBits",ipfixConfigNS));
                String outputMask = currentPacketSelection.getChildText("outputMask",ipfixConfigNS);
                String selection = currentPacketSelection.getChildText("selection",ipfixConfigNS);
                // Neues filterHash Element erzeugen:
                packetSelectionList.add(new FilterHashSelection(addrType, headerBits, payloadBytes, payloadBits, function, funcParam, inputBits, outputBits, outputMask, selection));
                
            }
            
            if(currentPacketSelection.getName().equals("filterRState")){
                String function = currentPacketSelection.getChildText("function", ipfixConfigNS);
                /*Laut der XML Schema Definition sind fuer boolean typen, sowohl true/false,
                * als auch 1/0 erlaubt. Daher muessen beide bearbeitet werde. 
                * Siehe: http://www.w3.org/TR/xmlschema-2/#boolean
                */
                String negate = currentPacketSelection.getChildText("negate",ipfixConfigNS);
                Boolean Bnegate;
                if(negate.equals("true") || negate.equals("1")) Bnegate = new Boolean("true");
                else Bnegate = new Boolean("false");
                
                Integer ifIndex = Integer.valueOf(currentPacketSelection.getChildText("ifIndex",ipfixConfigNS));
                Integer startAS = Integer.valueOf(currentPacketSelection.getChildText("startAS",ipfixConfigNS));
                Integer endAS = Integer.valueOf(currentPacketSelection.getChildText("endAS",ipfixConfigNS));
                String vendorFunc = currentPacketSelection.getChildText("vendorFunc",ipfixConfigNS);
                packetSelectionList.add(new FilterRStateSelection(function, Bnegate.booleanValue(), ifIndex, startAS, endAS, vendorFunc));
 
            }
            if(currentPacketSelection.getName().equals("rawFilter")){
                String settings = currentPacketSelection.getChildText("settings", ipfixConfigNS);
                packetSelectionList.add(new RawFilter(settings));
            }
        }
        packetSelectionObject.setPacketSelectionTypesList(packetSelectionList);
        return packetSelectionObject;
    }
    
    
    /*
     * PacketReporting Daten einlesen:
     *
     */
    
    private PacketReporting getPacketReportingData(Element packetReportingElement){
        Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
        PacketReporting packetReportingObject = new PacketReporting();
        
        if(packetReportingElement.getChildText("templateId", ipfixConfigNS) != null){
            Integer templateId = Integer.valueOf(packetReportingElement.getChildText("templateId", ipfixConfigNS));
            packetReportingObject.setTemplateId(templateId);
        }
        
        
        List reportedIEChildren = packetReportingElement.getChildren("reportedIE", ipfixConfigNS);
        Iterator reportedIEChildrenIterator = reportedIEChildren.iterator();
        List reportedIEObjectList = new ArrayList();
        Element reportedIEElement;
        
        while(reportedIEChildrenIterator.hasNext()){
            InformationElement currentIE = new InformationElement();
            reportedIEElement = (Element) reportedIEChildrenIterator.next();
            
            if(reportedIEElement.getChildText("ieId", ipfixConfigNS) != null){
                Integer ieId = Integer.valueOf(reportedIEElement.getChildText("ieId", ipfixConfigNS));
                currentIE.setIeId(ieId);
            }
            
            if(reportedIEElement.getChildText("enterpriseNumber", ipfixConfigNS) != null){
                Integer enterpriseNumber = Integer.valueOf(reportedIEElement.getChildText("enterpriseNumber", ipfixConfigNS));
                currentIE.setEnterpriseNumber(enterpriseNumber);
            }
            
            if(reportedIEElement.getChildText("ieName", ipfixConfigNS) != null){
                currentIE.setIeName(reportedIEElement.getChildText("ieName", ipfixConfigNS));
            }
            
            if(reportedIEElement.getChildText("ieLength", ipfixConfigNS) != null){
                Integer ieLength = Integer.valueOf(reportedIEElement.getChildText("ieLength", ipfixConfigNS));
                currentIE.setIeLength(ieLength);
            }
            
            if(reportedIEElement.getChildText("match", ipfixConfigNS) != null){
                currentIE.setMatch(reportedIEElement.getChildText("match", ipfixConfigNS));
            }
            
            if(reportedIEElement.getChildText("modifier", ipfixConfigNS) != null){
                currentIE.setModifier(reportedIEElement.getChildText("modifier", ipfixConfigNS));
            }
            
            reportedIEObjectList.add(currentIE);
            
        }
        packetReportingObject.setPacketReportingIEList(reportedIEObjectList);
        return packetReportingObject;
    }
    /*
     * Liest die Flow Metering Daten ein.
     *
     */
    
    private FlowMetering getFlowMeteringData(Element flowMetering){
        Namespace ipfixConfigNS = Namespace.getNamespace("urn:ietf:params:xml:ns:ipfix-config");
        FlowMetering flowMeteringObject = new FlowMetering();
        //Alle rule Unterelemente holen:
        List rulesChildren = flowMetering.getChildren("rule", ipfixConfigNS);
        Iterator rulesChildrenIterator = rulesChildren.iterator();
        List rulesObjectList = new ArrayList();
        Element ruleElement;
        
        while(rulesChildrenIterator.hasNext()){
            ruleElement = (Element) rulesChildrenIterator.next();
            /*
             * Jede Rule kann sowohl flow als auch nonFlowKeys enthalten:
             * Zuerst die flowKeys holen und in der Liste flowKeyList sammeln:
             */
            List flowKeyList = new ArrayList();
            // Alle FlowKey Unterelemente:
            List flowKeyChildren = ruleElement.getChildren("flowKey", ipfixConfigNS);
            Iterator flowKeyChildrenIterator = flowKeyChildren.iterator();
            Element flowKeyElement;
            
            while(flowKeyChildrenIterator.hasNext()){
                InformationElement currentIE = new InformationElement();
                flowKeyElement = (Element) flowKeyChildrenIterator.next();
                if(flowKeyElement.getChildText("ieId",ipfixConfigNS) != null){
                    Integer ieId = Integer.valueOf(flowKeyElement.getChildText("ieId",ipfixConfigNS));
                    currentIE.setIeId(ieId);
                }
                
                if(flowKeyElement.getChildText("enterpriseNumber", ipfixConfigNS) != null){
                    Integer enterpriseNumber = Integer.valueOf(flowKeyElement.getChildText("enterpriseNumber",ipfixConfigNS));
                    currentIE.setEnterpriseNumber(enterpriseNumber);
                }
                
                if(flowKeyElement.getChildText("ieName", ipfixConfigNS) != null){
                    currentIE.setIeName(flowKeyElement.getChildText("ieName", ipfixConfigNS));
                }
                
                if(flowKeyElement.getChildText("ieLength", ipfixConfigNS) != null){
                    Integer ieLength = Integer.valueOf(flowKeyElement.getChildText("ieLength", ipfixConfigNS));
                    currentIE.setIeLength(ieLength);
                }
                
                if(flowKeyElement.getChildText("match",ipfixConfigNS) != null){
                    currentIE.setMatch(flowKeyElement.getChildText("match", ipfixConfigNS));
                }
                
                if(flowKeyElement.getChildText("modifier", ipfixConfigNS) != null){
                    currentIE.setModifier(flowKeyElement.getChildText("modifier", ipfixConfigNS));
                }
                
                flowKeyList.add(currentIE);
            }
            /*
             * Dann die nonFlowKeys sammeln:
             *
             */
            List nonFlowKeyList = new ArrayList();
            // Alle nonFlowKey Unterelemente:
            List nonFlowKeyChildren = ruleElement.getChildren("nonFlowKey",ipfixConfigNS);
            Iterator nonFlowKeyChildrenIterator = nonFlowKeyChildren.iterator();
            Element nonFlowKeyElement;
            
            while(nonFlowKeyChildrenIterator.hasNext()){
                InformationElement currentIE = new InformationElement();
                nonFlowKeyElement = (Element) nonFlowKeyChildrenIterator.next();
                
                if(nonFlowKeyElement.getChildText("ieId", ipfixConfigNS) != null){
                    Integer ieId = Integer.valueOf(nonFlowKeyElement.getChildText("ieId",ipfixConfigNS));
                    currentIE.setIeId(ieId);
                }
                
                if(nonFlowKeyElement.getChildText("enterpriseNumber",ipfixConfigNS) != null){
                    Integer enterpriseNumber = Integer.valueOf(nonFlowKeyElement.getChildText("enterpriseNumber", ipfixConfigNS));
                    currentIE.setEnterpriseNumber(enterpriseNumber);
                }
                
                if(nonFlowKeyElement.getChildText("ieName",ipfixConfigNS) != null){
                    currentIE.setIeName(nonFlowKeyElement.getChildText("ieName", ipfixConfigNS));
                }
                
                if(nonFlowKeyElement.getChildText("ieLength", ipfixConfigNS) != null){
                    Integer ieLength = Integer.valueOf(nonFlowKeyElement.getChildText("ieLength", ipfixConfigNS));
                    currentIE.setIeLength(ieLength);
                }
                
                if(nonFlowKeyElement.getChildText("match", ipfixConfigNS) != null){
                    currentIE.setMatch(nonFlowKeyElement.getChildText("match", ipfixConfigNS));
                }
                
                if(nonFlowKeyElement.getChildText("modifier", ipfixConfigNS) != null){
                    currentIE.setModifier(nonFlowKeyElement.getChildText("modifier", ipfixConfigNS));
                }
                
                nonFlowKeyList.add(currentIE);
            }
            String tempId = ruleElement.getChildText("templateId", ipfixConfigNS);
            Integer templateId = new Integer(0);
            if(tempId != null){
                templateId = Integer.valueOf(tempId);
            }
            rulesObjectList.add(new FlowMeteringRule(templateId, flowKeyList, nonFlowKeyList));
            
        }
        flowMeteringObject.setRulesList(rulesObjectList);
        
        if(flowMetering.getChild("expiration", ipfixConfigNS) != null){
            Element expirationElement = flowMetering.getChild("expiration",ipfixConfigNS);
            Element activeTimeoutElement = expirationElement.getChild("activeTimeout",ipfixConfigNS);
            flowMeteringObject.setFlowExpirationActiveTimeout(Integer.valueOf(activeTimeoutElement.getText()));
            flowMeteringObject.setFlowExpirationActiveTimeoutUnit(activeTimeoutElement.getAttributeValue("unit"));
            Element inActiveTimeoutElement = expirationElement.getChild("inactiveTimeout", ipfixConfigNS);
            flowMeteringObject.setFlowExpirationInactiveTimeout(Integer.valueOf(inActiveTimeoutElement.getText()));
            flowMeteringObject.setFlowExpirationInactiveTimeoutUnit(inActiveTimeoutElement.getAttributeValue("unit"));
        }
        
        return flowMeteringObject;
    }
    
    /*
     * Save Metering Processes back to storage XML file.
     *
     * @params meteringProcessList List this Metering Processes to save.
     
    
   public void saveList(List meteringProcessList)throws Exception{
       Iterator meteringProcessIterator = meteringProcessList.iterator();
        Element meteringProcessRoot = new Element("meteringProcesses");
        
        while(meteringProcessIterator.hasNext()){
            MeteringProcess currentmeteringProcess = (MeteringProcess) meteringProcessIterator.next();
            meteringProcessRoot.addContent(currentmeteringProcess.getDOMElement());
        }
        Document meteringProcessStorage = new Document(meteringProcessRoot);
        
        XMLOutputter documentOutputter = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
        FileWriter xmlFileWriter = new FileWriter(appPath + "MeteringProcessStorage.xml");
        documentOutputter.output(meteringProcessStorage, xmlFileWriter);
        xmlFileWriter.close();
   }
   */ 
}
