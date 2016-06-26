package br.edu.ifsp.database.neo4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import br.edu.ifsp.database.Database;

public class Neo4jDatabase extends Database {

	private Session connection;
	
	public Neo4jDatabase(String databaseName, String username, String password, String host, Integer port) {
		super(databaseName, username, password, host, port);
	}
	
	public Neo4jDatabase(String databaseName, String username, String password) {
		super(databaseName, username, password, "localhost", 7474);
	}

	public Neo4jDatabase(Properties properties) {
		super(properties);
	}

	@Override
	public boolean connect() throws Exception {
		
		Driver driver = 
			GraphDatabase.driver(
				"bolt://" + super.host + ":" + super.port, 
				AuthTokens.basic(super.username, super.password) );

		this.connection = driver.session();
		
		return this.connection.isOpen();
	}

	@Override
	public void disconnect() throws Exception {

		if (this.connection != null)
			this.connection.close();
	}

	@Override
	public boolean insert(String tableName, Map<String, Object> values) throws Exception {

		boolean executeInsert = false;

		if (this.connection != null) {

			String query = createInsertQuery(tableName, values);

			StatementResult statementResult = this.connection.run(query, values);

			int result = 0;

			if (statementResult.hasNext())
				result = statementResult.next().get(1).asInt();

			executeInsert = result > 0;
		}

		return executeInsert;
	}

	@Override
	public boolean update(String tableName, Map<String, Object> values) throws Exception {

		boolean executeUpdate = false;

		if (this.connection != null) {

			String query = createUpdateQuery(tableName, values);

			StatementResult statementResult = this.connection.run(query, values);

			int result = 0;

			if (statementResult.hasNext())
				result = statementResult.next().get(1).asInt();

			executeUpdate = result > 0;

			executeUpdate = true;
		}

		return executeUpdate;
	}

	public boolean delete(String label, String parameter, Integer id) throws Exception {

		boolean executeDelete = false;

		if (this.connection != null) {

			String query = "MATCH (n:" + label + "{" + parameter + " : " + id + "}) DETACH DELETE n RETURN 1";

			StatementResult preparedStatement = this.connection.run(query);

			int result = 0;

			if (preparedStatement.hasNext())
				result = preparedStatement.next().get(1).asInt();

			executeDelete = result > 0;
		}

		return executeDelete;
	}

	@Override
	public List<Record> searchById(String label, String parameter, Integer id) throws Exception {

		List<Record> result = new ArrayList<Record>();
		
		String query = "MATCH (n:" + label + "{" + parameter + " : " + id
				+ "}) RETURN n.imageId, n.imageName, n.imageBytes";

		if (this.connection != null) {

			StatementResult statementResult = this.connection.run(query);

			result.addAll(statementResult.list());
		}

		return result;
	}

	public List<Record> listAll(String label) throws Exception {

		List<Record> result = new ArrayList<Record>();
		
		String query = "MATCH (n:" + label + ") RETURN n.imageId, n.imageName, n.imageBytes";

		if (this.connection != null) {

			StatementResult preparedStatement = this.connection.run(query);

			return preparedStatement.list();
		}
		
		return result;
	}

	public String createUpdateQuery(String label, Map<String, Object> values) {

		String query = "MATCH (n:" + label + "{ ";

		int i = 0;

		for (String key : values.keySet()) {

			if (i++ == 0)
				query += key + " : ? }) SET ";

			else {

				query += "n." + key + " = ?";

				if (++i < values.size())
					query += ", ";
			}
		}

		query += " RETURN 1";

		return query;

	}

	public String createInsertQuery(String label, Map<String, Object> values) {

		String query = "CREATE (n:" + label + " {";

		int i = 0;

		for (String key : values.keySet()) {

			query += key + " : ?";

			if (++i < values.size())
				query += ", ";
		}

		query += " }) RETURN 1";

		return query;
	}

	@Override
	public boolean deleteAll(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
