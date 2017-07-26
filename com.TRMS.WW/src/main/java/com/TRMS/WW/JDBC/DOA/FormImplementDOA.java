package com.TRMS.WW.JDBC.DOA;

import java.sql.Connection;
import java.sql.SQLException;

import com.TRMS.WW.JDBC.ConnectionFactory.ConnectionFactory;

public class FormImplementDOA {
	private static FormImplementDOA formDOA = null;
	private static Boolean build = true;
	Connection conn = null;
	private FormImplementDOA(){
		
	}
	
	public static synchronized FormImplementDOA getInstance(){
		if(build){
			formDOA = new FormImplementDOA();
		}
		return formDOA;
	}
	
	public void setup(){
		//log.debug("Attempting to use Connection Factory");
		conn = ConnectionFactory.getInstance().getConnection();
		
	}
	public void end(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
