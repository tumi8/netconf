<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{setScenario.page1}" id="page1">
            <ui:html binding="#{setScenario.html1}" id="html1">
                <ui:head binding="#{setScenario.head1}" id="head1">
                    <ui:link binding="#{setScenario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{setScenario.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{setScenario.form1}" id="form1">
                        <ui:label binding="#{setScenario.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Set Scenario"/>
                        <ui:staticText binding="#{setScenario.namefield}" id="namefield" style="left: 96px; top: 72px; position: absolute" text="#{setScenario.scenario.name}"/>
                        <ui:label binding="#{setScenario.label2}" id="label2" style="position: absolute; left: 24px; top: 72px" text="Name:"/>
                        <ui:label binding="#{setScenario.label3}" id="label3" style="position: absolute; left: 24px; top: 120px" text="Description:"/>
                        <ui:staticText binding="#{setScenario.descriptionfield}" id="descriptionfield" style="position: absolute; left: 120px; top: 120px" text="#{setScenario.scenario.description}"/>
                        <ui:table augmentTitle="false" binding="#{setScenario.table1}" id="table1"
                            style="left: 24px; top: 216px; position: absolute; width: 647px" title="Devices" width="810">
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
                            <ui:tableRowGroup binding="#{setScenario.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{SessionBean1.scenarioDeviceDataProvider}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{setScenario.tableColumn1}" headerText="Name" id="tableColumn1" sort="name">
                                    <ui:staticText binding="#{setScenario.staticText1}" id="staticText1" text="#{currentRow.value['name']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn2}" headerText="Type" id="tableColumn2" sort="type">
                                    <ui:staticText binding="#{setScenario.staticText2}" id="staticText2" text="#{currentRow.value['type']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn3}" headerText="Description" id="tableColumn3" sort="description">
                                    <ui:staticText binding="#{setScenario.staticText3}" id="staticText3" text="#{currentRow.value['description']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn5}" headerText="Host" id="tableColumn5" sort="hostIP">
                                    <ui:staticText binding="#{setScenario.staticText5}" id="staticText5" text="#{currentRow.value['hostIP']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn6}" headerText="Port" id="tableColumn6" sort="port">
                                    <ui:staticText binding="#{setScenario.staticText6}" id="staticText6" text="#{currentRow.value['port']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn7}" headerText="Role" id="tableColumn7" sort="role">
                                    <ui:staticText binding="#{setScenario.staticText7}" id="staticText7" text="#{currentRow.value['role']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn8}" headerText="Monitored Network" id="tableColumn8" sort="monitoredNetwork">
                                    <ui:staticText binding="#{setScenario.staticText8}" id="staticText8" text="#{currentRow.value['monitoredNetwork']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{setScenario.tableColumn4}" headerText="Status" id="tableColumn4" sort="status">
                                    <ui:staticText binding="#{setScenario.staticText4}" id="staticText4" text="#{currentRow.value['status']}"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:button action="#{setScenario.setScenarioButton_action}" binding="#{setScenario.setScenarioButton}" id="setScenarioButton"
                            style="left: 23px; top: 168px; position: absolute" text="Set Scenario"/>
                        <ui:alert binding="#{setScenario.alert1}" id="alert1" style="left: 408px; top: 72px; position: absolute"/>
                        <ui:button action="case1" binding="#{setScenario.returnButton}" id="returnButton" style="position: absolute; left: 168px; top: 168px" text="Return"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
