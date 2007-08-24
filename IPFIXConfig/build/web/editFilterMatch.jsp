<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editFilterMatch.page1}" id="page1">
            <ui:html binding="#{editFilterMatch.html1}" id="html1">
                <ui:head binding="#{editFilterMatch.head1}" id="head1">
                    <ui:link binding="#{editFilterMatch.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editFilterMatch.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editFilterMatch.form1}" id="form1">
                        <ui:label binding="#{editFilterMatch.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Match Filtering"/>
                        <ui:button action="#{editFilterMatch.save_all_changes_action}" binding="#{editFilterMatch.save_all_changes}" id="save_all_changes"
                            style="left: 23px; top: 72px; position: absolute" text="Save all changes"/>
                        <ui:button action="case2" binding="#{editFilterMatch.discard_all_changes}" id="discard_all_changes"
                            style="left: 191px; top: 72px; position: absolute" text="Discard changes"/>
                        <ui:button action="#{editFilterMatch.save_new_rule_action}" binding="#{editFilterMatch.save_new_rule}" id="save_new_rule"
                            style="left: 23px; top: 216px; position: absolute" text="Add new Rule"/>
                        <ui:messageGroup binding="#{editFilterMatch.messageGroup1}" id="messageGroup1" style="position: absolute; left: 384px; top: 24px"/>
                        <ui:textField binding="#{editFilterMatch.id_field}" columns="3" converter="#{editFilterMatch.integerConverter1}" id="id_field"
                            label="ID:" style="position: absolute; left: 24px; top: 120px" text="#{editFilterMatch.filterMatchRule.ieId}"/>
                        <ui:textField binding="#{editFilterMatch.enterpriseNr}" columns="6" converter="#{editFilterMatch.integerConverter1}" id="enterpriseNr"
                            label="EnterpriseNr.:" style="position: absolute; left: 120px; top: 120px" text="#{editFilterMatch.filterMatchRule.enterpriseNumber}"/>
                        <ui:textField binding="#{editFilterMatch.name_field}" columns="15" id="name_field" label="Name:"
                            style="position: absolute; left: 312px; top: 120px" text="#{editFilterMatch.filterMatchRule.ieName}"/>
                        <ui:textField binding="#{editFilterMatch.length_field}" columns="6" converter="#{editFilterMatch.integerConverter1}" id="length_field"
                            label="Length:" style="position: absolute; left: 24px; top: 168px" text="#{editFilterMatch.filterMatchRule.ieLength}"/>
                        <ui:textField binding="#{editFilterMatch.match_field}" columns="16" id="match_field" label="Match:"
                            style="position: absolute; left: 168px; top: 168px" text="#{editFilterMatch.filterMatchRule.match}"/>
                        <ui:textField binding="#{editFilterMatch.modifier_field}" columns="15" id="modifier_field" label="Modifier:"
                            style="position: absolute; left: 408px; top: 168px" text="#{editFilterMatch.filterMatchRule.modifier}"/>
                        <ui:table augmentTitle="false" binding="#{editFilterMatch.table1}" id="table1" style="position: absolute; left: 24px; top: 264px"
                            title="Filter Match Rules" width="720">
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
                            <ui:tableRowGroup binding="#{editFilterMatch.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{SessionBean1.sesFilterMatchDataProvider}" sourceVar="currentRow">
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn1}" headerText="ID" id="tableColumn1" sort="ieId">
                                    <ui:staticText binding="#{editFilterMatch.staticText1}" id="staticText1" text="#{currentRow.value['ieId']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn2}" headerText="E.Nr." id="tableColumn2" sort="enterpriseNumber">
                                    <ui:staticText binding="#{editFilterMatch.staticText2}" id="staticText2" text="#{currentRow.value['enterpriseNumber']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn3}" headerText="Length" id="tableColumn3" sort="ieLength">
                                    <ui:staticText binding="#{editFilterMatch.staticText3}" id="staticText3" text="#{currentRow.value['ieLength']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn4}" headerText="Name" id="tableColumn4" sort="ieName">
                                    <ui:staticText binding="#{editFilterMatch.staticText4}" id="staticText4" text="#{currentRow.value['ieName']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn5}" headerText="Match" id="tableColumn5" sort="match">
                                    <ui:staticText binding="#{editFilterMatch.staticText5}" id="staticText5" text="#{currentRow.value['match']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn6}" headerText="Modifier" id="tableColumn6" sort="modifier">
                                    <ui:staticText binding="#{editFilterMatch.staticText6}" id="staticText6" text="#{currentRow.value['modifier']}"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn7}" headerText="Edit" id="tableColumn7">
                                    <ui:button action="#{editFilterMatch.edit_button_action}" binding="#{editFilterMatch.edit_button}" id="edit_button" text="Edit"/>
                                </ui:tableColumn>
                                <ui:tableColumn binding="#{editFilterMatch.tableColumn8}" headerText="Remove" id="tableColumn8">
                                    <ui:button action="#{editFilterMatch.remove_button_action}" binding="#{editFilterMatch.remove_button}" id="remove_button" text="Remove"/>
                                </ui:tableColumn>
                            </ui:tableRowGroup>
                        </ui:table>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
