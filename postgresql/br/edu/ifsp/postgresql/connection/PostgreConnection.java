package br.edu.ifsp.postgresql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.edu.ifsp.connection.IConnection;

public class PostgreConnection implements IConnection {   
    
    private Connection connection = null;
	
	/**
     * Método que  cria uma conexão com o banco de dados
     * @return uma conexão com o banco de dados
     * @throws java.sql.SQLException
     */
    public Connection connect() throws SQLException {
        
    	try {
    		
            Class.forName("org.postgresql.Driver");
            
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "1qaz2wsx");
                        
        } catch (ClassNotFoundException ex) {
        	
            throw new SQLException(ex.getMessage());
            
        }
    	
    	return this.connection;
    }

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

	@Override
	public ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {	
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			return preparedStatement.executeQuery();
			
		}
		
		return null;
		
	}

	@Override
	public void startTransaction() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws SQLException {
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			this.connection.commit();
			
		}
		
	}

	@Override
	public void rollback() throws SQLException {
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			this.connection.rollback();
			
		}
		
	}

	@Override
	public boolean executeUpdate(PreparedStatement preparedStatement) throws SQLException {		
		
		if(!this.connection.isClosed() && this.connection != null) {
			
			return preparedStatement.executeUpdate() > 0;
			
		}
		
		return false;
	}
    
	
}
