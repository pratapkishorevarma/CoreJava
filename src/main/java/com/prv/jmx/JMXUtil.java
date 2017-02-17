/* $Header: biappsinst/projects/bilcmcommon/src/oracle/bi/framework/util/JMXUtil.java vivekv_bug-20730667/6 2015/06/24 23:48:41 vivekv Exp $ */

/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/

/*
 DESCRIPTION
 <This class will handle timeout for different JMX operations.>

 PRIVATE CLASSES
 <list of private classes defined - with one-line descriptions>

 NOTES
 <other useful comments, qualifications, etc.>

 MODIFIED    (MM/DD/YY)
 vivekv      06/17/15 - Bug 20730667 - ADD TIMEOUT LOGIC FOR ALL MBEAN RELATED ACTIVITIES
 vivekv      06/17/15 - Creation
 */

/**
 *  @version $Header: biappsinst/projects/bilcmcommon/src/oracle/bi/framework/util/JMXUtil.java vivekv_bug-20730667/6 2015/06/24 23:48:41 vivekv Exp $
 *  @author  vivekv  
 *  @since   11.1.12.0.0
 */

package com.prv.jmx;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 * This class will handle timeout for different JMX operations.
 * 
 * @author vivekv
 * 
 */
public class JMXUtil {

    private static final Logger _logger = Logger.getLogger(JMXUtil.class.getName());

    /**
     * Establishes a {@link MBeanServerConnection} to the specified running
     * AdminServer
     * 
     * @param adminServerHost
     *            the running AdminServer hostname
     * @param adminServerPort
     *            the running AdminServer listen port
     * @param adminServerUser
     *            the username to connect to running AdminServer
     * @param adminServerPassword
     *            the password to connect to the running AdminServer
     * @param mbeanServerURI
     *            the MBean Server URI name
     * @return a {@link MBeanServerConnection} to the specified running server
     */
    public static MBeanServerConnection getMBeanConnection(final String adminServerHost, final String adminServerPort, final String adminServerUser,
            final String adminServerPassword, final String mbeanServerURI) {
        return BIDomainMBeanProxyFactory.createMBeanConnection(adminServerHost, adminServerPort, adminServerUser, adminServerPassword, mbeanServerURI);
    }

    /**
     * Gets the specified attribute value from mbean configured with objectname
     * 
     * @param Domain
     *            's MBeanConnection object
     * @param MBean
     *            object name
     * @param attribute
     *            name of a mbean
     * @return attribute value corresponding to attribute name
     * @throws Exception
     */
    public static Object getMBeanAttribute(final MBeanServerConnection conn, final ObjectName objName, final String attrName) throws Exception {
        ExecuteWithTimeout taskExec = new ExecuteWithTimeout(BIFrameworkConstants.JMX_CON_TIMEOUT_SECS) {
            public Object execute() throws Exception {
                try {
                    return _getMBeanAttribute(conn, objName, attrName);
                } catch (Exception e) {
                    _logger.log(Level.SEVERE, "Exception while executing getMBeanAttribute operation. Error: " + e.getMessage(), e);
                    throw e;
                }
            }
        };
        return taskExec.executeWithTimeout();
    }

    /**
     * Gets the specified attribute value from mbean configured with objectname
     * 
     * @param Domain
     *            's MBeanConnection object
     * @param MBean
     *            object name
     * @param attribute
     *            name of a mbean
     * @return attribute value corresponding to attribute name
     * @throws Exception
     */
    private static Object _getMBeanAttribute(final MBeanServerConnection conn, final ObjectName objName, final String attrName) throws Exception {
        return conn.getAttribute(objName, attrName);
    }

    /**
     * Sets the specified attribute value of mbean configured with objectname
     * 
     * @param Domain
     *            's MBeanConnection object
     * @param MBean
     *            object name
     * @param attribute
     *            name of a mbean
     * @param attribute
     *            value corresponding to attribute name
     * @throws Exception
     */
    public static void setMBeanAttribute(final MBeanServerConnection conn, final ObjectName objName, final String attrName, final Object attValue) throws Exception {
        ExecuteWithTimeout taskExec = new ExecuteWithTimeout(BIFrameworkConstants.JMX_CON_TIMEOUT_SECS) {
            public Object execute() throws Exception {
                try {
                    _setMBeanAttribute(conn, objName, attrName, attValue);
                } catch (Exception e) {
                    _logger.log(Level.SEVERE, "Exception while executing setMBeanAttribute operation. Error: " + e.getMessage(), e);
                    throw e;
                }
                return null;
            }
        };
        taskExec.executeWithTimeout();
    }

    /**
     * Sets the specified attribute value of mbean configured with objectname
     * 
     * @param Domain
     *            's MBeanConnection object
     * @param MBean
     *            object name
     * @param attribute
     *            name of a mbean
     * @param attribute
     *            value corresponding to attribute name
     * @throws Exception
     */
    private static void _setMBeanAttribute(MBeanServerConnection conn, ObjectName objName, String attrName, Object attValue) throws Exception {
        Attribute att = new Attribute(attrName, attValue);
        conn.setAttribute(objName, att);
    }

    /**
     * Executes MBEAN's invoke operation
     * 
     * @param Domain
     *            's MBeanConnection object
     * @param MBean
     *            object name
     * @param operationName
     *            to be invoked on mbean
     * @param params
     *            to the mbean operation
     * @param signature
     *            of mbean operation
     * @return Object type
     * @throws Exception
     */
    private static Object _invoke(final MBeanServerConnection conn, final ObjectName name, final String operationName, final Object params[], final String signature[])
            throws Exception {
        return conn.invoke(name, operationName, params, signature);
    }

    /**
     * Executes MBEAN's invoke operation
     * 
     * @param Domain
     *            's MBeanConnection object
     * @param MBean
     *            object name
     * @param operationName
     *            to be invoked on mbean
     * @param params
     *            to the mbean operation
     * @param signature
     *            of mbean operation
     * @return Object type
     * @throws Exception
     */
    public static Object invoke(final MBeanServerConnection conn, final ObjectName mbeanObjName, final String operationName, final Object params[], final String signature[])
            throws Exception {
        ExecuteWithTimeout taskExec = new ExecuteWithTimeout(BIFrameworkConstants.JMX_CON_TIMEOUT_SECS) {
            public Object execute() throws Exception {
                try {
                    return _invoke(conn, mbeanObjName, operationName, params, signature);
                } catch (Exception e) {
                    _logger.log(Level.SEVERE, "Exception while executing invoke operation. Error: " + e.getMessage(), e);
                    throw e;
                }
            }
        };
        return taskExec.executeWithTimeout();
    }

    public static void main(String[] args) {
        final String host = "slc03vhn.us.oracle.com";
        final String port = "10201";
        final String adminUser = "FAAdmin";
        final String adminPwd = "fusionfa1";

        MBeanServerConnection mbeanConnection = null;

        try {
            mbeanConnection = JMXUtil.getMBeanConnection(host, port, adminUser, adminPwd, BIFrameworkConstants.WEBLOGIC_MBEANSERVERS_DOMAINRUNTIME.getValue());
            _logger.info("Successfully connected to " + mbeanConnection.getDefaultDomain());

            ObjectName topoName = new ObjectName(BIFrameworkConstants.FA_APPLCORE_COMMON_DOMAIN_TOPOLOGY_MGR_MBEAN.getValue());
            String[] offeringIds = (String[]) JMXUtil.getMBeanAttribute(mbeanConnection, topoName,
                    BIFrameworkConstants.FA_APPLCORE_COMMON_DOMAIN_TOPOLOGY_MGR_MBEAN_ATTRIBUTE.getValue());

            _logger.info("TopoName: " + topoName.toString());
            _logger.info("Offering IDs: " + Arrays.toString(offeringIds));

            if (offeringIds.length == 0) {
                String error = "ERROR: no provisioned offering is found.";
                _logger.severe(error);
            }
        } catch (Exception t) {
            _logger.info(t.getClass().getSimpleName());
            t.printStackTrace();
        }
    }
}
