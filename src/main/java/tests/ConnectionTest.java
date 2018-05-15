package tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import dao.DAOManager;

public class ConnectionTest {

	DAOManager dao;
	Connection connection;

	@BeforeClass
	public static void startTest() {
		System.out.println("[TEST] Checking connection...");
	}
	
	@Before
	public void setUp() {
	    dao = new DAOManager();
	    try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_service", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIfConnectionNotNull() {
	    assertNotNull(connection);
	}

	@Test
	public void testIfDAONotNull() {
	    assertNotNull(dao);
	}
	
	@AfterClass
	public static void stopTest() {
		System.out.println("[TEST] Stopping connection...");
	}
}
