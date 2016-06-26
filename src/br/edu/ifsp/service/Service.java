package br.edu.ifsp.service;

import java.util.List;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.mongodb.MongoDatabase;
import br.edu.ifsp.database.neo4j.Neo4jJDBCDatabase;
import br.edu.ifsp.database.postgresql.PostgreSQLDatabase;
import br.edu.ifsp.helper.DatabaseManagementSystem;
import br.edu.ifsp.helper.OperationType;
import br.edu.ifsp.model.MyImage;

/**
 * 
 * @author Lucas Venezian Povoa
 *
 */
public class Service {

	MyImageDAO dao;

	public boolean performTest(DatabaseManagementSystem dbms, String username, String password, 
			String databaseName, OperationType operationType, List<MyImage> imagesList) throws Exception {

		boolean result = true;
		
		switch (dbms) {
		case MONGO:
			this.dao = new MyImageDAO(new MongoDatabase(databaseName, username, password));
			break;
		case NEO4J:
			this.dao = new MyImageDAO(new Neo4jJDBCDatabase(databaseName, username, password));
			break;
		case PGSQL:
			this.dao = new MyImageDAO(new PostgreSQLDatabase(databaseName, username, password));
			break;
		}

		if (operationType.valueOf(OperationType.PERSISTENCE)) {

			for (MyImage image : imagesList)
				result &= this.dao.insert(image);
		}
		
		return result;
	}

}
