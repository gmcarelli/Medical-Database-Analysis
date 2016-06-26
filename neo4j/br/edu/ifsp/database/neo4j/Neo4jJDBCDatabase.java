package br.edu.ifsp.database.neo4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import br.edu.ifsp.database.Database;

public class Neo4jJDBCDatabase extends Database {

	public Connection connection;
	
	public Neo4jJDBCDatabase(String databaseName, String username, String password, String host, Integer port) {
		super(databaseName, username, password, host, port);
	}

	public Neo4jJDBCDatabase(String propertiesPath) throws Exception {
		super(propertiesPath);
	}

	public Neo4jJDBCDatabase(String databaseName, String username, String password, String host, int port) {
		super(databaseName, username, password, host, port);
	}

	public Neo4jJDBCDatabase(String databaseName, String username, String password) {
		super(databaseName, username, password, "localhost", 7474);
	}
	
	public Neo4jJDBCDatabase(Properties properties) {
		super(properties);
	}

	@Override
	public boolean connect() throws Exception {

		Class.forName("org.neo4j.jdbc.Driver");

		this.connection = 
			DriverManager.getConnection(
				"jdbc:neo4j://" + super.host + ":" + super.port + "/",
					super.username, super.password);

		return !this.connection.isClosed();
	}

	@Override
	public void disconnect() throws Exception {
		this.connection.close();
	}

	@Override
	public boolean insert(String tableName, Map<String, Object> values) throws Exception {

		boolean executeInsert = false;

		if (this.connection != null) {

			String query = createInsertQuery(tableName, values);

			int i = 0;

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			for (Object value : values.values()) {

				if (value instanceof String)
					preparedStatement.setString(++i, (String) value);
				else if (value instanceof Integer)
					preparedStatement.setInt(++i, (Integer) value);
				else if (value instanceof byte[])
					preparedStatement.setString(++i, Base64.encodeBase64String((byte[]) value));
			}

			ResultSet resultSet = preparedStatement.executeQuery();

			int result = 0;

			if (resultSet.next()) {

				result = resultSet.getInt(1);

			}

			executeInsert = result > 0;

			preparedStatement.close();
		}

		return executeInsert;
	}

	@Override
	public boolean update(String tableName, Map<String, Object> values) throws Exception {

		boolean result = false;

		if (this.connection != null) {

			String query = createUpdateQuery(tableName, values);

			int i = 0;

			PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

			for (Object value : values.values()) {

				if (value instanceof String)
					preparedStatement.setString(++i, (String) value);
				else if (value instanceof Integer)
					preparedStatement.setInt(++i, (int) value);
				else if (value instanceof byte[])
					preparedStatement.setString(++i, Base64.encodeBase64String((byte[]) value));
			}

			ResultSet resultSet = preparedStatement.executeQuery();

			int affectedRows = 0;

			if (resultSet.next())
				affectedRows = resultSet.getInt(1);

			result = affectedRows > 0;

			preparedStatement.close();

			result = true;
		}

		return result;
	}

	@Override
	public boolean delete(String tableName, String column, Integer id) throws Exception {

		boolean result = false;

		if (this.connection != null) {

			String query = "MATCH (n:" + tableName + "{" + column + " : " + id + "}) DETACH DELETE n RETURN 1";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			int affectedRows = 0;

			if (resultSet.next())
				affectedRows = resultSet.getInt(1);

			result = affectedRows > 0;
		}

		return result;
	}

	@Override
	public ResultSet searchById(String label, String parameter, Integer id) throws Exception {

		String query = "MATCH (n:" + label + "{" + parameter + " : " + id
				+ "}) RETURN n.imageId, n.imageName, n.imageBytes";

		ResultSet resultSet = null;

		if (this.connection != null) {

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
		}

		return resultSet;

	}

	@Override
	public ResultSet listAll(String label) throws Exception {

		ResultSet result = null;
		
		String query = "MATCH (n:" + label + ") RETURN n.imageId, n.imageName, n.imageBytes";

		if (this.connection != null) {

			PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

			preparedStatement.setString(1, label);

			result = preparedStatement.executeQuery();
			
		}

		return result;
	}

	private String createUpdateQuery(String tableName, Map<String, Object> values) {

		String query = "MATCH (n:" + tableName + "{ ";

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

	private String createInsertQuery(String tableName, Map<String, Object> values) {

		String query = "CREATE (n:" + tableName + " {";

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