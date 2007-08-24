<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editRawFilter.page1}" id="page1">
            <ui:html binding="#{editRawFilter.html1}" id="html1">
                <ui:head binding="#{editRawFilter.head1}" id="head1">
                    <ui:link binding="#{editRawFilter.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editRawFilter.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editRawFilter.form1}" id="form1">
                        <ui:label binding="#{editRawFilter.label1}" id="label1" labelLevel="1" style="left: 24px; top: 24px; position: absolute" text="Raw Filter"/>
                        <ui:textField binding="#{editRawFilter.settings_field}" id="settings_field" label="Settings:" style="position: absolute; left: 24px; top: 72px"/>
                        <ui:button action="#{editRawFilter.save_button_action}" binding="#{editRawFilter.save_button}" id="save_button"
                            style="position: absolute; left: 24px; top: 120px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editRawFilter.discard_button}" id="discard_button"
                            style="position: absolute; left: 168px; top: 120px" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
