package br.edu.ifsp.connection;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

public class PostgreTest {


	@Test
	public void connectionTest() throws SQLException {
		PostgreConnection postgreConnection = new PostgreConnection();
		
		assertTrue(postgreConnection.connect() != null);
	}	
	
}