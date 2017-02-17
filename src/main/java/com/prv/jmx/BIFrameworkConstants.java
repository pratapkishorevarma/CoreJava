package com.prv.jmx;

import java.io.File;

/**
 * {@link BIFrameworkConstants} is an enum used as a central repository for all {@link String} constants used by all classes in the BI Framework.
 *
 * @version $Header: biappsinst/projects/bilcmcommon/src/oracle/bi/framework/util/BIFrameworkConstants.java /main/37 2015/04/15 00:44:01 vivekv Exp $
 * @author  jhuey   
 * @since   11.1.1.7.2
 */
public enum BIFrameworkConstants
{
    JNDI_ROOT("jndi"),
    JMX_PROTOCOL("t3"),
    WEBLOGIC_MANAGEMENT_REMOTE("weblogic.management.remote"),
    WEBLOGIC_MBEANSERVERS_DOMAINRUNTIME("weblogic.management.mbeanservers.domainruntime"),
    BI_APPLICATION_NAME("coreapplication"),
    RPD_FILE_EXTENSION("rpd"),
    DELETE_TEMP_FILES("delete.temp.files"),
    DECLARE_XPATH("/Repository/DECLARE"),
    CONNECTIONPOOL_NODE_NAME("ConnectionPool"),
    PARENTNAME_ATTRIBUTE_NAME("parentName"),
    PARENTUID_ATTRIBUTE_NAME("parentUid"),
    NAME_ATTRIBUTE_NAME("name"),
    USER_ATTRIBUTE_NAME("user"),
    PASSWORD_ATTRIBUTE_NAME("password"),
    APPSERVERNAME_ATTRIBUTE_NAME("appServerName"),
    DATASOURCE_ATTRIBUTE_NAME("dataSource"),
    TYPE_ATTRIBUTE_NAME("type"),
    VARIABLE_NODE_NAME("Variable"),
    EXPR_NODE_NAME("Expr"),
    VALUEOF("VALUEOF"),
    DESCRIPTION_NODE_NAME("Description"),
    VALUE_NODE_NAME("Value"),
    XUDML_CDATA_SECTION_NODE_LIST(DESCRIPTION_NODE_NAME.getValue() + " " + EXPR_NODE_NAME.getValue() + " " + VALUE_NODE_NAME.getValue()),
    ADMINSERVER("AdminServer"),
    bi_cluster("bi_cluster"),
    bi_server_NAME_PREFIX("bi_server"),
    BI_SERVER_NAME_REGEX_PATTERN(bi_server_NAME_PREFIX.getValue() + "\\d+"),
    FUSIONAPPS("fusionapps"),
    SETDOMAINENVSH("setDomainEnv.sh"),
    SETDOMAINENVCMD("setDomainEnv.cmd"),
    REGISTRYXML("registry.xml"),
    DOMAIN_REGISTRYXML("domain-registry.xml"),
    BIDOMAIN("BIDomain"),
    COMMONDOMAIN("CommonDomain"),
    CRMDOMAIN("CRMDomain"),
    FINANCIALDOMAIN("FinancialDomain"),
    PROJECTSDOMAIN("ProjectsDomain"),
    BI_SYSTEM_APPID_CREDENTIAL_ALIAS_MAP("oracle.bi.system"),
    BI_SYSTEM_APPID_CREDENTIAL_ALIAS_KEY("system.user"),
    ORACLE_PATCHING_CREDENTIAL_ALIAS_MAP("oracle.patching"),
    ORACLE_SECURITY_CREDENTIAL_ALIAS_MAP("oracle.apps.security"),
    FUSION_APPS_BI_ALIAS_KEY("FUSION_APPS_BI-KEY"),
    FUSION_APPS_MDS_ALIAS_KEY("FUSION_APPS_MDS-KEY"),
    FUSION_APPS_FUSION_BIA_CLOUD_ALIAS_KEY("FUSION_APPS_FUSION_BIA_CLOUD-KEY"),
    FUSION_APPS_OTBI_ALIAS_KEY("FUSION_APPS_OTBI-KEY"),
    FUSION_APPS_DBA_ALIAS_KEY("FUSION_APPS_DBA-KEY"),
    FUSION_APPS_LCM_SUPER_ADMIN_KEY("FUSION_APPS_LCM_SUPER_ADMIN-KEY"),
    FUSION_APPS_BI_APPID_ALIAS_KEY("FUSION_APPS_BI_APPID-KEY"),
    FUSION_APPS_BIPLATFORM_ALIAS_KEY("FUSION_APPS_BIPLATFORM-KEY"),
    FUSION_APPS_FMW_RUNTIME_ALIAS_KEY("FUSION_APPS_FMW_RUNTIME-KEY"),
    FUSION_APPS_PATCH_FUSION_RUNTIME_SCHEMA_ALIAS_KEY("FUSION_APPS_PATCH_FUSION_RUNTIME_SCHEMA-KEY"),
    FUSION_APPS_PATCH_OAM_RWG_KEY("FUSION_APPS_PATCH_OAM_RWG-KEY"),
    FUSION_APPS_PATCH_OAM_ADMIN_KEY("FUSION_APPS_PATCH_OAM_ADMIN-KEY"),
    ORACLE_JDBC_DRIVER_NAME("oracle.jdbc.OracleDriver"),
    RPD_MESSAGE_STRING_EDITOR_OTBI_JDBC_DATA_SOURCE_NAME("OTBIRpdMsgsDS"),
    RPD_MESSAGE_STRING_EDITOR_OTBI_JDBC_DATA_SOURCE_JNDI_NAME("jdbc/OTBIRpdMsgsDS"),
    APPLICATIONS_CONFIG("APPLICATIONS_CONFIG"),
    FAINSTLOC("faInst.loc"),
    FAPATCH("fapatch"),
    FUSION_ENVPROPERTIES("FUSION_env.properties"),
    PRODUCTPROPERTIES(".product.properties"),
    JAVA_HOME("JAVA_HOME"),
    UPDATEBIAPPSMETADATA_QUALIFIED_CLASS_NAME("oracle.as.biapps.patch.update.UpdateBIAppsMetadata"),
    BIAPPSPATCHJAR_BI_ORACLE_HOME_RELATIVE_PATH("biapps/patch/biappspatch.jar"),
    OPSS_JDBC_DATA_SOURCE_JNDI_NAME("jdbc/OPSSDBDS"),
    OPSS_JDBC_DATA_SOURCE_NAME("opss-DBDS"),
    FMWDB_JDBC_DATA_SOURCE_NAME("fmwDB"),
    MDS_OWSM_DATA_SOURCE_NAME("mds-owsm"),
    REFERENCE_DATA_SOURCE_NAME("ApplicationDB"),
    BIANALYTICS_DATA_SOURCE_NAME("BIAnalytics"),
    BIEXTENDER_ESSBASE_DATABASE_NAME("BI Extender Essbase"),
    ESSBASE("Essbase"),
    CONNECTION_POOL("Connection Pool"),
    BIACM_DEPLOYMENT_MODE("BIACM_DEPLOYMENT_MODE"),
    CLOUD("cloud"),
    WEBLOGICJAR_WEBLOGIC_HOME_RELATIVE_PATH("server/lib/weblogic.jar"),
    WEBLOGIC_WLST_CLASS_NAME("weblogic.WLST"),
    TRIMMED_RPD_CACHE_DIR("lcm"+ File.separator +"rpd"+ File.separator +".trimRPDCache"),
    TRIMMED_GOLDEN_RPD("trimmedGolden.rpd"),
    BI_P2T_RPD_TAR_NAME("bip2t_rpd.tar"),    
    BI_P2T_WEBCAT_TAR_NAME("bip2t_webcat.tar"),    
    FA_APPLCORE_COMMON_DOMAIN_TOPOLOGY_MGR_MBEAN("oracle.topology:name=Topology,type=TopologyRuntimeMBean"),
    FA_APPLCORE_COMMON_DOMAIN_TOPOLOGY_MGR_MBEAN_ATTRIBUTE("SelectedProvisionedConfigs"),
    REG_PROPERTIES("reg.properties"),
    WALLET_FILE_NAME("cwallet.sso"),
    GOLDEN_PWDCHGD_RPD_PREFIX("GoldenRPDPWD_"),
    RPDCSAEXPORTFILENAME("rpdCSA.xml"),
    BI_EXTENDER_PARAMS_FILENAME("biExtenderParameters.xml"),    
    PWD_PROTECTED_WALLET_FILE("ewallet.p12"),
    P2T_WALLET_PWD("Admin123");
    
    public static final long JMX_CON_TIMEOUT_SECS = 600;

    private final String value;

    /**
     * Create a new {@link String} constant with the specified value.
     *
     * @param value constant {@link String} value
     */
    private BIFrameworkConstants(String value)
    {
        this.value = value;
    }

    /**
     * Returns the constant {@link String} value.
     *
     * @return the constant {@link String} value
     */
    public String toString()
    {
        return this.value;
    }

    /**
     * Returns the constant {@link String} value.
     *
     * @return the constant {@link String} value
     */
    public String getValue()
    {
        return this.value;
    }
}
