package com.trms.BD;

public class Employee {
	private int employeeID;
	private String userName;
	private String password;
	private int dsId;
	private int department;
	
	
	public Employee(int employeeID, String userName, String password, int dsId, int department) {
		super();
		this.employeeID = employeeID;
		this.userName = userName;
		this.password = password;
		this.dsId = dsId;
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", userName=" + userName + ", password=" + password + ", dsId="
				+ dsId + ", department=" + department + "]";
	}
	
	

}
