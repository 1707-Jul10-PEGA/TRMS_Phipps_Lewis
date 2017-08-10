package com.trms.driver;

import java.sql.SQLException;

import com.trms.jdbc.doa.EmployeeImplementDOA;
import com.trms.jdbc.util.Passwords;

public class Driver {
	public static void main(String[] args) throws Exception {
		EmployeeImplementDOA doa = new EmployeeImplementDOA();
		
		int joe = doa.getEmployeeIDOnLoginInfo("DH", "pass");
		System.out.println(joe);
		String[] myPasses = {"pass"};
		
		
		
		System.out.println(Passwords.getSaltedHash("pass"));
		
	}
}
