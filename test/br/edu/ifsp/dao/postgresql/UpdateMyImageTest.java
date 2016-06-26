package br.edu.ifsp.dao.postgresql;

import static org.junit.Assert.assertEquals;
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
import br.edu.ifsp.database.postgresql.PostgreSQLDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class UpdateMyImageTest {

	private Database database;
	
	@Before
	public void before() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = 
			new ReaderInputStream(
				new FileReader(
					new File("resources/pgsql.test.properties")));
		
		properties.load(inputStream);
		
		this.database = new PostgreSQLDatabase(properties);
	}
	
	@Test
	public void UpdateMyImagetest() throws Exception {

		MyImageDAO myImageDAO = new MyImageDAO(this.database);

		MyImage myImage = new MyImage();

		myImage.setImageId(5);

		myImage.setImageName("ECC.TIFF");

		myImage.setImageBytes(ImageHelper.imageFileToByteArray("imageSamples/DCC.TIFF"));

		myImageDAO.insert(myImage);

		myImage = myImageDAO.searchById(myImage.getImageId());

		assertEquals("ECC.TIFF", myImage.getImageName());

		myImage.setImageName("DCC.TIFF");

		assertTrue(myImageDAO.update(myImage));
		
		myImage = myImageDAO.searchById(myImage.getImageId());

		assertEquals("DCC.TIFF", myImage.getImageName());
	}
}
