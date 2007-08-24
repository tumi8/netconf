<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <xsl:element name="xsl:stylesheet">
      <xsl:attribute name="version">1.0</xsl:attribute>
      <xsl:call-template name="printStartingTemplate"/>
      <xsl:apply-templates/>
      <xsl:call-template name="printEndingTemplate"/>
      <xsl:call-template name="printDeepCopy"/>
    </xsl:element>
  </xsl:template>


  <!-- edit-config with 'merge' operation -->
  <xsl:template match="*[@*[local-name()='operation' and namespace-uri()='urn:ietf:params:xml:ns:netconf:base:1.0']='merge']">
    <xsl:element name="xsl:template">
      <xsl:attribute name="match"><!--rbac/users/user[lastname='cridlig']-->
        <xsl:call-template name="buildxpathancestors"/>
		<xsl:call-template name="buildxpathcurrent"/>
      </xsl:attribute>

      <xsl:copy>
        <xsl:for-each select="@*[local-name()!='operation' or namespace-uri()!='urn:ietf:params:xml:ns:netconf:base:1.0'] | * | text()">
          <xsl:call-template name="deepcopy"/>
        </xsl:for-each>
        <xsl:element name="xsl:for-each">
          <xsl:attribute name="select"><xsl:text>*</xsl:text></xsl:attribute>
          <xsl:element name="xsl:if">
            <xsl:attribute name="test">
              <xsl:for-each select="*">
                <xsl:text>name()!='</xsl:text>
                <xsl:value-of select="name()"/>
                <xsl:text>'</xsl:text>
                <xsl:if test="position()!=last()">
                  <xsl:text> and </xsl:text>
                </xsl:if>
              </xsl:for-each>
            </xsl:attribute>
            <xsl:element name="xsl:copy-of">
              <xsl:attribute name="select">
                <xsl:text>*</xsl:text>
              </xsl:attribute>
            </xsl:element>
          </xsl:element>
        </xsl:element>
      </xsl:copy>
    </xsl:element>
  </xsl:template>


  <!-- edit-config with 'replace' operation -->
  <xsl:template match="*[@*[local-name()='operation' and namespace-uri()='urn:ietf:params:xml:ns:netconf:base:1.0']='replace']">
    <xsl:element name="xsl:template">
      <xsl:attribute name="match"><!--rbac/users/user[lastname='cridlig']-->
        <xsl:call-template name="buildxpathancestors"/>
		<xsl:call-template name="buildxpathcurrent"/>
      </xsl:attribute>
      <xsl:call-template name="deepcopy"/>
    </xsl:element>
  </xsl:template>


  <!-- edit-config with 'create' operation -->
  <xsl:template match="*[@*[local-name()='operation' and namespace-uri()='urn:ietf:params:xml:ns:netconf:base:1.0']='create']">
    <xsl:element name="xsl:template">
      <xsl:attribute name="match"><!--rbac/users/user[lastname='cridlig']-->
        <!-- ERROR: check XPath spec to get the ancestor node (parent::* is not supported by 4Suite):
        <xsl:call-template name="buildxpathancestors"/>
        <xsl:text>parent::*</xsl:text>
        -->
    <xsl:for-each select="ancestor::*">
      <xsl:text>*[local-name()='</xsl:text>
      <xsl:value-of select="name()"/>
      <xsl:text>'</xsl:text>

      <!-- selection attributes-->
      <xsl:for-each select="@*">
        <xsl:text> and @</xsl:text>
        <xsl:value-of select="name()"/>
        <xsl:text>='</xsl:text>
        <xsl:value-of select="."/>
        <xsl:text>'</xsl:text>
      </xsl:for-each>

      <xsl:text>]</xsl:text>

      <!-- Only if their is a content match node child-->
      <xsl:for-each select="*[position()=2]">
        <xsl:for-each select="ancestor::*[position()=1]">
          <xsl:text>[*[local-name()='</xsl:text>
          <xsl:for-each select="*[position()=1]">
            <xsl:value-of select="name()"/>
          </xsl:for-each>
          <xsl:text>']='</xsl:text>
          <xsl:value-of select="*/text()"/>
          <xsl:text>']</xsl:text>
        </xsl:for-each>
      </xsl:for-each>
      
      <xsl:if test="position()!=last()">
        <xsl:text>/</xsl:text>
      </xsl:if>
    </xsl:for-each>

      </xsl:attribute>
      <xsl:element name="xsl:copy">
        <xsl:call-template name="deepcopy"/>
        <xsl:element name="xsl:copy-of">
          <xsl:attribute name="select">
            <xsl:text>@* | *</xsl:text>
          </xsl:attribute>
        </xsl:element>
      </xsl:element>
    </xsl:element>
  </xsl:template>


  <!-- edit-config with 'delete' operation -->
  <xsl:template match="*[@*[local-name()='operation' and namespace-uri()='urn:ietf:params:xml:ns:netconf:base:1.0']='delete']">
    <xsl:element name="xsl:template">
      <xsl:attribute name="match"><!--rbac/users/user[lastname='cridlig']-->
        <xsl:call-template name="buildxpathancestors"/>
		<xsl:call-template name="buildxpathcurrent"/>
      </xsl:attribute>
    </xsl:element>
  </xsl:template>

  <xsl:template name="buildxpathcurrent">
    <xsl:text>*[local-name()='</xsl:text>
    <xsl:value-of select="name()"/>
    <xsl:text>'][*[local-name()='</xsl:text>
    <xsl:for-each select="*[position()=1]">
      <xsl:value-of select="name()"/>
    </xsl:for-each>
    <xsl:text>']='</xsl:text>
    <xsl:value-of select="*[position()=1][name()]"/>
    <xsl:text>']</xsl:text>
  </xsl:template>

  <xsl:template name="buildxpathancestors">
    <xsl:for-each select="ancestor::*">
      <xsl:text>*[local-name()='</xsl:text>
      <xsl:value-of select="name()"/>
      <xsl:text>'</xsl:text>

      <!-- selection attributes-->
      <xsl:for-each select="@*">
        <xsl:text> and @</xsl:text>
        <xsl:value-of select="name()"/>
        <xsl:text>='</xsl:text>
        <xsl:value-of select="."/>
        <xsl:text>'</xsl:text>
      </xsl:for-each>

      <xsl:text>]</xsl:text>

      <!-- Only if their is a content match node child-->
      <xsl:for-each select="*[position()=2]">
        <xsl:for-each select="ancestor::*[position()=1]">
          <xsl:text>[*[local-name()='</xsl:text>
          <xsl:for-each select="*[position()=1]">
            <xsl:value-of select="name()"/>
          </xsl:for-each>
          <xsl:text>']='</xsl:text>
          <xsl:value-of select="*/text()"/>
          <xsl:text>']</xsl:text>
        </xsl:for-each>
      </xsl:for-each>
          
      <xsl:text>/</xsl:text>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="deepcopy">
    <xsl:copy>
      <xsl:for-each select="@*[local-name()!='operation' or namespace-uri()!='urn:ietf:params:xml:ns:netconf:base:1.0'] | * | text()">
        <xsl:call-template name="deepcopy"/>
      </xsl:for-each>
    </xsl:copy>
  </xsl:template>


  <xsl:template name="printStartingTemplate">
    <xsl:element name="xsl:template">
      <xsl:attribute name="match">/</xsl:attribute>
      <xsl:element name="xsl:apply-templates"/>
    </xsl:element>
  </xsl:template>


  <xsl:template name="printEndingTemplate">
    <xsl:element name="xsl:template">
      <xsl:attribute name="match">@* | *</xsl:attribute>
      <xsl:element name="xsl:copy">
        <xsl:element name="xsl:apply-templates">
          <xsl:attribute name="select"><xsl:text>@* | * | text()</xsl:text></xsl:attribute>
        </xsl:element>
      </xsl:element>
    </xsl:element>
    <xsl:element name="xsl:template">
      <xsl:attribute name="match">text()</xsl:attribute>
      <xsl:element name="xsl:value-of">
        <xsl:attribute name="select"><xsl:text>normalize-space(.)</xsl:text></xsl:attribute>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template name="printDeepCopy">
    <xsl:element name="xsl:template">
      <xsl:attribute name="name">deepcopy</xsl:attribute>
      <xsl:element name="xsl:copy">
        <xsl:element name="xsl:for-each">
          <xsl:attribute name="select"><xsl:text>@* | * | text()</xsl:text></xsl:attribute>
          <xsl:element name="xsl:call-template">
            <xsl:attribute name="name">deepcopy</xsl:attribute>
          </xsl:element>
        </xsl:element>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template match="text()">
		
	</xsl:template>

</xsl:stylesheet>
