package br.edu.ifsp.postgresql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class PostgreConnection extends AConnection {

	/**
	 * Método que cria uma conexão com o banco de dados
	 * 
	 * @return uma conexão com o banco de dados
	 * @throws java.sql.SQLException
	 */
	public Connection connect() throws SQLException {

		try {

			Class.forName("org.postgresql.Driver");

			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MedicalDatabaseAnalysis",
					"postgres", "1qaz2wsx");

		} catch (ClassNotFoundException ex) {

			throw new SQLException(ex.getMessage());

		}

		return this.connection;
	}
	
	
	public boolean executeInsert(String tableName, Map<String, Object> values) {		

		String query = "INSERT INTO " + tableName + " (";
		
		int i = 0;
		
		for (String key : values.keySet()) {
			query += key + ": ?";
			
			if (++i < values.size()) {
				query += ", ";
			}
			
		}
		
		query += ") VALUES (";
		
		for (i = 0; i < values.size(); i++) {
			query += "?";
			
			if (++i < values.size()) {
				query += ", ";
			}
			
		}
		
		query += ")";
		
		
//		this.preparedStatement = this.postgreConnection.connect().prepareStatement(this.query);
		i = 0;
		
		for (Object value : values.values()) {
			
			if (value instanceof String)
				this.preparedStatement.setString(++i, value);
			else if (value instanceof Integer)
				this.preparedStatement.setInt(++i, value);
			else if (value instanceof byte[])
				this.preparedStatement.setBytes(++i, value);
		}
		
		
		
	}

	public boolean executeUpdate(PreparedStatement preparedStatement) throws SQLException {

		if (!this.connection.isClosed() && this.connection != null) {

			return preparedStatement.executeUpdate() > 0;

		}

		return false;
	}
}
