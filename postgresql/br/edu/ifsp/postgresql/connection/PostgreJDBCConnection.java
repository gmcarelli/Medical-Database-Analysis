package br.edu.ifsp.postgresql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import br.edu.ifsp.connection.AConnection;
import br.edu.ifsp.helper.QueryHelper;

public class PostgreJDBCConnection extends AConnection {

	@Override
	public Object connect() {

		try {

			Class.forName("org.postgresql.Driver");

			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MedicalDatabaseAnalysis",
					"postgres", "1qaz2wsx");

		} catch (ClassNotFoundException | SQLException ex) {

			System.out.println(ex.getMessage());

			this.connection = null;

		}

		return this.connection;

	}

	@Override
	public boolean disconnect() {

		boolean result = false;

		try {

			((Connection) this.connection).close();

		} catch (Exception e) {

			System.out.println(e.getMessage());

			result = false;

		} finally {

			result = true;

		}

		return result;

	}

	@Override
	public boolean executeInsert(String tableName, Map<String, Object> values) {

		boolean executeInsert = false;

		if (this.connection != null) {

			try {

				this.queryHelper = new QueryHelper();

				String query = queryHelper.createInsertQueryPostgre(tableName, values);

				int i = 0;

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				for (Object value : values.values()) {

					if (value instanceof String)
						preparedStatement.setString(++i, (String) value);
					else if (value instanceof Integer)
						preparedStatement.setInt(++i, (int) value);
					else if (value instanceof byte[])
						preparedStatement.setBytes(++i, (byte[]) value);

				}

				executeInsert = preparedStatement.executeUpdate() > 0;

				preparedStatement.close();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

				executeInsert = false;

			}
		}

		return executeInsert;

	}

	@Override
	public boolean executeUpdate(String tableName, Map<String, Object> values) {

		boolean executeUpdate = false;

		if (this.connection != null) {

			this.queryHelper = new QueryHelper();

			String query = queryHelper.createUpdateQueryPostgre(tableName, values);

			try {				

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);
				
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
				
				System.out.println(preparedStatement.toString());

				executeUpdate = preparedStatement.executeUpdate() > 0;

				preparedStatement.close();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return executeUpdate;

	}

	@Override
	public boolean executeDelete(String tableName, String column, int objectId) {

		boolean executeDelete = false;

		if (this.connection != null) {

			String query = "DELETE FROM " + tableName + " WHERE " + column + " = " + objectId;

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				executeDelete = preparedStatement.executeUpdate() > 0;

				preparedStatement.close();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return executeDelete;

	}

	@Override
	public Object executeSearch(String tableName, String column, int objectId) {

		String query = "SELECT * FROM " + tableName + " WHERE " + column + " = " + objectId;

		ResultSet resultSet = null;

		if (this.connection != null) {

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				resultSet = preparedStatement.executeQuery();				

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return resultSet;

	}

	@Override
	public Object executeListData(String tableName) {

		String query = "SELECT * FROM " + tableName;

		ResultSet resultSet = null;

		if (this.connection != null) {

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				resultSet = preparedStatement.executeQuery();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return resultSet;
	}

	@Override
	public int getLastInsertedId(String tableName, String pkColumn) {

		String query = "SELECT " + pkColumn + " FROM " + tableName + " ORDER BY " + pkColumn + " DESC LIMIT 1";

		int lastInsertedId = 0;

		if (this.connection != null) {

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {

					lastInsertedId = resultSet.getInt(pkColumn);
				}

				preparedStatement.close();

				resultSet.close();

			} catch (SQLException e) {
				
				System.out.println(e.getMessage());				
				
			}

		}

		return lastInsertedId;

	}

}
