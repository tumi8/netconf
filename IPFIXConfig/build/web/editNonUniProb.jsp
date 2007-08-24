<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editNonUniProb.page1}" id="page1">
            <ui:html binding="#{editNonUniProb.html1}" id="html1">
                <ui:head binding="#{editNonUniProb.head1}" id="head1">
                    <ui:link binding="#{editNonUniProb.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editNonUniProb.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editNonUniProb.form1}" id="form1" virtualFormsConfig="save | function parameters | save_changes_button">
                        <ui:label binding="#{editNonUniProb.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Non Uniform Probabilistic Sampling"/>
                        <ui:textField binding="#{editNonUniProb.function}" id="function" label="Function:" required="true"
                            style="position: absolute; left: 24px; top: 72px" text="#{editNonUniProb.nonUniProbSelection.function}"/>
                        <ui:textField binding="#{editNonUniProb.parameters}" id="parameters" label="Parameters:" required="true"
                            style="position: absolute; left: 24px; top: 120px" text="#{editNonUniProb.nonUniProbSelection.funcParam}"/>
                        <ui:button action="#{editNonUniProb.save_changes_button_action}" binding="#{editNonUniProb.save_changes_button}"
                            id="save_changes_button" style="position: absolute; left: 24px; top: 168px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editNonUniProb.discard_button}" id="discard_button"
                            style="position: absolute; left: 144px; top: 168px" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
