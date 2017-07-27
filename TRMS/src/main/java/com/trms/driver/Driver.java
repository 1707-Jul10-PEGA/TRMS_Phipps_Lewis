package com.trms.driver;

import java.sql.SQLException;

import com.trms.jdbc.doa.EmployeeImplementDOA;

public class Driver {
	public static void main(String[] args) throws Exception {
		EmployeeImplementDOA doa = new EmployeeImplementDOA();
		
		System.out.println(doa.getReimbursementBalance(26));
		
	}
}
