package br.com.a1402072.projetoic.postgreconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class PostgreConnection {   
    
    private Connection connection;
    
    public PostgreConnection() {
    	
    }
   
    /**
     * Método que  cria uma conexão com o banco de dados
     * @return uma conexão com o banco de dados
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "1qaz2wsx");
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
