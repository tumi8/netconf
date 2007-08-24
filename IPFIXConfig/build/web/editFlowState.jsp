<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editFlowState.page1}" id="page1">
            <ui:html binding="#{editFlowState.html1}" id="html1">
                <ui:head binding="#{editFlowState.head1}" id="head1">
                    <ui:link binding="#{editFlowState.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editFlowState.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editFlowState.form1}" id="form1">
                        <ui:label binding="#{editFlowState.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Flow State Selection"/>
                        <ui:textField binding="#{editFlowState.textField1}" id="textField1" label="Function:" style="position: absolute; left: 24px; top: 72px" text="#{editFlowState.newFlowState.function}"/>
                        <ui:textField binding="#{editFlowState.textField2}" id="textField2" label="Parameter:"
                            style="position: absolute; left: 24px; top: 120px" text="#{editFlowState.newFlowState.funcParam}"/>
                        <ui:button action="#{editFlowState.button1_action}" binding="#{editFlowState.button1}" id="button1"
                            style="position: absolute; left: 24px; top: 168px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editFlowState.button2}" id="button2" style="position: absolute; left: 168px; top: 168px" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
