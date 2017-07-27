package com.trms.jdbc.doa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trms.jdbc.util.ConnectionFactory;


public class EmployeeDOATest {
	Logger logTest = Logger.getRootLogger();
	static Connection conn = null;
	//establishing a connection with the database
	//before class has to be a methodgit
	@BeforeClass
	public static void connSetup(){
		conn = ConnectionFactory.getInstance().getConnection();
	}
	
	
	//Fails if there IS a connection
	@Test
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

	@Test
	public void testGetReimbursementBalance(int employeeID) throws SQLException{
		logTest.debug("Testing getReimbursementBalance");
		
	}
}
