package br.edu.ifsp.helper;

import java.util.Map;
import java.util.Set;

public class QueryHelper {

	private String query;

	public String setInsertQuery(String tableName, Map<String, Object> values) {

		query = "INSERT INTO ? (";

		int i = 0;

		for (String key : values.keySet()) {
			query += key + "?";

			if (++i < values.size()) {
				query += ", ";
			}

		}

		query += ") VALUES (";

		for (i = 0; i < values.size(); i++) {
			query += "?";

			if (++i < values.size()) {
				query += ", ";
			}

		}

		query += ")";

		return query;

	}

	public String setUpdateQuery(String tableName, Map<String, Object> values) {

		query = "UPDATE ? SET ";

		int i = 0;

		for (String key : values.keySet()) {
			
			query += key + " = ?";

			if (++i < values.size()) {
				
				query += ", ";
				
			} else if (++i == values.size() - 1) {
				
				Set<String> set = values.keySet();
				
				query += " WHERE " + set.iterator().next() + " = ?";
				
			}

		}		
		
		return query;

	}
	
	
}
