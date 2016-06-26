package br.edu.ifsp.dao.neo4j;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.Database;
import br.edu.ifsp.database.neo4j.Neo4jJDBCDatabase;
import br.edu.ifsp.model.MyImage;

public class ReadImageNeo4jTest {

private Database database;
	
	@Before
	public void before() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = new ReaderInputStream(new FileReader(new File("neo4j.test.properties")));
		
		properties.load(inputStream);
		
		this.database = new Neo4jJDBCDatabase(properties);
	}
	
	@Test
	public void readImageTest() throws Exception {
		
		MyImageDAO myImageDAO = new MyImageDAO(this.database);

		MyImage myImage = null;

		int imageId = 1;

		myImage = myImageDAO.searchById(imageId);
		
		assertTrue(myImage.getImageId() == 1);

		assertTrue(myImage.getImageName().equals("ECC.TIFF"));

		assertTrue(myImage.getImageBytes().length == 11397120
);

	}

}
