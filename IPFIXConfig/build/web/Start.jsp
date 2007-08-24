<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{Start.page1}" id="page1">
            <ui:html binding="#{Start.html1}" id="html1">
                <ui:head binding="#{Start.head1}" id="head1" title="Monitor Manager">
                    <ui:link binding="#{Start.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{Start.body1}" id="body1" style="-rave-layout: grid">
                    <br/>
                    <ui:form binding="#{Start.form1}" id="form1">
                        <ui:label binding="#{Start.label1}" id="label1" labelLevel="1" style="left: 24px; top: 24px; position: absolute" text="Monitor Manager"/>
                        <h:panelGrid binding="#{Start.gridPanel5}" id="gridPanel5" style="left: 24px; top: 96px; position: absolute" width="984">
                            <h:panelGrid binding="#{Start.gridPanel1}" id="gridPanel1" style="height: 96px" width="672">
                                <ui:table augmentTitle="false" binding="#{Start.table1}" id="table1" style="width: 647px" title="Configurations" width="540">
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
                                    <ui:tableRowGroup binding="#{Start.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                        sourceData="#{ApplicationBean1.appConfigInfoDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{Start.tableColumn1}" headerText="Name" id="tableColumn1" sort="name">
                                            <ui:staticText binding="#{Start.staticText1}" id="staticText1" text="#{currentRow.value['name']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn2}" headerText="Last modified" id="tableColumn2" sort="lastmodified">
                                            <ui:staticText binding="#{Start.staticText2}" id="staticText2" text="#{currentRow.value['lastmodified']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn3}" headerText="Filename" id="tableColumn3" sort="fileName">
                                            <ui:hyperlink binding="#{Start.show_config_link}" id="show_config_link" text="#{currentRow.value['fileName']}" url="../DataStorage/#{currentRow.value['fileName']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn13}" headerText="Description" id="tableColumn13" sort="description">
                                            <ui:staticText binding="#{Start.staticText13}" id="staticText13" text="#{currentRow.value['description']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn14}" headerText="Edit" id="tableColumn14">
                                            <ui:button action="#{Start.edit_config_action}" binding="#{Start.edit_config}" id="edit_config" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn15}" headerText="Remove" id="tableColumn15">
                                            <ui:button action="#{Start.remove_config_action}" binding="#{Start.remove_config}" id="remove_config" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                        <ui:button action="#{Start.new_config_button_action}" binding="#{Start.new_config_button}" id="new_config_button" text="New Configuration"/>
                                    </f:facet>
                                    <f:facet name="actionsBottom">
                                        <ui:button action="uploadConfig" binding="#{Start.upload_config_button}" id="upload_config_button" text="Upload Configuration File"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{Start.gridPanel2}" id="gridPanel2">
                                
                                <ui:table augmentTitle="false" binding="#{Start.table2}" id="table2" style="width: 960px" title="Devices" width="786">
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
                                    <ui:tableRowGroup binding="#{Start.tableRowGroup2}" id="tableRowGroup2" rows="10"
                                        sourceData="#{ApplicationBean1.appDeviceDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{Start.tableColumn4}" headerText="Name" id="tableColumn4" sort="name">
                                            <ui:staticText binding="#{Start.staticText4}" id="staticText4" text="#{currentRow.value['name']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn5}" headerText="Type" id="tableColumn5" sort="type">
                                            <ui:staticText binding="#{Start.staticText5}" id="staticText5" text="#{currentRow.value['type']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn6}" headerText="Description" id="tableColumn6" sort="description">
                                            <ui:staticText binding="#{Start.staticText6}" id="staticText6" text="#{currentRow.value['description']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn24}" headerText="Role" id="tableColumn24" sort="role">
                                            <ui:staticText binding="#{Start.staticText3}" id="staticText3" text="#{currentRow.value['role']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn17}" headerText="Host" id="tableColumn17" sort="hostIP">
                                            <ui:staticText binding="#{Start.staticText15}" id="staticText15" text="#{currentRow.value['hostIP']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn16}" headerText="User" id="tableColumn16" sort="user">
                                            <ui:staticText binding="#{Start.staticText14}" id="staticText14" text="#{currentRow.value['user']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn20}" headerText="MonitoredNetwork" id="tableColumn20" sort="monitoredNetwork">
                                            <ui:staticText binding="#{Start.staticText18}" id="staticText18" text="#{currentRow.value['monitoredNetwork']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn21}" headerText="Edit" id="tableColumn21">
                                            <ui:button action="#{Start.edit_device_action}" binding="#{Start.edit_device}" id="edit_device" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn22}" headerText="Remove" id="tableColumn22">
                                            <ui:button action="#{Start.remove_device_action}" binding="#{Start.remove_device}" id="remove_device" text="Remove"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn25}" headerText="Configure" id="tableColumn25">
                                            <ui:button action="#{Start.setConfigbutton_action}" binding="#{Start.setConfigbutton}" id="setConfigbutton" text="Set Config"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{Start.new_device_button_action}" binding="#{Start.new_device_button}" id="new_device_button" text="New Device"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{Start.gridPanel3}" id="gridPanel3">
                                
                                <ui:table augmentTitle="false" binding="#{Start.table3}" id="table3" style="width: 480px" title="Roles" width="480">
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
                                    <ui:tableRowGroup binding="#{Start.tableRowGroup3}" id="tableRowGroup3" rows="10"
                                        sourceData="#{ApplicationBean1.appRoleDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{Start.tableColumn7}" headerText="Name" id="tableColumn7" sort="name">
                                            <ui:staticText binding="#{Start.staticText7}" id="staticText7" text="#{currentRow.value['name']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn8}" headerText="Description" id="tableColumn8" sort="description">
                                            <ui:staticText binding="#{Start.staticText8}" id="staticText8" text="#{currentRow.value['description']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn9}" headerText="Edit" id="tableColumn9">
                                            <ui:button action="#{Start.edit_role_action}" binding="#{Start.edit_role}" id="edit_role" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn23}" headerText="Remove" id="tableColumn23">
                                            <ui:button action="#{Start.remove_role_action}" binding="#{Start.remove_role}" id="remove_role" text="Remove"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{Start.new_role_action}" binding="#{Start.new_role}" id="new_role" text="New Role"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                            <h:panelGrid binding="#{Start.gridPanel4}" id="gridPanel4" width="479">
                                
                                <ui:table augmentTitle="false" binding="#{Start.table4}" id="table4" title="Scenarios" width="324">
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
                                    <ui:tableRowGroup binding="#{Start.tableRowGroup4}" id="tableRowGroup4" rows="10"
                                        sourceData="#{ApplicationBean1.appScenarioDataProvider}" sourceVar="currentRow">
                                        <ui:tableColumn binding="#{Start.tableColumn10}" headerText="Name" id="tableColumn10" sort="name">
                                            <ui:staticText binding="#{Start.staticText9}" id="staticText9" text="#{currentRow.value['name']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn11}" headerText="Description" id="tableColumn11" sort="description" width="42">
                                            <ui:staticText binding="#{Start.staticText10}" id="staticText10" text="#{currentRow.value['description']}"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn12}" headerText="Edit" id="tableColumn12">
                                            <ui:button action="#{Start.edit_scenario_action}" binding="#{Start.edit_scenario}" id="edit_scenario" text="Edit"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn18}" headerText="Remove" id="tableColumn18">
                                            <ui:button action="#{Start.remove_scenario_action}" binding="#{Start.remove_scenario}" id="remove_scenario" text="Remove"/>
                                        </ui:tableColumn>
                                        <ui:tableColumn binding="#{Start.tableColumn19}" headerText="Set Scenario" id="tableColumn19">
                                            <ui:button action="#{Start.setScenarioButton_action}" binding="#{Start.setScenarioButton}" id="setScenarioButton" text="Set Scenario"/>
                                        </ui:tableColumn>
                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                    <ui:button action="#{Start.new_scenario_action}" binding="#{Start.new_scenario}" id="new_scenario" text="New Scenario"/>
                                    </f:facet>
                                </ui:table>
                            </h:panelGrid>
                        </h:panelGrid>
                        <ui:alert binding="#{Start.alert1}" id="alert1" style="left: 648px; top: 48px; position: absolute; width: 190px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
