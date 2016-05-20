package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AConnection {
	
	protected Connection connection = null;
	
	protected String host;
	
	protected int port;
	
	protected String databaseName;	
	
	/**
	 * Método que cria uma conexão com o banco de dados
	 * @return uma conexão com o banco de dados
	 * @throws java.sql.SQLException
	 */
	public abstract AConnection connect() throws SQLException;

	/**
     * Método que encerra uma conexão com o banco de dados
     * @throws java.sql.SQLException
     */
    public boolean disconnect() throws SQLException {
    	
    	boolean result = true;
    	
    	try {
    		
    		this.connection.close();
    		
    	} catch(Exception e) {
    
    		result = false;
    		
    	}
    	
    	return result;
    }

	public ResultSet executeQuery() throws SQLException {
		
		ResultSet result = null;
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			return preparedStatement.executeQuery();
			
		}
		
		return null;
		
	}
	
	public abstract boolean executeUpdate(String string, Map<String, Object> values, ) throws SQLException;

	public abstract boolean executeInsert(String string, Map<String, Object> values);

}
