package com.trms.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyClassTest {

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrown(){
		MyClass tester = new MyClass();
		tester.multiply(1000, 5);
	}
	@Test
 	public void testMultiply() {
		MyClass tester = new MyClass();
		
	}

}
