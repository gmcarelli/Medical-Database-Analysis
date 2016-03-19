package br.com.a1402072.mia.testjunitneo4j;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.com.a1402072.mia.neoj4connection.Neo4JConnection;



public class Neo4JConnectionTest {


	@Test
	public void connectionTest() throws SQLException {		
		assertTrue(Neo4JConnection.getConnection() != null);
	}

}
