<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{uploadConfig.page1}" id="page1">
            <ui:html binding="#{uploadConfig.html1}" id="html1">
                <ui:head binding="#{uploadConfig.head1}" id="head1" title="Upload Configuration File">
                    <ui:link binding="#{uploadConfig.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{uploadConfig.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{uploadConfig.form1}" id="form1" virtualFormsConfig="save | gridPanel1:configName gridPanel1:description fileUpload1 | uploadFileButton , return | | discard">
                        <ui:label binding="#{uploadConfig.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Configuration file upload"/>
                        <h:panelGrid binding="#{uploadConfig.gridPanel1}" columns="2" id="gridPanel1"
                            style="height: 120px; left: 24px; top: 72px; position: absolute" width="408">
                            <ui:textField binding="#{uploadConfig.configName}" columns="15" id="configName" label="Config name:" required="true"
                                text="#{uploadConfig.configInformation.name}" validator="#{uploadConfig.configName_validate}"/>
                            <ui:message binding="#{uploadConfig.message1}" for="configName" id="message1" showDetail="false" showSummary="true"/>
                            <ui:textArea binding="#{uploadConfig.description}" columns="40" id="description" label="Description:" text="#{uploadConfig.configInformation.description}"/>
                        </h:panelGrid>
                        <ui:upload binding="#{uploadConfig.fileUpload1}" id="fileUpload1" required="true" style="position: absolute; left: 24px; top: 216px"/>
                        <ui:button action="#{uploadConfig.uploadFileButton_action}" binding="#{uploadConfig.uploadFileButton}" id="uploadFileButton"
                            style="position: absolute; left: 24px; top: 264px" text="Upload file"/>
                        <ui:button action="#{uploadConfig.discard_action}" binding="#{uploadConfig.discard}" id="discard"
                            style="position: absolute; left: 120px; top: 264px" text="Return"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
