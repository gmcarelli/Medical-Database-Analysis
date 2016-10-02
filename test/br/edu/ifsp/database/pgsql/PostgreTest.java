package br.edu.ifsp.database.pgsql;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.database.Database;
import br.edu.ifsp.database.postgresql.PostgreSQLDatabase;

public class PostgreTest {

	Database database;
	
	@Before
	public void before() throws FileNotFoundException, IOException {
		
		Properties properties = new Properties();
		
		InputStream inputStream = 
			new ReaderInputStream(
				new FileReader(
					new File("resources/pgsql.test.properties")));
		
		properties.load(inputStream);
		
		this.database = new PostgreSQLDatabase(properties);
	}	

	@Test
	public void connectionTest() throws Exception {
		assertTrue(database.connect());
	}	
}