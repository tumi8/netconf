<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{createConfig.page1}" id="page1">
            <ui:html binding="#{createConfig.html1}" id="html1">
                <ui:head binding="#{createConfig.head1}" id="head1" title="Create Configuration">
                    <ui:link binding="#{createConfig.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{createConfig.body1}" id="body1" style="-rave-layout: grid">
                    <br/>
                    <ui:form binding="#{createConfig.form1}" id="form1" virtualFormsConfig="save Config form | gridPanel3:configName gridPanel3:fileName gridPanel3:descript gridPanel6:gridPanel1:observationPointTable:tableRowGroup1:tableColumn23:checkbox2 gridPanel6:gridPanel2:collectingProcessTable:tableRowGroup2:tableColumn3:checkbox3 gridPanel6:gridPanel4:meteringProcessTable:tableRowGroup3:tableColumn18:checkbox1 gridPanel6:gridPanel5:exportingProcessTable:tableRowGroup4:tableColumn34:checkbox4 | save_config_button , do something else form | | gridPanel6:gridPanel1:add_ObservationPoint discard_changes_button gridPanel6:gridPanel1:observationPointTable:tableRowGroup1:tableColumn21:edit_Observationpoint_button gridPanel6:gridPanel1:observationPointTable:tableRowGroup1:tableColumn22:remove_observation_point gridPanel6:gridPanel1:save_observationpoints gridPanel6:gridPanel2:add_CollectingProcess gridPanel6:gridPanel2:save_collecting gridPanel6:gridPanel4:add_metering_process gridPanel6:gridPanel4:save_metering gridPanel6:gridPanel5:add_exportingProcess gridPanel6:gridPanel2:collectingProcessTable:tableRowGroup2:tableColumn11:edit_CollectingProcess gridPanel6:gridPanel2:collectingProcessTable:tableRowGroup2:tableColumn12:remove_CollectingProcess gridPanel6:gridPanel4:meteringProcessTable:tableRowGroup3:tableColumn14:edit_PacketSelection gridPanel6:gridPanel4:meteringProcessTable:tableRowGroup3:tableColumn15:edit_PacketReporting gridPanel6:gridPanel4:meteringProcessTable:tableRowGroup3:tableColumn16:edit_FlowMetering gridPanel6:gridPanel2:collectingProcessTable:tableRowGroup2:tableColumn10:collecting_next gridPanel6:gridPanel4:meteringProcessTable:tableRowGroup3:tableColumn17:metering_next gridPanel6:gridPanel4:meteringProcessTable:tableRowGroup3:tableColumn19:remove_MeteringProcess gridPanel6:gridPanel5:save_exporting gridPanel6:gridPanel5:exportingProcessTable:tableRowGroup4:tableColumn32:edit_ExportingProcess gridPanel6:gridPanel5:exportingProcessTable:tableRowGroup4:tableColumn33:remove_ExportingProcess gridPanel6:gridPanel1:observationPointTable:tableRowGroup1:tableColumn20:observ_next gridPanel6:gridPanel1:observationPointTable:add_ObservationPoint gridPanel6:gridPanel2:collectingProcessTable:add_CollectingProcess gridPanel6:gridPanel4:meteringProcessTable:add_metering_process gridPanel6:gridPanel5:exportingProcessTable:add_exportingProcess">
                        <ui:label binding="#{createConfig.label1}" id="label1" labelLevel="1" style="left: 24px; top: 24px; position: absolute" text="Create new Configuration"/>
                        <ui:messageGroup binding="#{createConfig.messageGroup1}" id="messageGroup1" style="height: 94px; left: 984px; top: 72px; position: absolute; width: 214px"/>
                        <h:panelGrid binding="#{createConfig.gridPanel3}" columns="2" id="gridPanel3" style="left: 24px; top: 72px; position: absolute">
                            <ui:textField binding="#{createConfig.configName}" id="configName" label="Configuration Name:" required="true"
                                style="height: 144px; width: 480px" text="#{createConfig.configInformation.name}" validator="#{createConfig.configName_validate}"/>
                            <ui:message binding="#{createConfig.message1}" for="configName" id="message1" showDetail="false" showSummary="true"/>
                            <ui:textField binding="#{createConfig.fileName}" id="fileName" label="Filename:" required="true"
                                text="#{createConfig.configInformation.fileName}" validator="#{createConfig.fileName_validate}"/>
                            <ui:message binding="#{createConfig.message2}" for="fileName" id="message2" showDetail="false" showSummary="true"/>
                            <ui:textArea binding="#{createConfig.descript}" columns="40" id="descript" label="Description:" style="height: 70px; width: 454px" text="#{createConfig.configInformation.description}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{createConfig.gridPanel6}" id="gridPanel6" style="height: 94px; left: 24px; top: 240px; position: absolute" width="717">
                            <h:panelGrid binding="#{createConfig.gridPanel1}" id="gridPanel1" style="height: 216px" width="983">
                                <ui:table augmentTitle="false" binding="#{createConfig.observationPointTable}" id="observationPointTable" style="height: 121px"
                                    title="Observation Points" width="958">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:observationPointTable");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:observationPointTable");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:observationPointTable");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:observationPointTable");
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
  var table = document.getElementById("form1:observationPointTable");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:observationPointTable");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:observationPointTable:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:observationPointTable:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{createConfig.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                        selected="#{createConfig.selectedObservationPoint}" sourceData="#{SessionBean1.observationDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{createConfig.tableColumn23}" headerText="Add to Config" id="tableColumn23"
                                            onClick="setTimeout('initAllRows()', 0)" selectId="checkbox2" width="57">
                                            <ui:checkbox binding="#{createConfig.checkbox2}" id="checkbox2" selected="#{createConfig.selectedObservationPoint}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn1}" headerText="ID" id="tableColumn1" sort="id">
                                            <ui:staticText binding="#{createConfig.staticText1}" id="staticText1" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn2}" headerText="DomainId" id="tableColumn2" sort="domainId">
                                            <ui:staticText binding="#{createConfig.staticText2}" id="staticText2" text="#{currentRow.value['domainId']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn5}" headerText="Type" id="tableColumn5" sort="type">
                                            <ui:staticText binding="#{createConfig.staticText5}" id="staticText5" text="#{currentRow.value['type']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn4}" headerText="Parameters" id="tableColumn4" sort="parameters">
                                            <ui:staticText binding="#{createConfig.staticText4}" id="staticText4" text="#{currentRow.value['parameters']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn20}" headerText="Next Processes" id="tableColumn20" sort="nextInfo">
                                            <ui:hyperlink action="#{createConfig.observ_next_action}" binding="#{createConfig.observ_next}" id="observ_next" text="#{currentRow.value['nextInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn21}" headerText="Edit" id="tableColumn21" width="25">
                                            <ui:button action="#{createConfig.edit_Observationpoint_button_action}"
                                                binding="#{createConfig.edit_Observationpoint_button}" id="edit_Observationpoint_button" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn22}" headerText="Remove" id="tableColumn22">
                                            <ui:button action="#{createConfig.remove_observation_point_action}"
                                                binding="#{createConfig.remove_observation_point}" id="remove_observation_point" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                        <ui:button action="#{createConfig.add_ObservationPoint_action}" binding="#{createConfig.add_ObservationPoint}"
                                            id="add_ObservationPoint" text="New Observation Point"/>
                                    </f:facet>
                                    <f:facet name="actionsBottom">
                                        <ui:button action="#{createConfig.save_observationpoints_action}" binding="#{createConfig.save_observationpoints}"
                                            id="save_observationpoints" text="Save Observation Points to Storage"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{createConfig.gridPanel2}" id="gridPanel2" style="height: 168px" width="959">
                                <ui:table augmentTitle="false" binding="#{createConfig.collectingProcessTable}" id="collectingProcessTable"
                                    title="Collecting Processes" width="742">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:collectingProcessTable");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:collectingProcessTable");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:collectingProcessTable");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:collectingProcessTable");
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
  var table = document.getElementById("form1:collectingProcessTable");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:collectingProcessTable");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:collectingProcessTable:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:collectingProcessTable:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{createConfig.tableRowGroup2}" id="tableRowGroup2" rows="10"
                                        selected="#{createConfig.selectedCollectingProcess}" sourceData="#{SessionBean1.collectingDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{createConfig.tableColumn3}" headerText="Add to Config" id="tableColumn3"
                                            onClick="setTimeout('initAllRows()', 0)" selectId="checkbox3">
                                            <ui:checkbox binding="#{createConfig.checkbox3}" id="checkbox3" selected="#{createConfig.selectedCollectingProcess}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn6}" headerText="ID" id="tableColumn6" sort="id">
                                            <ui:staticText binding="#{createConfig.staticText6}" id="staticText6" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn7}" headerText="UDP Lifetime" id="tableColumn7" sort="udpTemplateLifetime">
                                            <ui:staticText binding="#{createConfig.staticText8}" id="staticText8" text="#{currentRow.value['udpTemplateLifetime']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn8}" headerText="UDP Unit" id="tableColumn8">
                                            <ui:staticText binding="#{createConfig.staticText9}" id="staticText9" text="#{currentRow.value['udpTemplateUnit']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn9}" headerText="Listeners" id="tableColumn9" sort="listenerListLink">
                                            <ui:staticText binding="#{createConfig.staticText10}" escape="false" id="staticText10" text="#{currentRow.value['listenerListLink']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn10}" headerText="Next Processes" id="tableColumn10" sort="nextInfo">
                                            <ui:hyperlink action="#{createConfig.collecting_next_action}" binding="#{createConfig.collecting_next}"
                                                id="collecting_next" text="#{currentRow.value['nextInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn11}" headerText="Edit" id="tableColumn11">
                                            <ui:button action="#{createConfig.edit_CollectingProcess_action}" binding="#{createConfig.edit_CollectingProcess}"
                                                id="edit_CollectingProcess" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn12}" headerText="Remove" id="tableColumn12">
                                            <ui:button action="#{createConfig.remove_CollectingProcess_action}"
                                                binding="#{createConfig.remove_CollectingProcess}" id="remove_CollectingProcess" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                        <ui:button action="#{createConfig.add_CollectingProcess_action}" binding="#{createConfig.add_CollectingProcess}"
                                            id="add_CollectingProcess" text="New Collecting Process"/>
                                    </f:facet>
                                    <f:facet name="actionsBottom">
                                        <ui:button action="#{createConfig.save_collecting_action}" binding="#{createConfig.save_collecting}"
                                            id="save_collecting" text="Save Collecting Processes to Storage"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{createConfig.gridPanel4}" id="gridPanel4" width="959">
                                <ui:table augmentTitle="false" binding="#{createConfig.meteringProcessTable}" id="meteringProcessTable" style="height: 94px"
                                    title="Metering Processes" width="958">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:meteringProcessTable");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:meteringProcessTable");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:meteringProcessTable");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:meteringProcessTable");
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
  var table = document.getElementById("form1:meteringProcessTable");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:meteringProcessTable");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:meteringProcessTable:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:meteringProcessTable:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{createConfig.tableRowGroup3}" id="tableRowGroup3" rows="10"
                                        selected="#{createConfig.selectedMeteringProcess}" sourceData="#{SessionBean1.meteringDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{createConfig.tableColumn18}" headerText="Add to Config" id="tableColumn18"
                                            onClick="setTimeout('initAllRows()', 0)" selectId="checkbox1">
                                            <ui:checkbox binding="#{createConfig.checkbox1}" id="checkbox1" selected="#{createConfig.selectedMeteringProcess}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn13}" headerText="ID" id="tableColumn13" sort="id">
                                            <ui:staticText binding="#{createConfig.staticText7}" id="staticText7" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn14}" headerText="Edit PacketSelection" id="tableColumn14" sort="packetSelectionInfo">
                                            <ui:hyperlink action="#{createConfig.edit_PacketSelection_action}" binding="#{createConfig.edit_PacketSelection}"
                                                id="edit_PacketSelection" text="#{currentRow.value['packetSelectionInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn15}" headerText="Edit PacketReporting" id="tableColumn15" sort="packetReportingInfo">
                                            <ui:hyperlink action="#{createConfig.edit_PacketReporting_action}" binding="#{createConfig.edit_PacketReporting}"
                                                id="edit_PacketReporting" text="#{currentRow.value['packetReportingInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn16}" headerText="Edit FlowMetering" id="tableColumn16" sort="flowMeteringInfo">
                                            <ui:hyperlink action="#{createConfig.edit_FlowMetering_action}" binding="#{createConfig.edit_FlowMetering}"
                                                id="edit_FlowMetering" text="#{currentRow.value['flowMeteringInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn17}" headerText="Next Processes" id="tableColumn17" sort="nextInfo">
                                            <ui:hyperlink action="#{createConfig.metering_next_action}" binding="#{createConfig.metering_next}"
                                                id="metering_next" text="#{currentRow.value['nextInfo']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn19}" headerText="Remove" id="tableColumn19">
                                            <ui:button action="#{createConfig.remove_MeteringProcess_action}" binding="#{createConfig.remove_MeteringProcess}"
                                                id="remove_MeteringProcess" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                        <ui:button action="#{createConfig.add_metering_process_action}" binding="#{createConfig.add_metering_process}"
                                            id="add_metering_process" text="New Metering Process"/>
                                    </f:facet>
                                    <f:facet name="actionsBottom">
                                        <ui:button action="#{createConfig.save_metering_action}" binding="#{createConfig.save_metering}" id="save_metering" text="Save Metering Processes to Storage"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{createConfig.gridPanel5}" id="gridPanel5" style="height: 144px" width="984">
                                <ui:table augmentTitle="false" binding="#{createConfig.exportingProcessTable}" id="exportingProcessTable" style="height: 94px"
                                    title="Exporting Processes" width="958">
                                    <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:exportingProcessTable");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:exportingProcessTable");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:exportingProcessTable");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:exportingProcessTable");
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
  var table = document.getElementById("form1:exportingProcessTable");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:exportingProcessTable");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:exportingProcessTable:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:exportingProcessTable:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                    <ui:tableRowGroup binding="#{createConfig.tableRowGroup4}" id="tableRowGroup4" rows="10"
                                        selected="#{createConfig.selectedExportingProcess}" sourceData="#{SessionBean1.exportingDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{createConfig.tableColumn34}" headerText="Add to Config" id="tableColumn34"
                                            onClick="setTimeout('initAllRows()', 0)" selectId="checkbox4" width="57">
                                            <ui:checkbox binding="#{createConfig.checkbox4}" id="checkbox4" selected="#{createConfig.selectedExportingProcess}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn24}" headerText="ID" id="tableColumn24" sort="id">
                                            <ui:staticText binding="#{createConfig.staticText3}" id="staticText3" text="#{currentRow.value['id']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn25}" headerText="Collectors" id="tableColumn25"
                                            sort="collectorListLink" width="271">
                                            <ui:staticText binding="#{createConfig.staticText11}" escape="false" id="staticText11" text="#{currentRow.value['collectorListLink']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn26}" headerText="Max. Export- delay" id="tableColumn26"
                                            sort="restrictionsMaxExportDelay" width="53">
                                            <ui:staticText binding="#{createConfig.staticText12}" id="staticText12" text="#{currentRow.value['restrictionsMaxExportDelay']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn27}" headerText="Unit" id="tableColumn27" sort="restrictionsMaxExportDelayUnit">
                                            <ui:staticText binding="#{createConfig.staticText13}" id="staticText13" text="#{currentRow.value['restrictionsMaxExportDelayUnit']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn28}" headerText="Max. Packet- size" id="tableColumn28" sort="restrictionsMaxPacketSize">
                                            <ui:staticText binding="#{createConfig.staticText14}" id="staticText14" text="#{currentRow.value['restrictionsMaxPacketSize']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn29}" headerText="UDP Refresh Timeout" id="tableColumn29"
                                            sort="udpTemplateRefreshTimeout" width="78">
                                            <ui:staticText binding="#{createConfig.staticText15}" id="staticText15" text="#{currentRow.value['udpTemplateRefreshTimeout']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn30}" headerText="Unit" id="tableColumn30"
                                            sort="udpTemplateRefreshTimeoutUnit" width="194">
                                            <ui:staticText binding="#{createConfig.staticText16}" id="staticText16" text="#{currentRow.value['udpTemplateRefreshTimeoutUnit']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn31}" headerText="UDP Refresh- rate" id="tableColumn31"
                                            sort="udpTemplateRefreshrate" width="292">
                                            <ui:staticText binding="#{createConfig.staticText17}" id="staticText17" text="#{currentRow.value['udpTemplateRefreshrate']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn32}" headerText="Edit" id="tableColumn32">
                                            <ui:button action="#{createConfig.edit_ExportingProcess_action}" binding="#{createConfig.edit_ExportingProcess}"
                                                id="edit_ExportingProcess" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{createConfig.tableColumn33}" headerText="Remove" id="tableColumn33">
                                            <ui:button action="#{createConfig.remove_ExportingProcess_action}" binding="#{createConfig.remove_ExportingProcess}"
                                                id="remove_ExportingProcess" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                        <ui:button action="#{createConfig.add_exportingProcess_action}" binding="#{createConfig.add_exportingProcess}"
                                            id="add_exportingProcess" text="New Exporting Process"/>
                                    </f:facet>
                                    <f:facet name="actionsBottom">
                                        <ui:button action="#{createConfig.save_exporting_action}" binding="#{createConfig.save_exporting}" id="save_exporting" text="Save Exporting Processes to Storage"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                        </h:panelGrid>
                        <ui:button action="#{createConfig.save_config_button_action}" binding="#{createConfig.save_config_button}" id="save_config_button"
                            style="position: absolute; left: 24px; top: 192px" text="Save new Configuration"/>
                        <ui:button action="case4" binding="#{createConfig.discard_changes_button}" id="discard_changes_button"
                            style="position: absolute; left: 216px; top: 192px" text="Return without saving"/>
                        <ui:alert binding="#{createConfig.alert1}" id="alert1" style="left: 720px; top: 72px; position: absolute; width: 238px"/>
                        <br/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
