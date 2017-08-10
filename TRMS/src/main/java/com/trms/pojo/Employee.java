package com.trms.pojo;

import com.trms.jdbc.util.Passwords;

public class Employee {
	private int employeeID;
	private String userName;
	private String password;
	private int dsId;
	private int department;
	
	
	public Employee(int employeeID, String userName, String password, int dsId, int department, boolean plainTextPassword) throws Exception {
		this.employeeID = employeeID;
		this.userName = userName;
		if(plainTextPassword)
		{
		this.password = Passwords.getSaltedHash(password);
		}
		else if(!plainTextPassword)
		{
			this.password = password;
		}
		this.dsId = dsId;
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", userName=" + userName + ", password=" + password + ", dsId="
				+ dsId + ", department=" + department + "]";
	}
	
}
