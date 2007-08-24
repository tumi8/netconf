<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editUniProb.page1}" id="page1">
            <ui:html binding="#{editUniProb.html1}" id="html1">
                <ui:head binding="#{editUniProb.head1}" id="head1">
                    <ui:link binding="#{editUniProb.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editUniProb.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editUniProb.form1}" id="form1">
                        <ui:label binding="#{editUniProb.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Universal Probability Sampling"/>
                        <ui:textField binding="#{editUniProb.probability}" columns="12" converter="#{editUniProb.integerConverter1}" id="probability"
                            label="Probability:" required="true" style="position: absolute; left: 24px; top: 72px" text="#{editUniProb.uniProbSelection.probability}"/>
                        <ui:button action="#{editUniProb.save_changes_button_action}" binding="#{editUniProb.save_changes_button}" id="save_changes_button"
                            style="position: absolute; left: 24px; top: 120px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editUniProb.discard_button}" id="discard_button"
                            style="left: 143px; top: 120px; position: absolute" text="Discard changes"/>
                        <ui:message binding="#{editUniProb.message1}" for="probability" id="message1" showDetail="false" showSummary="true" style="position: absolute; left: 288px; top: 72px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
