<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editRandOutOfN.page1}" id="page1">
            <ui:html binding="#{editRandOutOfN.html1}" id="html1">
                <ui:head binding="#{editRandOutOfN.head1}" id="head1">
                    <ui:link binding="#{editRandOutOfN.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editRandOutOfN.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editRandOutOfN.form1}" id="form1" virtualFormsConfig="save | population sample | save_changes_button">
                        <ui:label binding="#{editRandOutOfN.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Random out of N Sampling"/>
                        <ui:textField binding="#{editRandOutOfN.population}" columns="6" converter="#{editRandOutOfN.integerConverter1}" id="population"
                            label="Population:" required="true" style="position: absolute; left: 24px; top: 72px" text="#{editRandOutOfN.randOutOfNSelection.population}"/>
                        <ui:textField binding="#{editRandOutOfN.sample}" columns="6" converter="#{editRandOutOfN.integerConverter2}" id="sample" label="Sample:"
                            required="true" style="position: absolute; left: 24px; top: 120px" text="#{editRandOutOfN.randOutOfNSelection.sample}"/>
                        <ui:button action="#{editRandOutOfN.save_changes_button_action}" binding="#{editRandOutOfN.save_changes_button}"
                            id="save_changes_button" style="position: absolute; left: 24px; top: 168px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editRandOutOfN.discard_button}" id="discard_button"
                            style="position: absolute; left: 168px; top: 168px" text="Discard changes"/>
                        <ui:message binding="#{editRandOutOfN.message1}" for="population" id="message1" showDetail="false" showSummary="true" style="position: absolute; left: 216px; top: 72px"/>
                        <ui:message binding="#{editRandOutOfN.message2}" for="sample" id="message2" showDetail="false" showSummary="true" style="position: absolute; left: 216px; top: 120px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
