package br.edu.ifsp.connection.neo4j;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.neo4j.Neo4jJDBCConnection;



public class Neo4JTest {


	@Test
	public void connectionTest() throws SQLException {		
		Neo4jJDBCConnection neo4jConnection = new Neo4jJDBCConnection();
		
		assertTrue(neo4jConnection.connect() != null);

	}

}
