package com.trms.pojo;

import com.trms.jdbc.util.ConversionTools;

public class Form {
	private String date;
	private String gradeFormat;
	private String status;
	private String firstName;
	private String lastName;
	
	public Form(){

		this.date = "Failed to generate proper form";
		this.gradeFormat = ConversionTools.GradeToName(-1);
		this.status = ConversionTools.StatusToName(-1);
		this.firstName = "Fake";
		this.lastName = "Failed";
	}
	public Form(String firstName, String lastName, String date, int Status, int Grade){
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.status = ConversionTools.StatusToName(-1);
		this.gradeFormat = ConversionTools.GradeToName(Grade);
	
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = ConversionTools.GradeToName(gradeFormat);
	}
	public void setGradeFormat(String gradeFormat){
		this.gradeFormat = gradeFormat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = ConversionTools.StatusToName(status);
	}
	public void setStatus(String status) {
		this.status = status;
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
