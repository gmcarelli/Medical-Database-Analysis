package br.edu.ifsp.postgresql.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifsp.connection.PostgreConnection;

public class QueryHelper {

	protected PostgreConnection postgreConnection;
	protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    protected String query;

    public QueryHelper() {
    	this.postgreConnection = new PostgreConnection();
    }

    public int getUltimoIdCadastrado(String tabela, String primaryKeyField) throws SQLException {
        this.query = "SELECT " + primaryKeyField  
                + " FROM " + tabela
                + " ORDER BY " + primaryKeyField
                + " DESC LIMIT 1";

        this.preparedStatement = this.postgreConnection.getConnection().prepareStatement(this.query);

        this.resultSet = this.executeQuerySelect();

        if (resultSet.next()) {
            return resultSet.getInt(primaryKeyField);
        }

        return 0;
    }
    
    /**
    *
    * @return
    * @throws java.sql.SQLException
    */
   public boolean executeUpdate() throws SQLException {
       int aux;
       aux = this.preparedStatement.executeUpdate();
       this.postgreConnection.closeConnection();
       return aux > 0;
   }

   /**
    *
    * @return
    * @throws java.sql.SQLException
    */
   public ResultSet executeQuerySelect() throws SQLException {
       return this.preparedStatement.executeQuery();
   }  
   
}
