package com.trms.jdbc.doa;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.trms.jdbc.util.ConnectionFactory;

public class FormDOATest {
	Logger logTest = Logger.getRootLogger();
	@Test
	public void testConnection() throws SQLException{
		Connection conn = ConnectionFactory.getInstance().getConnection();
		logTest.debug(conn.isClosed());
		
	}
	
	
	//Test the form cost set methods (2 methods)
	
	
	//Test the form cost get methods (2 methods)
}
