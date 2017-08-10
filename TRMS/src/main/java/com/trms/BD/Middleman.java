package com.trms.BD;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.trms.jdbc.doa.ReadImplementDOA;
import com.trms.jdbc.util.Passwords;

public class Middleman {
	private static Logger log = Logger.getRootLogger();
	
	public static boolean createEmployee(String userName, String plainPass){
		try {
			String newPass = Passwords.getSaltedHash(plainPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("Failed to generate a hash for employee: " + userName);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean createForm(int EmpID, String StartDate, double cost, int gradeFormatID, String description)
	{
		if(StartDate.isEmpty())
		{
			return false;
		}
		log.info("Successfully created a form!");
		return true;
	}
	//Adjustable form creation based on input level.
	public static boolean createForm(int EmpID, String StartDate, double cost, int gradeFormatID, String description, int PriviledgeLevel)
	{
		log.info("Successfully created a form!");
		return true;
	}
	public static int verifyEmployee(String userName, String plainPass) throws SQLException{
		ReadImplementDOA myDOA = new ReadImplementDOA();
		return myDOA.getEmployeeIDOnLoginInfo(userName, plainPass);
	}
	/**
	 * Remains to be implemented, should return an int based on if this imployee is a department Head or Benco etc
	 * @param EmpID
	 * @return undecided how to represent this info
	 */
	public static void checkPriviledgeLevel(int EmpID)
	{	
	}

}
