package br.edu.ifsp.connection;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.com.a1402072.mia.neoj4connection.Neo4JConnection;



public class Neo4JTest {


	@Test
	public void connectionTest() throws SQLException {		
		Neo4JConnection neo4jConnection = new Neo4JConnection();
		
		assertTrue(neo4jConnection.connect() != null);

	}

}
