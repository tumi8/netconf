<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editConfig.page1}" id="page1">
            <ui:html binding="#{editConfig.html1}" id="html1">
                <ui:head binding="#{editConfig.head1}" id="head1" title="Edit Configuration">
                    <ui:link binding="#{editConfig.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editConfig.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editConfig.form1}" id="form1" virtualFormsConfig="save | gridPanel1:configName1 gridPanel1:fileName1 gridPanel1:descript1 | save_config_button1 , do something else | | discard_changes_button1 gridPanel2:gridPanel3:add_ObservationPoint gridPanel2:gridPanel3:table1:tableRowGroup1:tableColumn6:edit_Observationpoint_button gridPanel2:gridPanel3:table1:tableRowGroup1:tableColumn7:remove_observation_point gridPanel2:gridPanel4:add_CollectingProcess gridPanel2:gridPanel3:table1:tableRowGroup1:tableColumn5:observ_next gridPanel2:gridPanel4:table2:tableRowGroup2:tableColumn10:collecting_next gridPanel2:gridPanel4:table2:tableRowGroup2:tableColumn13:edit_CollectingProcess1 gridPanel2:gridPanel4:table2:tableRowGroup2:tableColumn14:remove_CollectingProcess1 gridPanel2:gridPanel5:add_Metering gridPanel2:gridPanel5:table3:tableRowGroup3:tableColumn16:edit_PacketSelection gridPanel2:gridPanel5:table3:tableRowGroup3:tableColumn17:edit_PacketReporting gridPanel2:gridPanel5:table3:tableRowGroup3:tableColumn18:edit_FlowMetering gridPanel2:gridPanel5:table3:tableRowGroup3:tableColumn19:metering_next gridPanel2:gridPanel5:table3:tableRowGroup3:tableColumn20:remove_MeteringProcess gridPanel2:gridPanel6:add_exportingProcess gridPanel2:gridPanel6:table4:tableRowGroup4:tableColumn29:edit_ExportingProcess gridPanel2:gridPanel6:table4:tableRowGroup4:tableColumn30:remove_ExportingProcess">
                        <ui:label binding="#{editConfig.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Configuration"/>
                        <ui:button action="#{editConfig.discard_changes_button1_action}" binding="#{editConfig.discard_changes_button1}"
                            id="discard_changes_button1" style="left: 191px; top: 192px; position: absolute" text="Return without saving"/>
                        <ui:button action="#{editConfig.save_config_button1_action}" binding="#{editConfig.save_config_button1}" id="save_config_button1"
                            style="left: 23px; top: 192px; position: absolute" text="Save Configuration"/>
                        <h:panelGrid binding="#{editConfig.gridPanel1}" id="gridPanel1" style="left: 24px; top: 72px; position: absolute">
                            <ui:textField binding="#{editConfig.configName1}" id="configName1" label="Configuration Name:" required="true"
                                style="height: 144px; width: 480px" text="#{SessionBean1.configInfo.name}" validator="#{editConfig.configName_validate}"/>
                            <ui:textField binding="#{editConfig.fileName1}" id="fileName1" label="Filename:" required="true"
                                text="#{SessionBean1.configInfo.fileName}" validator="#{editConfig.fileName_validate}"/>
                            <ui:textArea binding="#{editConfig.descript1}" columns="40" id="descript1" label="Description:" style="height: 70px; width: 454px" text="#{SessionBean1.configInfo.description}"/>
                        </h:panelGrid>
                        <ui:messageGroup binding="#{editConfig.messageGroup1}" id="messageGroup1" style="left: 600px; top: 144px; position: absolute"/>
                        <h:panelGrid binding="#{editConfig.gridPanel2}" id="gridPanel2" style="height: 288px; left: 24px; top: 264px; position: absolute" width="696">
                            <h:panelGrid binding="#{editConfig.gridPanel3}" id="gridPanel3" style="width: 100%; height: 100%;">
                                
                                <ui:table augmentTitle="false" binding="#{editConfig.table1}" id="table1" title="Observation Points" width="840">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table1");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table1");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table1");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table1");
  table.initAllRows();
}
/*
 * Set the selected state for the given row groups
 * displayed in the table.  This functionality requires
 * the 'selectId' of the tableColumn to be set.
 *
 * @param rowGroupId HTML element id of the tableRowGroup component
 * @param selected Flag indicating whether components should be selected
 */
function selectGroupRows(rowGroupId, selected) {
  var table = document.getElementById("form1:table1");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table1");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{editConfig.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                        sourceData="#{SessionBean1.observationDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{editConfig.tableColumn1}" headerText="ID" id="tableColumn1" sort="id">
                                            <ui:staticText binding="#{editConfig.staticText1}" id="staticText1" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn2}" headerText="DomainID" id="tableColumn2" sort="domainId">
                                            <ui:staticText binding="#{editConfig.staticText2}" id="staticText2" text="#{currentRow.value['domainId']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn3}" headerText="Type" id="tableColumn3" sort="type">
                                            <ui:staticText binding="#{editConfig.staticText3}" id="staticText3" text="#{currentRow.value['type']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn4}" headerText="Parameters" id="tableColumn4" sort="parameters">
                                            <ui:staticText binding="#{editConfig.staticText4}" id="staticText4" text="#{currentRow.value['parameters']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn5}" headerText="Next Process" id="tableColumn5" sort="nextInfo">
                                            <ui:hyperlink action="#{editConfig.observ_next_action}" binding="#{editConfig.observ_next}" id="observ_next" text="#{currentRow.value['nextInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn6}" headerText="Edit" id="tableColumn6">
                                            <ui:button action="#{editConfig.edit_Observationpoint_button_action}"
                                                binding="#{editConfig.edit_Observationpoint_button}" id="edit_Observationpoint_button" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn7}" headerText="Remove" id="tableColumn7">
                                            <ui:button action="#{editConfig.remove_observation_point_action}" binding="#{editConfig.remove_observation_point}"
                                                id="remove_observation_point" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{editConfig.add_ObservationPoint_action}" binding="#{editConfig.add_ObservationPoint}"
                                    id="add_ObservationPoint" text="Add Observation Point"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{editConfig.gridPanel4}" id="gridPanel4" style="width: 100%; height: 100%;">
                                
                                <ui:table augmentTitle="false" binding="#{editConfig.table2}" id="table2" title="CollectingProcesses" width="840">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table2");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table2");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table2");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table2");
  table.initAllRows();
}
/*
 * Set the selected state for the given row groups
 * displayed in the table.  This functionality requires
 * the 'selectId' of the tableColumn to be set.
 *
 * @param rowGroupId HTML element id of the tableRowGroup component
 * @param selected Flag indicating whether components should be selected
 */
function selectGroupRows(rowGroupId, selected) {
  var table = document.getElementById("form1:table2");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table2");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{editConfig.tableRowGroup2}" id="tableRowGroup2" rows="10"
                                        sourceData="#{SessionBean1.collectingDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{editConfig.tableColumn8}" headerText="ID" id="tableColumn8" sort="id">
                                            <ui:staticText binding="#{editConfig.staticText5}" id="staticText5" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn9}" headerText="Listeners" id="tableColumn9" sort="listenerListLink">
                                            <ui:staticText binding="#{editConfig.staticText6}" escape="false" id="staticText6" text="#{currentRow.value['listenerListLink']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn11}" headerText="UDP Lifetime" id="tableColumn11" sort="udpTemplateLifetime">
                                            <ui:staticText binding="#{editConfig.staticText7}" id="staticText7" text="#{currentRow.value['udpTemplateLifetime']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn12}" headerText="Unit" id="tableColumn12" sort="udpTemplateUnit">
                                            <ui:staticText binding="#{editConfig.staticText8}" id="staticText8" text="#{currentRow.value['udpTemplateUnit']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn10}" headerText="Next Process" id="tableColumn10" sort="nextInfo">
                                            <ui:hyperlink action="#{editConfig.collecting_next_action}" binding="#{editConfig.collecting_next}"
                                                id="collecting_next" text="#{currentRow.value['nextInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn13}" headerText="Edit" id="tableColumn13">
                                            <ui:button action="#{editConfig.edit_CollectingProcess1_action}" binding="#{editConfig.edit_CollectingProcess1}"
                                                id="edit_CollectingProcess1" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn14}" headerText="Remove" id="tableColumn14">
                                            <ui:button action="#{editConfig.remove_CollectingProcess1_action}" binding="#{editConfig.remove_CollectingProcess1}"
                                                id="remove_CollectingProcess1" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{editConfig.add_CollectingProcess_action}" binding="#{editConfig.add_CollectingProcess}"
                                    id="add_CollectingProcess" text="Add Collecting Process"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{editConfig.gridPanel5}" id="gridPanel5" style="width: 100%; height: 100%;">
                                
                                <ui:table augmentTitle="false" binding="#{editConfig.table3}" id="table3" title="Metering Processes" width="720">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table3");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table3");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table3");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table3");
  table.initAllRows();
}
/*
 * Set the selected state for the given row groups
 * displayed in the table.  This functionality requires
 * the 'selectId' of the tableColumn to be set.
 *
 * @param rowGroupId HTML element id of the tableRowGroup component
 * @param selected Flag indicating whether components should be selected
 */
function selectGroupRows(rowGroupId, selected) {
  var table = document.getElementById("form1:table3");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table3");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{editConfig.tableRowGroup3}" id="tableRowGroup3" rows="10"
                                        sourceData="#{SessionBean1.meteringDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{editConfig.tableColumn15}" headerText="ID" id="tableColumn15" sort="id">
                                            <ui:staticText binding="#{editConfig.staticText9}" id="staticText9" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn16}" headerText="PacketSelection" id="tableColumn16" sort="packetSelectionInfo">
                                            <ui:hyperlink action="#{editConfig.edit_PacketSelection_action}" binding="#{editConfig.edit_PacketSelection}"
                                                id="edit_PacketSelection" text="#{currentRow.value['packetSelectionInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn17}" headerText="PacketReporting" id="tableColumn17" sort="packetReportingInfo">
                                            <ui:hyperlink action="#{editConfig.edit_PacketReporting_action}" binding="#{editConfig.edit_PacketReporting}"
                                                id="edit_PacketReporting" text="#{currentRow.value['packetReportingInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn18}" headerText="FlowMetering" id="tableColumn18" sort="flowMeteringInfo">
                                            <ui:hyperlink action="#{editConfig.edit_FlowMetering_action}" binding="#{editConfig.edit_FlowMetering}"
                                                id="edit_FlowMetering" text="#{currentRow.value['flowMeteringInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn19}" headerText="Next Processes" id="tableColumn19" sort="nextInfo">
                                            <ui:hyperlink action="#{editConfig.metering_next_action}" binding="#{editConfig.metering_next}" id="metering_next" text="#{currentRow.value['nextInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn20}" headerText="Remove" id="tableColumn20">
                                            <ui:button action="#{editConfig.remove_MeteringProcess_action}" binding="#{editConfig.remove_MeteringProcess}"
                                                id="remove_MeteringProcess" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{editConfig.add_Metering_action}" binding="#{editConfig.add_Metering}" id="add_Metering" text="Add Metering Process"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{editConfig.gridPanel6}" id="gridPanel6">
                                
                                <ui:table augmentTitle="false" binding="#{editConfig.table4}" id="table4" title="Exporting Processes" width="665">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table4");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table4");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table4");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table4");
  table.initAllRows();
}
/*
 * Set the selected state for the given row groups
 * displayed in the table.  This functionality requires
 * the 'selectId' of the tableColumn to be set.
 *
 * @param rowGroupId HTML element id of the tableRowGroup component
 * @param selected Flag indicating whether components should be selected
 */
function selectGroupRows(rowGroupId, selected) {
  var table = document.getElementById("form1:table4");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table4");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table4:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table4:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{editConfig.tableRowGroup4}" id="tableRowGroup4" rows="10"
                                        sourceData="#{SessionBean1.exportingDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{editConfig.tableColumn21}" headerText="ID" id="tableColumn21" sort="id">
                                            <ui:staticText binding="#{editConfig.staticText10}" id="staticText10" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn22}" headerText="Collectors" id="tableColumn22"
                                            sort="collectorListLink" width="92">
                                            <ui:staticText binding="#{editConfig.staticText11}" escape="false" id="staticText11" text="#{currentRow.value['collectorListLink']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn23}" headerText="Max. Export Delay" id="tableColumn23"
                                            sort="restrictionsMaxExportDelay" width="34">
                                            <ui:staticText binding="#{editConfig.staticText12}" id="staticText12" text="#{currentRow.value['restrictionsMaxExportDelay']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn24}" headerText="Unit" id="tableColumn24"
                                            sort="restrictionsMaxExportDelayUnit" width="40">
                                            <ui:staticText binding="#{editConfig.staticText13}" id="staticText13" text="#{currentRow.value['restrictionsMaxExportDelayUnit']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn25}" headerText="Max. Packetsize" id="tableColumn25" sort="restrictionsMaxPacketSize">
                                            <ui:staticText binding="#{editConfig.staticText14}" id="staticText14" text="#{currentRow.value['restrictionsMaxPacketSize']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn26}" headerText="UDP Refresh Timeout" id="tableColumn26" sort="udpTemplateRefreshTimeout">
                                            <ui:staticText binding="#{editConfig.staticText15}" id="staticText15" text="#{currentRow.value['udpTemplateRefreshTimeout']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn27}" headerText="Unit" id="tableColumn27" sort="udpTemplateRefreshTimeoutUnit">
                                            <ui:staticText binding="#{editConfig.staticText16}" id="staticText16" text="#{currentRow.value['udpTemplateRefreshTimeoutUnit']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn28}" headerText="UDP Refreshrate" id="tableColumn28" sort="udpTemplateRefreshrate">
                                            <ui:staticText binding="#{editConfig.staticText17}" id="staticText17" text="#{currentRow.value['udpTemplateRefreshrate']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn29}" headerText="Edit" id="tableColumn29" width="47">
                                            <ui:button action="#{editConfig.edit_ExportingProcess_action}" binding="#{editConfig.edit_ExportingProcess}"
                                                id="edit_ExportingProcess" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{editConfig.tableColumn30}" headerText="Remove" id="tableColumn30">
                                            <ui:button action="#{editConfig.remove_ExportingProcess_action}" binding="#{editConfig.remove_ExportingProcess}"
                                                id="remove_ExportingProcess" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{editConfig.add_exportingProcess_action}" binding="#{editConfig.add_exportingProcess}"
                                    id="add_exportingProcess" text="Add Exporting Process"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                        </h:panelGrid>
                        <ui:alert binding="#{editConfig.alert1}" id="alert1" style="left: 600px; top: 72px; position: absolute; width: 166px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
