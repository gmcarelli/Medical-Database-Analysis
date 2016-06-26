package br.edu.ifsp.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QueryHelper {

	private String query;

	public String createInsertQueryPostgre(String tableName, Map<String, Object> values) {

		query = "INSERT INTO " + tableName + " VALUES (";

		int i = 0;

		for (int j = 0; j < values.size(); j++) {
			query += "?";

			if (++i < values.size()) {
				query += ", ";
			}

		}

		query += ")";
		
		return query;

	}

	public String createInsertQueryNeo4j(String tableName, Map<String, Object> values) {

		query = "CREATE (n:" + tableName + " {";

		int i = 0;

		for (String key : values.keySet()) {
			query += key + " : ?";

			if (++i < values.size()) {
				query += ", ";
			}

		}

		query += " }) RETURN 1";

		return query;

	}

	public String createUpdateQueryPostgre(String tableName, Map<String, Object> values) {

		query = "UPDATE " + tableName + " SET ";

		int i = 0;

		Set<String> set = values.keySet();

		String column = set.iterator().next();

		Map<String, Object> valuesAux = new HashMap<String, Object>();

		for(String key : values.keySet()) {
			
			valuesAux.put(key, values.get(key));
			
		}

		valuesAux.remove(column);

		for (String key : valuesAux.keySet()) {

			query += key + " = ?";

			if (++i < valuesAux.size()) {

				query += ", ";

			}

		}

		query += " WHERE " + column + " = ?";

		return query;

	}

	public String createUpdateQueryNeo4j(String tableName, Map<String, Object> values) {

		query = "MATCH (n:" + tableName + "{ ";

		int i = 0;

		for (String key : values.keySet()) {

			if (i == 0) {

				query += key + " : ? }) SET ";

				i++;

			} else {

				query += "n." + key + " = ?";

				if (++i < values.size()) {

					query += ", ";

				}
			}

		}

		query += " RETURN 1";

		return query;

	}

}