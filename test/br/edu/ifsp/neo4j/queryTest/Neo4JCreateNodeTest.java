package br.edu.ifsp.neo4j.queryTest;

import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.edu.ifsp.neo4j.connection.Neo4JConnection;

public class Neo4JCreateNodeTest {

	@Test
	public void ExecuteUpdatetest() throws SQLException {

		Neo4JConnection neo4jConnection = new Neo4JConnection();

		PreparedStatement preparedStatement = neo4jConnection.connect()
				.prepareStatement("CREATE (n:Person {name : 'Gil Carelli', title : 'Developer'})");

		assertTrue(neo4jConnection.executeUpdate(preparedStatement));

	}

	@Test
	public void ExecuteQuerytest() throws SQLException {

		Neo4JConnection neo4jConnection = new Neo4JConnection();

		PreparedStatement preparedStatement = neo4jConnection.connect()
				.prepareStatement("CREATE (n:Person {name : 'Stevie Ray Vaughan', title : 'Yoda'})");

		ResultSet resultSet = neo4jConnection.executeQuery(preparedStatement);

		assertTrue(resultSet != null); // epic fail > .executeQuery retorna um
										// ResultSet que nunca é null! kkkkk

		neo4jConnection.disconnect();

	}

}
