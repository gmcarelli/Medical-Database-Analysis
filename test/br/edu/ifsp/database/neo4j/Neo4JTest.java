package br.edu.ifsp.database.neo4j;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.database.Database;

public class Neo4JTest {

	Database connection;
	
	@Before
	public void before() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = new ReaderInputStream(new FileReader(new File("neo4j.test.properties")));
		
		properties.load(inputStream);
		
		new Neo4jJDBCDatabase(null, null, null, null, 0);
	}
	
	@Test
	public void connectionTest() throws Exception {

		assertTrue(connection.connect());

	}

}
