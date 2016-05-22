package br.edu.ifsp.connection;

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
	
	public abstract Object connect();

	public abstract boolean disconnect();

	public abstract boolean executeInsert(String tableName, Map<String, Object> values);

	public abstract boolean executeUpdate(String tableName, Map<String, Object> values);

	public abstract boolean executeDelete(String tableName, String column, int objectId);

	public abstract Object executeSearch(String tableName, String column, int objectId);

	public abstract Object executeListData(String tableName);

	public abstract int getLastInsertedId(String tableName, String column);

}
