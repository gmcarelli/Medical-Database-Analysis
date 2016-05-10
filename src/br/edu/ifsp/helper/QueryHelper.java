package br.edu.ifsp.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryHelper {
	
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
    
}
