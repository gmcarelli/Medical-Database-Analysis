package br.edu.ifsp.neo4j.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import br.edu.ifsp.connection.AConnection;
import br.edu.ifsp.helper.QueryHelper;

public class Neo4jJDBCConnection extends AConnection {

	@Override
	public Object connect() {

		try {

			Class.forName("org.neo4j.jdbc.Driver");

			this.connection = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "1qaz2wsx");

		} catch (ClassNotFoundException | SQLException ex) {

			System.out.println(ex.getMessage());

			this.connection = null;

		}

		return this.connection;
	}

	public boolean executeUpdate(PreparedStatement preparedStatement) {

		int result = 0;

		try {
			if (!((Connection) this.connection).isClosed() && this.connection != null) {

				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {

					result = resultSet.getInt(1);

				}

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

			result = 0;

		}

		return result > 0;
	}

	@Override
	public boolean disconnect() {

		boolean disconnect = false;

		if (this.connection != null) {

			try {
				((Connection) this.connection).close();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

				disconnect = false;

			} finally {

				disconnect = true;

			}

		}

		return disconnect;
	}

	@Override
	public boolean executeInsert(String tableName, Map<String, Object> values) {

		boolean executeInsert = false;

		if (this.connection != null) {

			try {

				this.queryHelper = new QueryHelper();

				String query = queryHelper.createInsertQueryNeo4j(tableName, values);

				int i = 1;

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				preparedStatement.setString(i, tableName);

				for (Object value : values.values()) {

					if (value instanceof String)
						preparedStatement.setString(++i, (String) value);
					else if (value instanceof Integer)
						preparedStatement.setInt(++i, (int) value);
					else if (value instanceof byte[])
						preparedStatement.setString(++i, Base64.encodeBase64String((byte[]) value));
				}

				System.out.println(preparedStatement.toString());

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

			String query = queryHelper.createUpdateQueryNeo4j(tableName, values);

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
						preparedStatement.setString(++i, Base64.encodeBase64String((byte[]) value));

				}

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
	public boolean executeDelete(String tableName, String col, int objectId) {

		boolean executeDelete = false;

		if (this.connection != null) {

			String query = "MATCH (n:" + tableName + "{" + col + " : " + objectId + "}) DETACH DELETE n RETURN 1";

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				System.out.println(preparedStatement.toString());

				executeDelete = preparedStatement.executeUpdate() > 0;

				preparedStatement.close();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return executeDelete;

	}

	@Override
	public Object executeSearch(String tableName, String col, int objectId) {

		String query = "MATCH (n:" + tableName + "{" + col + " : " + objectId
				+ "}) RETURN n.imageId, n.imageName, n.imageBytes";

		ResultSet resultSet = null;

		if (this.connection != null) {

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				resultSet = preparedStatement.executeQuery();
				
				preparedStatement.close();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return resultSet;
	}

	@Override
	public Object executeListData(String tableName) {
		
		String query = "MATCH (n:" + tableName + ") RETURN n.imageId, n.imageName, n.imageBytes";

		ResultSet resultSet = null;

		if (this.connection != null) {

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				preparedStatement.setString(1, tableName);

				resultSet = preparedStatement.executeQuery();

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}	

		}

		return resultSet;
	}

	@Override
	public int getLastInsertedId(String tableName, String col) {
		// TODO Auto-generated method stub
		return 0;
	}

}