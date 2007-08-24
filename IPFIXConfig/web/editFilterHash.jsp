<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editFilterHash.page1}" id="page1">
            <ui:html binding="#{editFilterHash.html1}" id="html1">
                <ui:head binding="#{editFilterHash.head1}" id="head1">
                    <ui:link binding="#{editFilterHash.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editFilterHash.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editFilterHash.form1}" id="form1">
                        <ui:label binding="#{editFilterHash.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Hash Filtering"/>
                        <ui:dropDown binding="#{editFilterHash.addrtype}" id="addrtype" items="#{ApplicationBean1.addressTypeOptions}" label="Address Type:"
                            selected="#{editFilterHash.filterHashSelection.addrType}" style="position: absolute; left: 24px; top: 72px"/>
                        <ui:textField binding="#{editFilterHash.headerBits}" id="headerBits" label="Header Bits Mask:"
                            style="position: absolute; left: 24px; top: 120px" text="#{editFilterHash.filterHashSelection.headerBits}"/>
                        <ui:textField binding="#{editFilterHash.payloadBytes}" converter="#{editFilterHash.integerConverter1}" id="payloadBytes"
                            label="Payload Bytes:" style="position: absolute; left: 24px; top: 168px" text="#{editFilterHash.filterHashSelection.payloadBytes}"/>
                        <ui:textField binding="#{editFilterHash.payloadBits}" converter="#{editFilterHash.integerConverter1}" id="payloadBits"
                            label="Payload Bytes Final Bits Mask:" style="position: absolute; left: 24px; top: 216px" text="#{editFilterHash.filterHashSelection.payloadBits}"/>
                        <ui:textField binding="#{editFilterHash.function}" id="function" label="Function:" style="position: absolute; left: 24px; top: 264px" text="#{editFilterHash.filterHashSelection.function}"/>
                        <ui:textField binding="#{editFilterHash.parameters}" id="parameters" label="Parameters:"
                            style="position: absolute; left: 24px; top: 312px" text="#{editFilterHash.filterHashSelection.funcParam}"/>
                        <ui:textField binding="#{editFilterHash.inputBits}" converter="#{editFilterHash.integerConverter1}" id="inputBits" label="Input Bits:"
                            style="position: absolute; left: 24px; top: 360px" text="#{editFilterHash.filterHashSelection.inputBits}"/>
                        <ui:textField binding="#{editFilterHash.outputBits}" converter="#{editFilterHash.integerConverter1}" id="outputBits"
                            label="Output Bits:" style="position: absolute; left: 24px; top: 408px" text="#{editFilterHash.filterHashSelection.outputBits}"/>
                        <ui:textField binding="#{editFilterHash.outputMask}" id="outputMask" label="Output Bits Mask:"
                            style="position: absolute; left: 24px; top: 456px" text="#{editFilterHash.filterHashSelection.outputMask}"/>
                        <ui:textField binding="#{editFilterHash.selection}" id="selection" label="Selection:" style="position: absolute; left: 24px; top: 504px" text="#{editFilterHash.filterHashSelection.selection}"/>
                        <ui:button action="#{editFilterHash.save_changes_button_action}" binding="#{editFilterHash.save_changes_button}"
                            id="save_changes_button" style="position: absolute; left: 24px; top: 552px" text="Save changes"/>
                        <ui:button action="case2" binding="#{editFilterHash.discard_button}" id="discard_button"
                            style="position: absolute; left: 144px; top: 552px" text="Discard changes"/>
                        <ui:messageGroup binding="#{editFilterHash.messageGroup1}" id="messageGroup1" style="position: absolute; left: 432px; top: 72px"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
