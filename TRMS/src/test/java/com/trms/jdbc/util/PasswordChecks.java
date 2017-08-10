package com.trms.jdbc.util;



import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
public class PasswordChecks {
	Logger logTest = Logger.getRootLogger();
	
	@Test
	public void TestEncryptionScheme() throws Exception
	{
		String salty = Passwords.getSaltedHash("badPassword");
		assertTrue("Test check function", Passwords.check("badPassword", salty));
	}

}
