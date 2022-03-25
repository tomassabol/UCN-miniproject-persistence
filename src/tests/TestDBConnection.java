package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DBConnection;

public class TestDBConnection {
	DBConnection connection = null;

	@Before
	public void setUp() {
		connection = DBConnection.getInstance();
	}

	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", connection);
		
		DBConnection.disconnect();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		connection = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", connection);		
	}

	@After
	public void cleanUp() {
		DBConnection.disconnect();
	}

}
