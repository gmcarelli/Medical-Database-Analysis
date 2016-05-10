package br.edu.ifsp.neo4j.queryTest;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class Neo4JDeleteTest {

	@Test
	public void DeleteDBtest() throws SQLException {
		
		Neo4JConnection neo4jConnection = new Neo4JConnection();
		
		PreparedStatement preparedStatement = neo4jConnection.connect().prepareStatement("MATCH (n) DETACH DELETE n");
		
		ResultSet resultSet = neo4jConnection.executeQuery(preparedStatement);
		
		assertTrue(resultSet != null);

	}

}
