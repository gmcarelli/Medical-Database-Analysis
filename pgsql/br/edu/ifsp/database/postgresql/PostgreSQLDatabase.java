package br.edu.ifsp.database.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import br.edu.ifsp.database.Database;

public class PostgreSQLDatabase extends Database {

	private Connection connection;
	
	public PostgreSQLDatabase(String databaseName, String username, String password, String host, int port) {
		super(databaseName, username, password, host, port);
	}

	public PostgreSQLDatabase(String databaseName, String username, String password) {
		super(databaseName, username, password, "localhost", 5432);
	}
	
	public PostgreSQLDatabase(Properties properties) {
		super(properties);
	}
	
	public PostgreSQLDatabase(String propertiesPath) throws Exception {
		super(propertiesPath);
	}

	@Override
	public boolean connect() throws Exception {

		Class.forName("org.postgresql.Driver");

		this.connection = DriverManager.getConnection(
			"jdbc:postgresql://" + super.host + ":" + super.port + "/" + 
			super.databaseName, super.username, super.password);

		return !this.connection.isClosed();
	}

	@Override
	public void disconnect() throws Exception {
		
		if (this.connection != null)
			this.connection.close();
	}

	@Override
	public boolean insert(String table, Map<String, Object> values) throws Exception {

		boolean result = false;

		if (this.connection != null && !this.connection.isClosed()) {

			String query = createInsertQuery(table, values);

			int i = 0;

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			for (Object value : values.values()) {

				if (value instanceof String)
					preparedStatement.setString(++i, (String) value);
				else if (value instanceof Integer)
					preparedStatement.setInt(++i, (Integer) value);
				else if (value instanceof byte[])
					preparedStatement.setBytes(++i, (byte[]) value);
			}

			result = preparedStatement.executeUpdate() > 0;

			preparedStatement.close();

			result = true;
		}

		return result;
	}

	@Override
	public boolean update(String tableName, Map<String, Object> values) throws Exception {

		boolean executeUpdate = false;

		if (this.connection != null) {

			String query = createUpdateQuery(tableName, values);

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			Set<String> set = values.keySet();

			String column = set.iterator().next();

			int objectId = (int) values.get(column);

			values.remove(column);

			int i = 0;

			byte[] bytes = (byte[]) values.get("imageBytes");

			System.out.println(bytes.length);

			for (Object value : values.values()) {

				if (value instanceof String)
					preparedStatement.setString(++i, (String) value);
				else if (value instanceof Integer)
					preparedStatement.setInt(++i, (int) value);
				else if (value instanceof byte[])
					preparedStatement.setBytes(++i, (byte[]) value);
			}

			preparedStatement.setInt(++i, objectId);

			executeUpdate = preparedStatement.executeUpdate() > 0;

			preparedStatement.close();

			executeUpdate = true;
		}

		return executeUpdate;
	}

	@Override
	public boolean delete(String table, String column, Integer id) throws Exception {

		boolean executeDelete = false;

		if (this.connection != null && !this.connection.isClosed()) {

			String query = "DELETE FROM " + table + " WHERE " + column + " = " + id;

			PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

			executeDelete = preparedStatement.executeUpdate() > 0;

			preparedStatement.close();
		}

		return executeDelete;
	}

	@Override
	public ResultSet searchById(String table, String column, Integer id) throws SQLException {

		ResultSet result = null;
		
		String query = "SELECT * FROM " + table + " WHERE " + column + " = " + id;

		if (this.connection != null) {

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			result = preparedStatement.executeQuery();
		}

		return result;
	}

	@Override
	public ResultSet listAll(String table) throws Exception {

		ResultSet result = null;
		
		String query = "SELECT * FROM " + table;

		if (this.connection != null) {

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			result = preparedStatement.executeQuery();
		}

		return result;
	}

	private String createInsertQuery(String tableName, Map<String, Object> values) {

		String query = "INSERT INTO " + tableName + "(";

		List<String> keys = new ArrayList<String>(values.keySet());
		
		for (int j = 0; j < keys.size(); j++) {

			
			query += keys.get(j);

			if (j + 1 < keys.size())
				query += ", ";
		}

		query += ") VALUES (";

		for (int j = 0; j < values.size(); j++) {

			query += "?";

			if (j + 1 < values.size())
				query += ", ";
		}

		return query + ");";

	}

	private String createUpdateQuery(String tableName, Map<String, Object> values) {

		String query = "UPDATE " + tableName + " SET ";

		int i = 0;

		Set<String> set = values.keySet();

		String column = set.iterator().next();

		Map<String, Object> valuesAux = new HashMap<String, Object>();

		for (String key : values.keySet())
			valuesAux.put(key, values.get(key));

		valuesAux.remove(column);

		for (String key : valuesAux.keySet()) {

			query += key + " = ?";

			if (++i < valuesAux.size())
				query += ", ";
		}

		query += " WHERE " + column + " = ?";

		return query;
	}

	@Override
	public boolean deleteAll(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
