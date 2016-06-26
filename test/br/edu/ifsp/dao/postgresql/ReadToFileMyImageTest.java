package br.edu.ifsp.dao.postgresql;

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

public class ReadToFileMyImageTest {

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
	public void ReadToFileTest() throws Exception {

		MyImageDAO myImageDAO = new MyImageDAO(this.database);

		Integer imageId = 1;

		MyImage myImage = myImageDAO.searchById(imageId);

		assertTrue(ImageHelper.byteArrayToTiffFile(myImage));
	}
}
