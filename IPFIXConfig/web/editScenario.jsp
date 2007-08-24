<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editScenario.page1}" id="page1">
            <ui:html binding="#{editScenario.html1}" id="html1">
                <ui:head binding="#{editScenario.head1}" id="head1">
                    <ui:link binding="#{editScenario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editScenario.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editScenario.form1}" id="form1">
                        <ui:label binding="#{editScenario.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Scenario"/>
                        <ui:textField binding="#{editScenario.name}" id="name" label="Name:" required="true" style="position: absolute; left: 24px; top: 72px"
                            text="#{editScenario.scenario.name}" validator="#{editScenario.name_validate}"/>
                        <ui:textArea binding="#{editScenario.description}" id="description" label="Description:"
                            style="position: absolute; left: 24px; top: 120px" text="#{editScenario.scenario.description}"/>
                        <ui:addRemove availableItemsLabel="Available Devices:" binding="#{editScenario.addRemoveList1}" id="addRemoveList1"
                            items="#{editScenario.devices}" moveButtons="true" selected="#{editScenario.selectedDevices}" selectedItemsLabel="Selected Devices:" style="height: 262px; left: 24px; top: 192px; position: absolute; width: 406px"/>
                        <ui:button action="#{editScenario.save_action}" binding="#{editScenario.save}" id="save"
                            style="position: absolute; left: 24px; top: 480px" text="Save Scenario"/>
                        <ui:button action="#{editScenario.discard_action}" binding="#{editScenario.discard}" id="discard"
                            style="left: 143px; top: 480px; position: absolute" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
