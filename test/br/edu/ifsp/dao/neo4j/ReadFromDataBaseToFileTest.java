package br.edu.ifsp.dao.neo4j;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.Database;
import br.edu.ifsp.database.neo4j.Neo4jJDBCDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class ReadFromDataBaseToFileTest {

	private Database database;
	
	@Before
	public void before() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = new ReaderInputStream(new FileReader(new File("resources/neo4j.test.properties")));
		
		properties.load(inputStream);
		
		this.database = new Neo4jJDBCDatabase(properties);
	}
	
	@Test
	public void FromDataBaseToFileTest() throws Exception {

		MyImageDAO myImageDAO = new MyImageDAO(this.database);

		MyImage myImage = null;

		int imageId = 5;

		myImage = myImageDAO.searchById(imageId);

		try {
			assertTrue(ImageHelper.byteArrayToTiffFile(myImage));

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println(myImage.getImageBytes().length);

		assertTrue(myImage.getImageId() == 5);

		assertTrue(myImage.getImageName().equals("MIXX.TIFF"));

		assertTrue(myImage.getImageBytes().length == 11487232);
		
	}

}
