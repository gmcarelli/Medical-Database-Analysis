package br.com.a1402072.mia.neoj4connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Neo4JConnection {

	private static Connection connection;
	
	public void getConnection() throws SQLException {
		try {
			Class.forName("org.neo4j.jdbc.Driver");
			connection =  DriverManager.getConnection("jdbc:neo4j://localhost:7474/graph.db", "neo4j", "1qaz2wsx");
			return connection;
		} catch (ClassNotFoundException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
	
	 /**
     * M�todo que encerra uma conex�o com o banco de dados
     * @throws java.sql.SQLException
     */
    public static void closeConnection() throws SQLException {
        connection.close();
    }     
    
}
