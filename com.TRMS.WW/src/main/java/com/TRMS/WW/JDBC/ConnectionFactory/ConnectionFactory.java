package com.TRMS.WW.JDBC.ConnectionFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	private static Logger log = Logger.getRootLogger();
	private ConnectionFactory(){
		
	}
	
	public static synchronized ConnectionFactory getInstance(){
		if(build){
			cf = new ConnectionFactory();
		}
		return cf;
	}
	
	public Connection getConnection(){
		//log.debug("Starting connection");
		Connection conn = null;
		Properties prop = new Properties();
		//log.debug("Attempting to read Properties file.");
		System.out.println("hello");
		try{
			
		
		prop.load(new FileReader("./datasource.properties"));
	
																				
	} catch(IOException e1){
		e1.printStackTrace();

		}
	
	try{
		conn = DriverManager.getConnection(
				prop.getProperty("url"),
				prop.getProperty("username"), 
				prop.getProperty("password"));
	//	log.debug("Read URL and login");
	} catch(SQLException e){

		e.printStackTrace();
	}
	return conn;
	}
	
	
	
}
}
