package br.edu.ifsp.neo4j;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.database.Database;
//import br.edu.ifsp.database.neo4j.Neo4j;

public class Neo4jConnectionTest {

	private Database database;

	@Test
	public void test() throws Exception {

		Properties properties = new Properties();

		InputStream inputStream = new ReaderInputStream(new FileReader(new File("resources/neo4j.test.properties")));

		properties.load(inputStream);

		//this.database = new Neo4j(properties);
		
		assertTrue(this.database.connect());
		
	}

}
