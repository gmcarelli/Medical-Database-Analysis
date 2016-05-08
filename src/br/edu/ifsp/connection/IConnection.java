package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IConnection {

	public Connection connect() throws Exception;
	
	public boolean disconnect() throws Exception;
	
	public ResultSet executeQuery() throws Exception;
	
	public void startTransaction() throws Exception;
	
	public void commit() throws Exception;
	
	public void rollback() throws Exception;

	public boolean executeUpdate() throws Exception;	
	
}
