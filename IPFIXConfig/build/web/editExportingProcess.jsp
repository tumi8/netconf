<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editExportingProcess.page1}" id="page1">
            <ui:html binding="#{editExportingProcess.html1}" id="html1">
                <ui:head binding="#{editExportingProcess.head1}" id="head1">
                    <ui:link binding="#{editExportingProcess.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editExportingProcess.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editExportingProcess.form1}" id="form1" virtualFormsConfig="save | gridPanel1:maxpacketsize idField1 gridPanel1:maxExportDelay gridPanel3:refreshtimeout gridPanel1:exportdelay_unit gridPanel3:timeout_unit gridPanel3:refreshrate | save_changes1 , discard | | discard1 , collector | gridPanel2:ipAdresstypeField1 gridPanel2:ipAdressField1 gridPanel2:transportProtocolField1 gridPanel2:portField1 | add_collector">
                        <h:panelGrid binding="#{editExportingProcess.gridPanel1}" columns="3" id="gridPanel1"
                            style="border-width: 1px; border-style: solid; height: 96px; left: 24px; top: 120px; position: absolute" width="576">
                            <ui:textField binding="#{editExportingProcess.maxpacketsize}" columns="6" converter="#{editExportingProcess.integerConverter1}"
                                id="maxpacketsize" label="Max. Packetsize:" text="#{editExportingProcess.exportingProcess.restrictionsMaxPacketSize}"/>
                            <ui:textField binding="#{editExportingProcess.maxExportDelay}" columns="6" converter="#{editExportingProcess.integerConverter1}"
                                id="maxExportDelay" label="Max. Exportdelay:" text="#{editExportingProcess.exportingProcess.restrictionsMaxExportDelay}"/>
                            <ui:dropDown binding="#{editExportingProcess.exportdelay_unit}" id="exportdelay_unit"
                                items="#{ApplicationBean1.templateTimeoutUnitOptions}" label="Unit:" selected="#{editExportingProcess.exportingProcess.restrictionsMaxExportDelayUnit}"/>
                        </h:panelGrid>
                        <ui:button action="#{editExportingProcess.save_changes_action}" binding="#{editExportingProcess.save_changes1}" id="save_changes1"
                            style="left: 143px; top: 48px; position: absolute" text="Save changes and return"/>
                        <ui:button action="#{editExportingProcess.add_Listener_action}" binding="#{editExportingProcess.add_collector}" id="add_collector"
                            style="left: 23px; top: 480px; position: absolute" text="Add Collector"/>
                        <ui:messageGroup binding="#{editExportingProcess.messageGroup1}" id="messageGroup1" style="left: 624px; top: 48px; position: absolute; width: 190px"/>
                        <h:panelGrid binding="#{editExportingProcess.gridPanel2}" columns="4" id="gridPanel2"
                            style="border-width: 1px; border-style: solid; height: 96px; left: 24px; top: 384px; position: absolute" width="576">
                            <ui:dropDown binding="#{editExportingProcess.ipAdresstypeField1}" id="ipAdresstypeField1"
                                items="#{ApplicationBean1.addressTypeOptions}" label="IP Adresstype:" selected="#{editExportingProcess.collector.ipAddresstype}"/>
                            <ui:textField binding="#{editExportingProcess.ipAdressField1}" id="ipAdressField1" label="IP Adress:"
                                text="#{editExportingProcess.collector.ipAddress}" validator="#{editExportingProcess.ipAdressField1_validate}"/>
                            <ui:dropDown binding="#{editExportingProcess.transportProtocolField1}" id="transportProtocolField1"
                                items="#{ApplicationBean1.transportProtocolOptions}" label="Transport Protocol:" selected="#{editExportingProcess.collector.transportProtocol}"/>
                            <ui:textField binding="#{editExportingProcess.portField1}" columns="6" converter="#{editExportingProcess.integerConverter1}"
                                id="portField1" label="Port:" text="#{editExportingProcess.collector.port}" validator="#{editExportingProcess.longRangeValidator1.validate}"/>
                        </h:panelGrid>
                        <ui:button action="#{editExportingProcess.discard_action}" binding="#{editExportingProcess.discard1}" id="discard1"
                            style="left: 335px; top: 48px; position: absolute" text="Discard changes and return"/>
                        <h:panelGrid binding="#{editExportingProcess.gridPanel3}" columns="3" id="gridPanel3"
                            style="border-width: 1px; border-style: solid; height: 96px; left: 24px; top: 240px; position: absolute" width="576">
                            <ui:textField binding="#{editExportingProcess.refreshtimeout}" columns="6" converter="#{editExportingProcess.integerConverter1}"
                                id="refreshtimeout" label="Refresh Timeout:" text="#{editExportingProcess.exportingProcess.udpTemplateRefreshTimeout}"/>
                            <ui:dropDown binding="#{editExportingProcess.timeout_unit}" id="timeout_unit" items="#{ApplicationBean1.templateTimeoutUnitOptions}"
                                label="Unit:" selected="#{editExportingProcess.exportingProcess.udpTemplateRefreshTimeoutUnit}"/>
                            <ui:textField binding="#{editExportingProcess.refreshrate}" columns="6" converter="#{editExportingProcess.integerConverter1}"
                                id="refreshrate" label="Refreshrate:" text="#{editExportingProcess.exportingProcess.udpTemplateRefreshrate}"/>
                        </h:panelGrid>
                        <ui:textField binding="#{editExportingProcess.idField1}" columns="3" converter="#{editExportingProcess.integerConverter1}" id="idField1"
                            label="ID:" required="true" style="left: 24px; top: 48px; position: absolute" text="#{editExportingProcess.exportingProcess.id}"/>
                        <ui:label binding="#{editExportingProcess.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 0px" text="Edit Exporting Process"/>
                        <ui:label binding="#{editExportingProcess.label2}" id="label2" style="position: absolute; left: 24px; top: 216px" text="UDP Template Management:"/>
                        <ui:label binding="#{editExportingProcess.label3}" id="label3" style="left: 24px; top: 96px; position: absolute" text="Packetrestrictions:"/>
                        <ui:table augmentTitle="false" binding="#{editExportingProcess.table1}" id="table1"
                            style="left: 24px; top: 528px; position: absolute; width: 597px" title="Receiving Collectors" width="597">
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
                            <ui:tableRowGroup binding="#{editExportingProcess.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{SessionBean1.receivingCollectorsDataProvider}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{editExportingProcess.tableColumn1}" headerText="IPAddresstype" id="tableColumn1" sort="ipAddresstypeString">
                                    <ui:staticText binding="#{editExportingProcess.staticText1}" id="staticText1" text="#{currentRow.value['ipAddresstypeString']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editExportingProcess.tableColumn2}" headerText="IPAddress" id="tableColumn2" sort="ipAddress" width="105">
                                    <ui:staticText binding="#{editExportingProcess.staticText2}" id="staticText2" text="#{currentRow.value['ipAddress']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editExportingProcess.tableColumn3}" headerText="TransportProtocol" id="tableColumn3"
                                    sort="transportProtocolString" width="105">
                                    <ui:staticText binding="#{editExportingProcess.staticText3}" id="staticText3" text="#{currentRow.value['transportProtocolString']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editExportingProcess.tableColumn4}" headerText="Port" id="tableColumn4" sort="port">
                                    <ui:staticText binding="#{editExportingProcess.staticText4}" id="staticText4" text="#{currentRow.value['port']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editExportingProcess.tableColumn5}" headerText="Edit" id="tableColumn5">
                                    <ui:button binding="#{editExportingProcess.edit_collector}" id="edit_collector" text="Edit"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editExportingProcess.tableColumn6}" headerText="Remove" id="tableColumn6">
                                    <ui:button binding="#{editExportingProcess.remove_collector}" id="remove_collector" text="Remove"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:label binding="#{editExportingProcess.label4}" id="label4" style="position: absolute; left: 24px; top: 360px" text="Collector:"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
