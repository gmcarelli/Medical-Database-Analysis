package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class IConnection {
	
	protected Connection connection = null;
	
	/**
	 * Método que cria uma conexão com o banco de dados
	 * @return uma conexão com o banco de dados
	 * @throws java.sql.SQLException
	 */
	public abstract Connection connect() throws SQLException;

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

	public ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			return preparedStatement.executeQuery();
			
		}
		
		return null;
		
	}
	
	public abstract boolean executeUpdate(PreparedStatement preparedStatement) throws SQLException;	

}
