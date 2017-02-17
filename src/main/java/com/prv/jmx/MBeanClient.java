package com.prv.jmx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.RuntimeMBeanException;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

public class MBeanClient {

	private static final String MBEAN_NAME = "oracle.apps.setup.topology.runtime.mbean:Location=FunctionalSetupServer_1,name=TopologyInfo,type=TopologyInfoRuntimeMBean,Application=SetupApp,ApplicationVersion=V2.0";
	private static final String OPERATION_NAME = "retrieveAllLicensedOfferings";
	private static final String CLUSTER_MBEAN = "com.bea:Name=FunctionalSetupCluster,Location=CommonDomain,Type=Cluster";
	private static final String ATTRIBUTE = "Servers";

	public static void main(String[] args) throws IOException, MalformedObjectNameException {

		String host = args[0];
		String port = args[1];
		String adminUser = args[2];
		String adminPwd =args[3];
		
		
		MBeanServerConnection mbeanConnection = null;
		mbeanConnection = JMXUtil.getMBeanConnection(host, port, adminUser, adminPwd, BIFrameworkConstants.WEBLOGIC_MBEANSERVERS_DOMAINRUNTIME.getValue());

		getLicensedOfferings(mbeanConnection);
		//getServers(mbeanConnection);
		//getMBeans(mbeanConnection);
	}

	private static void getMBeans(MBeanServerConnection mbeanConnection) throws IOException, MalformedObjectNameException {

		ObjectName name = new ObjectName("oracle.apps.setup.topology.runtime.mbean:name=TopologyInfo,type=TopologyInfoRuntimeMBean,Application=SetupApp,*");
		Set<ObjectInstance> queryMBeans = mbeanConnection.queryMBeans(name , null);
		System.out.println(queryMBeans.size());
		for(ObjectInstance object: queryMBeans){
			System.out.println(object.getObjectName());			
		}
	}
	
	private static void getServers(MBeanServerConnection mbeanConnection) {
		try {
			ObjectName[] objectNames = (ObjectName[])JMXUtil.getMBeanAttribute(mbeanConnection, new ObjectName(CLUSTER_MBEAN), ATTRIBUTE);
			String[] servers = new String[objectNames.length];
			for (int i=0; i < objectNames.length; i++) {
				servers[i] = objectNames[i].getKeyProperty("Name");
			}
			System.out.println(Arrays.toString(servers));				
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getLicensedOfferings(MBeanServerConnection mbeanConnection) {
		TabularData[] offeringIds = null;
		try {
			offeringIds = (TabularData[]) JMXUtil.invoke(mbeanConnection, new ObjectName(MBEAN_NAME), OPERATION_NAME, new Object[]{}, new String[]{});
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			if(e instanceof InstanceNotFoundException){
				System.out.println("Instance not found");
			} else if(e instanceof RuntimeMBeanException){
				if(e.getCause() instanceof UnsupportedOperationException)
					System.out.println("unsupported exception");
				else
					System.out.println("runtime mbean exception");
			}else{
				System.out.println("Other exception");
			}
		}

		List<String> licensedOfferings = new ArrayList<String>();
		if(offeringIds != null){
			for (TabularData tabularData : offeringIds) {
				Collection<CompositeData> values=(Collection<CompositeData>)tabularData.values();
				for (CompositeData compositeData : values) {
					String key=(String) compositeData.get("key");
					if(key.equals("NAME"))
						licensedOfferings.add((String) compositeData.get("value"));
				}
			}
		}
		System.out.println(licensedOfferings);
	}
}
