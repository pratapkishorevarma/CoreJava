package com.prv.jmx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

/**
 * Utility class to establish JMX MBean connections to a BI Domain AdminServer.
 *
 * @version $Header: biappsinst/provision/scripts/bidomain/source/src/oracle/bi/framework/util/BIDomainMBeanProxyFactory.java /main/7 2015/02/12 08:51:15 rmunugal Exp $
 * @author  jhuey   
 * @since   11.1.1.7.2
 */
public class BIDomainMBeanProxyFactory
{
    private static Logger _logger = Logger.getLogger(BIDomainMBeanProxyFactory.class.getName());
    
    /**
     * Establishes a {@link MBeanServerConnection} to the specified running AdminServer, default connection retrival wait time is 10 mins.
     *
     * @param adminServerHost the running AdminServer hostname
     * @param adminServerPort the running AdminServer listen port
     * @param adminServerUser the username to connect to running AdminServer
     * @param adminServerPassword the password to connect to the running AdminServer
     * @param mbeanServerURI the MBean Server URI name
     * @return a {@link MBeanServerConnection} to the specified running server
     */
    public static MBeanServerConnection createMBeanConnection(final String adminServerHost, final String adminServerPort, final String adminServerUser, final String adminServerPassword, final String mbeanServerURI)
    {
        return createMBeanConnection(adminServerHost, adminServerPort, adminServerUser, adminServerPassword, mbeanServerURI, BIFrameworkConstants.JMX_CON_TIMEOUT_SECS);
    }

    /**
     * Establishes a {@link MBeanServerConnection} to the specified running AdminServer.
     *
     * @param adminServerHost the running AdminServer hostname
     * @param adminServerPort the running AdminServer listen port
     * @param adminServerUser the username to connect to running AdminServer
     * @param adminServerPassword the password to connect to the running AdminServer
     * @param mbeanServerURI the MBean Server URI name
     * @param timeoutInSeconds the JMX connection timeout in seconds
     * @return a {@link MBeanServerConnection} to the specified running server
     */
    public static MBeanServerConnection createMBeanConnection(final String adminServerHost, final String adminServerPort, final String adminServerUser, final String adminServerPassword, final String mbeanServerURI , final long timeoutInSeconds)
    {
        final BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(1);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            public void run() {
                Map<String, String> propMap = new HashMap<String, String>();
                propMap.put(Context.SECURITY_PRINCIPAL, adminServerUser);
                propMap.put(Context.SECURITY_CREDENTIALS, adminServerPassword);
                propMap.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, BIFrameworkConstants.WEBLOGIC_MANAGEMENT_REMOTE.getValue());
                JMXServiceURL serviceURL = null;
                try
                {
                    serviceURL = new JMXServiceURL(BIFrameworkConstants.JMX_PROTOCOL.getValue(), adminServerHost, Integer.parseInt(adminServerPort), "/" + BIFrameworkConstants.JNDI_ROOT.getValue() + "/" + mbeanServerURI);
                    JMXConnector connector = JMXConnectorFactory.connect(serviceURL, propMap);
                    MBeanServerConnection connection = connector.getMBeanServerConnection();
                    if (!queue.offer(connection))
                        connector.close();
                } catch (Throwable t) {
                        queue.offer(t);
                }
               }
        });
        
        Object result;
        try {
            result = queue.poll(timeoutInSeconds, TimeUnit.SECONDS);
            if (result == null) {
                if (!queue.offer(""))
                    result = queue.take();
            }
        } catch (InterruptedException e) {
            RuntimeException re =  new RuntimeException("Unable to create JMX connection to remote Domain "  +  adminServerHost +":" +adminServerPort  +" "+ e.getMessage(), e);
            _logger.log(Level.SEVERE, re.getMessage(), re);
            throw re;
        } finally {
            executor.shutdown();
        }
        
        if (result == null){
            RuntimeException re =  new RuntimeException("JMX Connection timed out: " +  adminServerHost +":" +adminServerPort);
            _logger.log(Level.SEVERE, re.getMessage(), re);
            throw re;
        }
        if (result instanceof MBeanServerConnection)
            return (MBeanServerConnection) result;
        
        try {
            throw (Throwable) result;
        } catch (Throwable e) {
            RuntimeException re = new RuntimeException("Unable to create JMX connection to remote  Domain  " + adminServerHost +":" +adminServerPort +" " + e.getMessage(), e);
            _logger.log(Level.SEVERE, re.getMessage(), re);
            throw re;
        }
    }

    
    /**
     * Creates a MBean Proxy from the specified running AdminServer.
     *
     * @param adminServerHost the running AdminServer hostname
     * @param adminServerPort the running AdminServer listen port
     * @param adminServerUser the username to connect to running AdminServer
     * @param adminServerPassword the password to connect to the running AdminServer
     * @param mbeanServerURI the MBean Server URI name
     * @param mbeanName the {@link ObjectName} for the specified MBean
     * @param mbeanClass the {@link Class} for the specified MBean
     * @return an instance of {@link Class} for the specified running server
     */
    public static <T> T createMBeanProxy(final String adminServerHost, final String adminServerPort, final String adminServerUser, final String adminServerPassword, final String mbeanServerURI, final ObjectName mbeanName, final Class<T> mbeanClass)
    {
        MBeanServerConnection mbeanConnection = createMBeanConnection(adminServerHost, adminServerPort, adminServerUser, adminServerPassword, mbeanServerURI);
    	return JMX.newMBeanProxy(mbeanConnection, mbeanName, mbeanClass);
    }

    /**
     * Creates a WebLogic MBeanServers Domain Runtime MBean Proxy from the specified running AdminServer.
     *
     * @param adminServerHost the running AdminServer hostname
     * @param adminServerPort the running AdminServer listen port
     * @param adminServerUser the username to connect to running AdminServer
     * @param adminServerPassword the password to connect to the running AdminServer
     * @param mbeanName the {@link ObjectName} for the specified MBean
     * @param mbeanClass the {@link Class} for the specified MBean
     * @return an instance of {@link Class} for the specified running server
     */
    public static <T> T createDomainRuntimeMBeanProxy(final String adminServerHost, final String adminServerPort, final String adminServerUser, final String adminServerPassword, final ObjectName mbeanName, final Class<T> mbeanClass)
    {
        return createMBeanProxy(adminServerHost, adminServerPort, adminServerUser, adminServerPassword, BIFrameworkConstants.WEBLOGIC_MBEANSERVERS_DOMAINRUNTIME.getValue(), mbeanName, mbeanClass);
    }
}
