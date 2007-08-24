<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{editDevice.page1}" id="page1">
            <ui:html binding="#{editDevice.html1}" id="html1">
                <ui:head binding="#{editDevice.head1}" id="head1">
                    <ui:link binding="#{editDevice.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{editDevice.body1}" id="body1" style="-rave-layout: grid">
                    <ui:form binding="#{editDevice.form1}" id="form1" virtualFormsConfig="save | basicPanel:name_field basicPanel:type_field basicPanel:descript_textarea connectionPanel:host_field connectionPanel:port_field connectionPanel:user_field connectionPanel:password_field agent_panel:netconf_role_field agent_panel:netconf_Namespace agent_panel:path informationPanel:monitored_network_field informationPanel:roleSelect | save_button , discard | | discard_button">
                        <ui:label binding="#{editDevice.label1}" id="label1" labelLevel="1" style="position: absolute; left: 24px; top: 24px" text="Device"/>
                        <ui:button action="#{editDevice.save_button_action}" binding="#{editDevice.save_button}" id="save_button"
                            style="left: 23px; top: 792px; position: absolute" text="Save changes"/>
                        <ui:button action="case2" binding="#{editDevice.discard_button}" id="discard_button" style="left: 143px; top: 792px; position: absolute" text="Discard changes"/>
                        <ui:messageGroup binding="#{editDevice.messageGroup1}" id="messageGroup1" style="height: 190px; left: 384px; top: 72px; position: absolute; width: 310px"/>
                        <h:panelGrid binding="#{editDevice.basicPanel}" id="basicPanel"
                            style="border-width: 1px; border-style: solid; height: 192px; left: 24px; top: 72px; position: absolute" width="336">
                            <ui:label binding="#{editDevice.label3}" id="label3" text="Basic Information:"/>
                            <ui:textField binding="#{editDevice.name_field}" id="name_field" label="Name:" required="true" text="#{editDevice.device.name}" validator="#{editDevice.name_field_validate}"/>
                            <ui:textField binding="#{editDevice.type_field}" columns="10" id="type_field" label="Type:" text="#{editDevice.device.type}"/>
                            <ui:textArea binding="#{editDevice.descript_textarea}" id="descript_textarea" label="Description:" text="#{editDevice.device.description}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{editDevice.connectionPanel}" id="connectionPanel"
                            style="border-width: 1px; border-style: solid; height: 192px; left: 24px; top: 288px; position: absolute" width="336">
                            <ui:label binding="#{editDevice.label2}" id="label2" text="Agent Information:"/>
                            <ui:textField binding="#{editDevice.host_field}" columns="16" id="host_field" label="Host:" required="true"
                                text="#{editDevice.device.hostIP}" validator="#{editDevice.host_field_validate}"/>
                            <ui:textField binding="#{editDevice.port_field}" columns="6" converter="#{editDevice.integerConverter1}" id="port_field"
                                label="Port:" required="true" text="#{editDevice.device.port}" validator="#{editDevice.longRangeValidator1.validate}"/>
                            <ui:textField binding="#{editDevice.user_field}" id="user_field" label="User:" required="true" text="#{editDevice.device.user}"/>
                            <ui:passwordField binding="#{editDevice.password_field}" id="password_field" label="Password:"
                                password="#{editDevice.device.password}" required="true"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{editDevice.informationPanel}" id="informationPanel"
                            style="border-width: 1px; border-style: solid; height: 264px; left: 24px; top: 504px; position: absolute" width="336">
                            <ui:label binding="#{editDevice.label4}" id="label4" text="Monitoring Information:"/>
                            <ui:label binding="#{editDevice.label8}" id="label8" text="Monitored Network:"/>
                            <ui:textField binding="#{editDevice.monitored_network_field}" id="monitored_network_field" text="#{editDevice.device.monitoredNetwork}"/>
                            <ui:label binding="#{editDevice.label5}" id="label5" text="Role:"/>
                            <ui:staticText binding="#{editDevice.roleN}" id="roleN" text="#{editDevice.device.role}"/>
                            <ui:dropDown binding="#{editDevice.roleSelect}" id="roleSelect" items="#{editDevice.roleOptions}" label="Select new Role:" selected="#{editDevice.device.role}"/>
                        </h:panelGrid>
                        <h:panelGrid binding="#{editDevice.agent_panel}" id="agent_panel"
                            style="border-width: 1px; border-style: solid; height: 192px; left: 384px; top: 288px; position: absolute" width="336">
                            <ui:label binding="#{editDevice.label7}" id="label7" text="Additional Agent Information:"/>
                            <ui:textField binding="#{editDevice.netconf_role_field}" id="netconf_role_field" label="NetConf Role:" text="#{editDevice.device.netconfRole}"/>
                            <ui:textField binding="#{editDevice.netconf_Namespace}" id="netconf_Namespace" label="Namespace:" text="#{editDevice.device.netconfNS}"/>
                            <ui:textField binding="#{editDevice.path}" id="path" label="Path:" text="#{editDevice.device.netconfPath}"/>
                        </h:panelGrid>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
