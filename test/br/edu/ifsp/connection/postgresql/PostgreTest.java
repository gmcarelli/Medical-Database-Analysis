package br.edu.ifsp.connection.postgresql;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.postgresql.PostgreJDBCConnection;

public class PostgreTest {


	@Test
	public void connectionTest() throws SQLException {
		PostgreJDBCConnection postgreConnection = new PostgreJDBCConnection();
		
		assertTrue(postgreConnection.connect() != null);
	}	
	
}