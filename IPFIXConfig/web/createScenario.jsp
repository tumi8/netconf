<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{createScenario.page1}" id="page1">
            <ui:html binding="#{createScenario.html1}" id="html1">
                <ui:head binding="#{createScenario.head1}" id="head1">
                    <ui:link binding="#{createScenario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{createScenario.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{createScenario.form1}" id="form1">
                        <ui:textField binding="#{createScenario.name1}" id="name1" label="Name:" required="true"
                            style="left: 24px; top: 72px; position: absolute" text="#{createScenario.scenario.name}" validator="#{createScenario.name_validate}"/>
                        <ui:label binding="#{createScenario.label1}" id="label1" labelLevel="1" style="left: 24px; top: 24px; position: absolute" text="Create Scenario"/>
                        <ui:textArea binding="#{createScenario.description1}" id="description1" label="Description:"
                            style="left: 24px; top: 120px; position: absolute" text="#{createScenario.scenario.description}"/>
                        <ui:table augmentTitle="false" binding="#{createScenario.table1}" id="table1"
                            style="height: 166px; left: 24px; top: 240px; position: absolute; width: 1200px" title="Devices" width="1200">
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
                            <ui:tableRowGroup binding="#{createScenario.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{ApplicationBean1.appDeviceDataProvider}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{createScenario.tableColumn1}" headerText="Name" id="tableColumn1" sort="name">
                                    <ui:staticText binding="#{createScenario.staticText1}" id="staticText1" text="#{currentRow.value['name']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn2}" headerText="Type" id="tableColumn2" sort="type">
                                    <ui:staticText binding="#{createScenario.staticText2}" id="staticText2" text="#{currentRow.value['type']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn3}" headerText="Description" id="tableColumn3" sort="description">
                                    <ui:staticText binding="#{createScenario.staticText3}" id="staticText3" text="#{currentRow.value['description']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn4}" headerText="Protocol" id="tableColumn4" sort="protocol">
                                    <ui:staticText binding="#{createScenario.staticText4}" id="staticText4" text="#{currentRow.value['protocol']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn5}" headerText="Host" id="tableColumn5" sort="hostIP">
                                    <ui:staticText binding="#{createScenario.staticText5}" id="staticText5" text="#{currentRow.value['hostIP']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn6}" headerText="Port" id="tableColumn6" sort="port">
                                    <ui:staticText binding="#{createScenario.staticText6}" id="staticText6" text="#{currentRow.value['port']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn7}" headerText="Role" id="tableColumn7" sort="role">
                                    <ui:staticText binding="#{createScenario.staticText7}" id="staticText7" text="#{currentRow.value['role']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn8}" headerText="User" id="tableColumn8" sort="user">
                                    <ui:staticText binding="#{createScenario.staticText8}" id="staticText8" text="#{currentRow.value['user']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn9}" headerText="Monitored Network" id="tableColumn9" sort="monitoredNetwork">
                                    <ui:staticText binding="#{createScenario.staticText9}" id="staticText9" text="#{currentRow.value['monitoredNetwork']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{createScenario.tableColumn10}" headerText="Add to Scenario" id="tableColumn10">
                                    <ui:checkbox binding="#{createScenario.checkbox1}" id="checkbox1" selected="#{createScenario.selectedDevice}"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:button action="#{createScenario.button1_action}" binding="#{createScenario.button1}" id="button1"
                            style="position: absolute; left: 24px; top: 192px" text="Save new Scenario"/>
                        <ui:button action="discard" binding="#{createScenario.button2}" id="button2" style="position: absolute; left: 192px; top: 192px" text="Discard"/>
                        <ui:messageGroup binding="#{createScenario.messageGroup1}" id="messageGroup1" style="position: absolute; left: 408px; top: 24px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
