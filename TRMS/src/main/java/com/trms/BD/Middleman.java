package com.trms.BD;

import org.apache.log4j.Logger;

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
	
	public static boolean verifyEmployee(String userName, String plainPass){
		
		
		return true;
	}

}
