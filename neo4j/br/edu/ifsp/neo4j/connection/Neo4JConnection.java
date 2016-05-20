package br.edu.ifsp.neo4j.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifsp.connection.AConnection;

public class Neo4JConnection extends AConnection {	

	/**
	 * Método que cria uma conexão com o banco de dados
	 * @return uma conexão com o banco de dados
	 * @throws java.sql.SQLException
	 */
	public Connection connect() throws SQLException {

		try {

			Class.forName("org.neo4j.jdbc.Driver");

			this.connection = DriverManager.getConnection("jdbc:neo4j://localhost:7474/", "neo4j", "1qaz2wsx");

		} catch (ClassNotFoundException ex) {

			throw new SQLException(ex.getMessage());

		}

		return this.connection;
	}	
	
public boolean executeUpdate(PreparedStatement preparedStatement) throws SQLException {		
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			int result = 0;
			
			if (resultSet.next()) {
				
				result = resultSet.getInt(1);
				
			}
			
			return result > 0;
			
		}
		
		return false;
	}
}