package com.trms.jdbc.doa;

import java.sql.Connection;
import java.sql.SQLException;

import com.trms.jdbc.util.ConnectionFactory;

public class FormImplementDOA implements FormDOA {
	private static Logger log = Logger.getRootLogger();

	Connection conn = null;
	public FormImplementDOA(){
		setup();
	}
	
	public void setup(){
		//log.debug("Attempting to use Connection Factory");
		conn = ConnectionFactory.getInstance().getConnection();
		
	}

}
