package br.edu.ifsp.connection;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;



public class Neo4JTest {


	@Test
	public void connectionTest() throws SQLException {		
		Neo4jJDBCConnection neo4jConnection = new Neo4jJDBCConnection();
		
		assertTrue(neo4jConnection.connect() != null);

	}

}
