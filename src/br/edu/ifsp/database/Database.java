package br.edu.ifsp.database;

import java.util.Map;
import java.util.Properties;

public abstract class Database {

	protected String databaseName;

	protected String host;

	protected Integer port;

	protected String username;

	protected String password;

	public Database(String databaseName, String username, String password, String host, Integer port) {
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	public Database(Properties properties) {
		this(properties.getProperty("database"), properties.getProperty("username"), properties.getProperty("password"),
				properties.getProperty("host", "localhost"), Integer.parseInt(properties.getProperty("port")));
	}

	public abstract boolean connect() throws Exception;

	public abstract void disconnect() throws Exception;

	public abstract boolean insert(String tableName, Map<String, Object> values) throws Exception;

	public abstract boolean update(String tableName, Map<String, Object> values) throws Exception;

	public abstract boolean delete(String table, String column, Integer id) throws Exception;
	
	public abstract Object searchById(String table, String column, Integer id) throws Exception;
	
	public abstract Object listAll(String table) throws Exception;

	public abstract boolean deleteAll(String string);
}
