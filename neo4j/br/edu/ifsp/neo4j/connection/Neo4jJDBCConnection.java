package br.edu.ifsp.neo4j.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mongodb.Mongo;

import br.edu.ifsp.connection.AConnection;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean executeUpdate(String tableName, Map<String, Object> values) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean executeDelete(String tableName, String col, int objectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object executeSearch(String tableName, String col, int objectId) {
		// TODO Auto-generated method stub
		return null;
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