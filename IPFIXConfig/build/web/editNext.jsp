<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editNext.page1}" id="page1">
            <ui:html binding="#{editNext.html1}" id="html1">
                <ui:head binding="#{editNext.head1}" id="head1">
                    <ui:link binding="#{editNext.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editNext.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editNext.form1}" id="form1">
                        <ui:label binding="#{editNext.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Next Processes"/>
                        <ui:addRemove availableItemsLabel="Available Processes:" binding="#{editNext.addRemoveList1}" id="addRemoveList1"
                            items="#{SessionBean1.nextProcessOptions}" moveButtons="true" selectAll="true" selected="#{SessionBean1.selectedNext}"
                            selectedItemsLabel="Selected Processes:" style="height: 286px; left: 24px; top: 72px; position: absolute; width: 502px"/>
                        <ui:button action="#{editNext.save_changes_action}" binding="#{editNext.save_changes}" id="save_changes"
                            style="position: absolute; left: 24px; top: 384px" text="Save changes"/>
                        <ui:button action="#{editNext.discard_changes_action}" binding="#{editNext.discard_changes}" id="discard_changes"
                            style="position: absolute; left: 168px; top: 384px" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
