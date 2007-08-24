<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editFlowMetering.page1}" id="page1">
            <ui:html binding="#{editFlowMetering.html1}" id="html1">
                <ui:head binding="#{editFlowMetering.head1}" id="head1">
                    <ui:link binding="#{editFlowMetering.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editFlowMetering.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editFlowMetering.form1}" id="form1">
                        <ui:label binding="#{editFlowMetering.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Flow Metering"/>
                        <ui:messageGroup binding="#{editFlowMetering.messageGroup1}" id="messageGroup1" style="left: 456px; top: 0px; position: absolute"/>
                        <ui:button action="#{editFlowMetering.add_new_template_button_action}" binding="#{editFlowMetering.add_new_template_button}"
                            id="add_new_template_button" style="left: 263px; top: 192px; position: absolute" text="Add new Template"/>
                        <ui:button action="#{editFlowMetering.save_all_changes_button_action}" binding="#{editFlowMetering.save_all_changes_button}"
                            id="save_all_changes_button" style="left: 23px; top: 120px; position: absolute" text="Save all changes"/>
                        <ui:button action="#{editFlowMetering.discard_button_action}" binding="#{editFlowMetering.discard_button}" id="discard_button"
                            style="left: 167px; top: 120px; position: absolute" text="Discard changes"/>
                        <ui:button action="#{editFlowMetering.change_template_button_action}" binding="#{editFlowMetering.change_template_button}"
                            id="change_template_button" style="left: 263px; top: 216px; position: absolute" text="Edit selected Template"/>
                        <h:panelGrid binding="#{editFlowMetering.gridPanel2}" columns="2" id="gridPanel2"
                            style="height: 96px; left: 24px; top: 288px; position: absolute" width="672">
                            <ui:textField binding="#{editFlowMetering.active_timeout_field}" columns="6" converter="#{editFlowMetering.integerConverter1}"
                                id="active_timeout_field" label="Active Timeout:" text="#{editFlowMetering.flowMeteringData.flowExpirationActiveTimeout}"/>
                            <ui:dropDown binding="#{editFlowMetering.active_timeout_unit}" id="active_timeout_unit"
                                items="#{ApplicationBean1.templateTimeoutUnitOptions}" label="Active Timeout Unit:" selected="#{editFlowMetering.flowMeteringData.flowExpirationActiveTimeoutUnit}"/>
                            <ui:textField binding="#{editFlowMetering.inactive_timeout}" columns="6" converter="#{editFlowMetering.integerConverter1}"
                                id="inactive_timeout" label="Inactive Timeout:" text="#{editFlowMetering.flowMeteringData.flowExpirationInactiveTimeout}"/>
                            <ui:dropDown binding="#{editFlowMetering.inactive_timeout_unit}" id="inactive_timeout_unit"
                                items="#{ApplicationBean1.templateTimeoutUnitOptions}" label="Inactive Timeout Unit:" selected="#{editFlowMetering.flowMeteringData.flowExpirationInactiveTimeoutUnit}"/>
                        </h:panelGrid>
                        <ui:button action="#{editFlowMetering.show_details_action}" binding="#{editFlowMetering.show_details}" id="show_details"
                            style="left: 23px; top: 408px; position: absolute" text="Show selected details"/>
                        <ui:listbox binding="#{editFlowMetering.rules}" id="rules" items="#{editFlowMetering.ruleOptions}"
                            selected="#{SessionBean1.ruleTemplate}" style="height: 72px; left: 24px; top: 192px; position: absolute; width: 216px"/>
                        <ui:label binding="#{editFlowMetering.label2}" id="label2" style="left: 24px; top: 168px; position: absolute" text="Rule Templates:"/>
                        <h:panelGrid binding="#{editFlowMetering.gridPanel1}" id="gridPanel1" style="height: 144px; left: 24px; top: 432px; position: absolute" width="672">
                            <ui:table augmentTitle="false" binding="#{editFlowMetering.table1}" id="table1" lite="true" style="width: 671px" title="Flowkeys" width="671">
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
                                <ui:tableRowGroup binding="#{editFlowMetering.tableRowGroup1}" id="tableRowGroup1" rows="5"
                                    sourceData="#{SessionBean1.flowkeyDataProvider}" sourceVar="currentRow">
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn1}" headerText="ID" id="tableColumn1" sort="ieId">
                                        <ui:staticText binding="#{editFlowMetering.staticText1}" id="staticText1" text="#{currentRow.value['ieId']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn2}" headerText="Enterpr.Nr." id="tableColumn2" sort="enterpriseNumber">
                                        <ui:staticText binding="#{editFlowMetering.staticText2}" id="staticText2" text="#{currentRow.value['enterpriseNumber']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn3}" headerText="Name" id="tableColumn3" sort="ieName" width="233">
                                        <ui:staticText binding="#{editFlowMetering.staticText3}" id="staticText3" text="#{currentRow.value['ieName']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn7}" headerText="Length" id="tableColumn7" sort="ieLength">
                                        <ui:staticText binding="#{editFlowMetering.staticText7}" id="staticText7" text="#{currentRow.value['ieLength']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn8}" headerText="Match" id="tableColumn8" sort="match">
                                        <ui:staticText binding="#{editFlowMetering.staticText8}" id="staticText8" text="#{currentRow.value['match']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn9}" headerText="Modifier" id="tableColumn9" sort="modifier">
                                        <ui:staticText binding="#{editFlowMetering.staticText9}" id="staticText9" text="#{currentRow.value['modifier']}"/>
                                    </ui:tableColumn>
                                </ui:tableRowGroup>
                            </ui:table>
                            <ui:table augmentTitle="false" binding="#{editFlowMetering.table2}" id="table2" lite="true" style="width: 671px"
                                title="Non Flowkeys" width="671">
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
                                <ui:tableRowGroup binding="#{editFlowMetering.tableRowGroup2}" id="tableRowGroup2" rows="5"
                                    sourceData="#{SessionBean1.nonFlowKeyDataProvider}" sourceVar="currentRow">
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn4}" headerText="ID" id="tableColumn4" sort="ieId">
                                        <ui:staticText binding="#{editFlowMetering.staticText4}" id="staticText4" text="#{currentRow.value['ieId']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn5}" headerText="Enterpr.Nr." id="tableColumn5" sort="enterpriseNumber">
                                        <ui:staticText binding="#{editFlowMetering.staticText5}" id="staticText5" text="#{currentRow.value['enterpriseNumber']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn6}" headerText="Name" id="tableColumn6" sort="ieName">
                                        <ui:staticText binding="#{editFlowMetering.staticText6}" id="staticText6" text="#{currentRow.value['ieName']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn12}" headerText="Match" id="tableColumn12" sort="match">
                                        <ui:staticText binding="#{editFlowMetering.staticText10}" id="staticText10" text="#{currentRow.value['match']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn13}" headerText="Length" id="tableColumn13" sort="ieLength">
                                        <ui:staticText binding="#{editFlowMetering.staticText11}" id="staticText11" text="#{currentRow.value['ieLength']}"/>
                                    </ui:tableColumn>
                                    <ui:tableColumn binding="#{editFlowMetering.tableColumn14}" headerText="Modifier" id="tableColumn14" sort="modifier">
                                        <ui:staticText binding="#{editFlowMetering.staticText12}" id="staticText12" text="#{currentRow.value['modifier']}"/>
                                    </ui:tableColumn>
                                </ui:tableRowGroup>
                            </ui:table>
                        </h:panelGrid>
                        <ui:textField binding="#{editFlowMetering.meteringProcess_id1}" columns="3" converter="#{editFlowMetering.integerConverter1}"
                            id="meteringProcess_id1" label="Metering Process ID:" style="left: 24px; top: 72px; position: absolute" text="#{SessionBean1.meteringProcessId}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
