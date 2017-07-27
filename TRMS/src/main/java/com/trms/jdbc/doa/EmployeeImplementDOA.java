package com.trms.jdbc.doa;

import java.sql.SQLException;

import com.trms.jdbc.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeImplementDOA implements EmployeeDOA {

	/*establishing a connection with the database using the Connection Factory*/
	Connection conn = null;
	private void setup(){
		conn = ConnectionFactory.getInstance().getConnection();
	}
	public EmployeeImplementDOA(){
		setup();
	}
	
	/* Returns the reimbursement value of the employee associated with 'employeeID'. */
	public double getReimbursementBalance(int employeeID) throws SQLException {
		
		//prepare sql statement
		String sql = "select firstname, lastname, employeeid, reimbursement_total from employee where employeeid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, employeeID);
		
		//store result set into a variable of type ResultSet
		ResultSet rs = pstmt.executeQuery();
		
		double balance = 0;
		
		//store the balance into a double
		while(rs.next()){
		
			balance = rs.getDouble(1);
		
		}
		
		//return the balance 
		return balance;
	}
	
	//forgot how our table works for this one. maybe return a boolean of active inactive status??
	public boolean cancelRequest(int requestID) throws SQLException {
		
		//prepare sql statement						??
		String sql = "update form_submissions set status = false where formid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, requestID);
		
		//store result set into a variable of type ResultSet
		ResultSet rs = pstmt.executeQuery();
		
		//store the status into a boolean variable
		boolean status = false;
		
		while(rs.next()){
			status = rs.getBoolean(1);
		}
		
		//return the status
		return status;
	}

}
