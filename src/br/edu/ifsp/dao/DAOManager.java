package br.edu.ifsp.dao;

import br.edu.ifsp.neo4j.dao.MyImageDAONeo4J;
import br.edu.ifsp.postgresql.dao.MyImageDAOPostgre;

public class DAOManager {

	public static MyImageDAOPostgre myImageDAOPostgre() {

		return new MyImageDAOPostgre();

	}
	
	public static MyImageDAONeo4J myImageDAONeo4J() {
		
		return new MyImageDAONeo4J();
		
	}

}
