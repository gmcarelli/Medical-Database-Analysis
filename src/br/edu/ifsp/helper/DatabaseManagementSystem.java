package br.edu.ifsp.helper;

public enum DatabaseManagementSystem {

	MONGO("mongo"), NEO4J("neo4j"), PGSQL("pgsql");

	private final String value;

	DatabaseManagementSystem(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public boolean isMongo() {
		return this.value.equals(MONGO.getValue());
	}

	public boolean isNeo4j() {
		return this.value.equals(NEO4J.getValue());
	}

	public boolean isPgSQL() {
		return this.value.equals(PGSQL.getValue());
	}

	public static DatabaseManagementSystem getValue(String value) {
		for (DatabaseManagementSystem e : DatabaseManagementSystem.values())
			if (e.value.toLowerCase().equals(value.toLowerCase()))
				return e;

		return null;
	}
}
