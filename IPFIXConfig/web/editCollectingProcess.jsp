<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editCollectingProcess.page1}" id="page1">
            <ui:html binding="#{editCollectingProcess.html1}" id="html1">
                <ui:head binding="#{editCollectingProcess.head1}" id="head1">
                    <ui:link binding="#{editCollectingProcess.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editCollectingProcess.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editCollectingProcess.form1}" id="form1">
                        <ui:label binding="#{editCollectingProcess.label2}" id="label2" labelLevel="1" style="left: 24px; top: 0px; position: absolute" text="Edit Collecting Process"/>
                        <ui:button action="#{editCollectingProcess.save_changes_action}" binding="#{editCollectingProcess.save_changes}" id="save_changes"
                            style="left: 23px; top: 48px; position: absolute" text="Save changes and return"/>
                        <ui:button action="#{editCollectingProcess.discard_action}" binding="#{editCollectingProcess.discard}" id="discard"
                            style="left: 215px; top: 48px; position: absolute" text="Discard changes and return"/>
                        <ui:button action="#{editCollectingProcess.add_Listener_action}" binding="#{editCollectingProcess.add_Listener}" id="add_Listener"
                            style="left: 23px; top: 288px; position: absolute" text="Add Listener"/>
                        <ui:messageGroup binding="#{editCollectingProcess.messageGroup1}" id="messageGroup1" style="left: 624px; top: 192px; position: absolute; width: 190px"/>
                        <h:panelGrid binding="#{editCollectingProcess.gridPanel1}" columns="2" id="gridPanel1"
                            style="height: 96px; left: 24px; top: 72px; position: absolute" width="792">
                            <ui:textField binding="#{editCollectingProcess.idField}" columns="3" converter="#{editCollectingProcess.integerConverter1}"
                                id="idField" label="ID:" required="true" text="#{editCollectingProcess.collectingProcess.id}"/>
                            <ui:message binding="#{editCollectingProcess.message1}" for="idField" id="message1" showDetail="false" showSummary="true"/>
                            <ui:textField binding="#{editCollectingProcess.UDPLifetimeField}" columns="6" converter="#{editCollectingProcess.integerConverter1}"
                                id="UDPLifetimeField" label="UDP Template Lifetime" text="#{editCollectingProcess.collectingProcess.udpTemplateLifetime}"/>
                            <ui:dropDown binding="#{editCollectingProcess.UDPUnitField}" id="UDPUnitField"
                                items="#{ApplicationBean1.templateTimeoutUnitOptions}" label="UDP Template Unit" selected="#{editCollectingProcess.collectingProcess.udpTemplateUnit}"/>
                            <ui:textField binding="#{editCollectingProcess.domainId_field}" columns="6" converter="#{editCollectingProcess.integerConverter1}"
                                id="domainId_field" label="Observation Domain ID:" text="#{editCollectingProcess.collectingProcess.observationDomainId}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{editCollectingProcess.gridPanel2}" columns="4" id="gridPanel2"
                            style="border-width: 1px; border-style: solid; height: 96px; left: 24px; top: 192px; position: absolute" width="576">
                            <ui:dropDown binding="#{editCollectingProcess.ipAdresstypeField}" id="ipAdresstypeField"
                                items="#{ApplicationBean1.addressTypeOptions}" label="IP Adresstype:" selected="#{editCollectingProcess.listener.ipAddresstype}"/>
                            <ui:textField binding="#{editCollectingProcess.ipAdressField}" id="ipAdressField" label="IP Adress:"
                                text="#{editCollectingProcess.listener.ipAddress}" validator="#{editCollectingProcess.ipAdressField_validate}"/>
                            <ui:dropDown binding="#{editCollectingProcess.transportProtocolField}" id="transportProtocolField"
                                items="#{ApplicationBean1.transportProtocolOptions}" label="Transport Protocol:" selected="#{editCollectingProcess.listener.transportProtocol}"/>
                            <ui:textField binding="#{editCollectingProcess.portField}" columns="6" converter="#{editCollectingProcess.integerConverter1}"
                                id="portField" label="Port:" text="#{editCollectingProcess.listener.port}" validator="#{editCollectingProcess.longRangeValidator1.validate}"/>
                        </h:panelGrid>
                        <ui:table augmentTitle="false" binding="#{editCollectingProcess.table1}" id="table1"
                            style="left: 24px; top: 312px; position: absolute; width: 360px" title="Listener" width="720">
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
                            <ui:tableRowGroup binding="#{editCollectingProcess.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{SessionBean1.listenerDataProvider}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{editCollectingProcess.tableColumn1}" headerText="IPAddresstype" id="tableColumn1" sort="ipAddresstypeString">
                                    <ui:staticText binding="#{editCollectingProcess.staticText1}" id="staticText1" text="#{currentRow.value['ipAddresstypeString']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editCollectingProcess.tableColumn2}" headerText="IPAddress" id="tableColumn2" sort="ipAddress">
                                    <ui:staticText binding="#{editCollectingProcess.staticText2}" id="staticText2" text="#{currentRow.value['ipAddress']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editCollectingProcess.tableColumn3}" headerText="Transportprotocol" id="tableColumn3" sort="transportProtocolString">
                                    <ui:staticText binding="#{editCollectingProcess.staticText3}" id="staticText3" text="#{currentRow.value['transportProtocolString']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editCollectingProcess.tableColumn4}" headerText="Port" id="tableColumn4" sort="port">
                                    <ui:staticText binding="#{editCollectingProcess.staticText4}" id="staticText4" text="#{currentRow.value['port']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editCollectingProcess.tableColumn5}" headerText="Edit" id="tableColumn5">
                                    <ui:button action="#{editCollectingProcess.edit_Listener_action}" binding="#{editCollectingProcess.edit_Listener}"
                                        id="edit_Listener" text="Edit"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editCollectingProcess.tableColumn6}" headerText="Remove" id="tableColumn6">
                                    <ui:button action="#{editCollectingProcess.remove_Listener_action}" binding="#{editCollectingProcess.remove_Listener}"
                                        id="remove_Listener" text="Remove"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
