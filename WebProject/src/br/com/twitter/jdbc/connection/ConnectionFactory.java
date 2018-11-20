package br.com.twitter.jdbc.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	static String PROPERTY_FILE = System.getProperty("catalina.base")+"\\wtpwebapps\\TwitterAnalyzer\\WEB-INF\\lib\\config.properties";
	static String HOSTNAME;
	static String DB_NAME;
	static String DB_USER;
	static String DB_PASS;
	static String DB_SSL;
	
	public Connection getConnection() {
		System.out.println(">>>>>>>>>>>>>>>>." + PROPERTY_FILE);
		
		readProperties(PROPERTY_FILE);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
	    	 //autoReconnect=true&useSSL=false
	         return DriverManager.getConnection(
	        		 "jdbc:mysql://"+HOSTNAME+"/"+DB_NAME+"?useSSL="+DB_SSL, DB_USER , DB_PASS);
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	public static void readProperties(String file) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(file);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			HOSTNAME = prop.getProperty("db.hostname");
			DB_NAME = prop.getProperty("db.name");
			DB_USER = prop.getProperty("db.user");
			DB_PASS = prop.getProperty("db.pass");
			DB_SSL = prop.getProperty("db.useSSL");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
