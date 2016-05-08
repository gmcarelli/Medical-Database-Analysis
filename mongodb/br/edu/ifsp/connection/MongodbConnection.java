package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MongodbConnection implements IConnection {   
    
    private Connection connection = null;
	private PreparedStatement preparedStatement;
      
    /**
     * M�todo que  cria uma conexao com o banco de dados
     * @return uma conexao com o banco de dados
     * @throws java.sql.SQLException
     */
    public Connection connect() throws SQLException {
        
    	try {
    		
            Class.forName("org.mongodb.Driver");
            
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
	public ResultSet executeQuery() throws Exception {
		
		if(!this.connection.isClosed() && this.connection != null) {
			return this.preparedStatement.executeQuery();
		}
		
		return null;
	}

	@Override
	public void startTransaction() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean executeUpdate() throws Exception {
		
		if(!this.connection.isClosed() && this.connection != null) {
			return this.preparedStatement.executeUpdate() > 0;
		}
		
		return false;
	}
    
	
   
}
