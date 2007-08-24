<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editTimeBased.page1}" id="page1">
            <ui:html binding="#{editTimeBased.html1}" id="html1">
                <ui:head binding="#{editTimeBased.head1}" id="head1">
                    <ui:link binding="#{editTimeBased.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editTimeBased.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editTimeBased.form1}" id="form1" virtualFormsConfig="save | interval spacing | save_changes_button">
                        <ui:label binding="#{editTimeBased.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Time-based Sampling"/>
                        <ui:textField binding="#{editTimeBased.interval}" columns="6" converter="#{editTimeBased.integerConverter1}" id="interval"
                            label="Interval:" required="true" style="position: absolute; left: 24px; top: 72px" text="#{editTimeBased.timeBasedSelection.interval}"/>
                        <ui:textField binding="#{editTimeBased.spacing}" columns="6" converter="#{editTimeBased.integerConverter2}" id="spacing"
                            label="Spacing:" required="true" style="left: 24px; top: 120px; position: absolute" text="#{editTimeBased.timeBasedSelection.spacing}"/>
                        <ui:button action="#{editTimeBased.save_changes_button_action}" binding="#{editTimeBased.save_changes_button}" id="save_changes_button"
                            style="position: absolute; left: 24px; top: 168px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editTimeBased.discard_changes_button}" id="discard_changes_button"
                            style="position: absolute; left: 168px; top: 168px" text="Discard changes"/>
                        <ui:message binding="#{editTimeBased.message1}" for="interval" id="message1" showDetail="false" showSummary="true" style="position: absolute; left: 192px; top: 72px"/>
                        <ui:message binding="#{editTimeBased.message2}" for="spacing" id="message2" showDetail="false" showSummary="true" style="position: absolute; left: 192px; top: 120px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
