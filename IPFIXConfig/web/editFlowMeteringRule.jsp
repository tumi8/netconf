<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editFlowMeteringRule.page1}" id="page1">
            <ui:html binding="#{editFlowMeteringRule.html1}" id="html1">
                <ui:head binding="#{editFlowMeteringRule.head1}" id="head1">
                    <ui:link binding="#{editFlowMeteringRule.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editFlowMeteringRule.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editFlowMeteringRule.form1}" id="form1">
                        <ui:label binding="#{editFlowMeteringRule.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Flow Metering Rule"/>
                        <ui:textField binding="#{editFlowMeteringRule.template_id_field}" columns="3" converter="#{editFlowMeteringRule.integerConverter1}"
                            id="template_id_field" label="Template ID:" required="true" style="left: 24px; top: 48px; position: absolute" text="#{editFlowMeteringRule.flowMeteringRule.templateId}"/>
                        <ui:button action="#{editFlowMeteringRule.add_new_flowkey_button_action}" binding="#{editFlowMeteringRule.add_new_flowkey_button}"
                            id="add_new_flowkey_button" style="left: 23px; top: 264px; position: absolute" text="Add Flowkey"/>
                        <ui:button action="#{editFlowMeteringRule.add_new_nonflowkey_button_action}" binding="#{editFlowMeteringRule.add_new_nonflowkey_button}"
                            id="add_new_nonflowkey_button" style="left: 239px; top: 264px; position: absolute" text="Add Non Flowkey"/>
                        <ui:messageGroup binding="#{editFlowMeteringRule.messageGroup1}" id="messageGroup1" style="left: 360px; top: 24px; position: absolute"/>
                        <ui:button action="#{editFlowMeteringRule.save_changes_button_action}" binding="#{editFlowMeteringRule.save_changes_button}"
                            id="save_changes_button" style="left: 23px; top: 96px; position: absolute" text="Save changes"/>
                        <ui:button action="case1" binding="#{editFlowMeteringRule.discard_changes_button}" id="discard_changes_button"
                            style="left: 143px; top: 96px; position: absolute" text="Discard changes"/>
                        <h:panelGrid binding="#{editFlowMeteringRule.gridPanel1}" columns="2" id="gridPanel1"
                            style="border-width: 1px; border-style: solid; height: 100px; left: 24px; top: 144px; position: absolute" width="624">
                            <ui:textField binding="#{editFlowMeteringRule.id_field}" columns="3" converter="#{editFlowMeteringRule.integerConverter1}"
                                id="id_field" label="ID:" text="#{editFlowMeteringRule.newKey.ieId}"/>
                            <ui:textField binding="#{editFlowMeteringRule.enterprise_Nr_field}" columns="6"
                                converter="#{editFlowMeteringRule.integerConverter1}" id="enterprise_Nr_field" label="Enterprise Nr.:" text="#{editFlowMeteringRule.newKey.enterpriseNumber}"/>
                            <ui:textField binding="#{editFlowMeteringRule.name_field}" columns="15" id="name_field" label="Name:" text="#{editFlowMeteringRule.newKey.ieName}"/>
                            <ui:textField binding="#{editFlowMeteringRule.length_field}" columns="6" converter="#{editFlowMeteringRule.integerConverter1}"
                                id="length_field" label="Length:" text="#{editFlowMeteringRule.newKey.ieLength}"/>
                            <ui:textField binding="#{editFlowMeteringRule.match_field}" columns="15" id="match_field" label="Match:" text="#{editFlowMeteringRule.newKey.match}"/>
                            <ui:textField binding="#{editFlowMeteringRule.modifier_field}" columns="15" id="modifier_field" label="Modifier:" text="#{editFlowMeteringRule.newKey.modifier}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{editFlowMeteringRule.gridPanel2}" id="gridPanel2"
                            style="height: 216px; left: 24px; top: 288px; position: absolute" width="792">
                            <ui:table augmentTitle="false" binding="#{editFlowMeteringRule.table1}" id="table1" title="Flow keys" width="720">
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
                                <ui:tableRowGroup binding="#{editFlowMeteringRule.tableRowGroup1}" id="tableRowGroup1" rows="5"
                                    sourceData="#{SessionBean1.flowkeyDataProvider}" sourceVar="currentRow">
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn1}" headerText="ID" id="tableColumn1" sort="ieId">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText1}" id="staticText1" text="#{currentRow.value['ieId']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn2}" headerText="E.Nr." id="tableColumn2" sort="enterpriseNumber">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText2}" id="staticText2" text="#{currentRow.value['enterpriseNumber']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn3}" headerText="Length" id="tableColumn3" sort="ieLength">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText3}" id="staticText3" text="#{currentRow.value['ieLength']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn7}" headerText="Name" id="tableColumn7" sort="ieName">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText7}" id="staticText7" text="#{currentRow.value['ieName']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn8}" headerText="Match" id="tableColumn8" sort="match">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText8}" id="staticText8" text="#{currentRow.value['match']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn9}" headerText="Modifier" id="tableColumn9" sort="modifier">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText9}" id="staticText9" text="#{currentRow.value['modifier']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn13}" headerText="Edit" id="tableColumn13">
                                        <ui:button action="#{editFlowMeteringRule.edit_flowkey_button_action}"
                                            binding="#{editFlowMeteringRule.edit_flowkey_button}" id="edit_flowkey_button" text="Edit"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn14}" headerText="Remove" id="tableColumn14">
                                        <ui:button action="#{editFlowMeteringRule.remove_flowkey_button_action}"
                                            binding="#{editFlowMeteringRule.remove_flowkey_button}" id="remove_flowkey_button" text="Remove"/>
                                    </ui:tableColumn>
                                </ui:tableRowGroup>
                            </ui:table>
                        </h:panelGrid>
                        <h:panelGrid binding="#{editFlowMeteringRule.gridPanel3}" id="gridPanel3"
                            style="height: 264px; left: 24px; top: 528px; position: absolute" width="792">
                            <ui:table augmentTitle="false" binding="#{editFlowMeteringRule.table2}" id="table2" style="width: 720px" title="Non Flow keys" width="720">
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
                                <ui:tableRowGroup binding="#{editFlowMeteringRule.tableRowGroup2}" id="tableRowGroup2" rows="5"
                                    sourceData="#{SessionBean1.nonFlowKeyDataProvider}" sourceVar="currentRow">
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn4}" headerText="ID" id="tableColumn4" sort="ieId">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText4}" id="staticText4" text="#{currentRow.value['ieId']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn5}" headerText="E.Nr." id="tableColumn5" sort="enterpriseNumber">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText5}" id="staticText5" text="#{currentRow.value['enterpriseNumber']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn6}" headerText="Length" id="tableColumn6" sort="ieLength">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText6}" id="staticText6" text="#{currentRow.value['ieLength']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn10}" headerText="Name" id="tableColumn10" sort="ieName">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText10}" id="staticText10" text="#{currentRow.value['ieName']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn11}" headerText="Match" id="tableColumn11" sort="match">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText11}" id="staticText11" text="#{currentRow.value['match']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn12}" headerText="Modifier" id="tableColumn12" sort="modifier">
                                        <ui:staticText binding="#{editFlowMeteringRule.staticText12}" id="staticText12" text="#{currentRow.value['modifier']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn15}" headerText="Edit" id="tableColumn15">
                                        <ui:button action="#{editFlowMeteringRule.edit_nonflowkey_button_action}"
                                            binding="#{editFlowMeteringRule.edit_nonflowkey_button}" id="edit_nonflowkey_button" text="Edit"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMeteringRule.tableColumn16}" headerText="remove" id="tableColumn16">
                                        <ui:button action="#{editFlowMeteringRule.remove_nonflowkey_button_action}"
                                            binding="#{editFlowMeteringRule.remove_nonflowkey_button}" id="remove_nonflowkey_button" text="Remove"/>
                                    </ui:tableColumn>
                                </ui:tableRowGroup>
                            </ui:table>
                        </h:panelGrid>
                        <ui:label binding="#{editFlowMeteringRule.label2}" id="label2" style="position: absolute; left: 24px; top: 120px" text="Key Data:"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
