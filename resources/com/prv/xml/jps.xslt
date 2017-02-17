<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" method="xml" version="1.0"/>
    
    <xsl:template match="//serviceInstance[@name='idstore.ldap']">
        <serviceInstance name="idstore.loginmodule" provider="jaas.login.provider">
		<description>Identity Store Login Module</description>
			<property name="loginModuleClassName"
                      value="oracle.security.jps.internal.jaas.module.idstore.IdStoreLoginModule"/>
			<property name="jaas.login.controlFlag" value="REQUIRED"/>
		</serviceInstance>
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
            <xsl:if test="not(./property[@name='username.attr'])">
            <property name="username.attr" value="uid"/>
            </xsl:if>
			<extendedProperty>
                <name>user.search.bases</name>
                <values>
                    <value>cn=Users,dc=us,dc=oracle,dc=com</value>
                </values>
            </extendedProperty>
            <extendedProperty>
                <name>group.search.bases</name>
                <values>
                    <value>cn=Groups,dc=us,dc=oracle,dc=com</value>
                </values>
            </extendedProperty>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="//serviceInstance[@name='idstore.ldap']/property[@name='idstore.config.provider']">
    		<property name="idstore.type" value="OID"/>
			<property name="bootstrap.security.principal.key" value="bootstrap_idstore_key"/>
            <property name="ldap.url" value="ldap://oidfa.us.oracle.com:3060"/>
    </xsl:template>
    <xsl:template match="//serviceInstance[@name='idstore.ldap']/property[@name='PROPERTY_ATTRIBUTE_MAPPING']"/>
    
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

</xsl:transform>
