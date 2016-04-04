package br.edu.ifsp.connection;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.MongodbConnection;

public class PostgreTest {


	@Test
	public void connectionTest() throws SQLException {
		MongodbConnection postgreConnection = new MongodbConnection();
		
		assertTrue(postgreConnection.connect() != null);
	}	
}