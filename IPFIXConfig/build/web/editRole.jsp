<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editRole.page1}" id="page1">
            <ui:html binding="#{editRole.html1}" id="html1">
                <ui:head binding="#{editRole.head1}" id="head1">
                    <ui:link binding="#{editRole.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editRole.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editRole.form1}" id="form1" virtualFormsConfig="save form | roleName descript_textarea | save_role , discard form | gridPanel1:devices gridPanel1:configs | discard gridPanel1:save_mapping_button table1:tableRowGroup1:tableColumn3:button2">
                        <ui:label binding="#{editRole.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Role"/>
                        <ui:textField binding="#{editRole.roleName}" id="roleName" label="Name:" required="true"
                            style="position: absolute; left: 24px; top: 72px" text="#{editRole.role.name}" validator="#{editRole.roleName_validate}"/>
                        <ui:textArea binding="#{editRole.descript_textarea}" id="descript_textarea" label="Description:"
                            style="position: absolute; left: 24px; top: 120px" text="#{editRole.role.description}"/>
                        <h:panelGrid binding="#{editRole.gridPanel1}" columns="2" id="gridPanel1"
                            style="height: 168px; left: 24px; top: 192px; position: absolute" width="384">
                            <ui:label binding="#{editRole.label2}" id="label2" text="Select device:"/>
                            <ui:label binding="#{editRole.label3}" id="label3" text="Select Configuration:"/>
                            <ui:listbox binding="#{editRole.devices}" id="devices" items="#{editRole.deviceOptions}" selected="#{editRole.selectedDevice}" style="height: 168px; width: 120px"/>
                            <ui:listbox binding="#{editRole.configs}" id="configs" items="#{editRole.configOptions}" selected="#{editRole.selectedConfig}" style="height: 168px; width: 144px"/>
                            <ui:button action="#{editRole.save_mapping_button_action}" binding="#{editRole.save_mapping_button}" id="save_mapping_button" text="Add new Mapping"/>
                        </h:panelGrid>
                        <ui:table augmentTitle="false" binding="#{editRole.mappingtable}" id="mappingtable"
                            style="left: 24px; top: 456px; position: absolute; width: 240px" title="Mapping" width="240">
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
                            <ui:tableRowGroup binding="#{editRole.tableRowGroup1}" id="tableRowGroup1" rows="10" sourceData="#{SessionBean1.mappingList}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{editRole.tableColumn1}" headerText="Device" id="tableColumn1" sort="device">
                                    <ui:staticText binding="#{editRole.staticText1}" id="staticText1" text="#{currentRow.value['device']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editRole.tableColumn2}" headerText="Configuration" id="tableColumn2" sort="config">
                                    <ui:staticText binding="#{editRole.staticText2}" id="staticText2" text="#{currentRow.value['config']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editRole.tableColumn3}" headerText="Remove" id="tableColumn3">
                                    <ui:button action="#{editRole.remove_action}" binding="#{editRole.removeMappingButton}" id="removeMappingButton" text="Remove"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                        <ui:messageGroup binding="#{editRole.messageGroup1}" id="messageGroup1" style="height: 118px; left: 408px; top: 24px; position: absolute"/>
                        <ui:button action="#{editRole.save_role_action}" binding="#{editRole.save_role}" id="save_role"
                            style="left: 23px; top: 432px; position: absolute" text="Save Role"/>
                        <ui:button action="saveRole" binding="#{editRole.discard}" id="discard" style="position: absolute; left: 120px; top: 432px" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
