package com.trms.jdbc.doa;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.trms.jdbc.util.ConnectionFactory;

import junit.framework.Assert;

public class FormDOATest {
	Logger logTest = Logger.getRootLogger();
	@Test
	public void testConnection() throws SQLException{
		Connection conn = ConnectionFactory.getInstance().getConnection();
		logTest.debug(conn.isClosed());
		assertFalse(conn.isClosed());
	}
	
	
	
	//Test the form cost set methods (2 methods)
	@Test
	public void testGetCostOnFormIDMethods() throws SQLException{
		
	//	assertTrue("Ensure output is 0 on negative input", getFullCostOnFormID(1) );
		
	}
	
	
	//Test the form cost get methods (2 methods)
	
//	public double getReimbursementCostOnFormID(Integer formID)throws SQLException;
//	public double getFullCostOnFormID(Integer formID)throws SQLException;
//	public double setFullCostOnFormID(Integer formID, Double newCost) throws SQLException;
//	public double setReimbursementCostOnFormID(Integer formID, Double newCost) throws SQLException;
}
