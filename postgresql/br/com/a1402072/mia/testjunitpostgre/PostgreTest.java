package br.com.a1402072.mia.testjunitpostgre;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.com.a1402072.mia.postgreconnection.PostgreConnection;

public class PostgreTest {


	@Test
	public void connectionTest() throws SQLException {
		PostgreConnection postgreConnection = new PostgreConnection();
		
		assertTrue(postgreConnection.getConnection() != null);
	}	
}