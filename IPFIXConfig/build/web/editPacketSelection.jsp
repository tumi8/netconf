<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editPacketSelection.page1}" id="page1">
            <ui:html binding="#{editPacketSelection.html1}" id="html1">
                <ui:head binding="#{editPacketSelection.head1}" id="head1">
                    <ui:link binding="#{editPacketSelection.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editPacketSelection.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editPacketSelection.form1}" id="form1">
                        <ui:label binding="#{editPacketSelection.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Packet Selection"/>
                        <ui:staticText binding="#{editPacketSelection.display_Details}" escape="false" id="display_Details"
                            style="border-width: 1px; border-style: solid; height: 238px; left: 480px; top: 144px; position: absolute; width: 238px" text="#{editPacketSelection.selectedMethodDetails}"/>
                        <ui:dropDown binding="#{editPacketSelection.new_packet_selection_dropdown}" id="new_packet_selection_dropdown"
                            items="#{ApplicationBean1.packetSelectionTypeOptions}" label="New Packet Selection Option:" onChange="" style="left: 120px; top: 456px; position: absolute"/>
                        <ui:label binding="#{editPacketSelection.label2}" id="label2" style="left: 480px; top: 120px; position: absolute; width: 216px" text="Packet Selection Option Details:"/>
                        <ui:listbox binding="#{editPacketSelection.listbox1}" id="listbox1" items="#{editPacketSelection.selectionMethodOptions}" onChange=""
                            selected="#{editPacketSelection.selectedMethod}" style="height: 240px; left: 24px; top: 144px; position: absolute; width: 336px"/>
                        <ui:label binding="#{editPacketSelection.label3}" id="label3" style="left: 24px; top: 120px; position: absolute" text="Configured Packet Selection Options:"/>
                        <ui:button action="#{editPacketSelection.edit_selected_action}" binding="#{editPacketSelection.edit_selected}" id="edit_selected"
                            style="left: 23px; top: 408px; position: absolute" text="Edit selected"/>
                        <ui:button action="#{editPacketSelection.remove_selected_action}" binding="#{editPacketSelection.remove_selected}" id="remove_selected"
                            style="left: 143px; top: 408px; position: absolute" text="Remove selected"/>
                        <ui:button action="#{editPacketSelection.save_changes_action}" binding="#{editPacketSelection.save_changes}" id="save_changes"
                            style="left: 23px; top: 528px; position: absolute" text="Save changes"/>
                        <ui:button action="#{editPacketSelection.discard_changes_action}" binding="#{editPacketSelection.discard_changes}" id="discard_changes"
                            style="left: 167px; top: 528px; position: absolute" text="Discard changes"/>
                        <ui:button action="#{editPacketSelection.show_details_action}" binding="#{editPacketSelection.show_details}" id="show_details"
                            style="left: 359px; top: 192px; position: absolute" text="Show Details"/>
                        <ui:button action="#{editPacketSelection.add_new_packet_selection_action}" binding="#{editPacketSelection.add_new_packet_selection}"
                            id="add_new_packet_selection" style="left: 23px; top: 456px; position: absolute" text="Add new"/>
                        <ui:textField binding="#{editPacketSelection.meteringProcess_id}" columns="3" converter="#{editPacketSelection.integerConverter1}"
                            id="meteringProcess_id" label="Metering Process ID:" style="left: 24px; top: 72px; position: absolute" text="#{SessionBean1.meteringProcessId}"/>
                        <ui:button action="#{editPacketSelection.up_button_action}" binding="#{editPacketSelection.up_button}" id="up_button"
                            style="position: absolute; left: 360px; top: 144px" text="Up"/>
                        <ui:button action="#{editPacketSelection.down_button_action}" binding="#{editPacketSelection.down_button}" id="down_button"
                            style="position: absolute; left: 360px; top: 168px" text="Down"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
