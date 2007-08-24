<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editObservationPoint.page1}" id="page1">
            <ui:html binding="#{editObservationPoint.html1}" id="html1">
                <ui:head binding="#{editObservationPoint.head1}" id="head1">
                    <ui:link binding="#{editObservationPoint.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editObservationPoint.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editObservationPoint.form1}" id="form1" virtualFormsConfig="save | gridPanel1:idField gridPanel1:domainIdField gridPanel1:typeField gridPanel1:pcapParameter gridPanel1:interface_field | save_changes , discard | | discard_changes">
                        <ui:label binding="#{editObservationPoint.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Edit Observation Point"/>
                        <h:panelGrid binding="#{editObservationPoint.gridPanel1}" columns="2" id="gridPanel1"
                            style="height: 168px; left: 24px; top: 72px; position: absolute" width="408">
                            <ui:label binding="#{editObservationPoint.id_label}" for="idField" id="id_label" text="ID:"/>
                            <ui:textField binding="#{editObservationPoint.idField}" columns="3" converter="#{editObservationPoint.integerConverter1}"
                                id="idField" required="true" text="#{editObservationPoint.observationPoint.id}"/>
                            <ui:label binding="#{editObservationPoint.domain_label}" for="domainIdField" id="domain_label" text="Domain ID:"/>
                            <ui:textField binding="#{editObservationPoint.domainIdField}" columns="6" converter="#{editObservationPoint.integerConverter1}"
                                id="domainIdField" required="true" text="#{editObservationPoint.observationPoint.domainId}"/>
                            <ui:label binding="#{editObservationPoint.type_field}" for="typeField" id="type_field" text="Type:"/>
                            <ui:textField binding="#{editObservationPoint.typeField}" columns="15" id="typeField" required="true" text="#{editObservationPoint.observationPoint.type}"/>
                            <ui:label binding="#{editObservationPoint.interface_label}" for="interface_field" id="interface_label" text="Interface:"/>
                            <ui:textField binding="#{editObservationPoint.interface_field}" columns="8" id="interface_field" text="#{editObservationPoint.observationPoint.networkInterface}"/>
                            <ui:label binding="#{editObservationPoint.pcap_label}" for="pcapParameter" id="pcap_label" text="Filter Parameter:"/>
                            <ui:textField binding="#{editObservationPoint.pcapParameter}" columns="15" id="pcapParameter" text="#{editObservationPoint.observationPoint.parameters}"/>
                        </h:panelGrid>
                        <ui:button action="#{editObservationPoint.save_changes_action}" binding="#{editObservationPoint.save_changes}" id="save_changes"
                            style="left: 23px; top: 264px; position: absolute" text="Save changes"/>
                        <ui:button action="#{editObservationPoint.discard_changes_action}" binding="#{editObservationPoint.discard_changes}"
                            id="discard_changes" style="left: 167px; top: 264px; position: absolute" text="Discard changes"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
