package com.trms.pojo;

public class Form {
	private String date;
	private int gradeFormat;
	private int status;
	private String firstName;
	private String lastName;
	
	public Form(){

		this.date = "Failed to generate proper form";
		this.gradeFormat = -1;
		this.status = -1;
		this.firstName = "Fake";
		this.lastName = "Failed";
	}
	public Form(String firstName, String lastName, String date, int Status, int Grade){
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.status = Status;
		this.gradeFormat = Grade;
	
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
