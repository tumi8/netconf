<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{setScenarioSummary.page1}" id="page1">
            <ui:html binding="#{setScenarioSummary.html1}" id="html1">
                <ui:head binding="#{setScenarioSummary.head1}" id="head1">
                    <ui:link binding="#{setScenarioSummary.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{setScenarioSummary.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{setScenarioSummary.form1}" id="form1">
                        <ui:pageAlert binding="#{setScenarioSummary.pageAlert1}" id="pageAlert1"
                            style="height: 454px; left: 48px; top: 24px; position: absolute; width: 598px" type="question">
                            <f:facet name="pageAlertButtons">
                                <ui:panelGroup binding="#{setScenarioSummary.groupPanel1}" id="groupPanel1">
                                    <ui:button binding="#{setScenarioSummary.button1}" id="button1" text="Return to Main"/>
                                </ui:panelGroup>
                            </f:facet>
                        </ui:pageAlert>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
