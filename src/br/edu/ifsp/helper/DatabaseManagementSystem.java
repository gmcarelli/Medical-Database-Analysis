package br.edu.ifsp.helper;

public enum DatabaseManagementSystem {

	MONGO("mongo"),
	NEO4J("neo4j"),
	PGSQL("pgsql");
	
	private final String databaseManagementSystem;
	
	DatabaseManagementSystem(String databaseManagementSystem) {
		this.databaseManagementSystem = databaseManagementSystem;
	}
	
	public String getValue() {
		return this.databaseManagementSystem;
	}
	
	public boolean isMongo() {
		return this.databaseManagementSystem.equals(MONGO.getValue());
	}
	
	public boolean isNeo4j() {
		return this.databaseManagementSystem.equals(NEO4J.getValue());
	}
	
	public boolean isPgSQL() {
		return this.databaseManagementSystem.equals(PGSQL.getValue());
	}
}
