package com.prv.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BILCMServicesDAO {

	private static final String FUSION_BIPLATFORM = "FUSION_BIPLATFORM";
	private static final String BI_LCM_SERVICES_TABLE = "BI_LCM_SERVICES_EXEC_INFO";
	private static final String WEBCAT_USERNAME_SYNCUP = "WEBCAT_USERNAME_SYNCUP";
	private String hostname;
	private String port;
	private String serviceName;
	private Properties props;
	private final String createTable = "CREATE TABLE FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO (ID NUMBER(10) PRIMARY KEY, TYPE VARCHAR2(50) NOT NULL, EXEC_TIME TIMESTAMP NOT NULL, "
			+ " EXEC_STATUS VARCHAR2(50) NOT NULL, EXEC_INPUTS VARCHAR2(50) NOT NULL, COMMENTS VARCHAR2(255))";
	private final String insertRow = "INSERT INTO FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO (ID , TYPE, EXEC_TIME, EXEC_STATUS , EXEC_INPUTS, COMMENTS) VALUES (?,?,?,?,?,?)";
	private final String updateRow = "UPDATE FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO SET EXEC_TIME=?, EXEC_INPUTS=? WHERE TYPE=?";
	
	private static final Logger logger = Logger.getLogger(BILCMServicesDAO.class.getName());
	
	public BILCMServicesDAO(String hostname, String port, String serviceName, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		props =  new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		this.hostname = hostname;
		this.port = port;
		this.serviceName = serviceName;
	}
	
	public void createBILcmServicesTable(){
		Connection connection = null;;
		Statement smt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ hostname +":"+ port +"/"+ serviceName, props);
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet tables = metaData.getTables(null, FUSION_BIPLATFORM, BI_LCM_SERVICES_TABLE, null);
			if(tables.next()){
				logger.log(Level.INFO, "FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO is already created.");
			}else{
				smt = connection.createStatement();
				smt.execute(createTable);
				logger.log(Level.INFO, "Table FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO created successfully.");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception while creating table FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO",e);
		}finally{
			closeResources(connection, smt);
		}
	}
	
	public void insertIntoBILcmServicesTable(){
		
		Connection connection = null;
		PreparedStatement psmt = null;
		
		SimpleDateFormat ldapDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		Calendar currentCal = Calendar.getInstance();
		currentCal.add(Calendar.MINUTE, -30);
		String dateString = ldapDateFormat.format(currentCal.getTime());
		
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ hostname +":"+ port +"/"+ serviceName, props);
			psmt = connection.prepareStatement(insertRow);
			psmt.setInt(1, 1);
			psmt.setString(2, WEBCAT_USERNAME_SYNCUP);
			psmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			psmt.setString(4, "SUCCESS");
			psmt.setString(5, dateString);
			psmt.setString(6, "webcat rename account executed for users");
			
			int executeUpdate = psmt.executeUpdate();
			System.out.println("Insert staus:" + executeUpdate);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception while inserting into table FUSION_BIPLATFORM.BI_LCM_SERVICES_EXEC_INFO", e);
		}finally{
			closeResources(connection, psmt);
		}
	}
	
	public void updateWebcatUsernameSyncupRow(Date updatedTime){
		
		Connection connection = null;
		PreparedStatement psmt = null;
		
		SimpleDateFormat ldapDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		String dateString = ldapDateFormat.format(updatedTime);
		
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ hostname +":"+ port +"/"+ serviceName, props);
			psmt = connection.prepareStatement(updateRow);
			psmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			psmt.setString(2, dateString);
			psmt.setString(3, WEBCAT_USERNAME_SYNCUP);
			
			int executeUpdate = psmt.executeUpdate();
			System.out.println("Update staus:" + executeUpdate);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception inserting into table",e);
		}finally{
			closeResources(connection, psmt);
		}
	}
	
	public void deleteTable(){
		
		Connection connection = null;
		Statement smt = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ hostname +":"+ port +"/"+ serviceName, props);
			smt = connection.createStatement();
			smt.execute("DROP TABLE BI_LCM_SERVICES_EXEC_INFO");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception deleting table",e);
		}finally{
			closeResources(connection, smt);
		}
	}
	
	private void closeResources(Connection connection, Statement smt) {
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Exception while closing connection",e);
			}
		}
		
		if(smt != null){
			try {
				smt.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Exception while closing statement",e);
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		//BILCMServicesDAO dao = new BILCMServicesDAO(args[0], args[1], args[2], args[3], args[4]);
		BILCMServicesDAO dao = new BILCMServicesDAO("slc03vla.us.oracle.com","1025","DB1025","sys as SYSDBA","Welcome1");
		dao.createBILcmServicesTable();
		dao.insertIntoBILcmServicesTable();
		dao.updateWebcatUsernameSyncupRow(new Date());
	}
}

