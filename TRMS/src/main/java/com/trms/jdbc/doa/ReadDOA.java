package com.trms.jdbc.doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReadDOA {
		//This function should take in an employee ID and return an integer based on the position of said employee.
		public int CheckEmployeePosition(int empID) throws SQLException;
		//Takes in login information and returns associated EmployeeID number
		public int getEmployeeIDOnLoginInfo(String username, String hashedPass) throws SQLException;
}
