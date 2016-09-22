package br.edu.ifsp.database.mongo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.database.Database;
import br.edu.ifsp.database.mongodb.MongodbDatabase;

public class MongodbTest {

	Database database;
	
	@Before
	public void beforeTest() throws FileNotFoundException, IOException {
		
		Properties properties = new Properties();
		
		InputStream inputStream = new ReaderInputStream(new FileReader(new File("mongo.test.properties")));
		
		properties.load(inputStream);
		
		this.database = new MongodbDatabase(properties);
	}
	
	@Test
	public void connectionTest() {
		
		try {
			assertNotNull(database.connect());
		} catch(Exception e) {
			assertTrue(e instanceof SQLException);
		}
	}
}
