package br.edu.ifsp.connection.neo4j;

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

				int result = 0;
				
				if (resultSet.next()) {
					
					result = resultSet.getInt(1);
					
				}
				
				executeInsert = result > 0;

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

				int result = 0;
				
				if (resultSet.next()) {
					
					result = resultSet.getInt(1);
					
				}
				
				executeUpdate = result > 0;				

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

			String query = "MATCH (n:" + tableName + "{" + column + " : " + objectId + "}) DETACH DELETE n RETURN 1";

			try {

				PreparedStatement preparedStatement = ((Connection) this.connection).prepareStatement(query);

				ResultSet resultSet = preparedStatement.executeQuery();

				int result = 0;
				
				if (resultSet.next()) {
					
					result = resultSet.getInt(1);
					
				}
				
				executeDelete = result > 0;

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			}

		}

		return executeDelete;

	}

	@Override
	public Object executeSearch(String tableName, String column, int objectId) {

		String query = "MATCH (n:" + tableName + "{" + column + " : " + objectId
				+ "}) RETURN n.imageId, n.imageName, n.imageBytes";
		
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
	public int getLastInsertedId(String tableName, String pkColumn) {
		// TODO Auto-generated method stub
		return 0;
	}

}