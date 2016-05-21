package br.edu.ifsp.postgresql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

		boolean executeInsert;
		
		try {

			this.queryHelper = new QueryHelper();

			String query = queryHelper.setInsertQuery(tableName, values);
			
			int i = 1;

			PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

			preparedStatement.setString(i, tableName);

			for (Object value : values.values()) {

				if (value instanceof String)
					preparedStatement.setString(++i, (String) value);
				else if (value instanceof Integer)
					preparedStatement.setInt(++i, (int) value);
				else if (value instanceof byte[])
					preparedStatement.setBytes(++i, (byte[]) value);

			}

			executeInsert = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

			executeInsert = false;

		}

		return executeInsert;
	}

	@Override
	public boolean executeUpdate(String tableName, Map<String, Object> values) {

		boolean executeUpdate = false;		

		if (this.connection != null) {
			
			this.queryHelper = new QueryHelper();

			String query = queryHelper.setUpdateQuery(tableName, values);

			try {

				int i = 1;

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				preparedStatement.setString(i, tableName);

				for (Object value : values.values()) {

					if (value instanceof String)
						preparedStatement.setString(++i, (String) value);
					else if (value instanceof Integer)
						preparedStatement.setInt(++i, (int) value);
					else if (value instanceof byte[])
						preparedStatement.setBytes(++i, (byte[]) value);

				}

				executeUpdate = preparedStatement.executeUpdate() > 0;

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return executeUpdate;
	}

	@Override
	public boolean executeDelete(String tableName, String col, int objectId) {

		boolean executeDelete = false;

		if (this.connection != null) {
			
			String query = "DELETE FROM ? WHERE ? = ?";

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				preparedStatement.setString(1, tableName);
				preparedStatement.setString(2, col);
				preparedStatement.setInt(3, objectId);

				executeDelete = preparedStatement.executeUpdate() > 0;

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return executeDelete;
	}

	@Override
	public Object executeSearch(String tableName, String col, int objectId) {

		String query = "SELECT * FROM ? WHERE ? = ?";

		ResultSet resultSet = null;

		if (this.connection != null) {

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				preparedStatement.setString(1, tableName);
				preparedStatement.setString(2, col);
				preparedStatement.setInt(3, objectId);

				resultSet = preparedStatement.executeQuery();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

			((AConnection) this.connection).disconnect();

		}

		return resultSet;
	}

	@Override
	public List<Object> executeListData(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLastInsertedId(String tableName, String col, int objectId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
