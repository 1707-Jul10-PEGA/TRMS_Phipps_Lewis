package com.trms.jdbc.doa;

import java.sql.SQLException;

public interface EmployeeDOA {
	//cancel
	//check balance
	double getReimbursementBalance(int employeeID) throws SQLException, Exception;
	void cancelRequest(int requestID) throws SQLException, Exception;
}
