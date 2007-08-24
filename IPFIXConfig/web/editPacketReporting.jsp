<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editPacketReporting.page1}" id="page1">
            <ui:html binding="#{editPacketReporting.html1}" id="html1">
                <ui:head binding="#{editPacketReporting.head1}" id="head1">
                    <ui:link binding="#{editPacketReporting.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editPacketReporting.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editPacketReporting.form1}" id="form1">
                        <ui:label binding="#{editPacketReporting.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Packet Reporting"/>
                        <ui:textField binding="#{editPacketReporting.templateid_field}" columns="3" converter="#{editPacketReporting.integerConverter1}"
                            id="templateid_field" label="Template ID:" style="left: 24px; top: 168px; position: absolute" text="#{SessionBean1.packetReportingData.templateId}"/>
                        <h:panelGrid binding="#{editPacketReporting.gridPanel1}" columns="3" id="gridPanel1"
                            style="border-width: 1px; border-style: solid; height: 96px; left: 24px; top: 216px; position: absolute" width="720">
                            <ui:textField binding="#{editPacketReporting.id_field1}" columns="3" converter="#{editPacketReporting.integerConverter1}"
                                id="id_field1" label="ID:" text="#{editPacketReporting.reportedIE.ieId}"/>
                            <ui:textField binding="#{editPacketReporting.enterpriseNr_field1}" columns="6" converter="#{editPacketReporting.integerConverter1}"
                                id="enterpriseNr_field1" label="Enterprise Nr:" text="#{editPacketReporting.reportedIE.enterpriseNumber}"/>
                            <ui:textField binding="#{editPacketReporting.name_field1}" columns="15" id="name_field1" label="Name:"
                                text="#{editPacketReporting.reportedIE.ieName}" trim="false"/>
                            <ui:textField binding="#{editPacketReporting.match_field1}" columns="15" id="match_field1" label="Match:" text="#{editPacketReporting.reportedIE.match}"/>
                            <ui:textField binding="#{editPacketReporting.length_field1}" columns="3" converter="#{editPacketReporting.integerConverter1}"
                                id="length_field1" label="Length:" text="#{editPacketReporting.reportedIE.ieLength}"/>
                            <ui:textField binding="#{editPacketReporting.modifier_field1}" columns="15" id="modifier_field1" label="Modifier:" text="#{editPacketReporting.reportedIE.modifier}"/>
                        </h:panelGrid>
                        <ui:button action="#{editPacketReporting.save_new_rule_action}" binding="#{editPacketReporting.save_new_rule1}" id="save_new_rule1"
                            style="left: 23px; top: 336px; position: absolute" text="Add new Rule"/>
                        <ui:table augmentTitle="false" binding="#{editPacketReporting.table1}" id="table1"
                            style="left: 24px; top: 360px; position: absolute; width: 720px" title="Reporting Elements" width="720">
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
                            <ui:tableRowGroup binding="#{editPacketReporting.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{SessionBean1.sesPacketReportingDataProvider}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn1}" headerText="ID" id="tableColumn1" sort="ieId">
                                    <ui:staticText binding="#{editPacketReporting.staticText1}" id="staticText1" text="#{currentRow.value['ieId']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn2}" headerText="Enterpr.Nr." id="tableColumn2" sort="enterpriseNumber">
                                    <ui:staticText binding="#{editPacketReporting.staticText2}" id="staticText2" text="#{currentRow.value['enterpriseNumber']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn3}" headerText="Length" id="tableColumn3" sort="ieLength">
                                    <ui:staticText binding="#{editPacketReporting.staticText3}" id="staticText3" text="#{currentRow.value['ieLength']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn4}" headerText="Name" id="tableColumn4" sort="ieName">
                                    <ui:staticText binding="#{editPacketReporting.staticText4}" id="staticText4" text="#{currentRow.value['ieName']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn5}" headerText="Match" id="tableColumn5" sort="match">
                                    <ui:staticText binding="#{editPacketReporting.staticText5}" id="staticText5" text="#{currentRow.value['match']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn6}" headerText="Modifier" id="tableColumn6" sort="modifier">
                                    <ui:staticText binding="#{editPacketReporting.staticText6}" id="staticText6" text="#{currentRow.value['modifier']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn8}" headerText="Edit" id="tableColumn8">
                                    <ui:button action="#{editPacketReporting.edit_button_action}" binding="#{editPacketReporting.edit_button}" id="edit_button" text="Edit"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editPacketReporting.tableColumn7}" headerText="Remove" id="tableColumn7">
                                    <ui:button action="#{editPacketReporting.remove_button_action}" binding="#{editPacketReporting.remove_button}"
                                        id="remove_button" text="Remove"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:messageGroup binding="#{editPacketReporting.messageGroup1}" id="messageGroup1" style="left: 432px; top: 24px; position: absolute"/>
                        <ui:button action="#{editPacketReporting.save_all_button_action}" binding="#{editPacketReporting.save_all_button}" id="save_all_button"
                            style="left: 23px; top: 120px; position: absolute" text="Save all changes"/>
                        <ui:button action="#{editPacketReporting.discard_button_action}" binding="#{editPacketReporting.discard_button}" id="discard_button"
                            style="left: 191px; top: 120px; position: absolute" text="Discard changes"/>
                        <ui:textField binding="#{editPacketReporting.meteringProcess_id1}" columns="3" converter="#{editPacketReporting.integerConverter1}"
                            id="meteringProcess_id1" label="Metering Process ID:" style="left: 24px; top: 72px; position: absolute" text="#{SessionBean1.meteringProcessId}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
