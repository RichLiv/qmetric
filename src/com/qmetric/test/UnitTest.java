package com.qmetric.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTest {

	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(UnitTest.class);
	}


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() { 
		assertTrue(true);
		
		// now the basic scaffolding is in place, next thing is to add the tests before implementing the 
		// classes in full
	}

}