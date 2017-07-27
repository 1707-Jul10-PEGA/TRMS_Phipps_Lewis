package com.trms.jdbc.doa;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trms.jdbc.util.ConnectionFactory;


//Do I need to test the GetSetFullCost and GetReimbursementCost?
public class FormDOATest {
	Logger logTest = Logger.getRootLogger();
	static Connection conn = null;
	static FormImplementDOA formDOA = new FormImplementDOA();
	
	@BeforeClass
	public static void connSetup(){
		conn = ConnectionFactory.getInstance().getConnection();
	}
	@Test
	public void testConnection() throws SQLException{
		Connection conn = ConnectionFactory.getInstance().getConnection();
		logTest.debug(conn.isClosed());
		assertFalse(conn.isClosed());
	}
	
	
	@Test
	public void testSetFullCostOnFormIDBadInputMethods() throws SQLException{

	assertTrue("Ensure output is 0 on negative input", formDOA.setFullCostOnFormID(1, -200.00) == 0.0);
	assertTrue("Ensure output is 0 on 0 input", formDOA.setFullCostOnFormID(1, 00.00) == 0.0);
	}
	@Test
	public void testSetCostOnFormIDBadInputMethods() throws SQLException{
	assertTrue("Ensure output is 0 on negative input", formDOA.setReimbursementCostOnFormID(1, -200.00) == 0.0);
	assertTrue("Ensure output is 0 on 0 input", formDOA.setReimbursementCostOnFormID(1, 00.00) == 0.0);
	}
	
	@Test
	public void testSetCostOnValidInputs() throws SQLException{
		assertTrue("Ensure output matches on full cost valid input", formDOA.setFullCostOnFormID(1, 200.00) == 200.00);
		assertFalse("Ensure output is valid on conversion input", formDOA.setReimbursementCostOnFormID(1, 200.00) == 200.00);
	}



}
