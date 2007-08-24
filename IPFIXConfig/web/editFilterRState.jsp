<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editFilterRState.page1}" id="page1">
            <ui:html binding="#{editFilterRState.html1}" id="html1">
                <ui:head binding="#{editFilterRState.head1}" id="head1">
                    <ui:link binding="#{editFilterRState.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editFilterRState.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editFilterRState.form1}" id="form1" virtualFormsConfig="save | vendorFunc endAS startAS negate function ifIndex | save_changes_button">
                        <ui:label binding="#{editFilterRState.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Router State Filtering:"/>
                        <ui:textField binding="#{editFilterRState.function}" id="function" label="Function:" style="position: absolute; left: 24px; top: 72px" text="#{editFilterRState.filterRStateSelection.function}"/>
                        <ui:checkbox binding="#{editFilterRState.negate}" id="negate" label="Negate" labelLevel="2"
                            selected="#{editFilterRState.filterRStateSelection.negate}" style="left: 24px; top: 120px; position: absolute"/>
                        <ui:textField binding="#{editFilterRState.ifIndex}" columns="10" converter="#{editFilterRState.integerConverter1}" id="ifIndex"
                            label="Ingress Interface:" style="position: absolute; left: 24px; top: 168px" text="#{editFilterRState.filterRStateSelection.ifIndex}"/>
                        <ui:textField binding="#{editFilterRState.startAS}" columns="10" converter="#{editFilterRState.integerConverter1}" id="startAS"
                            label="Origin Autonomous System Start:" style="position: absolute; left: 24px; top: 216px" text="#{editFilterRState.filterRStateSelection.startAs}"/>
                        <ui:textField binding="#{editFilterRState.endAS}" columns="10" converter="#{editFilterRState.integerConverter2}" id="endAS"
                            label="Origin Autonomous System End:" style="position: absolute; left: 24px; top: 264px" text="#{editFilterRState.filterRStateSelection.endAs}"/>
                        <ui:textField binding="#{editFilterRState.vendorFunc}" id="vendorFunc" label="Vendor specific Function:"
                            style="position: absolute; left: 24px; top: 312px" text="#{editFilterRState.filterRStateSelection.vendorFunc}"/>
                        <ui:button action="#{editFilterRState.save_changes_button_action}" binding="#{editFilterRState.save_changes_button}"
                            id="save_changes_button" style="position: absolute; left: 24px; top: 360px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editFilterRState.discard_button}" id="discard_button"
                            style="position: absolute; left: 144px; top: 360px" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
