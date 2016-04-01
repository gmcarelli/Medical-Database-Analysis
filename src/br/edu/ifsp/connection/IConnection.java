package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnection {

	public Connection connect() throws SQLException;
	
	public boolean disconnect() throws SQLException;
	
	public void query() throws SQLException;
	
	public void startTransaction() throws SQLException;
	
	public void commit() throws SQLException;
	
	public void rollback() throws SQLException;

	public ResultSet update(String query) throws SQLException;	
}
