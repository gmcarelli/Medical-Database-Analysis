package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Neo4JConnection {

	private Connection connection;
	
	public Connection connect() throws SQLException {
		
		try {
			
			Class.forName("org.neo4j.jdbc.Driver");
			
			connection =  DriverManager.getConnection("jdbc:neo4j://localhost:7474/graph.db", "neo4j", "1qaz2wsx");
			
			return connection;
			
		} catch (ClassNotFoundException ex) {
			
			throw new SQLException(ex.getMessage());
			
		}
	}
	
	 /**
     * Método que encerra uma conexão com o banco de dados
     * @throws java.sql.SQLException
     */
    public void closeConnection() throws SQLException {    	
        this.connection.close();
    }     
    
}
