package br.edu.ifsp.neo4j.queryTest;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.connection.AConnection;
import br.edu.ifsp.neo4j.connection.Neo4jJDBCConnection;

public class Neo4JDeleteTest {

	@Test
	public void DeleteDBtest() throws SQLException {
		
		AConnection connection = new Neo4jJDBCConnection();
		
		Connection conn = (Connection) connection.connect();
		
		PreparedStatement preparedStatement = conn.prepareStatement("MATCH (n) DETACH DELETE n");
		
		assertTrue(preparedStatement.executeQuery() != null);

	}

}
