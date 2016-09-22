package br.edu.ifsp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.mongodb.MongodbDatabase;
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
			this.dao = new MyImageDAO(new MongodbDatabase(databaseName, username, password));
			break;
		case NEO4J:
			this.dao = new MyImageDAO(new Neo4jJDBCDatabase(databaseName, username, password));
			break;
		case PGSQL:
			this.dao = new MyImageDAO(new PostgreSQLDatabase(databaseName, username, password));
			break;
		default:
			throw new Exception("invalid database system");
		}

		if (operationType.valueOf(OperationType.PERSISTENCE))
			result &= this.dao.insert(imagesList);
		
		else if (operationType.valueOf(OperationType.RETRIVEMENT)) {
			
			Set<Integer> ids = new HashSet<Integer>();
			
			for (MyImage image : imagesList)
				ids.add(image.getImageId());
			
			result &= this.dao.search(ids).size() == imagesList.size();
		}
		
		return result;
	}

}
