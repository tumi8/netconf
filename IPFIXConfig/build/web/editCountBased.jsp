<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editCountBased.page1}" id="page1">
            <ui:html binding="#{editCountBased.html1}" id="html1">
                <ui:head binding="#{editCountBased.head1}" id="head1">
                    <ui:link binding="#{editCountBased.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editCountBased.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editCountBased.form1}" id="form1" virtualFormsConfig="save | interval spacing | save_changes_button">
                        <ui:label binding="#{editCountBased.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Systematic Count-based Sampling "/>
                        <ui:textField binding="#{editCountBased.interval}" columns="6" converter="#{editCountBased.integerConverter1}" id="interval"
                            label="Interval:" required="true" style="left: 24px; top: 72px; position: absolute" text="#{editCountBased.countBasedSelection.interval}"/>
                        <ui:textField binding="#{editCountBased.spacing}" columns="6" converter="#{editCountBased.integerConverter1}" id="spacing"
                            label="Spacing:" required="true" style="left: 24px; top: 120px; position: absolute" text="#{editCountBased.countBasedSelection.spacing}"/>
                        <ui:button action="#{editCountBased.save_changes_button_action}" binding="#{editCountBased.save_changes_button}"
                            id="save_changes_button" style="left: 23px; top: 168px; position: absolute" text="Save changes"/>
                        <ui:button action="case2" binding="#{editCountBased.discard_changes_button}" id="discard_changes_button"
                            style="left: 167px; top: 168px; position: absolute" text="Discard changes"/>
                        <ui:message binding="#{editCountBased.message1}" for="interval" id="message1" showDetail="false" showSummary="true" style="position: absolute; left: 216px; top: 72px"/>
                        <ui:message binding="#{editCountBased.message2}" for="spacing" id="message2" showDetail="false" showSummary="true" style="position: absolute; left: 216px; top: 120px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
