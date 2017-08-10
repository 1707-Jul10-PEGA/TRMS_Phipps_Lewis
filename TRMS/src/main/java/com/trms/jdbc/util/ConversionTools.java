package com.trms.jdbc.util;

import java.util.ArrayList;

public class ConversionTools {
	public static final String[] GRADE_NAMES = {"No Such Grade Format", "University Course", "Seminar", "Certification Preperation Class", "Certification", "Technical Training", "Other" };
	public static final String[] STATUS_NAMES = {"Request Denied", "No approvals", "Direct Supervisor Approved", "Head of Department Approved", "BenCo Approved", "Grade Approved"};
	public static final String[] EMPLOYEE_LEVELS = {"Employee", "Head of Department", "BenCo"};
//	-1, 'Request Canceled');

	public static String GradeToName(int grade) {
		if(grade >= GRADE_NAMES.length)
		{
			return GRADE_NAMES[0];
		}
		else if(grade <= -1)
		{
			return GRADE_NAMES[0];
		}
		else
		return GRADE_NAMES[grade];
		}
	public static int NameToGrade(String grade) {
		for(int i = 0; i < GRADE_NAMES.length; i++)
		{
			if(grade.equals(GRADE_NAMES[i]))
			{
				return i;
			}
		}
		return 0;
	}
		
	
	public static String StatusToName(int status){
		//Adjust for arrayoffset.
		if(status >= STATUS_NAMES.length || status < -1)
		{
			return "No Such Approval State" + status;
		}
		else if(status == -1)
		{
			return "Request Canceled";
		}
		else
		return STATUS_NAMES[status] + status;
		
	}
	
}

/**
 * switch (myType) {
		case "University Course":
			myGrade = 1;
			break;
		case "Seminar":
			myGrade = 2;
			break;
		case "Certification Preparation Class":
			myGrade = 3;
			break;
		case "Certification":
			myGrade = 4;
			break;
		case "Technical Training":
			myGrade = 5;
			break;
		case "Other": myGrade = 6;
		break;
		default:
			throw new IllegalArgumentException("You have selected an invalid course type");
			**/
