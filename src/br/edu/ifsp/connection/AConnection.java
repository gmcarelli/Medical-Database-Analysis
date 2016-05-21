package br.edu.ifsp.connection;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.edu.ifsp.helper.QueryHelper;

public abstract class AConnection {

	protected Object connection = null;
	protected QueryHelper queryHelper;

//	protected String host;
//	protected String port;
//	protected String user;	
//	protected String password;
	//protected String tableName;

	/**
	 * Método que cria uma conexão com o banco de dados
	 * 
	 * @return uma conexão com o banco de dados
	 * @throws java.sql.SQLException
	 */
	public abstract Object connect();

	/**
	 * Método que encerra uma conexão com o banco de dados
	 * 
	 * @throws java.sql.SQLException
	 */
	public abstract boolean disconnect();

	public abstract boolean executeInsert(String tableName, Map<String, Object> values);

	public abstract boolean executeUpdate(String tableName, Map<String, Object> values);

	public abstract boolean executeDelete(String tableName, String col, int objectId);

	public abstract Object executeSearch(String tableName, String col, int objectId);

	public abstract Object executeListData(String tableName);

	public abstract int getLastInsertedId(String tableName, String col) throws Exception;

}
