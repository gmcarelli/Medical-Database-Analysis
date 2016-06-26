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
import br.edu.ifsp.database.neo4j.Neo4jJDBCDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class DeleteMyImageTest {

	private MyImageDAO myImageDAO;
	
	@Before
	public void beforeTest() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = 
			new ReaderInputStream(
				new FileReader(
					new File("resources/neo4j.test.properties")));
		
		properties.load(inputStream);
		
		this.myImageDAO = new MyImageDAO(new Neo4jJDBCDatabase(properties));
	}
	
	@Test
	public void deleteMyImagetest() throws Exception {
		
		MyImage myImage = new MyImage();

		myImage.setImageId(1);

		myImage.setImageName("ECC.TIFF");

		myImage.setImageBytes(ImageHelper.imageFileToByteArray("imageSamples/DCC.TIFF"));

		myImageDAO.insert(myImage);
		
		assertTrue(myImageDAO.delete(myImage.getImageId()));
	}
}
