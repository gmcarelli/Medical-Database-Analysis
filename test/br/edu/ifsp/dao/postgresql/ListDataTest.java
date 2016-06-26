package br.edu.ifsp.dao.postgresql;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.engine.io.ReaderInputStream;

import br.edu.ifsp.dao.MyImageDAO;
import br.edu.ifsp.database.postgresql.PostgreSQLDatabase;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class ListDataTest {

	private MyImageDAO myImageDAO;
	
	private Integer[] IDS = new Integer[] {1000001, 1000002, 1000003, 1000004};
	
	@Before
	public void before() throws Exception {
		
		Properties properties = new Properties();
		
		InputStream inputStream = 
			new ReaderInputStream(
				new FileReader(
					new File("resources/pgsql.test.properties")));
		
		properties.load(inputStream);
		
		myImageDAO = new MyImageDAO(new PostgreSQLDatabase(properties));
		
		File folder = new File("resources/images");
		
		File[] listOfFiles = folder.listFiles();
		
		List<MyImage> list = new ArrayList<MyImage>();
		
		int imageId = 0;
		
		for (File file : listOfFiles) {
			
			MyImage image = new MyImage();
			
			image.setImageId(IDS[imageId++]);
			image.setImageName(file.getName());
			image.setImageBytes(ImageHelper.imageFileToByteArray(file));
			
			list.add(image);
		}
		
		myImageDAO.insert(list);
	}
	
	@Test
	public void listDataTest() throws Exception {
		
		List<MyImage> myImageList = myImageDAO.listAll();
		
		assertTrue(!myImageList.isEmpty());
		
		assertTrue(myImageList.size() == 5);
		
		assertTrue(myImageList instanceof ArrayList<?>);
		
		assertTrue(myImageList.get(0) != null);
	}
	
	@After
	public void afterTest() throws Exception {
		myImageDAO.delete(IDS);
	}
}
