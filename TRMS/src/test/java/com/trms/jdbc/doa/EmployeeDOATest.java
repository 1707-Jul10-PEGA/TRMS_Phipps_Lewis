package com.trms.jdbc.doa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.Exception;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trms.jdbc.util.ConnectionFactory;


public class EmployeeDOATest {
	Logger logTest = Logger.getRootLogger();
	static Connection conn = null;
	static EmployeeImplementDOA doa = null;
	//establishing a connection with the database
	//before class has to be a method
	@BeforeClass
	public static void setup(){
		conn = ConnectionFactory.getInstance().getConnection();
		doa = new EmployeeImplementDOA();
		
	}
		
	//Fails if there IS a connection
	@Test (expected = AssertionError.class)
	public void testConnectionSuccess() throws SQLException{
		logTest.debug("Testing the Connection Unit");
		logTest.debug(conn.isClosed());
		assertTrue(conn.isClosed());
		
	}
	//Fails if there is NO connection
	@Test
	public void testConnectionFail() throws SQLException{
		logTest.debug("Testing the Connection Unit");
		logTest.debug(conn.isClosed());
		assertFalse(conn.isClosed());
	}


	@Test (expected = Exception.class)
	public void getReimbursementNegative() throws Exception{
		logTest.debug("Testing getReimbursementBalance");
		
		doa.getReimbursementBalance(-1);
	}	
	@Test
	public void testGetReimbursementBalanceSucces() throws Exception{
		logTest.debug("Testing getReimbursementBalance");
		doa.getReimbursementBalance(1);
	}
	
	@Test (expected = Exception.class)
	public void testCancelRequestNegative() throws Exception{
		logTest.debug("Testing cancelRequest");
		doa.cancelRequest(-1);
	}
	@Test
	public void testCancelRequest() throws Exception{
		logTest.debug("Testing cancelRequest");
		doa.cancelRequest(1);
	}	
	
	
	@AfterClass
	public static void closout() throws SQLException{
		conn.close();
	}
}
