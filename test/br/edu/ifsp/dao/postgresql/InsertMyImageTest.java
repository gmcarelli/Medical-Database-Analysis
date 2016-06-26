package br.edu.ifsp.dao.postgresql;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.postgresql.PostgreSQLDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class InsertMyImageTest {

	private MyImageDAO myImageDAO;
	
	private final Integer ID = 9999999;
	
	@Before
	public void before() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = 
			new ReaderInputStream(
				new FileReader(
					new File("resources/pgsql.test.properties")));
		
		properties.load(inputStream);
		
		this.myImageDAO = new MyImageDAO(new PostgreSQLDatabase(properties));
	}
	
	@Test
	public void insertImageTest() throws Exception {

		MyImage myImage = new MyImage();

		myImage.setImageId(ID);

		myImage.setImageName("DCC.TIFF");

		myImage.setImageBytes(ImageHelper.imageFileToByteArray("resources/images/DCC.TIFF"));

		assertTrue(myImageDAO.insert(myImage));
	}
	
	@After
	public void afterTest() throws Exception {
		myImageDAO.delete(ID);
	}
}
