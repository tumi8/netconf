<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{setConfig.page1}" id="page1">
            <ui:html binding="#{setConfig.html1}" id="html1">
                <ui:head binding="#{setConfig.head1}" id="head1">
                    <ui:link binding="#{setConfig.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{setConfig.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{setConfig.form1}" id="form1">
                        <ui:label binding="#{setConfig.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Set Configuration on Device"/>
                        <ui:messageGroup binding="#{setConfig.messageGroup1}" id="messageGroup1" style="height: 118px; left: 648px; top: 192px; position: absolute; width: 286px"/>
                        <h:panelGrid binding="#{setConfig.devicePanel}" columns="2" id="devicePanel"
                            style="border-width: 1px; border-style: solid; height: 120px; left: 24px; top: 72px; position: absolute" title="Device Information" width="288">
                            <ui:label binding="#{setConfig.label2}" id="label2" text="Name:"/>
                            <ui:staticText binding="#{setConfig.deviceName}" id="deviceName" text="#{setConfig.deviceToSet.name}"/>
                            <ui:label binding="#{setConfig.label3}" id="label3" text="Type:"/>
                            <ui:staticText binding="#{setConfig.deviceType}" id="deviceType" text="#{setConfig.deviceToSet.type}"/>
                            <ui:label binding="#{setConfig.label4}" id="label4" text="Description:"/>
                            <ui:staticText binding="#{setConfig.deviceDescript}" id="deviceDescript" text="#{setConfig.deviceToSet.description}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{setConfig.connectionPanel}" columns="2" id="connectionPanel"
                            style="border-width: 1px; border-style: solid; height: 120px; left: 24px; top: 216px; position: absolute" width="288">
                            <ui:label binding="#{setConfig.label5}" id="label5" text="Host:"/>
                            <ui:staticText binding="#{setConfig.hostIP}" id="hostIP" text="#{setConfig.deviceToSet.hostIP}"/>
                            <ui:label binding="#{setConfig.label10}" id="label10" text="Port:"/>
                            <ui:staticText binding="#{setConfig.port}" id="port" text="#{setConfig.deviceToSet.port}"/>
                            <ui:label binding="#{setConfig.label6}" id="label6" text="User:"/>
                            <ui:staticText binding="#{setConfig.user}" id="user" text="#{setConfig.deviceToSet.user}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{setConfig.rolePanel}" columns="2" id="rolePanel"
                            style="border-width: 1px; border-style: solid; height: 120px; left: 336px; top: 72px; position: absolute" width="288">
                            <ui:label binding="#{setConfig.label8}" id="label8" text="Role:"/>
                            <ui:staticText binding="#{setConfig.role}" id="role" text="#{setConfig.deviceRole.name}"/>
                            <ui:label binding="#{setConfig.label9}" id="label9" text="Configuration:"/>
                            <ui:staticText binding="#{setConfig.configuration}" id="configuration" text="#{setConfig.deviceConfig.name}"/>
                            <ui:label binding="#{setConfig.label11}" id="label11" text="Filename:"/>
                            <ui:staticText binding="#{setConfig.config_filename}" id="config_filename" text="#{setConfig.deviceConfig.fileName}"/>
                        </h:panelGrid>
                        <ui:button action="#{setConfig.set_config_button_action}" binding="#{setConfig.set_config_button}" id="set_config_button"
                            style="left: 23px; top: 432px; position: absolute" text="Send Configuration to Device"/>
                        <ui:button action="#{setConfig.return_button_action}" binding="#{setConfig.return_button}" id="return_button"
                            style="left: 431px; top: 384px; position: absolute" text="Return"/>
                        <ui:button action="#{setConfig.check_connection_button_action}" binding="#{setConfig.check_connection_button}"
                            id="check_connection_button" style="left: 23px; top: 384px; position: absolute" text="Check connection and capabilities"/>
                        <ui:label binding="#{setConfig.label12}" id="label12" style="left: 24px; top: 480px; position: absolute" text="Request:"/>
                        <ui:textArea binding="#{setConfig.request_area}" id="request_area"
                            style="border-width: 1px; border-style: double; height: 360px; left: 24px; top: 504px; position: absolute; width: 648px" text="#{setConfig.copyConfigRequest}"/>
                        <ui:alert binding="#{setConfig.alert1}" id="alert1" style="height: 94px; left: 648px; top: 72px; position: absolute; width: 166px"/>
                        <h:panelGrid binding="#{setConfig.netconf_panel}" columns="2" id="netconf_panel"
                            style="border-width: 1px; border-style: solid; height: 120px; left: 336px; top: 216px; position: absolute" width="288">
                            <ui:label binding="#{setConfig.label7}" id="label7" text="Netconf Role:"/>
                            <ui:staticText binding="#{setConfig.netconfRole}" id="netconfRole" text="#{setConfig.deviceToSet.netconfRole}"/>
                            <ui:label binding="#{setConfig.label13}" id="label13" text="Netconf Namespace:"/>
                            <ui:staticText binding="#{setConfig.namespace_field}" id="namespace_field" text="#{setConfig.deviceToSet.netconfNS}"/>
                            <ui:label binding="#{setConfig.label14}" id="label14" text="Netconf Path:"/>
                            <ui:staticText binding="#{setConfig.netconfpath_field}" id="netconfpath_field" text="#{setConfig.deviceToSet.netconfPath}"/>
                        </h:panelGrid>
                        <ui:button action="#{setConfig.getConfig_action}" binding="#{setConfig.getConfig}" id="getConfig"
                            style="left: 263px; top: 384px; position: absolute" text="Get Configuration"/>
                        <ui:button action="#{setConfig.validateConfig_action}" binding="#{setConfig.validateConfig}" id="validateConfig"
                            style="left: 263px; top: 432px; position: absolute" text="Validate Configuration"/>
                        <ui:button action="#{setConfig.restartDevice_action}" binding="#{setConfig.restartDevice}" id="restartDevice"
                            style="left: 431px; top: 432px; position: absolute" text="Restart Device"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
