package tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import services.HelperService;

public class HelperServiceTest {

	HelperService hst = new HelperService();
	
	@BeforeClass
	public static void startTest() {
		System.out.println("[TEST] Checking method logic..");
	}
	
	@Test
	public void returnHelperValue_true() {
		assertEquals(true, hst.isEmpty("Some data.."));
	}
	
	@Test
	public void returnHelperValue_false() {
		assertNotEquals(true, hst.isEmpty(""));
	}
	
	@AfterClass
	public static void stopTest() {
		System.out.println("[TEST] Finished.");
	}
}
